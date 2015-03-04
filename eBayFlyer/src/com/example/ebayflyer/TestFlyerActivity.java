package com.example.ebayflyer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class TestFlyerActivity extends Activity {

	String outputName = null;
	String currentTag = null;
	TextView errorText = null;
	FileInputStream fis = null;
	InputStreamReader isr = null;
	char[] inputBuffer = null;
	String data = null;
	XmlPullParserFactory factory = null;
	String[][] ausgabe = null;
	XmlPullParser xpp = null;
	int eventType = 0;
	int zaehler0 = 0;
	int zaehler1 = 0;
	int zaehler2 = 0;
	int resultAnzahl = 0;
	public String errorString = null;
	PopupWindow popupWindow;
	WindowManager.LayoutParams lp = null;
	final String editTextShopID = null;
	boolean delete = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flyerview);

		Intent intent = getIntent();
		final String editTextShopID = intent.getStringExtra("editTextShopID");
		final String spinnerShopLand = intent.getStringExtra("spinnerShopLand");
		final String editTextShopSchluesselwort = intent
				.getStringExtra("editTextShopSchluesselwort");
		final String name = intent.getStringExtra("name");
		outputName = "output_" + editTextShopID + ".xml";

		ausgabe = new String[100][15];
		Button buttonFlyerAnzeigen = (Button) findViewById(R.id.button1);
		buttonFlyerAnzeigen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							fis = getApplicationContext().openFileInput(
									outputName);
							isr = new InputStreamReader(fis);
							inputBuffer = new char[fis.available()];
							isr.read(inputBuffer);
							data = new String(inputBuffer);
							isr.close();
							fis.close();

							DocumentBuilderFactory dbFactory = DocumentBuilderFactory
									.newInstance();
							dbFactory.setIgnoringElementContentWhitespace(true);
							DocumentBuilder dBuilder = dbFactory
									.newDocumentBuilder();
							Document doc = dBuilder.parse(new InputSource(
									new StringReader(data)));

							NodeList nList = doc
									.getElementsByTagName("content");

							Log.v("lang", nList.getLength() + "");
							for (int i = 0; i < nList.getLength(); i++) {

								Node nNode = nList.item(i);

								if (nNode.getNodeType() == Node.ELEMENT_NODE) {
									Log.v("current", "\nCurrent Element :"
											+ nNode.getNodeName());

									Element eElement = (Element) nNode;

									// 0-x,1-y,2-width,3-height,4-headline,5-url,6-description,7-price
									if (eElement.getElementsByTagName("x") != null
											&& eElement.getElementsByTagName(
													"x").getLength() != 0)
										ausgabe[i][0] = eElement
												.getElementsByTagName("x")
												.item(0).getTextContent();
									if (eElement.getElementsByTagName("y") != null
											&& eElement.getElementsByTagName(
													"y").getLength() != 0)
										ausgabe[i][1] = eElement
												.getElementsByTagName("y")
												.item(0).getTextContent();
									if (eElement.getElementsByTagName("width") != null
											&& eElement.getElementsByTagName(
													"width").getLength() != 0)
										ausgabe[i][2] = eElement
												.getElementsByTagName("width")
												.item(0).getTextContent();
									if (eElement.getElementsByTagName("height") != null
											&& eElement.getElementsByTagName(
													"height").getLength() != 0)
										ausgabe[i][3] = eElement
												.getElementsByTagName("height")
												.item(0).getTextContent();
									if (eElement
											.getElementsByTagName("headline") != null
											&& eElement.getElementsByTagName(
													"headline").getLength() != 0)
										ausgabe[i][4] = eElement
												.getElementsByTagName(
														"headline").item(0)
												.getTextContent();
									if (eElement.getElementsByTagName("url") != null
											&& eElement.getElementsByTagName(
													"url").getLength() != 0)
										ausgabe[i][5] = eElement
												.getElementsByTagName("url")
												.item(0).getTextContent();
									if (!eElement.getElementsByTagName("span")
											.item(0).getTextContent().isEmpty())
										ausgabe[i][6] = eElement
												.getElementsByTagName("span")
												.item(0).getTextContent();
									if (!eElement.getElementsByTagName("span")
											.item(1).getTextContent().isEmpty())
										ausgabe[i][7] = eElement
												.getElementsByTagName("span")
												.item(1).getTextContent();
								}

							}
							for (int i = 0; i < nList.getLength(); i++) {
								StringBuilder headlineStringBuilder = new StringBuilder(
										ausgabe[i][4]);
								headlineStringBuilder.delete(0, 3);
								ausgabe[i][4] = headlineStringBuilder
										.toString();//
								Log.v("x", i + "x : " + ausgabe[i][0]);
								Log.v("y", i + "y : " + ausgabe[i][1]);
								Log.v("width : ", i + "width : "
										+ ausgabe[i][2]);
								Log.v("height", i + "height : " + ausgabe[i][3]);
								Log.v("headline", i + ausgabe[i][4]);
								Log.v("link : ", i + "url : " + ausgabe[i][5]);
								Log.v("desription : ", i + "desription: "
										+ ausgabe[i][6]);
								Log.v("price : ", i + "price: " + ausgabe[i][7]);
							}

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();

						} catch (SAXException e) {
							e.printStackTrace();
						} catch (ParserConfigurationException e) {
							e.printStackTrace();
						}

					}
				});
				t.start();
			}
		});

		// Button um auf Flyer-Erstellung weiterzuleiten
		Button buttonFlyerErstellen = (Button) findViewById(R.id.button2);
		buttonFlyerErstellen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent mainIntent = new Intent(view.getContext(),
						MainActivity.class); 
				mainIntent.putExtra("editTextShopID", editTextShopID);
				mainIntent.putExtra("spinnerShopLand", spinnerShopLand);
				mainIntent.putExtra("editTextShopSchluesselwort",
						editTextShopSchluesselwort);

				startActivity(mainIntent);

			}
		});
	}
}
