package edu.cmu.cs.cs214.hw1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author raoliang
 *
 */
public class ClosetMatch{
/**
 * input a set of URLs and prints the two URLs for the most similar pair of web pages
 * @param args
 * @throws IOException 
 * @throws MalformedURLException 
 */
public static void main(String[] args) throws MalformedURLException, IOException{
	 Document doc = new Document(); 
	 List<String> inputUrls = new ArrayList<String>();
	 Scanner sc=new Scanner(System.in);
     System.out.println("Input a set of URLs separated by a space:");
     while (sc.hasNextLine()){    	 
      String url=sc.next();
      inputUrls.add(url);
//      System.out.println(inputUrls);
     }
     System.out.println(inputUrls);
     
     double cosineResult=0;
     Set<String> urlsPair = new HashSet<String>();
     for(int i=0;i<inputUrls.size();i++){
    	 Map<String,Double> doc1 = new HashMap<String,Double>();
    	 String url1= inputUrls.get(i);
    	 doc1 = doc.getUrlDoc(url1);
    	 for(int j=i+1;j<inputUrls.size();j++){
    		 String url2 = inputUrls.get(j);
    		 Map<String,Double> doc2 = new HashMap<String,Double>();
    		 doc2=doc.getUrlDoc(url2);
    		 double temp = doc.calCosine(doc1, doc2);
    		 if (i==0){
    			 cosineResult=temp;
    			 urlsPair.add(url1);
    			 urlsPair.add(url2);
    		 }else{
    			 if(temp<cosineResult){
    				 cosineResult = temp;
    				 urlsPair.clear();
    				 urlsPair.add(url1);
        			 urlsPair.add(url2);
    			 }
    		 }
    		 
    		 
    	 }
     }
}
}
