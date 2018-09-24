package com.mis.community.nepalsimservices;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class important extends ListActivity {
    Context context;

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


    ClipboardManager cbm;
    ClipData cd;

    String pack[] = {"सीधा कुरा जनता संग ", "Police Control Room", "Traffic Control Room", "Fire Brigade",
            "Tourist Police[KTM]", "Lost & Found HotLine", "Prithvi Highway Bus",

            "Sajha Bus", "Araniko Bus", "Tribhuban University", "Pokhara University", "Purbanchal University",
            "Lumbini Buddha University",

            "Nepal Sanskrit University", "Kathmandu University", "Nepal Bank", "Nepal Rastra Bank", "Nepal SBI Bank", "Nepal Investment Bank", "Prabhu Bank", "Red Cross Blood Bank-1",

            "Red Cross Blood Bank-2", "Blood Bank Pokhara", "Buddha Air", "Yeti Air", "Nepal Air", "Sita Air",
            "Nepal Eye Bank",

            "Chitwan Cancer Hospital", "Pokhara Gandaki Hospital", "Dharan B.P Koirala",
            "Nepaljung Bheri Zonal Hospital", "Nepal Eye Hospital", "बीर अस्पताल -१ ", "बीर अस्पताल -२",

            "TU Teaching Hospital-1", "TU Teaching Hospital-2"};
    String num1[] = {"16600112424", "100", "103", "101", "014226359", "9860312211", "061520265",
            "015552686", "016631147", "014274924", "061561046", "9844076000", "015527283",
            "0824221510", "011661399", "16600137373", "014410158", "014435613", "014228229", "014788500", "014229344",
            "014225344", "061521091", "061465997", "014464878", "014248614", "014110710",
            "014493684", "056523747", "061520066", "025525555", "081522458", "014250691", "014223807",
            "014221988", "014412505", "014412504"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important);
        context = this;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.ncelldatalist, pack);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        final String num = num1[position];

        SharedPreferences sharedPreferences = context.getSharedPreferences("prefs", MODE_PRIVATE);

        int sim1 = sharedPreferences.getInt("sim1", 0);
        int sim2 = sharedPreferences.getInt("sim2", 0);


        if (sim1 == 0 || sim2 == 0) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel: " + num));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            startActivity(callIntent);
        }else {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.simchoose_activity, null);
        builder.setView(view);

        LinearLayout sim1Call, sim2Call;
        TextView sim1T, sim2T;

        sim1Call = view.findViewById(R.id.sim1Call);
        sim2Call = view.findViewById(R.id.sim2Call);

        sim1T = view.findViewById(R.id.sim1T);
        sim2T = view.findViewById(R.id.sim2T);
        if (sim1 == 1){
            sim1T.setText("Ncell");
        }else if(sim1 == 2){
            sim1T.setText("Ntc");
        }else if(sim1 == 3){
            sim1T.setText("Smart");
        }

            if (sim2 == 1){
                sim2T.setText("Ncell");
            }else if(sim2 == 2){
                sim2T.setText("Ntc");
            }else if(sim2 == 3){
                sim2T.setText("Smart");
            }




            sim1Call.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @TargetApi(Build.VERSION_CODES.M)
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {

                    int simSel = 0;

                    telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
                    phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();


                    Intent sim1 = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sim1.setData(Uri.parse("tel:" + num));
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
            });

            sim2Call.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @TargetApi(Build.VERSION_CODES.M)
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    int simSel = 1;

                    telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
                    phoneAccountHandleList = telecomManager.getCallCapablePhoneAccounts();

                    Intent sim2 = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    sim2.setData(Uri.parse("tel:" + num));
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
            });

        AlertDialog ouster = builder.create();
        ouster.show();

        }
    }
}
