package com.example.ebayflyer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import android.os.Environment;



//@Author: Anton
public class Einlesen {
//	String location = "C:\\Users\\Revird\\Desktop\\userdaten.xml";
	String location = Environment.getExternalStorageDirectory().toString()+"/userdaten.xml";
	LinkedList<LinkedList<String>> userList = new LinkedList<LinkedList<String>>();
	LinkedList<String> contentList = new LinkedList<String>();
	
	LinkedList<LinkedList<AttributeObject>> attributeList = new LinkedList<LinkedList<AttributeObject>>();
	LinkedList<AttributeObject> specificAttributeList = new LinkedList<AttributeObject>();
	
	LinkedList<String> attributes = new LinkedList<String>(); //Attributnamen (age, postalCode, etc)
	

	//@Author: Anton
	//Hauptmethode
	//Es wird die Datei gelesen, die Attribute in einer Liste getan, die wiederrum in einer Liste getan werden
	//Danach werden die Attributnamen in einer Liste getan, in der jeweiligen Reihenfolge (damit man die Positionen abgleichen kann
	//Danach werden die Attributwerde den UserIDs eindeutig zugeordnet
	public void einlesen() {
		userList.clear();
		contentList.clear();
		attributeList.clear();
		specificAttributeList.clear();
		attributes.clear();
		
		addUserContent();
		defineAttributes();
		System.out.println("Daten eingelesen");
		transformToAttribute();
		System.out.println("Daten transformiert");
		

	}
	
	//@Author: Anton
	//Liste mit den jeweiligen Attributen erstellen (zum Suchen der Position des Attributes)
	public void defineAttributes() {
		try {
			boolean check = true;
			BufferedReader in = new BufferedReader(new FileReader(location));
			String zeile = null;
			while ((zeile = in.readLine()) != null && check) {
				if (!zeile.equals(" <user>") && !zeile.equals(" </user>") && !zeile.equals("<userdaten>") && !zeile.equals("</userdaten>") && !zeile.equals("<?xml version=\"1.0\" encoding=\"utf-8\"?>"))
				{
					attributes.add(getAttributeName(zeile));
				}
				if (zeile.equals(" </user>"))
				{
					check = false;
				}
				
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	//@Author: Anton
	//Es wird eine liste erstellt/transformiert, die das jeweilige Attribut eines Users seiner userID zuordnet
	public void transformToAttribute() {
		for (int i = 0; i < userList.get(0).size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				specificAttributeList.add(new AttributeObject(userList.get(j).get(0), userList.get(j).get(i)));
			}
			attributeList.add(specificAttributeList);
			specificAttributeList = new LinkedList<AttributeObject>();
		}
	}
	
	//@Author: Anton
	//Erstellen einer Liste, die selbst eine Liste mit den jeweiligen Attributen des Users enthaelt
	public void addUserContent() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(location));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				if (!zeile.equals(" <user>") && !zeile.equals(" </user>") && !zeile.equals("<userdaten>") && !zeile.equals("</userdaten>") && !zeile.equals("<?xml version=\"1.0\" encoding=\"utf-8\"?>"))
				{
					contentList.add(getContent(zeile));
				}
				if (zeile.equals(" </user>"))
				{
					userList.add(contentList);
					contentList = new LinkedList<String>();
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	//@Author: Anton
	//Inhalt des Attributes erhalten
	public String getContent(String line) {
		boolean check = false;
		String content = "";
		for (int i = 0; i < line.length(); i++){
			if (line.charAt(i) == '>')
			{
				check = true;
			}
			if (line.charAt(i) == '<')
			{
				check = false;
			}
			if (check && line.charAt(i) != '<' && line.charAt(i) != '>')
			{
				content += line.charAt(i);
			}
		}
		return content;
	}
	
	//@Author: Anton
	//Erhalten des Attributnamens der jeweiligen Zeile
	public String getAttributeName(String line) {
		boolean check = false;
		String content = "";
		for (int i = 0; i < line.length(); i++){
			if (line.charAt(i) == '<')
			{
				check = true;
			}
			if (line.charAt(i) == '>')
			{
				check = false;
				break;
			}
			if (check && line.charAt(i) != '<' && line.charAt(i) != '>')
			{
				content += line.charAt(i);
			}
		}
		return content;
	}


}
