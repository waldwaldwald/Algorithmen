package com.example.ebayflyer;


import java.util.LinkedList;
import java.util.List;

//@Author: Anton
public class Algorithm {

	LinkedList<AttributeObject> liste = new LinkedList<AttributeObject>();
	LinkedList<AttributeObject> cluster;
	LinkedList<AttributeObject> clusterBuff;
	LinkedList<LinkedList<AttributeObject>> clusterList = new LinkedList<LinkedList<AttributeObject>>();
	CompareObject compareObject;
	LinkedList<CompareObject> compareList = new LinkedList<CompareObject>();
	Einlesen einlesen;
	Double neededPercent = (double) 50; // Die Items müssen von soviel Prozent der Leute gekauft worden sein
	Double leastDistanceGlobal;
	Double leastDistanceNeededPercent = (double) 20; // Wieviel Prozent der groeßten Distanz soll als Abbruch gelten?

	// @Author: Anton
	// Schnittstelle
	// Input sind das Objekt der Einlesen-Klasse und die Kategorie sowie das der Prozentwert fuer das Abbruchkriterium beim Clustern
	// Output sind die jeweiligen Cluster
	// Beispiel: (Einlesen-Objekt, age, 20)
	public LinkedList<String> inputCategory(Einlesen einlesen, String value, Integer cancelPercent) {
		this.leastDistanceNeededPercent = (double) cancelPercent;
		this.einlesen = einlesen;
		Integer number = null;
		for (int i = 0; i < einlesen.attributes.size(); i++) {
			if (einlesen.attributes.get(i).equals(value)) {
				number = i;
			}
		}

		return cluster(einlesen.attributeList.get(number));
	}

	// @Author: Anton
	// Schnittstelle
	// Input sind das Objekt der Einlesen-Klasse und einer der Cluster, der auch vorher in inputCategory übergeben worden ist, sowie die neededPercent (oben definiert)
	// Output ist eine Liste mit den jeweiligen Items, die von diesem Cluster gekauft worden sind (als Produktempfehlung) 
	// Beispiel (Einlesen-Objekt, 16 - 45, 50)
	public LinkedList<String> inputCluster(Einlesen einlesen, String value, Integer cancelPercent) {
		this.neededPercent = (double) cancelPercent;
		this.einlesen = einlesen;
		LinkedList<String> itemList = new LinkedList<String>();
		Integer number = null;
		

		// Finden des Clusters (z.b. "16 - 28" (Alter))
		for (int i = 0; i < clusterList.size(); i++) {
			if (value.equals((findSmallestNumber(clusterList.get(i)) + " - " + findBiggestNumber(clusterList.get(i))))) {
				number = i;
			}
		}

		Integer numberCategory = null;
		for (int i = 0; i < einlesen.attributes.size(); i++) {
			if (einlesen.attributes.get(i).equals("items")) {
				numberCategory = i;
			}
		}

		for (int i = 0; i < clusterList.get(number).size(); i++) {
			for (int j = 0; j < einlesen.attributeList.get(numberCategory).size(); j++) {
				if (clusterList.get(number).get(i).ID.equals(einlesen.attributeList.get(numberCategory).get(j).ID)) {
					LinkedList<String> tempItemList = returnItems(einlesen.attributeList.get(numberCategory).get(j).attribute);
					for (int k = 0; k < tempItemList.size(); k++) {
						itemList.add(tempItemList.get(k));
						// Alle Items sind (mehrfach) in der Liste
					}
				}
			}
		}

		LinkedList<AttributeObject> countList = new LinkedList<AttributeObject>();
		countList.add(new AttributeObject("1", itemList.get(0)));

		// Die Liste mit allen (mehrfach vorhandenen) Items wird in eine neue Liste getan
		// Jedes Item ist nur ein mal vorhanden, jedoch wird das Attribut ID erhöht, sollte das Item öfters vorkommen
		for (int i = 1; i < itemList.size(); i++) {
			Integer counter = -1;
			for (int j = 0; j < countList.size(); j++) {
				if (countList.get(j).attribute.equals(itemList.get(i))) {
					counter = j;
				}
			}
			if (counter == -1) {
				countList.add(new AttributeObject("1", itemList.get(i)));
			} else {
				Integer tempInt = Integer.parseInt(countList.get(counter).ID) + 1;
				countList.get(counter).ID = Integer.toString(tempInt);
			}
		}

		LinkedList<String> returnList = new LinkedList<String>();

		// Werden die items von X% der Leute gekauft, so kommen sie in die Liste, sonst nicht
		for (int i = 0; i < countList.size(); i++) {
			Double percent = ((double) Integer.parseInt(countList.get(i).ID) / clusterList.get(number).size()) * 100;
			if (percent >= neededPercent) {
				returnList.add(countList.get(i).attribute);
			}

		}

		return returnList;
	}

	// @Author: Anton
	// Aufsplitten des <items> Attribut in der XML Datei in die jeweiligen Items und hinzufuegen dieser in eine eigene Liste
	public LinkedList<String> returnItems(String itemString) {
		LinkedList<String> itemList = new LinkedList<String>();
		String temp = "";

		for (int i = 0; i < itemString.length(); i++) {
			if (itemString.charAt(i) != ',') {
				temp += itemString.charAt(i);
			}
			if (itemString.charAt(i) == ',') {
				itemList.add(temp);
				temp = "";
			}
			if (i == itemString.length() - 1) {
				itemList.add(temp);
				temp = "";
			}
		}
		return itemList;
	}

	// Hauptmethode/Hauptalgorithmus
	// Gibt eine LinkedList mit Clustern (wiederrum LinkedListen) zurueck
	// @Author: Anton
	public LinkedList<String> cluster(LinkedList<AttributeObject> list) {
		this.liste = list;
		clusterList.clear();
		compareList.clear();
		createCluster();
		calcDistance();

		leastDistanceGlobal = findBiggestDistance();

		Integer iteration = 0;

		// Der eigentliche Algorithmus
		while (iterationCheck()) {
			mergeLeastDistanceCluster();
			calcDistance();
			iteration += 1;
		}

		LinkedList<String> returnList = new LinkedList<String>();

		for (int i = 0; i < clusterList.size(); i++) {
			returnList.add(findSmallestNumber(clusterList.get(i)) + " - " + findBiggestNumber(clusterList.get(i)));
		}

		return returnList;
	}

	// Finden der kleinsten Nummer eines Clusters
	// @Author: Anton
	public String findSmallestNumber(LinkedList<AttributeObject> list) {
		int number = Integer.MAX_VALUE;
		for (int i = 0; i < list.size(); i++) {
			if (Integer.parseInt(list.get(i).attribute) < number) {
				number = Integer.parseInt(list.get(i).attribute);
			}
		}
		return Integer.toString(number);
	}

	// Finden der groessten Nummer eines Clusters
	// @Author: Anton
	public String findBiggestNumber(LinkedList<AttributeObject> list) {
		int number = 0;
		for (int i = 0; i < list.size(); i++) {
			if (Integer.parseInt(list.get(i).attribute) > number) {
				number = Integer.parseInt(list.get(i).attribute);
			}
		}
		return Integer.toString(number);
	}

	// Der Algorithmus zum Zusammenlegen zweier Cluster
	// @Author: Anton
	public void mergeCluster(double average1, double average2) {
		boolean check1 = true;
		boolean check2 = true;
		boolean check3 = true;
		clusterBuff = new LinkedList<AttributeObject>();
		// Clusterinhalt in Objekt "ClusterBuff" einfügen
		for (int i = 0; i < clusterList.size(); i++) {
			if (average(clusterList.get(i)) == average1 && check1) {
				for (int j = 0; j < clusterList.get(i).size(); j++) {
					clusterBuff.add(clusterList.get(i).get(j));
				}
				check1 = false;
			}
		}
		for (int i = 0; i < clusterList.size(); i++) {
			if (average(clusterList.get(i)) == average1 && check2) {
				clusterList.remove(i);
				check2 = false;
			}
		}
		for (int i = 0; i < clusterList.size(); i++) {
			if (average(clusterList.get(i)) == average2 && check3) {
				for (int j = 0; j < clusterBuff.size(); j++) {
					clusterList.get(i).add(clusterBuff.get(j));
				}
				clusterBuff.clear();
				check3 = false;
			}
		}
	}

	// Zusammenlegen der beiden CLuster mit der geringsten Distanz zueinander
	// (Algorithmus ist in MergeCluster())
	// @Author: Anton
	public void mergeLeastDistanceCluster() {
		boolean check = true;
		for (int i = 0; i < compareList.size(); i++) {
			if (compareList.get(i).difference == findLeastDistance() && check) {
				mergeCluster(compareList.get(i).attribute1, compareList.get(i).attribute2);
				check = false;
			}
		}
	}

	// Kuerzeste Distanz aller Cluster finden
	// @Author: Anton
	public double findLeastDistance() {
		double leastDistance = Integer.MAX_VALUE;
		for (int i = 0; i < compareList.size(); i++) {
			if (compareList.get(i).difference < leastDistance) {
				leastDistance = compareList.get(i).difference;
			}
		}
		return leastDistance;
	}

	// Groesste Distanz aller Cluster finden
	// @Author: Anton
	public double findBiggestDistance() {
		double leastDistance = 0;
		for (int i = 0; i < compareList.size(); i++) {
			if (compareList.get(i).difference > leastDistance) {
				leastDistance = compareList.get(i).difference;
			}
		}
		return leastDistance;
	}

	// @Author: Anton
	// Methode zum überpruefen, ob noch eine Iteration im Segmentierungsalgorithmus stattfinden soll
	// Abbruchkriterium ist, wenn die derzeitige kleinste Distanz zweier Cluster
	// groesser ist als ein bestimmter Prozentwert der groessten Distanz, die mit den Anfangsclustern festgestellt worden ist
	public boolean iterationCheck() {
		if (findLeastDistance() > leastDistanceGlobal * (leastDistanceNeededPercent / 100)) {
			return false;
		} else {
			return true;
		}
	}

	// Berechnung der Distanz zwischen allen Clustern zueinander
	// @Author: Anton
	public void calcDistance() {
		compareList.clear();
		for (int i = 0; i < clusterList.size(); i++) {
			for (int j = i; j < clusterList.size(); j++) {
				if (i != j) {
					compareObject = new CompareObject(average(clusterList.get(i)), average(clusterList.get(j)));
					compareList.add(compareObject);
				}
			}
		}

	}

	// Bildung des Durchschnittswertes eines Clusters
	// @Author: Anton
	public double average(List<AttributeObject> cluster) {
		double average = 0;
		for (int i = 0; i < cluster.size(); i++) {
			average += Double.parseDouble(cluster.get(i).attribute);
		}
		average = average / (cluster.size());

		return average;
	}

	// Erstellen der Cluster und hinzufuegen der Cluster in eine Liste
	// @Author: Anton
	public void createCluster() {
		for (int i = 0; i < liste.size(); i++) {
			cluster = new LinkedList<AttributeObject>();
			cluster.add(liste.get(i));
			clusterList.add((LinkedList<AttributeObject>) cluster);
		}
	}

}
