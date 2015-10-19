package com.github.florent37.materialviewpager.sample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Detail extends Activity {

	//
	Context contex;
    RelativeLayout rel,arrowrel;
    String cont;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);
		contex=this;
		setscreen();
		rel=(RelativeLayout)findViewById(R.id.pagelayout);
		arrowrel=(RelativeLayout)findViewById(R.id.arrowlayout);
		arrowrel.setVisibility(RelativeLayout.GONE);
		
		//
		
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
	public void setscreen()
	{
		this.cont=getIntent().getStringExtra("content");
		//
		
		//
		ImageView im=(ImageView)findViewById(R.id.imageView1);
		//im.setImageResource(R.drawable.windows_logo);
		
		im.setImageResource(R.drawable.trans1);
		TextView te=(TextView)findViewById(R.id.textView1);
		te.setText(cont);
		Typeface face = Typeface.createFromAsset(getAssets(), "font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_content);
		te.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
		te.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
		te.setTypeface(face);
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
}
