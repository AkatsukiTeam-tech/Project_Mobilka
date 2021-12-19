package com.example.project.Service;

import com.example.project.Entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("register")
    Call<User> createAccount(@Body User user);
}