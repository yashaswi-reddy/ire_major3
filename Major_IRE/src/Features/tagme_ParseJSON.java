package Features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class tagme_ParseJSON {

	static Analyzer analyzer; 
	static Directory directory;
	static IndexWriterConfig config;
	static IndexWriter iwriter;
	static String path;
	BufferedWriter bw=null;
	tagme_APIRequest pi;
	public tagme_ParseJSON(){
		pi = new tagme_APIRequest();
	
	}
	
	public static void main(String[] args) throws IOException {
		tagme_ParseJSON p=new tagme_ParseJSON();
		p.write();
  
	}
	public void retrieve() throws IOException{
		String directory = "/home/sandeep/Desktop/Replabindex/Testingoct12013/25entities/vm2";
		File[] f = new File(directory).listFiles();
		for(File i : f){
			BufferedReader br = new BufferedReader(new FileReader(i));
			  bw = new BufferedWriter(new FileWriter(new File("/home/sandeep/Desktop/Replabindex/Testingoct12013/25entities/conceptsfiles/"+i.getName())));
			String line = br.readLine();
			while((line=br.readLine())!=null){
				String entity = line.split("\t")[1].replaceAll("\"","");
				String tweetid = line.split("\t")[0].replaceAll("\"","");
				String tweet = line.split("\t")[3];
				String tagConcepts = parse(tweet);
				String row="";
				if(tagConcepts.length()>1)
				{
				row=tweetid+"~!"+entity+"~!"+tagConcepts;
				}
				else
				{
					row=tweetid+"~!"+entity+"~!"+"";
				}
				this.writeTofile(row);
				//this.WriteRowsToLucene(tweetid+"~!"+entity+"~!"+tagConcepts);
			}
			
			br.close();
			bw.flush();
			bw.close();
			//iwriter.commit();
		}
		
		//iwriter.close();

	}
	// index in lucene the concepts for all tweets 
	// dont use wordnet
	// take url keywords of url in tweet
	// and consider only keyphrases of tweet of bigrams and trigrams and test accuracy 
	// do with graph based approach for training and then for testing as usual and report accuracies.
	// use goose and other tools necessary for extracting html content refer replab filtering for updation.
	public  String parse(String text) throws IOException{

		text=pi.doPost(text);
		JsonParser parser = new JsonParser();
		String concepts = "";
		try {

			JsonArray msg=	parser.parse(text).getAsJsonObject().get("annotations").getAsJsonArray();


			Iterator<JsonElement> iterator = msg.iterator();
			concepts = "";
			while (iterator.hasNext()) {

				concepts += iterator.next().getAsJsonObject().get("title") + "\t";
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 

		return concepts.trim();
	}

	
	public void write() throws IOException{
		File f=new File("/home/sandeep/Desktop/Replabindex/Testingoct12013/25entities/Urltokeywordstesting/UrltoKeywordsmappingtestingvm5.txt");
		BufferedReader br = new BufferedReader(new FileReader(new File(f.getAbsolutePath())));
		  bw = new BufferedWriter(new FileWriter(new File("/home/sandeep/Desktop/Replabindex/Testingoct12013/25entities/urlconceptsfiles/"+f.getName())));
		String line = "";
		while((line = br.readLine()) != null){
			//WriteRowsToLucene(line);
			String[] s = line.split("~!");
			String tweetId= s[0];
			String entityId = s[1];
			String urlKeywords="",urlKeyphrases = "";
			
			try {
				urlKeywords = s[2];
			}
			catch(Exception e){
				
			}
			try{
				urlKeyphrases =s[3];
			}
			catch(Exception e){
				
			}
			HashSet<String> hs = new HashSet<String>();
			
			if(urlKeywords!="" && urlKeywords.length()>=4){
				String ss = parse(urlKeywords);
				String[] brea = ss.split("\t");
				for(String i: brea){
					hs.add(i.trim());
				}
			}
			if(urlKeyphrases!="" && urlKeyphrases.length()>=4){
				String ss = parse(urlKeyphrases);
				String[] brea = ss.split("\t");
				for(String i: brea){
					hs.add(i.trim());
				}
			}
			if(hs.size()==0)
			{
				continue;
			}
			Iterator<String> it1=hs.iterator();
			String send="";
			while(it1.hasNext())
			{
				send+=it1.next()+"\t";
			}
			String row=tweetId+"~!"+entityId+"~!"+send.trim();
			writeTofile(row);
			//WriteRowsToLucene(tweetId+"~!"+entityId+"~!"+send.trim());
		}
		
		br.close();
		bw.close();
		//iwriter.commit();
		//iwriter.close();
	}
	;
	
	public void writeTofile(String row)
	{
		System.out.println(row);
		try
		{
		bw.write(row);
		bw.newLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}

