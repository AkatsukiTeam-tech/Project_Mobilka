package com.example.project;

import android.app.Application;

public class App extends Application {
/*    public static final String CHANNEL_ID_1 = "exampleServiceChannel_1";
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
    }*/
    public static String url = "192.168.0.181";
    public static Long user_id = null;
    public static String email = "";
    public static String password = "";
    public static String full_name = "";

    public void CurrentUser() {
    }

    public void GlobalClass() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void GlobalClass(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
