package Train;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class classifier {
	String dataFileName;
	String linksFileName;
	String labelFileName;
	TreeMap<String,String []> train = new TreeMap<String, String[] >();
	public classifier(String s,String s2,String s3)
	{
		dataFileName=s;	
		 linksFileName=s2;
		 labelFileName=s3;
	}
	public void text() throws IOException
	{
		 BufferedReader bReader = new BufferedReader(
	                new FileReader(dataFileName));
	 
	        String line;	    
	        /**
	         * Looping the read block until all lines in the file are read.
	         */
	        while ((line = bReader.readLine()) != null) {
	 
	            /**
	             * Splitting the content of tabbed separated line
	             */
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
	            train.put(value1,data);
	           
	       }
	        bReader.close();
	    
	 BufferedReader bReader2 = new BufferedReader(
             new FileReader(linksFileName));

     String line2;	    
     /**
      * Looping the read block until all lines in the file are read.
      */
     while ((line2 = bReader2.readLine()) != null) {

         /**
          * Splitting the content of tabbed separated line
          */
         String datavalue[] = line2.split("\t");
         String value1 = datavalue[0];
         value1=value1.replaceAll("\"", "");
         String  value2 = datavalue[4];
         value2=value2.replaceAll("\"", "");
         String  value3=datavalue[8];
         value3=value3.replaceAll("\"", "");
        if(value2.equals("EN"))
         //System.out.println(value2);
         {
         String [] data = new String [4];
         try
         {
         data=train.get(value1);
         {
         //System.out.println(value1+":"+data);
         //System.out.println(value3+"hello");
         if(!value3.equals(null))
         data[1]=value3;
         else
         data[1]="";	 
         train.put(value1,data);
       //  System.out.println(value1+":"+train.get(value1)[0]+":"+train.get(value1)[1]);
         }
         }
         catch(NullPointerException n)
         {
        	 
         }
    }
         }
     bReader2.close();
     
     
     BufferedReader bReader3 = new BufferedReader(
             new FileReader(labelFileName));

     String line3;	    
     /**
      * Looping the read block until all lines in the file are read.
      */
     while ((line3 = bReader3.readLine()) != null) {

         /**
          * Splitting the content of tabbed separated line
          */
         String datavalue[] = line3.split("\t");
         String value1 = datavalue[0];
         value1=value1.replaceAll("\"", "");
        
         //System.out.println(value1);
         String  value2 = datavalue[5];
         value2=value2.replaceAll("\"", "");
         String [] data = new String [4];
         try
         {
         data=train.get(value1);
         {
         if(!value2.equals(null))
         data[2]=value2;
         else
         data[2]="";	 
         train.put(value1,data);
         System.out.println(value1+"|||"+train.get(value1)[0]+"|||"+train.get(value1)[1]+"|||"+train.get(value1)[2]);
         }
         }
         catch(NullPointerException n)
         {
        	 
         }
    }
         
     bReader3.close();
} 


	
	public static void main(String args []) throws IOException
	{
		classifier c = new classifier("/media/82CC3BADCC3B9A7D/major_project/IRData/Training/tweet_text_modified/RL2013D01E008_texts.tsv","/media/82CC3BADCC3B9A7D/major_project/IRData/Training/tweet_info/RL2013D01E005.dat","/media/82CC3BADCC3B9A7D/major_project/IRData/Training/labeled/RL2013D01E008.dat");
		c.text();
	}
	

}
