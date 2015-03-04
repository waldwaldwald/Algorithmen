/**
 * Activity fuer Anzeigen der erstellten myFlyer.
 * @author Li,Yuanyuan
 */

package com.example.ebayflyer;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class MyFlyer extends Activity {

	String currentTag = null;
	FileInputStream fis = null;
	InputStreamReader isr = null;
	char[] inputBuffer = null;
	String data = null;
	XmlPullParserFactory factory = null;
	String[][] liste = null;
	XmlPullParser xpp = null;
	int eventType = 0;
	int zaehler0 = 0;
	int zaehler1 = 0;
	int resultAnzahl = 0;
	String itemId = null;
	String title = null;
	InputStream is = null;
	ByteArrayOutputStream os = null;
	String myFlyerDateinamen[] = null;
	String myFlyerDateiname = null;
	String myFlyer[] = null;
	String myFlyerInhalt = null;
	ArrayAdapter<String> adapterMyFlyer = null;
	TextView textViewMyFlyerInhalt = null;
	ScrollView scrollViewMyFlyerInhalt = null;
	PopupWindow popupWindow;
	WindowManager.LayoutParams lp = null;

	//@author Li,Yuanyuan
	// Methode um GUI zu aktualisieren, um ausgewaehlte gespeicherte Flyer
	// anzuzeigen
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			textViewMyFlyerInhalt.setText(myFlyerInhalt);

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_flyer);

		lp = getWindow().getAttributes();

		final Spinner spinnerMyFlyer = (Spinner) findViewById(R.id.spinnerMyFlyer);
		scrollViewMyFlyerInhalt = (ScrollView) findViewById(R.id.scrollViewMyFlyerInhalt);
		textViewMyFlyerInhalt = (TextView) findViewById(R.id.textViewMyFlyerInhalt);
		textViewMyFlyerInhalt.setMaxLines(Integer.MAX_VALUE);
		textViewMyFlyerInhalt.setMovementMethod(new ScrollingMovementMethod());
		textViewMyFlyerInhalt.setEllipsize(null);
		os = new ByteArrayOutputStream();

		myFlyerDateinamen = getApplicationContext().fileList();

		adapterMyFlyer = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, myFlyerDateinamen);
		spinnerMyFlyer.setAdapter(adapterMyFlyer);
		adapterMyFlyer
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerMyFlyer.setAdapter(adapterMyFlyer);

		// Methode um die DropDown-Liste fuer Dateiauswahl zu zeigen,
		// aktualisieren und danach Datei einzulesen und anzuzeigen
		spinnerMyFlyer.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {

				String item = parentView.getItemAtPosition(position).toString();
				myFlyerDateiname = item;
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						os.reset();
						byte[] buffer = new byte[1024];
						int len = -1;
						try {
							is = MyFlyer.this.openFileInput(myFlyerDateiname);

							while ((len = is.read(buffer)) != -1) {
								os.write(buffer, 0, len);
							}
							myFlyerInhalt = os.toString();

							os.flush();
							os.close();
							is.close();

						} catch (IOException e) {
							e.printStackTrace();
						}

						Message message = new Message();
						mHandler.sendMessage(message);

					}
				});
				t.start();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				myFlyerInhalt = "Noch keinen Flyer ausgew\u00e4hlt";
				Message message = new Message();
				mHandler.sendMessage(message);
			}
		});

		//@author Li,Yuanyuan
		//Button um auf ALS-Seite weiterzuleiten
		Button buttonFlyerAnzeige = (Button) findViewById(R.id.buttonFlyerAnzeige);
		buttonFlyerAnzeige.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent flyIntent = new Intent(view.getContext(),
						TestFlyerActivity.class);
				startActivity(flyIntent);
			}

		});

		//@author Li,Yuanyuan
		// Button um auf Flyer-Erstellung weiterzuleiten
		Button buttonZurueck = (Button) findViewById(R.id.buttonMyFlyerZurueck);
		buttonZurueck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	//@author Li,Yuanyuan
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_flyer, menu);
		return true;
	}

	//@author Li,Yuanyuan
	//Hilfe/Tutorial
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
					.setText("MyFlyer:\r\n"
							+ "W\u00e4hlen Sie einen Flyer aus der Dropdown-Liste um diesen Flyer anzuzeigen.");

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
