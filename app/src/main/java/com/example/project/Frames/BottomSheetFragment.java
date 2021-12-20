package com.example.project.Frames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Activities.MainActivity;
import com.example.project.App;
import com.example.project.Entities.Cinemas;
import com.example.project.Entities.Films;
import com.example.project.Entities.Place;
import com.example.project.Entities.Sessions;
import com.example.project.Entities.User;
import com.example.project.R;
import com.example.project.Service.PaymentService;
import com.example.project.dto.PaymentsDTO;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private MaterialButton confirm;
    private Retrofit retrofit;
    private PaymentService service;
    private EditText email;
    private EditText card;
    private TextView price;

    public static Films film;
    public static Cinemas cinema;
    public static Sessions session;
    public static List<Place> places;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public BottomSheetFragment() {
    }

    public static BottomSheetFragment newInstance(String param1, String param2) {
        BottomSheetFragment fragment = new BottomSheetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        price = view.findViewById(R.id.price);
        price.setText(String.valueOf(session.getSession_price()*places.size()));

        email = view.findViewById(R.id.email);
        email.setText(App.email);

        card = view.findViewById(R.id.card);
        card.setText("**** 1234");

        confirm = view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(App.user_id, App.email, App.password, App.full_name);
                if (sendPaymentToCreate(places, film, cinema, user)){
                    startActivity(new Intent(BottomSheetFragment.this.getActivity(), MainActivity.class));
                }
                else {
                    Toast.makeText(BottomSheetFragment.this.getActivity(), "Повторите позже", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private boolean sendPaymentToCreate(List<Place> places, Films film, Cinemas cinema, User user){
        try {
            String URL = App.url;
            String endpoint = "http://"+URL+":8080/api/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(endpoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(PaymentService.class);
            Call<Place> call = service.createPayments(new PaymentsDTO(places, film, cinema, user, session.getSession_price()*places.size()));
            call.enqueue(new Callback<Place>() {
                @Override
                public void onResponse(Call<Place> call, Response<Place> response) {

                }

                @Override
                public void onFailure(Call<Place> call, Throwable t) {

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