package com.github.florent37.materialviewpager.sample.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.florent37.materialviewpager.sample.Document;
import com.github.florent37.materialviewpager.sample.MainActivity;
import com.github.florent37.materialviewpager.sample.Ostan;
import com.github.florent37.materialviewpager.sample.Pelak;
import com.github.florent37.materialviewpager.sample.Police;
import com.github.florent37.materialviewpager.sample.R;

public class Attach extends Fragment
{
    public  MainActivity m;
    public void setActivity(MainActivity n)
    {
        m=n;
    }
    public static Attach newInstance(  MainActivity n) {
        Attach fragmentDemo = new Attach();

        fragmentDemo.setActivity(n);

        return fragmentDemo;
    }



    ImageButton im1,im2,im3,im4;
	Animation anim1,anim2,anim3,anim4;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.attach, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.attach);
		im1=(ImageButton)this.getActivity().findViewById(R.id.imageButton1);
		im2=(ImageButton)this.getActivity().findViewById(R.id.imageButton2);
		im3=(ImageButton)this.getActivity().findViewById(R.id.imageButton3);
		im4=(ImageButton)this.getActivity().findViewById(R.id.imageButton4);
		
		//
		im1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                com.github.florent37.materialviewpager.sample.Rotate.Flip3d.applyRotation(0, 360,v);
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
                        m.initialViewPager("ostan");
//						Intent i=new Intent(Attach.this.getActivity(),Ostan.class);
//						i.putExtra("next", "school");
//						startActivity(i);
//						getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
					}
				}, 950);
				
			}
		});
		//
		im2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                com.github.florent37.materialviewpager.sample.Rotate.Flip3d.applyRotation(0, 360,v);
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
                        m.initialViewPager("pelak");
//						Intent i=new Intent(Attach.this.getActivity(),Pelak.class);
//						startActivity(i);
//						getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
					}
				}, 950);
				
			}
		});
		//
		//
		im3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                com.github.florent37.materialviewpager.sample.Rotate.Flip3d.applyRotation(0, 360,v);
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
//						Intent i=new Intent(Attach.this.getActivity(),Document.class);
//						startActivity(i);
//						getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        m.initialViewPager("document");
					}
				}, 950);
				
			}
		});
		//
		//
		im4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
                com.github.florent37.materialviewpager.sample.Rotate.Flip3d.applyRotation(0, 360,v);
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
                        m.initialViewPager("police");
//						Intent i=new Intent(Attach.this.getActivity(),MainActivity.class);
//                        i.putExtra("query","police");
//						startActivity(i);
//						getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
					}
				}, 950);
				
			}
		});
		//
		anim1=(Animation)AnimationUtils.loadAnimation(this.getActivity(), R.anim.anim1);
		anim2=(Animation)AnimationUtils.loadAnimation(this.getActivity(), R.anim.anim2);
		anim3=(Animation)AnimationUtils.loadAnimation(this.getActivity(), R.anim.anim3);
		anim4=(Animation)AnimationUtils.loadAnimation(this.getActivity(), R.anim.anim4);
		//
		im1.startAnimation(anim1);
		im1.setVisibility(View.VISIBLE);
		anim1.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				im2.setVisibility(View.VISIBLE);
				im2.startAnimation(anim2);
			}
		});
		anim2.setAnimationListener(new Animation.AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				im3.setVisibility(View.VISIBLE);
				im3.startAnimation(anim3);
			}
		});
		anim3.setAnimationListener(new Animation.AnimationListener() {
	
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
		
			}
	
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				im4.setVisibility(View.VISIBLE);
				im4.startAnimation(anim4);
			}
		});
		anim4.setAnimationListener(new Animation.AnimationListener() {
	
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
		
			}
	
			@Override
			public void onAnimationEnd(Animation animation) {
				
				
			}
		});

		

	
		
		
	}
	
	
	
	//
//	public boolean onKeyDown(int keyCode, KeyEvent event)
//	 {
//		    //Handle the back button
//		    if( keyCode == KeyEvent.KEYCODE_BACK ) {
//		        //Ask the user if they want to quit
//		    	//Intent i=new Intent(Sign.this, DrivingLowActivity.class);
//		    	//startActivity(i);
//		    	getActivity().finish();
//		    	getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//		        return true;
//		    }
//		    else {
//		        return super.onKeyDown(keyCode, event);
//		    }
//	 }
	
}
