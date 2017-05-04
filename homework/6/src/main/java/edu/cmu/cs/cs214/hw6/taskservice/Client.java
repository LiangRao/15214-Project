package edu.cmu.cs.cs214.hw6.taskservice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw6.sequential.textwordcount.TextAnalysis;
import edu.cmu.cs.cs214.hw6.sequential.buildsystem.BuildFindbugs;
import edu.cmu.cs.cs214.hw6.sequential.sentiment.GithubAnalysis;
import edu.cmu.cs.cs214.hw6.taskservice.util.Task;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * A client which sends all tasks needing to process to coordinator
 *
 * @author liang rao
 */
public class Client {
    @Immutable
    private static final ServerInfo aggregate = new ServerInfo("127.0.0.1", "Aggregate", 15212);

    /**
     * The main class to set up taskList and send them to Coordinator
     * @param args the arguments of main
     * @throws NotBoundException RMI exception
     * @throws InterruptedException the exception thrown by Coordinator
     * @throws RemoteException RMI exception
     */
    public static void main(String[] args) throws NotBoundException, InterruptedException,RemoteException {
        Task findBug = new BuildFindbugs();
        findBug.setTaskName("Task1:buildFindBug");
        Task githubAnalysis = new GithubAnalysis();
        githubAnalysis.setTaskName("Task2:githubAnalysis");
        Task analysis = new TextAnalysis();
        analysis.setTaskName("Task3:analysisText");
        List<Task> taskList = new ArrayList<>();

      //  taskList.add(findBug);
        //taskList.add(githubAnalysis);
        taskList.add(analysis);

        Coordinator service = (Coordinator) LocateRegistry
                .getRegistry(aggregate.getHostName(), aggregate.getPort())
                .lookup(aggregate.getServiceName());
        Map<String, String> logResult = service.aggregate(taskList);
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Task1:buildFindBug");
        System.out.println(logResult.get("Task1:buildFindBug"));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Task2:githubAnalysis");
        System.out.println(logResult.get("Task2:githubAnalysis"));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Task3:analysisText");
        System.out.println(logResult.get("Task3:analysisText"));
    }
}
