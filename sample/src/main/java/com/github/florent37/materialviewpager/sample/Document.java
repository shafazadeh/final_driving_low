package com.github.florent37.materialviewpager.sample;


import java.util.ArrayList;
import java.util.HashMap;
import com.github.florent37.materialviewpager.sample.adapter.ListAdapter;

import com.github.florent37.materialviewpager.sample.xml.AndroidXmlParsing;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Document extends Fragment {
	static final String KEY_NAME = "name";
	ListView listView;
    public static MainActivity m;
    public static String back_frament;
    public static int level=0;

    public static Document newInstance( MainActivity n) {
        Document fragmentDemo = new Document();

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
		setListViewContent();
		TextView textView = (TextView)getActivity(). findViewById(R.id.textView1);
		textView.setText( "مدارک اخذ گواهینامه");
		Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
		textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_size);
		textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_color);
		textView.setTypeface(face);
        ImageButton backbt= (ImageButton)this.getActivity().findViewById(R.id.back_);
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

	}
	
	//
	public void setListViewContent()
	{
			//this.getActivity().setContentView(R.layout.list4);
			listView = (ListView) getActivity().findViewById(R.id.items);
			String[] Values=getdata();
			listView.setAdapter(new ListAdapter(this.getActivity(), Values));
		
	}

	private String[] getdata() {
		String[] output=null;
		try{
			ArrayList<HashMap<String, String>> items=new AndroidXmlParsing(this.getActivity()).getMenuItems();
			output=new String[items.size()];
			int i=0;
			for(HashMap<String, String> h : items)
			{
				output[i++]= h.get(KEY_NAME);
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
		
}
