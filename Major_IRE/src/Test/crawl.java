package Test;


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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class crawl {
	 public static HashMap _stopwords = new HashMap();
	 
	static HashMap<String,Integer> word=new HashMap<String,Integer>();
	public StringTokenizer token(String tk)
	{
		StringTokenizer st = new StringTokenizer(tk,"'\";/\\_|=[]{},\n\t!@#$%^&*()1234567890<>-.:?~`+- 	=’ˈ“”—");
		return st;
	}
	
	public static ArrayList<Map.Entry> ans  (String filename) 
	{
		     crawl cr=new crawl();
	File input = new File(filename);
	
	Document doc = null;
	try {
		doc = Jsoup.parse(input, "UTF-8", "");
	} catch (IOException e3) {
		// TODO Auto-generated catch block
		System.out.println(e3);
	}
	Elements title=doc.select("title");
	String title_text=title.text();
	Elements body = doc.select("body");
	try
	{
		
	if(!(title_text.equals("")) && title_text!=null)
	{
	tagme_APIRequest req = new tagme_APIRequest();
	String s=req.doPost(title_text);
    // Pattern p = Pattern.compile("\"id\":");  // insert your pattern here
	 Pattern p = Pattern.compile("\"title\":\"");
	Matcher m = p.matcher(s);
    while (m.find())
    {      
         int j1,n=  m.end();
         String f="";
         for(j1=n;s.charAt(j1)!='\"';j1++)
        // for(j1=n;s.charAt(j1)!=',';j1++)
         {
        	 f=f+s.charAt(j1);
         }
         if(word.containsKey(f))
         {
        	 word.put(f,word.get(f)+1);
       			       
         }
         else
         {
        	 word.put(f,1);
         }
     }
	}
	}
	catch(Exception e)
	{
		
	}
    String body_text = body.text();
    String[] t=body_text.split("(?<=[.?!])\\s+(?=[a-zA-Z])");
  for(int i=0;i<3;i++)
    {
	  try
     	{
    	if(!(t[i].equals("")) && t[i]!=null)
    	{
    	
    	//System.out.println("here");	
    	tagme_APIRequest req2 = new tagme_APIRequest();
    	String s2=req2.doPost(t[i]);
    //    Pattern p2 = Pattern.compile("\"id\":");  // insert your pattern here
     Pattern p2 = Pattern.compile("\"title\":\"");
    	Matcher m2 = p2.matcher(s2);
        while (m2.find())
        {      
             int j1,n=  m2.end();
             String f="";
        //     for(j1=n;s2.charAt(j1)!=',';j1++)
             for(j1=n;s2.charAt(j1)!='\"';j1++)
             {
            	 f=f+s2.charAt(j1);
             }
             if(word.containsKey(f))
             {
            	 word.put(f,word.get(f)+1);
           			       
             }
             else
             {
            	 word.put(f,1);
             }
        }
    	}
     	}
    	catch(Exception e)
    	{
    		
    	}
     
    }
	 

	 ArrayList<Map.Entry> a = new ArrayList<Map.Entry>(word.entrySet());
	 
	 
	Collections.sort(a,new Comparator() {
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
