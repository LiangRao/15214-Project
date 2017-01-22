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
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author raoliang
 *
 */
public class Document {
	String url;
    Map<String, Double> docMap1 =new HashMap<String,Double>();
	/**
	 * Constructor
	 * 
	 * @param url1
	 * @param url2
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	public Document(String url1) throws MalformedURLException, IOException {
		this.url = url;
		this.docMap1=getUrlDoc(url);
	}
	/**
	 * Get the content of Url
	 * @throws MalformedURLException
	 * @throws java.io.IOException
	 */
	public Map<String, Double> getUrlDoc(String url) throws MalformedURLException, IOException {
		StringBuilder htmlTxt = new StringBuilder();
		// String urlString = "https://en.wikipedia.org/wiki/Shiba_Inu";
		Scanner sc = new Scanner(new URL(url).openStream());
		while (sc.hasNext()) {
			String word = sc.next();
			htmlTxt.append(word);
		}
		HashMap<String, Double> map = new HashMap<String, Double>();
		String reg = "[a-zA-Z]+";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(htmlTxt);
		while (m.find()) {
			String w = m.group();
			if (null == map.get(w)) {
				map.put(w, (double) 1);
			} else {
				Double x = map.get(w);
				map.put(w, x + 1);
			}
		}
		return map;
	}

	/**
	 * calculate the cosine similarity of 
	 * @param doc2
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public double calCosine( Document doc2) throws MalformedURLException, IOException {
		String url2 =doc2.url;
        Map<String, Double> docMap2 = doc2.getUrlDoc(url2);
        
		double result;
		List<String> keyList1 = new ArrayList<String>();// the keyList of doc1
		List<Double> valueList1 = new ArrayList<Double>();// the valueList of
															// doc1
		List<String> keyList2 = new ArrayList<String>();// the keyList of doc2
		List<Double> valueList2 = new ArrayList<Double>();// the valueList of
															// doc2
		double denominator1 = 0;
		double denominator2 = 0;
		double numerator = 0;
		Iterator it = docMap1.entrySet().iterator();
		while (it.hasNext()) {
			Entry entry = (Entry) it.next();
			String key = entry.getKey().toString();// 返回与此项对应的键
			Double value = (Double) (entry.getValue()); // 返回与此项对应的值
			keyList1.add(key);
			valueList1.add(value);
			denominator1 += Math.pow(value, 2);

		}
		Iterator it2 = docMap2.entrySet().iterator();
		while (it2.hasNext()) {
			Entry entry = (Entry) it2.next();
			String key = entry.getKey().toString();// 返回与此项对应的键
			Double value = (Double) (entry.getValue()); // 返回与此项对应的值
			keyList2.add(key);
			valueList2.add(value);
			denominator2 += Math.pow(value, 2);
		}
		int size1 = keyList1.size();
		int size2 = keyList2.size();

		for (int i = 0; i < size1; i++) {
			String key1 = keyList1.get(i);
			for (int j = 0; j < size2; j++) {
				String key2 = keyList2.get(j);
				if (key1.equalsIgnoreCase(key2)) {
					double num1 = docMap1.get(key1) * docMap2.get(key2);
					numerator += num1;
				}
			}
		}
		result = numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2));
		return result;
	}

public String toString(){
	return url;
}
//	public static void main(String[] args) {
//		Map<String, Double> doc1 = new HashMap<String, Double>();
//		doc1.put("year", 1.0);
//		doc1.put("dog", 2.0);
//
//		Map<String, Double> doc2 = new HashMap<String, Double>();
//		doc2.put("year", 1.0);
//		doc2.put("dog", 2.0);
//		Document doc = new Document();
//		System.out.println(doc.calCosine(doc2));
//
//	}
}
