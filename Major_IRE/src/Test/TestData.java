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
public class TestData {
	String dataFileName;
	String linksFileName;
	TreeMap<String,String []> test = new TreeMap<String, String[] >();
	public TestData(String s,String s2)
	{
		dataFileName=s;	
		 linksFileName=s2;
	}
	public void text() throws IOException
	{
		 BufferedReader bReader = new BufferedReader( new FileReader(dataFileName));
	        String line;	    
	        FileWriter fw = new FileWriter("/media/82CC3BADCC3B9A7D/major_project/IRData/url3test.txt", false);
		    BufferedWriter file_out= new BufferedWriter(fw); 
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
       	//  System.out.println(key+"#"+value[0]+"#"+value[1]);
       	//System.out.print(key+"|||");
       	  file_out.write(key+"|||");
       	  FindUrls nu = new FindUrls();  
       	  String text=nu.removeurls(value[0]);
       	 String []url_list=value[1].split(",");
       	  
       	 
       	 
       	 
  //   	 System.out.println("url_features");
    /*   	 for(int i1=0;i1<url_list.length;i1++)
      	 {
       	 crawl c = new crawl();  
       	 if(url_list[i1]!=null && !(url_list[i1].equals("")))
       	 {
       		 try
       		 {
       			File f = new File("/media/82CC3BADCC3B9A7D/major_project/IRData/Testing/downloaded_entities_urls/RL2013D01E003/"+url_list[i1]+"/1");
       			if(f.exists() && !f.isDirectory()) { 
			 ArrayList<Map.Entry> a = c.ans("/media/82CC3BADCC3B9A7D/major_project/IRData/Testing/downloaded_entities_urls/RL2013D01E003/"+url_list[i1]+"/1");
			 int l=0;
			 for (Map.Entry e : a)
				 {
					 String s=(String) e.getKey();
					 Integer v =(Integer)e.getValue();
					  if ( !(s.toLowerCase().equals("volvo")))
					  {
						  l++;
						  file_out.write(s+"###");
						  }
					  if(l>11) break;
				 }
       			}
       		 }
       		 catch(Exception e)
       		 {
       			 System.out.println(e);
       		 }
       	  }
       	 
      	  }*/
       	 
       	 
       	 
       	 // System.out.println("tagme_features");
     /* 	  TreeMap <String ,Integer> store=new TreeMap <String ,Integer>();
       	  ValueComparator bvc =  new ValueComparator(store);
       	  TreeMap<String,Integer> sorted_map = new TreeMap <String,Integer> (bvc); 
       	  try
       	  {
       		  tagme_APIRequest req = new tagme_APIRequest();     
       		 //System.out.println("input :"+text);
       		 
       		 String s=req.doPost(text);
       		  
       		 // System.out.println(s);
       		  Pattern p = Pattern.compile("\"title\":\"");  // insert your pattern here
       		  Matcher m = p.matcher(s);
       		  while (m.find())
       		  {      
       			  int j1,n=  m.end();
       			  String f="";
       			  for(j1=n;s.charAt(j1)!='\"';j1++)
       			  {
       				  f=f+s.charAt(j1);
       			  }
       			  if ( !(f.toLowerCase().equals("volvo")))
       			  {
       			 System.out.print(f+"###");
       				 file_out.write(f+"###");
       			  }
       		  }
    	 }
    	catch(IOException e)
    	 {
    		 System.out.println(e);
         }*/
       	System.out.println();
        file_out.newLine(); 
       }
	
} 
  

	
	public static void main(String args []) throws IOException
	{
		TestData c = new TestData("/media/82CC3BADCC3B9A7D/major_project/IRData/Testing/tweet_text_modified/RL2013D01E003_texts.tsv","/media/82CC3BADCC3B9A7D/major_project/IRData/Testing/tweet_info/RL2013D01E003.dat");
		c.text();
	}
	

}
