package edu.cmu.cs.cs214.hw6.taskservice;

import edu.cmu.cs.cs214.hw6.taskservice.util.Task;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A remote work to execute the task sent from Coordinator
 *
 * @author liang rao
 */
public interface Worker extends Remote {
    /**
     * Execute a single task sent from Coordinator
     * @param task the task needing to execute
     * @return The log of the task
     * @throws IllegalAccessException if the task is illegal and cannot restore by java reflection
     * @throws InstantiationException fail to restore a task by java reflection
     * @throws NoSuchMethodException cannot get a specific method from task class
     * @throws InterruptedException ExecutorService is interrupted by others
     * @throws RemoteException RMI exception
     */
    String exec(Task task) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InterruptedException,RemoteException;

}
