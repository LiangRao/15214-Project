package edu.cmu.cs.cs214.hw6.taskservice;

import edu.cmu.cs.cs214.hw6.taskservice.util.Task;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by raoliang on 4/29/17.
 */
public class RemoteWorker implements Worker{

    private ExecutorService executor = Executors.newCachedThreadPool();
    @Override
    public String exec(Task task) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InterruptedException  {
        List<Set<String>> stepNum = task.getFunctionName();
        int step = stepNum.size();
        Class<?> taskClass = task.getClass();
        Object invokeTester = taskClass.newInstance();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < step; i++) {
            Set<String> funName = stepNum.get(i);
            int setSize = stepNum.get(i).size();
            List<Callable<String>> callables = new ArrayList<>();
            for (String str : funName) {
                Method method = taskClass.getMethod(str, new Class[]{});
                callables.add(()->{

                   String log= (String)method.invoke(invokeTester, new Object[]{});
                   return log;
                });
            }
            List<Future<String>> results = executor.invokeAll(callables);

            for (Future<String> future: results){
                try {
                    String log =future.get();
                    sb.append(log);
                    //future.cancel()
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }
        File workDirec = task.getWorkingDirectory();
        if (workDirec!=null) {
            if (workDirec.isFile()){
                workDirec.delete();
            }else if (workDirec.isDirectory()){
                deleteDir(workDirec);
            }
        }
        return sb.toString();
    }

    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();

            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * The main function to launch a single worker
     * @param args the arguments of main
     * @throws IOException
     * @throws AlreadyBoundException
     */
    public static void main(String[] args) throws IOException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        int port = Integer.parseInt(args[1]);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind(args[0],
                UnicastRemoteObject.exportObject(new RemoteWorker(), port));
        System.out.println("File Service running");
    }
}
