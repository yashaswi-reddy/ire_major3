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
public class namedTrain 
{
	   public static void main(String[] args) throws Exception
	   {
	      MultiHashMap mp=new MultiHashMap();
	      BufferedReader bReader = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_2/entity2"));
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
	         FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_2/features/named_entities/subtopics"+fss+".txt", false);
	         BufferedWriter file_out= new BufferedWriter(fw);
	         file_out.write((String)me.getKey());
	         file_out.newLine();
	         String named_entities="";

	         TreeMap <String ,Integer> named_store=new TreeMap <String ,Integer>();
	         ValueComparator bvc =  new ValueComparator(named_store);
		     TreeMap<String,Integer> sorted_namedstore = new TreeMap <String,Integer> (bvc);
		     
	         for(int j=0;j<list.size();j++)
	         {
	        	FindUrls nu = new FindUrls();
	        	String text=nu.removeurls(((String) list.get(j)).split("\\|\\|\\|",-1)[0]);
	        	String tweet_text =((String) list.get(j)).replaceAll("[^\\w\\s\\-_]", " ");
	        	named_event abc =new named_event();
	        	named_entities =named_entities+abc.Named_entities(tweet_text);
	        	//System.out.println(tweet_text);
	        	//System.out.println(abc.event_phrases(tweet_text));
	         
	         }
	         String [] tokens = named_entities.split("\t");
	         for(int kk=0;kk< tokens.length ;kk++)
	         {
	        	 if(!tokens[kk].equals(""))
	        	 {
	        		// file_out.write(tokens[kk]);
	     	        // file_out.newLine();
	        		 if(named_store.containsKey(tokens[kk]))
	                 {
	        			 named_store.put(tokens[kk],named_store.get(tokens[kk])+1);
	               			       
	                 }
	                 else
	                 {
	                	 named_store.put(tokens[kk],1);
	                 }
	        	 }
	          //System.out.println(tokens[kk]);            	
	         }	
	         sorted_namedstore.putAll(named_store); 
			    for (Entry<String,Integer> entry : sorted_namedstore.entrySet())
			        {
			        	  String key = entry.getKey();
			        	  Integer value = entry.getValue();
			        	  if(!key.equals("audi"))
			        	  {
			        	  file_out.write(key+"###"+value);
			        	  file_out.newLine();
			        	  }
			        }
	       	
	        file_out.close(); 
		     fw.close();
	      
	      
	      
	      }
	      
	   }

	      
	   
}
