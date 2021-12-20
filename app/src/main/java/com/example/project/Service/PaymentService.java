package com.example.project.Service;

import com.example.project.Entities.Cinemas;
import com.example.project.Entities.Films;
import com.example.project.Entities.Place;
import com.example.project.Entities.User;
import com.example.project.dto.PaymentsDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PaymentService {

    @POST("create-payments")
    Call<Place> createPayments(@Body PaymentsDTO dto);
}
