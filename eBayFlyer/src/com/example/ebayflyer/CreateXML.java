package com.example.ebayflyer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import android.os.Environment;


//@Author: Anton
public class CreateXML {
	
	String location = Environment.getExternalStorageDirectory().toString()+"/userdaten.xml";
	String newLine = "\r\n";
	
	//Items
	String item1 = "1";
	String item2 = "4";
	String item3 = "7";
	String item4 = "9";
	String item5 = "12";
	String item6 = "13";
	String item7 = "15";
	String item8 = "17";
	String item9 = "19";
	String item10 = "20";
	String item11 = "22";
	String item12 = "24";
	String item13 = "25";
	String item14 = "28";
	String item15 = "29";
	String item16 = "30";
	String item17 = "33";
	String item18 = "34";
	String item19 = "35";
	String item20 = "37";
	
	//@Author: Anton
	//Damit auch wenn nichts uebergeben wird, Itemempfehlungen gemacht werden koennen
	public CreateXML() {
	}
	
	//@Author: Anton
	//Uebergeben der ersten 20 Items aus der XML, die von Ebay gezogen wurde
	public CreateXML(String[][] list) {
		defineItems(list);
		System.out.println("Items in die XML uebertragen");
	}
	
	
	
	//@Author: Anton
	//Deklarieren der Items
	public void defineItems(String[][] list) {
		item1 = list[0][0];
		item2 = list[2][0];
		item3 = list[4][0];
		item4 = list[6][0];
		item5 = list[8][0];
		item6 = list[10][0];
		item7 = list[12][0];
		item8 = list[14][0];
		item9 = list[16][0];
		item10 = list[18][0];
		item11 = list[20][0];
		item12 = list[22][0];
		item13 = list[24][0];
		item14 = list[26][0];
		item15 = list[28][0];
		item16 = list[30][0];
		item17 = list[32][0];
		item18 = list[34][0];
		item19 = list[36][0];
		item20 = list[38][0];
		
		//Ueberpruefen der Werte
//		System.out.println("1: " + item1);
//		System.out.println("2: " + item2);
//		System.out.println("3: " + item3);
//		System.out.println("4: " + item4);
//		System.out.println("5: " + item5);
//		System.out.println("6: " + item6);
//		System.out.println("7: " + item7);
//		System.out.println("8: " + item8);
//		System.out.println("9: " + item9);
//		System.out.println("10: " + item10);
//		System.out.println("11: " + item11);
//		System.out.println("12: " + item12);
//		System.out.println("13: " + item13);
//		System.out.println("14: " + item14);
//		System.out.println("15: " + item15);
//		System.out.println("16: " + item16);
//		System.out.println("17: " + item17);
//		System.out.println("18: " + item18);
//		System.out.println("19: " + item19);
//		System.out.println("20: " + item20);
//		

		
	}
	
	//@Author: Anton
	//HauptMethode
	//Es wird die XML Datei (aus der Klasse heraus) erstellt
	//Vereinfachung zum Testen auf verschiedenen Geraeten
	public void create() {
		
		File outputFile = new File(location);

		try {
			outputFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		PrintWriter output;
		BufferedWriter buffer;
		FileWriter fileWriter;

		try {
		    fileWriter = new FileWriter(outputFile);
		    buffer = new BufferedWriter(fileWriter);
		    output = new PrintWriter(buffer);
		    try {
		        writeFile(output);
		    } finally {
		        output.close();
		        buffer.close();
		        fileWriter.close();
		    }
		} catch (IOException e) {
		    System.out.println("Fehler");
		}
	}
	
	//@Author: Anton
	public void writeFile(PrintWriter output) {
		output.print("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + newLine);
		output.print("<userdaten>" + newLine);
		output.print(" <user>" + newLine);
		output.print("  <userId>1</userId>" + newLine);
		output.print("  <age>33</age>" + newLine);
		output.print("  <postalCode>50667</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item3 + "," + item6 + "," + item7 + "," + item8 + "," + item12 + "," + item13 + "," + item14 + "," + item15 + "," + item17 + "," + item18 + "," + item19 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>2</userId>" + newLine);
		output.print("  <age>34</age>" + newLine);
		output.print("  <postalCode>50667</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item4 + "," + item5 + "," + item6 + "," + item7 + "," + item8 + "," + item9 + "," + item15 + "," + item16 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>3</userId>" + newLine);
		output.print("  <age>29</age>" + newLine);
		output.print("  <postalCode>51406</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item7 + "," + item8 + "," + item10 + "," + item11 + "," + item13 + "," + item14 + "," + item15 + "," + item17 + "," + item18 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>4</userId>" + newLine);
		output.print("  <age>16</age>" + newLine);
		output.print("  <postalCode>50996</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item4 + "," + item5 + "," + item6 + "," + item8 + "," + item9 + "," + item11 + "," + item13 + "," + item14 + "," + item17 + "," + item18 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>5</userId>" + newLine);
		output.print("  <age>19</age>" + newLine);
		output.print("  <postalCode>85400</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item5 + "," + item6 + "," + item7 + "," + item9 + "," + item11 + "," + item13 + "," + item14 + "," + item15 + "," + item16 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>6</userId>" + newLine);
		output.print("  <age>60</age>" + newLine);
		output.print("  <postalCode>85300</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item5 + "," + item6 + "," + item7 + "," + item11 + "," + item12 + "," + item13 + "," + item15 + "," + item16 + "," + item18 + "," + item19 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>7</userId>" + newLine);
		output.print("  <age>55</age>" + newLine);
		output.print("  <postalCode>85400</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item7 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>8</userId>" + newLine);
		output.print("  <age>45</age>" + newLine);
		output.print("  <postalCode>10500</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item5 + "," + item8 + "," + item9 + "," + item12 + "," + item13 + "," + item14 + "," + item17 + "," + item18 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>9</userId>" + newLine);
		output.print("  <age>32</age>" + newLine);
		output.print("  <postalCode>10500</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item3 + "," + item4 + "," + item5 + "," + item8 + "," + item9 + "," + item10 + "," + item11 + "," + item14 + "," + item15 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>10</userId>" + newLine);
		output.print("  <age>35</age>" + newLine);
		output.print("  <postalCode>10200</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>11</userId>" + newLine);
		output.print("  <age>49</age>" + newLine);
		output.print("  <postalCode>10100</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item4 + "," + item5 + "," + item6 + "," + item7 + "," + item11 + "," + item12 + "," + item16 + "," + item17 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>12</userId>" + newLine);
		output.print("  <age>70</age>" + newLine);
		output.print("  <postalCode>04500</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item5 + "," + item6 + "," + item9 + "," + item10 + "," + item11 + "," + item13 + "," + item14 + "," + item15 + "," + item18 + "," + item19 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>13</userId>" + newLine);
		output.print("  <age>65</age>" + newLine);
		output.print("  <postalCode>04300</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item5 + "," + item6 + "," + item12 + "," + item13 + "," + item14 + "," + item15 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>14</userId>" + newLine);
		output.print("  <age>43</age>" + newLine);
		output.print("  <postalCode>50667</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item18 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>15</userId>" + newLine);
		output.print("  <age>33</age>" + newLine);
		output.print("  <postalCode>04300</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item5 + "," + item6 + "," + item7 + "," + item8 + "," + item11 + "," + item12 + "," + item13 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>16</userId>" + newLine);
		output.print("  <age>18</age>" + newLine);
		output.print("  <postalCode>85800</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item7 + "," + item8 + "," + item9 + "," + item10 + "," + item11 + "," + item14 + "," + item15 + "," + item17 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>17</userId>" + newLine);
		output.print("  <age>22</age>" + newLine);
		output.print("  <postalCode>85800</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item7 + "," + item8 + "," + item9 + "," + item10 + "," + item11 + "," + item17 + "," + item18 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>18</userId>" + newLine);
		output.print("  <age>24</age>" + newLine);
		output.print("  <postalCode>10500</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>19</userId>" + newLine);
		output.print("  <age>39</age>" + newLine);
		output.print("  <postalCode>51400</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item17 + "," + item18 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>20</userId>" + newLine);
		output.print("  <age>36</age>" + newLine);
		output.print("  <postalCode>50100</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>21</userId>" + newLine);
		output.print("  <age>26</age>" + newLine);
		output.print("  <postalCode>85700</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item6 + "," + item7 + "," + item9 + "," + item10 + "," + item12 + "," + item13 + "," + item18 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>22</userId>" + newLine);
		output.print("  <age>17</age>" + newLine);
		output.print("  <postalCode>04600</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item8 + "," + item9 + "," + item15 + "," + item16 + "," + item17 + "," + item19 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>23</userId>" + newLine);
		output.print("  <age>44</age>" + newLine);
		output.print("  <postalCode>04800</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>24</userId>" + newLine);
		output.print("  <age>46</age>" + newLine);
		output.print("  <postalCode>10900</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item5 + "," + item6 + "," + item7 + "," + item8 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>25</userId>" + newLine);
		output.print("  <age>41</age>" + newLine);
		output.print("  <postalCode>85700</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item6 + "," + item7 + "," + item10 + "," + item11 + "," + item13 + "," + item16 + "," + item17 + "," + item18 +  "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>26</userId>" + newLine);
		output.print("  <age>57</age>" + newLine);
		output.print("  <postalCode>67400</postalCode>" + newLine);
		output.print("  <genderBinary>0</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item7 + "," + item8 + "," + item9 + "," + item10 + "," + item11 + "," + item12 + "," + item13 + "," + item14 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>27</userId>" + newLine);
		output.print("  <age>51</age>" + newLine);
		output.print("  <postalCode>67300</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item7 + "," + item8 + "," + item9 + "," + item10 + "," + item12 + "," + item13 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>28</userId>" + newLine);
		output.print("  <age>24</age>" + newLine);
		output.print("  <postalCode>67900</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item12 + "," + item13 + "," + item14 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>29</userId>" + newLine);
		output.print("  <age>29</age>" + newLine);
		output.print("  <postalCode>85400</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item11 + "," + item12 + "," + item13 + "," + item14 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>30</userId>" + newLine);
		output.print("  <age>38</age>" + newLine);
		output.print("  <postalCode>50700</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item14 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>31</userId>" + newLine);
		output.print("  <age>18</age>" + newLine);
		output.print("  <postalCode>50600</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item7 + "," + item8 + "," + item9 + "," + item19 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>32</userId>" + newLine);
		output.print("  <age>49</age>" + newLine);
		output.print("  <postalCode>85600</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item7 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>33</userId>" + newLine);
		output.print("  <age>19</age>" + newLine);
		output.print("  <postalCode>10500</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item7 + "," + item8 + "," + item9 + "," + item10 + "," + item11 + "," + item12 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>34</userId>" + newLine);
		output.print("  <age>40</age>" + newLine);
		output.print("  <postalCode>85700</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item14 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);

		output.print(" <user>" + newLine);
		output.print("  <userId>35</userId>" + newLine);
		output.print("  <age>30</age>" + newLine);
		output.print("  <postalCode>85200</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>36</userId>" + newLine);
		output.print("  <age>29</age>" + newLine);
		output.print("  <postalCode>04500</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item14 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>37</userId>" + newLine);
		output.print("  <age>35</age>" + newLine);
		output.print("  <postalCode>04700</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>38</userId>" + newLine);
		output.print("  <age>45</age>" + newLine);
		output.print("  <postalCode>51700</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>39</userId>" + newLine);
		output.print("  <age>55</age>" + newLine);
		output.print("  <postalCode>51400</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item12 + "," + item13 + "," + item14 + "," + item15 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>40</userId>" + newLine);
		output.print("  <age>48</age>" + newLine);
		output.print("  <postalCode>10500</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>41</userId>" + newLine);
		output.print("  <age>38</age>" + newLine);
		output.print("  <postalCode>10900</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item7 + "," + item13 + "," + item14 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>42</userId>" + newLine);
		output.print("  <age>33</age>" + newLine);
		output.print("  <postalCode>85300</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>43</userId>" + newLine);
		output.print("  <age>36</age>" + newLine);
		output.print("  <postalCode>67400</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item10 + "," + item11 + "," + item14 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>44</userId>" + newLine);
		output.print("  <age>43</age>" + newLine);
		output.print("  <postalCode>10999</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item14 + "," + item15 + "," + item16 + "," + item17 + "," + item18 + "," + item19 + "," + item20 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		
		output.print(" <user>" + newLine);
		output.print("  <userId>45</userId>" + newLine);
		output.print("  <age>34</age>" + newLine);
		output.print("  <postalCode>50500</postalCode>" + newLine);
		output.print("  <genderBinary>1</genderBinary>" + newLine);
		output.print("  <items>" + item1 + "," + item2 + "," + item3 + "," + item4 + "," + item5 + "," + item6 + "," + item7 + "," + item8 + "," + item9 + "</items>" + newLine);
		output.print(" </user>" + newLine);
		output.print("</userdaten>");
	}

}
