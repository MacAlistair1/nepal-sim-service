package com.mis.community.nepalsimservices;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class DialerActivity extends Activity {

    TextView tv, sim1Text, sim2Text;
    int sim1, sim2;
    LinearLayout dial1, dial2;
    Context context;

    ImageButton call1;

    TelecomManager telecomManager;
    List<PhoneAccountHandle> phoneAccountHandleList;

    private final static String simSlotName[] = {
            "extra_asus_dial_use_dualsim",
            "com.android.phone.extra.slot",
            "slot",
            "simslot",
            "simSlot",
            "sim_slot",
            "subscription",
            "Subscription",
            "phone",
            "com.android.phone.DialingMode",
            "slot_id",
            "simId",
            "simnum",
            "phone_type",
            "slotId",
            "slotIdx"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);

        context = this;

        tv = findViewById(R.id.input);
        sim1Text = findViewById(R.id.sim1);
        sim2Text = findViewById(R.id.sim2);
        dial1 = findViewById(R.id.dial1);
        dial2 = findViewById(R.id.dial2);

        call1 = findViewById(R.id.btnCall1);


        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("prefs", MODE_PRIVATE);

        sim1 = sharedPreferences.getInt("sim1", 0);
        sim2 = sharedPreferences.getInt("sim2", 0);

        if (sim1 == 1 && sim2 == 0){
            sim1Text.setText("Ncell");
            dial2.setVisibility(View.GONE);
        }else if (sim1 == 0 && sim2 == 1){
            sim2Text.setText("Ncell");
            dial1.setVisibility(View.GONE);
        }else if (sim1 == 1 && sim2 == 2){
            sim1Text.setText("Ncell");
            sim2Text.setText("Ntc");
        }else if (sim2 == 1 && sim1 == 2){
            sim2Text.setText("Ncell");
            sim1Text.setText("Ntc");
        }else if (sim1 == 1 && sim2 == 3){
            sim2Text.setText("Smart");
            sim1Text.setText("Ncell");
        }else if (sim2 == 1 && sim1 == 3){
            sim1Text.setText("Smart");
            sim2Text.setText("Ncell");
        }else if (sim1 == 1 && sim2 == 1){
            sim1Text.setText("Ncell");
            sim2Text.setText("Ncell");
        }

        if (sim1 == 2 && sim2 == 0){
            sim1Text.setText("Ntc");
            dial2.setVisibility(View.GONE);
        }else if (sim1 == 0 && sim2 == 2){
            sim2Text.setText("Ntc");
            dial1.setVisibility(View.GONE);
        }else if (sim1 == 2 && sim2 == 3){
            sim2Text.setText("Smart");
            sim1Text.setText("Ntc");
        }else if (sim2 == 2 && sim1 == 3){
            sim1Text.setText("Smart");
            sim2Text.setText("Ntc");
        }else if (sim1 == 2 && sim2 == 2){
            sim1Text.setText("Ntc");
            sim2Text.setText("Ntc");
        }

        if (sim1 == 3 && sim2 == 0){
            sim1Text.setText("Smart");
            dial2.setVisibility(View.GONE);
        }else if (sim1 == 0 && sim2 == 3){
            sim2Text.setText("Smart");
            dial1.setVisibility(View.GONE);
        }else if (sim1 == 3 && sim2 == 3){
            sim1Text.setText("Smart");
            sim2Text.setText("Smart");
        }

        if (sim1 == 0 && sim2 == 0){
            sim1Text.setText("Unknown");
            dial2.setVisibility(View.GONE);
        }

        call1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"MissingPermission", "NewApi"})
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {


                if (sim1 == 0 || sim2 == 0){

                    String number = tv.getText().toString();
                    tv.setError("Input Box is Empty; Please Write a Number");
                    if(!number.equals("") || !number.isEmpty()){
                        if(number.contains("*") || number.contains("#") || number.endsWith("#") || number.startsWith("*")){
                            Intent bala = new Intent(Intent.ACTION_CALL);
                            String newNum = Arrays.toString(number.split("#"));
                            bala.setData(Uri.parse(Uri.parse("tel:" + newNum) + Uri.encode("#")));
                            {
                                startActivity(bala);
                            }
                        }else{
                            Intent bala = new Intent(Intent.ACTION_CALL);
                            bala.setData(Uri.parse("tel:" + number));
                            {
                                startActivity(bala);
                            }
                        }

                    }
                    tv.setText("");

                }else {

                    int simSel = 0;

                    telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
                    phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();


                    String number = tv.getText().toString();
                    tv.setError("Input Box is Empty; Please Write a Number");
                    if(!number.equals("") || !number.isEmpty()){
                        if(number.contains("*") || number.contains("#") || number.endsWith("#") || number.startsWith("*")){
                            Intent bala = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            String newNum = Arrays.toString(number.split("#"));
                            bala.setData(Uri.parse(Uri.parse("tel:" + newNum) + Uri.encode("#")));
                            bala.putExtra("com.android.phone.force.slot", true);
                            bala.putExtra("Cdma_Supp", true);
                            if (simSel == 0) {
                                for (String s : simSlotName)
                                    bala.putExtra(s, 0);

                                if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                                    bala.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(0));
                                }
                            } else {
                                for (String s : simSlotName)
                                    bala.putExtra(s, 1);

                                if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                                    bala.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(1));
                                }
                            }
                            context.startActivity(bala);
                        }else{
                            Intent bala = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            bala.setData(Uri.parse("tel:" + number));
                            bala.putExtra("com.android.phone.force.slot", true);
                            bala.putExtra("Cdma_Supp", true);
                            if (simSel == 0) {
                                for (String s : simSlotName)
                                    bala.putExtra(s, 0);

                                if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                                    bala.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(0));
                                }
                            } else {
                                for (String s : simSlotName)
                                    bala.putExtra(s, 1);

                                if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                                    bala.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(1));
                                }
                            }
                            context.startActivity(bala);
                        }

                    }
                    tv.setText("");

                }


            }
        });

    }

    public void one(View view) {
        tv.append("1");
    }
    public void two(View view) {
        tv.append("2");
    }
    public void three(View view) {
        tv.append("3");
    }
    public void four(View view) {
        tv.append("4");
    }
    public void five(View view) {
        tv.append("5");
    }
    public void six(View view) {
        tv.append("6");
    }
    public void seven(View view) {
        tv.append("7");
    }
    public void eight(View view) {
        tv.append("8");
    }
    public void nine(View view) {
        tv.append("9");
    }
    public void zero(View view) {
        tv.append("0");
    }
    public void star(View view) {
        tv.append("*");
    }
    public void hash(View view) {
        tv.append("#");
    }

    public void clear(View view) {
        tv.setText("");
    }
    public void back(View view){

        String backSpace;

        if (tv.getText().length() > 0){
            StringBuilder builder = new StringBuilder(tv.getText());
            builder.deleteCharAt(tv.getText().length() -1);
            backSpace = builder.toString();
            tv.setText(backSpace);
        }


    }

    @SuppressLint("MissingPermission")
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void call2(View view){

        if (sim1 == 0 || sim2 == 0){

            String number = tv.getText().toString();
            tv.setError("Input Box is Empty; Please Write a Number");
            if(!number.equals("") || !number.isEmpty()){

                if(number.contains("*") || number.contains("#") || number.endsWith("#") || number.startsWith("*")){
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    String newNum = Arrays.toString(number.split("#"));
                    bala.setData(Uri.parse(Uri.parse("tel:" + newNum) + Uri.encode("#")));
                    {
                        startActivity(bala);
                    }
                }else{
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse("tel:" + number));
                    {
                        startActivity(bala);
                    }
                }

            }
            tv.setText("");

        }else {

            int simSel = 1;

            telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
            phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();


            String number = tv.getText().toString();
            tv.setError("Input Box is Empty; Please Write a Number");
            if(!number.equals("") || !number.isEmpty()){

                if(number.contains("*") || number.contains("#") || number.endsWith("#") || number.startsWith("*")){
                    Intent bala = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    String newNum = Arrays.toString(number.split("#"));
                    bala.setData(Uri.parse(Uri.parse("tel:" + newNum) + Uri.encode("#")));
                    bala.putExtra("com.android.phone.force.slot", true);
                    bala.putExtra("Cdma_Supp", true);
                    if (simSel == 0) {
                        for (String s : simSlotName)
                            bala.putExtra(s, 0);

                        if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                            bala.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(0));
                        }
                    } else {
                        for (String s : simSlotName)
                            bala.putExtra(s, 1);

                        if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                            bala.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(1));
                        }
                    }
                    context.startActivity(bala);
                }else{
                    Intent bala = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    bala.setData(Uri.parse("tel:" + number));
                    bala.putExtra("com.android.phone.force.slot", true);
                    bala.putExtra("Cdma_Supp", true);
                    if (simSel == 0) {
                        for (String s : simSlotName)
                            bala.putExtra(s, 0);

                        if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                            bala.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(0));
                        }
                    } else {
                        for (String s : simSlotName)
                            bala.putExtra(s, 1);

                        if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                            bala.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(1));
                        }
                    }
                    context.startActivity(bala);
                }

            }
            tv.setText("");

        }


    }

}
