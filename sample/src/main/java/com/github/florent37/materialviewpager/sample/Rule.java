package com.github.florent37.materialviewpager.sample;



//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.sample.fragment.Slider;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;


public class Rule extends android.support.v4.app.Fragment
{
    public  MainActivity m;

    public void setActivity(MainActivity n)
    {
        m=n;
    }


    public static Rule newInstance(MainActivity n) {
        Rule fragmentDemo = new Rule();
        fragmentDemo.setActivity(n);

        return fragmentDemo;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main2, container, false);

    }
	 /*String[] Title_String = new String[] 
			 {"تصادفات" , "هوشیاری" ,"آلودگی",
				"برخورد و واکنش", "آگاهی از خطرات","عابرین پیاده",
				"مقررات راها","بار و نحوه بار گیری","آشنایی با اتومبیل",
				"علائم راهنمایی و رانندگی","هدایت  وسیله نقلیه"
			};*/
	String[] Title_String = new String[] 
			 {"آشنایی با اتومبیل" , "هوشیاری" ,"هدایت  وسیله نقلیه","برخورد و واکنش","آگاهی از خطرات","عابرین پیاده","مقررات راه ها","علائم راهنمایی","تصادفات","بارگیری","آلودگی"};
	 Bitmap[] Title_Image=new Bitmap[Title_String.length];
	 GridView gridView;
	 Context contex;
    private ObservableScrollView mScrollView2;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



		//this.getActivity().setContentView(R.layout.main);
        mScrollView2 = (ObservableScrollView) view.findViewById(R.id.scrollView3);
		gridView = (GridView) this.getActivity().findViewById(R.id.gridView9);

        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = 900; //this is in pixels
        gridView.setLayoutParams(layoutParams);

		gridView.setSelector(android.R.color.transparent);
		contex=this.getActivity();

		for(int i=0;i<Title_Image.length;i++) {
            Title_Image[i] = BitmapFactory.decodeResource(getResources(), R.drawable.cat01 + i);

        }


		gridView.setAdapter(new RuleArrayAdapter(this.getActivity(), Title_String,Title_Image));
		// set image based on selected text
		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v,int position, long id)
			{
				ScaleAnimation scale = new ScaleAnimation(1.1f, 0.9f, 1.1f, 0.9f,
			        	ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
			            ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
			    scale.setDuration(200);
			    scale.setRepeatCount(0);
				//
				v.startAnimation(scale);
//                Toast.makeText(getActivity().getApplicationContext(), String.valueOf(position),
//                                Toast.LENGTH_LONG).show();
//				Intent i=new Intent(Rule.this.getActivity(), MainActivity.class);
////                Toast.makeText(getActivity(), String.valueOf(position),
////                        Toast.LENGTH_LONG).show();
//				i.putExtra("query", String.valueOf(position));
//                i.putExtra("tabname", Title_String[position]);
//				startActivity(i);
                MainActivity.tab_name=Title_String[position];
                m.initialViewPager(String.valueOf(position));
				//overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
			}
		});
//		//
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

		//
        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView2, null);
		
	}

	 
	 public boolean onKeyDown(int keyCode, KeyEvent event)
	 {
		    //Handle the back button
		    if( keyCode == KeyEvent.KEYCODE_BACK ) {
		    	this.getActivity().finish();
		    	//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		        return true;
		    }
		    else {
		        return super.getActivity().onKeyDown(keyCode, event);
		    }
		}
		
	//
	public class RuleArrayAdapter extends BaseAdapter 
	{
		private Context context;
		private final String[] Values;
		private final Bitmap[] Pics;

		public RuleArrayAdapter(Context context, String[] Values,Bitmap[] Pics) {
			this.context = context;
			this.Values = Values;
			this.Pics=Pics;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			View gridView;
			if (convertView == null) {

				//gridView = new View(context);
				LayoutInflater li = getActivity().getLayoutInflater();
				// get layout from mobile.xml
				gridView = li.inflate(R.layout.item, null);
			} else {
				gridView = convertView;
			}
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
					
					
					imageView.setImageBitmap(this.Pics[position]);
					// set value into textview
					TextView textView = (TextView) gridView
							.findViewById(R.id.grid_item_label);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy", "onDestroy position ImageGridFragment");
        Title_Image=null;
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(this);
        ft.commit();
        System.gc();
        Runtime.getRuntime().gc();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("onDestroy", "onDestroy position ImageGridFragment");
        Title_Image=null;
        android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(this);
        ft.commit();
        System.gc();
        Runtime.getRuntime().gc();
    }

}
