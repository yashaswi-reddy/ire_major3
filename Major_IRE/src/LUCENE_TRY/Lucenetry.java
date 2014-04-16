package LUCENE_TRY;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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


public class Lucenetry {

	static Analyzer analyzer; 
	static Directory directory;
	static IndexWriterConfig config;
	public static void WriteRowsToLucene(ArrayList< String > rows, String path) throws IOException
	{
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		directory = FSDirectory.open(new File(path));
		IndexWriterConfig config =new IndexWriterConfig(Version.LUCENE_42, analyzer);
		IndexWriter iwriter =new IndexWriter(directory, config);
		
		int i;
		int j=0;
		for(i=0;i<rows.size();i++)
		{
			j++;
			String row= rows.get(i);
			String [] elements= row.split("&&");
			Document doc = new Document();
			/* String row= tweet_timestamp+"&&"+hours+"&&"+day+"&&"+month+"&&"+date+"&&"+time+"&&"+year+"&&"+tweet_id
					+"&&"+text+"&&"+latitude+"&&"+longitude+"&&"+country+"&&"+countrycode+"&&"+placefullname+
					"&&"+retweetcount+"&&"+user_id+"&&"+user_name+"&&"+user_location+"&&"+user_language+
					"&&"+user_screename+"&&"+user_description; */
			TextField tweet = new TextField("text",elements[8].trim(), Field.Store.YES);
			StringField tweet_id=new StringField("tweetid",elements[7].trim(),Field.Store.YES);
			StringField timestamp=new StringField("timestamp",elements[0].trim(),Field.Store.YES);
			StringField hours= new StringField("hours",elements[1].trim(),Field.Store.YES);
			TextField day= new TextField("week",elements[2].trim(),Field.Store.YES);
			TextField month= new TextField("month",elements[3].trim(),Field.Store.YES);
			StringField date= new StringField("date",elements[4].trim(),Field.Store.YES);
			StringField time= new StringField("time",elements[5].trim(),Field.Store.YES);
			StringField year= new StringField("year",elements[6].trim(),Field.Store.YES);
			StringField latitude=new StringField("latitude",elements[9].trim(),Field.Store.YES);
			StringField longitude=new StringField("longitude",elements[10].trim(),Field.Store.YES);
			StringField country=new StringField("country",elements[11].trim(),Field.Store.YES);
			StringField countrycode=new StringField("countrycode",elements[12].trim(),Field.Store.YES);
			TextField place=new TextField("place",elements[13].trim(),Field.Store.YES);
			StringField retweetcount=new StringField("retweetcount",elements[14].trim(),Field.Store.YES);
			StringField userid=new StringField("userid",elements[15].trim(),Field.Store.YES);
			StringField username=new StringField("username",elements[16].trim(),Field.Store.YES);
			StringField userlocation=new StringField("userlocation",elements[17].trim(),Field.Store.YES);
			StringField userlanguage=new StringField("userlanguage",elements[18].trim(),Field.Store.YES);
			StringField userscreenname=new StringField("userscreenname",elements[19].trim(),Field.Store.YES);
			StringField userdescription=new StringField("userdescription",elements[20].trim(),Field.Store.YES);
			StringField timeseconds=new StringField("seconds",elements[21].trim(),Field.Store.YES);
			doc.add(tweet);
			doc.add(tweet_id);
			doc.add(timestamp);
			doc.add(hours);
			doc.add(day);
			doc.add(month);
			doc.add(date);
			doc.add(time);
			doc.add(year);
			doc.add(latitude);
			doc.add(longitude);
			doc.add(country);
			doc.add(countrycode);
			doc.add(place);
			doc.add(retweetcount);
			doc.add(userid);
			doc.add(username);
			doc.add(userlocation);
			doc.add(userlanguage);
			doc.add(userscreenname);
			doc.add(userdescription);
			doc.add(timeseconds);
			//System.out.println(tweet.stringValue());
			iwriter.addDocument(doc);
			System.out.println((j+1)); //+" : "+elements[21]);
		}
		//Date date = new Date();
		//System.out.println(date);
		iwriter.commit();
		iwriter.close();
	}
}
