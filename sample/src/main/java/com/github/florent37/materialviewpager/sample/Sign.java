package com.github.florent37.materialviewpager.sample;



//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

public class Sign  extends Fragment
{
    public static String back_frament;
    public static int level;

	ListView listView;
	//public final static String ID_EXTRA="_ID";
    public static MainActivity m;
	 String[] Title_String = new String[] { "تابلو های بازدارنده", "تابلو های هشدار دهنده",
			"تابلو های آگاهی دهنده", "تابلوهای محلی","راهنمای مسیر", "تابلوهای مکمل","برچسب ها"};
	 Bitmap[] Title_Image=new Bitmap[Title_String.length];

    public static Sign newInstance(  MainActivity n) {
        Sign fragmentDemo = new Sign();

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

		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.list);
		// set image based on selected text
		listView = (ListView) this.getActivity().findViewById(R.id.items);
		listView.setAdapter(new SignArrayAdapter(this.getActivity(), Title_String));
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
//                Toast.makeText(getActivity().getApplicationContext(), "select * from sign" +
//                        " where category='" + String.valueOf(position) + "'",
//                        Toast.LENGTH_LONG).show();
//                m.initialViewPager("select * from sign" +
//                        " where category='" + String.valueOf(position) + "'");
				Intent i=new Intent(Sign.this.getActivity(), SignItem.class);
				i.putExtra("query","select * from sign" +
						" where category='"+String.valueOf(position)+"'");
				startActivity(i);
				//overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
			}
		});
		//
		TextView textView = (TextView) this.getActivity().findViewById(R.id.textView1);
		textView.setText( "تابلوها");
		Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(), "font/" + com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
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
//				getActivity().finish();
//		    	getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//			}
//		});

	}

	 public boolean onKeyDown(int keyCode, KeyEvent event)
	 {
		    //Handle the back button
		    if( keyCode == KeyEvent.KEYCODE_BACK ) {
		        //Ask the user if they want to quit
		    	//Intent i=new Intent(Sign.this, DrivingLowActivity.class);
		    	//startActivity(i);
		    	this.getActivity().finish();
		    	getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		        return true;
		    }
		    else {
		        return super.getActivity().onKeyDown(keyCode, event);
		    }
		}
		
	//
	public class SignArrayAdapter extends BaseAdapter 
	{
		private Context context;
		private final String[] Values;
		

		public SignArrayAdapter(Context context, String[] Values) {
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
				
				// set image based on selected text
				

			} else {
				gridView = convertView;
			}
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.icon);
					imageView.setImageResource(R.drawable.logo);
					// set value into textview
					TextView textView = (TextView) gridView
							.findViewById(R.id.titletxt);
					textView.setText(Values[position]);
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
