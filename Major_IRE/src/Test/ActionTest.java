package Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Features.tagme_APIRequest;


public class ActionTest {
	String dataFileName;
	String linksFileName;
	TreeMap<String,String []> test = new TreeMap<String, String[] >();
	public ActionTest(String s,String s2)
	{
		dataFileName=s;	
		 linksFileName=s2;
	}
	public void text() throws Exception
	{
		 BufferedReader bReader = new BufferedReader( new FileReader(dataFileName));
	        String line;	    
	    
	       
	        while ((line = bReader.readLine()) != null) {
	            String datavalue[] = line.split("\t");
	            String value1 = datavalue[0];
	            value1=value1.replaceAll("\"", "");
	            String  value4 = datavalue[3];
	            value4=value4.replaceAll("\"", "");
	            String [] data = new String [4];
	            if(value4.equals(null))
	            	data[0]="";
	            else	
	            data[0]=value4;
	            data[1]="";
	            data[2]="";
	            data[3]="";
	            test.put(value1,data);          
	       }
	        bReader.close();
	    
	 BufferedReader bReader2 = new BufferedReader(
             new FileReader(linksFileName));
     String line2;	    
     while ((line2 = bReader2.readLine()) != null) {
         String datavalue[] = line2.split("\t");
         String value1 = datavalue[0];
         value1=value1.replaceAll("\"", "");
         String  value2 = datavalue[4];
         value2=value2.replaceAll("\"", "");
         String  value3=datavalue[8];
         value3=value3.replaceAll("\"", "");
        if(value2.equals("EN"))
        {
         String [] data = new String [4];
         try
         {
         data=test.get(value1);
         {
         if(!value3.equals(null))
         data[1]=value3;
         else
         data[1]="";	 
         test.put(value1,data);
         //System.out.println(value1+"|||"+test.get(value1)[0]+"|||"+test.get(value1)[1]);
         }
         }
         catch(NullPointerException n)
         {
        	 
         }
        }
         }
     bReader2.close();
    
     
     for (Entry<String,String []> entry : test.entrySet())
       {
       	  String key = entry.getKey();
       	  String [] value = entry.getValue();
       	 FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/test-features/event_phrases/"+key+".txt", false);
         BufferedWriter file_out= new BufferedWriter(fw);
       	  FindUrls nu = new FindUrls();  
       	  String text=nu.removeurls(value[0]);
       	  System.out.println(text);
       	  String []url_list=value[1].split(",");     	        	  
      	  String [] tokens = text.split(" ");
       	  int i=0;
         Actionphrases abc=new Actionphrases();
         ArrayList<String> ans =abc.tokenizeNouns(text);
         for (String item : ans)
     	 {   
        	 item=item.toLowerCase();
        	 if(!item.equals("volvo"))
        	 {
             file_out.write(item);
             file_out.newLine();
        	 } 
        }
         file_out.close();
	}
	
} 
  

	
	public static void main(String args []) throws Exception
	{
		ActionTest c = new ActionTest("/media/82CC3BADCC3B9A7D/major_project/IRData/Testing/tweet_text_modified/RL2013D01E003_texts.tsv","/media/82CC3BADCC3B9A7D/major_project/IRData/Testing/tweet_info/RL2013D01E003.dat");
		c.text();
	}
	

}
