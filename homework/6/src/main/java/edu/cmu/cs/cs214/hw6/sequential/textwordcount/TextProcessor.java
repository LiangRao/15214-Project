package edu.cmu.cs.cs214.hw6.sequential.textwordcount;

import java.util.*;

public class TextProcessor{
    /*
    Reads in a literary work and analyze's the word use.
     */
    Scanner sc;
    HashMap<String, Integer> lengthMap = new HashMap<String, Integer> ();
    HashMap<String, Integer> frequency = new HashMap<String, Integer>();

    /**
     * Return the shortest word or words from the literary work.
     * Since most languages contain many 1-letter words,
     * a collection is returned.
     * @return the shortest word(s)
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
     * Return the longest word or words from the literary work.
     * There will often only be one word, but in case of a tie,
     * a collection is returned.
     * @return the longest word(s)
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
     * Return the letters that are most likely to appear at
     * the start of a word from the literary work. Frequency
     * of the words involved is not taken into account. There
     * will often only be one letter, but in case of a tie,
     * a collection is returned.
     * @return the most common first letter(s) in the words
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
     * Return the letters that are most likely to appear at
     * the start of a word from the literary work. Frequency
     * of the words involved is taken into account. There
     * will often only be one letter, but in case of a tie,
     * a collection is returned.
     * @return the most common first letter(s) in the words
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
     * Return the most common word or words of a given
     * length in the literary work. There will often
     * only be one such word, but in case of a tie,
     * a collection is returned.
     * @param length the length of words to consider
     * @return the list of most common words
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

    /**
     *
     * @param fileName
     */
    public TextProcessor(String fileName) {
        /*
        Initialize an instance of TextProcessor with the file that it should read.
        Words containing the digits 0-9 are ignored, and any punctuation in a word is stripped before storage.
        (This does have the unfortunate consequence of storing "don't" as the four-letter word "dont".
        It does not mess up accented letters as in "fÃ¼nf" or "tÃº".)
        After the constructor returns, the new instance will have all the information it needs to do analysis;
        the file will not be needed again.
        Parameters:
            fileName - the name of the file to be opened (Program will fail if file can't be opened.)
         */
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
}
