package com.example.ebayflyer;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * Klasse fuer die Anzeige: 
 * Naechster Schritt: Versuch, ein Bild einzupacken 
 */

//1-title,2-categoryId,3-categoryName,4-currentPrice,5-galleryURL,6-Beschreibung
public class TestActivity extends Activity {
	

	LinearLayout lLayout; 
	TextView tView; 
	TextView subT; 
	TextView descT;
	TextView priceT; 
	ArrayList<View> aView = new ArrayList<View>();
	
	RelativeLayout rlayout; 
	ListView lView1; 
	ListView lView2; 
	ImageView iView; 
	
	String text; 
	String description; 
	String price; 
	int xPos; 
	int yPos; 
	int width; 
	int height; 
	
	
	
	
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		
//        lLayout = new LinearLayout(this);
//        lLayout.setOrientation(LinearLayout.VERTICAL);
//        lLayout.setX(x);
//        lLayout.setY(y);
//        lLayout.setBackground(background);
//        lLayout.set
//        lLayout.seth
		
//		rlayout.setBackground(background);
		
        //-1(LayoutParams.MATCH_PARENT) is fill_parent or match_parent since API level 8
        //-2(LayoutParams.WRAP_CONTENT) is wrap_content
		
		
//        rlayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		Log.d(Integer.toString(LayoutParams.MATCH_PARENT), "OBJEKT ");
		
		rlayout = new RelativeLayout(this);
		rlayout.setLayoutParams(new LayoutParams(1024,600));
		
		Log.d("asd", "Error");
        
        lView1 = new ListView(this); 
        lView1.setX(0);
        lView1.setY(0);
    
        lView1.setLayoutParams(new RelativeLayout.LayoutParams(236, LayoutParams.WRAP_CONTENT));
        Log.d("listview", "listview");
        
        lView2 = new ListView(this); 
        lView2.setX(300);
        lView2.setY(0);
        lView2.setLayoutParams(new RelativeLayout.LayoutParams(142, LayoutParams.WRAP_CONTENT));
        
        ArrayAdapter<String> ad2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aView);
        ad2.add("Lederschuhe ");
        ad2.add("Sehr Stylisch!!");
        ad2.add("250€");
        ad2.add("");
        
        
        lView2.setAdapter(ad2);
      
        tView = new TextView(this);
        tView.setText("Handtasche");
//        tView.setX(13);
//		tView.setY(20);
//		tView.setWidth(236);
//		tView.setHeight(76); 
        tView.setLayoutParams(new LayoutParams(
LayoutParams.MATCH_PARENT,
LayoutParams.WRAP_CONTENT));
        
       
        
        iView = new ImageView(this); 
        iView.setImageResource(R.drawable.tasche);
        iView.setX(240);
        iView.setY(0);
        iView.setLayoutParams(new LayoutParams(
        		236,
        		LayoutParams.WRAP_CONTENT));
        
        
        
      
        
       
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, aView);
        ad.add("Handtasche");
        ad.add("DIESE TASCHE IS SEHR SCHICK UND AUS GOLD, GANZ GANZ TOOOLLLLLLLL!!!");
        ad.add("99€"); 
        ad.add("");
        
       
//        lView.addHeaderView(iView);
        lView1.setAdapter(ad);
      

        
        
//        lView.addChildrenForAccessibility(aView);
        
        Log.d("listview", "listview");
        
       
        
        lView1.setVisibility(1);
        rlayout.addView(lView1);
        rlayout.addView(iView);
//        rlayout.addView(lView2);
      
       
       
        Log.d("listview", "listview");
        setContentView(rlayout);
        Log.d("listview", "listview");
	}
	
	

}
