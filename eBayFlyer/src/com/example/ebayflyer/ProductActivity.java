/**
 * Activity fuer Produktauswahl: hier wird die vorgeschlagenen Produkten angezeigt, die Nutzer fuer die Flyer auswaehlen kann.
 * @author Li,Yuanyuan
 * @author Naima
 */

package com.example.ebayflyer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends Activity implements
		android.widget.CompoundButton.OnCheckedChangeListener {

	Spinner spinnerMerkmale;
	Algorithm algorithm = new Algorithm();
	Spinner spinnerGruppe;
	ArrayAdapter<String> spinnerAdapter;
	ListView lv;
	ArrayList<ProduktListe> produktListe;
	ListViewAdapter plAdapter;
	ArrayAdapter<String> adapter;
	ArrayList<String> vorschlagsListe;
	Button produktVorschlag;
	Button kriteriumAuswahl;
	Button alsBestaetigen;
	PopupWindow popupWindow;
	WindowManager.LayoutParams lp = null;
	String[][] itemListe = null;
	ScrollView myScrollView = null;
	List<String> vergleichListe = null;
	String[][] alsListe = null;
	String[][] produktvorschlagsListe = null;
	boolean segmentierungsprozess = false;
	String alsInhalt = null;
	int start = 0;
	boolean eingabeFinish = false;
	boolean kriteriumFinish = false;
	int abbruchkriterium = 50;
	boolean kriteriumStart = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product);

		lp = getWindow().getAttributes();

		// @author Li,Yuanyuan
		// Methode um die von MainActivity uebergebenen itemListe(Objekt) in
		// 2D-Array umzuwandeln
		Object[] objectArray = (Object[]) getIntent().getExtras()
				.getSerializable("itemListe");
		int yLength = 0;
		if (objectArray.length > 0) {
			yLength = ((Object[]) objectArray[0]).length;
			itemListe = new String[objectArray.length][yLength];
			int i = 0;
			for (Object row : objectArray) {
				itemListe[i][0] = ((String[]) row)[0];
				itemListe[i++][1] = ((String[]) row)[1];
			}
			i = 0;
			for (Object row : objectArray) {
				itemListe[i][2] = ((String[]) row)[2];
				itemListe[i++][3] = ((String[]) row)[3];
			}
			i = 0;
			for (Object row : objectArray) {
				itemListe[i][4] = ((String[]) row)[4];
				itemListe[i++][5] = ((String[]) row)[5];
			}
		}

		// @author Li,Yuanyuan
		// Initialisierung
		final String editTextShopID = getIntent().getStringExtra(
				"editTextShopID");
		final String spinnerShopLand = getIntent().getStringExtra(
				"spinnerShopLand");
		final String editTextShopSchluesselwort = getIntent().getStringExtra(
				"editTextShopSchluesselwort");

		spinnerMerkmale = (Spinner) findViewById(R.id.spinnerKriteriumAuswahl);
		spinnerGruppe = (Spinner) findViewById(R.id.spinnerGruppeAuswahl);
		lv = (ListView) findViewById(R.id.listView);

		produktVorschlag = (Button) findViewById(R.id.buttonProduktvorschlag);
		kriteriumAuswahl = (Button) findViewById(R.id.buttonKriteriumAuswahl);
		alsBestaetigen = (Button) findViewById(R.id.buttonBestaetigen);
		vorschlagsListe = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_spinner_item, vorschlagsListe);
		adapter.notifyDataSetChanged();
		spinnerAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, android.R.id.text1);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerGruppe.setAdapter(spinnerAdapter);
		vergleichListe = new ArrayList<String>();
		alsListe = new String[100][7];
		produktvorschlagsListe = new String[100][7];
		displayProductList();

		// @author Li,Yuanyuan
		// Button um Semgmentierungsmethode aufzurufen
		kriteriumAuswahl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				initEingabePopuptWindow();
				eingabeKundengruppierung();
			}
		});

		// @author Li,Yuanyuan
		// Button um Semgmentierungsmethode aufzurufen
		produktVorschlag.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (kriteriumFinish) {
					initEingabePopuptWindow();
					eingabeProduktschlag();
				}
			}
		});

		// @author Li,Yuanyuan
		// Methode um endgueltige Produkte-Liste fuer Flyer zu erstellen
		alsBestaetigen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				vergleichListe.clear();

				for (int i = 0; i < produktListe.size(); i++) {
					if (produktListe.get(i).isSelected() == true) {
						vergleichListe.add(String.valueOf(i));
					}
				}
				for (int i = 0; i < vergleichListe.size(); i++) {

					alsListe[i][0] = itemListe[Integer.parseInt(vergleichListe
							.get(i))][0];
					alsListe[i][1] = itemListe[Integer.parseInt(vergleichListe
							.get(i))][1];
					alsListe[i][2] = itemListe[Integer.parseInt(vergleichListe
							.get(i))][2];
					alsListe[i][3] = itemListe[Integer.parseInt(vergleichListe
							.get(i))][3];
					alsListe[i][4] = itemListe[Integer.parseInt(vergleichListe
							.get(i))][4];
					alsListe[i][5] = itemListe[Integer.parseInt(vergleichListe
							.get(i))][5];
				}

				String[][] alsListeStrings = getAlsListe();

				Intent konfigurationIntent = new Intent(view.getContext(),
						KonfigurationActivity.class);
				konfigurationIntent.putExtra("alsListe",
						(Serializable) alsListeStrings);

				konfigurationIntent.putExtra("editTextShopID", editTextShopID);
				konfigurationIntent
						.putExtra("spinnerShopLand", spinnerShopLand);
				konfigurationIntent.putExtra("editTextShopSchluesselwort",
						editTextShopSchluesselwort);
				startActivity(konfigurationIntent);
			}
		});
	}

	// @author Li,Yuanyuan
	// Methode um das Eingabe-Popupfenster anzuzeigen und das Abbruchkriterium
	// fuer Kundengruppierungsmethode anzugeben
	public void eingabeKundengruppierung() {
		final EditText abbruchkriteriumKundenEditText = (EditText) popupWindow
				.getContentView().findViewById(
						(R.id.editTextEingabeAbbruchkriterium));
		abbruchkriteriumKundenEditText.setText("20");

		final TextView textViewEingabeKundenAbbruchkriterium = (TextView) popupWindow
				.getContentView().findViewById(
						(R.id.textViewEingabeAbbruchkriterium));

		textViewEingabeKundenAbbruchkriterium
				.setText(textViewEingabeKundenAbbruchkriterium.getText()
						+ " (Kundengruppierung)");

		Button button = (Button) popupWindow.getContentView().findViewById(
				R.id.buttonPopupBestaetigen);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
				popupWindow.dismiss();

				abbruchkriterium = Integer
						.parseInt(abbruchkriteriumKundenEditText.getText()
								.toString());
				kriteriumStart = true;
				eingabeKundengruppierungssegmentierung();
			}
		});

	}

	// @author Li,Yuanyuan
	// Methode um die Kundengruppierungsmethode aufzurufen
	public void eingabeKundengruppierungssegmentierung() {
		if (kriteriumStart) {
			Message message = new Message();
			mHandler.sendMessage(message);

			CreateXML writer = new CreateXML(itemListe);
			writer.create();

			Einlesen einlesen = new Einlesen();
			einlesen.einlesen();
			String strMerkmale = spinnerMerkmale.getSelectedItem().toString();
			LinkedList<String> listBuffer = algorithm.inputCategory(einlesen,
					strMerkmale, abbruchkriterium);
			spinnerAdapter.clear();
			for (int i = 0; i < listBuffer.size(); i++) {
				spinnerAdapter.add(listBuffer.get(i));
			}
			spinnerAdapter.notifyDataSetChanged();
			plAdapter.notifyDataSetChanged();

		}
	}

	// @author Li,Yuanyuan
	// Methode um das Eingabe-Popupfenster und die
	// Produktvorschlagssegmentierungsmethode aufzurufen
	public void eingabeProduktschlag() {
		final EditText abbruchkriteriumProduktEditText = (EditText) popupWindow
				.getContentView().findViewById(
						(R.id.editTextEingabeAbbruchkriterium));

		final TextView textViewEingabeProduktAbbruchkriterium = (TextView) popupWindow
				.getContentView().findViewById(
						(R.id.textViewEingabeAbbruchkriterium));

		textViewEingabeProduktAbbruchkriterium
				.setText(textViewEingabeProduktAbbruchkriterium.getText()
						+ " (Produktvorschlag)");

		Button button = (Button) popupWindow.getContentView().findViewById(
				R.id.buttonPopupBestaetigen);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				vorschlagsListe.clear();
				abbruchkriterium = Integer
						.parseInt(abbruchkriteriumProduktEditText.getText()
								.toString());
				lp.alpha = 1.0f;
				getWindow().setAttributes(lp);
				popupWindow.dismiss();
				Einlesen einlesen = new Einlesen();
				einlesen.einlesen();
				String strGruppe = spinnerGruppe.getSelectedItem().toString();
				LinkedList<String> listBuffer = algorithm.inputCluster(
						einlesen, strGruppe, abbruchkriterium);
				listeClear();
				for (int i = 0; i < listBuffer.size(); i++) {
					vorschlagsListe.add(listBuffer.get(i));
				}
				vergleichAlleVorschlag();
				plAdapter.notifyDataSetChanged();

			}
		});

	}

	// @author Li,Yuanyuan
	// Methode um das Liste zu leeren falls Produktvorschlag nochmals gemacht
	// wird.
	public void listeClear() {

		vergleichListe.clear();
		vorschlagsListe.clear();

		for (int i = 0; i < produktvorschlagsListe.length; i++) {

			produktvorschlagsListe[i][0] = null;
			produktvorschlagsListe[i][1] = null;
			produktvorschlagsListe[i][2] = null;
			produktvorschlagsListe[i][3] = null;
			produktvorschlagsListe[i][4] = null;
			produktvorschlagsListe[i][5] = null;
		}
		for (int i = 0; i < produktListe.size(); i++) {
			produktListe.get(i).setSelected(false);
		}
	}

	// @author Li,Yuanyuan
	// Methode um das Vergleich von vorgeschlagenen Produkte und aller Produkten
	public void vergleichAlleVorschlag() {

		for (int i = 0; i < produktListe.size(); i++) {
			for (int j = 0; j < vorschlagsListe.size(); j++) {
				if (produktListe.get(i).getProduktID()
						.equals(vorschlagsListe.get(j))) {
					produktListe.get(i).setSelected(true);
				}
			}
		}

		for (int i = 0; i < produktListe.size(); i++) {
			if (produktListe.get(i).isSelected() == true) {
				vergleichListe.add(String.valueOf(i));
			}
		}

		for (int i = 0; i < vergleichListe.size(); i++) {

			produktvorschlagsListe[i][0] = itemListe[Integer.parseInt(vergleichListe
					.get(i))][0];
			produktvorschlagsListe[i][1] = itemListe[Integer.parseInt(vergleichListe
					.get(i))][1];
			produktvorschlagsListe[i][2] = itemListe[Integer.parseInt(vergleichListe
					.get(i))][2];
			produktvorschlagsListe[i][3] = itemListe[Integer.parseInt(vergleichListe
					.get(i))][3];
			produktvorschlagsListe[i][4] = itemListe[Integer.parseInt(vergleichListe
					.get(i))][4];
			produktvorschlagsListe[i][5] = itemListe[Integer.parseInt(vergleichListe
					.get(i))][5];
		}

		// @author Li,Yuanyuan
		// Nach dem Vergleich soll PopupFenster mit vorschlagenenen Progdukten
		// angezeigt werden
		initsmallPopuptWindow();
		TextView vorschlagTextView = (TextView) popupWindow.getContentView()
				.findViewById((R.id.textViewPopupSmall));
		String textString = "";
		int start1 = 0;

		for (int i = 0; i < produktvorschlagsListe.length; i++) {
			if (produktvorschlagsListe[i][0] != null) {
				start1 = i;
			}
		}
		for (int i = 0; i < (start1 + 1); i++) {
			textString += (i + ": " + produktvorschlagsListe[i][1] + "\r\n");
		}
		vorschlagTextView.setText("Produktvorschlag:\r\n" + textString);

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

	public String[][] getAlsListe() {
		return alsListe;
	}

	public void setAlsListe(String[][] alsListe) {
		this.alsListe = alsListe;
	}

	// @author Li,Yuanyuan
	// Methode um GUI(ShopID) zu aktualisieren und Popupfenster anzuzeigen
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			Toast.makeText(ProductActivity.this,
					"Segmentierung wird ausgef\u00fchrt.", Toast.LENGTH_SHORT)
					.show();
			kriteriumFinish = true;
		};
	};

	// @author Naima
	// Methode um via ueberschriebenen ListViewAdapter ein Listview fuer
	// Produkte anzuzeigen und aktualisieren
	private void displayProductList() {

		produktListe = new ArrayList<ProduktListe>();
		for (int i = 0; i < itemListe.length; i++) {
			produktListe.add(new ProduktListe(itemListe[i][1], itemListe[i][0],
					itemListe[i][4], itemListe[i][5]));
		}

		plAdapter = new ListViewAdapter(produktListe, this);
		lv.setAdapter(plAdapter);
	}

	// @author Naima
	// Methode um die manull ausgewaehlte/abgewaehlte Produkte zu Flyer
	// hinzuzufuegen
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		int pos = lv.getPositionForView(buttonView);

		if (pos != ListView.INVALID_POSITION) {
			produktListe.get(pos).setSelected(isChecked);
		}
		Toast.makeText(
				this,
				produktListe.get(pos).getProduktName() + ". State: is "
						+ isChecked, Toast.LENGTH_SHORT).show();
	}

	// @author Li,Yuanyuan
	// Methode um Popupfenster anzuzeigen
	@SuppressWarnings("deprecation")
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

	// @author Li,Yuanyuan
	// Methode um Popupfenster mit kleinen Schriftgroesse anzuzeigen
	@SuppressWarnings("deprecation")
	private void initsmallPopuptWindow() {

		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View popupView = layoutInflater.inflate(R.layout.popupsmall, null);
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

	// @author Li,Yuanyuan
	// Methode um Popupfenster fuer Eingabe der Anzahl der Kundengruppe und
	// Abbruchkriterium in % anzuzeigen
	@SuppressWarnings("deprecation")
	private void initEingabePopuptWindow() {

		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View popupView = layoutInflater.inflate(R.layout.popupeingabe, null);
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
		getMenuInflater().inflate(R.menu.product, menu);
		return true;
	}

	// @author Li,Yuanyuan
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
					.setText("Produktauswahl:\r\n"
							+ "1.W\u00e4hlen Sie ein Kriterium und klicken Sie die Schaltfl\u00e4che \"Kriterium\"\r\n"
							+ "2.W\u00e4hlen Sie eine Kundengruppe und klicken Sie die Schaltfl\u00e4che \"Gruppe\"\r\n"
							+ "3.Neben der vorschlagenene Produkte k\u00f6nen Sie weitere Produkte selektieren\r\n"
							+ "4.Kliecken Sie die Schaltfl\u00e4che \"Best\u00e4tigen\"");

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
