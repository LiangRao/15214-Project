package edu.cmu.cs.cs214.hw6.taskservice;

import edu.cmu.cs.cs214.hw6.taskservice.util.Task;

import java.io.IOException;
import java.rmi.Remote;

/**
 * Created by raoliang on 4/28/17.
 */
public interface Worker extends Remote {

    String exec(Task task) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InterruptedException;

}
