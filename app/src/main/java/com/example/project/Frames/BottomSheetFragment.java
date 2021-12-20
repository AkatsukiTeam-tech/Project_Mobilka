package com.example.project.Frames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project.Activities.MainActivity;
import com.example.project.Activities.SignInActivity;
import com.example.project.Activities.SignUpActivity;
import com.example.project.App;
import com.example.project.Entities.Cinemas;
import com.example.project.Entities.Films;
import com.example.project.Entities.Place;
import com.example.project.Entities.User;
import com.example.project.R;
import com.example.project.Service.PaymentService;
import com.example.project.Service.UserService;
import com.example.project.dto.PaymentsDTO;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BottomSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {

    private MaterialButton button;
    private Retrofit retrofit;
    private PaymentService service;

    public static Films film;
    public static Cinemas cinema;
    public static List<Place> places;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomSheetFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        button = view.findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(App.user_id, App.email, App.password, App.full_name);
                if (sendPaymentToCreate(places, film, cinema, user)){
                    getActivity().startActivity(new Intent(BottomSheetFragment.this.getActivity(), MainActivity.class));
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
            service.createPayments(new PaymentsDTO(places, film, cinema, user));

            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}