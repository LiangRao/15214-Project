package edu.cmu.cs.cs214.hw1.closestmatches;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.cs.cs214.hw1.Document;

/**
 * Takes a list of URLs on the command line and prints the two URL whose web pages have the highest cosine similarity.
 * Prints a stack trace if any of the URLs are invalid, or if an exception occurs while reading data from
 * the URLs.
 */
public class FindClosestMatches {
	 /**
	 * Find the closest matching web page for each of the command line arguments
     *@param args for a input
	 * @throws IOException 
	 * @throws MalformedURLException 
     */
    public static void main(String[] args) {
    	try{
        int numDocs = args.length;
        double cosineVal = 0.0;
        int tagI = 0;
        int tagJ = 1;
        List<List<Double>> totalCosine = new ArrayList<List<Double>>();//for storing the all result from the calCosine method
        List<Integer> resultList = new ArrayList<Integer>();//for storing the closest matching web page for each url
        //Find the closest web page for each url
        for (int i = 0; i < numDocs; i++) {
        	Document doc = new Document(args[i]);
        	tagI=i;
    		List<Double> listTemp = new ArrayList<Double>();
        	for (int j = i+1; j < numDocs; j++) {
        		Document doc2 = new Document(args[j]);
        		double temp=doc.calCosine(doc2);
        		listTemp.add(temp);
        		if((i==0)&&(j==1)){
        			cosineVal = temp;       			
        		}else if(j==i+1){
        			int m=i;
        			for(int n=0;n<i;n++){
                    	double temp2 = totalCosine.get(n).get(m-1);
                    	m--;
                    	cosineVal=temp;
                    	if(temp2>temp){
                    		cosineVal =temp2;
                    		tagJ=n;         	         	
                    	}    		
        			}
        		}else if(temp>cosineVal){	
        				tagJ = j;
        				cosineVal=temp;
        			}
        		}
        	if(i==numDocs-1){
        		int m=i;
        		double cosineTemp = totalCosine.get(0).get(m-1);
        		tagJ=0;
        		for(int n=1;n<numDocs-1;n++){
        			double temp = totalCosine.get(n).get(m-2);
        			m--;
        			if(temp>cosineTemp){
        				cosineTemp = temp;
        				tagJ=n;
        			}		
        		}     		
        	}
					resultList.add(tagJ);
					totalCosine.add(listTemp);
		}
        //print out the result
        for(int i=0;i<numDocs;i++){
        	Document docFinal = new Document(args[i]);
        	Document docFinal2 = new Document(args[resultList.get(i)]);
        System.out.println("For the "+docFinal+", the closet match URL is: "+docFinal2);
        }
        
    	
    	}catch(Exception e){
    	e.printStackTrace();
    }
    }
}
