package com.github.florent37.materialviewpager.sample;


import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;

import com.github.florent37.materialviewpager.sample.xml.XMLParser;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LearnSchool extends Activity 
{
	ListView listView;
	Context context;
	static final String KEY_ITEM = "row"; // parent node
    String id = "";
    School[] Values=null;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//
		id=getIntent().getStringExtra("id");
		context=this;
		setListViewContent();
		TextView textView = (TextView) findViewById(R.id.textView1);
		textView.setText( "آموزشگاه های رانندگی");
		Typeface face = Typeface.createFromAsset(this.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
		textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_size);
		textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_color);
		textView.setTypeface(face);
		//
		listView.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View v,int position, long idx) 
			{
				ScaleAnimation scale = new ScaleAnimation(1.1f, 0.9f, 1.1f, 0.9f, 
			        	ScaleAnimation.RELATIVE_TO_SELF, 1f, 
			            ScaleAnimation.RELATIVE_TO_SELF, 1f);
			    scale.setDuration(300);
			    scale.setRepeatCount(0);
				//
				v.startAnimation(scale);
				//
				String con;
				if(Integer.parseInt(id)!=0)
					con="\n"+ Values[position].amozeshgah+"\n"+Values[position].ostan+" "+Values[position].shahr+" "+Values[position].address+
						"\n\n تلفن:"+Values[position].tel;
				else
					con="\n"+ Values[position].amozeshgah+"\n"+Values[position].ostan+" منطقه "+Values[position].shahr+" "+Values[position].address+
					"\n\n تلفن:"+Values[position].tel;
				Intent i=new Intent(LearnSchool.this, Detail.class);
				i.putExtra("content", con);
				startActivity(i);
				overridePendingTransition(R.anim.rotate3d_in_right,R.anim.rotate3d_out_left);
				//	
				
				
			}
		});
		//
		Button button=(Button)this.findViewById(R.id.back);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		});

	}
	
	//
	public void setListViewContent()
	{
			this.setContentView(R.layout.list4);
			listView = (ListView) findViewById(R.id.items);
			try{
				 Values=getdata();
			}catch(Exception e)
			{
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
			listView.setAdapter(new SchoolListAdapter(this, Values));
	}

	private School[] getdata() throws NotFoundException, IOException {
		School[] output=null;
		ArrayList<HashMap<String, String>> items= new ArrayList<HashMap<String, String>>();
	    XMLParser parser=new XMLParser();
	    InputStream is = context.getResources().openRawResource(R.raw.m101+Integer.parseInt(id))  ;
	    if(Integer.parseInt(id)!=0)
	    {
	    	String[] s={"radif","ostan","shahr","amozeshgah","address","tel"};
	    	items= parser.getDomElement(is,s);
	    }
	    else
	    {
	    	String[] s={"radif","ostan","amozeshgah","mantagheh","address","tel"};
	    	items= parser.getDomElement(is,s);
	    }
	    //Document docs = parser.getDomElement(xml); // getting DOM element
		//NodeList nl = docs.getElementsByTagName(KEY_ITEM);
	    
		try{
			output=new School[items.size()];
			int i=0;
			for(HashMap<String, String> h : items)
			{
				if(Integer.parseInt(id)!=0)
			    {
					output[i++]= new School(h.get("radif"), h.get("ostan"), h.get("shahr"), h.get("amozeshgah"), h.get("address"), h.get("tel")) ;
			    }
				else
				{
					output[i++]= new School(h.get("radif"), h.get("ostan"), h.get("mantagheh"), h.get("amozeshgah"), h.get("address"), h.get("tel")) ;
				}
			}
			}catch(Exception e)
			{
				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
		return output;
	}
	public boolean onKeyDown(int keyCode, KeyEvent event)
	 {
		    //Handle the back button
		    if( keyCode == KeyEvent.KEYCODE_BACK ) {
		        //Ask the user if they want to quit
		    	//Intent i=new Intent(Sign.this, DrivingLowActivity.class);
		    	//startActivity(i);
		    	finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		        return true;
		    }
		    else {
		        return super.onKeyDown(keyCode, event);
		    }
		}
	
	//
	class School
	{
		String radif,ostan,shahr,amozeshgah,address,tel;
		public School(String r,String o, String s,String a,String ad,String tel)
		{
			this.radif=r;this.ostan=o;this.shahr =s;this.amozeshgah=a;
			this.address=ad;this.tel=tel;
		}
	}
	//
	public class SchoolListAdapter extends BaseAdapter 
	{
		private Context context;
		private final School[] Values;
		

		public SchoolListAdapter(Context context, School[] Values) {
			this.context = context;
			this.Values = Values;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View gridView;

			if (convertView == null) {

				gridView = new View(context);

				// get layout from mobile.xml
				gridView = inflater.inflate(R.layout.searchrow, null);
				//
				/*
				String mobile = Values[position];
				if (mobile.equals("Windows")) {
					imageView.setImageResource(R.drawable.windows_logo);
				}
				else if (mobile.equals("iOS")) {
					imageView.setImageResource(R.drawable.ios_logo);
				}
				else if (mobile.equals("Blackberry")) {
					imageView.setImageResource(R.drawable.blackberry_logo);
				}
				else {
					imageView.setImageResource(R.drawable.android_logo);
				}
				*/
				// set value into textview
				

			} else {
				gridView = convertView;
			}
			TextView textView = (TextView) gridView
					.findViewById(R.id.rowtitle);
			textView.setText(Values[position].amozeshgah);
			Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
			textView.setTypeface(face);
			textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_size);
			textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_color);
			//
			TextView textViewsub = (TextView) gridView
					.findViewById(R.id.rowsub);
			String str=Values[position].ostan+" "+Values[position].shahr+" "+Values[position].address;
			String substr=str.length()<40?str:str.substring(0, 40);
			textViewsub.setText(substr);
			textViewsub.setTypeface(face);
			textViewsub.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
			textViewsub.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
			
			return gridView;
		}

	
		public int getCount() {
			return Values.length;
		}

		
		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}
	}
}
//

