package com.example.ebayflyer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class AlsActivity extends Activity {
	TextView textViewMyFlyerInhalt = null;
	ScrollView scrollViewMyFlyerInhalt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_als);
		Intent intent = getIntent();
		final String alsInhalt = intent.getStringExtra("alsInhalt");

		final String editTextShopID = getIntent().getStringExtra(
				"editTextShopID");
		final String spinnerShopLand = getIntent().getStringExtra(
				"spinnerShopLand");
		final String editTextShopSchluesselwort = getIntent().getStringExtra(
				"editTextShopSchluesselwort");

		scrollViewMyFlyerInhalt = (ScrollView) findViewById(R.id.scrollViewALSInhalt);
		textViewMyFlyerInhalt = (TextView) findViewById(R.id.textViewALSInhalt);
		textViewMyFlyerInhalt.setMaxLines(Integer.MAX_VALUE);
		textViewMyFlyerInhalt.setMovementMethod(new ScrollingMovementMethod());
		textViewMyFlyerInhalt.setEllipsize(null);

		textViewMyFlyerInhalt.setText(alsInhalt);

		Button buttonALSzurueck = (Button) findViewById(R.id.buttonALSZurueck);
		buttonALSzurueck.setOnClickListener(new View.OnClickListener() {

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.als, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
