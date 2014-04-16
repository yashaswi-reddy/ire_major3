package Train;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

import java.util.*;
public class Intersection {
    public int intersect(String []strings1 , String []strings2) {
        
        List<String> list=Arrays.asList(strings1);
        Set<String> set=new LinkedHashSet<String>(Arrays.asList(strings1));
        set.retainAll(Arrays.asList(strings2));
        return set.size();
    }
}