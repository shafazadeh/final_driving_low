package com.github.florent37.materialviewpager.sample.fragment;

import java.util.List;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.sample.MainActivity;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.RuleDetail;
import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.rule;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
//import farin.code.rahnamaee.shapengin.PersianReshape;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class RuleItem extends Fragment
{
    public static String back_frament;
    public static int level;

    private ObservableScrollView mScrollView;
    public void setActivity(MainActivity n)
    {
        m=n;
    }
    public static RuleItem newInstance( String someTitle, MainActivity n) {
        RuleItem fragmentDemo = new RuleItem();
        Bundle args = new Bundle();
        args.putString("query", someTitle);
        fragmentDemo.setActivity(n);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main, container, false);
    }


	//***************final fields*************
	public static final int GRID_VIEW=0;
	public static final int LIST_VIEW=1;
	//*****************************************
	
	public static int state=RuleItem.GRID_VIEW;
	GridView gridView;
	ListView listView;
	public  static String CatId=null;
	//int [] offset={257,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	//
    public  MainActivity m;
	DbAdapter database;
//	public boolean onKeyDown(int keyCode, KeyEvent event)
//	 {
//		    //Handle the back button
//		    if( keyCode == KeyEvent.KEYCODE_BACK ) {
//		        //Ask the user if they want to quit
//		    	//Intent i=new Intent(Sign.this, DrivingLowActivity.class);
//		    	//startActivity(i);
//		    	this.getActivity().finish();
//		    	//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//		        return true;
//		    }
//		    else {
//		        return super.onKeyDown(keyCode, event);
//		    }
//		}
	//
	//
	public void setListViewContent()
	{
          //  this.getActivity().setContentView(R.layout.list);
			listView = (ListView) this.getActivity().findViewById(R.id.items);
			rule[] Values=getdata();
			listView.setAdapter(new RuleItemAdapter(this.getActivity(), Values));
//			listView.setOnItemClickListener(new OnItemClickListener()
//			{
//				public void onItemClick(AdapterView<?> parent, View v,int position, long id)
//				{
//					ScaleAnimation scale = new ScaleAnimation(1.1f, 0.9f, 1.1f, 0.9f,
//				        	ScaleAnimation.RELATIVE_TO_SELF, 1f,
//				            ScaleAnimation.RELATIVE_TO_SELF, 1f);
//				    scale.setDuration(300);
//				    scale.setRepeatCount(0);
//					//
//					v.startAnimation(scale);
//					Intent i=new Intent(RuleItem.this, RuleDetail.class);
//					i.putExtra("catid", String.valueOf(RuleItem.CatId));
//					i.putExtra("id", String.valueOf(id));
//					startActivity(i);
//					overridePendingTransition(R.anim.rotate3d_in_right,R.anim.rotate3d_out_left);
//				}
//			});
			
	}
	public void setGridViewContent()
	{
            //this.getActivity().setContentView(R.layout.main);
			gridView = (GridView) this.getActivity().findViewById(R.id.gridView8);
			rule[] Values=getdata();
			gridView.setAdapter(new RuleItemAdapter(this.getActivity(), Values));
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = 1030; //this is in pixels
        gridView.setLayoutParams(layoutParams);
			gridView.setOnItemClickListener(new OnItemClickListener()
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
					Intent i=new Intent(RuleItem.this.getActivity(), RuleDetail.class);
					i.putExtra("catid", String.valueOf(RuleItem.CatId));
					i.putExtra("id", String.valueOf(position));
					startActivity(i);
					//overridePendingTransition(R.anim.rotate3d_in_right,R.anim.rotate3d_out_left);
				}
			});
			
	}	
	private rule[] getdata() 
	{
		List<List<Object>> rules=null;
		try {
			rules = database.selectRecordsFromDBList(RuleItem.CatId);
		} catch (Exception e) {
            this.getActivity().finish();
	    	//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		rule[] ret=new rule[rules.size()];
		for(int i=0;i<ret.length;i++)
		{
			ret[i]=new rule((String)rules.get(i).get(0), (String)rules.get(i).get(1), (String)rules.get(i).get(2), (String)rules.get(i).get(3), (String)rules.get(i).get(4));
		}
		return ret;
	}
	//
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);

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
		//RuleItem.CatId=getIntent().getStringExtra("query");
        RuleItem.CatId=getArguments().getString("query");
		try {
			database=new DbAdapter(this.getActivity());
		} catch (Exception e) {
            this.getActivity().finish();
			//overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		setGridViewContent();

      //  MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
	}
	//
	public class RuleItemAdapter extends BaseAdapter 
	{
		private Context context;
		private final rule[] Values;
		

		public RuleItemAdapter(Context context, rule[] Values) {
			this.context = context;
			this.Values = Values;
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
					
					
					imageView.setImageResource(R.drawable.logo);
					TextView textView = (TextView) gridView
							.findViewById(R.id.grid_item_label);
					textView.setText(Values[position].getName());
					Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
					textView.setTypeface(face);
					//textView.setWidth(imageView.getWidth());
					textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
					textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_color);
					
					// set image based on selected text
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
