package com.example.ebayflyer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/*
 * Klasse fuer die Anzeige: 
 * Naechster Schritt: Versuch, ein Bild einzupacken 
 */

//1-title,2-categoryId,3-categoryName,4-currentPrice,5-galleryURL,6-Beschreibung
public class DisplayActivity extends Activity {
	

	LinearLayout lLayout; 
	TextView tView; 
	TextView subT; 
	TextView descT;
	TextView priceT; 
	ArrayList<View> aView = new ArrayList<View>();
	
	RelativeLayout rlayout; 
	ListView lView; 
	ListView lView2; 
	ImageView iView; 
	SimpleAdapter sa; 
	
	String text; 
	String description; 
	String price; 
	
	String[] prodContent = new String[4]; 
    int[] prodData = new int[4]; 
    
	int xPos; 
	int yPos; 
	int width; 
	int height; 
	
	
	
	
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		

		
//		rlayout.setBackground(background);
		

		
//        rlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		
		rlayout = new RelativeLayout(this);
		rlayout.setLayoutParams(new LayoutParams(1024,600));
		
		lView = new ListView(this); 
			
	

//        
		
        
       
//        lView1.setX(0);
//        lView1.setY(0);
//    
//        lView1.setLayoutParams(new RelativeLayout.LayoutParams(236, LayoutParams.WRAP_CONTENT));
//        Log.d("listview", "listview");
//        
//        lView2 = new ListView(this); 
//        lView2.setX(300);
//        lView2.setY(0);
//        lView2.setLayoutParams(new RelativeLayout.LayoutParams(142, LayoutParams.WRAP_CONTENT));
//        
//        ArrayAdapter<String> ad2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aView);
//        ad2.add("Lederschuhe ");
//        ad2.add("Sehr Stylisch!!");
//        ad2.add("250€");
//        ad2.add("");
//        
//        
//        lView2.setAdapter(ad2);
//      
//        tView = new TextView(this);
//        tView.setText("Handtasche");
////        tView.setX(13);
////		tView.setY(20);
////		tView.setWidth(236);
////		tView.setHeight(76); 
//        tView.setLayoutParams(new LayoutParams(
//        		LayoutParams.MATCH_PARENT,
//        		LayoutParams.WRAP_CONTENT));
//        
//       
//        
  
//        
//        
//        
//      
//        
//       
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aView);
        
        
//        insertProdStrings("Tasche", "Coole Tasche", "293");
//
//		insertProdData(0, 0, 236, 76);
//
//		int i=0; 
//	
//			lView.setX(prodData[i]);i=i+1; 
//			
//			lView.setY(prodData[i]);i=i+1;
//	   
//		
//			lView.setLayoutParams(new RelativeLayout.LayoutParams(i, LayoutParams.WRAP_CONTENT));
//		
//        int j=0; 
		
	
//		ad.add(prodContent[j]);j=j+1;
//	
//		ad.add(prodContent[j]);j=j+1;
//	
//		ad.add(prodContent[j]);
//	
//		ad.add("");
	LinearLayout lv = new LinearLayout(this); 
	

	
          lView.setX(0);
          lView.setY(0);
  
          lView.setLayoutParams(new RelativeLayout.LayoutParams(236, LayoutParams.WRAP_CONTENT));
          
//          insertProdStrings("GB", "aus GOld", "9999");
//          
//          int j=0;
//  		ad.add(prodContent[j]);j=j+1;
//  	
//  		ad.add(prodContent[j]);j=j+1;
//  	
//  		ad.add(prodContent[j]);
//  	
//  		ad.add("");
          
  		prodToAdapter(ad, "GB", "GOLD", "44€");
        iView = new ImageView(this); 
        iView.setImageResource(R.drawable.tasche);
        iView.setX(236);
        iView.setY(0);
        
        
        iView.setLayoutParams(new LayoutParams(
        		230,
        		100));
        
  		prodToAdapter(ad, "tt", "sad", "65€");
  		
  		prodToAdapter(ad, "bli", "bla", "blub");
  		prodToAdapter(ad, "da", "da", "daaaah");
  		prodToAdapter(ad, "ddads", "ddos", "ddamasdas");
  		prodToAdapter(ad, "dsefs", "dsefs", "d345ah");
  		
//		  ad.add("Handtasche");
//          ad.add("DIESE TASCHE IS SEHR SCHICK UND AUS GOLD, GANZ GANZ TOOOLLLLLLLL!!!");
//          ad.add("99€"); 
//          ad.add("");
		 lView.setAdapter(ad);
		 Log.d("HERE", "HERE");
		 lView.setVisibility(1);
		 rlayout.addView(lView);
		 lView.setScrollContainer(false);
		 lView.setClickable(false);
		 rlayout.addView(iView);
		 Log.d("HERE", "HERE");
		
		 


		 setContentView(rlayout);
       
     
		
//		insertProduct(lView, prodContent, prodData, rlayout, ad); 
		
//		insertProdStrings("GB", "aus GOld", "9999");
//		insertProdData(512, 0, 142, 76);
//		insertProduct(lView1, prodContent, prodData, rlayout); 

      
//       

//        lView1.setAdapter(ad);
//      
//
//        
//        

//        
//        Log.d("listview", "listview");
//        
//       
//        
//        lView1.setVisibility(1);
//        rlayout.addView(lView1);
//        rlayout.addView(iView);
////        rlayout.addView(lView2);
       
//      
//       
//       
//        Log.d("listview", "listview");
        
//        Log.d("listview", "listview");
	}
	
	
	
	public void prodToAdapter(ArrayAdapter<String> ad, String text, String description, String price){
		insertProdStrings(text, description, price);
        
        int j=0;
		ad.add(prodContent[j]);j=j+1;
	
		ad.add(prodContent[j]);j=j+1;
	
		ad.add(prodContent[j]);
	
		ad.add(" ");
	}
	public void insertProduct(ListView lView, String[]prodContent, int[] prodData, RelativeLayout rlayout, 
			ArrayAdapter<String> ad) {
		
		
		int i=0; 
	
			lView.setX(prodData[i]);i=i+1; 
			
			lView.setY(prodData[i]);i=i+1;
	   
		
			lView.setLayoutParams(new RelativeLayout.LayoutParams(i, LayoutParams.WRAP_CONTENT));
		
        int j=0; 
		
	
		ad.add(prodContent[j]);j=j+1;
	
		ad.add(prodContent[j]);j=j+1;
	
		ad.add(prodContent[j]);
	
		ad.add("");
	
//		  ad.add("Handtasche");
//        ad.add("DIESE TASCHE IS SEHR SCHICK UND AUS GOLD, GANZ GANZ TOOOLLLLLLLL!!!");
//        ad.add("99€"); 
//        ad.add("");
		 lView.setAdapter(ad);
		 Log.d("HERE", "HERE");
		 lView.setVisibility(1);
		 rlayout.addView(lView);
		 Log.d("HERE", "HERE");
		 ad.clear();
		 setContentView(rlayout);
		 
			
		
	}
	
	public void insertProdStrings(String text, String description, String price) {
		
		prodContent[0] = text; 
		
		prodContent[1] = description; 
	
		prodContent[2] = price;

	}
	
	public void insertProdData(	int xPos, int yPos, int width, int height)  {
		
		prodData[0] = xPos;
		
		prodData[1] = yPos; 
	
		prodData[2] = width; 
	
		prodData[3] = height; 
	
	}
	
	private InputStream OpenHttpConnection(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;

		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();

		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Keine HttpConnection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn; 
			httpConn.setAllowUserInteraction(false); 
			httpConn.setInstanceFollowRedirects(true); 
			httpConn.setRequestMethod("GET"); 
			httpConn.connect(); 
			response = httpConn.getResponseCode(); 
			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream(); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}
	
	private Bitmap DownloadImage(String URL) {
		Bitmap bitmap = null;
		InputStream in = null;
		try {
			in = OpenHttpConnection(URL);
			bitmap = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return bitmap;
	}

	

}
