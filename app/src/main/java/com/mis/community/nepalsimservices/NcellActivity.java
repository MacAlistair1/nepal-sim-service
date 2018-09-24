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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class NcellActivity extends AppCompatActivity {

    Context context;
    int sim1, sim2;


    Button checkBal, remainVol, customerCare, tunes, recharge, num, time, dataPack, voicePack, smsPack, transferAmt, my5, loan, love, rechargeOther;

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
        setContentView(R.layout.activity_ncell);
        context = this;



        checkBal = findViewById(R.id.balance);
        remainVol = findViewById(R.id.data);
        customerCare = findViewById(R.id.customer);
        tunes = findViewById(R.id.tunes);
        recharge = findViewById(R.id.recharge);
        time = findViewById(R.id.time);
        num = findViewById(R.id.number);
        dataPack = findViewById(R.id.pack);
         smsPack = findViewById(R.id.sms);
         voicePack = findViewById(R.id.voice);
         loan = findViewById(R.id.loan);
         love = findViewById(R.id.love);
         rechargeOther = findViewById(R.id.rechargeOthers);
        transferAmt = findViewById(R.id.transfer);
        my5 = findViewById(R.id.my5);


        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("prefs", MODE_PRIVATE);

        sim1 = sharedPreferences.getInt("sim1", 0);
        sim2 = sharedPreferences.getInt("sim2", 0);

        if (sim1 == 0 || sim2 == 0){

            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "*101")+Uri.encode("#")));
                    startActivity(bala);
                }
            });

            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "*101")+Uri.encode("#")));
                    startActivity(bala);
                }
            });

            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "9005")+Uri.encode("")));
                    startActivity(bala);
                }
            });

            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "9208")+Uri.encode("")));
                    startActivity(bala);
                }
            });

            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
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
                                Intent call = new Intent(Intent.ACTION_CALL);
                                call.setData(Uri.parse(Uri.parse("tel:"+ "*102*"+number)+Uri.encode("#")));
                                startActivity(call);

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
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "*103")+Uri.encode("#")));
                    startActivity(bala);
                }
            });

            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "*17123")+Uri.encode("#")));
                    startActivity(bala);
                }
            });

            transferAmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(NcellActivity.this);
                    transfering.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ncell_transfer, null);
                    transfering.setView(view);

                    final EditText phNo, rupee;
                    Button tranBtn;

                    phNo = view.findViewById(R.id.phNo);
                    rupee = view.findViewById(R.id.rupee);
                    tranBtn = view.findViewById(R.id.tranBtn);

                    tranBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = phNo.getText().toString();
                            String Rs = rupee.getText().toString();

                            if(Number.isEmpty() || Number.equals("")){
                                Toast.makeText(getApplicationContext(),"Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Number.length()<10 || Number.length()>10){
                                Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                phNo.setText("");
                            }else if(Rs.isEmpty() || Rs.equals("")){
                                Toast.makeText(getApplicationContext(),"Transfer Amount is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Rs.length()<2){
                                Toast.makeText(getApplicationContext(),"Can Only Transfer Greater than 10 Rupees.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Number.length() == 10 && Rs.length() >= 2){
                                Intent voice = new Intent(Intent.ACTION_CALL);
                                voice.setData(Uri.parse(Uri.parse("tel:"+"*17122*"+Number+"*"+Rs)+Uri.encode("#")));
                                    startActivity(voice);
                            }
                        }
                    });
                    AlertDialog oust = transfering.create();
                    oust.show();

                }
            });

            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:"+ "*104")+Uri.encode("#")));
                    startActivity(bala);
                }
            });

            smsPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sms = new Intent(Intent.ACTION_CALL);
                    sms.setData(Uri.parse(Uri.parse("tel:"+"*17119")+Uri.encode("#")));
                        startActivity(sms);
                }
            });

            voicePack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent voice = new Intent(Intent.ACTION_CALL);
                    voice.setData(Uri.parse(Uri.parse("tel:"+"*17118")+Uri.encode("#")));
                    startActivity(voice);
                }
            });

            loan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
                    builder.setTitle("Are You Sure ...");
                    builder.setMessage("तपाई साचै सापटी लिन चाहनुहुन्छ?\n" +
                            "सापटी लिनको लागी:-\n" +
                            "१.मैन ब्यालेन्स Rs.5 वा सो भन्दा कम हुनुपर्छ"+"\n" +
                            "२.सिम खरीद गरेको ६० दिन नाग्नुपर्छ"+"!");
                    builder.setIcon(R.drawable.misicon);
                    builder.setCancelable(false);
                    builder.setPositiveButton("हो", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            AlertDialog.Builder building = new AlertDialog.Builder(NcellActivity.this);
                            building.setTitle("Choose One:");
                            building.setMessage("Take 40 Rs. Loan.");
                            building.setIcon(R.drawable.misicon);
                            building.setCancelable(false);
                            building.setPositiveButton("Through Direct Call", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent bala = new Intent(Intent.ACTION_CALL);
                                    bala.setData(Uri.parse(Uri.parse("tel:"+ "*9988")+Uri.encode("#")));

                                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                        if(checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                                            Toast.makeText(getApplicationContext(),"Unissued Call Permission",Toast.LENGTH_SHORT).show();
                                            requestPermissions(new String[] {Manifest.permission.CALL_PHONE},10);
                                            return;
                                        }
                                    }
                                    startActivity(bala);
                                }
                            });

                            building.setNegativeButton("Through Internet", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent fb = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://portal.ncell.axiata.com/na"));
                                    startActivity(fb);
                                }
                            });

                            building.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog call = building.create();
                            call.show();

                        }
                    });

                    builder.setNeutralButton("रद्द गर्नुहोस् ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();
                }
            });

            my5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
                    builder.setTitle("MY5 Service");
                    builder.setMessage("If you Activate this service you can add 5 Ncell Numbers in MY5 group and you can talk to them at only 99 paisa per minute.\n Touch Start to " +
                            "activate/deactivate , add new number , delete exist number and Modify the List... ");
                    builder.setIcon(R.drawable.misicon);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent voice = new Intent(Intent.ACTION_CALL);
                            voice.setData(Uri.parse(Uri.parse("tel:"+"*5599")+Uri.encode("#")));
                                startActivity(voice);
                        }
                    });

                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();
                }
            });

            love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent action = new Intent(Intent.ACTION_CALL);
                    action.setData(Uri.parse("tel:"+ "17143"));
                        startActivity(action);
                }
            });

            rechargeOther.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(NcellActivity.this);
                    transfering.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge_other, null);
                    transfering.setView(view);

                    final EditText phNo, rupee;
                    Button tranBtn;

                    phNo = view.findViewById(R.id.phNo);
                    rupee = view.findViewById(R.id.rupee);
                    tranBtn = view.findViewById(R.id.tranBtn);

                    tranBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = phNo.getText().toString();
                            String Rs = rupee.getText().toString();

                            if(Number.isEmpty() || Number.equals("")){
                                Toast.makeText(getApplicationContext(),"Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Number.length()<10 || Number.length()>10){
                                Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                phNo.setText("");
                            }else if(Rs.isEmpty() || Rs.equals("")){
                                Toast.makeText(getApplicationContext(),"Pin Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Rs.length()<16){
                                Toast.makeText(getApplicationContext(),"Pin Number is Less than 16 digit.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Rs.length()<16){
                                Toast.makeText(getApplicationContext(),"Pin Number is Greater than 16 digit.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Number.length() == 10 && Rs.length() == 16){
                                Intent voice = new Intent(Intent.ACTION_CALL);
                                voice.setData(Uri.parse(Uri.parse("tel:"+"*102*"+Rs+"*"+Number)+Uri.encode("#")));
                                    startActivity(voice);
                            }
                        }
                    });
                    AlertDialog oust = transfering.create();
                    oust.show();

                }
            });


        }else if (sim1 == 1 && sim2 != 1){

            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*101", "#");
                }
            });

            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*101", "#");
                }
            });

            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("9005", "");
                }
            });

            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("9208", "");
                }
            });

            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
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
                                sim1Calling("*102*"+number, "#");

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
                    sim1Calling("*103", "#");

                }
            });

            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  sim1Calling("*17123", "#");
                }
            });

            transferAmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(NcellActivity.this);
                    transfering.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ncell_transfer, null);
                    transfering.setView(view);

                    final EditText phNo, rupee;
                    Button tranBtn;

                    phNo = view.findViewById(R.id.phNo);
                    rupee = view.findViewById(R.id.rupee);
                    tranBtn = view.findViewById(R.id.tranBtn);

                    tranBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = phNo.getText().toString();
                            String Rs = rupee.getText().toString();

                            if(Number.isEmpty() || Number.equals("")){
                                Toast.makeText(getApplicationContext(),"Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Number.length()<10 || Number.length()>10){
                                Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                phNo.setText("");
                            }else if(Rs.isEmpty() || Rs.equals("")){
                                Toast.makeText(getApplicationContext(),"Transfer Amount is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Rs.length()<2){
                                Toast.makeText(getApplicationContext(),"Can Only Transfer Greater than 10 Rupees.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Number.length() == 10 && Rs.length() >= 2){
                                sim1Calling("*17122*"+Number+"*"+Rs, "#");
                            }
                        }
                    });
                    AlertDialog oust = transfering.create();
                    oust.show();

                }
            });

            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*104", "#");

                }
            });

            smsPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*17119", "#");
                }
            });

            voicePack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*17118", "#");
                }
            });

            loan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
                    builder.setTitle("Are You Sure ...");
                    builder.setMessage("तपाई साचै सापटी लिन चाहनुहुन्छ?\n" +
                            "सापटी लिनको लागी:-\n" +
                            "१.मैन ब्यालेन्स Rs.5 वा सो भन्दा कम हुनुपर्छ"+"\n" +
                            "२.सिम खरीद गरेको ६० दिन नाग्नुपर्छ"+"!");
                    builder.setIcon(R.drawable.misicon);
                    builder.setCancelable(false);
                    builder.setPositiveButton("हो", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            AlertDialog.Builder building = new AlertDialog.Builder(NcellActivity.this);
                            building.setTitle("Choose One:");
                            building.setMessage("Take 40 Rs. Loan.");
                            building.setIcon(R.drawable.misicon);
                            building.setCancelable(false);
                            building.setPositiveButton("Through Direct Call", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sim1Calling("*9988", "#");
                                }
                            });

                            building.setNegativeButton("Through Internet", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent fb = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://portal.ncell.axiata.com/na"));
                                    startActivity(fb);
                                }
                            });

                            building.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog call = building.create();
                            call.show();

                        }
                    });

                    builder.setNeutralButton("रद्द गर्नुहोस् ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();
                }
            });

            my5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
                    builder.setTitle("MY5 Service");
                    builder.setMessage("If you Activate this service you can add 5 Ncell Numbers in MY5 group and you can talk to them at only 99 paisa per minute.\n Touch Start to " +
                            "activate/deactivate , add new number , delete exist number and Modify the List... ");
                    builder.setIcon(R.drawable.misicon);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sim1Calling("*5599", "#");
                        }
                    });

                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();
                }
            });

            love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("17143", "");
                }
            });

            rechargeOther.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(NcellActivity.this);
                    transfering.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge_other, null);
                    transfering.setView(view);

                    final EditText phNo, rupee;
                    Button tranBtn;

                    phNo = view.findViewById(R.id.phNo);
                    rupee = view.findViewById(R.id.rupee);
                    tranBtn = view.findViewById(R.id.tranBtn);

                    tranBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = phNo.getText().toString();
                            String Rs = rupee.getText().toString();

                            if(Number.isEmpty() || Number.equals("")){
                                Toast.makeText(getApplicationContext(),"Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Number.length()<10 || Number.length()>10){
                                Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                phNo.setText("");
                            }else if(Rs.isEmpty() || Rs.equals("")){
                                Toast.makeText(getApplicationContext(),"Pin Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Rs.length()<16){
                                Toast.makeText(getApplicationContext(),"Pin Number is Less than 16 digit.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Rs.length()<16){
                                Toast.makeText(getApplicationContext(),"Pin Number is Greater than 16 digit.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Number.length() == 10 && Rs.length() == 16){
                                sim1Calling("*102*"+Rs+"*"+Number, "#");
                            }
                        }
                    });
                    AlertDialog oust = transfering.create();
                    oust.show();

                }
            });




        }else if (sim2 == 1 && sim1 != 1){

            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*101", "#");
                }
            });

            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*101", "#");
                }
            });

            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("9005", "");
                }
            });

            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("9208", "");
                }
            });

            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
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
                                sim2Calling("*102*"+number, "#");

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
                    sim2Calling("*103", "#");

                }
            });

            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*17123", "#");
                }
            });

            transferAmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(NcellActivity.this);
                    transfering.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ncell_transfer, null);
                    transfering.setView(view);

                    final EditText phNo, rupee;
                    Button tranBtn;

                    phNo = view.findViewById(R.id.phNo);
                    rupee = view.findViewById(R.id.rupee);
                    tranBtn = view.findViewById(R.id.tranBtn);

                    tranBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = phNo.getText().toString();
                            String Rs = rupee.getText().toString();

                            if(Number.isEmpty() || Number.equals("")){
                                Toast.makeText(getApplicationContext(),"Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Number.length()<10 || Number.length()>10){
                                Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                phNo.setText("");
                            }else if(Rs.isEmpty() || Rs.equals("")){
                                Toast.makeText(getApplicationContext(),"Transfer Amount is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Rs.length()<2){
                                Toast.makeText(getApplicationContext(),"Can Only Transfer Greater than 10 Rupees.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Number.length() == 10 && Rs.length() >= 2){
                                sim2Calling("*17122*"+Number+"*"+Rs, "#");
                            }
                        }
                    });
                    AlertDialog oust = transfering.create();
                    oust.show();

                }
            });

            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*104", "#");

                }
            });

            smsPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*17119", "#");
                }
            });

            voicePack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*17118", "#");
                }
            });

            loan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
                    builder.setTitle("Are You Sure ...");
                    builder.setMessage("तपाई साचै सापटी लिन चाहनुहुन्छ?\n" +
                            "सापटी लिनको लागी:-\n" +
                            "१.मैन ब्यालेन्स Rs.5 वा सो भन्दा कम हुनुपर्छ"+"\n" +
                            "२.सिम खरीद गरेको ६० दिन नाग्नुपर्छ"+"!");
                    builder.setIcon(R.drawable.misicon);
                    builder.setCancelable(false);
                    builder.setPositiveButton("हो", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            AlertDialog.Builder building = new AlertDialog.Builder(NcellActivity.this);
                            building.setTitle("Choose One:");
                            building.setMessage("Take 40 Rs. Loan.");
                            building.setIcon(R.drawable.misicon);
                            building.setCancelable(false);
                            building.setPositiveButton("Through Direct Call", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sim2Calling("*9988", "#");
                                }
                            });

                            building.setNegativeButton("Through Internet", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent fb = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://portal.ncell.axiata.com/na"));
                                    startActivity(fb);
                                }
                            });

                            building.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog call = building.create();
                            call.show();

                        }
                    });

                    builder.setNeutralButton("रद्द गर्नुहोस् ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();
                }
            });

            my5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
                    builder.setTitle("MY5 Service");
                    builder.setMessage("If you Activate this service you can add 5 Ncell Numbers in MY5 group and you can talk to them at only 99 paisa per minute.\n Touch Start to " +
                            "activate/deactivate , add new number , delete exist number and Modify the List... ");
                    builder.setIcon(R.drawable.misicon);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sim2Calling("*5599", "#");
                        }
                    });

                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();
                }
            });

            love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("17143", "");
                }
            });

            rechargeOther.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(NcellActivity.this);
                    transfering.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge_other, null);
                    transfering.setView(view);

                    final EditText phNo, rupee;
                    Button tranBtn;

                    phNo = view.findViewById(R.id.phNo);
                    rupee = view.findViewById(R.id.rupee);
                    tranBtn = view.findViewById(R.id.tranBtn);

                    tranBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = phNo.getText().toString();
                            String Rs = rupee.getText().toString();

                            if(Number.isEmpty() || Number.equals("")){
                                Toast.makeText(getApplicationContext(),"Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Number.length()<10 || Number.length()>10){
                                Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                phNo.setText("");
                            }else if(Rs.isEmpty() || Rs.equals("")){
                                Toast.makeText(getApplicationContext(),"Pin Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Rs.length()<16){
                                Toast.makeText(getApplicationContext(),"Pin Number is Less than 16 digit.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Rs.length()<16){
                                Toast.makeText(getApplicationContext(),"Pin Number is Greater than 16 digit.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Number.length() == 10 && Rs.length() == 16){
                                sim2Calling("*102*"+Rs+"*"+Number, "#");
                            }
                        }
                    });
                    AlertDialog oust = transfering.create();
                    oust.show();

                }
            });


        }else if (sim1 == 1 && sim2 == 1){
            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*101", "#");
                    Toast.makeText(getApplicationContext(),"CheckBal", Toast.LENGTH_SHORT).show();
                }
            });

            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*101", "#");
                }
            });

            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("9005", "");
                }
            });

            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("9208", "");
                }
            });

            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
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
                                MyDialog("*102*"+number, "#");

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
                    MyDialog("*103", "#");

                }
            });

            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*17123", "#");
                }
            });
            transferAmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(NcellActivity.this);
                    transfering.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ncell_transfer, null);
                    transfering.setView(view);

                    final EditText phNo, rupee;
                    Button tranBtn;

                    phNo = view.findViewById(R.id.phNo);
                    rupee = view.findViewById(R.id.rupee);
                    tranBtn = view.findViewById(R.id.tranBtn);

                    tranBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = phNo.getText().toString();
                            String Rs = rupee.getText().toString();

                            if(Number.isEmpty() || Number.equals("")){
                                Toast.makeText(getApplicationContext(),"Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Number.length()<10 || Number.length()>10){
                                Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                phNo.setText("");
                            }else if(Rs.isEmpty() || Rs.equals("")){
                                Toast.makeText(getApplicationContext(),"Transfer Amount is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Rs.length()<2){
                                Toast.makeText(getApplicationContext(),"Can Only Transfer Greater than 10 Rupees.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Number.length() == 10 && Rs.length() >= 2){
                                MyDialog("*17122*"+Number+"*"+Rs, "#");
                            }
                        }
                    });
                    AlertDialog oust = transfering.create();
                    oust.show();

                }
            });

            time.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*10", "#");

                }
            });

            smsPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*17119", "#");
                }
            });

            voicePack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*17118", "#");
                }
            });
            loan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
                    builder.setTitle("Are You Sure ...");
                    builder.setMessage("तपाई साचै सापटी लिन चाहनुहुन्छ?\n" +
                            "सापटी लिनको लागी:-\n" +
                            "१.मैन ब्यालेन्स Rs.5 वा सो भन्दा कम हुनुपर्छ"+"\n" +
                            "२.सिम खरीद गरेको ६० दिन नाग्नुपर्छ"+"!");
                    builder.setIcon(R.drawable.misicon);
                    builder.setCancelable(false);
                    builder.setPositiveButton("हो", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            AlertDialog.Builder building = new AlertDialog.Builder(NcellActivity.this);
                            building.setTitle("Choose One:");
                            building.setMessage("Take 40 Rs. Loan.");
                            building.setIcon(R.drawable.misicon);
                            building.setCancelable(false);
                            building.setPositiveButton("Through Direct Call", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MyDialog("*9988", "#");
                                }
                            });

                            building.setNegativeButton("Through Internet", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent fb = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://portal.ncell.axiata.com/na"));
                                    startActivity(fb);
                                }
                            });

                            building.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            AlertDialog call = building.create();
                            call.show();

                        }
                    });

                    builder.setNeutralButton("रद्द गर्नुहोस् ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();
                }
            });

            my5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
                    builder.setTitle("MY5 Service");
                    builder.setMessage("If you Activate this service you can add 5 Ncell Numbers in MY5 group and you can talk to them at only 99 paisa per minute.\n Touch Start to " +
                            "activate/deactivate , add new number , delete exist number and Modify the List... ");
                    builder.setIcon(R.drawable.misicon);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDialog("*5599", "#");
                        }
                    });

                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog sh = builder.create();
                    sh.show();
                }
            });

            love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("17143", "");
                }
            });

            rechargeOther.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder transfering = new AlertDialog.Builder(NcellActivity.this);
                    transfering.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge_other, null);
                    transfering.setView(view);

                    final EditText phNo, rupee;
                    Button tranBtn;

                    phNo = view.findViewById(R.id.phNo);
                    rupee = view.findViewById(R.id.rupee);
                    tranBtn = view.findViewById(R.id.tranBtn);

                    tranBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = phNo.getText().toString();
                            String Rs = rupee.getText().toString();

                            if(Number.isEmpty() || Number.equals("")){
                                Toast.makeText(getApplicationContext(),"Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Number.length()<10 || Number.length()>10){
                                Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                phNo.setText("");
                            }else if(Rs.isEmpty() || Rs.equals("")){
                                Toast.makeText(getApplicationContext(),"Pin Number is Empty!", Toast.LENGTH_LONG).show();
                            }else if(Rs.length()<16){
                                Toast.makeText(getApplicationContext(),"Pin Number is Less than 16 digit.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Rs.length()<16){
                                Toast.makeText(getApplicationContext(),"Pin Number is Greater than 16 digit.", Toast.LENGTH_LONG).show();
                                rupee.setText("");
                            }else if(Number.length() == 10 && Rs.length() == 16){
                                MyDialog("*102*"+Rs+"*"+Number, "#");
                            }
                        }
                    });
                    AlertDialog oust = transfering.create();
                    oust.show();

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
            NcellActivity.this.finish();
        }else if(i==R.id.abtus){
            Intent abt = new Intent(NcellActivity.this, About.class);
            startActivity(abt);
        }else if(i==R.id.share){
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "Nepal Sim Services contains all the function of diff. Sims Get with it From :-" +
                    "https://www.play.google.com/store/apps/details?id=com.mis.community.nepalsimservices&hl=en");
            share.putExtra(Intent.EXTRA_SUBJECT,"Nepal Sim Services");
            startActivity(Intent.createChooser(share, "Share Via"));
        }else if(i==R.id.rate){
            try {
                Intent rate = getPackageManager().getLaunchIntentForPackage("com.android.vending");
                rate.setData(Uri.parse("com.mis.community.nepalsimservices"));
                startActivity(rate);
            }catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.play.google.com/store/apps/details?id=com.mis.community.nepalsimservices&hl=en")));
            }
        }else if(i==R.id.exit){
            NcellActivity.this.finish();
        }else if(i==R.id.feed){
            Intent fed = new Intent(NcellActivity.this, feedBack.class);
            startActivity(fed);
        }else if(i == R.id.clear) {
            MyApplication.getInstance().clearApplicationData();
            finish();
            Intent fb = getPackageManager().getLaunchIntentForPackage("com.mis.community.nepalsimservices");
            startActivity(fb);
        }


        return super.onOptionsItemSelected(item);
    }


    public void MyDialog(final String num, final String has){
        final AlertDialog.Builder builder = new AlertDialog.Builder(NcellActivity.this);
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
                        Intent go = new Intent(NcellActivity.this, NcellActivity.class);
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
                        Intent go = new Intent(NcellActivity.this, NcellActivity.class);
                        startActivity(go);
                    }
                },10000);
            }
        });



        AlertDialog ouster = builder.create();
        ouster.show();
    }


    public void app(View view) {
        try{
            Intent fb = getPackageManager().getLaunchIntentForPackage("com.mventus.ncell.activity");
            startActivity(fb);
        }catch (Exception e){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Suggest!!!");
            builder.setIcon(R.drawable.misicon);
            builder.setMessage("Ncell App doesn\'t exist in Your Device.\n" +
                    "If You want to download Ncell App Click \"Ok\" |");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent fb = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://bit.ly/1t6Xymc"));
                    startActivity(fb);
                }
            });
            builder.setNeutralButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog sho = builder.create();
            sho.show();

        }

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
