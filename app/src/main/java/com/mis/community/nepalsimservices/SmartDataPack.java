package com.mis.community.nepalsimservices;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class SmartDataPack extends ListActivity{

    String pack[] = {"Daily Rental Voice Pack", "Weekly Voice Pack", "15days Voice Pack", "Monthly Voice Pack","International Voice Pack",
            "Monthly SmS Pack", "Daily 2G Data", "Weekly 2G Data", "15days 2G Data", "Monthly 2G Data",
            "First Recharge Offer", "Special Offer", "Daily 4G Data", "Weekly 4G Data", "15days 4G Data",
            "Monthly 4G Data", "Unlimited 4G Night Data"};

    String num1[] = {"*141*1*1", "*141*1*2", "*141*1*3", "*141*1*4", "*141*1*5", "*141*2*1",
            "*141*3*1","*141*3*2", "*141*3*3", "*141*3*4", "*141*4", "*141*5", "*141*6*1", "*141*6*2",
            "*141*6*3", "*141*6*4", "*141*6*5"};

    Context context;
    int sim1, sim2;



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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.ncelldatalist, pack);
        setListAdapter(adapter);
        sim1 = getIntent().getIntExtra("sim1", 0);
        sim2 = getIntent().getIntExtra("sim2", 0);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        final String number = num1[position];

        if (sim1 == 0 || sim2 == 0){

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse(Uri.parse("tel:"+ number)+Uri.encode("#")));
            startActivity(intent);

        }else if (sim1 == 1 && sim2 != 1){
            sim1Calling(number, "#");

        }else if (sim2 == 1 && sim1 != 1){
            sim2Calling(number, "#");

        }else if (sim2 == 1 && sim1 == 1){
            MyDialog(number, "#");
        }
    }

    public void MyDialog(final String num, final String has){
        final AlertDialog.Builder builder = new AlertDialog.Builder(SmartDataPack.this);
        builder.setCancelable(true);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.simchoose_activity, null);
        builder.setView(view);

        LinearLayout sim1Call, sim2Call;

        sim1Call = view.findViewById(R.id.sim1Call);
        sim2Call = view.findViewById(R.id.sim2Call);


        sim1Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sim1 Calling.........", Toast.LENGTH_SHORT).show();
                sim1Calling(num, has);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        Intent go = new Intent(SmartDataPack.this, SmartDataPack.class);
                        startActivity(go);
                    }
                },10000);




            }
        });

        sim2Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Sim2 Calling.........", Toast.LENGTH_SHORT).show();
                sim2Calling(num, has);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        Intent go = new Intent(SmartDataPack.this, SmartDataPack.class);
                        startActivity(go);
                    }
                },10000);
            }
        });



        AlertDialog ouster = builder.create();
        ouster.show();
    }

    public void sim1Calling(String nos, String enco){

        int simSel = 0;

        telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
        phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();


        Intent sim1 = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sim1.setData(Uri.parse(Uri.parse("tel:"+ nos)+Uri.encode(enco)));
        sim1.putExtra("com.android.phone.force.slot", true);
        sim1.putExtra("Cdma_Supp", true);

        if (simSel == 0) {
            for (String s : simSlotName)
                sim1.putExtra(s, 0);

            if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                sim1.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(0));
            }
        } else {
            for (String s : simSlotName)
                sim1.putExtra(s, 1);

            if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                sim1.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(1));
            }
        }
        context.startActivity(sim1);
    }

    public void sim2Calling(String nos, String enco){
        int simSel = 1;

        telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
        phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();

        Intent sim2 = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sim2.setData(Uri.parse(Uri.parse("tel:"+ nos)+Uri.encode(enco)));
        sim2.putExtra("com.android.phone.force.slot", true);
        sim2.putExtra("Cdma_Supp", true);

        if (simSel == 0) {
            for (String s : simSlotName)
                sim2.putExtra(s, 0);

            if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                sim2.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(0));
            }
        } else {
            for (String s : simSlotName)
                sim2.putExtra(s, 1);

            if (phoneAccountHandleList != null && phoneAccountHandleList.size() > 0) {
                sim2.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandleList.get(1));
            }
        }
        context.startActivity(sim2);
    }
}
