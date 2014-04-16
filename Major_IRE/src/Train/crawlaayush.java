package Train;


 import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class crawlaayush {
	 public static HashMap _stopwords = new HashMap();
	 
	static HashMap<String,Integer> word=new HashMap<String,Integer>();
	public StringTokenizer token(String tk)
	{
		StringTokenizer st = new StringTokenizer(tk,"'\";/\\_|=[]{},\n\t!@#$%^&*()1234567890<>-.:?~`+- 	=’ˈ“”—");
		return st;
	}
	
	public static ArrayList<Map.Entry> ans  (String filename) throws IOException
	{
		crawlaayush cr=new crawlaayush();
			_stopwords.put("http", 1);
			_stopwords.put("blog", 1);
			_stopwords.put("a", 1);
			_stopwords.put("about", 1);
			_stopwords.put("above", 1);
			_stopwords.put("above", 1);
			_stopwords.put("across", 1);
			_stopwords.put("after", 1);
			_stopwords.put("afterwards", 1);
			_stopwords.put("again", 1);
			_stopwords.put("against", 1);
			_stopwords.put("all", 1);
			_stopwords.put("almost", 1);
			_stopwords.put("alone", 1);
			_stopwords.put("along", 1);
			_stopwords.put("already", 1);
			_stopwords.put("also", 1);
			_stopwords.put("although", 1);
			_stopwords.put("always", 1);
			_stopwords.put("am", 1);
			_stopwords.put("among", 1);
			_stopwords.put("amongst", 1);
			_stopwords.put("amoungst", 1);
			_stopwords.put("amount", 1);
			_stopwords.put("an", 1);
			_stopwords.put("and", 1);
			_stopwords.put("another", 1);
			_stopwords.put("any", 1);
			_stopwords.put("anyhow", 1);
			_stopwords.put("anyone", 1);
			_stopwords.put("anything", 1);
			_stopwords.put("anyway", 1);
			_stopwords.put("anywhere", 1);
			_stopwords.put("are", 1);
			_stopwords.put("around", 1);
			_stopwords.put("as", 1);
			_stopwords.put("at", 1);
			_stopwords.put("back", 1);
			_stopwords.put("be", 1);
			_stopwords.put("became", 1);
			_stopwords.put("because", 1);
			_stopwords.put("become", 1);
			_stopwords.put("becomes", 1);
			_stopwords.put("becoming", 1);
			_stopwords.put("been", 1);
			_stopwords.put("before", 1);
			_stopwords.put("beforehand", 1);
			_stopwords.put("behind", 1);
			_stopwords.put("being", 1);
			_stopwords.put("below", 1);
			_stopwords.put("beside", 1);
			_stopwords.put("besides", 1);
			_stopwords.put("between", 1);
			_stopwords.put("beyond", 1);
			_stopwords.put("bill", 1);
			_stopwords.put("both", 1);
			_stopwords.put("bottom", 1);
			_stopwords.put("but", 1);
			_stopwords.put("by", 1);
			_stopwords.put("call", 1);
			_stopwords.put("can", 1);
			_stopwords.put("cannot", 1);
			_stopwords.put("cant", 1);
			_stopwords.put("co", 1);
			_stopwords.put("con", 1);
			_stopwords.put("could", 1);
			_stopwords.put("couldnt", 1);
			_stopwords.put("cry", 1);
			_stopwords.put("de", 1);
			_stopwords.put("describe", 1);
			_stopwords.put("detail", 1);
			_stopwords.put("do", 1);
			_stopwords.put("done", 1);
			_stopwords.put("down", 1);
			_stopwords.put("due", 1);
			_stopwords.put("during", 1);
			_stopwords.put("each", 1);
			_stopwords.put("eg", 1);
			_stopwords.put("eight", 1);
			_stopwords.put("either", 1);
			_stopwords.put("eleven", 1);
			_stopwords.put("else", 1);
			_stopwords.put("elsewhere", 1);
			_stopwords.put("empty", 1);
			_stopwords.put("enough", 1);
			_stopwords.put("etc", 1);
			_stopwords.put("even", 1);
			_stopwords.put("ever", 1);
			_stopwords.put("every", 1);
			_stopwords.put("everyone", 1);
			_stopwords.put("everything", 1);
			_stopwords.put("everywhere", 1);
			_stopwords.put("except", 1);
			_stopwords.put("few", 1);
			_stopwords.put("fifteen", 1);
			_stopwords.put("fify", 1);
			_stopwords.put("fill", 1);
			_stopwords.put("find", 1);
			_stopwords.put("fire", 1);
			_stopwords.put("first", 1);
			_stopwords.put("five", 1);
			_stopwords.put("for", 1);
			_stopwords.put("former", 1);
			_stopwords.put("formerly", 1);
			_stopwords.put("forty", 1);
			_stopwords.put("found", 1);
			_stopwords.put("four", 1);
			_stopwords.put("from", 1);
			_stopwords.put("front", 1);
			_stopwords.put("full", 1);
			_stopwords.put("further", 1);
			_stopwords.put("get", 1);
			_stopwords.put("give", 1);
			_stopwords.put("go", 1);
			_stopwords.put("had", 1);
			_stopwords.put("has", 1);
			_stopwords.put("hasnt", 1);
			_stopwords.put("have", 1);
			_stopwords.put("he", 1);
			_stopwords.put("hence", 1);
			_stopwords.put("her", 1);
			_stopwords.put("here", 1);
			_stopwords.put("hereafter", 1);
			_stopwords.put("hereby", 1);
			_stopwords.put("herein", 1);
			_stopwords.put("hereupon", 1);
			_stopwords.put("hers", 1);
			_stopwords.put("herself", 1);
			_stopwords.put("him", 1);
			_stopwords.put("himself", 1);
			_stopwords.put("his", 1);
			_stopwords.put("how", 1);
			_stopwords.put("however", 1);
			_stopwords.put("hundred", 1);
			_stopwords.put("ie", 1);
			_stopwords.put("if", 1);
			_stopwords.put("in", 1);
			_stopwords.put("inc", 1);
			_stopwords.put("indeed", 1);
			_stopwords.put("interest", 1);
			_stopwords.put("into", 1);
			_stopwords.put("is", 1);
			_stopwords.put("it", 1);
			_stopwords.put("its", 1);
			_stopwords.put("itself", 1);
			_stopwords.put("keep", 1);
			_stopwords.put("last", 1);
			_stopwords.put("latter", 1);
			_stopwords.put("latterly", 1);
			_stopwords.put("least", 1);
			_stopwords.put("less", 1);
			_stopwords.put("ltd", 1);
			_stopwords.put("made", 1);
			_stopwords.put("many", 1);
			_stopwords.put("may", 1);
			_stopwords.put("me", 1);
			_stopwords.put("meanwhile", 1);
			_stopwords.put("might", 1);
			_stopwords.put("mill", 1);
			_stopwords.put("href", 1);
			_stopwords.put("title", 1);
			_stopwords.put("mine", 1);
			_stopwords.put("more", 1);
			_stopwords.put("moreover", 1);
			_stopwords.put("most", 1);
			_stopwords.put("mostly", 1);
			_stopwords.put("move", 1);
			_stopwords.put("much", 1);
			_stopwords.put("must", 1);
			_stopwords.put("my", 1);
			_stopwords.put("myself", 1);
			_stopwords.put("name", 1);
			_stopwords.put("namely", 1);
			_stopwords.put("neither", 1);
			_stopwords.put("never", 1);
			_stopwords.put("nevertheless", 1);
			_stopwords.put("next", 1);
			_stopwords.put("nine", 1);
			_stopwords.put("no", 1);
			_stopwords.put("nobody", 1);
			_stopwords.put("none", 1);
			_stopwords.put("noone", 1);
			_stopwords.put("nor", 1);
			_stopwords.put("not", 1);
			_stopwords.put("nothing", 1);
			_stopwords.put("now", 1);
			_stopwords.put("nowhere", 1);
			_stopwords.put("of", 1);
			_stopwords.put("off", 1);
			_stopwords.put("often", 1);
			_stopwords.put("on", 1);
			_stopwords.put("once", 1);
			_stopwords.put("one", 1);
			_stopwords.put("only", 1);
			_stopwords.put("onto", 1);
			_stopwords.put("or", 1);
			_stopwords.put("other", 1);
			_stopwords.put("others", 1);
			_stopwords.put("otherwise", 1);
			_stopwords.put("our", 1);
			_stopwords.put("ours", 1);
			_stopwords.put("ourselves", 1);
			_stopwords.put("out", 1);
			_stopwords.put("over", 1);
			_stopwords.put("own", 1);
			_stopwords.put("part", 1);
			_stopwords.put("per", 1);
			_stopwords.put("perhaps", 1);
			_stopwords.put("please", 1);
			_stopwords.put("put", 1);
			_stopwords.put("rather", 1);
			_stopwords.put("re", 1);
			_stopwords.put("same", 1);
			_stopwords.put("see", 1);
			_stopwords.put("seem", 1);
			_stopwords.put("seemed", 1);
			_stopwords.put("seeming", 1);
			_stopwords.put("seems", 1);
			_stopwords.put("serious", 1);
			_stopwords.put("several", 1);
			_stopwords.put("she", 1);
			_stopwords.put("should", 1);
			_stopwords.put("show", 1);
			_stopwords.put("side", 1);
			_stopwords.put("since", 1);
			_stopwords.put("sincere", 1);
			_stopwords.put("six", 1);
			_stopwords.put("sixty", 1);
			_stopwords.put("so", 1);
			_stopwords.put("some", 1);
			_stopwords.put("somehow", 1);
			_stopwords.put("www", 1);
			_stopwords.put("someone", 1);
			_stopwords.put("something", 1);
			_stopwords.put("sometime", 1);
			_stopwords.put("sometimes", 1);
			_stopwords.put("somewhere", 1);
			_stopwords.put("still", 1);
			_stopwords.put("such", 1);
			_stopwords.put("system", 1);
			_stopwords.put("take", 1);
			_stopwords.put("ten", 1);
			_stopwords.put("than", 1);
			_stopwords.put("that", 1);
			_stopwords.put("the", 1);
			_stopwords.put("their", 1);
			_stopwords.put("them", 1);
			_stopwords.put("themselves", 1);
			_stopwords.put("then", 1);
			_stopwords.put("thence", 1);
			_stopwords.put("there", 1);
			_stopwords.put("thereafter", 1);
			_stopwords.put("thereby", 1);
			_stopwords.put("therefore", 1);
			_stopwords.put("therein", 1);
			_stopwords.put("thereupon", 1);
			_stopwords.put("these", 1);
			_stopwords.put("they", 1);
			_stopwords.put("thickv", 1);
			_stopwords.put("thin", 1);
			_stopwords.put("third", 1);
			_stopwords.put("this", 1);
			_stopwords.put("those", 1);
			_stopwords.put("though", 1);
			_stopwords.put("three", 1);
			_stopwords.put("through", 1);
			_stopwords.put("throughout", 1);
			_stopwords.put("thru", 1);
			_stopwords.put("thus", 1);
			_stopwords.put("to", 1);
			_stopwords.put("together", 1);
			_stopwords.put("too", 1);
			_stopwords.put("top", 1);
			_stopwords.put("toward", 1);
			_stopwords.put("towards", 1);
			_stopwords.put("twelve", 1);
			_stopwords.put("twenty", 1);
			_stopwords.put("two", 1);
			_stopwords.put("un", 1);
			_stopwords.put("under", 1);
			_stopwords.put("until", 1);
			_stopwords.put("up", 1);
			_stopwords.put("upon", 1);
			_stopwords.put("us", 1);
			_stopwords.put("very", 1);
			_stopwords.put("via", 1);
			_stopwords.put("was", 1);
			_stopwords.put("we", 1);
			_stopwords.put("well", 1);
			_stopwords.put("were", 1);
			_stopwords.put("what", 1);
			_stopwords.put("whatever", 1);
			_stopwords.put("when", 1);
			_stopwords.put("whence", 1);
			_stopwords.put("whenever", 1);
			_stopwords.put("where", 1);
			_stopwords.put("whereafter", 1);
			_stopwords.put("whereas", 1);
			_stopwords.put("whereby", 1);
			_stopwords.put("wherein", 1);
			_stopwords.put("whereupon", 1);
			_stopwords.put("wherever", 1);
			_stopwords.put("whether", 1);
			_stopwords.put("which", 1);
			_stopwords.put("while", 1);
			_stopwords.put("whither", 1);
			_stopwords.put("who", 1);
			_stopwords.put("whoever", 1);
			_stopwords.put("whole", 1);
			_stopwords.put("whom", 1);
			_stopwords.put("whose", 1);
			_stopwords.put("why", 1);
			_stopwords.put("will", 1);
			_stopwords.put("with", 1);
			_stopwords.put("within", 1);
			_stopwords.put("without", 1);
			_stopwords.put("would", 1);
			_stopwords.put("yet", 1);
			_stopwords.put("you", 1);
			_stopwords.put("your", 1);
			_stopwords.put("yours", 1);
			_stopwords.put("yourself", 1);
			_stopwords.put("yourselves", 1);
			_stopwords.put("the", 1);
			_stopwords.put("category", 1);
			_stopwords.put("com", 1);

	File input = new File(filename);
	Document doc = Jsoup.parse(input, "UTF-8", "");
	Elements title=doc.select("title");
	StringTokenizer st = cr.token(title.text());
	while (st.hasMoreElements()) 
	{
   		 String f=st.nextToken().toLowerCase();
   		 if(!(_stopwords.containsKey(f)) && f.length()>2)
   			 word.put(f,50);
	}
	//System.out.println(word.keySet());
	
	
	//links
	Elements links = doc.select("link");
	for (Element link : links) 
	{
		  String linkHref = link.attr("href");
		   st = cr.token(linkHref);
		  while (st.hasMoreElements()) 
		{
	   		 String f=st.nextToken().toLowerCase();
	   		 if(!(_stopwords.containsKey(f)) && f.length()>2)
	   			 if(word.containsKey(f))
	   			 {
	   				 int x=word.get(f);
	   				 x=x+1;
	   				 word.put(f,x);
	   			 }
	   			 else
	   				 word.put(f,1);
	   	}
	
	
	  
	  String linktitle=link.attr("title");
	  st = cr.token(linktitle);
	  while (st.hasMoreElements()) 
	{
   		 String f=st.nextToken().toLowerCase();
   		 if(!(_stopwords.containsKey(f)) && f.length()>2)
   			 if(word.containsKey(f))
   			 {
   				 int x=word.get(f);
   				 x=x+10;
   				 word.put(f,x);
   			 }
   			 else
   				 word.put(f,10);
   	}
	}
	
	//body
	Elements body = doc.select("body");
	 st = cr.token(body.text());
	  while (st.hasMoreElements()) 
	{
  		 String f=st.nextToken().toLowerCase();
  		 if(!(_stopwords.containsKey(f)) && f.length()>2)
  			 if(word.containsKey(f))
  			 {
  				 int x=word.get(f);
  				 x=x+1;
  				 word.put(f,x);
  			 }
  			 else
  				 word.put(f,1);
  	}
	
	
	//anchor tag
	Elements anchor=doc.select("a");
	for (Element anr : anchor)
	{
		  String anrHref = anr.attr("href");
		  st = cr.token(anrHref);
		  while (st.hasMoreElements()) 
		{
	   		 String f=st.nextToken().toLowerCase();
	   		 if(!(_stopwords.containsKey(f)) && f.length()>2)
	   			 if(word.containsKey(f))
	   			 {
	   				 int x=word.get(f);
	   				 x=x+1;
	   				 word.put(f,x);
	   			 }
	   			 else
	   				 word.put(f,1);
	   	}
		  String anrText = anr.text();
		  st = cr.token(anrText);
		  while (st.hasMoreElements()) 
		{
	   		 String f=st.nextToken().toLowerCase();
	   		 if(!(_stopwords.containsKey(f)) && f.length()>2)
	   			 if(word.containsKey(f))
	   			 {
	   				 int x=word.get(f);
	   				 x=x+1;
	   				 word.put(f,x);
	   			 }
	   			 else
	   				 word.put(f,1);
	   	}
		 
	}
	
	//paragraph
	Elements para=doc.select("p");
	for (Element pra : para)
	{
		  
		  st = cr.token(pra.text());
		  while (st.hasMoreElements()) 
		{
	   		 String f=st.nextToken().toLowerCase();
	   		 if(!(_stopwords.containsKey(f)) && f.length()>2)
	   			 if(word.containsKey(f))
	   			 {
	   				 int x=word.get(f);
	   				 x=x+1;
	   				 word.put(f,x);
	   			 }
	   			 else
	   				 word.put(f,1);
	   	}
		 
	}
	
	/*
	Set set = word.entrySet();
	Iterator i = set.iterator();
	while (i.hasNext())
	{
		Map.Entry me = (Map.Entry) i.next();
		System.out.print(me.getKey() + ": ");
		System.out.println(me.getValue());
	}*/
	ArrayList<Map.Entry> a = new ArrayList<Map.Entry>(word.entrySet());
	Collections.sort(a,
	         new Comparator() {
	             public int compare(Object o1, Object o2) {
	                 Map.Entry e1 = (Map.Entry) o1;
	                 Map.Entry e2 = (Map.Entry) o2;
	                 return ((Comparable) e2.getValue()).compareTo(e1.getValue());
	             }
	         });
/*	for (Map.Entry e : a) {
        System.out.println(e.getKey() + " " + e.getValue());}*/
	return a;
	
	
	
	
	}
	
}
