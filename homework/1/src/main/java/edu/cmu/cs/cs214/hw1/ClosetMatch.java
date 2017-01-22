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
//	 Document doc = new Document(url); 
	 List<String> inputUrls = new ArrayList<String>();
	 Scanner sc=new Scanner(System.in);
     System.out.println("Input a set of URLs separated by a space and end with ##:");
     String url="";
     do{    	 
     url=sc.next();
      inputUrls.add(url);
     }while(!"##".equals(url));
     inputUrls.remove(inputUrls.size()-1);
     System.out.println(inputUrls);
     
     double cosineResult=0;
     Set<Document> documentsSet = new HashSet<Document>();
     documentsSet.add(new Document(inputUrls.get(0)));
     documentsSet.add(new Document(inputUrls.get(1)));
     for(int i=0;i<inputUrls.size();i++){
    	 String url1 = inputUrls.get(i);
    	 Document doc1 = new Document(url1);
    	 for(int j=i+1;j<inputUrls.size();j++){
    		 String url2 = inputUrls.get(j);
    		 Document doc2 = new Document(url2);
    		 double temp = doc1.calCosine(doc2);
    			 if(temp<cosineResult){
    				 cosineResult = temp;
    				 documentsSet.clear();
    				 documentsSet.add(doc1);
        			 documentsSet.add(doc2);
    			 }
    	 }
     }
}
}
