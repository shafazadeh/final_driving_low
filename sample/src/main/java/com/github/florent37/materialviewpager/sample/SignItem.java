package com.github.florent37.materialviewpager.sample;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.sign;

public class SignItem extends Activity
{
    //***************final fields*************
    public static final int GRID_VIEW=0;
    public static final int LIST_VIEW=1;
    //*****************************************

    public static int state=SignItem.GRID_VIEW;
    GridView gridView;
    ListView listView;
    Activity act;
    public  static String CatId=null;
    DbAdapter database;
    //
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //Handle the back button
        if( keyCode == KeyEvent.KEYCODE_BACK ) {
            //Ask the user if they want to quit
            //Intent i=new Intent(Sign.this, DrivingLowActivity.class);
            //startActivity(i);
            finish();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }
    }
    //
    public void setListViewContent()
    {
        this.setContentView(R.layout.list);
        listView = (ListView) findViewById(R.id.items);
        sign[] Values=getdata();
        listView.setAdapter(new SignItemAdapter(this, Values));
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
                Intent i=new Intent(SignItem.this, SignDetail.class);
                i.putExtra("catid", String.valueOf(SignItem.CatId));
                i.putExtra("id", String.valueOf(position));
                startActivity(i);
                overridePendingTransition(R.anim.rotate3d_in_right,R.anim.rotate3d_out_left);
            }
        });

    }
    public void setGridViewContent()
    {
        this.setContentView(R.layout.main3);
        gridView = (GridView) findViewById(R.id.grid);
        sign[] Values=getdata();
        gridView.setAdapter(new SignItemAdapter(this, Values));
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
                Intent i=new Intent(SignItem.this, SignDetail.class);
                i.putExtra("catid", String.valueOf(SignItem.CatId));
                i.putExtra("id", String.valueOf(position));
                startActivity(i);
                overridePendingTransition(R.anim.rotate3d_in_right,R.anim.rotate3d_out_left);
            }
        });
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {



            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {


            }

            public void onScrollStateChanged(AbsListView view, int scrollState) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        gridView.invalidate();
                    }
                });
            }


        });
    }
    private sign[] getdata()
    {
        List<List<Object>> signs=null;
        try {
            signs = database.selectRecordsFromDBList(SignItem.CatId);
        } catch (Exception e) {
            finish();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }

        sign[] ret=new sign[signs.size()];
        for(int i=0;i<ret.length;i++)
        {
            ret[i]=new sign((String)signs.get(i).get(0), (String)signs.get(i).get(1), (String)signs.get(i).get(2), (String)signs.get(i).get(3));
        }
        return ret;
    }
    //
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try {
            database=new DbAdapter(this);
        } catch (Exception e) {
            finish();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        SignItem.CatId=getIntent().getStringExtra("query");
        setGridViewContent();
        act=this;
        //
        Button button=(Button)this.findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

    }
    //
    public class SignItemAdapter extends BaseAdapter
    {
        private Context context;
        private final sign[] Values;


        public SignItemAdapter(Context context, sign[] Values) {
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



            } else {
                gridView = convertView;
            }
            //
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            try {
                imageView.setImageBitmap(com.github.florent37.materialviewpager.sample.db.Utilities.getImage(database.fetchSingle(Integer.parseInt(Values[position].getpic()))));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (Exception e) {
                finish();
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText("");
            Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title3);
            textView.setTypeface(face);
            textView.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_size);
            textView.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title3_font_color);

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
