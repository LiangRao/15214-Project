package edu.cmu.cs.cs214.hw6.sequential.textwordcount;

import edu.cmu.cs.cs214.hw6.taskservice.util.Task;
import net.jcip.annotations.GuardedBy;

import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 * Text analysis
 */
public class TextAnalysis implements Task,Serializable{
    @GuardedBy("textProc")
    private TextProcessor textProc;
    private String taskName;

    /**
     * Download a file from given url
     * @return project log
     */
    public String setUp(){
        System.out.println("Setting up...");
        TextReader textReader = new TextReader();
        String url = "http://gutenberg.net.au/ebooks02/0200161.txt";
        String dir = "src/resources/gonewithwind.txt";
        textReader.downloadFile(url,dir);
        textProc = new TextProcessor(dir);
        return "";

    }

    /**
     * Analyze the longest words and shortest words in the file
     * @return the longest words and shortest words in the file
     */
    public String analysisFirst(){
        StringBuilder sb = new StringBuilder();
        System.out.println(">First analysis Running....\n");
        synchronized (textProc) {
            Collection<String> longestWords = textProc.getLongestWords();

            System.out.println("Longest words: " + longestWords + "\n");
            sb.append("Longest words: " + longestWords + "\n");
        }
        synchronized (textProc) {
            Collection<String> shortestWords = textProc.getShortestWords();
            System.out.println("Shortest words: " + shortestWords);
            sb.append("Shortest words: " + shortestWords + "\n");
        }
        return sb.toString();
    }

    /**
     * Analyze the most common words of length 6 and most common first
     * character in the file
     * @return the most common words of length 6 and most common first
     * character in the file
     */
    public String analysisSecond(){
        StringBuilder sb = new StringBuilder();
        System.out.println(">Second analysis Running....\n");
        synchronized (textProc) {
            Collection<String> common = textProc.mostCommon(6);
            System.out.println("Most common word of length 6: " + common);
            sb.append("Most common word of length 5: " + common + "\n");
        }
        synchronized (textProc) {
            Collection<Character> mostCommon = textProc.mostCommonFirstWeighted();
            System.out.println("Most common first character: " + mostCommon);
            sb.append("Most common first character: " + mostCommon + "\n");
        }
        synchronized (textProc) {
            Collection<Character> mostCommonIgnore = textProc.mostCommonFirstUnweighted();
            System.out.println("Most common first character, ignoring word frequency: " + mostCommonIgnore);
            sb.append("Most common first character, ignoring word frequency: " + mostCommonIgnore + "\n");
        }
        return sb.toString();
    }

    @Override
    public List<Set<String>> getFunctionName() {
        List resultList = new ArrayList();
        Set<String> setUp = new HashSet<>();
        setUp.add("setUp");
        Set<String> function = new HashSet<>();
        function.add("analysisFirst");
        function.add("analysisSecond");
        resultList.add(setUp);
        resultList.add(function);
        return resultList;
    }

    @Override
    public File getWorkingDirectory() {
        return null;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public void setTaskName(String name) {
        taskName = name;
    }

}
