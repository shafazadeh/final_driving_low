package com.github.florent37.materialviewpager.sample;

import java.util.ArrayList;
import java.util.List;

import com.github.florent37.materialviewpager.sample.attrib.attribute;
import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.Utilities;
import com.github.florent37.materialviewpager.sample.db.archive;
import com.github.florent37.materialviewpager.sample.db.exam;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ExaminExam extends Activity
{

	public String exam_id;
	List <archive> questions=null;
	static boolean Enable=false;
	//
	RelativeLayout[] op=new RelativeLayout[4];
	RadioButton[] opradio=new RadioButton[4];
	TextView text;
	TextView op1text,op2text,op3text,op4text;
	Button next;
	//
	exam current;
	int turn=0;
	ImageView im;
	Context context;
	DbAdapter database;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		context=this;
		try {
			database=new DbAdapter(this);
		} catch (Exception e1) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			return;
		}
		this.setContentView(R.layout.examin);
		exam_id=getIntent().getStringExtra("exam_id");
		
		questions=getdata();
		
		//
		
		initial();
		Button button=(Button)this.findViewById(R.id.back);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		});
		try{
		showNextQuest();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void showNextQuest() 
	{
		try {
			current=database.getExam(Integer.valueOf(this.questions.get(turn).getId()));
		} catch (NumberFormatException e) {
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		//
		for(int i=0;i<op.length;i++)
		{
			op[i].setBackgroundResource(0);
		}
		
		//
		
		if(current.getPic()==null||current.getPic().length()==0)
		{
			im.setImageResource(R.drawable.trans1);
		}
		else
		{
			try {
				im.setImageBitmap(Utilities.getImage(new DbAdapter(this).fetchSingle(Integer.parseInt(current.getPic()))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				finish();
		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		}
		//
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
		if(Integer.valueOf(current.getRightOpt())==Integer.valueOf(this.questions.get(turn).getAnswer()) ||Integer.valueOf(this.questions.get(turn).getAnswer())==0 )
			op[Integer.valueOf(current.getRightOpt())-1].setBackgroundResource(R.drawable.correctans);
		else
		{
			op[Integer.valueOf(current.getRightOpt())-1].setBackgroundResource(R.drawable.correctans);
			op[Integer.valueOf(this.questions.get(turn).getAnswer())-1].setBackgroundResource(R.drawable.wrongans);
		}
		turn++;
		ExaminExam.Enable=true;
	}

	/**
	 * 
	 * @return
	 */
	private List<archive> getdata() {
		List<List<Object>> quest=null;
		try {
			quest=database.selectRecordsFromDBList("select * from "+archive.TableName +
					" where "+ archive.KEY_EXAMID +"='"+this.exam_id+"'");
		} catch (Exception e) {
			finish();
	    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		List<archive> temp =new ArrayList<archive>();
		for(int i=0;i<quest.size();i++)
		{
			temp.add(new archive((String)quest.get(i).get(2), (String)quest.get(i).get(1), (String)quest.get(i).get(0)));
		}
		return temp;
	}
	
	private View.OnClickListener nextbtnlistner=new View.OnClickListener() {
		public void onClick(View v) 
		{
			if(!ExaminExam.Enable)
				return;
			if(turn==attribute.exam_question_number){
				finish();
	    		overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}else{
				ExaminExam.Enable=false;
				showNextQuest();
				
			}
		}
	};
	
	
	public void initial()
	{
		op[0]=(RelativeLayout)findViewById(R.id.examinop1);
		op[1]=(RelativeLayout)findViewById(R.id.examinop2);
		op[2]=(RelativeLayout)findViewById(R.id.examinop3);
		op[3]=(RelativeLayout)findViewById(R.id.examinop4);
		
		im=(ImageView)findViewById(R.id.examinimage);
		
		text=(TextView)findViewById(R.id.examintext);
		Typeface titleface = Typeface.createFromAsset(getApplicationContext().getAssets(),
				"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
		text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_color);
		text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
		text.setTypeface(titleface);
		Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(),
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
		
		opradio[0]=(RadioButton)op[0].findViewById(R.id.op1radio);
		opradio[1]=(RadioButton)op[1].findViewById(R.id.op1radio);
		opradio[2]=(RadioButton)op[2].findViewById(R.id.op1radio);
		opradio[3]=(RadioButton)op[3].findViewById(R.id.op1radio);
		for(int i=0;i<4;i++)
			opradio[i].setVisibility(View.GONE);
		//
		next=(Button)findViewById(R.id.btnnext);
		//
		
		//
		next.setOnClickListener(nextbtnlistner);
	}
}
