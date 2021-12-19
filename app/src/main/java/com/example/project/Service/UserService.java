package com.example.project.Service;

import com.example.project.Entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("register")
    Call<User> createAccount(@Body User user);

    @POST("login")
    Call<User> login(@Body User user);

    @POST("update")
    Call<User> update(@Body User user);
}
