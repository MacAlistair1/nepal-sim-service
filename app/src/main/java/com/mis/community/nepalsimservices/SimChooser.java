package com.mis.community.nepalsimservices;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SimChooser extends Activity {

    CheckBox ncell1, ntc1, smart1;
    CheckBox ncell2, ntc2, smart2;
    int sim1 = 0, sim2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_chooser);

        ncell1 = findViewById(R.id.ncell1);
        ncell2 = findViewById(R.id.ncell2);
        ntc1 = findViewById(R.id.ntc1);
        ntc2 = findViewById(R.id.ntc2);
        smart1 = findViewById(R.id.smart1);
        smart2 = findViewById(R.id.smart2);

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


    }

    public void btnGo(View view) {

        if (!ncell1.isChecked() && !ntc1.isChecked() && !smart1.isChecked() && !ncell2.isChecked() && !ntc2.isChecked() && !smart2.isChecked()){
            Toast.makeText(getApplicationContext(), "You must checked at least one Sim.", Toast.LENGTH_LONG).show();
            sim1 = 0;
            sim2 = 0;
        }else{



            Log.d("Sim1:", String.valueOf(sim1));
            Log.d("Sim2:", String.valueOf(sim2));

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sim1", sim1);
        editor.putInt("sim2", sim2);
        editor.apply();

        Intent go = new Intent(this, MainActivity.class);
        startActivity(go);
        finish();
        }

    }
}
