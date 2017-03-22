package edu.cmu.cs.cs214.hw4.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * A class represent a dictionary
 * 
 * @author raoliang
 *
 */
public class Dictionary {
	private static Set<String> wordsSet;
	private String path;

	/**
	 * A constructor
	 * 
	 * @param path
	 *            the path of a dictionary
	 */
	public Dictionary(String path) {
		wordsSet = new HashSet<>();
		this.path = path;
		initial();
	}

	/**
	 * Initial a dictionary
	 */
	public void initial() {
		try {
			String filePath = "src/main/resources/" + path;
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // determine that the file
													// exist or not
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					wordsSet.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("Can not find the specific file");
			}

		} catch (Exception e) {
			System.out.println("Error occurred in reading file");
			e.printStackTrace();
		}

	}

	/**
	 * To check whether the word is in the dictionary
	 * 
	 * @param word
	 *            the word needs to check
	 * @return return true if the word is in dictionary, or return false
	 */
	public boolean isIn(Word word) {
		System.out.println(word.toString().toLowerCase());
		return wordsSet.contains(word.toString().toLowerCase());
	}

}
