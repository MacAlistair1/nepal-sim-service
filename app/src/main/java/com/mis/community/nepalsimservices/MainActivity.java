package com.mis.community.nepalsimservices;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;


public class MainActivity extends Activity {

    TextView qt, battery;
    ImageView battery_icon,torchChange;
    int sim1, sim2;
    String batteryLvl;
    Context context;
    Camera camera;
    Camera.Parameters p;
    boolean isTouch = false;
    boolean isOn = false;
    private static final int CALL_RQST = 1;
    private static final int SMS_RQST = 1;

    String qts[] = new String[]{" ",

            "╯▅╰╱▔▔▔▔▔▔▔╲╯╯☼\n" +
                    "▕▕╱╱╱╱╱╱╱╱╱╲╲╭╭\n" +
                    "▕▕╱╱╱╱╱╱╱╱┛▂╲╲╭\n" +
                    "╱▂▂▂▂▂▂╱╱┏▕╋▏╲╲\n" +
                    "▔▏▂┗┓▂▕▔┛▂┏▔▂▕▔\n" +
                    "▕▕╋▏▕╋▏▏▕┏▏▕╋▏▏\n" +
                    "▕┓▔┗┓▔┏▏▕┗▏┓▔┏▏\n" +
                    "—————————————-\n" +
                    "sweet home just for Me N U\n" +
                    "I am just waiting for you come\n" +
                    "N live with me for rest of life\n" +
                    "N feel my overwhelming emotion N\n" +
                    "passion 4 u….\n" +
                    "My valentine love you.",

            "\uFEFFYou have to learn the rules of the game. \n" +
                    "And then you have to play better than anyone else.\n\n" +
                    "\t\t--Albert Einstein",

            "Do not dwell in the past,\n" +
                    " do not dream of the future, \n" +
                    "concentrate the mind on the present moment.\n\n" +
                    "\t\t--Buddha",

            "I studied every thing but never topped.. \n" +
                    "But today the toppers of the best universities are my employees.\n\n" +
                    "\t\t--Bill Gates",
            "A day without laughter is a day wasted.\n\n" +
                    "\t\t--Charlie Chaplin",

            "Innovation distinguishes between a leader and a follower.\n\n" +
                    "\t\t--Steve Jobs",

            "Learn from yesterday,\n" +
                    " live for today, hope for tomorrow.\n" +
                    "The important thing is not to stop questioning.\n\n" +
                    "\t\t--Albert Einstein",
            "You can search throughout the entire universe for someone \n" +
                    "who is more deserving of your love and affection than you are yourself,\n" +
                    " and that person is not to be found anywhere.\n" +
                    " You yourself, as much as anybody in the entire \n" +
                    "universe deserve your love and affection.\n\n" +
                    "\t\t--Buddha",
            "It's fine to celebrate success but it is more\n" +
                    " important to heed the lessons of failure.\n\n" +
                    "\t\t--Bill Gates",

            "A tramp, a gentleman, a poet, \n" +
                    "a dreamer, a lonely fellow, \n" +
                    "always hopeful of romance and adventure.\n\n" +
                    "\t\t--Charlie Chaplin",
            "Your work is going to fill a large part of your life, \n" +
                    "and the only way to be truly satisfied is to do what you believe is great work.\n" +
                    " And the only way to do great work is to love what you do.\n" +
                    " If you haven't found it yet, keep looking. Don't settle. \n" +
                    "As with all matters of the heart, you'll know when you find it.\n\n" +
                    "\t\t--Steve Jobs",
            "Look deep into nature, \n" +
                    "and then you will understand everything better.\n\n" +
                    "\t\t--Albert Einstein",

            "No one saves us but ourselves.\n" +
                    " No one can and no one may. \n" +
                    "We ourselves must walk the path.\n\n" +
                    "\t\t--Buddha",
            "Technology is just a tool. \n" +
                    "In terms of getting the kids working together and motivating them, \n" +
                    "the teacher is the most important.\n\n" +
                    "\t\tBill Gates",

            "To truly laugh, you must be able to take your pain,\n" +
                    " and play with it!\n\n" +
                    "\t\t--Charlie Chaplin",
            "Your time is limited, so don't waste it living someone else's life. \n" +
                    "Don't be trapped by dogma - which is living with the results of other people's thinking.\n" +
                    " Don't let the noise of others' opinions drown out your own inner voice. \n" +
                    "And most important, have the courage to follow your heart and intuition.\n\n" +
                    "\t\t--Steve Jobs",
            "When you are courting a nice girl an hour seems like a second.\n" +
                    " When you sit on a red-hot cinder a second seems like an hour. \n" +
                    "That's relativity.\n\n" +
                    "\t\t--Albert Einstein",
            "Three things cannot be long hidden:\n" +
                    " the sun, the moon, and the truth.\n\n" +
                    "\t\t--Buddha",
            "Success is a lousy teacher.\n" +
                    " It seduces smart people into thinking they can't lose.\n\n" +
                    "\t\t--Bill Gates",
            "Life is a tragedy when seen in close-up, \n" +
                    "but a comedy in long-shot.\n\n" +
                    "\t\t--Charlie Chaplin",
            "No one wants to die. Even people who want to go to heaven don't want to die to get there. \n" +
                    "And yet death is the destination we all share. No one has ever escaped it.\n" +
                    " And that is as it should be, \n" +
                    "because Death is very likely the single best invention of Life.\n" +
                    " It is Life's change agent.\n" +
                    " It clears out the old to make way for the new.\n\n" +
                    "\t\t--Steve Jobs",
            "We cannot solve our problems with the same thinking we used when we created them.\n\n" +
                    "\t\t--Albert Einstein",
            "We are shaped by our thoughts;\n" +
                    " we become what we think. \n" +
                    "When the mind is pure, \n" +
                    "joy follows like a shadow that never leaves.\n\n" +
                    "\t\t--Buddha",
            "What's amazing is, if young people understood how doing well in school\n" +
                    " makes the rest of their life so much interesting, they would be more motivated.\n" +
                    " It's so far away in time that they can't appreciate what it means for their whole life.\n\n" +
                    "\t\t--Bill Gates",
            "Nothing is permanent in this wicked world - \n" +
                    "not even our troubles.\n\n" +
                    "\t\t--Charlie Chaplin",
            "For the past 33 years, \n" +
                    "I have looked in the mirror every morning and asked myself: \n" +
                    "'If today were the last day of my life, \n" +
                    "would I want to do what I am about to do today?'\n" +
                    " And whenever the answer has been 'No' for too many days in a row,\n" +
                    " I know I need to change something.\n\n" +
                    "\t\t--Steve Jobs",
            "Try not to become a man of success, \n" +
                    "but rather try to become a man of value.\n\n" +
                    "\t\t--Albert Enistein",
            "Thousands of candles can be lighted from a single candle, \n" +
                    "and the life of the candle will not be shortened.\n" +
                    " Happiness never decreases by being shared.\n\n" +
                    "\t\t--Buddha",
            "If you can't make it good,\n" +
                    " at least make it look good.\n\n" +
                    "\t\t--Bill Gates",
            "A man's true character comes out when he's drunk.\n\n" +
                    "\t\t--Charlie Chaplin",
            "You can't connect the dots looking forward; \n" +
                    "you can only connect them looking backwards.\n" +
                    " So you have to trust that the dots will somehow connect in your future. \n" +
                    "You have to trust in something - your gut, destiny, life, karma, whatever.\n" +
                    " This approach has never let me down, \n" +
                    "and it has made all the difference in my life.\n\n" +
                    "\t\t--Steve Jobs",
            "The true sign of intelligence is not knowledge but imagination.\n" +
                    "\t\t--Albert Einstein",
            "You will not be punished for your anger, \n" +
                    "you will be punished by your anger.\n\n" +
                    "\t\t--Buddha",
            "The PC has improved the world in just about every area you can think of.\n" +
                    " Amazing developments in communications, \n" +
                    "collaboration and efficiencies.\n" +
                    "New kinds of entertainment and social media.\n" +
                    " Access to information and the ability to give a voice \n" +
                    "people who would never have been heard.\n\n" +
                    "\t\tBill Gates",
            "Failure is unimportant.\n" +
                    " It takes courage to make a fool of yourself.\n" +
                    "\t\t--Charlie Chaplin",
            "My favorite things in life don't cost any money.\n" +
                    " It's really clear that the most precious resource we all have is time.\n\n" +
                    "\t\t--Steve Jobs",
            "A person who never made a mistake never tried anything new.\n\n" +
                    "\t\t--Albert Einstein",
            "It is a man's own mind, not his enemy or foe,\n" +
                    " that lures him to evil ways.\n\n" +
                    "\t\t--Buddha",
            "We all need people who will give us feedback. \n" +
                    "That's how we improve.\n\n" +
                    "\t\t--Bill Gates",
            "We think too much and feel too little.\n\n" +
                    "\t\t--Charlie Chaplin",
            "Design is not just what it looks like and feels like.\n" +
                    " Design is how it works.\n\n" +
                    "\t\t--Steve Jobs",
            "Education is what remains after one has forgotten \n" +
                    "what one has learned in school.\n\n" +
                    "\t\t--Albert Einstein",
            "Whatever words we utter should be chosen with care \n" +
                    "for people will hear them and be influenced by them for good or ill.\n\n" +
                    "\t\tBuddha",
            "Your most unhappy customers are your greatest source of learning.\n\n" +
                    "\t\tBill Gates",
            "I do not have much patience with a thing of beauty \n" +
                    "that must be explained to be understood. \n" +
                    "If it does need additional interpretation by someone other than the creator,\n" +
                    " then I question whether it has fulfilled its purpose.\n\n" +
                    "\t\t--Charlie Chaplin",
            "Be a yardstick of quality. \n" +
                    "Some people aren't used to an environment where excellence is expected.\n\n" +
                    "\t\t--Steve Jobs"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qt = findViewById(R.id.myquote);
        battery = findViewById(R.id.battery);
        battery_icon = findViewById(R.id.battery_icon);
        torchChange = findViewById(R.id.torchChange);
        battery.setText("Hi!");

        context = this;

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_RQST);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, SMS_RQST);

        registerBatteryLevelReceiver();

        Date date = new Date();
        int today1 = Integer.parseInt((String) DateFormat.format("d", date));
        qt.setText(qts[Integer.parseInt(Integer.toString(today1))]);

        SharedPreferences preferences = getSharedPreferences("PREPS", MODE_PRIVATE);
        boolean first = preferences.getBoolean("done", true);
        int lastday = preferences.getInt("day", 0);

        if(first){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.misicon);
            builder.setTitle("Welcome From MIS Community");
            builder.setMessage("Hi, This is Nepal SiM Services Brought to you By MIS Community.\nOfficial FB Page of MIS Community " +
                    "available at:\t \"www.facebook.com/misview\"");
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("GoTo MiS Page", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/misview")));

                    }catch (ActivityNotFoundException e){
                        Intent rate = getPackageManager().getLaunchIntentForPackage("com.facebook.lite");
                        rate.setData(Uri.parse("facebook/misview"));
                        startActivity(rate);
                    }
                }
            });
            builder.setCancelable(false);
            AlertDialog bu = builder.create();
            bu.show();


            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("done", false);
            editor.apply();
        }

        if(lastday != today1){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("day", today1);
            editor.apply();

            Intent ser=new Intent(getApplicationContext(),myService.class);
            startService(ser);

        }
        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("prefs", MODE_PRIVATE);

        sim1 = sharedPreferences.getInt("sim1", 0);
        sim2 = sharedPreferences.getInt("sim2", 0);


    }

    @Override
    protected void onStop() {
        super.onStop();

        if (camera != null){
            camera.release();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        camera = Camera.open();
        p = camera.getParameters();
    }


    private BroadcastReceiver battery_receiver = new BroadcastReceiver()
    {

        @Override
        public void onReceive(Context context, Intent intent) {

            boolean isPresent = intent.getBooleanExtra("present", false);
            int scale = intent.getIntExtra("scale", -1);
            int status = intent.getIntExtra("status", 0);
            int rawlevel = intent.getIntExtra("level", -1);
            int level = 0;



            if(isPresent){
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                batteryLvl = level +"%";

                String stat = getStatusString(status);
                int charge = level;


                if ((stat.equals("Charging") || stat.equals("Discharging") || stat.equals("Not Charging")) && charge<=10) {
                    battery_icon.setImageResource(R.drawable.ic_alert);
                    battery.setText(batteryLvl);
                }else if (stat.equals("Charging") && (charge>10 && charge<=20)){
                    battery_icon.setImageResource(R.drawable.ic_20c);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Discharging") || stat.equals("Not Charging")) && charge<=20){
                    battery_icon.setImageResource(R.drawable.ic_20);
                    battery.setText(batteryLvl);
                }else if (stat.equals("Charging") && (charge >20 && charge<=30)){
                    battery_icon.setImageResource(R.drawable.ic_30c);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Discharging") || stat.equals("Not Charging")) && (charge >20 && charge<=30)){
                    battery_icon.setImageResource(R.drawable.ic_30);
                    battery.setText(batteryLvl);
                }else if (stat.equals("Charging") && (charge >30 && charge<=50)){
                    battery_icon.setImageResource(R.drawable.ic_50c);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Discharging") || stat.equals("Not Charging")) && (charge >30 && charge<=50)){
                    battery_icon.setImageResource(R.drawable.ic_50);
                    battery.setText(batteryLvl);
                }else if (stat.equals("Charging") && (charge >50 && charge<=60)){
                    battery_icon.setImageResource(R.drawable.ic_60c);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Discharging") || stat.equals("Not Charging")) && (charge >50 && charge<=60)){
                    battery_icon.setImageResource(R.drawable.ic_60);
                    battery.setText(batteryLvl);
                }else if (stat.equals("Charging") && (charge >60 && charge<=80)){
                    battery_icon.setImageResource(R.drawable.ic_80c);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Discharging") || stat.equals("Not Charging")) && (charge >60 && charge<=80)){
                    battery_icon.setImageResource(R.drawable.ic_80);
                    battery.setText(batteryLvl);
                }else if (stat.equals("Charging") && (charge >80 && charge<=90)){
                    battery_icon.setImageResource(R.drawable.ic_90c);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Discharging") || stat.equals("Not Charging")) && (charge >80 && charge<=90)){
                    battery_icon.setImageResource(R.drawable.ic_90);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Full")|| stat.equals("Charging")) && charge>90 ){
                    battery_icon.setImageResource(R.drawable.ic_fullc);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Discharging") || stat.equals("Not Charging")) && charge>90){
                    battery_icon.setImageResource(R.drawable.ic_full);
                    battery.setText(batteryLvl);
                }else if ((stat.equals("Discharging") || stat.equals("Not Charging")) && charge==100){
                    battery_icon.setImageResource(R.drawable.ic_full);
                    battery.setText(batteryLvl);
                }


            }else{
                Toast.makeText(getApplicationContext(),"Battery not Present!!", Toast.LENGTH_SHORT).show();
            }

        }
    };

    private String getStatusString(int status)
    {
        String statusString = "Unknown";

        switch(status)
        {
            case BatteryManager.BATTERY_STATUS_CHARGING: statusString = "Charging"; break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING: statusString = "Discharging"; break;
            case BatteryManager.BATTERY_STATUS_FULL: statusString = "Full"; break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING: statusString = "Not Charging"; break;
        }

        return statusString;
    }


    protected void onDestroy()
    {
        unregisterReceiver(battery_receiver);

        super.onDestroy();
    }




    private void registerBatteryLevelReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(battery_receiver, filter);
    }


    int i=0;
    public void ncellServices(View v){

        if (sim1 == 1 || sim2 == 1){
            Intent next = new Intent(MainActivity.this,NcellActivity.class);
            startActivity(next);
        }else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Message");
            builder.setMessage("Sorry! You do not have Ncell Sim.\nIf you just change your Sim then Go to App Manager & clear the data of this App.");
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            android.app.AlertDialog ouster = builder.create();
            ouster.show();
        }


        i=0;
    }
    public void ntcServices(View v){

        if (sim1 == 2 || sim2 == 2){
            Intent next = new Intent(MainActivity.this,NtcActivity.class);
            startActivity(next);
        }else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Message");
            builder.setMessage("Sorry! You do not have Ntc Sim.\nIf you just change your Sim then Go to App Manager & clear the data of this App.");
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            android.app.AlertDialog ouster = builder.create();
            ouster.show();
        }

        i=0;
    }
    public void smartServices(View v){

        if (sim1 == 3 || sim2 == 3){
            Intent next = new Intent(MainActivity.this,SmartActivity.class);
            startActivity(next);
        }else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Message");
            builder.setMessage("Sorry! You do not have Smart Sim.\nIf you just change your Sim then Go to App Manager & clear the data of this App.");
            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            android.app.AlertDialog ouster = builder.create();
            ouster.show();
        }

        i=0;
    }
    public void importantNum(View view) {
        Intent next = new Intent(MainActivity.this,important.class);
        startActivity(next);
        i=0;
    }



    public void openDialer(View view) {
        Intent next = new Intent(MainActivity.this,DialerActivity.class);
        startActivity(next);
        i=0;

    }

    public void timerSmsBtn(View view) {
        Intent next = new Intent(MainActivity.this,CalculatorActivity.class);
        startActivity(next);
        i=0;
    }

    public void onBackPressed(){
        i++;
        if(i==1){
            Toast.makeText(getApplicationContext(),"Press Once Again to Exit",Toast.LENGTH_SHORT).show();
        }else if(i==2){
            MainActivity.this.finish();
        }
    }

    public void ClearMe(View view) {
        MyApplication.getInstance().clearApplicationData();
        finish();
    }

    public void torchOpen(View view){
        PackageManager pm = context.getPackageManager();

        boolean has = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) || !has){
            Toast.makeText(getApplicationContext(), "Device has no Camera Flash Feature.", Toast.LENGTH_SHORT).show();
            return;
        }



        if (isTouch && isOn){
            p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
            Toast.makeText(context, "Torch OFF", Toast.LENGTH_SHORT).show();
            isTouch = false;
            isOn = false;
            torchChange.setImageResource(R.drawable.ic_torch_off);
        }else if (!isTouch && !isOn){
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.startPreview();
            Toast.makeText(context, "Torch ON", Toast.LENGTH_SHORT).show();
            isTouch = true;
            isOn = true;
            torchChange.setImageResource(R.drawable.ic_torch_on);
        }

    }

}
