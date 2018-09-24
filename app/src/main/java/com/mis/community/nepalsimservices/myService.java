package com.mis.community.nepalsimservices;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by MACPC on 2/10/2018.
 */

public class myService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Date date = new Date();
        int today1 = Integer.parseInt((String) DateFormat.format("d", date));
        SharedPreferences preferences = getSharedPreferences("PREPS", 0);
        int lastday = preferences.getInt("day", 0);

        if(lastday != today1) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("day", today1);
            editor.commit();

            NotificationCompat.Builder building = new NotificationCompat.Builder(myService.this)
                    .setAutoCancel(true)
                    .setContentTitle("Quote oF thE daY")
                    .setContentText("Your Daily Quotes arrived")
                    .setSmallIcon(R.drawable.misicon)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.sim_round_icon));
            building.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
            NotificationManagerCompat compat = NotificationManagerCompat.from(myService.this);
            compat.notify(1, building.build());


        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
