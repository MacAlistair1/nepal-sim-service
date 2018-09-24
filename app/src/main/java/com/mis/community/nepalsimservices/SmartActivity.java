package com.mis.community.nepalsimservices;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class SmartActivity extends AppCompatActivity {

    Button checkBal, remainVol, customerCare, bonus, tunes, num, dataPack, recharge;
    int sim1, sim2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart);
        context = this;

        checkBal = findViewById(R.id.balance);
        remainVol = findViewById(R.id.data);
        customerCare = findViewById(R.id.customer);
        bonus = findViewById(R.id.bonus);
        tunes = findViewById(R.id.tunes);
        recharge = findViewById(R.id.recharge);
        num = findViewById(R.id.number);
        dataPack = findViewById(R.id.pack);

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("prefs", MODE_PRIVATE);

        sim1 = sharedPreferences.getInt("sim1", 0);
        sim2 = sharedPreferences.getInt("sim2", 0);

        if (sim1 == 0 || sim2 == 0){
            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "*123")+Uri.encode("#")));
                    startActivity(bala);
                }
            });
            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent voice = new Intent(Intent.ACTION_CALL);
                    voice.setData(Uri.parse(Uri.parse("tel:"+"*141")+Uri.encode("#")));
                        startActivity(voice);

                }
            });
            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse("tel:" + "4242"));
                    startActivity(bala);

                }
            });
            bonus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "*143")+Uri.encode("#")));
                    startActivity(bala);
                }
            });
            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse("tel:" + "4260"));
                    startActivity(bala);

                }
            });
            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SmartActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge, null);
                    builder.setView(view);

                    final ImageButton rBtn;
                    final EditText editText;
                    editText= view.findViewById(R.id.pin);
                    rBtn= view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String number = editText.getText().toString();
                            if(number.isEmpty() || number.equals("") || number.length() <16 || number.length() >16){
                                editText.setError("Pin Number is Empty or Incorrect!!");
                            }else{
                                rBtn.setBackgroundResource(R.drawable.ok_dialog_pressed);
                                rBtn.setImageResource(R.drawable.ok_dialog_pressed);
                                Intent voice = new Intent(Intent.ACTION_CALL);
                                voice.setData(Uri.parse(Uri.parse("tel:"+"*122*"+number)+Uri.encode("#")));
                                startActivity(voice);
                            }
                        }
                    });
                    AlertDialog ouster = builder.create();
                    ouster.show();

                }
            });
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent voice = new Intent(Intent.ACTION_CALL);
                    voice.setData(Uri.parse(Uri.parse("tel:"+"*134")+Uri.encode("#")));
                    startActivity(voice);

                }
            });
            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(SmartActivity.this, SmartDataPack.class);
                    data.putExtra("sim1", 0);
                    data.putExtra("sim2", 0);
                    startActivity(data);

                }
            });

        }else if(sim1 == 3 && sim2 != 3){
            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*123", "#");
                }
            });
            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*141", "#");

                }
            });
            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("4242", "");

                }
            });
            bonus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*143", "#");

                }
            });
            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("4260", "");

                }
            });
            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SmartActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge, null);
                    builder.setView(view);

                    final ImageButton rBtn;
                    final EditText editText;
                    editText= view.findViewById(R.id.pin);
                    rBtn= view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String number = editText.getText().toString();
                            if(number.isEmpty() || number.equals("") || number.length() <16 || number.length() >16){
                                editText.setError("Pin Number is Empty or Incorrect!!");
                            }else{
                                rBtn.setBackgroundResource(R.drawable.ok_dialog_pressed);
                                rBtn.setImageResource(R.drawable.ok_dialog_pressed);
                                sim1Calling("*122*"+number, "#");
                            }
                        }
                    });
                    AlertDialog ouster = builder.create();
                    ouster.show();
                }
            });
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*134", "#");

                }
            });
            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(SmartActivity.this, SmartDataPack.class);
                    data.putExtra("sim1", 1);
                    data.putExtra("sim2", 0);
                    startActivity(data);

                }
            });


        }else if (sim2 == 3 && sim1 != 3){

            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*123", "#");
                }
            });
            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*141", "#");

                }
            });
            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("4242", "");

                }
            });
            bonus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*143", "#");

                }
            });
            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("4260", "");

                }
            });
            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SmartActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge, null);
                    builder.setView(view);

                    final ImageButton rBtn;
                    final EditText editText;
                    editText= view.findViewById(R.id.pin);
                    rBtn= view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String number = editText.getText().toString();
                            editText.setError("Pin Number is Empty or Incorrect!!");
                            if(!number.isEmpty() || !number.equals("") || number.length() ==16 || !(number.length() >16) || !(number.length() <16)){
                                rBtn.setBackgroundResource(R.drawable.ok_dialog_pressed);
                                rBtn.setImageResource(R.drawable.ok_dialog_pressed);
                                sim2Calling("*122*"+number, "#");
                            }
                        }
                    });
                    AlertDialog ouster = builder.create();
                    ouster.show();

                }
            });
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*134", "#");

                }
            });
            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(SmartActivity.this, SmartDataPack.class);
                    data.putExtra("sim1", 0);
                    data.putExtra("sim2", 1);
                    startActivity(data);

                }
            });

        }else if (sim1 == 3 && sim2 == 3){


            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*123", "#");
                }
            });
            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*141", "#");

                }
            });
            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("4242", "");

                }
            });
            bonus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*143", "#");

                }
            });
            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("4260", "");

                }
            });
            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SmartActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge, null);
                    builder.setView(view);

                    final ImageButton rBtn;
                    final EditText editText;
                    editText= view.findViewById(R.id.pin);
                    rBtn= view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String number = editText.getText().toString();
                            if(number.isEmpty() || number.equals("") || number.length() <16 || number.length() >16){
                                editText.setError("Pin Number is Empty or Incorrect!!");
                            }else{
                                rBtn.setBackgroundResource(R.drawable.ok_dialog_pressed);
                                rBtn.setImageResource(R.drawable.ok_dialog_pressed);
                                sim1Calling("*122*"+number, "#");
                            }
                        }
                    });
                    AlertDialog ouster = builder.create();
                    ouster.show();
                }
            });
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*134", "#");

                }
            });
            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(SmartActivity.this, SmartDataPack.class);
                    data.putExtra("sim1", 1);
                    data.putExtra("sim2", 1);
                    startActivity(data);

                }
            });

        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menuler,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int i = item.getItemId();

        if(i==android.R.id.home){
            SmartActivity.this.finish();
        }else if(i==R.id.abtus){
            Intent abt = new Intent(SmartActivity.this, About.class);
            startActivity(abt);
        }else if(i==R.id.exit){
            SmartActivity.this.finish();
        }else if(i==R.id.share){
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "Nepal Sim Services contains all the function of diff. Sims Get with it From :-" +
                    "https://www.play.google.com/store/apps/details?id=com.mis.community.nepalsimservices&hl=en");
            share.putExtra(Intent.EXTRA_SUBJECT,"Nepal Sim Services");
            startActivity(Intent.createChooser(share, "Share Via"));
        }else if(i==R.id.feed){
            Intent fed = new Intent(SmartActivity.this, feedBack.class);
            startActivity(fed);
        }else if(i==R.id.rate){
            try {
                Intent rate = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                rate.setData(Uri.parse("com.mis.community.nepalsimservices"));
                startActivity(rate);
            }catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.play.google.com/store/apps/details?id=com.mis.community.nepalsimservices&hl=en")));
            }
        }else if(i == R.id.clear){
            MyApplication.getInstance().clearApplicationData();
            finish();
            Intent fb = getPackageManager().getLaunchIntentForPackage("com.mis.community.nepalsimservices");
            startActivity(fb);
        }


        return super.onOptionsItemSelected(item);
    }

    public void MyDialog(final String num, final String has){
        final AlertDialog.Builder builder = new AlertDialog.Builder(SmartActivity.this);
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
                        Intent go = new Intent(SmartActivity.this, SmartActivity.class);
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
                        Intent go = new Intent(SmartActivity.this, SmartActivity.class);
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
