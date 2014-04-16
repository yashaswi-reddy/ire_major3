package Test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

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

public class SingleTest
{
	public static String id;
	static String path="/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/subtopic-index";
	public int [] top = new int[67];
	static HashMap<Integer,String> sub_topic;
	//static FileWriter fw;
	public SingleTest(String s) throws IOException
	{
	id=	s;
	//fw = new FileWriter("/home/aayush/workspace/Urldata/ans.txt", false);
	}
	
	public static void main(String args[]) throws Exception
	{
		SingleTest s= new SingleTest("233900903097303041");
		System.out.println(s.Test());
   	
	}
	public String Test() throws Exception
	{
		sub ss=new sub();
		ss.create_sub();
		sub_topic=sub.sub1;
		BufferedReader bReader1=null;
		BufferedReader bReader2=null;
		BufferedReader bReader3=null;
		BufferedReader bReader4=null;
		BufferedReader bReader5=null;
		int i;
		for(i=0;i<67;i++)
		{
			top[i]=i;
		}
		try
		{
		 bReader1 = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/test-features/tweet_tag_subcategories/"+id+".txt"));
		 bReader2 = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/test-features/tweet_url_subcategories/"+id+".txt"));
		 bReader3 = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/test-features/hash_tags/"+id+".txt"));
		 bReader4 = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/test-features/keyphrases/"+id+".txt"));
		 bReader5 = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/test-features/named_entities/"+id+".txt"));
		
		String line;	    
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(path)));
		IndexSearcher searcher = new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
		//System.out.println("tweet id"+id);
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
		HashMap<Integer,Integer> hm1 =new HashMap<Integer,Integer>();
		SubtopicSearchLucene sc=new SubtopicSearchLucene();
		for(i=0;i<10;i++)
		{
			//System.out.println("started "+top[i]);	
			//tagme_api
			String id_1="";
			try
			{
			 String sub=sc.searching_tagme(""+top[i]);
			// System.out.println(sub);
			 String sub_tag[]=sub.split("\\|\\|\\|");
			    int j=0;
			    for( j=1;j<sub_tag.length;j++)
			    {
			    	String tag[]=sub_tag[j].split("###");
			    	if(j==1)
			    	{
			    		//System.out.println("yes");
			    		//System.out.println(id_1);
			    		id_1=id_1+tag[0].trim();
			    		//System.out.println("chk "+id_1);
			    	}
			    	else
			    	{
			    		//System.out.println("no");
			    		id_1=id_1+','+tag[0].trim();
			    	}
			    	//tag_comp.add(tag[0].trim());
			    }
			  //  System.out.println("\n"+id_1);
			}
			catch(Exception e)
			{
				id_1=null;
			}
			    
			//url concepts 
			    String id_2="";
			    try
			    {
			    	
		    String sub_2=sc.searching_url(""+top[i]);
		    //String topic1=sub_topic.get(top[i]);
		    //System.out.println(sub_2);
		    String sub_tag_2[]=sub_2.split("\\|\\|\\|");
		    
		   // System.out.println(sub_tag_2.length);
		    for(int jj=1;jj<50;jj++)
		    {
		    	String tag[]=sub_tag_2[jj].split("###");
		    	if(jj==1) id_2=id_2+tag[0].trim();
		    	else 		id_2=id_2+","+tag[0].trim();
		    }
		    //System.out.println(id_2);
		    String sub_ids=id_1+","+id_2;
		    //System.out.println(sub_ids);
		    //System.out.println(id);
			    }
			    catch(Exception e)
			    {
			    	id_2=null;
			    }
		    //reading tweet tagme
				String tweet_id_tag="";
				String lin="";
				
				int cnt=0;
			
			    try
			    {
		    BufferedReader bReader_1 = new BufferedReader(new FileReader("/home/aayush/workspace/Urldata/final/test-features/tagme_id/"+id+".txt"));
			    while ((lin = bReader_1.readLine()) != null) 
		        {
		    	if(cnt==0) tweet_id_tag=tweet_id_tag+""+lin;
		    	else		tweet_id_tag=tweet_id_tag+","+lin;
		    	cnt++;
		        //System.out.println(line);	
		        }
		    //System.out.println(tweet_id_tag);
			    }
			    catch(Exception e)
			    {
			    	tweet_id_tag=null;
			    }
		   //reading tweet url
			    String tweet_id_url="";
			    try{
		    BufferedReader bReader_2 = new BufferedReader(new FileReader("/home/aayush/workspace/Urldata/final/test-features/url_id/"+id+".txt"));
			lin="";
			
			 cnt=0;
		    while ((lin = bReader_2.readLine()) != null) 
		        {
		    	tweet_id_url=tweet_id_url+","+lin;
		    	cnt++;	
		        }
			    }
			    catch(Exception e)
			    {
			    	tweet_id_url=null;
			    }
		    String tweet_ids=tweet_id_tag+tweet_id_url;
		    //System.out.println(tweet_ids);
		    compare cmp=new compare();		
			int tag_val=cmp.comp2(tweet_ids,id_1);
			int url_val=cmp.comp2(tweet_ids,id_2);
			int val=0;
			if(url_val!=0)
			val=(tag_val+url_val)/2;
			else
			val=tag_val;
			//System.out.println("similarity is "+val);
			hm1.put(top[i],val);
		}
	
		ArrayList<Map.Entry> a = new ArrayList<Map.Entry>(hm1.entrySet());
		Collections.sort(a,
		         new Comparator() {
		             public int compare(Object o1, Object o2) {
		                 Map.Entry e1 = (Map.Entry) o1;
		                 Map.Entry e2 = (Map.Entry) o2;
		                 return ((Comparable) e2.getValue()).compareTo(e1.getValue());
		             }
		         });
		HashMap<Integer,Integer> out=new HashMap<Integer,Integer>();
		int f=0,max=0;
		for (Map.Entry e : a) {
				if(f==0) max=(Integer)e.getKey();
				//System.out.println(e.getKey()+" "+e.getValue()); 
				f++;
			}
	
		String topic=sub_topic.get(max);
		
		 return "\""+"RL2013D01E003\""+"	"+"\""+id+"\"	"+"\""+topic+"\"";
		
		}
		
		catch(Exception e)
		{
			//System.out.println("file not found");
		}
		return null;
		}
		
	
	
}