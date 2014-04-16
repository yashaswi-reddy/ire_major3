package Train;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class tagme_APIRequest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	/**
	 * @param args
	 * @throws IOException
	 */
	static String url = "http://tagme.di.unipi.it/tag?";
	static String charset = "UTF-8";
	static String key = "jhfd543210pl";
	//static String key = "verma2014";
	static String include_categories="true";
	public static String k="";
	
	
	public static String doPost(String text) throws IOException{
		System.setProperty("http.proxyHost", "proxy.iiit.ac.in");
    	System.setProperty("http.proxyPort", "8080");
		String type = "application/x-www-form-urlencoded";


    	String query = String.format("key=%s&text=%s&include_categories=%s", 
    	     URLEncoder.encode(key, charset), 
    	     URLEncoder.encode(text, charset),
    	     URLEncoder.encode(include_categories, charset));

    	url = url+query;
																																																																																																																						
    	URL u = new URL(url);
		url = "http://tagme.di.unipi.it/tag?" ;
    	HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		
		conn.setDoOutput(true);
		conn.setRequestMethod( "POST" );
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		conn.setRequestProperty( "Content-Type", type );
		conn.setRequestProperty( "Content-Length", String.valueOf(query.length()));
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.flush();
		wr.close();
//		int responseCode = conn.getResponseCode();
//		System.out.println("\nSending 'POST' request to URL : " + u);
//		System.out.println("Post parameters : " + query);
//		System.out.println("Response Code : " + responseCode);
// 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		//System.out.println(response.toString());
		return response.toString();
		
	}
}

