package com.github.florent37.materialviewpager.sample;

import java.util.List;
import java.util.Random;

import com.github.florent37.materialviewpager.sample.attrib.attribute;
import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.Utilities;
import com.github.florent37.materialviewpager.sample.db.archive;
import com.github.florent37.materialviewpager.sample.db.exam;
import com.github.florent37.materialviewpager.sample.db.history;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ExamPage extends Activity 
{
	private long Time=attribute.exam_time_min*60*1000;
	private long Start;
	public static int remain;
	static boolean Enable=false;
	exam[] question=null;
	archive[] result=new archive[attribute.exam_question_number];
	int[] order=new int[attribute.exam_question_number];
	boolean[] visited=null;
	exam current;
	int turn=0;
	Random rand;
	int exam_id;
	//
	RadioButton[] opradio=new RadioButton[4];
	RelativeLayout[] op=new RelativeLayout[4];
	TextView text;
	TextView op1text,op2text,op3text,op4text;
	TextView time,number;
	Button btnanswer;
	//
	ImageView im;
	Context contex;
	Thread timecal;
	history historyexam;
	ScrollView scrollView;
	Activity act;
	DbAdapter database;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		try {
			database=new DbAdapter(this);		
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
	    	return;
		}
		this.setContentView(R.layout.exam);
		contex=this;
		act=this;
		
		getdata();
		initial();
		remain=attribute.exam_time_min*60;
		Start=System.currentTimeMillis();
		timecal= new Thread(updatetime);
		timecal.start();
		showNextQuest();
	}
	
	RadioButton.OnClickListener myOptionOnClickListener =
			   new RadioButton.OnClickListener()
			  {
					
					public void onClick(View arg0) 
					{
						RadioButton r=(RadioButton)arg0;
						for(int i=0;i<opradio.length;i++)
						{
							if(opradio[i]!=r)
								opradio[i].setChecked(false);
						}
						r.setChecked(true);
						MediaPlayer mPlayer = MediaPlayer.create(contex, R.raw.answer);
						 AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
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
			 AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
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
	private void showNextQuest() 
	{
		
		current=this.question[order[turn]];
		//
		for(int i=0;i<op.length;i++)
		{
			op[i].setBackgroundResource(0);
			opradio[i].setChecked(false);
		}
		//
		
		if(current.getPic()==null||current.getPic().length()==0)
		{
			im.setImageResource(R.drawable.trans1);
		}
		else
		{
			try {
				im.setImageBitmap(Utilities.getImage(database.fetchSingle(Integer.parseInt(current.getPic()))));
			} catch (NumberFormatException e) {
			} catch (Exception e) {
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		}
		
		//
		this.number.setText(String.valueOf(turn+1));
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
		ExamPage.Enable=true;
		DownScroll(1);
	}
	
	private View.OnClickListener ansbtnlistner=new View.OnClickListener() {
		public void onClick(View v) 
		{
			if(!ExamPage.Enable)
				return;
			Thread t=new Thread(btn);
			t.start();
			ExamPage.Enable=false;
		}
	};
	
	private boolean IsEnd()
	{
		if(turn==attribute.exam_question_number||remain==0)
				return true;
		return false;
	}
	public void initial()
	{
		scrollView=(ScrollView)findViewById(R.id.scrollView1);
		op[0]=(RelativeLayout)findViewById(R.id.op1);
		op[1]=(RelativeLayout)findViewById(R.id.op2);
		op[2]=(RelativeLayout)findViewById(R.id.op3);
		op[3]=(RelativeLayout)findViewById(R.id.op4);
		for(int i=0;i<4;i++)
			op[i].setOnClickListener(opclick);
		
		im=(ImageView)findViewById(R.id.questimage);
		
		rand=new Random(System.currentTimeMillis());
		text=(TextView)findViewById(R.id.questtext);
		Typeface titleface = Typeface.createFromAsset(getApplicationContext().getAssets(),
				"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
		text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
		text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_color);
		text.setTypeface(titleface);
		//
		Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(),
				"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_content);
		//
		time=(TextView)findViewById(R.id.examtime);
		time.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.small_font_size);
		time.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.small_font_color);
		time.setTypeface(face);
		//
		number=(TextView)findViewById(R.id.examnumber);
		number.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.small_font_size);
		number.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.small_font_color);
		number.setTypeface(face);
		//
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
		//
		btnanswer=(Button)findViewById(R.id.btnanswer);
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
		for(int i=0;i<visited.length;i++)
			visited[i]=false;
	}
	private void getdata() {
		List<List<Object>> exams=null;
		try {
			exams=database.selectRecordsFromDBList("select * from "+exam.TableName);
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		this.question=new exam[exams.size()];
		this.visited=new boolean[exams.size()];
		for(int i=0;i<this.question.length;i++)
		{
			this.question[i]=new exam((String)exams.get(i).get(0), (String)exams.get(i).get(1), (String)exams.get(i).get(2), (String)exams.get(i).get(3)
					, (String)exams.get(i).get(4), (String)exams.get(i).get(5), (String)exams.get(i).get(6), (String)exams.get(i).get(7)
					, (String)exams.get(i).get(8), (String)exams.get(i).get(9), (String)exams.get(i).get(10),(String)exams.get(i).get(11));
		}
		//
		List<archive> allarchive=null;
		try {
			allarchive = database.getAllarchive();
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		int max=0;
		for(int i=0;i<allarchive.size();i++)
			if(Integer.valueOf(allarchive.get(i).getExam_id())>max)
				max=Integer.valueOf(allarchive.get(i).getExam_id());
		exam_id=max+1;
		//
		Random rand=new Random(System.currentTimeMillis());
		for(int i=0;i<order.length;i++)
		{
			order[i]=rand.nextInt(question.length);
			int j;
			for(j=0;j<i&&order[i]!=order[j];j++);
			if(j<i)
				i--;
		}
	}

	Runnable btn=new Runnable() 
	{
		
		public void run() 
		{
			runOnUiThread(new Runnable() {
				public void run() 
				{
					int i;
					for( i=0;i<opradio.length && !opradio[i].isChecked();i++);
					result[turn++]=new archive(i==opradio.length?String.valueOf(0):String.valueOf(i+1),
							current.getID(),String.valueOf( exam_id));
					//SystemClock.sleep(300);
					if(IsEnd())
					{
						if(turn<attribute.exam_question_number)
							for(;turn<attribute.exam_question_number;turn++)
								result[turn]=new archive("0",question[turn].getID(),String.valueOf(exam_id));
						
						for(int c=0;c<result.length;c++)
							database.Addarchive(result[c]);
						historyexam=new history(String.valueOf(exam_id),String.valueOf(System.currentTimeMillis()));
						database.addhistory(historyexam);
						//
						finish();
				    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
						//
						Intent nextintend=new Intent(ExamPage.this, Result.class);
						nextintend.putExtra("id",String .valueOf(exam_id));
						startActivity(nextintend);
						
					}
					else
					{
						showNextQuest();
					}
				}
				});
			
		}
	};
	//
	Runnable updatetime=new Runnable() {
		
		
		public void run() 
		{
			for(;ExamPage.remain>0;)
				updatetimer();
		}
	};
	private void updatetimer() {
		runOnUiThread(new Runnable() {
			public void run() 
			{
				long pass=Time-(System.currentTimeMillis()-Start);
				if(ExamPage.remain!=(int)pass/1000)
				{
					ExamPage.remain=(int)pass/1000;
					time.setText(String.valueOf(ExamPage.remain/60)+":"+String.valueOf(ExamPage.remain%60));
				}
				
			}
		});
		SystemClock.sleep(498);	// should be something more useful!
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//timecal.s
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//timecal.resume();
	}
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


