package com.example.ebayflyer;
import java.io.File;
import java.io.FileInputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.ebayflyer.ReadJson;

import android.os.Environment;
import android.util.Log;

public class ReadJson {
	
	
/*
 * @Author Christian
 * Liest einen String als Json aus (noch nicht komplett)
 */
	public void readJson(String jsonString) {
		
		  Log.d("READING ", "JSON DATA");
		
	
	try {

	                 JSONObject jsonObj = new JSONObject(jsonString);

	                // Lies JSON Array 
	                JSONArray data  = jsonObj.getJSONArray("alsDocumentPages");

	                // Durchlaufe Alle Knoten des Arrays 
	                for (int i = 0; i < data.length(); i++) {
	                    JSONObject c = data.getJSONObject(i);

	                    String id = c.getString("id");
	                    Log.d("id", id);
	                    String cssClass = c.getString("cssClass");
	                    Log.d("cssClass", cssClass);
	                    String pageNumber = c.getString("pageNumber");
	                    Log.d("pageNumber", pageNumber);
	                    String width = c.getString("width");
	                    Log.d("width", width);
	                    String height = c.getString("height");
	                    Log.d("height", height);
	                    String css = c.getString("css");
	                    Log.d("css", css);
	                    String layout = c.getString("layout"); 
	                    Log.d("layout", layout); 
	                    
////	                    JSONObject layoutObj = new JSONObject(c.getString("layout"));
//	                    Log.d("LAYOUT", " ");
////	                    JSONObject position = new JSONObject(c.getString("position"));
//	                    Log.d("POSITION", " .");
//	                    String x = c.getString("x");
//	                    Log.d("x", x);
//	                    String y = c.getString("y");
//	                    Log.d("y", y);
//	                    String widthP = c.getString("width");
//	                    Log.d("widthP", widthP);
//	                    String heightP = c.getString("height");
//	                    Log.d("heightP", heightP);
//	                    
// //	                    JSONArray dataLayout  = layoutObj.getJSONArray("layout");
//	                    
//	                   
//	                for(int j =i; j< data.length(); j++) {
//	                	String layout = c.getString("layout");
//	                	Log.d("layout", layout); 
//	                }
//	                    String layout = c.getString("layout");
//	                    Log.d("layout", layout);
	                    
//	                    String title = c.getString("title");
//	                    String duration = c.getString("duration");
	                    //use >  int id = c.getInt("duration"); if you want get an in

	                    // tmp hashmap for single node
	                    HashMap<String, String> parsedData = new HashMap<String, String>();

	                    // adding each child node to HashMap key => value
	                    parsedData.put("page", id);
	                   
//	                    parsedData.put("title", title);
//	                    parsedData.put("duration", duration);

	                  }
	                JSONArray data2  = jsonObj.getJSONArray("alsDocumentPages");
	                
	                for (int j = 0; j<data2.length(); j++) {
	                JSONObject d = data.getJSONObject(j);
	                
	                if (d.getString("layout")!=null) {
	                	data2.getJSONObject(j).getJSONArray("position").getString(1);
	                	Log.d("POS", data2.getJSONObject(j).getJSONArray("position").getString(1));
      	
	                }
	                	               
	                }

	           } catch (Exception e) {
	           e.printStackTrace();
	          }
	}

}
