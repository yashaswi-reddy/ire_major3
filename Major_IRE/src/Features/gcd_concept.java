package Features;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
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

public class gcd_concept 
{
	static int count=0;
	static int j=0;

	static Analyzer analyzer; 
	static Directory directory;
	static IndexWriterConfig config;
	static IndexWriter iwriter;
	static String path;
	public gcd_concept(){
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		try {
			path="GoogleWikiConcepts";
			directory = FSDirectory.open(new File(path));
			config =new IndexWriterConfig(Version.LUCENE_42, analyzer);
			iwriter =new IndexWriter(directory, config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException{
		//path="Indexed"; 

		gcd_concept c= new gcd_concept();
		System.out.println(path+"  check");
		BZip2CompressorInputStream bzip = new BZip2CompressorInputStream(new FileInputStream("/media/82CC3BADCC3B9A7D/major_project/IRData/GoogleWikiConcepts/dictionary.bz2"));
		BufferedReader br = new BufferedReader(new InputStreamReader(bzip));
		String line="";
		while((line = br.readLine())!=null) {
			count++;
			if(count%10000==0)
				System.out.println(count + "   " + line.charAt(0));
			try {
				if(line.split("\t")[0].trim().equals(""))
					continue;
				c.WriteRowsToLucene(line,path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		iwriter.commit();
		iwriter.close();
	}


	public void WriteRowsToLucene(String row, String path) throws IOException
	{

		int i;
		//String [] elements= row.split(":");
		String [] elements= row.split("\t");
		Document doc = new Document();
		//String e = elements[0];
		String e = elements[0].replaceAll("[^a-zA-Z0-9.,-_]", " ");
		e=e.replaceAll("[ ]+", " ");
		if(e.trim().equals(""))
			return;
		//System.err.println(e + "---" + elements[1].split(" ")[0]+ "---" + elements[1].split(" ")[1]);
		TextField tweet = new TextField("text",elements[0].trim(), Field.Store.YES);
		StringField cluster_id=new StringField("probability",elements[1].trim().split(" ")[0].trim(),Field.Store.YES);
		StringField entity=new StringField("concept",elements[1].trim().split(" ")[1].trim(),Field.Store.YES);
		String temp = "";
		for(int j=2;j<elements[1].trim().split(" ").length;j++)
			temp+=elements[1].trim().split(" ")[j].trim()+" ";
		StringField other = new StringField("other",temp.trim(),Field.Store.YES);
		doc.add(tweet);
		doc.add(cluster_id);
		doc.add(entity);
		doc.add(other);
		iwriter.addDocument(doc);

	}
}
