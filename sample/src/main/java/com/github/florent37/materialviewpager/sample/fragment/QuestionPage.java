package com.github.florent37.materialviewpager.sample.fragment;

import java.util.List;
import java.util.Random;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.sample.MainActivity;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.Utilities;
import com.github.florent37.materialviewpager.sample.db.exam;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class QuestionPage extends Fragment
{
	String type=null;
	exam[] question=null;
	boolean[] visited=null;
	exam current;
	int turn=0;
	Random rand;
	boolean Isclickable=true;
	//
	Context contex;
	RadioButton[] opradio=new RadioButton[4];
	RelativeLayout[] op=new RelativeLayout[4];
	TextView text;
	TextView op1text,op2text,op3text,op4text;
	Button btnanswer;
	Button next;
	ImageView help,im;
	int number;
	ScrollView scrollView;
	DbAdapter database;
    private ObservableScrollView mScrollView;

    public static String back_frament;
    public static int level;

    public  MainActivity m;
    public void setActivity(MainActivity n)
    {
        m=n;
    }
    public static QuestionPage newInstance(String s, MainActivity n){
    QuestionPage fragmentDemo = new QuestionPage();
    Bundle args = new Bundle();
    args.putString("query", s);
    fragmentDemo.setActivity(n);
    fragmentDemo.setArguments(args);
    return fragmentDemo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.quest, container, false);
    }




	//
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
		//super.onCreate(savedInstanceState);
		//this.getActivity().setContentView(R.layout.quest);


//        ImageButton backbt= (ImageButton)this.getActivity().findViewById(R.id.back_);
//        if(level==1){
//            backbt.setVisibility(View.VISIBLE);
//
//            backbt.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v){
//
//                    m.initialViewPager(back_frament);
//                }});
//
//        }else{
//            backbt.setVisibility(View.INVISIBLE);
//        }


		contex=this.getActivity();
		try {
			database=new DbAdapter(this.getActivity());
		} catch (Exception e) {
			this.getActivity().finish();
	    	//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		getdata();
		initial();
		showNextQuest();
		//
		help=(ImageView)this.getActivity().findViewById(R.id.helpbtn);
		help.setOnTouchListener(touch);
		Animation anim=(Animation)AnimationUtils.loadAnimation(contex, R.anim.anim1);
		help.startAnimation(anim);
//		Button button=(Button)this.findViewById(R.id.back);
//		button.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				finish();
//		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//			}
//		});

        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
    }
	
	//
	View.OnTouchListener touch=new View.OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			Isclickable=false;
			final Dialog dialog = new Dialog(contex,android.R.style.Theme_Translucent_NoTitleBar);
			dialog.setContentView(R.layout.help);
			// set the custom dialog components - text, image and button
			Typeface face = Typeface.createFromAsset(contex.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_content);
			
			ImageView image=null;
			try{
				image = (ImageView) dialog.findViewById(R.id.closehelpbtn);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			image.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					dialog.dismiss();
					Isclickable=true;
					return false;
				}
			});
			TextView helptxt=(TextView)dialog.findViewById(R.id.helptxt);
			helptxt.setTypeface(face);
			helptxt.setTextColor(Color.BLACK);
			helptxt.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
			helptxt.setText(question[number].getHelp());
			try{
				//dialog.getWindow().getAttributes().windowAnimations = R.style.SearchDialogAnimation;
				dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
					
					@Override
					public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
						if(keyCode==KeyEvent.KEYCODE_BACK)
						{
							dialog.dismiss();
							Isclickable=true;
							return false;
						}
						return false;
					}
				});
				dialog.getWindow().getAttributes().windowAnimations = R.style.SearchDialogAnimation;
				dialog.show();
			}
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
			return false;
		}
	 };
	
	
	
	
	//
	RadioButton.OnClickListener myOptionOnClickListener =
			   new RadioButton.OnClickListener()
			  {
					public void onClick(View arg0) 
					{
						if(!Isclickable)
							return;
						RadioButton r=(RadioButton)arg0;
						for(int i=0;i<opradio.length;i++)
						{
							if(opradio[i]!=r)
								opradio[i].setChecked(false);
						}
						r.setChecked(true);
						 MediaPlayer mPlayer = MediaPlayer.create(contex, R.raw.answer);
						 AudioManager am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
						 try
						 {
							 if(!(am.getRingerMode()==AudioManager.RINGER_MODE_SILENT || 
									 am.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE||
                                     com.github.florent37.materialviewpager.sample.attrib.attribute.IsMute))
								 mPlayer.start();
	                     }catch(Exception e)
	                     {
	                    	 e.printStackTrace();
	                     }
						 DownScroll(0);
					}
			  };
	
	
			  
	//*******************************************		  
			  
			  View.OnClickListener opclick=new View.OnClickListener() {
					@Override
					public void onClick(View v)
					{
						RelativeLayout layout=(RelativeLayout)v;
						RadioButton r=(RadioButton)layout.findViewById(R.id.op1radio);
						for(int i=0;i<opradio.length;i++)
						{
							if(opradio[i]!=r)
								opradio[i].setChecked(false);
						}
						r.setChecked(true);
						MediaPlayer mPlayer = MediaPlayer.create(contex, R.raw.answer);
						 AudioManager am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
						 try
						 {
							 if(!(am.getRingerMode()==AudioManager.RINGER_MODE_SILENT ||
									 am.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE||
                                     com.github.florent37.materialviewpager.sample.attrib.attribute.IsMute))
								 mPlayer.start();
			             }catch(Exception e)
			             {
			            	 e.printStackTrace();
			             }
						 DownScroll(0);
					}
				};
			  
			  
			  
	//*************************************		  
	private void showNextQuest() {
		do{
			number=rand.nextInt(question.length);
		}while(IsVisited(number));
		visited[number]=true;
		current=this.question[number];
		//
		for(int i=0;i<op.length;i++)
		{
			op[i].setBackgroundResource(0);
			opradio[i].setChecked(false);
		}
		//
		if(current.getPic()==null ||current.getPic().length()==0)
		{
			im.setImageResource(R.drawable.trans1);
		}
		else
		{
			try {
				im.setImageBitmap(Utilities.getImage(database.fetchSingle(Integer.parseInt(current.getPic()))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				this.getActivity().finish();
		    	//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		}
		//
		text.setText(current.getQuestion());
		//
		op1text.setText(current.getOpt1());
		//
		op2text.setText(current.getOpt2());
		//
		op3text.setText(current.getOpt3());
		//
		op4text.setText(current.getOpt4());
		//
		DownScroll(1);
		
	}
	
	
	
	private View.OnClickListener nextbtnlistner=new View.OnClickListener() {
		public void onClick(View v) 
		{
			if(!Isclickable)
				return;
			if(IsEnd())
			{
				getActivity().finish();
		    	//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
			else
			{
				showNextQuest();
			}
		}
	};
	
	 

	
	private View.OnClickListener ansbtnlistner=new View.OnClickListener() {
		public void onClick(View v) 
		{
			if(!Isclickable)
				return;
			Thread t=new Thread(btn);
			t.start();
		}
	};
	private boolean IsVisited(int number) {
		return visited[number];
	}
	private boolean IsEnd()
	{
		for(int i=0;i<visited.length;i++)
			if(!visited[i])
				return false;
		return true;
	}
	public void initial()
	{
		scrollView=(ScrollView)this.getActivity().findViewById(R.id.scrollView1);
		op[0]=(RelativeLayout)this.getActivity().findViewById(R.id.op1);
		op[1]=(RelativeLayout)this.getActivity().findViewById(R.id.op2);
		op[2]=(RelativeLayout)this.getActivity().findViewById(R.id.op3);
		op[3]=(RelativeLayout)this.getActivity().findViewById(R.id.op4);
		//
		im=(ImageView)this.getActivity().findViewById(R.id.questimage);
		
		//
		rand=new Random(System.currentTimeMillis());
		text=(TextView)this.getActivity().findViewById(R.id.questtext);
		Typeface titleface = Typeface.createFromAsset(this.getActivity().getApplicationContext().getAssets(),
				"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
		text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
		text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_color);
		text.setTypeface(titleface);
		//
		Typeface face = Typeface.createFromAsset(this.getActivity().getApplicationContext().getAssets(),
				"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_content);
		op1text=(TextView)op[0].findViewById(R.id.op1text);
		op1text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
		op1text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
		op1text.setTypeface(face);
		//
		op2text=(TextView)op[1].findViewById(R.id.op1text);
		op2text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
		op2text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
		op2text.setTypeface(face);
		//
		op3text=(TextView)op[2].findViewById(R.id.op1text);
		op3text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
		op3text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
		op3text.setTypeface(face);
		//
		op4text=(TextView)op[3].findViewById(R.id.op1text);
		op4text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
		op4text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
		op4text.setTypeface(face);
		//
		btnanswer=(Button)this.getActivity().findViewById(R.id.btnanswer);
		//
		next=(Button)this.getActivity().findViewById(R.id.btnnext);
		//
		//
		opradio[0]=(RadioButton)op[0].findViewById(R.id.op1radio);
		opradio[1]=(RadioButton)op[1].findViewById(R.id.op1radio);
		opradio[2]=(RadioButton)op[2].findViewById(R.id.op1radio);
		opradio[3]=(RadioButton)op[3].findViewById(R.id.op1radio);
		for(int i=0;i<4;i++)
			opradio[i].setOnClickListener(myOptionOnClickListener);
		
		//
		btnanswer.setOnClickListener(ansbtnlistner);
		next.setOnClickListener(nextbtnlistner);
		for(int i=0;i<visited.length;i++)
			visited[i]=false;
	}
	private void getdata() {
		this.type=getArguments().getString("query");
		List<List<Object>> exams=null;
		try {
			exams = database.selectRecordsFromDBList(type);
		} catch (Exception e) {
			this.getActivity().finish();
	    	//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		
		this.question=new exam[exams.size()];
		this.visited=new boolean[exams.size()];
		for(int i=0;i<this.question.length;i++)
		{
			this.question[i]=new exam((String)exams.get(i).get(0), (String)exams.get(i).get(1), (String)exams.get(i).get(2), (String)exams.get(i).get(3)
					, (String)exams.get(i).get(4), (String)exams.get(i).get(5), (String)exams.get(i).get(6), (String)exams.get(i).get(7)
					, (String)exams.get(i).get(8), (String)exams.get(i).get(9), (String)exams.get(i).get(10),(String)exams.get(i).get(11));
		}
	}

	Runnable btn=new Runnable() 
	{
		
		public void run() 
		{
			setstatuse();
		}
	};
	private void setstatuse()
	{
		this.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                int i;
                for (i = 0; i < opradio.length && !opradio[i].isChecked(); i++) ;

                if (i < opradio.length && current.getRightOpt() == String.valueOf(i))
                    op[i].setBackgroundResource(R.drawable.correctans);
                else if (i < opradio.length) {
                    op[i].setBackgroundResource(R.drawable.wrongans);
                    opradio[i].setChecked(false);
                    //
                    op[Integer.valueOf(current.getRightOpt()) - 1].setBackgroundResource(R.drawable.correctans);
                    opradio[Integer.valueOf(current.getRightOpt()) - 1].setChecked(true);
                } else {
                    op[Integer.valueOf(current.getRightOpt()) - 1].setBackgroundResource(R.drawable.correctans);
                    opradio[Integer.valueOf(current.getRightOpt()) - 1].setChecked(true);
                }
            }
        });
		SystemClock.sleep(1000);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event)
	 {
		    //Handle the back button
		    if( keyCode == KeyEvent.KEYCODE_BACK ) {
		        //Ask the user if they want to quit
		    	//Intent i=new Intent(Sign.this, DrivingLowActivity.class);
		    	//startActivity(i);
		    	this.getActivity().finish();
		    	//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		        return true;
		    }
		    else {
		        return super.getActivity().onKeyDown(keyCode, event);
		    }
		}
	//
	private void DownScroll(final int s){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {Thread.sleep(100);} catch (InterruptedException e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    	if(s==0)
                    		scrollView.fullScroll(View.FOCUS_DOWN);
                    	else
                    		 scrollView.fullScroll(View.FOCUS_UP);	
                    }
                });
            }
        }).start();
    }
}
