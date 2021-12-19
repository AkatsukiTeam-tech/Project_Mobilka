package com.example.project;

import android.app.Application;

public class CurrentUser extends Application {
    private Long user_id = null;
    private String email = "";
    private String password = "";
    private String full_name = "";



    public CurrentUser(Long user_id, String email, String password, String full_name) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
    }

    public CurrentUser() {
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
}
