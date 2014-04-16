package LUCENE_TRY;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class SearchSandeepClusters{

	static String path="/home/sandeep/Desktop/conceptdatasetindex/Indexed";
	public SearchSandeepClusters(String spath)
	{
		 path=spath;
	}
	@SuppressWarnings("deprecation")
	public static void main(String [] args) throws IOException,Exception 
	{
		SearchSandeepClusters sc=new SearchSandeepClusters("/home/sandeep/Desktop/conceptdatasetindex/Indexed");
		Set<String> h1=new HashSet<String>();
		h1.add("car ");
		System.out.println(sc.searching(h1,"RL2013D01E019",10));
	}
	
	public HashSet<String> searching(Set<String> keywords,String entity,int c) throws IOException, Exception
	{ 
		String q="";
		// while searching remove parenthesis and other characters from searching keywords.
		HashSet<String> arr=new HashSet<String>();
		for(Iterator<String> i=keywords.iterator();i.hasNext();)
			q+=i.next().replaceAll("/","")+" ";
		System.out.println(q);
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		QueryParser parser = new QueryParser(Version.LUCENE_40, "text", analyzer);
		Query query = parser.parse(q.trim()+" AND entity:"+entity);
		TopDocs results = searcher.search(query, 10000);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		if(numTotalHits > 0)
			hits = searcher.search(query, numTotalHits).scoreDocs;
		//System.out.println(numTotalHits + " total matching documents");
		int count=0;
		for(int i=0;i<numTotalHits;i++)
		{
			Document doc = searcher.doc(hits[i].doc);
			//System.out.println("Document "+(i+1)+" : "+doc.get("text")+" -> "+doc.get("clusterid")+" -> "+doc.get("entity"));
			if(arr.contains(doc.get("clusterid")))
				continue;
			else{
				arr.add(Integer.toString(Integer.parseInt(doc.get("clusterid"))));
				count++;
				if(count==c)
					break;
			}
		}
		return arr;
	}
	
	public void searching() throws IOException,Exception
	{ 
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		QueryParser parser = new QueryParser(Version.LUCENE_40, "text", analyzer);
		Query query = parser.parse("tiger");
		
		TopDocs results = searcher.search(query, 10000);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		if(numTotalHits > 0)
			hits = searcher.search(query, numTotalHits).scoreDocs;
		//System.out.println(numTotalHits + " total matching documents");
		int count=0;
		for(int i=0;i<numTotalHits;i++)
		{
			Document doc = searcher.doc(hits[i].doc);
			//System.out.println("Document "+(i+1)+" : "+doc.get("text")+" -> "+doc.get("clusterid")+" -> "+doc.get("entity"));
			count++;
		}
	}
}
