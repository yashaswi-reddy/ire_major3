package Features;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;


public class named_event {

	/**
	 * @param args
	 */

	String url_string="http://10.2.4.93:8086/process";
	
	
	public static void main(String args[]) throws Exception
	{
		named_event cs =new named_event();	
		String tweet_text="Rememeber folks, Geoge Brandis is the sharpest legal mind in the Coalition, just like Sophie Mirabella is its Science spokesperson|||"; 
		String value=cs.parse(tweet_text);
		String named_entities = "";
		String events="";
		String nouns="";
		System.out.println(value);
	/*	StringTokenizer st = new StringTokenizer(value," ");		
		while (st.hasMoreTokens()) 
		{
			String k=st.nextToken();
			System.out.println(k);
			String tokens[] = k.split("\\/");
			System.out.println(tokens[0]);
			System.out.println(tokens[1]);
			System.out.println(tokens[2]);
			System.out.println(tokens[3]);
			if( (tokens[1].startsWith("B-") || tokens[2].startsWith("NNP")))
			{
				named_entities+="\t" + tokens[0];
			}
			else if(tokens[1].startsWith("I-") || tokens[2].startsWith("NNP")){
				named_entities += " " + tokens[0];
			}
			
			if(tokens[3].startsWith("B-E"))
			{
				events+="\t" + tokens[0];

			}
			else if(tokens[3].startsWith("I-E")){
				events += " " + tokens[0];
			}
			if(tokens[2].endsWith("NN"))
			{
				nouns+="\t" + tokens[0];
				
			}
			else if(tokens[2].endsWith("NN") ){
				nouns += " " + tokens[0];
			}
			
		
		}
		System.out.println("omsairam");
		System.out.println("nouns "+nouns);
		System.out.println("ne "+named_entities);
		System.out.println("e "+events);*/
	}

	public String parse(String Tweet) throws IOException
        {
		String parse_string="";
		try {
			URL url = new URL(url_string);
			HttpURLConnection uc = (HttpURLConnection) url.openConnection();
			uc.setRequestMethod("POST");
			uc.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(uc.getOutputStream());
			wr.writeBytes("tweet="+Tweet);
			wr.flush();
			//uc.setRequestProperty( "Content-type", "text/xml" );
			//uc.setRequestProperty( "Accept", "text/xml" );
			int rspCode = uc.getResponseCode();
			if (rspCode == 200) {
				InputStream is = uc.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				parse_string = br.readLine();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("omsai"+parse_string);
		return parse_string;

	}
}



