package com.github.florent37.materialviewpager.sample.adapter;

import com.github.florent37.materialviewpager.sample.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter
{
	private Context context;
	private final String[] Values;


	public ListAdapter(Context context, String[] Values) {
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
		//ImageView imageView = (ImageView) gridView
			//	.findViewById(R.id.icon);
				
				TextView textView = (TextView) gridView
						.findViewById(R.id.titletxt);
				textView.setText( Values[position]);
				Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title1);
				textView.setTypeface(face);
				textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_size);
				textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_color);
				
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
