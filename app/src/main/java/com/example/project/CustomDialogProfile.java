package com.example.project;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.project.Entities.User;
import com.example.project.Service.UserService;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomDialogProfile extends Dialog{

    public Activity activity;
    public Dialog dialog;
    private Retrofit retrofit;
    private MaterialButton done;
    private UserService service;
    public EditText old_pass, new_pass;
    public User user;

    public CustomDialogProfile(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_profile);

        done = findViewById(R.id.done);
        old_pass = findViewById(R.id.old_pass);
        new_pass = findViewById(R.id.new_pass);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (old_pass.getText().toString().contentEquals(App.password)){
                    user = new User(App.user_id, App.email, new_pass.getText().toString(), App.full_name);
                    if (sendUserToUpdate(user)){
                        activity.finish();
                    }
                }
                else {
                    old_pass.requestFocus();
                    old_pass.setError("Неверный старый пароль");
                    old_pass.setText("");
                }
            }
        });

    }

    private boolean sendUserToUpdate(User user){
        try {
            String URL = App.url;
            String endpoint = "http://"+URL+":8080/api/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(endpoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(UserService.class);
            Call<User> call = service.update(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
