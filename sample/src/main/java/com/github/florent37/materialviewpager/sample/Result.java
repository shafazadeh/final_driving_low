package com.github.florent37.materialviewpager.sample;

import java.util.ArrayList;
import java.util.List;

import com.github.florent37.materialviewpager.sample.attrib.attribute;
import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.archive;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends Activity 
{
	List<archive> result=new ArrayList<archive>();
	
	String id,resoan;
	
	int count_unans=0,count_corans=0,count_wroans=0;
	DbAdapter database;
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.result);
		try {
			database=new DbAdapter(this);
		} catch (Exception e) {
			finish();
			overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		//RelativeLayout rel=(RelativeLayout)findViewById(R.id.rel1);
		id=getIntent().getStringExtra("id");
		getdata();
		clacalute();
		intial();
		Button button=(Button)this.findViewById(R.id.back);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		});

	}

	private void intial() 
	{
		
		Typeface face = Typeface.createFromAsset(getAssets(), "font/BNazanin.ttf");
		//
		//
		TextView corans=(TextView)findViewById(R.id.correctanstext);
		corans.setTextSize(18f);
		corans.setTextColor(Color.GREEN);
		corans.setTypeface(face);
		corans.setText(String.valueOf(count_corans) + ":"+ "تعداد پاسخ صحیح");
		//
		TextView wrongans=(TextView)findViewById(R.id.wronganstext);
		wrongans.setTextSize(18f);
		wrongans.setTextColor(Color.RED);
		wrongans.setTypeface(face);
		wrongans.setText(String.valueOf(count_wroans) + ":"+ "تعداد پاسخ اشتباه");
		//
		TextView unans=(TextView)findViewById(R.id.unanswtext);
		unans.setTextSize(18f);
		unans.setTextColor(Color.YELLOW);
		unans.setTypeface(face);
		unans.setText(String.valueOf(count_unans) + ":"+ "تعداد سوالات بی پاسخ");
		//
		TextView status=(TextView)findViewById(R.id.status);
		status.setTextSize(20f);
		status.setTypeface(face);
		if(count_corans >= attribute.exam_question_pass)
		{
			status.setTextColor(Color.GREEN);
			status.setText("شما این امتحان را با موفقیت گذراندید");
		}
		else
		{
			status.setTextColor(Color.RED);
			status.setText("شما این امتحان را با موفقیت نگذراندید");
		}
		//	
		Button examin=(Button)findViewById(R.id.examin);
		
		examin.setOnClickListener(examinbtnlistner);
	}
	
	private void clacalute() 
	{
		for (archive archives : result) 
		{
			if(Integer.valueOf(archives.getAnswer())==0)
				count_unans++;
			else
				try {
					if(Integer.valueOf( database.getExam(Integer.valueOf(archives.getId())).getRightOpt()) ==Integer.valueOf( archives.getAnswer()))
						count_corans++;
					else
						count_wroans++;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					finish();
			    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
				}
		}
	}

	private void getdata() 
	{
		List<archive> allarchive=null;
		try {
			allarchive = database.getAllarchive();
		} catch (Exception e) {
			finish();
			overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		for (archive archives : allarchive) 
		{
			if(Integer.valueOf(archives.getExam_id().trim())== Integer.valueOf(id.trim()))
			{
				result.add(archives);
			}
		}
	}
	//
	private View.OnClickListener examinbtnlistner=new View.OnClickListener() {
		public void onClick(View v) 
		{
			Intent i=new Intent(Result.this, ExaminExam.class);
			i.putExtra("exam_id", String.valueOf(id));
			startActivity(i);
		}
	};
}
