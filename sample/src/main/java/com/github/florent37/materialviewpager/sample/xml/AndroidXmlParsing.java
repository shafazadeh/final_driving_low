package com.github.florent37.materialviewpager.sample.xml;

import java.io.BufferedReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.florent37.materialviewpager.sample.R;
import android.content.Context;


public class AndroidXmlParsing
{
	 private Context contex;
	// All static variables
		static final String URL = "http://farinidea.com/update/mobile/DrivingLow/document.xml";
		// XML node keys
		static final String KEY_ITEM = "item"; // parent node
		static final String KEY_NAME = "name";
		//
		ArrayList<HashMap<String, String>> menuItems;
	//
	public AndroidXmlParsing(Context c)throws Exception {
		this.contex=c;
		//menuItems = new ArrayList<HashMap<String, String>>();
		String xml=null;
		XMLParser parser=null;
		try{
			parser = new XMLParser();
			xml = parser.getXmlFromUrl(URL); // getting XML
			if(xml==null)
			{
				xml=loadDoc();
			}
		}catch(Exception e)
		{
			xml=loadDoc();
		}
		InputStream is =  new ByteArrayInputStream(xml.getBytes("UTF-8"));
		String[] s={"name"};
		menuItems= parser.getDomElement(is,s);
	}
	
	public ArrayList<HashMap<String, String>> getMenuItems() {
		return menuItems;
	}
	//
	public String loadDoc() throws IOException {
	    String doc="";
	    String sep = System.getProperty("line.separator");
	    InputStreamReader reader = new InputStreamReader(contex.getResources().openRawResource(R.raw.document), "UTF-8");
	    BufferedReader br = new BufferedReader(reader); 
	    String line = br.readLine();
	    while (line != null) {
	        doc+=line;
	        doc+=sep;
	        line = br.readLine();
	    }
	    if(doc=="")
	    	return null;
	    return doc;
	}
}
