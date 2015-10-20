package com.github.florent37.materialviewpager.sample;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;




import com.github.florent37.materialviewpager.sample.xml.XMLParser;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Pelak extends Fragment {
    public static MainActivity m;
	ListView listView;
	Context context;
	static final String KEY_ITEM = "row"; // parent node
    Pelaks[] Values=null;

    public static String back_frament;
    public static int level=0;

    public static Pelak newInstance( MainActivity n) {
        Pelak fragmentDemo = new Pelak();

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
		//

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
		try{
		context=this.getActivity();
		setListViewContent();
		TextView textView = (TextView) getActivity().findViewById(R.id.textView1);
		textView.setText("مراکز تعویض پلاک");
		Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(), "font/" + com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
		textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_size);
		textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_color);
		textView.setTypeface(face);
		//
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
				
				//
				String con="\n"+ Values[position].markaz+"\n"+Values[position].neshani+
						"\n\n تلفن:"+Values[position].tel;
					Intent i=new Intent(Pelak.this.getActivity(), Detail.class);
					i.putExtra("content", con);
					startActivity(i);
					getActivity().overridePendingTransition(R.anim.rotate3d_in_right, R.anim.rotate3d_out_left);
				//	
				
				
			}
		});
		//
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
		}catch(Exception e)
		{
			Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
		}

		//
		SharedPreferences settings = getActivity().getSharedPreferences("pref", 0);
	    int Reshap = settings.getInt("alert1", -1);
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
				    editor.putInt("alert1", 0);
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

	//
	public void setListViewContent()
	{
			//this.setContentView(R.layout.list4);
			listView = (ListView) getActivity().findViewById(R.id.items);
			try{
				 Values=getdata();
			}catch(Exception e)
			{
				Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
			listView.setAdapter(new PelakListAdapter(this.getActivity(), Values));
	}

	private Pelaks[] getdata() throws NotFoundException, IOException  {
		Pelaks[] output=null;
		ArrayList<HashMap<String, String>> items= new ArrayList<HashMap<String, String>>();
		
	    XMLParser parser=new XMLParser();
	    InputStream is = context.getResources().openRawResource(R.raw.m201)  ;
	    String[] s={"radif","markaz","neshani","tel"};
		items= parser.getDomElement(is,s);
		try{
			output=new Pelaks[items.size()];
			int i=0;
			for(HashMap<String, String> h : items)
			{
				output[i++]= new Pelaks(h.get("radif"), h.get("markaz"), h.get("neshani"), h.get("tel")) ;
			}
			}catch(Exception e)
			{
				Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			}
			
		return output;
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
	//
	class Pelaks
	{
		String radif,markaz,neshani,tel;
		public Pelaks(String r,String m, String n,String t)
		{
			this.radif=r;this.markaz=m;this.neshani =n;this.tel=t;
		}
	}
	//
	public class PelakListAdapter extends BaseAdapter 
	{
		private Context context;
		private final Pelaks[] Values;
		

		public PelakListAdapter(Context context, Pelaks[] Values) {
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
				gridView = inflater.inflate(R.layout.searchrow, null);
				//
				/*
				String mobile = Values[position];
				if (mobile.equals("Windows")) {
					imageView.setImageResource(R.drawable.windows_logo);
				}
				else if (mobile.equals("iOS")) {
					imageView.setImageResource(R.drawable.ios_logo);
				}
				else if (mobile.equals("Blackberry")) {
					imageView.setImageResource(R.drawable.blackberry_logo);
				}
				else {
					imageView.setImageResource(R.drawable.android_logo);
				}
				*/
				// set value into textview
				

			} else {
				gridView = convertView;
			}
			TextView textView = (TextView) gridView
					.findViewById(R.id.rowtitle);
			textView.setText(Values[position].markaz);
			Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
			textView.setTypeface(face);
			textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_size);
			textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_color);
			//
			TextView textViewsub = (TextView) gridView
					.findViewById(R.id.rowsub);
			String str=Values[position].neshani;
			String substr=str.length()<40?str:str.substring(0, 40);
			textViewsub.setText(substr);
			textViewsub.setTypeface(face);
			textViewsub.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
			textViewsub.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
			
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
//


