package Features;

import java.io.File;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class compare
{
	public static void comp(String s1,String s2) throws IOException
	{
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in"); 
		System.setProperty("http.proxyPort", "8080");
		String url="http://wikipedia-miner.cms.waikato.ac.nz/services/compare?term1="+s1+"&term2="+s2;
		Document doc = Jsoup.connect(url).timeout(0).get();
		//System.out.println(doc.html());
		//System.out.println("check \n"+doc.select("unknowntermmessage"));
		String chk=doc.select("unknowntermmessage").text();
		if(chk.isEmpty())
		{
			//System.out.println(doc.select("message").attr("relatedness"));
			Float similarity=Float.parseFloat(doc.select("message").attr("relatedness"));
			int d = (int)(Math.ceil(similarity*100));
			System.out.println(d);	
		}
		else
		{
			System.out.println("Sorry! wrong title entered");
		}

	}
	
	public static void main(String args[]) throws IOException
	{
		compare cmp=new compare();
		cmp.comp("kiwi","takahe");
	}
}