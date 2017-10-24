package com.paypal.risk.riskonboardingmsgd.services;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.net.*;

import org.json.JSONArray;
import org.json.JSONObject;
 
class ResponseMovie {

	int totalNumOfPages = 0;
	int pageNum = 0;
	int perPageCount = 0;
	int totalPages = 0;
	static String data[];
	
	public static ResponseMovie parseResponse(JSONObject responseObject, String substr) {
		int i =0;
		
		// Parse Data
		int pageNum = responseObject.getInt("page");
		int perPageCount = responseObject.getInt("per_page");
		int totalNumOfPages = responseObject.getInt("total");
		int totalPages = responseObject.getInt("total_pages");
		
		JSONArray resultsArray = responseObject.getJSONArray("data");
		String parsedData[] = new String[resultsArray.length()];
		data = new String[resultsArray.length()];
		
		for (i=0; i<resultsArray.length(); i++){
			JSONObject dataElement = resultsArray.getJSONObject(i);
			
			//Parsing all, no need to parse all though
			String poster = dataElement.getString("Poster");
			String title = dataElement.getString("Title");
			String type = dataElement.getString("Type");
			int year = dataElement.getInt("Year");
			String imdbID = dataElement.getString("imdbID");
			
			parsedData[i] = title;

		}
		
		ResponseMovie resMov = new ResponseMovie();
		resMov.pageNum = pageNum;
		resMov.perPageCount = perPageCount;
		resMov.totalNumOfPages = totalNumOfPages;
		resMov.totalPages = totalPages;
		
		i=0;
		for(String movieTitle : parsedData){
			if(movieTitle.toLowerCase().contains(substr.toLowerCase())){
				ResponseMovie.data[i] = movieTitle;
				i++;
			}
		}
		
		return resMov;
	}
	
}

public class Solution {
    /*
     * Complete the function below.
     */

    static String[] getMovieTitles(String substr) {
    	String[] solStringArr = null;
    	System.out.println("REACHED START");
        
        String urlToRead = "https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr;
        try{
    		URL obj = new URL(urlToRead);
    		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    		// optional default is GET
    		con.setRequestMethod("GET");

    		BufferedReader in = new BufferedReader(
    		        new InputStreamReader(con.getInputStream()));
    		String inputLine;
    		StringBuffer response = new StringBuffer();
    		
    		while ((inputLine = in.readLine()) != null) {
    			System.out.println("________" + inputLine);
    			JSONObject responseObject = new JSONObject(inputLine);
    			
    			ResponseMovie.parseResponse(responseObject, substr);
    			solStringArr = ResponseMovie.data;
    			
    			response.append(inputLine);
    		}
    		in.close();
    		
        }catch(Exception e){
            System.out.println("EXCEPTION " + e);
        }
        System.out.println("REACHED END");
        
        return solStringArr;
    }

	public static void main(String[] args) {

		String _substr = "spiderman";
		String arr[] = getMovieTitles(_substr);
		
		for(int res_i=0; res_i < arr.length; res_i++) {
            System.out.println(String.valueOf(arr[res_i]));
        }
		
	}

}
