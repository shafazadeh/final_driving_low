package com.github.florent37.materialviewpager.sample;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Ostan extends Fragment
{
    public static MainActivity m;
	String[] Title_String = new String[] {"تهران بزرگ","آذربایجان شرقی","آذربایجان غربی","اردبیل","اصفهان","ایلام",
											"بوشهر","تهران","چهار محال و بختیاری","خراسان جنوبی","خراسان رضوی",
											"خراسان شمالی","خوزستان","زنجان","سمنان","سیستان و بلوچستان",
											"فارس","قزوین","قم","کردستان","کرمان",
											"کرمانشاه","کهگیلویه و بویراحمد","گلستان","گیلان","لرستان",
											"مازندران","مرکزی","هرمزگان","همدان","یزد"};
	 Bitmap[] Title_Image=new Bitmap[Title_String.length];
    public static String back_frament;
    public static int level=0;
	 static String Next="";
	 ListView listView;
	 Context context;

    public static Ostan newInstance( MainActivity n,String s) {
        Ostan fragmentDemo = new Ostan();
        Next=s;
        m=n;
        return fragmentDemo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
		//super.onCreate(savedInstanceState);
		context=this.getActivity();
		//setContentView(R.layout.list4);

        ImageButton backbt=m.mViewPager.getIB();
        if(level==1){
//            Toast.makeText(getActivity().getApplicationContext(), String.valueOf(level),
//                    Toast.LENGTH_LONG).show();
            backbt.setVisibility(View.VISIBLE);

            backbt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
//                    Toast.makeText(getActivity().getApplicationContext(), "fgdfgdfgdsgd",
//                    Toast.LENGTH_LONG).show();
                    m.initialViewPager(back_frament);
                }});

        }else{
            backbt.setVisibility(View.INVISIBLE);
        }


       // Next=getActivity().getIntent().getStringExtra("next");
		listView = (ListView) getActivity().findViewById(R.id.items);
		listView.setAdapter(new OstanArrayAdapter(this.getActivity(), Title_String));
		// set image based on selected text
		listView.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View v,int position, long id) 
			{
				ScaleAnimation scale = new ScaleAnimation(1.1f, 0.9f, 1.1f, 0.9f, 
			        	ScaleAnimation.RELATIVE_TO_SELF, 1f, 
			            ScaleAnimation.RELATIVE_TO_SELF, 1f);
			    scale.setDuration(300);
			    scale.setRepeatCount(0);
				//
				v.startAnimation(scale);
				
				 if(Next.equals("school"))
				 {
					Intent i=new Intent(Ostan.this.getActivity(), LearnSchool.class);
					i.putExtra("id", String.valueOf(position));
					startActivity(i);
					getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
				 }
				 else if(Next.equals("police"))
				 {
					Intent i=new Intent(Ostan.this.getActivity(), Police.class);
					i.putExtra("id", id);
					startActivity(i);
					getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
				 }
				 else if(Next.equals("pelak"))
				 {
					Intent i=new Intent(Ostan.this.getActivity(), Pelak.class);
					i.putExtra("id", id);
					startActivity(i);
					getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
				 }
			}
		});
		//
		TextView textView = (TextView) getActivity().findViewById(R.id.textView1);
		textView.setText( "استان");
		Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
		textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_size);
		textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_color);
		textView.setTypeface(face);
		//
//		Button button=(Button)this.getActivity().findViewById(R.id.back);
//		button.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				finish();
//		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//			}
//		});
		
		//
		SharedPreferences settings = getActivity().getSharedPreferences("pref", 0);
	    int Reshap = settings.getInt("alert", -1);
	    if(Reshap==-1)
	    	showAlert();
	 
	 }

	 
	 private void showAlert() {
		 final Dialog dialog = new Dialog(context,android.R.style.Theme_Translucent_NoTitleBar);
			dialog.setContentView(R.layout.alert);
			Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
			
			TextView alerttxt = (TextView) dialog.findViewById(R.id.text);
			alerttxt.setText("کاربر گرامی:\n در صورت رویت مغایرت در اطلاعات لطفا از طریق فرم تماس با ما گزارش نمایید \n از همکاری شما کمال تشکر را داریم");
			alerttxt.setTypeface(face);
			alerttxt.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
			alerttxt.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_color);
			// if button is clicked, close the custom dialog
			
			Button dialogButton1 = (Button) dialog.findViewById(R.id.dialogButtonOK);
			
			dialogButton1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					SharedPreferences settings = getActivity().getSharedPreferences("pref", 0);
				    SharedPreferences.Editor editor = settings.edit();
				    editor.putInt("alert", 0);
				    editor.commit();
				}
			});
			//
			
			//
			try{
				dialog.getWindow().getAttributes().windowAnimations = R.style.SearchDialogAnimation;
			dialog.show();
			}
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	}


//	public boolean onKeyDown(int keyCode, KeyEvent event)
//	 {
//		    //Handle the back button
//		    if( keyCode == KeyEvent.KEYCODE_BACK ) {
//		        //Ask the user if they want to quit
//		    	//Intent i=new Intent(Sign.this, DrivingLowActivity.class);
//		    	//startActivity(i);
//		    	finish();
//		    	overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//		        return true;
//		    }
//		    else {
//		        return super.onKeyDown(keyCode, event);
//		    }
//		}
		
	 //
	 public class OstanArrayAdapter extends BaseAdapter 
		{
			private Context context;
			private final String[] Values;
			

			public OstanArrayAdapter(Context context, String[] Values) {
				this.context = context;
				this.Values = Values;
			}

			public View getView(int position, View convertView, ViewGroup parent) {

				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				View gridView;

				if (convertView == null) {

					gridView = new View(context);

					// get layout from mobile.xml
					gridView = inflater.inflate(R.layout.row, null);

					//
				} else {
					gridView = convertView;
				}
				ImageView imageView = (ImageView) gridView
						.findViewById(R.id.icon);
						imageView.setImageResource(R.drawable.trans1);
						// set value into textview
						TextView textView = (TextView) gridView
								.findViewById(R.id.titletxt);
						textView.setText( Values[position]);
						Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title2);
						textView.setTypeface(face);
						//textView.setWidth(imageView.getWidth());
						textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title2_font_size);
						textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title2_font_color);
						
				return gridView;
			}

		
			public int getCount() {
				return Values.length;
			}

			
			public Object getItem(int position) {
				return null;
			}

			
			public long getItemId(int position) {
				return 0;
			}
		}

}


