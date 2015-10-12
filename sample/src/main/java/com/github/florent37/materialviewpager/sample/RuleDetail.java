package com.github.florent37.materialviewpager.sample;


import java.util.List;

import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.Utilities;
import com.github.florent37.materialviewpager.sample.db.rule;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class RuleDetail extends Activity 
{
	public static String CatId;
	public static String ConId;
	//
	
    private GestureDetector gestureDetector;
    RelativeLayout rel,arrowrel;
    public static RuleDetail reuledetail;
    public RuleDetail rulepage=null;
    Context contex;
    DbAdapter database;
    public static boolean canmoveright=true,canmoveleft=true;
    //
	private rule[] getdata() 
	{
		List<List<Object>> rules=null;
		try {
			rules = database.selectRecordsFromDBList(CatId);
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		rule[] ret=new rule[rules.size()];
		for(int i=0;i<ret.length;i++)
		{
			ret[i]=new rule((String)rules.get(i).get(0), (String)rules.get(i).get(1), (String)rules.get(i).get(2), (String)rules.get(i).get(3), (String)rules.get(i).get(4));
		}
		return ret;
	}
	//
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);
		contex=this;
		try {
			database=new DbAdapter(this);
		} catch (Exception e) {
			finish();
			overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		if(reuledetail!=null)
			reuledetail.finish();
		rulepage=this;
		canmoveleft=canmoveright=true;
		setscreen();
		gestureDetector = new GestureDetector(new MyGestureDetector());
		rel=(RelativeLayout)findViewById(R.id.pagelayout);
		arrowrel=(RelativeLayout)findViewById(R.id.arrowlayout);
		final AnimationSet a=(AnimationSet)AnimationUtils.loadAnimation(contex, R.anim.alphaset);
		a.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
					
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
			}
		});
		rel.setOnTouchListener(new View .OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				runOnUiThread( new Runnable() {
					public void run() {
						arrowrel.setVisibility(View.VISIBLE);
					}
				});
				arrowrel.startAnimation(a);
				if (gestureDetector.onTouchEvent(event)) {
		             return true;
		         }
				arrowrel.setVisibility(View.GONE);
				rel.invalidate();
				return false;
			}
		});
	
		Button button=(Button)this.findViewById(R.id.back);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		});

	}
	//
	public void setscreen()
	{
		RuleDetail.CatId=getIntent().getStringExtra("catid");
		RuleDetail.ConId=getIntent().getStringExtra("id");
		
		rule temp_rule[]=getdata();
		if(Integer.valueOf( RuleDetail.ConId)>=temp_rule.length-1)
		{
			RuleDetail.ConId=String.valueOf(temp_rule.length-1);
			canmoveright=false;
		}
		else if(Integer.valueOf( RuleDetail.ConId)<=0)
		{
			RuleDetail.ConId=String.valueOf(0);
			canmoveleft=false;
		}
		//
		ImageView im=(ImageView)findViewById(R.id.imageView1);
		if(temp_rule[Integer.valueOf( RuleDetail.ConId)].getpic()==null)
		{
			im.setImageResource(0);
		}
		else
		{
			try {
				im.setImageBitmap(Utilities.getImage(database.fetchSingle(Integer.parseInt(temp_rule[Integer.valueOf( RuleDetail.ConId)].getpic()))));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		}
		
		TextView te=(TextView)findViewById(R.id.textView1);
		te.setText(temp_rule[Integer.valueOf( RuleDetail.ConId)].getContent());
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
	//
	
	//
	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	    Intent intent = new Intent(RuleDetail.this.getBaseContext(), RuleDetail.class);
	    intent.putExtra("catid", String.valueOf(RuleDetail.CatId));
	    
            if (Math.abs(e1.getY() - e2.getY()) >com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_MAX_OFF_PATH) {
                return false;
            }
 
            // right to left swipe
            if(e1.getX() - e2.getX() > com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_MIN_DISTANCE && Math.abs(velocityX) >com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_THRESHOLD_VELOCITY&&RuleDetail.canmoveright) {
            	intent.putExtra("id", String.valueOf(Integer.parseInt(RuleDetail.ConId)+1));
            	reuledetail=rulepage;
            	startActivity(intent);
            	RuleDetail.this.overridePendingTransition(
            			R.anim.slide_in_right,R.anim.slide_out_left
            			);
            			// right to left swipe
            }  else if (e2.getX() - e1.getX() > com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_MIN_DISTANCE && Math.abs(velocityX) >com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_THRESHOLD_VELOCITY&&RuleDetail.canmoveleft) {
            	intent.putExtra("id", String.valueOf(Integer.parseInt(RuleDetail.ConId)-1));
            	reuledetail=rulepage;
            	startActivity(intent);
            	RuleDetail.this.overridePendingTransition(
            			R.anim.slide_in_left,R.anim.slide_out_right
    		);
            }
 
            return false;
        }
 
        // It is necessary to return true from onDown for the onFling event to register
        @Override
        public boolean onDown(MotionEvent e) {
	        	return true;
        }
    }
	
}
