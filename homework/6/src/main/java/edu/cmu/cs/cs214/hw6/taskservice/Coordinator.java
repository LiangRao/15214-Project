package edu.cmu.cs.cs214.hw6.taskservice;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Remote;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import edu.cmu.cs.cs214.hw6.taskservice.util.SystemStep;
import edu.cmu.cs.cs214.hw6.taskservice.util.Task;

/**
 * Created by raoliang on 4/28/17.
 */
public interface Coordinator extends Remote{
    Map<String, String> aggregate(List<Task> taskList) throws IOException, InterruptedException;
}
