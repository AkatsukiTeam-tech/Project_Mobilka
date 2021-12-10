package com.example.project;

import static com.example.project.App.CHANNEL_ID_1;
import static com.example.project.App.CHANNEL_ID_2;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.project.bottom_menu.NotificationsFragment;


public class NotificationService extends Service {
    String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String message = intent.getStringExtra("message");

        Intent notificationIntent = new Intent(this, NotificationsFragment.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);


        if(NotificationsFragment.getType_notif().equals("new")) {
            Notification notification_1 = new NotificationCompat.Builder(this,CHANNEL_ID_1)
                    .setContentTitle("The date of this MOVIE came out \n")
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine(message)
                            .setBigContentTitle("1 new messages")
                            .setSummaryText("kinopoisk.com")
                    )
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_android)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_EMAIL)
                    .setContentIntent(pendingIntent)
                    .setGroup(GROUP_KEY_WORK_EMAIL)
                    .build();
            startForeground(1,notification_1);
        }else{
            Notification notification_2 = new NotificationCompat.Builder(this,CHANNEL_ID_2)
                    .setContentTitle("The date of this MOVIE came out \n")
                    .setStyle(new NotificationCompat.InboxStyle()
                            .addLine(message)
                            .setBigContentTitle("1 new messages")
                            .setSummaryText("kinopoisk.com")
                    )
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_android)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_EMAIL)
                    .setContentIntent(pendingIntent)
                    .setGroup(GROUP_KEY_WORK_EMAIL)
                    .build();


            startForeground(2,notification_2);
        }






        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
