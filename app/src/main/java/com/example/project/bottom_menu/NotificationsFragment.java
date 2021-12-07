package com.example.project.bottom_menu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.project.NotificationService;
import com.example.project.R;

public class NotificationsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Switch notifSwitch;
    private View view;

    public NotificationsFragment() {
    }

    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
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
        view = inflater.inflate(R.layout.fragment_notificatons, container, false);

        notifSwitch = view.findViewById(R.id.notif_switch);

        notifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startService(view);
                } else {
                    // The toggle is disabled
                }
            }
        });
        return view;
    }

    public void startService(View v){
        String message = "Welcome";
        Intent serviceIntent = new Intent(getActivity(), NotificationService.class);
        serviceIntent.putExtra("message",message);

        getActivity().startService(serviceIntent);
    }

    public void stopService(View v){
        Intent serviceIntent = new Intent(getActivity(), NotificationService.class);
        getActivity().stopService(serviceIntent);

    }
}