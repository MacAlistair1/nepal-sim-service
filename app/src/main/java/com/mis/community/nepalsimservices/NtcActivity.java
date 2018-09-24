package com.mis.community.nepalsimservices;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NtcActivity extends AppCompatActivity {

    Button checkBal, remainVol, customerCare, tunes, recharge, num, dataPack, transferAmt, fnf;
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
        setContentView(R.layout.activity_ntc);

        context = this;

        checkBal = findViewById(R.id.balance);
        remainVol = findViewById(R.id.data);
        customerCare = findViewById(R.id.customer);
        tunes = findViewById(R.id.tunes);
        recharge = findViewById(R.id.recharge);
        num = findViewById(R.id.number);
        dataPack = findViewById(R.id.pack);
        transferAmt = findViewById(R.id.transfer);
        fnf = findViewById(R.id.fnf);

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("prefs", MODE_PRIVATE);

        sim1 = sharedPreferences.getInt("sim1", 0);
        sim2 = sharedPreferences.getInt("sim2", 0);

        if (sim1 == 0 || sim2 == 0) {

            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:" + "*400") + Uri.encode("#")));
                    startActivity(bala);
                }
            });

            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:" + "*1415*51") + Uri.encode("#")));
                    startActivity(bala);
                }
            });

            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:" + "1498") + Uri.encode("")));
                    startActivity(bala);
                }
            });

            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent bala = new Intent(Intent.ACTION_CALL);
                    bala.setData(Uri.parse(Uri.parse("tel:" + "1455") + Uri.encode("")));
                    startActivity(bala);
                }
            });

            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge, null);
                    builder.setView(view);

                    final ImageButton rBtn;
                    final EditText editText;
                    editText = view.findViewById(R.id.pin);
                    rBtn = view.findViewById(R.id.rbtn);
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
                                call.setData(Uri.parse(Uri.parse("tel:" + "*412*" + number) + Uri.encode("#")));
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
                    bala.setData(Uri.parse(Uri.parse("tel:" + "*9") + Uri.encode("#")));
                    startActivity(bala);
                }
            });

            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(NtcActivity.this, NtcDataPack.class);
                    data.putExtra("sim1", 0);
                    data.putExtra("sim2", 0);
                    startActivity(data);
                }
            });
            transferAmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ntc_transfer, null);
                    builder.setView(view);

                    Button rBtn;
                    final EditText num;
                    final EditText rs;
                    final EditText code;
                    num = view.findViewById(R.id.phNo);
                    rs = view.findViewById(R.id.rupee);
                    code = view.findViewById(R.id.code);
                    rBtn = view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = num.getText().toString();
                            String Rs = rs.getText().toString();
                            String Cd = code.getText().toString();

                            if (Number.isEmpty() || Number.equals("")) {
                                Toast.makeText(getApplicationContext(), "Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Number.length() < 10 || Number.length() > 10) {
                                Toast.makeText(getApplicationContext(), "Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                num.setText("");
                            } else if (Rs.isEmpty() || Rs.equals("")) {
                                Toast.makeText(getApplicationContext(), "Transfer Amount is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Rs.length() < 2) {
                                Toast.makeText(getApplicationContext(), "Can Only Transfer Greater than 10 Rupees.", Toast.LENGTH_LONG).show();
                                rs.setText("");
                            } else if (Cd.isEmpty() || Cd.equals("")) {
                                Toast.makeText(getApplicationContext(), "Security Code is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Cd.length() < 8 || Cd.length() > 8) {
                                Toast.makeText(getApplicationContext(), "Security Code doesn't exist!", Toast.LENGTH_LONG).show();
                                code.setText("");
                            } else if (Number.length() == 10 && Rs.length() >= 2 && Cd.length() == 8) {
                                Intent voice = new Intent(Intent.ACTION_CALL);
                                voice.setData(Uri.parse(Uri.parse("tel:" + "*422*" + Cd + "*" + Number + "*" + Rs) + Uri.encode("#")));
                                startActivity(voice);
                            }
                        }
                    });
                    AlertDialog ouster = builder.create();
                    ouster.show();
                }
            });


        } else if (sim1 == 2 && sim2 != 2) {

            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*400", "#");
                }
            });

            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("*1415*51", "#");
                }
            });

            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("1498", "");
                }
            });

            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim1Calling("1455", "");
                }
            });

            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge, null);
                    builder.setView(view);

                    final ImageButton rBtn;
                    final EditText editText;
                    editText = view.findViewById(R.id.pin);
                    rBtn = view.findViewById(R.id.rbtn);

                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String number = editText.getText().toString();
                            editText.setError("Pin Number is Empty or Incorrect!!");
                            if(number.isEmpty() || number.equals("") || number.length() <16 || number.length() >16){
                                editText.setError("Pin Number is Empty or Incorrect!!");
                            }else{
                                rBtn.setBackgroundResource(R.drawable.ok_dialog_pressed);
                                rBtn.setImageResource(R.drawable.ok_dialog_pressed);
                                sim1Calling("*412*" + number, "#");
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
                    sim1Calling("*9", "#");
                }
            });
            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(NtcActivity.this, NtcDataPack.class);
                    data.putExtra("sim1", 1);
                    data.putExtra("sim2", 0);
                    startActivity(data);
                }
            });

            transferAmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ntc_transfer, null);
                    builder.setView(view);

                    Button rBtn;
                    final EditText num;
                    final EditText rs;
                    final EditText code;
                    num = view.findViewById(R.id.phNo);
                    rs = view.findViewById(R.id.rupee);
                    code = view.findViewById(R.id.code);
                    rBtn = view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = num.getText().toString();
                            String Rs = rs.getText().toString();
                            String Cd = code.getText().toString();

                            if (Number.isEmpty() || Number.equals("")) {
                                Toast.makeText(getApplicationContext(), "Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Number.length() < 10 || Number.length() > 10) {
                                Toast.makeText(getApplicationContext(), "Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                num.setText("");
                            } else if (Rs.isEmpty() || Rs.equals("")) {
                                Toast.makeText(getApplicationContext(), "Transfer Amount is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Rs.length() < 2) {
                                Toast.makeText(getApplicationContext(), "Can Only Transfer Greater than 10 Rupees.", Toast.LENGTH_LONG).show();
                                rs.setText("");
                            } else if (Cd.isEmpty() || Cd.equals("")) {
                                Toast.makeText(getApplicationContext(), "Security Code is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Cd.length() < 8 || Cd.length() > 8) {
                                Toast.makeText(getApplicationContext(), "Security Code doesn't exist!", Toast.LENGTH_LONG).show();
                                code.setText("");
                            } else if (Number.length() == 10 && Rs.length() >= 2 && Cd.length() == 8) {
                                sim1Calling("*422*" + Cd + "*" + Number + "*" + Rs, "#");
                            }
                        }
                    });
                    AlertDialog ouster = builder.create();
                    ouster.show();
                }
            });


        } else if (sim2 == 2 && sim1 != 2) {
            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*400", "#");
                }
            });

            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("*1415*51", "#");
                }
            });

            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("1498", "");
                }
            });

            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sim2Calling("1455", "");
                }
            });

            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge, null);
                    builder.setView(view);

                    final ImageButton rBtn;
                    final EditText editText;
                    editText = view.findViewById(R.id.pin);
                    rBtn = view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String number = editText.getText().toString();
                            editText.setError("Pin Number is Empty or Incorrect!!");
                            if(number.isEmpty() || number.equals("") || number.length() <16 || number.length() >16){
                                editText.setError("Pin Number is Empty or Incorrect!!");
                            }else{
                                rBtn.setBackgroundResource(R.drawable.ok_dialog_pressed);
                                rBtn.setImageResource(R.drawable.ok_dialog_pressed);
                                sim1Calling("*412*" + number, "#");
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
                    sim2Calling("*9", "#");
                }
            });
            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(NtcActivity.this, NtcDataPack.class);
                    data.putExtra("sim1", 0);
                    data.putExtra("sim2", 1);
                    startActivity(data);
                }
            });
            transferAmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ntc_transfer, null);
                    builder.setView(view);

                    Button rBtn;
                    final EditText num;
                    final EditText rs;
                    final EditText code;
                    num = view.findViewById(R.id.phNo);
                    rs = view.findViewById(R.id.rupee);
                    code = view.findViewById(R.id.code);
                    rBtn = view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = num.getText().toString();
                            String Rs = rs.getText().toString();
                            String Cd = code.getText().toString();

                            if (Number.isEmpty() || Number.equals("")) {
                                Toast.makeText(getApplicationContext(), "Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Number.length() < 10 || Number.length() > 10) {
                                Toast.makeText(getApplicationContext(), "Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                num.setText("");
                            } else if (Rs.isEmpty() || Rs.equals("")) {
                                Toast.makeText(getApplicationContext(), "Transfer Amount is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Rs.length() < 2) {
                                Toast.makeText(getApplicationContext(), "Can Only Transfer Greater than 10 Rupees.", Toast.LENGTH_LONG).show();
                                rs.setText("");
                            } else if (Cd.isEmpty() || Cd.equals("")) {
                                Toast.makeText(getApplicationContext(), "Security Code is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Cd.length() < 8 || Cd.length() > 8) {
                                Toast.makeText(getApplicationContext(), "Security Code doesn't exist!", Toast.LENGTH_LONG).show();
                                code.setText("");
                            } else if (Number.length() == 10 && Rs.length() >= 2 && Cd.length() == 8) {
                                sim2Calling("*422*" + Cd + "*" + Number + "*" + Rs, "#");
                            }
                        }
                    });
                    AlertDialog ouster = builder.create();
                    ouster.show();
                }
            });

        } else if (sim1 == 2 && sim2 == 2) {

            checkBal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*400", "#");
                }
            });

            remainVol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("*1415*51", "#");
                }
            });
            customerCare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyDialog("1498", "");
                }
            });
            tunes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  MyDialog("1455", "");
                }
            });
            recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_recharge, null);
                    builder.setView(view);

                    final ImageButton rBtn;
                    final EditText editText;
                    editText = view.findViewById(R.id.pin);
                    rBtn = view.findViewById(R.id.rbtn);

                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String number = editText.getText().toString();
                            editText.setError("Pin Number is Empty or Incorrect!!");
                            if(number.isEmpty() || number.equals("") || number.length() <16 || number.length() >16){
                                editText.setError("Pin Number is Empty or Incorrect!!");
                            }else{
                                rBtn.setBackgroundResource(R.drawable.ok_dialog_pressed);
                                rBtn.setImageResource(R.drawable.ok_dialog_pressed);
                                sim1Calling("*412*" + number, "#");
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
                    MyDialog("*9", "#");
                }
            });
            dataPack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(NtcActivity.this, NtcDataPack.class);
                    data.putExtra("sim1", 1);
                    data.putExtra("sim2", 1);
                    startActivity(data);
                }
            });
            transferAmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ntc_transfer, null);
                    builder.setView(view);

                    Button rBtn;
                    final EditText num;
                    final EditText rs;
                    final EditText code;
                    num = view.findViewById(R.id.phNo);
                    rs = view.findViewById(R.id.rupee);
                    code = view.findViewById(R.id.code);
                    rBtn = view.findViewById(R.id.rbtn);
                    rBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String Number = num.getText().toString();
                            String Rs = rs.getText().toString();
                            String Cd = code.getText().toString();

                            if (Number.isEmpty() || Number.equals("")) {
                                Toast.makeText(getApplicationContext(), "Destination Number is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Number.length() < 10 || Number.length() > 10) {
                                Toast.makeText(getApplicationContext(), "Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                num.setText("");
                            } else if (Rs.isEmpty() || Rs.equals("")) {
                                Toast.makeText(getApplicationContext(), "Transfer Amount is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Rs.length() < 2) {
                                Toast.makeText(getApplicationContext(), "Can Only Transfer Greater than 10 Rupees.", Toast.LENGTH_LONG).show();
                                rs.setText("");
                            } else if (Cd.isEmpty() || Cd.equals("")) {
                                Toast.makeText(getApplicationContext(), "Security Code is Empty!", Toast.LENGTH_LONG).show();
                            } else if (Cd.length() < 8 || Cd.length() > 8) {
                                Toast.makeText(getApplicationContext(), "Security Code doesn't exist!", Toast.LENGTH_LONG).show();
                                code.setText("");
                            } else if (Number.length() == 10 && Rs.length() >= 2 && Cd.length() == 8) {

                                MyDialog("*422*" + Cd + "*" + Number + "*" + Rs, "#");
                            }
                        }
                    });
                    AlertDialog ouster = builder.create();
                    ouster.show();
                }
            });

            fnf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
                    builder.setCancelable(true);
                    LayoutInflater inflater = LayoutInflater.from(context);
                    final View view = inflater.inflate(R.layout.activity_ntc_fnf, null);
                    builder.setView(view);

                    Button abtn, sbtn, dbtn, mbtn, helpBtn;
                    abtn = view.findViewById(R.id.abtn);
                    sbtn = view.findViewById(R.id.sbtn);
                    mbtn = view.findViewById(R.id.mbtn);
                    dbtn = view.findViewById(R.id.dbtn);
                    helpBtn = view.findViewById(R.id.helpBtn);

                    sbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(NtcActivity.this);
                            builder1.setCancelable(true);
                            LayoutInflater inflater = LayoutInflater.from(context);
                            final View view = inflater.inflate(R.layout.activity_sub_number, null);
                            builder1.setView(view);

                            final EditText numb;
                            Button subSrc;

                            numb = view.findViewById(R.id.phNo);
                            subSrc = view.findViewById(R.id.sub);
                            subSrc.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String Number = numb.getText().toString();

                                    if(Number.isEmpty() || Number.equals("")){
                                        Toast.makeText(getApplicationContext(),"New Number is Empty!", Toast.LENGTH_LONG).show();
                                    }else if(Number.length()<10 || Number.length()>10){
                                        Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                        numb.setText("");
                                    }else if(Number.length() == 10){
                                        Intent sms = new Intent(Intent.ACTION_VIEW);
                                        sms.setData(Uri.parse("smsto:"+Uri.encode("1415")));
                                        sms.putExtra("address", 1415);
                                        sms.putExtra("sms_body", "FNFSUB*"+Number);
                                        startActivity(Intent.createChooser(sms, "Complete action using"));

                                    }
                                }
                            });
                            AlertDialog ouster1 = builder1.create();
                            ouster1.show();
                        }
                    });

                    abtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(NtcActivity.this);
                            builder1.setCancelable(true);
                            LayoutInflater inflater = LayoutInflater.from(context);
                            final View view = inflater.inflate(R.layout.activity_add_num, null);
                            builder1.setView(view);

                            final EditText numb;
                            Button add;

                            numb = view.findViewById(R.id.phNo);
                            add = view.findViewById(R.id.add);
                            add.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String Number = numb.getText().toString();

                                    if(Number.isEmpty() || Number.equals("")){
                                        Toast.makeText(getApplicationContext(),"New Number is Empty!", Toast.LENGTH_LONG).show();
                                    }else if(Number.length()<10 || Number.length()>10){
                                        Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                        numb.setText("");
                                    }else if(Number.length() == 10){
                                        Intent sms = new Intent(Intent.ACTION_VIEW);
                                        sms.setData(Uri.parse("smsto:"+Uri.encode("1415")));
                                        sms.putExtra("address", 1415);
                                        sms.putExtra("sms_body", "FNFADD*"+Number);
                                        startActivity(Intent.createChooser(sms, "Complete action using"));

                                    }
                                }
                            });
                            AlertDialog ouster1 = builder1.create();
                            ouster1.show();
                        }
                    });
                    mbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(NtcActivity.this);
                            builder2.setCancelable(true);
                            LayoutInflater inflater = LayoutInflater.from(context);
                            final View view = inflater.inflate(R.layout.activity_mod_num, null);
                            builder2.setView(view);

                            final EditText numb, numb1;
                            Button modi;

                            numb = view.findViewById(R.id.phNo);
                            numb1 = view.findViewById(R.id.phNo1);
                            modi = view.findViewById(R.id.modi);
                            modi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String Number = numb.getText().toString();
                                    String Number1 = numb1.getText().toString();

                                    if(Number.isEmpty() || Number.equals("")){
                                        Toast.makeText(getApplicationContext(),"Old Number is Empty!", Toast.LENGTH_LONG).show();
                                    }else if(Number.length()<10 || Number.length()>10){
                                        Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                        numb.setText("");
                                    }else  if(Number1.isEmpty() || Number1.equals("")){
                                        Toast.makeText(getApplicationContext(),"New Number is Empty!", Toast.LENGTH_LONG).show();
                                    }else if(Number1.length()<10 || Number1.length()>10){
                                        Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                        numb1.setText("");
                                    }else if(Number.length() == 10){
                                        Intent sms = new Intent(Intent.ACTION_VIEW);
                                        sms.setData(Uri.parse("smsto:"+Uri.encode("1415")));
                                        sms.putExtra("address", 1415);
                                        sms.putExtra("sms_body", "FNFMOD*"+Number+"*"+Number1);
                                        startActivity(Intent.createChooser(sms, "Complete action using"));

                                    }
                                }
                            });
                            AlertDialog ouster2 = builder2.create();
                            ouster2.show();
                        }
                    });
                    dbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder3 = new AlertDialog.Builder(NtcActivity.this);
                            builder3.setCancelable(true);
                            LayoutInflater inflater = LayoutInflater.from(context);
                            final View view = inflater.inflate(R.layout.activity_del_num, null);
                            builder3.setView(view);

                            final EditText numb;
                            Button del;

                            numb = view.findViewById(R.id.phNo);
                            del = view.findViewById(R.id.del);
                            del.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String Number = numb.getText().toString();

                                    if(Number.isEmpty() || Number.equals("")){
                                        Toast.makeText(getApplicationContext(),"New Number is Empty!", Toast.LENGTH_LONG).show();
                                    }else if(Number.length()<10 || Number.length()>10){
                                        Toast.makeText(getApplicationContext(),"Phone Number doesn't exist!", Toast.LENGTH_LONG).show();
                                        numb.setText("");
                                    }else if(Number.length() == 10){
                                        Intent sms = new Intent(Intent.ACTION_VIEW);
                                        sms.setData(Uri.parse("smsto:"+Uri.encode("1415")));
                                        sms.putExtra("address", 1415);
                                        sms.putExtra("sms_body", "FNFDEL*"+Number);
                                        startActivity(Intent.createChooser(sms, "Complete action using"));

                                    }
                                }
                            });
                            AlertDialog ouster3 = builder3.create();
                            ouster3.show();
                        }
                    });



                    helpBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent sms = new Intent(Intent.ACTION_VIEW);
                            sms.setData(Uri.parse("smsto:"+Uri.encode("1415")));
                            sms.putExtra("address", 1415);
                            sms.putExtra("sms_body", "FNFINQ");
                            startActivity(Intent.createChooser(sms, "Complete action using"));
                        }
                    });

                    AlertDialog ouster = builder.create();
                    ouster.show();
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
            NtcActivity.this.finish();
        }else if(i==R.id.abtus){
            Intent abt = new Intent(NtcActivity.this, About.class);
            startActivity(abt);
        }else if(i==R.id.exit){
            NtcActivity.this.finish();
        }else if(i==R.id.share){
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "Nepal Sim Services contains all the function of diff. Sims Get with it From :-" +
                    "https://www.play.google.com/store/apps/details?id=com.mis.community.nepalsimservices&hl=en");
            share.putExtra(Intent.EXTRA_SUBJECT,"Nepal Sim Services");
            startActivity(Intent.createChooser(share, "Share Via"));
        }else if(i==R.id.feed){
            Intent fed = new Intent(NtcActivity.this, feedBack.class);
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(NtcActivity.this);
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
                        Intent go = new Intent(NtcActivity.this, NtcActivity.class);
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
                        Intent go = new Intent(NtcActivity.this, NtcActivity.class);
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
