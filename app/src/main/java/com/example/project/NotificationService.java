package com.example.project;

import static com.example.project.App.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.project.bottom_menu.NotificationsFragment;


public class NotificationService extends Service {

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


        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("From Cinema")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1,notification);

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
