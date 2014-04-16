package Train;

import org.apache.commons.collections.MultiHashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class urlTrain 
{
	   public static void main(String[] args) throws IOException
	   {
	      MultiHashMap mp=new MultiHashMap();
	      BufferedReader bReader = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_5/entity5"));
	      String line;	    
	      Stemming stemming =new Stemming();
	      while ((line = bReader.readLine()) != null) 
	      {
	      String [] s=line.split("\\|\\|\\|",-1);
	      String s1 =s[1]+"|||"+s[2];
	      mp.put(s[3],s1);  
	      }
	      List list = null;
	      Set set = mp.entrySet();
	      Iterator i = set.iterator();
	      Integer fineno=0;
	      while(i.hasNext()) 
	      {
	         Map.Entry me = (Map.Entry)i.next();
	         list=(List)mp.get(me.getKey());
	         System.out.println("subtopic :" +me.getKey());
	         String fss=(fineno++).toString();
	       //  FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/features/url/subtopics"+fss+".txt", false);
	         FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_5/features/url/subtopics"+fss+".txt", false);
	         BufferedWriter file_out= new BufferedWriter(fw);
	         file_out.write((String)me.getKey());
	         file_out.newLine();
	         TreeMap <String ,Integer> tagme_store=new TreeMap <String ,Integer>();
	         ValueComparator bvc =  new ValueComparator(tagme_store);
		     TreeMap<String,Integer> sorted_tagmestore = new TreeMap <String,Integer> (bvc);
	         String [] url_list=new String [1000];
	         String [] hash_list=new String [1000];   
	         int hashn=0;
	         int urln=0;
	         for(int j=0;j<list.size();j++)
	         {
	        	FindUrls nu = new FindUrls();
	        	String text=nu.removeurls(((String) list.get(j)).split("\\|\\|\\|",-1)[0]);
	        	String url=((String) list.get(j)).split("\\|\\|\\|",-1)[1];
	       	    if(url!=null && url != "" )
	        	{
	        		String []ss = url.split(",");
	        		for(int ii=0;ii<ss.length;ii++)
	        		{
	        			url_list[urln++]=ss[ii];
	        		}
	        	}
	         	List<String> urls=	nu.extractUrls((String) list.get(j));
	        	 
		   
	         } 	 
	         TreeMap <String ,Integer> url_store=new TreeMap <String ,Integer>();
	       	  ValueComparator bvc2 =  new ValueComparator(url_store); 
	       	 TreeMap<String,Integer> sorted_urlstore = new TreeMap <String,Integer> (bvc2); 
	       	  for(int i1=0;i1<urln;i1++)
	       	  {
	       		 File folder = new File("/media/82CC3BADCC3B9A7D/major_project/IRData/Training/downloaded_external_links/RL2013D01E005/"+url_list[i1]);
	       		 File[] listOfFiles = folder.listFiles();
	       		 try
	       		 {
	       			 for (int i2 = 0; i2 < listOfFiles.length; i2++) 
	       			 {
	       				 if (listOfFiles[i2].isFile())
	       				 {
	       					// System.out.println("File " + listOfFiles[i2].getName());
	       					 crawl c = new crawl();  
	       					 ArrayList<Map.Entry> a = c.ans("/media/82CC3BADCC3B9A7D/major_project/IRData/Training/downloaded_external_links/RL2013D01E005/"+url_list[i1]+"/"+listOfFiles[i2].getName());
	       					 for (Map.Entry e : a)
	       					 {
	       						 String s=(String) e.getKey();
	       						 Integer v =(Integer)e.getValue();
	       					//	 System.out.println(s);
	       					//	System.out.println(v);
	       						 if(url_store.containsKey(s))
	       						 {
	       							 url_store.put(s,url_store.get(s)+v);		
	       						 }
	       						 else
	       						 {
	       							 url_store.put(s,v);	
	       						 }
	        	         		
	       					 }
	        	       
	       				 }
	       			 }
	                 
	       		 }
	       		catch(NullPointerException n)
	   	        {
	   	    	System.out.println(n); 
	   	         }
	        }
	       	try
	       	{	       	
	       	sorted_urlstore.putAll(url_store);
	        int l=0;
	        int count=0;
	        for (Entry<String,Integer> entry3 : sorted_urlstore.entrySet())
	     	{
	        	        if(count>10)
	        	        {
	        	        	String key3 = entry3.getKey();
	        	        	Integer value3 = entry3.getValue();
	        	        	if(value3>=2)
	        	        	{
	        	        		//if(!(key3.toLowerCase().equals("volkswagen")))
	        	        		if(!(key3.toLowerCase().equals("toyota")))
	        	        		{	    
	        	        			file_out.write(key3+"###"+value3);
	        	        			file_out.newLine();
	        	        		}
	        	        		else
	        	        			count--;
	        	        	}
	        	        	else break;
	        	        }
	        	        else
	        	        {
	        	        	String key3 = entry3.getKey();
		     	            Integer value3 = entry3.getValue();
		     	           // if(!(key3.toLowerCase().equals("volkswagen")))
		     	           if(!(key3.toLowerCase().equals("toyota")))
					        {
		     	            	file_out.write(key3+"###"+value3);
		     	            	file_out.newLine();
					        }
		     	        	 else
		     	        		 count--;
		     	        	  
	        	        }
	     	            count++;
	     	} 
	     	}
	       	
	     	catch(NullPointerException e)
	      {
	    	  
	      }               	
	        file_out.close(); 
		     fw.close();
	      }
	      
	   }

	      
}
