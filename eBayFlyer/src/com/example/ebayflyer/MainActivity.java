/**
 * Activity fuer Hauptseite der App. 
 */
package com.example.ebayflyer;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {

	boolean done = false;
	boolean error = false;
	int outputSize = 300000;
	int outputDoneSize = 0;
	private TextProgressBar datenImportProgressBar = null;
	String outputName = null;
	String currentTag = null;
	TextView errorText = null;
	FileInputStream fis = null;
	InputStreamReader isr = null;
	char[] inputBuffer = null;
	String data = null;
	XmlPullParserFactory factory = null;
	String[][] itemListe = null;
	XmlPullParser xpp = null;
	int eventType = 0;
	int zaehler0 = 0;
	public String errorString = null;
	PopupWindow popupWindow;
	WindowManager.LayoutParams lp = null;
	final String editTextShopID = null;

	// Methode um GUI(ShopID) zu aktualisieren und Popupfenster anzuzeigen
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			initPopuptWindow();
			errorTextAktualisieren();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lp = getWindow().getAttributes();

		final String appID = "UniColog-7e7e-4366-8181-f51b12f4c8dc";
		Intent intent = getIntent();
		final String editTextShopID = intent.getStringExtra("editTextShopID");
		final String spinnerShopLand = intent.getStringExtra("spinnerShopLand");
		final String editTextShopSchluesselwort = intent
				.getStringExtra("editTextShopSchluesselwort");
		outputName = "output_" + editTextShopID + ".xml";

		itemListe = new String[100][7];
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {

				HttpClient httpClient = null;
				HttpGet httpGet = null;
				HttpResponse httpResponse = null;
				HttpEntity httpEntity = null;
				String input = null;
				InputStream inputStream = null;
				FileOutputStream outputStream = null;
				String globalID = null;

				// GlobalID (ausgewaehlt von Dropdownliste in Loginseite) um
				// Antwort von eBay-Server effizienter zu machen
				if (spinnerShopLand.equals("Deutschland - DE"))
					globalID = "EBAY-DE";
				if (spinnerShopLand.equals("Gro\u00dfbritanien - UK"))
					globalID = "EBAY-GB";
				if (spinnerShopLand.equals("United States - US"))
					globalID = "EBAY-US";

				// Standard Client-Anfrage ohne Schluesselwort
				String eBayURLStoreName = "http://svcs.ebay.com/services/search/FindingService/v1?"
						+ "OPERATION-NAME=findItemsIneBayStores"
						+ "&SECURITY-APPNAME="
						+ appID
						+ "&GLOBAL-ID="
						+ globalID + "&storeName=" + editTextShopID;

				// Client-Anfrage mit Schluesselwort
				if (editTextShopSchluesselwort != null) {

					String schluesselwort = editTextShopSchluesselwort.trim()
							.replaceAll(" ", "%20");
					eBayURLStoreName = "http://svcs.ebay.com/services/search/FindingService/v1?"
							+ "OPERATION-NAME=findItemsIneBayStores"
							+ "&SECURITY-APPNAME="
							+ appID
							+ "&GLOBAL-ID="
							+ globalID
							+ "&storeName="
							+ editTextShopID
							+ "&keywords=" + schluesselwort;
				}

				// Client-Anfrage an eBay-Server verschicken
				httpClient = new DefaultHttpClient();
				httpGet = new HttpGet(eBayURLStoreName);

				try {

					// Antwort aus eBay-Server zu erhalten
					httpResponse = httpClient.execute(httpGet);

					// weil Ebay keine Contentlength zurueckschicken (content
					// length unkown), wird EntityUtils-Methode eingesetzt und
					// damit outputSize festzulegen und ProgressBar zu nutzen.
					httpEntity = httpResponse.getEntity();
					input = EntityUtils.toString(httpEntity);
					outputSize = input.length();
					inputStream = new ByteArrayInputStream(input
							.getBytes(StandardCharsets.UTF_8));
					outputStream = openFileOutput(outputName,
							Context.MODE_WORLD_READABLE
									+ Context.MODE_WORLD_WRITEABLE);

					// Methode um ProgressBar zu aktualisieren um Status vom
					// Datenimport zu zeigen
					datenImportProgressBar = (TextProgressBar) findViewById(R.id.progressBarItemsImport);
					datenImportProgressBar.setVisibility(View.VISIBLE);
					datenImportProgressBar.setMax(outputSize);

					// Schreiben der erhaltenen eBay-Daten auf SDKarte
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
						outputDoneSize += bytesRead;
						datenImportProgressBar.setText(outputDoneSize
								/ outputSize * 100 + " %");
						datenImportProgressBar.setProgress(outputDoneSize);
					}

					inputStream.close();
					outputStream.flush();
					outputStream.close();

					// Methode um eBay-Daten(XML) in strukturierter Array zu
					// umwandeln/interpretieren
					if (error == false) {
						for (int i = 0; i < itemListe.length; i++) {
							for (int j = 0; j < itemListe[i].length; j++) {
								itemListe[i][j] = " ";
							}
						}

						try {
							fis = getApplicationContext().openFileInput(
									outputName);
							isr = new InputStreamReader(fis);
							inputBuffer = new char[fis.available()];
							isr.read(inputBuffer);
							data = new String(inputBuffer);
							isr.close();
							fis.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}

						try {
							factory = XmlPullParserFactory.newInstance();
							factory.setNamespaceAware(true);
							xpp = factory.newPullParser();
							xpp.setInput(new StringReader(data));
							eventType = xpp.getEventType();
							while (eventType != XmlPullParser.END_DOCUMENT
									&& zaehler0 < 100) {
								if (eventType == XmlPullParser.START_TAG) {
									currentTag = xpp.getName();
								} else if (eventType == XmlPullParser.TEXT) {

									// Methode um Popupfenster zu zeigen falls
									// Error aus Ebay zurueckkommt (Eingabe in
									// Login fehlerhaft)
									if (currentTag.equalsIgnoreCase("message")) {
										error = true;
										errorString = xpp.getText().toString();
										Message message = new Message();
										mHandler.sendMessage(message);
									}
									// Methode um richitge eBay-Daten von
									// XML-Format in Array zu umwandeln
									else {
										error = false;
										if (currentTag
												.equalsIgnoreCase("itemId")) {
											itemListe[zaehler0][0] = xpp
													.getText().toString();
										}
										if (currentTag
												.equalsIgnoreCase("title")) {
											itemListe[zaehler0][1] = xpp
													.getText().toString();
										}

										if (currentTag
												.equalsIgnoreCase("categoryId")) {
											itemListe[zaehler0][2] = xpp
													.getText().toString();
										}
										if (currentTag
												.equalsIgnoreCase("categoryName")) {
											itemListe[zaehler0][3] = xpp
													.getText().toString();
										}
										if (currentTag
												.equalsIgnoreCase("currentPrice")) {
											itemListe[zaehler0][4] = xpp
													.getText().toString();
										}
										if (currentTag
												.equalsIgnoreCase("galleryURL")) {
											itemListe[zaehler0][5] = xpp
													.getText().toString();
											zaehler0++;
										}
										;
									}
								}
								eventType = xpp.next();
							}

							done = true;

						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();

		// In Loginseite ausgewaehltes Land in Hauptseite anzuzeigen
		if (!(spinnerShopLand.isEmpty())) {
			String spinnerShopLandinMain = spinnerShopLand;
			TextView textViewShopLandinMain = (TextView) findViewById(R.id.textViewShopLandinMain);
			textViewShopLandinMain.setText(textViewShopLandinMain.getText()
					+ " " + spinnerShopLandinMain);
		}

		// In Loginseite eingegebes ShopID in Hauptseite anzuzeigen
		if (!(editTextShopID.isEmpty())) {
			String editTextShopIDinMain = editTextShopID;
			TextView textViewShopIDinMain = (TextView) findViewById(R.id.textViewShopIDinMain);
			textViewShopIDinMain.setText(textViewShopIDinMain.getText() + " "
					+ editTextShopIDinMain);
		}

		// Button um auf Flyer-Erstellung weiterzuleiten
		Button buttonFlyerErstellen = (Button) findViewById(R.id.buttonErstellenFlyer);
		buttonFlyerErstellen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String[][] itemListeStrings = getListe();
				if (done) {
					Intent productIntent = new Intent(view.getContext(),
							ProductActivity.class);
					productIntent.putExtra("itemListe",
							(Serializable) itemListeStrings);
					productIntent.putExtra("editTextShopID", editTextShopID);
					productIntent.putExtra("spinnerShopLand", spinnerShopLand);
					productIntent.putExtra("editTextShopSchluesselwort", editTextShopSchluesselwort);
					
					startActivity(productIntent);
				}
			}
		});

		// Button um auf gespeicherte myFlyer-Seite weiterzuleiten
		Button buttonFlyerAnzeigen = (Button) findViewById(R.id.buttonAnzeigenFlyer);
		buttonFlyerAnzeigen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (done) {
					Intent myFlyerIntent = new Intent(view.getContext(),
							MyFlyer.class);
					startActivity(myFlyerIntent);
				}
			}
		});
	}

	public String[][] getListe() {
		return itemListe;
	}

	public void setListe(String[][] liste) {
		this.itemListe = liste;
	}

	// Methode um Popupfenster anzuzeigen
	private void initPopuptWindow() {

		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View popupView = layoutInflater.inflate(R.layout.popup, null);
		popupView.setBackgroundResource(R.layout.sharp);
		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(false);
		popupWindow.update();
		lp.alpha = 0.3f;
		getWindow().setAttributes(lp);
	}

	public void errorTextAktualisieren() {

		TextView textViewShopIDinMain = (TextView) findViewById(R.id.textViewShopIDinMain);
		textViewShopIDinMain.setText("Storename nicht existiert");

		TextView errorTextView = (TextView) popupWindow.getContentView()
				.findViewById((R.id.textViewPopup));
		errorTextView.setText(errorTextView.getText() + " " + errorString);

		// Button um wieder zurueck auf Loginseite weiterzuleiten, um
		// Anmeldedaten zu ueberpruefen und korrigieren
		Button button = (Button) popupWindow.getContentView().findViewById(
				R.id.buttonPopupBestaetigen);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
				popupWindow.dismiss();
				Intent loginIntent = new Intent(view.getContext(),
						LoginActivity.class);
				startActivity(loginIntent);

			}
		});
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Methode um ausgewaehlte Menu-Item zu zeigen (Hilfe/Tutorial)
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (id == R.id.action_hilfe) {
			initPopuptWindow();
			TextView hilfeTextView = (TextView) popupWindow.getContentView()
					.findViewById((R.id.textViewPopup));
			hilfeTextView
					.setText("Hauptseite:\r\n"
							+ "Wenn Datenimport fertig ist, w\u00e4hlen Sie die Schaltfl\u00e4che \r\n"
							+ "1.\"Flyer erstellen\" um einen neuen Flyer f\u00fcr Ihre Produkte zu erstellen\r\n"
							+ "2.\"myFlyer anzeigen\" um gespeicherte Flyer anzuzeigen.");

			Button button = (Button) popupWindow.getContentView().findViewById(
					R.id.buttonPopupBestaetigen);
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					lp.alpha = 1.0f;
					getWindow().setAttributes(lp);
					popupWindow.dismiss();

				}
			});
		}
		return super.onOptionsItemSelected(item);
	}
}
