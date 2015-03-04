/**
 * Activity fuer Loginseite der App.
 * @author Li,Yuanyuan
 * @Author Christian 
 */

package com.example.ebayflyer;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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

import com.example.ebayflyer.NanoHTTPD.Method;
import com.example.ebayflyer.NanoHTTPD.Response;
import com.example.ebayflyer.NanoHTTPD.ResponseException;


import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Spinner spinnerLand;
	private ArrayAdapter<CharSequence> adapterLand;
	PopupWindow popupWindow;
	WindowManager.LayoutParams lp = null;

	// Fuer Post Anfrage bearbeiten, benoetigt
	public String id;
	public String file;
	public String error;

	// Fuer String in File schreiben benoetigt
	private Writer writer;
	private final Context context = null;
	ReadJson readj = new ReadJson();
	
	FileOutputStream writeFile = null;
	InputStream inputStream = null;
	int outputDoneSize = 0;
	String content = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		lp = getWindow().getAttributes();

		spinnerLand = (Spinner) findViewById(R.id.spinnerShopLand);
		adapterLand = ArrayAdapter.createFromResource(this, R.array.shopland,
				R.layout.spinnerland);
		adapterLand
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerLand.setAdapter(adapterLand);

		final EditText editTextShopID = (EditText) findViewById(R.id.editTextShopID);
		final EditText editTextShopSchluesselwort = (EditText) findViewById(R.id.editTextShopSchluesselwort);

		
		 
		Button buttonAls = (Button) findViewById(R.id.buttonAls);
		buttonAls.setOnClickListener(new View.OnClickListener() {

			/*
			 * ALS Schnittstelle wird hier angesprochen. Die Datei wird von der
			 * SD-Karte gelesen
			 */
			@Override
			public void onClick(View v) {
				
				Intent ausgabe = new Intent(v.getContext(),DisplayActivity.class);
				startActivity(ausgabe);

				

			}// on click

		});

		//@author Li,Yuanyuan
		//Button um zur Hauptseite zu leiten
		Button buttonShopIDBestaetigen = (Button) findViewById(R.id.buttonShopIDBestaetigen);
		buttonShopIDBestaetigen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				

				Intent mainIntent = new Intent(view.getContext(),
						MainActivity.class);
				mainIntent.putExtra("editTextShopID", editTextShopID.getText()
						.toString());
				mainIntent.putExtra("spinnerShopLand", adapterLand
						.getItem(spinnerLand.getSelectedItemPosition()));
				mainIntent.putExtra("editTextShopSchluesselwort",
						editTextShopSchluesselwort.getText().toString());
				startActivity(mainIntent);
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
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = 0.3f;
		getWindow().setAttributes(lp);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	//@author Li,Yuanyuan
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
					.setText("Loginseite:\r\n"
							+ "1. Geben Sie den Name von Shop-Betreiber ein.\r\n"
							+ "2. W\u00e4hlen Sie ein Land f\u00fcr den Shop.\r\n"
							+ "3. Geben Sie ggf. einem Schl\u00fcsselwort ihrer Produkte ein, um die empfangenen Produkte zu begrenzen.\r\n");

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

	/*
	 * @Author Christian 
	 * Versucht die eingehende JobResponse in eine Datei zu speichern Ziel:
	 * SD-Karte, dabei wird ein Ordner mit dem Dateinamen erstellt und Dort
	 * gespeichert
	 * Erster Teil(Uebernommen von MainActivity, von Yuan!
	 * : erstellt eine Datei im "Data/data/" Ordner (der Interne Speicher)
	 * und schreibt anschlieﬂend den "content" in die Datei
	 * Zweiter Teil(auskommentiert): Erstellt eine Datei auf SD-Karte 
	 */
	public void writeToFile(String content) {
//		File dir = Environment.getExternalStorageDirectory();
		
		//erstellt eine Datei im "Data/data/" Ordner (der Interne Speicher
		//Und schreibt anschlieﬂend den "content" in die Datei
		
		inputStream = new ByteArrayInputStream(content
				.getBytes(StandardCharsets.UTF_8));
		
		  try {
			  writeFile = openFileOutput("NewFile1.txt",
						Context.MODE_WORLD_READABLE
								+ Context.MODE_WORLD_WRITEABLE);
			  
			  byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					writeFile.write(buffer, 0, bytesRead);
					
				}
				
				Log.v("alstest",inputStream.toString());
			  
				inputStream.close();
				writeFile.flush();
				writeFile.close();
				
				//Fuer das speichern in der SD-Karte
//			    Log.d("DateiPfad", context.getFilesDir().toString());
////	            File myFile = new File(dir.toString()+"/NewFile.txt");
//			    File myFile = new File(context.getFilesDir(), "NewFile.txt");
////			    Log.d("HIERSCHAUEN", myFile.getAbsolutePath());
////	            myFile.createNewFile();
//			    
//			    String isthere = Boolean.toString(myFile.exists());
//			    Log.d("Datei ist hier", isthere);
//			    
//	            FileOutputStream fOut = new FileOutputStream(myFile);
//	            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
//	            myOutWriter.append(content);
//	            myOutWriter.close();
//	            fOut.close();
//	            Log.d("Success", "File stored");
	        } catch (Exception e) {
	            Log.d("Error", "Could not write FIle"); 
	        }

	}
	
	public String alsSend() {//S
		new Thread(new Runnable() {
			public void run() {
				
				String url = "http://als.dev.medieninnovation.com:4003/postJob/?apiKey=cv6oAh4vDGQYKJwouVYTNnwcVPUdUVK8";

				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);

				String filePath = Environment
						.getExternalStorageDirectory().toString()
						+ "/ALSXML.txt";
				File file = new File(filePath);
				// StringBuilder sb = new StringBuilder(); (Falls der
				// Job als String abgeschickt werden soll)
		

				try {

					String boundary = "-------------"
							+ System.currentTimeMillis();
					httppost.setHeader("Content-type",
							"multipart/form-data; boundary=" + boundary);
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

					HttpResponse response = httpclient
							.execute(httppost);

					HttpEntity httpEntity = response.getEntity();

					String content= EntityUtils.toString(httpEntity);
					Log.v("als0", content+" ");
					
					//Liest die Response direkt als JSON 
		           writeToFile(content);
					// Gibt die Response in den Log aus
					int maxLogSize = 1000;
					for (int i = 0; i <= content.length() / maxLogSize; i++) {
						int start = i * maxLogSize;
						int end = (i + 1) * maxLogSize;
						end = end > content.length() ? content.length()
								: end;
						Log.v("RESPONSE", content.substring(start, end));
					}

				} catch (ClientProtocolException e) {

					e.printStackTrace();

				} catch (IOException e) {

					e.printStackTrace();

					Log.d("Error", "IO Exception");

				}
				

			} // on run
		}).start();
		
		return content;
	}

	/*@Author Christian
	 * Server-Klasse wird hier, quais erstellt und dann aufgerufen, wenn das
	 * Login Fenster geoeffnet wird. Der Konstruktor, benoetigt eine Hostadresse
	 * und einen Port. Wenn Hostadresse = "null", dann wird das Geraet, auf dem
	 * der Server gestartet wird als Host behandelt. --> Verbindung mittels IP.
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private class nano extends NanoHTTPD {
		public nano() throws IOException {
			super(null, 8080);
		}

		/*@Author Christian
		 * Die serve-Methode, verarbeitet einkommende Post-Anfragen und
		 * verarbeitet sie. Hier wird diese, mittels super aufgerufen. Die
		 * eigentliche Methode,befindet sich in der "NanoHTTPD"-Klasse und wurde
		 * hier Ueberschrieben, um die benoetigten Post-Felder (id, file, error)
		 * zu empfangen
		 */
		public Response serve(IHTTPSession session) {
			Map<String, String> files = new HashMap<String, String>();
			Method method = session.getMethod();

			Log.d(session.getMethod().toString(), session.getUri());

			Log.d("HEADER", session.getHeaders().toString());

			if (Method.POST.equals(method)) {
				try {
					session.parseBody(files);

				} catch (IOException ioe) {
					return new Response(Response.Status.INTERNAL_ERROR,
							MIME_PLAINTEXT,
							"SERVER INTERNAL ERROR: IOException: "
									+ ioe.getMessage());
				} catch (ResponseException re) {
					return new Response(re.getStatus(), MIME_PLAINTEXT,
							re.getMessage());
				}
			}
			Map<String, String> parms = session.getParms();
			parms.put(QUERY_STRING_PARAMETER, session.getQueryParameterString());

			// Wenn PostData "id", "file" oder "error" (oder eben alles"
			// enthaelt:
			if (parms.containsKey("id") || parms.containsKey("file")
					|| parms.containsKey("error"))
				id = parms.get("id");
			file = parms.get("file");
			error = parms.get("error");

			Log.d("ID", id);
			Log.d("FILE", file);
			Log.d("ERROR", error);

			// String file, wird in eine Datei gespichert
			writeToFile(file);

			return serve(session.getUri(), method, session.getHeaders(), parms,
					files);

			// return super.serve(session);
		}

	}

}
