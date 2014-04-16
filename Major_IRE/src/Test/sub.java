package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class sub {
	static HashMap<Integer,String> sub1=new HashMap<Integer,String>();
	public static void create_sub() throws IOException
	{
		int i=0;
		for(i=0;i<67;i++)
		{
			BufferedReader br1 = new BufferedReader(new FileReader(new File("/media/82CC3BADCC3B9A7D/major_project/IRData/entity_3/features/keyphrase_entities/subtopics"+i+".txt")));
		    String s=br1.readLine();
		    sub1.put(i,s);
		   // System.out.println(i + "  "+s);
		}
	}

}