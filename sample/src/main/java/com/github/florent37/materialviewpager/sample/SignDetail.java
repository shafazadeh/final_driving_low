package com.github.florent37.materialviewpager.sample;

import java.util.List;

import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.sign;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;



public class SignDetail extends Activity 
{
	public static String CatId;
	public static String ConId;
	//
	Context contex;
	private GestureDetector gestureDetector;
    RelativeLayout rel,arrowrel;
    ScrollView scv;
    public static SignDetail signdetail;
    public SignDetail signpage=null;
    //
    public static boolean canmoveright=true,canmoveleft=true;
    DbAdapter database;
	private sign[] getdata() 
		{
			List<List<Object>> signs=null;
			try {
				signs = database.selectRecordsFromDBList(CatId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sign[] ret=new sign[signs.size()];
			for(int i=0;i<ret.length;i++)
			{
				ret[i]=new sign((String)signs.get(i).get(0), (String)signs.get(i).get(1), (String)signs.get(i).get(2), (String)signs.get(i).get(3));
			}
			return ret;
		}
		//
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);
		 try {
			database=new DbAdapter(this);
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		if(signdetail!=null)
			signdetail.finish();
		signpage=this;
		canmoveleft=canmoveright=true;
		contex=this;
		
		setscreen();
		gestureDetector = new GestureDetector(new MyGestureDetector());
		rel=(RelativeLayout)findViewById(R.id.pagelayout);
		arrowrel=(RelativeLayout)findViewById(R.id.arrowlayout);
		scv=(ScrollView)findViewById(R.id.scrollView1);
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
		//
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
				return false;
			}
		});
		//
		scv.setOnTouchListener(new View .OnTouchListener() {
		
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
				return false;
			}
		});
		//
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
		SignDetail.CatId=getIntent().getStringExtra("catid");
		SignDetail.ConId=getIntent().getStringExtra("id");
		sign temp_sign[]=getdata();
		//
		if(Integer.valueOf( SignDetail.ConId)>=temp_sign.length-1)
		{
			SignDetail.ConId=String.valueOf(temp_sign.length-1);
			canmoveright=false;
		}
		else if(Integer.valueOf( SignDetail.ConId)<=0)
		{
			SignDetail.ConId=String.valueOf(0);
			canmoveleft=false;
		}
		//
		ImageView im=(ImageView)findViewById(R.id.imageView1);
		//im.setImageResource(R.drawable.windows_logo);
		
		try {
			im.setImageBitmap(com.github.florent37.materialviewpager.sample.db.Utilities.getImage(database.fetchSingle(Integer.parseInt(temp_sign[Integer.valueOf( SignDetail.ConId)].getpic()))));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		AnimationSet set = new		// set image based on selected text
				AnimationSet(true);
				Animation animation = new AlphaAnimation(0.0f, 1.0f);
				animation.setDuration(300);
				set.addAnimation(animation);
				ScaleAnimation scale = new ScaleAnimation(0.8f, 1.2f, 0.8f, 1.2f, 
			        	ScaleAnimation.RELATIVE_TO_SELF, 0.5f, 
			            ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
			    scale.setDuration(700);
			    set.addAnimation(scale);
			    ScaleAnimation scale1 = new ScaleAnimation(1.2f, 1f, 1.2f, 1f, 
			        	ScaleAnimation.RELATIVE_TO_SELF, 0.5f, 
			            ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
			    scale1.setDuration(350);
			    set.addAnimation(scale1);
		im.startAnimation(scale);
		TextView te=(TextView)findViewById(R.id.textView1);
		te.setText(temp_sign[Integer.valueOf( SignDetail.ConId)].getName());
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
	    Intent intent = new Intent(SignDetail.this.getBaseContext(), SignDetail.class);
	    intent.putExtra("catid", String.valueOf(SignDetail.CatId));
	    
            if (Math.abs(e1.getY() - e2.getY()) >com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_MAX_OFF_PATH) {
                return false;
            }
 
            // right to left swipe
            if(e1.getX() - e2.getX() >com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_THRESHOLD_VELOCITY&&SignDetail.canmoveright) {
            	intent.putExtra("id", String.valueOf(Integer.parseInt(SignDetail.ConId)+1));
            	signdetail=signpage;
            	startActivity(intent);
            	SignDetail.this.overridePendingTransition(
            			R.anim.slide_in_right,R.anim.slide_out_left
            			);
            			// right to left swipe
            }  else if (e2.getX() - e1.getX() > com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_MIN_DISTANCE && Math.abs(velocityX) > com.github.florent37.materialviewpager.sample.attrib.attribute.SWIPE_THRESHOLD_VELOCITY&&SignDetail.canmoveleft) {
            	intent.putExtra("id", String.valueOf(Integer.parseInt(SignDetail.ConId)-1));
            	signdetail=signpage;
            	startActivity(intent);
            	SignDetail.this.overridePendingTransition(
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
