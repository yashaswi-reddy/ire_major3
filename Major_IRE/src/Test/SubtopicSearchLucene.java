package Test;

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


public class SubtopicSearchLucene{

	static String path="/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/subtopic-index";
	
	@SuppressWarnings("deprecation")
	public static void main(String [] args) throws IOException,Exception 
	{
	
		//SubtopicSearchLucene sc=new SubtopicSearchLucene();
	    //sc.searching("23");
	    //sc.searching_tagme("23");
	    //sc.searching_url("23");
	}
	
	public String searching(String s) throws IOException,Exception
	{ 
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		QueryParser parser = new QueryParser(Version.LUCENE_40, "subtopic", analyzer);
		Query query = parser.parse(s);
		TopDocs results = searcher.search(query, 10000);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		if(numTotalHits > 0)
			hits = searcher.search(query, numTotalHits).scoreDocs;
		System.out.println(numTotalHits + " total matching documents");
		int count=0;
		for(int i=0;i<numTotalHits;i++)
		{
			Document doc = searcher.doc(hits[i].doc);
			//System.out.println("Document "+(i+1)+" : "+doc.get("subtopic")+"hash_featues"+doc.get("hash")+"named_features"+doc.get("named_entity")+"key_features"+doc.get("key_phrase")+"tagme_features"+doc.get("tagme")+" -> "+doc.get("url"));
			count++;
			return doc.get("hash");
			
		}
		return null;
	}
	
	public String searching_tagme(String s) throws IOException,Exception
	{ 
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		QueryParser parser = new QueryParser(Version.LUCENE_40, "subtopic", analyzer);
		Query query = parser.parse(s);
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
			//System.out.println("Document "+(i+1)+" : "+"tagme_features"+doc.get("tagme"));
			count++;
			return doc.get("tagme");
			
		}
		return null;
	}
	
	public String searching_url(String s) throws IOException,Exception
	{ 
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		QueryParser parser = new QueryParser(Version.LUCENE_40, "subtopic", analyzer);
		Query query = parser.parse(s);
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
			//System.out.println("Document "+(i+1)+" : "+" -> "+doc.get("url"));
			count++;
			return doc.get("url");
			
		}
		return null;
	}
}
	
	
	