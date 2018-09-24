package com.mis.community.nepalsimservices;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class LauncherActivity extends Activity {

    public static int TIME = 2000;
    public static int TIME1 = 3000;
    TextView name;
    Context context;
    int sim1 = 0;
    int sim2 = 0;
    boolean first  = false;
    boolean shown  = false;

    private static final int CAMERA_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        context = this;
        name = (TextView) findViewById(R.id.name);

        final TextView tit = (TextView) findViewById(R.id.texttitle);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tit.setVisibility(View.VISIBLE);
                tit.setHovered(true);
                name.setVisibility(View.INVISIBLE);
            }
        },TIME);

        ActivityCompat.requestPermissions(LauncherActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST);

        func();

    }
    public void func(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);

                first = sharedPreferences.getBoolean("done", true);

                final SharedPreferences.Editor editor = sharedPreferences.edit();

                if (first) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(LauncherActivity.this);
                    transfering.setCancelable(false);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_sim_chooser, null);
                    transfering.setView(view);

                    final CheckBox ncell1, ntc1, smart1;
                    final CheckBox ncell2, ntc2, smart2;

                    Button goBtn;

                    ncell1 = view.findViewById(R.id.ncell1);
                    ncell2 = view.findViewById(R.id.ncell2);
                    ntc1 = view.findViewById(R.id.ntc1);
                    ntc2 = view.findViewById(R.id.ntc2);
                    smart1 = view.findViewById(R.id.smart1);
                    smart2 = view.findViewById(R.id.smart2);
                    goBtn = view.findViewById(R.id.goBtn);

                    ncell1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ntc1.setChecked(false);
                            smart1.setChecked(false);

                            sim1 = 1;
                        }
                    });

                    ntc1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ncell1.setChecked(false);
                            smart1.setChecked(false);

                            sim1 = 2;
                        }
                    });

                    smart1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ntc1.setChecked(false);
                            ncell1.setChecked(false);

                            sim1 = 3;
                        }
                    });

                    ncell2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ntc2.setChecked(false);
                            smart2.setChecked(false);

                            sim2 = 1;
                        }
                    });

                    ntc2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ncell2.setChecked(false);
                            smart2.setChecked(false);

                            sim2 = 2;
                        }
                    });

                    smart2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ntc2.setChecked(false);
                            ncell2.setChecked(false);

                            sim2 = 3;
                        }
                    });

                    goBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            shown = true;

                            if (!ncell1.isChecked() && !ntc1.isChecked() && !smart1.isChecked() && !ncell2.isChecked() && !ntc2.isChecked() && !smart2.isChecked()) {
                                Toast.makeText(getApplicationContext(), "You must checked at least one Sim.", Toast.LENGTH_LONG).show();
                                sim1 = 0;
                                sim2 = 0;
                            }else{


                                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("prefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("sim1", sim1);
                                editor.putInt("sim2", sim2);
                                editor.apply();

                                Intent go = new Intent(LauncherActivity.this, MainActivity.class);
                                startActivity(go);
                                finish();
                            }
                        }
                    });

                    if (!shown){

                        SharedPreferences sharedPreferences1 = getBaseContext().getSharedPreferences("prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.putInt("sim1", 0);
                        editor1.putInt("sim2", 0);
                        editor1.apply();

                    }




                    editor.putBoolean("done", false);
                    editor.apply();

                    AlertDialog ost = transfering.create();
                    ost.show();

                }

                if (!first){
                    Intent next = new Intent(LauncherActivity.this, MainActivity.class);
                    startActivity(next);
                    finish();
                }



            }
        },TIME1);
    }
}