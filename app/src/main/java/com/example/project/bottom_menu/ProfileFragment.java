package com.example.project.bottom_menu;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.App;
import com.example.project.CustomDialogProfile;
import com.example.project.R;
import com.google.android.material.button.MaterialButton;


public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View view;
    private TextView myPurchases, signOut;
    private TextView full_name, email;
    private MaterialButton edit;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
/*
        User currentUser = (User) this.getActivity().getIntent().getSerializableExtra("currentUser");*/

        myPurchases = view.findViewById(R.id.myPurchases);
        full_name = view.findViewById(R.id.full_name);
        email = view.findViewById(R.id.email);
        edit = view.findViewById(R.id.edit);

        full_name.setText(App.full_name);
        email.setText(App.email);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogProfile customDialogProfile = new CustomDialogProfile(ProfileFragment.this.getActivity());
                customDialogProfile.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialogProfile.show();
            }
        });

        myPurchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new PurchasesFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        signOut = view.findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        return view;
    }
}