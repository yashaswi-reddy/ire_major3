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
public class HashTrain 
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
	         FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_8/features/hash_tags/subtopics"+fss+".txt", false);
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
	        	String [] tokens = text.split(" ");
	        	for(int k=0; k<tokens.length ;k++ )
	          	  {
	          		  
	          		  if(!(tokens[k].equals("")) && tokens[k]!=null && tokens[k].charAt(0)=='#')
	          			  hash_list[hashn++]=tokens[k];
	          	  }
	         } 	 
	         for(int kk=0;kk<hashn ;kk++)
	         {
	        	 file_out.write(hash_list[kk]);
		         file_out.newLine();
	         }
	        file_out.close(); 
		     fw.close();
	      
	      
	      
	      }
	      
	   }

	      
}
