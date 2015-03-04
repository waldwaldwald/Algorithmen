package com.example.ebayflyer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import android.util.Log;

public class XML {
	
	public void xmlLesen() throws FileNotFoundException{
		String content=null;
		Scanner scannner = new Scanner(new File("res/xml/request.xml"));
		while(scannner.hasNextLine()){
		    content = scannner.nextLine();                     
		}
		scannner.close();
		
		System.out.print(content);
		Log.i("test",content);
		
	}

}
