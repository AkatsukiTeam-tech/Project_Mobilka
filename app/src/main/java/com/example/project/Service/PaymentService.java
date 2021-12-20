package com.example.project.Service;

import com.example.project.Entities.Place;
import com.example.project.dto.PaymentsDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PaymentService {

    @POST("create-payments")
    Call<Place> createPayments(@Body PaymentsDTO dto);
}
