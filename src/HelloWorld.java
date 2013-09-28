import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class HelloWorld {
	public static void main(String[] args) {
        System.out.println("Hello World!"); // Display the string.
        
        try {
        	String name = "Aaron Tietz";
        	String encodedname = URLEncoder.encode(name,"UTF-8");
        	String url = "http://www.facebook.com/search.php?q=" + encodedname + "&amp;_fb_noscript=1";
        	
			String result = executeGet(url);
			PrintWriter writer = new PrintWriter("fbscrape.txt", "UTF-8");
			writer.println(result);
			writer.close();

		} catch (UnsupportedEncodingException e) {
			System.out.println("could not encode parameters");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("could not open file");
			e.printStackTrace();
		}
                
        
    }
	public static String executeGet(String targetURL){
	    URL url;
	    HttpURLConnection connection = null;  
	    try {
	      //Create connection
	      url = new URL(targetURL);
	      connection = (HttpURLConnection)url.openConnection();
	      connection.setRequestMethod("GET");
	      connection.setRequestProperty("Content-Type", 
	           "application/x-www-form-urlencoded");
				
	      connection.setRequestProperty("Content-Language", "en-US");  
				
	      connection.setUseCaches (false);
	      connection.setDoInput(true);
	      connection.setDoOutput(true);

	      //Send request
	      DataOutputStream wr = new DataOutputStream (
	                  connection.getOutputStream ());
	      wr.flush ();
	      wr.close ();

	      //Get Response	
	      InputStream is = connection.getInputStream();
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	      String line;
	      StringBuffer response = new StringBuffer(); 
	      while((line = rd.readLine()) != null) {
	        response.append(line);
	        response.append('\r');
	      }
	      rd.close();
	      return response.toString();

	    } catch (Exception e) {

	      e.printStackTrace();
	      return null;

	    } finally {

	      if(connection != null) {
	        connection.disconnect(); 
	      }
	    }
	  }
	
	public void processhtml(String html){
		System.out.println("Aaron do your work here");
	}
}
