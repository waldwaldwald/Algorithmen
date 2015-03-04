/**
 * Activity fuer Produktkonfiguration: hier wird die Produkten angezeigt, die Nutzer fuer die Flyer ausgewaehlt hat. 
 * Die Nutzer koennen nun die Informationen von Produkten verarbeiten.
 */
package com.example.ebayflyer;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class KonfigurationActivity extends Activity {

	ArrayAdapter<String> adapterKonfiguration = null;
	String[][] alsListe = null;
	String[] produkteArray = null;
	String position = null;
	int start = 0;
	TextView textViewKonID = null;
	EditText editTextKonName = null;
	EditText editTextKonBeschreibung = null;
	EditText editTextKonPreis = null;
	ImageView imageViewKonBild = null;
	Button alsBestaetigen = null;
	String alsInhalt = null;
	Bitmap bitmap = null;
	String name = null;

	private Writer writer;
	private final Context context = null;
	ReadJson readj = new ReadJson();

	FileOutputStream writeFile = null;
	InputStream inputStream = null;
	int outputDoneSize = 0;
	String content = "";

	// AlsListe[x][y]
	// [x]:Index von Items
	// [y]:0-itemId,
	// 1-title,2-categoryId,3-categoryName,4-currentPrice,5-galleryURL,6-Beschreibung

	// @author Li,Yuanyuan
	// Methode um GUI zu aktualisieren, um Produktkonfiguration anzuzeigen
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			imageViewKonBild.setImageBitmap(bitmap);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_konfiguration);

		// @author Li,Yuanyuan
		// Methode um die von MainActivity uebergebenen itemListe(Objekt) in
		// 2D-Array umzuwandeln
		Object[] objectArray = (Object[]) getIntent().getExtras()
				.getSerializable("alsListe");
		int yLength = 0;
		if (objectArray.length > 0) {
			yLength = ((Object[]) objectArray[0]).length;
			alsListe = new String[objectArray.length][yLength];
			int i = 0;
			for (Object row : objectArray) {
				alsListe[i][0] = ((String[]) row)[0];
				alsListe[i++][1] = ((String[]) row)[1];
			}
			i = 0;
			for (Object row : objectArray) {
				alsListe[i][2] = ((String[]) row)[2];
				alsListe[i++][3] = ((String[]) row)[3];
			}
			i = 0;
			for (Object row : objectArray) {
				alsListe[i][4] = ((String[]) row)[4];
				alsListe[i++][5] = ((String[]) row)[5];
			}

		}

		// @author Li,Yuanyuan
		// Schleife um Produktliste als DropDown-Liste anzuzeigen
		for (int i = 0; i < alsListe.length; i++) {
			if (alsListe[i][0] != null) {
				start = i + 1;
			}
		}

		produkteArray = new String[start];
		for (int i = 0; i < start; i++) {
			produkteArray[i] = alsListe[i][0];
		}

		textViewKonID = (TextView) findViewById(R.id.textViewKonfigurationIDInhalt);
		editTextKonName = (EditText) findViewById(R.id.editTextKonfigurationNameInhalt);
		editTextKonBeschreibung = (EditText) findViewById(R.id.editTextKonfigurationBeschreibungInhalt);
		editTextKonPreis = (EditText) findViewById(R.id.editTextKonfigurationPreisInhalt);
		imageViewKonBild = (ImageView) findViewById(R.id.imageViewKonfigurationBildInhalt);

		final String editTextShopID = getIntent().getStringExtra(
				"editTextShopID");
		final String spinnerShopLand = getIntent().getStringExtra(
				"spinnerShopLand");
		final String editTextShopSchluesselwort = getIntent().getStringExtra(
				"editTextShopSchluesselwort");
		
		name = "response_"+editTextShopID+".xml";

		final Spinner spinnerKonfiguration = (Spinner) findViewById(R.id.spinnerKonfiguration);
		adapterKonfiguration = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, produkteArray);
		spinnerKonfiguration.setAdapter(adapterKonfiguration);
		adapterKonfiguration
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerKonfiguration.setAdapter(adapterKonfiguration);

		alsBestaetigen = (Button) findViewById(R.id.buttonKonfigurationBestaetigen);

		// @author Li,Yuanyuan
		// Methode um die DropDown-Liste fuer Dateiauswahl zu zeigen,
		// aktualisieren und danach Datei einzulesen und anzuzeigen
		spinnerKonfiguration
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, final int position, long id) {

						textViewKonID.setText(alsListe[position][0]);
						editTextKonName.setText(alsListe[position][1]);
						editTextKonPreis.setText(alsListe[position][4]);
						editTextKonBeschreibung.setText(alsListe[position][6]);

						// @author Li,Yuanyuan
						// Methode um Bild via URL fuer Produkte darzustellen
						new Thread(new Runnable() {
							@Override
							public void run() {
								bitmap = DownloadImage(alsListe[position][5]);
								Message message = new Message();
								mHandler.sendMessage(message);
							}
						}).start();

						editTextKonName.setFocusable(true);
						editTextKonBeschreibung.setFocusable(true);
						editTextKonPreis.setFocusable(true);

						editTextKonName
								.addTextChangedListener(new TextWatcher() {
									@Override
									public void onTextChanged(CharSequence s,
											int start, int before, int count) {
									}

									@Override
									public void beforeTextChanged(
											CharSequence s, int start,
											int count, int after) {
									}

									@Override
									public void afterTextChanged(Editable s) {
										alsListe[position][1] = editTextKonName
												.getText().toString();
									}
								});

						editTextKonBeschreibung
								.addTextChangedListener(new TextWatcher() {
									@Override
									public void onTextChanged(CharSequence s,
											int start, int before, int count) {
									}

									@Override
									public void beforeTextChanged(
											CharSequence s, int start,
											int count, int after) {
									}

									@Override
									public void afterTextChanged(Editable s) {
										alsListe[position][6] = editTextKonBeschreibung
												.getText().toString();
									}
								});

						editTextKonPreis
								.addTextChangedListener(new TextWatcher() {
									@Override
									public void onTextChanged(CharSequence s,
											int start, int before, int count) {
									}

									@Override
									public void beforeTextChanged(
											CharSequence s, int start,
											int count, int after) {
									}

									@Override
									public void afterTextChanged(Editable s) {
										alsListe[position][4] = editTextKonPreis
												.getText().toString();
									}
								});
					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {

					}
				});
		// @author Li,Yuanyuan
		// Button um ALS-Abfrage-Methode aufzurufen und weiter an
		// FlyerDarstellungseite leiten
		alsBestaetigen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View view) {

				new Thread(new Runnable() {
					public void run() {

						String[][] alsListeStrings = getAlsListe();

						alsSend(); // ALS METHODE wird
						// hier aufgerufen
						// Klasse ALSXML muss noch ein bisschen angepasst werden

						Intent testFlyerIntent = new Intent(view.getContext(),
								TestFlyerActivity.class);

						testFlyerIntent.putExtra("editTextShopID",
								editTextShopID);
						testFlyerIntent.putExtra("spinnerShopLand",
								spinnerShopLand);
						testFlyerIntent.putExtra("editTextShopSchluesselwort",
								editTextShopSchluesselwort);
						testFlyerIntent.putExtra("responsename", name);
						startActivity(testFlyerIntent);

					} // on run
				}).start();
			}

		});
	}

	//@author Li,Yuanyuan
	// Methode um Bild via URL herunterzuladen
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

	//@author Li,Yuanyuan
	// Methode um heruntergeladends Bild in Bitmap umzuwandeln
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

	public String[][] getAlsListe() {
		return alsListe;
	}

	public void setAlsListe(String[][] alsListe) {
		this.alsListe = alsListe;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.konfiguration, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//@author Christian
	public void alsSend() {

		ALSXML al = new ALSXML();
		al.create(alsListe);

		String url = "http://als.dev.medieninnovation.com:4003/postJob/?apiKey=cv6oAh4vDGQYKJwouVYTNnwcVPUdUVK8";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		String filePath = Environment.getExternalStorageDirectory().toString()
				+ "/ALSXML.txt";
		File file = new File(filePath);
		// StringBuilder sb = new StringBuilder(); (Falls der
		// Job als String abgeschickt werden soll)

		try {

			String boundary = "-------------" + System.currentTimeMillis();
			httppost.setHeader("Content-type", "multipart/form-data; boundary="
					+ boundary);
			httppost.setHeader("Accept", "text/xml");

			MultipartEntityBuilder entityBuilder = MultipartEntityBuilder
					.create();
			entityBuilder.setMode(HttpMultipartMode.STRICT);
			entityBuilder.setBoundary(boundary);

			@SuppressWarnings("deprecation")
			FileBody fb = new FileBody(file, "text/xml");
			entityBuilder.addPart("jobFile", fb);

			// Fuer das Abschicken als String
			// entityBuilder.addTextBody("Example.xml",
			// "text/xml");

			Log.d("1", "1");

			Boolean a = file.exists();
			String b = a.toString();
			Log.d("EXIST", b); // Nur zum Ausprobieren, ob die
								// Datei vorhanden ist

			HttpEntity entity = entityBuilder.build();

			httppost.setEntity(entity);
			Log.d("2", "2");

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity httpEntity = response.getEntity();

			String content = EntityUtils.toString(httpEntity);

			// Schreibe die Response direkt in eine Datei
			writeToFile(content);

			// Gibt die Response in den Log aus
			int maxLogSize = 1000;
			for (int i = 0; i <= content.length() / maxLogSize; i++) {
				int start = i * maxLogSize;
				int end = (i + 1) * maxLogSize;
				end = end > content.length() ? content.length() : end;
				Log.v("RESPONSE", content.substring(start, end));
			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

			Log.d("Error", "IO Exception");

		}

	}

	//@author Christian
	public void writeToFile(String content) {
		// File dir = Environment.getExternalStorageDirectory();

		// erstellt eine Datei im "Data/data/" Ordner (der Interne Speicher
		// Und schreibt anschlieﬂend den "content" in die Datei

		inputStream = new ByteArrayInputStream(
				content.getBytes(StandardCharsets.UTF_8));

		
		try {
			writeFile = openFileOutput(name,
					Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				writeFile.write(buffer, 0, bytesRead);

			}

			Log.v("alstest", inputStream.toString());

			inputStream.close();
			writeFile.flush();
			writeFile.close();

			// Fuer das speichern in der SD-Karte
			// Log.d("DateiPfad", context.getFilesDir().toString());
			// // File myFile = new File(dir.toString()+"/NewFile.txt");
			// File myFile = new File(context.getFilesDir(), "NewFile.txt");
			// // Log.d("HIERSCHAUEN", myFile.getAbsolutePath());
			// // myFile.createNewFile();
			//
			// String isthere = Boolean.toString(myFile.exists());
			// Log.d("Datei ist hier", isthere);
			//
			// FileOutputStream fOut = new FileOutputStream(myFile);
			// OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
			// myOutWriter.append(content);
			// myOutWriter.close();
			// fOut.close();
			// Log.d("Success", "File stored");
		} catch (Exception e) {
			Log.d("Error", "Could not write FIle");
		}

	}
}
