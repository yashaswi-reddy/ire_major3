package LUCENE_TRY;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

public class CreateSandeepIndex 
{
	static int count=0;
	static int j=0;

	static Analyzer analyzer; 
	static Directory directory;
	static IndexWriterConfig config;
	static IndexWriter iwriter;
	static String path;
	public CreateSandeepIndex(){
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		try {
			path="/home/sandeep/Desktop/conceptdatasetindex/Indexed";
			directory = FSDirectory.open(new File(path));
			config =new IndexWriterConfig(Version.LUCENE_42, analyzer);
			iwriter =new IndexWriter(directory, config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException{
		CreateSandeepIndex c= new CreateSandeepIndex();
		System.out.println(path+"  check");
		// while indexing remove paranethesis and other irrelevant characters.
			BufferedReader br = new BufferedReader(new FileReader(new File("/home/sandeep/Desktop/conceptdatasettesting/RL2013D01E019_index/keywords.txt")));
			String line="";
			while((line = br.readLine())!=null) {
				count++;
				try {
					if(line.split(":")[0].trim().equals(""))
						continue;
					line=line.replaceAll("[^a-zA-Z0-9:]+"," ");
					c.WriteRowsToLucene(line,path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			br.close();
		
		iwriter.commit();
		iwriter.close();
	}


	public void WriteRowsToLucene(String row, String path) throws IOException
	{

		int i;
		//String [] elements= row.split(":");
		String [] elements= row.split(":");
		Document doc = new Document();
		//System.out.println(elements[0]+ " "+elements[1]+" "+elements[2]);
		//System.err.println("---" + elements[1].split(" ")[0]+ "---" + elements[1].split(" ")[1]);
		try{
		TextField tweet = new TextField("text",elements[0].trim(), Field.Store.YES);
		StringField cluster_id=new StringField("clusterid",elements[1].trim(),Field.Store.YES);
		TextField entity=new TextField("entity",elements[2].trim(),Field.Store.YES);
		doc.add(tweet);
		doc.add(cluster_id);
		doc.add(entity);
		iwriter.addDocument(doc);
		iwriter.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.err.println(row);
		}
	}
}
