package com.example.project;

import android.app.Application;

public class GlobalClass extends Application {

    private String url = "192.168.0.181";

    public GlobalClass() {
    }

    public GlobalClass(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
