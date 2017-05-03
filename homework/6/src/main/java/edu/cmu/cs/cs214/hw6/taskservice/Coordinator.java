package edu.cmu.cs.cs214.hw6.taskservice;

import java.io.IOException;
import java.rmi.Remote;
import java.util.List;
import java.util.Map;

import edu.cmu.cs.cs214.hw6.taskservice.util.Task;

/**
 * The coordinator server which receives tasks from the client and dispatches
 * all these tasks to different workers
 *
 * @author liang rao
 */
public interface Coordinator extends Remote{

    /**
     * Dispatch all the tasks from client to the separated worker
     * @param taskList all tasks received from client
     * @return The log of all tasks
     * @throws IOException
     * @throws InterruptedException
     */
    Map<String, String> aggregate(List<Task> taskList) throws IOException, InterruptedException;
}
