package com.example.ebayflyer;

//@Author: Anton
public class CompareObject {
	
	double attribute1;
	double attribute2;
	double difference;
	
	//@Author: Anton
	public CompareObject (double attribute1, double attribute2)
	{
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		difference = Math.abs(attribute1 - attribute2);
	}

}
