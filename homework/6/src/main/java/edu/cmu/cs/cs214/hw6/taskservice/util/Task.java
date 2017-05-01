package edu.cmu.cs.cs214.hw6.taskservice.util;

import java.util.List;
import java.util.Set;
import java.io.File;

/**
 * Created by raoliang on 4/30/17.
 */
public interface Task {
    public List<Set<String>> getFunctionName();

    public File getWorkingDirectory();

    public String getTaskName();

    public void setTaskName(String name);

}
