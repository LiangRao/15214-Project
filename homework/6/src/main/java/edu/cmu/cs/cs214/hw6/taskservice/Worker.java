package edu.cmu.cs.cs214.hw6.taskservice;

import edu.cmu.cs.cs214.hw6.taskservice.util.Task;

import java.io.IOException;
import java.rmi.Remote;

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
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InterruptedException
     */
    String exec(Task task) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InterruptedException;

}
