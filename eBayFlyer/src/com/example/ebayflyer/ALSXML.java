package com.example.ebayflyer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;

import android.os.Environment;

//@Author: Anton
public class ALSXML {

	String location = Environment.getExternalStorageDirectory().toString()
			+ "/ALSXML.txt";
	String newLine = "\r\n";

	public void start() {
		// create();
	}

	// @Author: Anton
	// Erstellen der XML-Datei
	public void create(String[][] products) {
		File outputFile = new File(location);

		try {
			outputFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		PrintWriter output;
		BufferedWriter buffer;
		FileWriter fileWriter;

		try {
			fileWriter = new FileWriter(outputFile);
			buffer = new BufferedWriter(fileWriter);
			output = new PrintWriter(buffer);
			try {
//				writeFile(output, products);
				writeFile(output);
			} finally {
				output.close();
				buffer.close();
				fileWriter.close();
			}
		} catch (IOException e) {
			System.out.println("Fehler");
		}
	}

	// @Author: Anton
	// Mit Inhalt befuellen
	public void writeFile(PrintWriter output) {
		output.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newLine);
		output.print("<jobCollection xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://als.medieninnovation.com/job\" xmlns:prod=\"http://als.medieninnovation.com/prod\""
				+ newLine);
		output.print("	xmlns:content=\"http://als.medieninnovation.com/content\""
				+ newLine);
		output.print("	xsi:schemaLocation=\"http://als.medieninnovation.com/job http://als.dev.medieninnovation.com/schema/JobCollection.xsd http://als.medieninnovation.com/prod http://als.dev.medieninnovation.com/schema/ProductCollection.xsd http://als.medieninnovation.com/content http://als.dev.medieninnovation.com/schema/ContentCollection.xsd\">"
				+ newLine);
		output.print("	<contentCollection xmlns=\"http://als.medieninnovation.com/content\">"
				+ newLine);

		// Produkte
		output.print("		<product contentRefId=\"p1\" xmlns=\"http://als.medieninnovation.com/prod\">"
				+ newLine);
		output.print("			<product_id>01</product_id>" + newLine);
		output.print("			<title>Handtasche</title>" + newLine);
		output.print("			<subTitle>aus Gold</subTitle>" + newLine);
		output.print("			<shortDescription>Sehr schicke Handtasche!!!</shortDescription>"
				+ newLine);
		output.print("			<category>" + newLine);
		output.print("				<name>Damen Handtaschen</name>" + newLine);
		output.print("			</category>" + newLine);
		output.print("			<state>NEU</state>" + newLine);
		output.print("			<availability>Sofort Lieferbar</availability>"
				+ newLine);
		output.print("			<priceGross>500</priceGross>" + newLine);
		output.print("			<currency>EUR</currency>" + newLine);
		output.print("		</product>" + newLine);

		output.print("		<product contentRefId=\"p2\" xmlns=\"http://als.medieninnovation.com/prod\">"
				+ newLine);
		output.print("			<product_id>02</product_id>" + newLine);
		output.print("			<title>Lederschuhe</title>" + newLine);
		output.print("			<subTitle>aus Leder!</subTitle>" + newLine);
		output.print("			<shortDescription>Sehr Toll!!</shortDescription>"
				+ newLine);
		output.print("			<category>" + newLine);
		output.print("				<name>Damen Handtaschen</name>" + newLine);
		output.print("			</category>" + newLine);
		output.print("			<state>NEU</state>" + newLine);
		output.print("			<availability>Sofort Lieferbar</availability>"
				+ newLine);
		output.print("			<priceGross>500</priceGross>" + newLine);
		output.print("			<currency>EUR</currency>" + newLine);
		output.print("		</product>" + newLine);

		output.print("	</contentCollection>" + newLine);
		output.print("	<job>" + newLine);
		output.print("		<pageCollection>" + newLine);
		output.print("			<singlePage>" + newLine);
		output.print("				<contentToPageCollection>" + newLine);
		output.print("					<contentToPage>" + newLine);
		output.print("						<contentRef>p1</contentRef>" + newLine);
		output.print("						<weight>0.5</weight>" + newLine);
		output.print("					</contentToPage>" + newLine);
		output.print("					<contentToPage>" + newLine);
		output.print("						<contentRef>p2</contentRef>" + newLine);
		output.print("						<weight>0.5</weight>" + newLine);
		output.print("					</contentToPage>" + newLine);
		output.print("				</contentToPageCollection>" + newLine);
		output.print("				<pageDesign>" + newLine);
		output.print("					<css></css>" + newLine);
		output.print("				</pageDesign>" + newLine);
		output.print("			</singlePage>" + newLine);
		output.print("			<multiPage maxPages=\"0\">" + newLine);
		output.print("				<contentToPageCollection>" + newLine);
		output.print("					<contentToPage>" + newLine);
		output.print("						<contentRef>p1</contentRef>" + newLine);
		output.print("						<weight>0.5</weight>" + newLine);
		output.print("					</contentToPage>" + newLine);
		output.print("					<contentToPage>" + newLine);
		output.print("						<contentRef>p2</contentRef>" + newLine);
		output.print("						<weight>0.5</weight>" + newLine);
		output.print("					</contentToPage>" + newLine);
		output.print("				</contentToPageCollection>" + newLine);
		output.print("				<pageDesign>" + newLine);
		output.print("					<css></css>" + newLine);
		output.print("				</pageDesign>" + newLine);
		output.print("			</multiPage>" + newLine);
		output.print("			<pageDesign>" + newLine);
		output.print("				<css>" + newLine);
		output.print("					page{" + newLine);
		output.print("					ppi: 170;" + newLine);
		output.print("					background-image: url(\"http://twofrugalmommas.com/wp-content/uploads/2011/05/shopping.jpg\");"
				+ newLine);
		output.print("					height:600px;" + newLine);
		output.print("					width:1024px;" + newLine);
		output.print("					padding-top: 20px;" + newLine);
		output.print("					padding-bottom: 33px;" + newLine);
		output.print("					padding-left: 13px;" + newLine);
		output.print("					padding-right: 13px;" + newLine);
		output.print("					}" + newLine);

		output.print("				</css>" + newLine);
		output.print("			</pageDesign>" + newLine);
		output.print("		</pageCollection>" + newLine);
		output.print("		<layoutGeneratorTable />" + newLine);
		output.print("		<outputCollection>" + newLine);
		output.print("		    <!--<outputWebserver outputRefId=\"outputWebserver\" directOutput=\"true\">-->"
				+ newLine);
		output.print("			<!--	<showWaiting>true</showWaiting>-->" + newLine);
		output.print("			<!--	<showSubmittedJob>true</showSubmittedJob>-->"
				+ newLine);
		output.print("			<!--	<addPagination>true</addPagination>-->" + newLine);
		output.print("		   <!--	</outputWebserver>-->" + newLine);
		output.print("			     <outputXML outputRefId=\"outputXml\" directOutput=\"true\" /> "
				+ newLine);
		output.print("			   <!--  <outputJSON outputRefId=\"outputJson\" directOutput=\"true\" /> -->"
				+ newLine);
		output.print("		</outputCollection>" + newLine);
		output.print("	            <senderCollection> " + newLine);
		output.print("		             <senderPost> " + newLine);
		output.print("			     <outputRef>outputJson</outputRef>" + newLine);
		output.print("		             <url>http://client.als.dev.medieninnovation.com/post.php?action=save</url> "
				+ newLine);
		output.print("		<fieldnameJobId>id</fieldnameJobId> " + newLine);
		output.print("		<fieldnameFile>file</fieldnameFile> " + newLine);
		output.print("		<fieldnameError>error</fieldnameError>" + newLine);
		output.print("		 </senderPost> " + newLine);
		output.print("		   <!--   <senderMail> -->" + newLine);
		output.print("		    <!--  <outputRef>outputXML</outputRef> -->"
				+ newLine);
		output.print("		    <!--  <to>chrismasc@web.de</to> -->" + newLine);
		output.print("		     <!-- <from>als@medieninnovation.com</from> -->"
				+ newLine);
		output.print("		     <!-- <subject>New ALS output document</subject> -->"
				+ newLine);
		output.print("		    <!--  <attachment>false</attachment> -->" + newLine);
		output.print("		    <!--  </senderMail> -->" + newLine);
		output.print("		   </senderCollection> " + newLine);
		output.print("		<configuration>" + newLine);
		output.print("			<apiKey>cv6oAh4vDGQYKJwouVYTNnwcVPUdUVK8</apiKey>"
				+ newLine);
		output.print("			<language>de-DE</language>" + newLine);
		output.print("			<minLayouts>10</minLayouts>" + newLine);
		output.print("			<maxRenderingTime>30</maxRenderingTime>" + newLine);
		output.print("		</configuration>" + newLine);
		output.print("	</job>" + newLine);
		output.print("</jobCollection>");

	}

	public void writeFile(PrintWriter output, String[][] products) {
		output.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + newLine);
		output.print("<jobCollection xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://als.medieninnovation.com/job\" xmlns:prod=\"http://als.medieninnovation.com/prod\""
				+ newLine);
		output.print("	xmlns:content=\"http://als.medieninnovation.com/content\""
				+ newLine);
		output.print("	xsi:schemaLocation=\"http://als.medieninnovation.com/job http://als.dev.medieninnovation.com/schema/JobCollection.xsd http://als.medieninnovation.com/prod http://als.dev.medieninnovation.com/schema/ProductCollection.xsd http://als.medieninnovation.com/content http://als.dev.medieninnovation.com/schema/ContentCollection.xsd\">"
				+ newLine);
		output.print("	<contentCollection xmlns=\"http://als.medieninnovation.com/content\">"
				+ newLine);

		// Produkte

		for (int i = 0; i < products.length; i++) {
			if (!(products[i][0] == null)) {

				products[i][0] = Normalizer.normalize(products[i][0],
						Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "e");
				if (products[i][0].indexOf('<') > -1) {
					products[i][0] = products[i][0].replaceAll("<", "&lt;");
				}
				if (products[i][0].indexOf('<') > -1) {
					products[i][0] = products[i][0].replaceAll(">", "&gt;");
				}
				if (products[i][0].indexOf('<') > -1) {
					products[i][0] = products[i][0].replaceAll("&", "&amp;");
				}
				if (products[i][0].indexOf('<') > -1) {
					products[i][0] = products[i][0].replaceAll("\'", "&apos;");
				}
				if (products[i][0].indexOf('<') > -1) {
					products[i][0] = products[i][0].replaceAll("\"", "&quot;");
				}

				if (!(products[i][1] == null)) {
					products[i][1] = Normalizer.normalize(products[i][1],
							Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]",
							"e");
					if (products[i][1].indexOf('<') > -1) {
						products[i][1] = products[i][1].replaceAll("<", "&lt;");
					}
					if (products[i][1].indexOf('<') > -1) {
						products[i][1] = products[i][1].replaceAll(">", "&gt;");
					}
					if (products[i][1].indexOf('<') > -1) {
						products[i][1] = products[i][1]
								.replaceAll("&", "&amp;");
					}
					if (products[i][1].indexOf('<') > -1) {
						products[i][1] = products[i][1].replaceAll("\'",
								"&apos;");
					}
					if (products[i][1].indexOf('<') > -1) {
						products[i][1] = products[i][1].replaceAll("\"",
								"&quot;");
					}
				} else {
					products[i][1] = "NoErrorString";
				}

				if (!(products[i][2] == null)) {
					products[i][2] = Normalizer.normalize(products[i][2],
							Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]",
							"e");
					if (products[i][2].indexOf('<') > -1) {
						products[i][2] = products[i][2].replaceAll("<", "&lt;");
					}
					if (products[i][2].indexOf('<') > -1) {
						products[i][2] = products[i][2].replaceAll(">", "&gt;");
					}
					if (products[i][2].indexOf('<') > -1) {
						products[i][2] = products[i][2]
								.replaceAll("&", "&amp;");
					}
					if (products[i][2].indexOf('<') > -1) {
						products[i][2] = products[i][2].replaceAll("\'",
								"&apos;");
					}
					if (products[i][2].indexOf('<') > -1) {
						products[i][2] = products[i][2].replaceAll("\"",
								"&quot;");
					}
				} else {
					products[i][2] = "NoErrorString";
				}

				if (!(products[i][3] == null)) {
					products[i][3] = Normalizer.normalize(products[i][3],
							Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]",
							"e");
					if (products[i][3].indexOf('<') > -1) {
						products[i][3] = products[i][3].replaceAll("<", "&lt;");
					}
					if (products[i][3].indexOf('<') > -1) {
						products[i][3] = products[i][3].replaceAll(">", "&gt;");
					}
					if (products[i][3].indexOf('<') > -1) {
						products[i][3] = products[i][3]
								.replaceAll("&", "&amp;");
					}
					if (products[i][3].indexOf('<') > -1) {
						products[i][3] = products[i][3].replaceAll("\'",
								"&apos;");
					}
					if (products[i][3].indexOf('<') > -1) {
						products[i][3] = products[i][3].replaceAll("\"",
								"&quot;");
					}
				} else {
					products[i][3] = "NoErrorString";
				}

				products[i][4] = "EUR" + products[i][4];

				if (!(products[i][5] == null)) {
					products[i][5] = Normalizer.normalize(products[i][5],
							Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]",
							"e");
					if (products[i][5].indexOf('<') > -1) {
						products[i][5] = products[i][5].replaceAll("<", "&lt;");
					}
					if (products[i][5].indexOf('<') > -1) {
						products[i][5] = products[i][5].replaceAll(">", "&gt;");
					}
					if (products[i][5].indexOf('<') > -1) {
						products[i][5] = products[i][5]
								.replaceAll("&", "&amp;");
					}
					if (products[i][5].indexOf('<') > -1) {
						products[i][5] = products[i][5].replaceAll("\'",
								"&apos;");
					}
					if (products[i][5].indexOf('<') > -1) {
						products[i][5] = products[i][5].replaceAll("\"",
								"&quot;");
					}
				} else {
					products[i][5] = "NoErrorString";
				}

				if (!(products[i][6] == null)) {
					products[i][6] = Normalizer.normalize(products[i][6],
							Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]",
							"e");
					if (products[i][6].indexOf('<') > -1) {
						products[i][6] = products[i][6].replaceAll("<", "&lt;");
					}
					if (products[i][6].indexOf('<') > -1) {
						products[i][6] = products[i][6].replaceAll(">", "&gt;");
					}
					if (products[i][6].indexOf('<') > -1) {
						products[i][6] = products[i][6]
								.replaceAll("&", "&amp;");
					}
					if (products[i][6].indexOf('<') > -1) {
						products[i][6] = products[i][6].replaceAll("\'",
								"&apos;");
					}
					if (products[i][6].indexOf('<') > -1) {
						products[i][6] = products[i][6].replaceAll("\"",
								"&quot;");
					}
				} else {
					products[i][6] = "NoErrorString";
				}

				output.print("		<product contentRefId=\"p" + (i + 1)
						+ "\" xmlns=\"http://als.medieninnovation.com/prod\">"
						+ newLine);
				output.print("			<product_id>" + products[i][0]
						+ "</product_id>" + newLine);
				output.print("			<title>" + products[i][1] + "</title>"
						+ newLine);
				output.print("			<shortDescription>" + products[i][6]
						+ "</shortDescription>" + newLine);
				output.print("			<category>" + newLine);
				output.print("				<name>" + products[i][3] + "</name>"
						+ newLine);
				output.print("			</category>" + newLine);
				output.print("			<image xmlns=\"http://als.medieninnovation.com/content\">"
						+ newLine);
				output.print("				<url>" + products[i][5] + "</url>" + newLine);
				output.print("				<width>140</width>" + newLine);
				output.print("				<height>105</height>" + newLine);
				output.print("				<alternateText>" + products[i][1]
						+ "</alternateText> " + newLine);
				output.print("			</image>" + newLine);
				output.print("			<priceGross>" + products[i][4]
						+ "</priceGross>" + newLine);
				output.print("			<currency>EUR</currency>" + newLine);
				output.print("		</product>" + newLine);

			}
		}

		output.print("	</contentCollection>" + newLine);
		output.print("	<job>" + newLine);
		output.print("		<pageCollection>" + newLine);
		output.print("			<singlePage>" + newLine);
		output.print("				<contentToPageCollection>" + newLine);
		output.print("					<contentToPage>" + newLine);
		output.print("						<contentRef>p1</contentRef>" + newLine);
		output.print("						<weight>0.5</weight>" + newLine);
		output.print("					</contentToPage>" + newLine);
		output.print("					<contentToPage>" + newLine);
		output.print("						<contentRef>p2</contentRef>" + newLine);
		output.print("						<weight>0.5</weight>" + newLine);
		output.print("					</contentToPage>" + newLine);
		output.print("				</contentToPageCollection>" + newLine);
		output.print("				<pageDesign>" + newLine);
		output.print("					<css></css>" + newLine);
		output.print("				</pageDesign>" + newLine);
		output.print("			</singlePage>" + newLine);
		output.print("			<multiPage maxPages=\"0\">" + newLine);
		output.print("				<contentToPageCollection>" + newLine);
		output.print("					<contentToPage>" + newLine);
		output.print("						<contentRef>p1</contentRef>" + newLine);
		output.print("						<weight>0.5</weight>" + newLine);
		output.print("					</contentToPage>" + newLine);
		output.print("					<contentToPage>" + newLine);
		output.print("						<contentRef>p2</contentRef>" + newLine);
		output.print("						<weight>0.5</weight>" + newLine);
		output.print("					</contentToPage>" + newLine);
		output.print("				</contentToPageCollection>" + newLine);
		output.print("				<pageDesign>" + newLine);
		output.print("					<css></css>" + newLine);
		output.print("				</pageDesign>" + newLine);
		output.print("			</multiPage>" + newLine);
		output.print("			<pageDesign>" + newLine);
		output.print("				<css>" + newLine);
		output.print("					page{" + newLine);
		output.print("					ppi: 170;" + newLine);
		output.print("					background-image: url(\"http://twofrugalmommas.com/wp-content/uploads/2011/05/shopping.jpg\");"
				+ newLine);
		output.print("					height:600px;" + newLine);
		output.print("					width:1024px;" + newLine);
		output.print("					padding-top: 20px;" + newLine);
		output.print("					padding-bottom: 33px;" + newLine);
		output.print("					padding-left: 13px;" + newLine);
		output.print("					padding-right: 13px;" + newLine);
		output.print("					}" + newLine);

		output.print("				</css>" + newLine);
		output.print("			</pageDesign>" + newLine);
		output.print("		</pageCollection>" + newLine);
		output.print("		<layoutGeneratorTable />" + newLine);
		output.print("		<outputCollection>" + newLine);
		output.print("		    <!--<outputWebserver outputRefId=\"outputWebserver\" directOutput=\"true\">-->"
				+ newLine);
		output.print("			<!--	<showWaiting>true</showWaiting>-->" + newLine);
		output.print("			<!--	<showSubmittedJob>true</showSubmittedJob>-->"
				+ newLine);
		output.print("			<!--	<addPagination>true</addPagination>-->" + newLine);
		output.print("		   <!--	</outputWebserver>-->" + newLine);
		output.print("			   <!--  <outputXML outputRefId=\"outputXml\" directOutput=\"true\" /> -->"
				+ newLine);
		output.print("			        <outputJSON outputRefId=\"outputJson\" directOutput=\"true\" /> "
				+ newLine);
		output.print("		</outputCollection>" + newLine);
		output.print("	            <senderCollection> " + newLine);
		output.print("		             <senderPost> " + newLine);
		output.print("			     <outputRef>outputJson</outputRef>" + newLine);
		output.print("		             <url>http://client.als.dev.medieninnovation.com/post.php?action=save</url> "
				+ newLine);
		output.print("		<fieldnameJobId>id</fieldnameJobId> " + newLine);
		output.print("		<fieldnameFile>file</fieldnameFile> " + newLine);
		output.print("		<fieldnameError>error</fieldnameError>" + newLine);
		output.print("		 </senderPost> " + newLine);
		output.print("		   <!--   <senderMail> -->" + newLine);
		output.print("		    <!--  <outputRef>outputJson</outputRef> -->"
				+ newLine);
		output.print("		    <!--  <to>chrismasc@web.de</to> -->" + newLine);
		output.print("		     <!-- <from>als@medieninnovation.com</from> -->"
				+ newLine);
		output.print("		     <!-- <subject>New ALS output document</subject> -->"
				+ newLine);
		output.print("		    <!--  <attachment>false</attachment> -->" + newLine);
		output.print("		    <!--  </senderMail> -->" + newLine);
		output.print("		   </senderCollection> " + newLine);
		output.print("		<configuration>" + newLine);
		output.print("			<apiKey>cv6oAh4vDGQYKJwouVYTNnwcVPUdUVK8</apiKey>"
				+ newLine);
		output.print("			<language>de-DE</language>" + newLine);
		output.print("			<minLayouts>10</minLayouts>" + newLine);
		output.print("			<maxRenderingTime>30</maxRenderingTime>" + newLine);
		output.print("		</configuration>" + newLine);
		output.print("	</job>" + newLine);
		output.print("</jobCollection>");

	}

}
