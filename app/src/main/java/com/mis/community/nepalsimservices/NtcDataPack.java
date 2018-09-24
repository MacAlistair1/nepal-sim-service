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

public class NtcDataPack extends ListActivity {

    String pack[] = {"All Time Data Pack", "Night Data", "Social Media", "Streaming Pack", "Voice Pack(NT-NT only)", "4G Offer(KTM & PKH only)", "Night Voice(NT-NT only)", "SMS",
    "Weekend Pack", "Night Unlimited Data", "Voice", "1GB Per Day", "India Call", "1Hr Unlimited"};
    String num[] = {"*1415*11", "*1415*12", "*1415*13", "*1415*14", "*1415*15", "*1415*16", "*1415*17", "*1415*18",
    "*1415*2", "*1415*3", "*1415*4", "*1415*6", "*1415*7", "*1415*8"};

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

        final String number = num[position];

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
        final AlertDialog.Builder builder = new AlertDialog.Builder(NtcDataPack.this);
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
                        Intent go = new Intent(NtcDataPack.this, NtcDataPack.class);
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
                        Intent go = new Intent(NtcDataPack.this, NtcDataPack.class);
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
