package com.github.florent37.materialviewpager.sample;

import android.app.ActivityManager;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.github.florent37.materialviewpager.sample.db.exam;
import com.github.florent37.materialviewpager.sample.fragment.Attach;
import com.github.florent37.materialviewpager.sample.fragment.Exam;
import com.github.florent37.materialviewpager.sample.fragment.QuestionPage;
import com.github.florent37.materialviewpager.sample.fragment.RecyclerViewFragment;
import com.github.florent37.materialviewpager.sample.fragment.RuleItem;
import com.github.florent37.materialviewpager.sample.fragment.Slider;
import com.github.florent37.materialviewpager.sample.fragment.slider2;
import com.github.florent37.materialviewpager.sample.menu.CustomMenu;
import com.github.florent37.materialviewpager.sample.menu.CustomMenuItem;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.fabric.sdk.android.Fabric;

import android.view.ViewGroup;

import java.util.List;

import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

   public static MaterialViewPager mViewPager;
//
    private ViewGroup mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
//    private Toolbar toolbar;
private static final long RIPPLE_DURATION = 250;


    static String extra2;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.root)
    FrameLayout root;
    @InjectView(R.id.content_hamburger)
    View contentHamburger;
    private CustomMenu mMenu;

    /////////
    boolean Isclickable=true;
    ImageView search,closesearch;
    Button imagebuttonsearch;
    EditText edit;
    private NotificationManager notifyMgr=null;
    private static final int NOTIFY_ME_ID=1337;
    private int count=0;

    static String tab_name;
    ImageButton back;
    String back_fragment;
    int level=0;

    ////////
    CheckBox cb1,cb2,cb3;
    int key=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity);
        ButterKnife.inject(this);




        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);

        }



        TextView txt=(TextView)findViewById(R.id.tabloha_txt);
        txt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,MainActivity.class);
                i.putExtra("query", "7");

                startActivity(i);
            }
        });
        TextView txt_amozesh=(TextView)findViewById(R.id.amozesh);
        txt_amozesh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("query", "Rule");

                startActivity(i);


            }
        });
        TextView txt_azmon=(TextView)findViewById(R.id.azmon);
        txt_azmon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("query", "exam");

                startActivity(i);
            }
        });
        TextView txt_aboutus=(TextView)findViewById(R.id.aboutus);
        txt_aboutus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, aboutus.class);

                startActivity(i);

            }
        });
        TextView txt_zamaem=(TextView)findViewById(R.id.zamaem);
        txt_zamaem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("query", "attach");

                startActivity(i);

            }
        });

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);


        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .build();

        if (!BuildConfig.DEBUG)
            Fabric.with(this, new Crashlytics());
//
        setTitle("");

        mMenu = new CustomMenu(MainActivity.this,null, getLayoutInflater());
        mMenu.setHideOnSelect(true);
        mMenu.setItemsPerLineInPortraitOrientation(3);
        mMenu.setItemsPerLineInLandscapeOrientation(8);

        ///////////
        search=(ImageView)findViewById(R.id.ser);




        if(com.github.florent37.materialviewpager.sample.attrib.attribute.pre!=null)
            com.github.florent37.materialviewpager.sample.attrib.attribute.pre.finish();

        Runnable runable=new Runnable() {

            @Override
            public void run() {
                Animation anim=(Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim2);
                search.setVisibility(VISIBLE);
                search.startAnimation(anim);

            }
        };
        notifyMgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        handler.postDelayed(runable, 3000);
        search.setOnTouchListener(touch);




        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        if(getIntent().getStringExtra("query")!=null) {
            extra2= getIntent().getStringExtra("query");
            if(getIntent().getStringExtra("query")!=null) {
                tab_name = getIntent().getStringExtra("tabname");
            }
        }else{extra2="7";}


        initialViewPager(extra2);



        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green,
                                getApplicationContext().getResources().getDrawable(R.drawable.bbacc));
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.blue,
                                getApplicationContext().getResources().getDrawable(R.drawable.bbacc));
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.cyan,
                                getApplicationContext().getResources().getDrawable(R.drawable.bbacc));
                    case 3:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.red,
                                getApplicationContext().getResources().getDrawable(R.drawable.bbacc));
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

    }



    public void clearNotification(View v) {
        notifyMgr.cancel(NOTIFY_ME_ID);
    }



    Handler handler=new Handler();


    View.OnTouchListener touch=new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {


            Isclickable=true;
            final Dialog dialog = new Dialog(MainActivity.this,android.R.style.Theme_Translucent_NoTitleBar);
            dialog.setContentView(R.layout.search);
            edit=(EditText) dialog.findViewById(R.id.editText1);
            Button dialogButton = (Button) dialog.findViewById(R.id.sdB);
            ImageView image=null;
            try{
                image = (ImageView) dialog.findViewById(R.id.closesearchbtn);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
            image.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dialog.dismiss();
                    return false;
                }
            });

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(edit.getText().toString().trim().length()==0)
                    {
                        edit.setBackgroundColor(Color.RED);
                        createToast(4000, "لطفا متن مورد نظر را وارد کنید");

                    }
                    else
                    {
                        edit.setBackgroundColor(Color.BLACK);

//
                        initialViewPager("search");
                        edit.setText("");
                        dialog.dismiss();
                        Isclickable=true;


                    }
                }
            });
            try{
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if(keyCode==KeyEvent.KEYCODE_BACK)
                        {
                            dialog.dismiss();
                            Isclickable=true;
                            return false;
                        }
                        return false;
                    }
                });
                dialog.getWindow().getAttributes().windowAnimations = R.style.SearchDialogAnimation;
                dialog.show();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return false;
        }
    };



    public  void  createToast(int duration,String str)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(str);
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title1);
        text.setTypeface(face);
        text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_size);
        text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_color);
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(duration);
        toast.setView(layout);
        toast.show();
    }



    public  void initialViewPager(final String extra)
    {

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

                @Override
                public Fragment getItem(int position) {
                    switch (position % 3) {
                        case 0:
                            if(extra.equals("search")){

                                return Search.newInstance(edit.getText().toString(),MainActivity.this);
                            }
                            if(extra.equals("ostan")){
                                Ostan.back_frament="attach";
                                Ostan.level=1;
                                return Ostan.newInstance(MainActivity.this, "school");
                            }
                            if(extra.equals("pelak")){
                                Pelak.back_frament="attach";
                                Pelak.level=1;
                                return Pelak.newInstance(MainActivity.this);
                            }
                            if(extra.equals("document")){
                                Document.back_frament="attach";
                                Document.level=1;
                                return Document.newInstance(MainActivity.this);
                            }
                            if(extra.equals("police")){
                                Police.back_frament="attach";
                                Police.level=1;
                                return Police.newInstance(MainActivity.this);
                            }
                            if(extra.equals("attach")){
                                return Attach.newInstance(MainActivity.this);
                            }
                            if(extra.equals("sign")){
                                Sign.back_frament="7";
                                Sign.level=1;
                                return Sign.newInstance(MainActivity.this);
                            }
                            if( extra.equals("8")||extra.equals("9")){
                                RuleItem.back_frament="Rule";
                                RuleItem.level=1;
                                return RuleItem.newInstance("select * from rules" +
                                " where category='" + String.valueOf((int) (Math.floor(Integer.parseInt(extra)))) + "'",MainActivity.this);
                            }
                            if(extra.equals("exam")){

                                return Exam.newInstance();
                            }

                            if(extra.equals("Rule")){
                                return Rule.newInstance(MainActivity.this);
                            }
                            if(extra.equals("0") ||extra.equals("1")||extra.equals("2")||extra.equals("3")
                             ||extra.equals("4")||extra.equals("5")||extra.equals("6")||
                               extra.equals("10")|| extra.equals("7")||extra.equals("71") ||
                                extra.equals("72")|| extra.equals("05")||extra.equals("08")
                               || extra.equals("36") || extra.equals("37")) {

                                ///////for back bt
                                if(extra.equals("7")){

                                    Slider.back_frament="Rule";
                                    Slider.level=1;
                                    return Slider.newInstance(extra, MainActivity.this);
                                }
                                if(extra.equals("0") ||extra.equals("1")||extra.equals("2")||extra.equals("3")
                                        ||extra.equals("4")||extra.equals("5")||extra.equals("6")
                                        ||extra.equals("10")){
                                    back_fragment="Rule";
                                    level=1;


                                    Slider.back_frament="Rule";
                                    Slider.level=1;
                                    return Slider.newInstance(extra, MainActivity.this);
                                }
                                if(extra.equals("71") ||
                                        extra.equals("72")|| extra.equals("05")||extra.equals("08")
                                        || extra.equals("36") || extra.equals("37")){
                                    back_fragment="Rule";
                                    level=1;
                                    Slider.back_frament="7";
                                    Slider.level=1;
                                    return Slider.newInstance(extra, MainActivity.this);
                                }

                            }

                        case 1:
                            if( extra.equals("8")||extra.equals("9")){

                                return QuestionPage.newInstance("select * from exam" +
                                " where " + exam.KEY_TYPE + "='" + extra + "'",MainActivity.this);
                            }
                            if(extra.equals("7")  ){

                                return RuleItem.newInstance("select * from rules" +
                                " where category='" + String.valueOf((int) (Math.floor(Integer.parseInt("7")))) + "'",MainActivity.this);
                            }
                        case 2:
                            if(extra.equals("7")  ) {

                                return QuestionPage.newInstance("select * from exam" +
                                        " where " + exam.KEY_TYPE + "='" + "7" + "'",MainActivity.this);
                            }
                        default:
                            return RecyclerViewFragment.newInstance();

                    }
                }

                @Override
                public int getCount() {
                    return 3;
                }


                @Override
                public CharSequence getPageTitle(int position) {
                    switch (position % 4) {
                        case 0:
                            if(extra.equals("search")){return "جستجو";}
                            if(extra.equals("ostan")){return "آموزشگاه های رانندگی";}
                            if(extra.equals("pelak")){return "تعویض پلاک";}
                            if(extra.equals("document")){
                                return "مدارک اخذ گواهینامه";
                            }
                            if(extra.equals("police")){
                                return "مراکز پلیس +10";
                            }
                            if(extra.equals("attach")){return "ضمائم";
                            }
                            if(extra.equals("71") || extra.equals("72")|| extra.equals("05")||extra.equals("08")
                             || extra.equals("36") || extra.equals("37")|| extra.equals("7")|| extra.equals("sign")) {
                                     return getString(R.string.tab1);}
                             if(extra.equals("0") ||extra.equals("1")||extra.equals("2")||extra.equals("3")
                             ||extra.equals("4")||extra.equals("5")||extra.equals("6")||
                              extra.equals("10")){
                                 return tab_name;}
                            if( extra.equals("8")||extra.equals("9")){
                                return "قوانین";
                            }
                            if(extra.equals("exam")){
                                return "آزمون";
                            }
                            if(extra.equals("Rule")){
                                return "آموزش";
                            }
                        case 1:
                            if( extra.equals("8")||extra.equals("9")){
                                return "سوالات";
                            }
                            if(extra.equals("7")  ){
                                return getString(R.string.tab2);
                            }
                        case 2:
                            if(extra.equals("7")  ) {
                                return getString(R.string.tab3);
                            }

                    }
                    return "";
                }
            });



        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        View logo = findViewById(R.id.logo_white);
        if (logo != null)
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        mViewPager.notifyHeaderChanged();
    }


    private void doMenu() {
        if (mMenu.isShowing()) {
            mMenu.hide();
        } else {
            //Note it doesn't matter what widget you send the menu as long as it gets view.
            mMenu.show(this.findViewById(R.id.rel1));
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Handle the back button

        ActivityManager mngr = (ActivityManager) getSystemService( ACTIVITY_SERVICE );

        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        if(taskList.get(0).numActivities == 1 &&
                taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                //Ask the user if they want to quit
                if (mMenu.isShowing()) {
                    doMenu();
                    return true; //always eat it!
                }
                final Dialog dialog = new Dialog(MainActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                dialog.setContentView(R.layout.custom);
                Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/" + com.github.florent37.materialviewpager.sample.attrib.attribute.font_title);
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("قصد خروج از برنامه را دارید؟");
                text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_size);
                text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title_font_color);
                text.setTypeface(face);
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.exit);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                //dialogButton.setText(PersianReshape.reshape("بله	"));
                //dialogButton.setTypeface(face);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //handler1.removeCallbacks(updaterun);
//                    Calendar c = Calendar.getInstance();
//                    int month = c.get(Calendar.MONTH);
//                    int day=c.get(Calendar.DAY_OF_MONTH);
//                    if(month==Calendar.MARCH||(month==Calendar.APRIL&&day<10))
//                        startActivity(new Intent(MainActivity.this,splash2.class));
                      //  finish();
                        finish();
                    }
                });
                Button dialogButton1 = (Button) dialog.findViewById(R.id.dialogButtonNo);
                //dialogButton1.setText(PersianReshape.reshape("خیر"));
                //dialogButton1.setTypeface(face);
                // if button is clicked, close the custom dialog
                dialogButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                try {
                    dialog.getWindow().getAttributes().windowAnimations = R.style.SearchDialogAnimation;
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_MENU) {
                doMenu();
                return true; //always eat it!
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }else{
            finish();
            return true;}
    }

}
