

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

public class Action_phrasesTrain 
{
	   public static void main(String[] args) throws Exception
	   {
	      MultiHashMap mp=new MultiHashMap();
	      BufferedReader bReader = new BufferedReader(new FileReader("/media/82CC3BADCC3B9A7D/major_project/IRData/entity3"));
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
	         FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/features/event_phrases/subtopics"+fss+".txt", false);
	         BufferedWriter file_out= new BufferedWriter(fw);
	         file_out.write((String)me.getKey());
	         file_out.newLine();
	         String keyphrase_entities="";

	         TreeMap <String ,Integer> keyphrase_store=new TreeMap <String ,Integer>();
	         ValueComparator bvc =  new ValueComparator(keyphrase_store);
		     TreeMap<String,Integer> sorted_keyphrasestore = new TreeMap <String,Integer> (bvc);
		     
	         for(int j=0;j<list.size();j++)
	         {
	        	FindUrls nu = new FindUrls();
	        	String text=nu.removeurls(((String) list.get(j)).split("\\|\\|\\|",-1)[0]);
	        	//keyphrase_event abc =new keyphrase_event();
	        	Actionphrases abc=new Actionphrases();
	        //	keyphrase_entities =keyphrase_entities+abc.tokenizeNouns(tweet_text);
	        	//System.out.println("input:"+text);
	        	
	        	ArrayList<String> ans =abc.tokenizeNouns(text);
	        	//System.out.println(ans);
	        	for (String item : ans)
	        	{   
	        	item=item.toLowerCase(); 
	        	System.out.println(item);
	        	if(keyphrase_store.containsKey(item))
                {
       			 keyphrase_store.put(item,keyphrase_store.get(item)+1);
              			       
                }
                else
                {
               	 keyphrase_store.put(item,1);
                }
	        	}
	        	
	         }
	        
	         sorted_keyphrasestore.putAll(keyphrase_store); 
			    for (Entry<String,Integer> entry : sorted_keyphrasestore.entrySet())
			        {
			        	  String key = entry.getKey();
			        	  Integer value = entry.getValue();
			        	  file_out.write(key+"###"+value);
			        	  file_out.newLine();
			        }
	       
	        file_out.close(); 
		     fw.close();
	      
	      
	      
	      }
	      
	   }

	      
	   
}