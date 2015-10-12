package com.github.florent37.materialviewpager.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by diamond on 10/4/2015.
 */
public class aboutus extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        		Button button=(Button)findViewById(R.id.back);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                finish();
		        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
			}
		});
    }
}
