package edu.cmu.cs.cs214.hw6.taskservice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.cs.cs214.hw6.sequential.buildsystem.BuildFindbugs;
import edu.cmu.cs.cs214.hw6.sequential.sentiment.GithubAnalysis;
import edu.cmu.cs.cs214.hw6.taskservice.util.Task;

/**
 * Created by raoliang on 4/29/17.
 */
public class Client {
    private static final ServerInfo aggregate = new ServerInfo("127.0.0.1", "Aggregate", 15213);

    public static void main(String[] args) throws IOException, NotBoundException, InterruptedException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Task findBug = new BuildFindbugs();
//        findBug.setTaskName("Task1:buildFindBug");
        Task githubAnalysis = new GithubAnalysis();
        githubAnalysis.setTaskName("Task1:githubAnalysis");
        List<Task> taskList = new ArrayList<>();
        //taskList.add(findBug);
        taskList.add(githubAnalysis);
        Coordinator service = (Coordinator) LocateRegistry
                .getRegistry(aggregate.getHostName(), aggregate.getPort())
                .lookup(aggregate.getServiceName());

        System.out.println(service.aggregate(taskList));
    }
}
