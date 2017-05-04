package edu.cmu.cs.cs214.hw6.sequential.textwordcount;

import java.util.*;

/**
 * A TextProcessor class to deal with given file
 */
public class TextProcessor{
    Scanner sc;
    HashMap<String, Integer> lengthMap = new HashMap<String, Integer> ();
    HashMap<String, Integer> frequency = new HashMap<String, Integer>();
    /**
     * A constructor which initializes a TextProcessor with a given fileName
     * @param fileName the name of the file needing to be opened
     */
    public TextProcessor(String fileName) {
        sc = TextReader.openFile(fileName);
        while (true){
            if (sc.hasNext()){
                String word = sc.next();
                word = word.replaceAll( "[\\p{P}]", "" );
                if(word.matches(".*\\d+.*")==false) {
                    if (frequency.containsKey(word)) {
                        int value = frequency.get(word);
                        lengthMap.put(word, word.length());
                        frequency.replace(word, value, value + 1);
                    } else {
                        lengthMap.put(word, word.length());
                        frequency.put(word, 1);
                    }
                }
            }
            else{
                break;
            }
        }
    }

    /**
     * Return the shortest words from the given file
     * @return the shortest words
     */
    public Collection<String> getShortestWords() {
        Collection<String> shortest = new HashSet<String>();
        int smallest = 10000000;
        for (String key : lengthMap.keySet()){
            int value = lengthMap.get(key);
            if (value != 0) {
                if (smallest > value) {
                    smallest = value;
                    shortest.clear();
                    shortest.add(key);
                } else if (smallest == value) {
                    shortest.add(key);
                }
            }
        }
        return shortest;
    }

    /**
     * Return the longest words in the file
     * @return the longest words
     */
    public Collection<String> getLongestWords() {
        Collection<String> longest = new HashSet<String>();
        int largest = 0;
        for (String key : lengthMap.keySet()){
            int value = lengthMap.get(key);
            if (largest < value){
                largest = value;
                longest.clear();
                longest.add(key);
            }
            else if (largest == value){
                longest.add(key);
            }
        }
        return longest;
    }

    /**
     * Return the letters which are most likely arise at the
     * start of a word from the given file. Ignore the frequency
     * of these words
     * @return the letters which are most likely arise at the
     *         start of a word from the given file.
     */
    public Collection<Character> mostCommonFirstUnweighted() {
        Collection<Character> firstUnweighted = new HashSet<Character>();
        HashMap<Character, Integer> letters = new HashMap<Character, Integer> ();
        for (String key : lengthMap.keySet()){
            if (key.length() >=1) {
                char firstLetter = key.charAt(0);
                if (letters.containsKey(firstLetter)) {
                    int value = letters.get(firstLetter);
                    letters.replace(firstLetter, value, value + 1);
                } else {
                    letters.put(firstLetter, 1);
                }
            }
        }
        int maxvalue = 0;
        for (Character key : letters.keySet()) {
            int val = letters.get(key);
            if (maxvalue < val) {
                maxvalue = val;
                firstUnweighted.clear();
                firstUnweighted.add(key);
            } else if (maxvalue == val) {
                firstUnweighted.add(key);
            }
        }
        return firstUnweighted;
    }

    /**
     * Return the letters which are most likely arise at the
     * start of a word from the given file. Consider the frequency
     * of these words
     * @return Return the letters which are most likely arise at the
     *         start of a word from the given file.
     */
    public Collection<Character> mostCommonFirstWeighted() {

        Collection<Character> firstWeighted = new HashSet<Character>();
        HashMap<Character, Integer> letters = new HashMap<Character, Integer> ();
        for (String key : frequency.keySet()){
            if (key.length() >=1) {
                char firstLetter = key.charAt(0);
                if (letters.containsKey(firstLetter)) {
                    int value = letters.get(firstLetter);
                    letters.replace(firstLetter, value, value + frequency.get(key));
                } else {
                    letters.put(firstLetter, frequency.get(key));
                }
            }
        }
        int maxvalue = 0;
        for (Character key : letters.keySet()) {
            int val = letters.get(key);
            if (maxvalue < val) {
                maxvalue = val;
                firstWeighted.clear();
                firstWeighted.add(key);
            } else if (maxvalue == val) {
                firstWeighted.add(key);
            }
        }
        return firstWeighted;
    }

    /**
     * Return the most common words with given length in the file
     * @param length the given length
     * @return the most common words with given length
     */
    public Collection<String> mostCommon(int length) {
        Collection<String> mostCommon = new HashSet<String>();
        int maxvalue = 0;
        for (String key : lengthMap.keySet()){
            int value = lengthMap.get(key);
            if (value == length) {
                for (String k : frequency.keySet()){
                    int val = frequency.get(key);
                    if (maxvalue < val) {
                        maxvalue = val;
                        mostCommon.clear();
                        mostCommon.add(key);
                    } else if (maxvalue == val) {
                        mostCommon.add(key);
                    }
                }
            }
        }
        return mostCommon;
    }


}
