package com.mis.community.nepalsimservices;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by MACPC on 1/19/2018.
 */

public class About extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        final ImageView img =(ImageView) findViewById(R.id.mis);
        final ImageView img1 =(ImageView) findViewById(R.id.aj);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                img1.setVisibility(View.VISIBLE);
            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                img.setVisibility(View.VISIBLE);
            }
        },2000);
    }
}