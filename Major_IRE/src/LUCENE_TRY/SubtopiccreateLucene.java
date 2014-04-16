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

public class SubtopiccreateLucene 
{
	static int count=0;
	static int j=0;

	static Analyzer analyzer; 
	static Directory directory;
	static IndexWriterConfig config;
	static IndexWriter iwriter;
	static String path;
	public SubtopiccreateLucene(){
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		try {
			path="/media/82CC3BADCC3B9A7D/major_project/IRData/subtopic-index";
			directory = FSDirectory.open(new File(path));
			config =new IndexWriterConfig(Version.LUCENE_42, analyzer);
			iwriter =new IndexWriter(directory, config);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException{
		SubtopiccreateLucene c= new SubtopiccreateLucene();
		System.out.println(path+"  check");
		// while indexing remove paranethesis and other irrelevant characters.
		int iter=6;
		for(iter=0;iter<=66;iter++)
		{
			System.out.println(iter);
		    BufferedReader br1 = new BufferedReader(new FileReader(new File("/media/82CC3BADCC3B9A7D/major_project/IRData/features/hash_tags/subtopics"+iter+".txt")));
		    BufferedReader br2 = new BufferedReader(new FileReader(new File("/media/82CC3BADCC3B9A7D/major_project/IRData/features/keyphrase_entities/subtopics"+iter+".txt")));
		    BufferedReader br3 = new BufferedReader(new FileReader(new File("/media/82CC3BADCC3B9A7D/major_project/IRData/features/named_entities/subtopics"+iter+".txt")));
		    BufferedReader br4 = new BufferedReader(new FileReader(new File("/media/82CC3BADCC3B9A7D/major_project/IRData/features/tagme/subtopics"+iter+".txt")));
		    BufferedReader br5 = new BufferedReader(new FileReader(new File("/media/82CC3BADCC3B9A7D/major_project/IRData/features/url/subtopics"+iter+".txt")));
		    String line="";
			String hash="";
			String tagme="";
			String subtopic="";
			String url="";
			String key_phrase="";
			String named_entity="";
			line = br1.readLine();
			try
			{
            subtopic=Integer.toString(iter);
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				subtopic="";	
			}
            while((line = br1.readLine())!=null)
            {
            	hash=hash+"|||"+line;
            }
            line = br2.readLine();
            while((line = br2.readLine())!=null)
            {
           	key_phrase=key_phrase+"|||"+line;
            }
            line = br3.readLine();
            while((line = br3.readLine())!=null)
            {
           	named_entity=named_entity+"|||"+line;
            }
            line = br4.readLine();
            while((line = br4.readLine())!=null)
            {
           	tagme=tagme+"|||"+line;
            }
            line = br5.readLine();
            while((line = br5.readLine())!=null)
            {
           	url=url+"|||"+line;
            }
            try {
				    System.out.println("om");
					c.WriteRowsToLucene(subtopic,hash,key_phrase,named_entity,tagme,url,path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			br1.close();
			br2.close();
			br3.close();
			br4.close();
			br5.close();
		}
		iwriter.commit();
		iwriter.close();
	}
	public void WriteRowsToLucene(String subtopic ,String hash ,String key_phrase,String named_entity,String tagme ,String url, String path) throws IOException
	{

		int i;
		Document doc = new Document();
		try{
		TextField hash_s = new TextField("hash",hash, Field.Store.YES);
		TextField keyphrase_s = new TextField("named_entity",named_entity, Field.Store.YES);
		TextField namedentity_s = new TextField("key_phrase",key_phrase, Field.Store.YES);
		TextField subtopic_s=new TextField("subtopic",subtopic,Field.Store.YES);
		System.out.println(subtopic);
		TextField tagme_s=new TextField("tagme",tagme,Field.Store.YES);
		TextField url_s=new TextField("url",url,Field.Store.YES);
		doc.add(subtopic_s);
		doc.add(keyphrase_s);
		doc.add(namedentity_s);
		doc.add(hash_s);
		doc.add(tagme_s);
		doc.add(url_s);
		iwriter.addDocument(doc);
		iwriter.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.err.println("err");
		}
	}
}
