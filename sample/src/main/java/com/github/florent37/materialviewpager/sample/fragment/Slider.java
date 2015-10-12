package com.github.florent37.materialviewpager.sample.fragment;


import android.annotation.TargetApi;
import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.github.florent37.materialviewpager.sample.MainActivity;
import com.github.florent37.materialviewpager.sample.Page;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.Rule;
import com.github.florent37.materialviewpager.sample.Sign;
import com.github.florent37.materialviewpager.sample.menu.CustomMenu;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.exam;
import com.github.florent37.materialviewpager.sample.db.images;

import android.content.Context;
import android.content.Intent;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Slider extends Fragment {

    public List<List<Object>> exams=null;
    public List<List<Object>> rules=null;
    public static boolean back_bt=false;
    public static String cadid_for_back;
   public static MainActivity m;
    private ObservableScrollView mScrollView2;
    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;
    int id_ft;
    ////////////////////////////////////////
    FragmentTransaction ft;
    Context contex;
    LinearLayout line;
    String Catid;
    int offset=500,distance=200;
    ImageButton low,quest;
    Hashtable<String, Integer> hash=new Hashtable<String, Integer>();
    int [] counts={10,2,5,11,6,2,5,6,0,0,4,2,3,4,3,7,2,0};
    String[][] title={{"روشن کردن صحیح","تنظیم صندلی و تامین میدان دید راننده","طریقه حرکت","چگونه ترمز کنیم","چگونه فرمان را بگیریم","دور زدن","روش دست روی دست","پارک دوبل","توقف در سطح شیبدار","خروج از پارک"},
            {"آمادگی","حالت روحی راننده"},
            {"رانندگی در طول شبانه روز","رانندگی در شرایط مختلف آب و هوایی","شرایط رانندگی در راه ها","نقش وسیله نقلیه در رانندگی ایمن","استفاده از چراغ های بزرگ"}
            ,{"حفظ فاصله با ماشین جلو","روش یک طول اتومبیل","روش 2 ثانیه زمان","تصادف جلو به عقب","مسافت توقف","اطلاعات از قوانین رانندگی","نکات مهم در رانندگی","سبقت  وگردش","خط کشی","قواعد گردش","حق تقدم در عبور و مرور"},
            {"سرعت مطمئنه","آگاهی از خطرات","توجه و زمان واکنش","تاثیرات مواد مخدر","خستگی","سرعت و فاصله"},
            {"عابرین پیاده","افراد مسن و کودکان "},
            {"مقررات آزاد راه","مراحل خروج از آزاد راه","مقررات راهها","مراحل ورود به آزاد راه","خروج از آزادراه"}
            ,{"چراغ راهنمایی","تابلوها","خط کشی ها","رنگ ها","شکل ها","مفهوم علائم دست"},
            {},{},
            {"مقدمه","تایرها","آلودگی ترافیک","شناخت اجزای خودرو"},
            {"دور زدن دو فرمانه","دور زدن یک فرمانه"},
            {"راه بدون جدول و پارک در سر بالایی","راه دارای جدول کناری و پارک در سر بالایی","راه دارای جدول کناری و پارک در سرازیری"}
            ,{"نگاه کردن و دیدن","فکر کردن و تصمیم گرفتن","اجازه دهید که دیگران بدانند که شما چه میخواهید  بکنید","عمل کردن"},
            {"چگونه گردش نماییم؟","کجا سبقت نگیریم","چگونه سبقت بگیریم!"},
            {"تابلو های بازدارنده", "تابلو های هشدار دهنده",
                    "تابلو های آگاهی دهنده", "تابلوهای محلی","راهنمای مسیر", "تابلوهای مکمل","برچسب ها"},
            {"خط کشی طولی","خط کشی عرضی"},{}};
    DbAdapter db;

/////////////////////////////


    public static Slider newInstance( String someTitle, MainActivity n) {
        Slider fragmentDemo = new Slider();
        Bundle args = new Bundle();
        args.putString("query", someTitle);

        fragmentDemo.setArguments(args);
        m=n;
        return fragmentDemo;
    }



//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

//            FragmentManager fragManager = getFragmentManager();
//            FragmentTransaction backTransaction = fragManager.beginTransaction();
//            Fragment frag = fragManager.findFragmentById(this.getId());
//            if(frag != null) {
//                backTransaction.detach(frag);
//                backTransaction.commit();
//            }
            //m.initialViewPager("7");
//        }
//        return super.getActivity().onKeyDown(keyCode, event);
//    }

    public static void set_cadid_for_back(String s){
       cadid_for_back=s;
   }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        getView().setFocusableInTouchMode(true);
//        getView().requestFocus();
//        getView().setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
//                    // handle back button's click listener
//                    return true;
//                }
//                return false;
//            }
//        });
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.slider, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mScrollView2 = (ObservableScrollView) view.findViewById(R.id.scrollView2);

//






        contex=this.getActivity();
        ft=getFragmentManager().beginTransaction();
        id_ft=this.getId();
        try {
            db=new DbAdapter(this.getActivity());
        } catch (Exception e) {
            this.getActivity().finish();
            //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            return;
        }
        Catid=getArguments().getString("query");

        loadhash();
        setContent();




      //  MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView2, null);
    }
    private void loadhash() {
        for(int i=0;i<11;i++)
        {
            hash.put(String.valueOf(i), i);
        }
        hash.put("05", 11);
        hash.put("08", 12);
        hash.put("36", 13);
        hash.put("37", 14);
        hash.put("71", 15);
        hash.put("72", 16);
    }


    private void setContent() {
        // this.getActivity().setContentView(R.layout.slider);
        //line=(LinearLayout)this.getActivity().findViewById(R.id.llhsv1);



        ////low=(ImageButton)this.getActivity().findViewById(R.id.low);
        //quest=(ImageButton)this.getActivity().findViewById(R.id.questions);
//        checkcount();
        //low.setOnClickListener(lowbtn);
        //quest.setOnClickListener(questbtn);
        //
//


//        ImageButton backbt= (ImageButton)this.getActivity().findViewById(R.id.backbt);
//        backbt.setVisibility(View.INVISIBLE);

        final float id;
        if(Catid.equals("05")||Catid.equals("08")||Catid.equals("36")||Catid.equals("37")
                ||Catid.equals("71")||Catid.equals("72"))
            id=Float.parseFloat(Catid)/10;
        else
            id=Float.parseFloat(Catid);

        int decode=(hash.get(Catid));
        if(decode==8||decode==9)
        {
            this.getActivity().findViewById(R.id.scrollView2).setVisibility(View.GONE);
            return;
        }

        for( int i=0;i<counts[decode];i++)
        {

            final int temp=i;
            images img=null;
            try {
                img = db.fetchimage((int)(offset+distance*id+i));
            } catch (Exception e) {
                this.getActivity().finish();
                //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                break;
            }
            //Bitmap b=Utilities.getImage(img.getData());
            final String name=img.getName();
            //final String idcontent=img.getName();
            SliderItem s=new SliderItem(contex,img);
            //
          //  line.addView(s.getView(img.getData(), title[decode][i]));

            gridArray.add(new Item(img.getData(),title[decode][i],img.getName(),i));

        }

        gridView = (GridView) this.getActivity().findViewById(R.id.gridView);
//          // gridView.addView( s.getView(img.getData(), title[decode][i]));
        customGridAdapter = new CustomGridViewAdapter(this.getActivity(),R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ScaleAnimation scale = new ScaleAnimation(1.1f, 0.9f, 1.1f, 0.9f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                scale.setDuration(200);
                scale.setRepeatCount(0);
                //
                v.startAnimation(scale);

                CustomGridViewAdapter.RecordHolder holder=(CustomGridViewAdapter.RecordHolder) v.getTag();


                if(holder.index==1 && Catid.equals("7"))
                {
//
//                    Intent i=new Intent(Slider.this.getActivity(),Sign.class);
//                    startActivity(i);
                    m.initialViewPager("sign");
                    //overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    return;
                }
                if(holder.name.equals("true"))
                {

                    m.initialViewPager(Catid+String.valueOf(holder.index));
                    //overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
                else
                {

                    Intent i=new Intent(Slider.this.getActivity(),Page.class);
                    i.putExtra("query", String.valueOf(holder.name));
                    startActivity(i);
                    //overridePendingTransition(R.anim.rotate3d_in_right,R.anim.rotate3d_out_left);
                }

            }
        });


    }



    private void checkcount()
    {

        //List<List<Object>> rules=null;
        try {
            rules = db.selectRecordsFromDBList("select * from rules" +
                    " where category='" + String.valueOf((int) (Math.floor(Integer.parseInt(Catid)))) + "'");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.getActivity().finish();
            //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        //if(rules==null||rules.size()==0)
        //    low.setVisibility(View.GONE);
        //
       // List<List<Object>> exams=null;
        try {
            exams = db.selectRecordsFromDBList("select * from exam" +
                    " where "+ exam.KEY_TYPE +"='"+String.valueOf((int)(Math.floor(Integer.parseInt(Catid))))+"'");
        } catch (NumberFormatException e) {
            this.getActivity().finish();
            // overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        } catch (Exception e) {
            this.getActivity().finish();
            //overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        if(exams==null|| exams.size()==0)
            quest.setVisibility(View.GONE);

    }

//////////////////////////////////



}


