package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class including the content of a web page and a instance method to calculate the similarity of two Document 
 * @author Liang Rao
 *
 */
public class Document {
	private String url;
	private Map<String,Integer> docMap = new HashMap<String, Integer>();
    private Double sqrSum;
	
	/**
	 * Constructor
	 * @param url  the URL of a web page which needs to 
	 * @throws MalformedURLException  a URL Exception
	 * @throws IOException  a IO Exception
	 */
	public Document(String url) 
			throws MalformedURLException, IOException {
		this.url = url;
		this.docMap=getUrlDoc(url);
		double sum = 0.0;
		Set entries = docMap.entrySet( );  
		Iterator iterator = entries.iterator( );  
		while(iterator.hasNext( )) {  
		   Map.Entry entry = (Entry) iterator.next( );//entry是一个键值项   
		   Integer value = (Integer) entry.getValue(); 
		   sum += Math.pow(value, 2);
		}  
		this.sqrSum= Math.sqrt(sum);		
	}
		
	/**
	 * Get the content of a specific Document
	 * @param url  the URL of Document 
	 * @return a Map which Key is the word in Document content and Value is the frequency of the word
	 * @throws MalformedURLException  q URL Exception
	 * @throws IOException  q IO Exception
	 */
	private Map<String, Integer> getUrlDoc(String url) 
			throws MalformedURLException, IOException {
		StringBuilder htmlTxt = new StringBuilder();
		// String urlString = "https://en.wikipedia.org/wiki/Shiba_Inu";
		Scanner sc = new Scanner(new URL(url).openStream());
		while (sc.hasNext()) {
			String word = sc.next();
			if(docMap.containsKey(word)){
				docMap.put(word,docMap.get(word)+1);
			}else{
				docMap.put(word, 1);
			}
		}	
		return docMap;
	}

	/**
	 * Calculate the cosine similarity of 
	 * @param doc2  the Second Document 
	 * @return the cosine similarity of the two Document
	 * @throws MalformedURLException  a URL Exception
	 * @throws IOException  a IO Exception
	 */
	public double calCosine( Document doc2) 
			throws MalformedURLException, IOException {
		double result;
		double numerator = 0;
	    for(String key:docMap.keySet())	{
	    	if(doc2.docMap.containsKey(key)){
	    		numerator+=docMap.get(key)*(doc2.docMap.get(key));
	    	}
		}
		result = numerator / (sqrSum * doc2.sqrSum);
		return result;
	}
	
	/**
	 * @return return the url of Document
	 */
public String toString(){
	return url;
}
}
