package Train;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import cmu.arktweetnlp.Tagger;
import cmu.arktweetnlp.Tagger.TaggedToken;

public class Keyphrases {
	Tagger t1;
	public Keyphrases() throws Exception {
		t1 = new Tagger();
		try {
			t1.loadModel("/media/82CC3BADCC3B9A7D/major_project/IRData/models/model.20120919");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
 Keyphrases t =new Keyphrases();
 System.out.println(t.tokenizeNouns("Birthdays over shout out my boys @BrinMonkman @FreddyBarratt @TomDobz havin it large #volvo"));
	}
	public ArrayList<String> tokenizeNouns(String tweet) throws IOException 
	{
		// TODO Auto-generated method stub
		//String entityname=entityid;
		ArrayList<String> arr = new ArrayList<String>();
		String l = tweet.replaceAll("\\s+", " ");
		String tweet1="";
		String l1 = l.replaceAll("[^\\x00-\\x7F]", "");
		List<TaggedToken> pos_list=null;
		try
		{
		pos_list = t1.tokenizeAndTag(l1);
		}
		catch (Exception e)
		{
			arr.add(tweet1.trim());
			return arr;
		}
		String curr = "";
		for(int i=0;i<pos_list.size();i++){
			String tag1=pos_list.get(i).tag;
			String word1=pos_list.get(i).token;
			//System.out.println(word1 + " " + tag1);
			if(!(tag1.compareTo("U")==0))
			{
				tweet1+=" "+word1;
			}
			if(tag1.compareTo("N")==0 || tag1.compareTo("Z")==0  ||  tag1.compareTo("^")==0  ||tag1.compareTo("S")==0){
				curr+=word1+" ";
			}
			else if(!curr.equals("")){
				arr.add(curr.trim());
				curr = "";
			}
		}
		if(!curr.equals("")){
			arr.add(curr.trim());
			curr = "";
		}	
		//arr.add(tweet1.trim());
		//System.out.println(arr);
		return arr;
	}

}
