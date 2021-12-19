package com.example.project.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.App;
import com.example.project.Entities.User;
import com.example.project.R;
import com.example.project.Service.UserService;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {

    private TextView email;
    private TextView password;
    private MaterialButton login;
    private Retrofit retrofit;
    private UserService service;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        email = findViewById(R.id.text_email);
        password = findViewById(R.id.text_password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(null, String.valueOf(email.getText()), String.valueOf(password.getText()), null);
                sendUserToLogin(user);
            }
        });


    }

    private boolean sendUserToLogin(User user){
        try {
            String URL = App.url;
            String endpoint = "http://"+URL+":8080/api/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(endpoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(UserService.class);
            Call<User> call = service.login(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        assert response.body() != null;

                        App.user_id = response.body().getUser_id();
                        App.full_name = response.body().getFull_name();
                        App.email = response.body().getEmail();
                        App.password = response.body().getPassword();

                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                    }
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

    public void toMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toSignUpActivity(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}
