package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

public class SingleTestold 
{
	public String id;
	static String path="/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/subtopic-index";
	public int [] top = new int[67];
	
	public SingleTestold(String s)
	{
	id=	s;
	}
	public static void main(String args[]) throws IOException, ParseException
	{
	SingleTestold k = new SingleTestold("233900903097303041");
	k.Test("k");
	}
	public String Test(String r) throws IOException, ParseException
	{
		int i;
		for(i=0;i<67;i++)
		{
			top[i]=i;
		}
		
		BufferedReader bReader1 = new BufferedReader(new FileReader("/media/" +
				"82CC3BADCC3B9A7D/major_project/IRData/entity_3/test-features/tweet_tag_subcategories/"+id+".txt"));
		BufferedReader bReader2 = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/" +
				"IRData/entity_3/test-features/tweet_url_subcategories/"+id+".txt"));

		BufferedReader bReader3 = new BufferedReader(new FileReader("/media/" +
				"82CC3BADCC3B9A7D/major_project/IRData/entity_3/test-features/hash_tags/"+id+".txt"));
		BufferedReader bReader4 = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/" +
				"IRData/entity_3/test-features/keyphrases/"+id+".txt"));
		BufferedReader bReader5 = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/" +
				"IRData/entity_3/test-features/named_entities/"+id+".txt"));
		
		String line;	    
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		
		String feat="";
		while ((line = bReader1.readLine()) != null) 
	        {
	        	feat=feat+" "+line;
	        //System.out.println(line);	
	        }
		while ((line = bReader2.readLine()) != null) 
        {
        	feat=feat+" "+line;
        //System.out.println(line);	
        }
		while ((line = bReader3.readLine()) != null) 
        {
        	feat=feat+" "+line;
        //System.out.println(line);	
        }
		while ((line = bReader4.readLine()) != null) 
        {
        	feat=feat+" "+line;
        //System.out.println(line);	
        }
		while ((line = bReader5.readLine()) != null) 
        {
        	feat=feat+" "+line;
        //System.out.println(line);	
        }
		
		
		
		
		
		
		QueryParser parser = new QueryParser(Version.LUCENE_40, "features", analyzer);
		Query query = parser.parse(feat);
		TopDocs results = searcher.search(query, 10000);
		ScoreDoc[] hits = results.scoreDocs;
		int numTotalHits = results.totalHits;
		if(numTotalHits > 0)
			hits = searcher.search(query, numTotalHits).scoreDocs;
	//	System.out.println(numTotalHits + " total matching documents");
		if(numTotalHits>0)
		{
		int n;
		if(numTotalHits>10) n=10;
		else 		n=numTotalHits;
		for( i=0;i<n;i++)
		{
			Document doc = searcher.doc(hits[i].doc);
		  // System.out.println("Document "+doc.get("subtopic"));
			top[i]=Integer.parseInt(doc.get("subtopic"));
		}
		for(i=n;i<67;i++)
			top[i]=-1;
		}
		//List after 1st level
		for(i=0;i<67;i++)
		{
			System.out.println(top[i]);
		}
		
		return r;
     }
		
		
	

}
