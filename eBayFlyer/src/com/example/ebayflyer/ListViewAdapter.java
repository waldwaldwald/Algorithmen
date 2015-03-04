/**
 * Klasse fuer ListViewAdapter, das in ProductActivity fuer ListView verwendet wird.
 * @author Naima
 */

package com.example.ebayflyer;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

class ProduktListe {

	String produktName;
	String produktID;
	String preis;
	String urlBild;
	boolean selected = false;
	ProduktListe p = null;

	public ProduktListe(String produktName, String produktID, String preis,
			String urlBild) {
		super();
		this.produktName = produktName;
		this.produktID = produktID;
		this.preis = preis;
		this.urlBild = urlBild;
	}

	public String getProduktName() {
		return produktName;
	}

	public void setProduktName(String produktName) {
		this.produktName = produktName;
	}

	public String getProduktID() {
		return produktID;
	}

	public void setProduktID(String produktID) {
		this.produktID = produktID;
	}

	public String getPreis() {
		return preis;
	}

	public void setPreis(String preis) {
		this.preis = preis;
	}

	public String getBild() {
		return urlBild;
	}

	public void setBild(String urlBild) {
		this.urlBild = urlBild;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}

public class ListViewAdapter extends ArrayAdapter<ProduktListe> {
	private List<ProduktListe> prdList;
	private Context context;

	public ListViewAdapter(List<ProduktListe> produktListe, Context context) {
		super(context, R.layout.activity_product, produktListe);
		this.prdList = produktListe;
		this.context = context;
	}

	private static class ProduktHolder {
		public TextView pName;
		public TextView preis;
		public CheckBox chkBox;
	}

	ProduktHolder holder = null;
	ProduktListe p = null;
	View v = null;
	LayoutInflater inflater = null;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView != null) {
			v = convertView;
			holder = (ProduktHolder) v.getTag();

		} else {
			v = convertView;
			holder = new ProduktHolder();
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.listview, null);

			holder.pName = (TextView) v.findViewById(R.id.produkte);
			holder.preis = (TextView) v.findViewById(R.id.preis);
			holder.chkBox = (CheckBox) v.findViewById(R.id.chk_box);

			holder.chkBox.setOnCheckedChangeListener((ProductActivity) context);
			p = prdList.get(position);
			holder.pName.setText(p.getProduktName());
			holder.preis.setText("\u20AC" + p.getPreis() + "      ");
			holder.chkBox.setChecked(p.isSelected());
			holder.chkBox.setTag(p);

		}
		return v;
	}
}
