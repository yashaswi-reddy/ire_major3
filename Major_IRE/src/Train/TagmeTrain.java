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
public class TagmeTrain 
{
	   public static void main(String[] args) throws IOException
	   {
	      MultiHashMap mp=new MultiHashMap();
	      BufferedReader bReader = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_8/entity8"));
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
	         FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_8/features/tagme/subtopics"+fss+".txt", false);
	       //  FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_2/features/tagme/subtopics"+fss+".txt", false);
	         BufferedWriter file_out= new BufferedWriter(fw);
	         file_out.write((String) me.getKey());
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
	        	try
	        	 {
	           	 tagme_APIRequest req = new tagme_APIRequest();        
	           	 String s=req.doPost(text);
	           //	 System.out.println(s);
	          // 	 Pattern p = Pattern.compile("\"id\":");  // insert your pattern here
	           	 Pattern p = Pattern.compile("\"title\":\"");
	           	 Matcher m = p.matcher(s);
	                while (m.find())
	                {      
	                 int j1,n=  m.end();
	                 String f="";
	               //  for(j1=n;s.charAt(j1)!=',';j1++)
	                 for(j1=n;s.charAt(j1)!='\"';j1++)
	                 {
	                	 f=f+s.charAt(j1);
	                 }
	                 if(tagme_store.containsKey(f))
	                 {
	                	 tagme_store.put(f,tagme_store.get(f)+1);
	               			       
	                 }
	                 else
	                 {
	                	 tagme_store.put(f,1);
	                 }
	                }
	        	 }
	        	 catch(IOException e)
	        	 {
	        	 
	              }
		   
	         } 	     	 
	        sorted_tagmestore.putAll(tagme_store); 
	        int count=0;
		    for (Entry<String,Integer> entry : sorted_tagmestore.entrySet())
		        {
		    	      if(count>10)
		    	      {
		    	    	  String key = entry.getKey();
		    	    	  Integer value = entry.getValue();
		    	    	  if(value>=2)
		    	    	  {
		    	    		  if(!(key.toLowerCase().equals("volkswagen")))
		    	    		  {
		    	    			  file_out.write(key+"###"+value);
		    	    			  file_out.newLine();
		    	    		  }
		    	    		  else
                             	  count--;
		    	    	  }
		    	    	  else
		    	    		  break;
		    	      }
		    	      else
		    	      {
		    	    	  String key = entry.getKey();
		    	    	  Integer value = entry.getValue();
		    	    	  if(!(key.toLowerCase().equals("volkswagen")))
		    	    	  {
		    	    		  file_out.write(key+"###"+value);
		    	    		  file_out.newLine();
		    	    	  }
		    	    	  else
		    	    	  	  count--;
		    	      }
		    	      System.out.println("count:"+count);
		        	  count++;
		        }
	        file_out.close(); 
		     fw.close();
	      
	      
	      
	      }
	      
	   }

	      
}
