package edu.cmu.cs.cs214.hw6.taskservice;


import edu.cmu.cs.cs214.hw6.taskservice.util.Task;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;


/**
 * Created by raoliang on 4/29/17.
 */
public class CoordinatorServer implements Coordinator{
    private ExecutorService executor = Executors.newCachedThreadPool();
    // Immutable
    //private final Map<ServerInfo, RemoteWorker> services;
    private final List<Worker> services;

    public CoordinatorServer(ServerInfo... servers) throws RemoteException, NotBoundException {
        services = new ArrayList<>();
        for (ServerInfo serverInfo : servers) {
            Registry registry = LocateRegistry.getRegistry(serverInfo.getHostName(), serverInfo.getPort());
            Worker service = (Worker) registry.lookup(serverInfo.getServiceName());
            services.add(service);
        }
    }


    @Override
    public Map<String, String> aggregate(List<Task> taskList) throws IOException, InterruptedException,RemoteException {
        List<Callable<String>> tasks = buildTaskList(taskList);
        System.out.println(tasks.size());
        List<Future<String>> results = executor.invokeAll(tasks);
        List<String> taskName = new ArrayList<>();
        Map<String, String> resultMap = new HashMap<>();
        for (Task task : taskList) {
            taskName.add(task.getTaskName());
        }
        //int taskSize = taskList.size();
        int i =0;
        for (Future<String> future : results) {
            try {
                resultMap.put(taskName.get(i), future.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println("resultMap");
        System.out.println(resultMap);
        return resultMap;
    }

    private List<Callable<String>> buildTaskList(List<Task> taskList){
        List<Callable<String>> result = new ArrayList<>();
        int size = services.size();
        int taskSize = taskList.size();
        for (int i = 0; i < taskSize; i++) {
            Task task = taskList.get(i);
            int serverNum = i%taskSize;
            result.add(() -> {
                    return services.get(serverNum).exec(task);
            });
        }
        return result;
    }

    public static void main(String[] args) throws IOException, NotBoundException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        int port = Integer.parseInt(args[0]);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind("Aggregate",
                UnicastRemoteObject.exportObject(
                        new CoordinatorServer(
                                new ServerInfo("127.0.0.1", "Peer1", 15215),
                                new ServerInfo("127.0.0.1", "Peer2", 15216)
                        ), port));
        System.out.println("aggregate service running");
    }


}
