package com.github.florent37.materialviewpager.sample;

import java.util.List;
import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.exam;
import com.github.florent37.materialviewpager.sample.db.rule;
import com.github.florent37.materialviewpager.sample.db.sign;
import com.github.florent37.materialviewpager.sample.fragment.QuestionPage;
import com.github.florent37.materialviewpager.sample.fragment.RuleItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Search extends Fragment
{
    public static boolean back_bt=false;
    public static String cadid_for_back;
    public static MainActivity m;
	String searchfor;
	ListView listView;
	String q1;
	String q2;
	String q3;
	DbAdapter database;

    public static Search newInstance( String someTitle, MainActivity n) {
        Search fragmentDemo = new Search();
        Bundle args = new Bundle();
        args.putString("searchfor", someTitle);

        fragmentDemo.setArguments(args);
        m=n;
        return fragmentDemo;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

		searchfor=getArguments().getString("searchfor");
		try {
			database=new DbAdapter(this.getActivity());
		} catch (Exception e) {
			getActivity().finish();
			getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		//
		q1="select * from "+rule.TableName+" where "+rule.KEY_CONTENT+" LIKE '%"+searchfor+"%' OR "+rule.KEY_NAME+" LIKE '%"+searchfor+"%'";
		q2="select * from "+sign.TableName+" where "+sign.KEY_NAME+" LIKE '%"+searchfor+"%'";
		q3="select * from "+exam.TableName+" where "+exam.KEY_QUESTION+" LIKE '%"+searchfor+"%' OR "+exam.KEY_TYPE+" LIKE '%"+searchfor+"%'";
		//
		setListViewContent();
		TextView textView = (TextView) getActivity().findViewById(R.id.textView1);
		textView.setText("نتایج جستجو "+searchfor);
		Typeface face = Typeface.createFromAsset(this.getActivity().getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
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
				TextView textView = (TextView) v
						.findViewById(R.id.rowtitle);
				if(textView.getText().toString().trim().equals("قوانین".trim()))
				{
					Intent i=new Intent(Search.this.getActivity(), RuleItem.class);
					i.putExtra("query", q1);
					startActivity(i);
				}
				else if(textView.getText().toString().trim().equals("تابلوها".trim()))
				{
					Intent i=new Intent(Search.this.getActivity(), SignItem.class);
					i.putExtra("query", q2);
					startActivity(i);
				}
				else if(textView.getText().toString().trim().equals("سوالات".trim()))
				{
					Intent i=new Intent(Search.this.getActivity(), QuestionPage.class);
					i.putExtra("query", q3);
					startActivity(i);
				}
				
			}
		});

	}
	//
	
	//
	public void setListViewContent()
	{
			//this.setContentView(R.layout.list3);
			listView = (ListView) getActivity().findViewById(R.id.items);
			Item[] Values=getdata();
			if(Values.length>0)
			listView.setAdapter(new ListAdapter(this.getActivity(), Values));
			else
			{
				Values=new Item[1];
				Values[0]=new Item("موردی یافت نشد", 0, "");
				listView.setAdapter(new ListAdapter(this.getActivity(), Values));
			}
		
	}

	private Item[] getdata() {
		Item[] out=new Item[3];
		int i=0;
		List<List<Object>> temp=null;
		try {
			temp = database.selectRecordsFromDBList(q1);
		} catch (Exception e) {
			getActivity().finish();
	    	getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		if(temp.size()>0)
			out[i++]=new Item("قوانین", temp.size(),q1);
		//
		
		try {
			temp= database.selectRecordsFromDBList(q2);
		} catch (Exception e) {
			getActivity().finish();
	    	getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		if(temp.size()>0)
			out[i++]=new Item("تابلوها", temp.size(),q2);
		//
		
		try {
			temp= database.selectRecordsFromDBList(q3);
		} catch (Exception e) {
			getActivity().finish();
	    	getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
		}
		if(temp.size()>0)
			out[i++]=new Item("سوالات", temp.size(),q3);
		Item[] items=new Item[i];
		for(int j=0;j<i;j++)
			items[j]=out[j];
		return items;
	}
	//
	class ListAdapter extends BaseAdapter
	{
		private Context context;
		private final Item[] Values;


		public ListAdapter(Context context, Item[] Values) {
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
			textView.setText(Values[position].Title);
			Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title1);
			textView.setTypeface(face);
			textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_size);
			textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_color);
			//
			TextView textViewsub = (TextView) gridView
					.findViewById(R.id.rowsub);
			textViewsub.setText(String.valueOf( Values[position].Count));
			textView.setTypeface(face);
			textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_size);
			textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.content_font_color);
			
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
	//
	class Item
	{
		String Title;
		int Count;
		String Query;
		public Item(String title, int count,String query)
		{
			Title = title;
			Count = count;
			Query=query;
		}
		
	}
}
