package com.github.florent37.materialviewpager.sample;


import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.Utilities;
import com.github.florent37.materialviewpager.sample.db.learn;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Page extends Activity 
{
	RelativeLayout relative;
	private String Catid;
	Context contex;
	TableLayout table;
	DbAdapter db;
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		contex=this;
		try {
			db=new DbAdapter(this);
		} catch (Exception e1) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		Catid=getIntent().getStringExtra("query");
		try{
		setscreen();
		Button button=(Button)this.findViewById(R.id.back);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		});

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private learn getRow() {
		
		int id=Integer.parseInt(Catid);
		learn l=null;
		try {
			l = db.getlearn(id);
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		return l;
	}
	//
	public void setscreen()
	{
		setContentView(R.layout.contentpage);
		learn l=getRow();
		TextView title=(TextView)this.findViewById(R.id.titletext);
		table=(TableLayout)this.findViewById(R.id.tableLayout1);
		title.setText(l.getTitle());
		Typeface face = Typeface.createFromAsset(contex.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
		title.setTypeface(face);
		//textView.setWidth(imageView.getWidth());
		title.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
		title.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_color);
		int index1=0,index2=0;
		//int f=0;
		
		while((index2=l.getText().indexOf("IMG", index1))!=-1)
		{
			
			String sub=l.getText().substring(index1, index2);
			int k=0;
			for(k=index2+3;k<l.getText().length()&&l.getText().charAt(k)>47 &&l.getText().charAt(k)<58 ;k++);
			String id=l.getText().substring(index2+3,k);
			Bitmap b=null;
			try {
				b = Utilities.getImage(db.fetchSingle(Integer.parseInt(id)));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
			index1=k;
			String[] rows=sub.split("\n");
			LayoutInflater inflate=getLayoutInflater();
			for(int i=0;i<rows.length;i++)
			{
				if(rows[i].length()==0)
					continue;
				TableRow t=(TableRow)inflate.inflate(R.layout.text, table, false);
				TextView text=(TextView)t.findViewById(R.id.textView1);
				//
				face = Typeface.createFromAsset(contex.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
				text.setTypeface(face);
				int temp=0;
				temp=rows[i].indexOf("FONT");
				if(temp==-1)
					text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
				else
				{
					text.setTextSize(Integer.valueOf(rows[i].substring(temp+4, temp+6)));
					rows[i]=rows[i].substring(0, temp);
				}
				temp=rows[i].indexOf("COL");
				if(temp==-1)
					text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
				else
				{
					String color=rows[i].substring(temp+3, temp+11);
					text.setTextColor(Color.parseColor("#"+color));
					rows[i]=rows[i].substring(0, temp);
				}
				
				text.setText(rows[i]);
				//
				table.addView(t);
			}
			TableRow t=(TableRow)inflate.inflate(R.layout.image, table, false);
			ImageView image=(ImageView)t.findViewById(R.id.imageView1);
			if(b!=null)
				image.setImageBitmap(b);
			else
				image.setImageResource(0);
			
			//int w=b.getWidth();
			//int h=b.getHeight();
			//System.out.println("width="+w+"height="+h);
			table.addView(t);
		}
		if(index2+5<l.getText().length())
		{
			String sub=l.getText().substring(index1, l.getText().length());
			String[] rows=sub.split("\n");
			LayoutInflater inflate=getLayoutInflater();
			for(int i=0;i<rows.length;i++)
			{
				if(rows[i].length()==0)
					continue;
				//TableRow v=(TableRow)(new Row(contex).getView( rows[i]));
				TableRow t=(TableRow)inflate.inflate(R.layout.text, table, false);
				TextView text=(TextView)t.findViewById(R.id.textView1);
				//
				face = Typeface.createFromAsset(contex.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
				text.setTypeface(face);
				int temp=0;
				temp=rows[i].indexOf("FONT");
				if(temp==-1)
					text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
				else
				{
					text.setTextSize(Integer.valueOf(rows[i].substring(temp+4, temp+6)));
					rows[i]=rows[i].substring(0, temp);
				}
				temp=rows[i].indexOf("COL");
				if(temp==-1)
					text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
				else
				{
					String color=rows[i].substring(temp+3, temp+11);
					text.setTextColor(Color.parseColor("#"+color));
					rows[i]=rows[i].substring(0, temp);
				}
				//
				text.setText(rows[i]);
				
				table.addView(t);
			}
		}
		
	}
	//
	public boolean onKeyDown(int keyCode, KeyEvent event)
	 {
		    //Handle the back button
		    if( keyCode == KeyEvent.KEYCODE_BACK ) {
		        //Ask the user if they want to quit
		    	//Intent i=new Intent(Sign.this, DrivingLowActivity.class);
		    	//startActivity(i);
		    	finish();
		    	overridePendingTransition(R.anim.rotate3d_in_left,R.anim.rotate3d_out_right);
		        return true;
		    }
		    else {
		        return super.onKeyDown(keyCode, event);
		    }
		}
	
	
	
	
	
	class Row extends TableRow
	{

		Context con;
		ImageView image;
		TextView textView;
		public Row(Context context) {
			super(context);
			con=context;
		}
		//
		public RelativeLayout getView(String text)
		{
			
			View v;
			v=LayoutInflater.from(con).inflate(R.layout.text, this,true);
			RelativeLayout rel=(RelativeLayout)v.findViewById(R.id.relativeLayout1);
			textView = (TextView) rel
					.findViewById(R.id.textView1);
			Typeface face = Typeface.createFromAsset(con.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
			textView.setTypeface(face);
			textView.setText(text);
			//textView.setWidth(imageView.getWidth());
			textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
			textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
			return rel;
		}
	
	}
}
