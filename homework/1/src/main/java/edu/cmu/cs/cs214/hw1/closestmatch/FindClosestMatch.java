package edu.cmu.cs.cs214.hw1.closestmatch;

import edu.cmu.cs.cs214.hw1.Document;

/**
 * Takes a list of URLs on the command line and prints the two URL whose web pages have the highest cosine similarity.
 * Prints a stack trace if any of the URLs are invalid, or if an exception occurs while reading data from
 * the URLs.
 */
public class FindClosestMatch {
	/**
	 * find the two most similar web pages from a list of web pages 
	 * @param args is a input including a list of URLs
	 */
    public static void main(String[] args) {
    	try{
        int numDocs = args.length;
        double cosineVal = 0.0;
        int tagI=0;
        int tagJ=1;
        
        //Compare the all the documents one by one
        //perform numDocs(numDocs-1)/2 calls to the calCosine method 
        for (int i = 0; i < numDocs; i++) {
        	Document doc = new Document(args[i]);
        	for (int j = i+1; j < numDocs; j++) {
        		Document doc2 = new Document(args[j]);
        		double temp = doc.calCosine(doc2);
        		if ((i==0)&&(j==1)) {
					cosineVal = temp;
				}else if (temp>cosineVal) {
					cosineVal = temp;
					tagI =i;
					tagJ=j;
				}		    		
			}
        }
        Document docFinal1 = new Document(args[tagI]);
    	Document docFinal2 = new Document(args[tagJ]);
        System.out.println("The two having the highest cosine similarity are: "+docFinal1.toString()+" and "+docFinal2.toString());   	
    	}catch(Exception e){
    	e.printStackTrace();
    }
    }
}
