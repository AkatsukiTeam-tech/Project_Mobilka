package com.example.project;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_ID_1 = "exampleServiceChannel_1";
    public static final String CHANNEL_ID_2 = "exampleServiceChannel_2";

    @Override
    public void onCreate() {
        super.onCreate();
        
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel serviceChannel_1 = new NotificationChannel(
                    CHANNEL_ID_1,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationChannel serviceChannel_2 = new NotificationChannel(
                    CHANNEL_ID_2,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel_1);
            manager.createNotificationChannel(serviceChannel_2);

        }
    }
}
