package Stanford_ner;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



/** This is a demo of calling CRFClassifier programmatically.
 *  <p>
 *  Usage: <code> java -mx400m -cp "stanford-ner.jar:." NERDemo [serializedClassifier [fileName]]</code>
 *  <p>
 *  If arguments aren't specified, they default to
 *  ner-eng-ie.crf-3-all2006.ser.gz and some hardcoded sample text.
 *  <p>
 *  To use CRFClassifier from the command line:
 *  java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier
 *      [classifier] -textFile [file]
 *  Or if the file is already tokenized and one word per line, perhaps in
 *  a tab-separated value format with extra columns for part-of-speech tag,
 *  etc., use the version below (note the 's' instead of the 'x'):
 *  java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier
 *      [classifier] -testFile [file]
 *
 *  @author Jenny Finkel
 *  @author Christopher Manning
 */

public class ExtractEntities {

    public static void main(String[] args) throws IOException {

      String serializedClassifier = "/home/yashaswi/workspace/Major_IRE/src/Stanford_ner/classifiers/english.all.3class.distsim.crf.ser.gz";

      if (args.length > 0) {
        serializedClassifier = args[0];
      }

      AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
      if (args.length > 1) {
        String fileContents = IOUtils.slurpFile(args[1]);
        List<List<CoreLabel>> out = classifier.classify(fileContents);
        for (List<CoreLabel> sentence : out) {
          for (CoreLabel word : sentence) {
            System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
          }
          System.out.println();
        }
        out = classifier.classifyFile(args[1]);
        for (List<CoreLabel> sentence : out) {
          for (CoreLabel word : sentence) {
            System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
          }
          System.out.println();
        }

      }
      else 
      {
    	File textFile = new File("/home/yashaswi/Desktop/honors/article");
  		BufferedReader in = new BufferedReader(new FileReader(textFile));
  		String currentLine;
  		String [] lines = new String [10000];
  		int num=0;
  		lines[0]="";	
  		while ((currentLine = in.readLine()) != null)
  		{
  		int i;	
  		String [] k=currentLine.split("[;\\.]");
  		int[] flag = new int[k.length];
  		for(i=0;i<k.length;i++)
  		{
  		String []k1=k[i].split(" ");
  		if(k1.length-1>0)
  		if(k1[k1.length-1].length()==1 || k1[k1.length-1].equals("Rs")||k1[k1.length-1].equals("Mr")||k1[k1.length-1].equals("Dr")||k1[k1.length-1].equals("Prof")||k1[k1.length-1].equals("Ltd"))
  			flag[i]=1;
  		}
  		for(i=0;i<k.length;i++)
  		{		
  		if(flag[i]==0) 
  		{
  			if(k[i].startsWith(" "))
  				lines[num]=lines[num]+(k[i].substring(1,k[i].length()));
  			else
  			    lines[num]=lines[num]+k[i];
  			num=num+1;
  			lines[num]="";
  		}
  		else
  		{
  		while(flag[i]==1)
  		{
  			if(k[i].startsWith(" "))
  				lines[num]=lines[num]+(k[i].substring(1,k[i].length())+".");
  			else
  				lines[num]=lines[num]+k[i]+".";
  			i++;
  		}
  		if(k[i].startsWith(" "))
  			lines[num]=lines[num]+(k[i].substring(1,k[i].length()));
  		else
  	    			lines[num]=lines[num]+k[i];
  		num=num+1;
  		lines[num]="";
  		}
  		}
  		}
  		for(int it=0;lines[it]!=null ;it++)
  		{	
        String s1 = lines[it];//"Good afternoon Rajat Raina, how is climate?";
    //    String ans= classifier.classifyWithInlineXML(s1);
        if(s1.equals("")) continue;
      //  System.out.println("sentence: " +s1);
      //  System.out.println("ans="+ans);
      /*  for(int i=0;i<ans.length();i++)
        {
        	if(ans.charAt(i)=='<')
        	{
        		while(ans.charAt(i)!='>')
        		{
        			i++;
        		}
        		i++;
        		while(ans.charAt(i)!='<')
        		{
        			System.out.print(ans.charAt(i));
        			i++;
        		}
        		System.out.println();
        		i++;
        		while(ans.charAt(i)!='>')
        		{
        			i++;
        		}
        		i++;
        	}
        }*/
      //  tagme_APIRequest req = new tagme_APIRequest(s1);
     //   System.out.println(req.ans());
  		}
      
      }
    }

}


