package com.github.florent37.materialviewpager.sample.fragment;

import java.util.Hashtable;
import java.util.List;
import com.github.florent37.materialviewpager.sample.R;
import com.github.florent37.materialviewpager.sample.Page;
import com.github.florent37.materialviewpager.sample.Sign;
import com.github.florent37.materialviewpager.sample.db.DbAdapter;
import com.github.florent37.materialviewpager.sample.db.exam;
import com.github.florent37.materialviewpager.sample.db.images;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by diamond on 9/30/2015.
 */
public class slider2 extends Activity
{
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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        contex=this;
        try {
            db=new DbAdapter(this);
        } catch (Exception e) {
            finish();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            return;
        }
        Catid=getIntent().getStringExtra("query");
        loadhash();
        setContent();
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
        this.setContentView(R.layout.slider2);
        line=(LinearLayout)findViewById(R.id.llhsv1);
        low=(ImageButton)findViewById(R.id.low);
        quest=(ImageButton)findViewById(R.id.questions);
        checkcount();
        low.setOnClickListener(lowbtn);
        quest.setOnClickListener(questbtn);
        //
        final float id;
        if(Catid.equals("05")||Catid.equals("08")||Catid.equals("36")||Catid.equals("37")
                ||Catid.equals("71")||Catid.equals("72"))
            id=Float.parseFloat(Catid)/10;
        else
            id=Float.parseFloat(Catid);

        int decode=(hash.get(Catid));
        if(decode==8||decode==9)
        {
            findViewById(R.id.scroll_view).setVisibility(View.GONE);
            return;
        }

        for( int i=0;i<counts[decode];i++)
        {
            final int temp=i;
            images img=null;
            try {
                img = db.fetchimage((int)(offset+distance*id+i));
            } catch (Exception e) {
                finish();
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                break;
            }
            //Bitmap b=Utilities.getImage(img.getData());
            final String name=img.getName();
            //final String idcontent=img.getName();
            SliderItem s=new SliderItem(contex,img);
            //
            line.addView(s.getView(img.getData(), title[decode][i]));
            //

            s.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScaleAnimation scale = new ScaleAnimation(1.1f, 0.9f, 1.1f, 0.9f,
                            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                            ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                    scale.setDuration(200);
                    scale.setRepeatCount(0);
                    //
                    v.startAnimation(scale);
                    if(temp==1 && Catid.equals("7"))
                    {
                        Intent i=new Intent(slider2.this,Sign.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                        return;
                    }
                    if(name.trim().equals("true"))
                    {
                        Intent i=new Intent(slider2.this,slider2.class);
                        i.putExtra("query", Catid+String.valueOf(temp));
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    }
                    else
                    {
                        Intent i=new Intent(slider2.this,Page.class);
                        i.putExtra("query", String.valueOf(name.trim()));
                        startActivity(i);
                        overridePendingTransition(R.anim.rotate3d_in_right,R.anim.rotate3d_out_left);
                    }


                }
            });
        }

    }

    private void checkcount()
    {

        List<List<Object>> rules=null;
        try {
            rules = db.selectRecordsFromDBList("select * from rules" +
                    " where category='"+String.valueOf((int)(Math.floor(Integer.parseInt(Catid))))+"'");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            finish();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        if(rules==null||rules.size()==0)
            low.setVisibility(View.GONE);
        //
        List<List<Object>> exams=null;
        try {
            exams = db.selectRecordsFromDBList("select * from exam" +
                    " where "+ exam.KEY_TYPE +"='"+String.valueOf((int)(Math.floor(Integer.parseInt(Catid))))+"'");
        } catch (NumberFormatException e) {
            finish();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        } catch (Exception e) {
            finish();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }
        if(exams==null|| exams.size()==0)
            quest.setVisibility(View.GONE);

    }
    View.OnClickListener lowbtn=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ScaleAnimation scale = new ScaleAnimation(1.1f, 0.9f, 1.1f, 0.9f,
                    ScaleAnimation.RELATIVE_TO_SELF, 1f,
                    ScaleAnimation.RELATIVE_TO_SELF, 1f);
            scale.setDuration(200);
            scale.setRepeatCount(0);
            //
            v.startAnimation(scale);
            try{
                Toast.makeText(getApplicationContext(),String.valueOf((int)(Math.floor(Integer.parseInt(Catid)))),
                        Toast.LENGTH_LONG).show();
//                Intent i=new Intent(slider2.this, RuleItem.class);
//                i.putExtra("query","select * from rules" +
//                        " where category='"+String.valueOf((int)(Math.floor(Integer.parseInt(Catid))))+"'" );
//                //i.putExtra("query",String.valueOf(position));
//                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }catch(Exception e)

            {
                e.printStackTrace();
            }
        }
    };
    View.OnClickListener questbtn=new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ScaleAnimation scale = new ScaleAnimation(1.1f, 0.9f, 1.1f, 0.9f,
                    ScaleAnimation.RELATIVE_TO_SELF, 1f,
                    ScaleAnimation.RELATIVE_TO_SELF, 1f);
            scale.setDuration(200);
            scale.setRepeatCount(0);
            //
            v.startAnimation(scale);
           // Intent i=new Intent(slider2.this, QuestionPage.class);
            Toast.makeText(getApplicationContext(),String.valueOf((int)(Math.floor(Integer.parseInt(Catid)))),
                    Toast.LENGTH_LONG).show();
//            i.putExtra("query","select * from exam" +
//                    " where "+ exam.KEY_TYPE +"='"+String.valueOf((int)(Math.floor(Integer.parseInt(Catid))))+"'" );
//            //i.putExtra("query",String.valueOf(position));
//            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

        }
    };
}

