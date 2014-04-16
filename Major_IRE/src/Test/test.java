package Test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test {

    public static void main(String[] args) throws Exception {

    /*    TreeMap<String,Integer> map = new TreeMap<String,Integer>();
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Integer> sorted_map = new TreeMap<String,Integer>(bvc);

        map.put("A",99);
        map.put("B",67);
        map.put("C",66);
        map.put("D",69);

        System.out.println("unsorted map: "+map);

        sorted_map.putAll(map);

        System.out.println("results: "+sorted_map);*/
        named_event n = new named_event(); 	
    	int i=0;
    	for(i=0;i<30 ;i++)
    	{
    	System.out.println(n.parse("Birthdays over shout out my boys @BrinMonkman @FreddyBarratt @TomDobz havin it large #volvo|||"));
    	}
    //System.out.println(n.Named_entities("Rememeber folks, Geoge Brandis is the sharpest legal mind in the Coalition, just like Sophie Mirabella is its Science spokesperson|||"));
    
    }             
    }


