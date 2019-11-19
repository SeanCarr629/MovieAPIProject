/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.getmovieinfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author scarroll
 */
public class MovieAPIProject {
    
    
    public static String jsonText;
    
    
    public static JSONObject createJSONObject(String line, HttpURLConnection conn){
        
        JSONObject json = null;
        
        
        BufferedReader br = null;
        try {
            
            
            
         
            
            br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            ;
            try {
                
                
                
                
                
                StringBuffer response = new StringBuffer();
                
                
                
                
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                json = new JSONObject(response.toString());
                
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(MovieAPIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(MovieAPIProject.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MovieAPIProject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
        
        
        
        return json;
    }
   
 
  
    
    
    
    
    
    public static String popularMovies(String year){
        
        String results = "";
        String line = "";
     try {

		URL url = new URL("https://api.themoviedb.org/3/discover/movie?api_key="
                        + "1fc1286e451238f0135931901e9f7392&language=en-US&sort_by=popularity.desc&primary_release_year=" + year
                        + "");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Accept", "application/json");
		//conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

                    
                
            JSONObject JSONObject =  createJSONObject(line, conn);
     
		JSONObject.getJSONArray("results").length();
                
                for(int i = 0; i < JSONObject.getJSONArray("results").length(); i++)
                
                {
                    results +=  JSONObject.getJSONArray("results").getJSONObject(i).get("title") + "\n";
                
                }
                
                conn.disconnect();

	  } catch (MalformedURLException e) {
	  } catch (IOException e) {
	  }
     
             return results;
    }
	}
   

