package com.github.florent37.materialviewpager.sample.fragment;

import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.db.images;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class SliderItem extends RelativeLayout
{

	public ImageButton button;
	public TextView textView;
	Context con;
	images img;
	
	public SliderItem(Context context) {
		super(context);
		con=context;
	}
	//
	public SliderItem(Context context,images i) {
		super(context);
		con=context;
		img=i;
	}
	//
	
	public View getView(Bitmap b,String text)
	{
		
		View v;
		v=LayoutInflater.from(con).inflate(R.layout.slider_item, this,true);
		button=(ImageButton)v.findViewById(R.id.imageButton1);
		button.setImageBitmap(b);
		button.setBackgroundResource(0);
		
		
		
		
		textView = (TextView) v
				.findViewById(R.id.textView1);
		Typeface face = Typeface.createFromAsset(con.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
		textView.setTypeface(face);
		textView.setText(text);
		//textView.setWidth(imageView.getWidth());
		textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
		textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_color);
		return v;
	}
	
	//
	
}


