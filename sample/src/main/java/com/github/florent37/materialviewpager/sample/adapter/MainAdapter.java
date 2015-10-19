package com.github.florent37.materialviewpager.sample.adapter;

import com.github.florent37.materialviewpager.sample.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class MainAdapter extends BaseAdapter 
{
	private Context context;
	private final String[] Values;


	public MainAdapter(Context context, String[] Values) {
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
			gridView = inflater.inflate(R.layout.item, null);

			//
			ImageView imageView = (ImageView) gridView
			.findViewById(R.id.grid_item_image);
			
			//String mobile = Values[position];
			if (position==0) {
				imageView.setImageResource(R.drawable.amozesh);
			}
			else if (position==1) {
				imageView.setImageResource(R.drawable.signs);
			}
			else if(position==2) {
				imageView.setImageResource(R.drawable.azmoon);
			}
			else if(position==4)
			{
				imageView.setImageResource(R.drawable.attachs);
			}
			// set value into textview
			TextView textView = (TextView) gridView
					.findViewById(R.id.grid_item_label);
			textView.setText(Values[position]);
			// set image based on selected text
			

		} else {
			gridView = convertView;
		}

		return gridView;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return Values.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
