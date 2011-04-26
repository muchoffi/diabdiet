package org.ai.diabdiet.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Utils {
	public static String MENU 		= "menudasar.txt";
	public static String KNOWLEDGE	= "knowledge.txt";
	public static String PLAN		= "plan.txt";
	public static String PATIENT	= "patient.txt";

	public static String URL_KNOWLEDGE	= "http://fbbed.cer33.com/AI/knowledge.txt";
	public static String URL_DIETMENU	= "http://fbbed.cer33.com/AI/menudasar.txt";
	
	public static InputStream OpenHttpConnection(String urlString) 
    throws IOException
    {
        InputStream in = null;
        int response = -1;
               
        URL url = new URL(urlString); 
        URLConnection conn = url.openConnection();
                 
        if (!(conn instanceof HttpURLConnection))                     
            throw new IOException("Not an HTTP connection");
        
        try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect(); 

            response = httpConn.getResponseCode();                 
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();                                 
            }                     
        }
        catch (Exception ex)
        {
            throw new IOException("Error connecting");            
        }
        return in;     
    }
}
