//
//  JsonReader.java
//  java-by-java
//
//  Created by Munan Xu on 2015-11-16.
//  Copyright 2015 Munan Xu. All rights reserved.
//
//	Example code that reads a JSON array from a localhost running ngrok
//	Prints out the associated public address for the ngrok client
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class JsonReader {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }

  public static void main(String[] args) throws IOException, JSONException {
    JSONObject json = readJsonFromUrl("http://localhost:4040/api/tunnels");
    JSONObject ngrokTunnels = json.getJSONArray("tunnels").getJSONObject(1);
	//System.out.println(json.toString());
	
    System.out.println(ngrokTunnels.get("public_url"));
  }
}