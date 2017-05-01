package edu.cmu.cs.cs214.hw6.taskservice.util;

import java.io.IOException;

/**
 * Created by raoliang on 4/29/17.
 */
public interface SystemStep<T> {
    void stepRun(T data, String workingDirector) throws IOException, ClassNotFoundException, InterruptedException;
}
