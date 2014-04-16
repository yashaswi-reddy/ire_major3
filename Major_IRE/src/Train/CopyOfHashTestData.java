package Train;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Features.tagme_APIRequest;


public class CopyOfHashTestData {
	String dataFileName;
	String linksFileName;
	TreeMap<String,String []> test = new TreeMap<String, String[] >();
	
	public static void quickSort(int [] a,int [] b,int f, int l)  
    {  
    if(f>=l) return; 
    int piv=a[l];
    int less=f-1,more;
    for(more=f;more<l;more++)
    {
    if(a[more]<piv)
    {
    less++;
    a[more]=(a[more]+a[less])-(a[less]=a[more]);
    b[more]=(b[more]+b[less])-(b[less]=b[more]);
    }
    }
    a[less+1]=(a[less+1]+a[l])-(a[l]=a[less+1]);
    b[less+1]=(b[less+1]+b[l])-(b[l]=b[less+1]);
    quickSort(a,b,f,less);
    quickSort(a,b,less+2,l); 
    }
	
	public CopyOfHashTestData(String s,String s2)
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
       	  //System.out.println(key+"#"+value[0]+"#"+value[1]);
       	  FindUrls nu = new FindUrls();  
       	  String text=nu.removeurls(value[0]);
       	  System.out.println(text);
       	  String []url_list=value[1].split(",");     	        	  
      	  //System.out.println("hash_tags"); 
       	  String [] tokens = text.split(" ");
       	  String [] hashtags = new String [tokens.length];
       	  int i=0;
       	  for(int k=0; k<tokens.length ;k++ )
       	  {
       		  if(!(tokens[k].equals("")) && tokens[k]!=null && tokens[k].charAt(0)=='#')
       		  {  
       			 hashtags[i++]=tokens[k];
       		  }
       	  }
       	SustopicSearchLucene l = new SustopicSearchLucene();
       	Intersection in = new Intersection ();  
       	int hash_int [] = new int [67];
       	int index [] = new int [67];
       	for(i=0;i<67;i++)
       	  {
       		  String [] ans= l.searching(Integer.toString(i)).split("\\|\\|\\|");
       		  hash_int[i]=in.intersect(ans,hashtags);
       		  index[i]=i;	 
       	  }
       	quickSort(hash_int,index,0,66);
       	
       	for(i=0;i<67;i++)
       	{
       		if(hash_int[i]>0)
       		System.out.print(index[i]+":"+hash_int[i]+",");
       		//else
       			//break;
       	}
       	System.out.println();
	}
	
} 
  

	
	public static void main(String args []) throws Exception
	{
		CopyOfHashTestData c = new CopyOfHashTestData("/media/82CC3BADCC3B9A7D/major_project/IRData/Testing/tweet_text_modified/RL2013D01E003_texts.tsv","/media/82CC3BADCC3B9A7D/major_project/IRData/Testing/tweet_info/RL2013D01E003.dat");
		c.text();
	}
	

}
