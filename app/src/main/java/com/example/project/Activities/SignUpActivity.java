package com.example.project.Activities;

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

public class SignUpActivity extends AppCompatActivity {

    private TextView full_name;
    private TextView email;
    private TextView password;
    private TextView confirm_password;
    private Retrofit retrofit;
    private MaterialButton done;
    private UserService service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        done = findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation(full_name, email, password, confirm_password)){
                    User user = new User(null, String.valueOf(email.getText()), String.valueOf(password.getText()), String.valueOf(full_name.getText()));

                    if (sendUserToRegister(user))
                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));

                }

            }
        });


    }

    private boolean sendUserToRegister(User user){
        try {
            String URL = App.url;
            String endpoint = "http://"+URL+":8080/api/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(endpoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(UserService.class);
            Call<User> call = service.createAccount(user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "Пользователь уже существует", Toast.LENGTH_SHORT).show();
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


    public boolean validation(TextView full_name, TextView email, TextView password, TextView confirm_password){
        if (full_name.getText().length() == 0){
            full_name.requestFocus();
            full_name.setError("Заполните поле");
            return false;
        }
        else if (email.getText().length() == 0){
            email.requestFocus();
            email.setError("Заполните поле");
            return false;
        }
        else if (password.getText().length() == 0){
            password.requestFocus();
            password.setError("Заполните поле");
            return false;
        }
        else if (!confirm_password.getText().toString().contentEquals(password.getText().toString())){
            confirm_password.requestFocus();
            confirm_password.setError("Не совпадает");
            confirm_password.setText("");
            return false;
        }
        else
            return true;
    }

    public void toMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void toSignInActivity(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }
}
