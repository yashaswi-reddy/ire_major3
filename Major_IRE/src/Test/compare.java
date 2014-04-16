package Test;

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
//http://wikipedia-miner.cms.waikato.ac.nz/services/?compare
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
			//System.out.println("Sorry! wrong title entered");
		}

	}
	

	
	
	public static int comp2(String s1,String s2) throws IOException
	{
		//System.out.println("in wikipidea miner check");
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in"); 
		System.setProperty("http.proxyPort", "8080");
		String url="http://wikipedia-miner.cms.waikato.ac.nz/services/compare?ids1="+s1+"&ids2="+s2+"&titles=true";
		Document doc = Jsoup.connect(url).timeout(0).get();
		//System.out.println(doc.text());
		Elements comp = doc.select("comparison");
		//System.out.println(comp.text());
		int count=0;
		int sum=0;
		for (Element cmp : comp) 
		{
			//System.out.println("here");
			
			  String lowtitle = cmp.attr("lowtitle");
			  String hightitle = cmp.attr("hightitle");
			  String similarity = cmp.attr("relatedness");
			//  System.out.println(lowtitle+"|"+hightitle+"|"+similarity);
				Float simi=Float.parseFloat(similarity);
				int d = (int)(Math.ceil(simi*100));
				if(d>=5)
				{
				sum=sum+d;
				count++;
				//System.out.print(d+" ");
				}
		}
		//System.out.println();
		int avg=0;
		if(count!=0) 
		avg=sum/count;
		//System.out.println("%age similarity is "+avg);
		return avg;

	}
	
	public static String Idfind(String s1) throws IOException
	{
		int st,strt;
		boolean flag=false;
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in"); 
		System.setProperty("http.proxyPort", "8080");
		String url="http://en.wikipedia.org/w/api.php?action=query&generator=search&gsrsearch="+s1+"&srprop=size|wordcount|timestamp|snippet";
		Document doc = Jsoup.connect(url).timeout(0).get();
		String x=doc.text();
		System.out.println("abcd "+s1);
		System.out.println(x);
	//System.out.println("text xml \n"+x.substring(x.indexOf("<pages>")+7,x.indexOf("</pages>")+8));
		
		StringBuilder pg=new StringBuilder();
		pg.append(x.substring(x.indexOf("<pages>")+7,x.indexOf("</pages>")).trim());
		
		while((pg.length()>0))
		{
			strt=pg.indexOf("pageid")+8;
			String pageid=pg.substring(pg.indexOf("pageid")+8,pg.indexOf("\"",strt));
			
			st=pg.indexOf("title")+7;
			String title=pg.substring(pg.indexOf("title")+7,pg.indexOf("\"",st));
			if(title.equals(s1))
			{
				flag=true;
				System.out.println("pg id is "+pageid);
				return pageid;
								
			}
			pg.delete(pg.indexOf("<"),pg.indexOf(">")+1);
			String y=pg.toString().trim();
			pg.setLength(0);
			pg.append(y);
		}
		return "";
	}
	public  HashMap ret_idset(Vector<String> inp) 
	{
		String x = null;
		compare cmp=new compare();
		HashMap<String,String> id =new HashMap<String,String>();
		for(int i=0;i<inp.size();i++)
		{
		
		try 
		{
				System.out.println("in");
				x = cmp.Idfind(inp.get(i));
				System.out.println("out");
			//System.out.println("acb "+x);
			if(!x.equals(""))
				id.put(inp.get(i),x);
			
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			 System.out.println("title not found");
		}
		}
		/*Set set = id.entrySet();
		Iterator ii = set.iterator();
		while (ii.hasNext())
		{
			Map.Entry me = (Map.Entry) ii.next();
			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}*/
		return id;
	}
	
	public void cmpr(Vector<String> inp1,Vector<String> inp2) throws IOException
	{
		compare cmp=new compare();
		HashMap id1=cmp.ret_idset(inp1);
		//System.out.println("called");
		HashMap id2=cmp.ret_idset(inp2);
	//	System.out.println("done");
		Set set = id1.entrySet();
		Iterator ii = set.iterator();
		int i=0;
		String id_1="";
		while (ii.hasNext())
		{
			
			Map.Entry me = (Map.Entry) ii.next();
			if(i==0)	id_1=id_1+me.getValue();
			else		id_1=id_1+','+me.getValue();
			i++;
			
			
		}
			i=0;
			Set set2 = id2.entrySet();
			Iterator ii2 = set2.iterator();
			int i2=0;
			String id_2="";
			while (ii2.hasNext())
			{
				
				Map.Entry me2 = (Map.Entry) ii2.next();
				if(i==0)	id_2=id_2+me2.getValue();
				else		id_2=id_2+','+me2.getValue();
				i++;
		}
				System.out.println("id1=" +id_1);
				System.out.println("id2=" +id_2);
				cmp.comp2(id_1,id_2);

	}
	
	public static void main(String args[]) throws IOException
	{
		//System.out.println(pg);
		compare cmp=new compare();
		Vector<String> inp1=new Vector<String>();
		inp1.add("Kiwi");
		inp1.add("Kea");
		Vector<String> inp2=new Vector<String>();
		inp2.add("Kea");
		inp2.add("Kakapo");
		inp2.add("Takahē");
		cmp.cmpr(inp1, inp2);
			}
}