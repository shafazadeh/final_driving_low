package com.github.florent37.materialviewpager.sample.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLParser {

	// constructor
	public XMLParser() {

	}

	/**
	 * Getting XML from URL making HTTP request
	 * @param url string
	 * @throws java.io.IOException
	 * */
	public String getXmlFromUrl(String url)  {
		String xml = null;

		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
			HttpPost httpPost = new HttpPost(url);
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			HttpEntity httpEntity = httpResponse.getEntity();
			
			xml = EntityUtils.toString(httpEntity,"utf-8");

		} catch (UnsupportedEncodingException e) {
		
		} catch (ClientProtocolException e) {
			
		} catch (IOException e) {
			
		}
		// return XML
		return xml;
	}
	
	/**
	 * Getting XML DOM element
	 * @param XML string
	 * */
	public ArrayList<HashMap<String, String>> getDomElement(InputStream is,String[] node){
		//Document doc = null;
		ArrayList<HashMap<String, String>> items=new ArrayList<HashMap<String,String>>();
		XmlPullParser xmlpullparser=null;
		 XmlPullParserFactory factory = null;  
         try {  
             factory = XmlPullParserFactory.newInstance();  
         } catch (XmlPullParserException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  
         factory.setNamespaceAware(true);  
         try {  
             xmlpullparser = factory.newPullParser();  
         } catch (XmlPullParserException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  
   
   
         try {  
             xmlpullparser.setInput(is, "UTF-8");  
         } catch (XmlPullParserException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
         }  
       
         try {
        	 	int i=0;
        	 	 HashMap<String, String> map=new HashMap<String, String>();
				while (xmlpullparser.getEventType()!=XmlPullParser.END_DOCUMENT)
				{	
				    	while(xmlpullparser.getName()==null || !xmlpullparser.getName().equals(node[i]))
				    	{
				    		if(xmlpullparser.getEventType()==XmlPullParser.END_DOCUMENT)
				    			break;
				    		xmlpullparser.next();
				    	}
				    	xmlpullparser.next();
				    	if(xmlpullparser.getEventType()==XmlPullParser.TEXT && !xmlpullparser.getText().contains("\n"))
				    	{ 
				    		  map.put(node[i], xmlpullparser.getText());
				        }
				    	i++;
				    	if(i==node.length)
				    	{
				    		i=0;
				    		if(map.size()>0)
				    		{
				    			items.add(map);
				    			map=new HashMap<String, String>();
				    		}
				    	}
				    xmlpullparser.next();
				}
				
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return items;
	}
	
	/** Getting node value
	  * @param elem element
	  */
	 public final String getElementValue( Node elem ) {
	     Node child;
	     if( elem != null){
	         if (elem.hasChildNodes()){
	             for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
	                 if( child.getNodeType() == Node.TEXT_NODE  ){
	                     return child.getNodeValue();
	                 }
	             }
	         }
	     }
	     return "";
	 }
	 
	 /**
	  * Getting node value
	  * @param Element node
	  * @param key string
	  * */
	 public String getValue(Element item, String str) {		
			NodeList n = item.getElementsByTagName(str);		
			return this.getElementValue(n.item(0));
		}
}
