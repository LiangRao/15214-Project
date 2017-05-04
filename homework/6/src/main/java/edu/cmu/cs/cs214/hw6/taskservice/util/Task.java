package edu.cmu.cs.cs214.hw6.taskservice.util;

import java.util.List;
import java.util.Set;
import java.io.File;

/**
 * A Task interface for all the tasks needing to send to Coordinator
 */
public interface Task {
    /**
     * Get the names of functions which need to execute in the worker
     * @return the names of functions which need to execute in the worker
     */
    public List<Set<String>> getFunctionName();

    /**
     * Get the working directory of the task
     * @return the working directory of the task
     */
    public File getWorkingDirectory();

    /**
     * Get the name of the Task
     * @return the name of the task
     */
    public String getTaskName();

    /**
     * Set the name to the task
     * @param name the name of the task
     */
    public void setTaskName(String name);

}
