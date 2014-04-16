package Features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import edu.stanford.nlp.util.HashableCoreMap;


public class gcd_searchindex{

	IndexReader reader;
	IndexSearcher searcher;
	Analyzer analyzer;
	QueryParser parser;
	<K,V extends Comparable<? super V>>
	SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
		SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
				new Comparator<Map.Entry<K,V>>() {
					@Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
						if(e2.getValue().compareTo(e1.getValue())==0 || e2.getValue().compareTo(e1.getValue())>0)
							return 1;
						else
							return -1;
					}
				}
				);
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}
	public gcd_searchindex(){
		reader = null;
		try {
			reader = DirectoryReader.open(FSDirectory.open(new File("/media/82CC3BADCC3B9A7D/major_project/IRData/GoogleWikiConcepts")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searcher = new IndexSearcher(reader);
		analyzer = new StandardAnalyzer(Version.LUCENE_40);
		parser = new QueryParser(Version.LUCENE_40, "text", analyzer);
	}
	
	
	public static void main(String args[]) throws Exception
	{
		//"Research"	"Automobile safety"	"Business"	"Central Group"	"Road"	"Volvo"	"Partnership"	"United Kingdom"	"Centre ice hockey"	"Medical research"	"Group A"	"Sweden"	"The Wire"
		gcd_searchindex sc=new gcd_searchindex();
		//sc.searching("volvo automobile", 100,"");
		sc.searching2("4.3 m #earthquake.");
	}
	
	public void searching2(String text) throws Exception
	{
		Query query = parser.parse(text.toLowerCase());
		TopDocs results = searcher.search(query, 100);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		if(numTotalHits > 0)
			hits = searcher.search(query, numTotalHits).scoreDocs;
		System.out.println(numTotalHits + " total matching documents");
		int count=0;
		for(int i=0;i<numTotalHits;i++)
		{
			Document doc = searcher.doc(hits[i].doc);
			String concept=doc.get("concept").toLowerCase();
			System.out.println(concept);
			String text1=doc.get("text").toLowerCase();
			System.out.println(text1);
			count++;
			if(count==100)
				break;
		}
	}
	
	public HashMap<String,Integer> searching(String suserquery,int requiredresults,String entity) throws IOException, ParseException
	{ 
		Query query = parser.parse(suserquery.toLowerCase());
		System.out.println(query);
		TopDocs results = searcher.search(query, 100);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		if(numTotalHits > 0)
			hits = searcher.search(query, numTotalHits).scoreDocs;
		System.out.println(numTotalHits + " total matching documents");
		int count=0;
		HashMap<String,Integer> concepts =new HashMap<String,Integer>();
		for(int i=0;i<numTotalHits;i++)
		{
			Document doc = searcher.doc(hits[i].doc);
			String concept=doc.get("concept").toLowerCase();
			String text=doc.get("text").toLowerCase();
			System.out.println(text);
			Double prob=Double.parseDouble(doc.get("probability"));
			Integer obj=concepts.get(concept);
			//if(text.contains("volvo"))
			if(obj!=null)
			{
				System.out.println(obj);
				obj=obj+1;
				concepts.put(concept, obj);
			}
			else
			{
				concepts.put(concept,1);
			}
			System.out.println("Document "+(i+1)+" : "+doc.get("text")+" -> "+doc.get("concept")+" -> "+doc.get("probability"));
			count++;
			if(count==requiredresults)
				break;
		}
		SortedSet<Map.Entry<String,Integer>> s3=entriesSortedByValues(concepts);
		Iterator<Map.Entry<String,Integer>> it1=s3.iterator();
		int count1=0;
		while(it1.hasNext())
		{
			Entry<String,Integer> entry=it1.next();
			System.out.println(entry.getKey()+" "+entry.getValue());
			count1++;
			if(count1==10)
				break;
		}
		System.out.println(concepts);
	return concepts;
	}
	public String searching1(String suserquery,int requiredresults,String entity) throws IOException, ParseException
	{ 
		
		HashSet<String> refinedconcepts=new HashSet<String>();
		HashMap<String,ArrayList<String>> mapconcepttowords=new HashMap<String,ArrayList<String>>();
		String concepts[]=suserquery.replaceAll("\"(),","").split("\t");
		
		for(String i:concepts)
		{
			
			String a[]=i.split(" ");
			ArrayList<String> temp=new ArrayList<String>();
			for(String j:a)
			{
				temp.add(j.toLowerCase());
			}
			mapconcepttowords.put(i,temp);
			}
		suserquery=suserquery.replaceAll("[\"():/,\t^?:*&%$#@!=+';-]"," ");
		suserquery=suserquery.toLowerCase();
		Query query = parser.parse(suserquery);
		System.out.println(query);
		TopDocs results = searcher.search(query, 10);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		if(numTotalHits > 0)
			hits = searcher.search(query, numTotalHits).scoreDocs;
		//System.out.println(numTotalHits + " total matching documents");
		int count=0;
		HashSet<String> strings =new HashSet<String>();
		for(int i=0;i<numTotalHits;i++)
		{
			Document doc = searcher.doc(hits[i].doc);
			String concept=doc.get("concept").toLowerCase();
			String text=doc.get("text").toLowerCase();
			Double prob=Double.parseDouble(doc.get("probability"));
			if(text.contains(entity) && !concept.contains(entity))
			{
				strings.add(concept);
				count++;
			}
			//System.out.println("Document "+(i+1)+" : "+doc.get("text")+" -> "+doc.get("concept")+" -> "+doc.get("probability"));
			
			if(count==requiredresults)
				break;
		}
		Set<String> s1=mapconcepttowords.keySet();
		Iterator<String> it1=s1.iterator();
		while(it1.hasNext())
		{
			String q=it1.next();
			Iterator<String> it=strings.iterator();
			ArrayList<String> words=mapconcepttowords.get(q);
			for(int j=0;j<words.size();j++)
			{
				while(it.hasNext())
				{
					String r=it.next();
					if(r.contains(words.get(j)))
					{
						refinedconcepts.add(q);
						break;
					}
				}
			}
		}
		String send="";
		for(String m:refinedconcepts)
		{
			send+=m+"\t";
		}
		System.out.println(strings);
		return send.trim();
		
	}
}

