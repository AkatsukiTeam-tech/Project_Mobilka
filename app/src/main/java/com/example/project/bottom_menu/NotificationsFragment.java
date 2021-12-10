package com.example.project.bottom_menu;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.project.Activities.MainActivity;
import com.example.project.Entities.Films;
import com.example.project.ExampleService;
import com.example.project.NotificationService;
import com.example.project.R;
import com.example.project.tab_layout.TodayFragment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static String type_notif = "";

    private String mParam1;
    private String mParam2;

    private Switch notifSwitch,announceSwitch;
    private View view;

    public static String getType_notif(){
        return type_notif;
    }
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
        announceSwitch = view.findViewById(R.id.announce_switch);

        notifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    startService(view,"new");
                }
            }
        }
        );
        announceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b) {
                   startService(view,"announce");
               }
           }
       }
        );


        return view;
    }

    public void startService(View view,String type){
        List<Films> films = TodayFragment.HttpRequest();
        List<Films> new_films = new ArrayList<>();
        List<Films> announce_films = new ArrayList<>();
        if(films!=null){
            for(Films f:films){
                if(f.isAnnounce()){
                    if(f.getFilm_date() != null){
                        new_films.add(f);
                    }else{
                        announce_films.add(f);
                    }
                }
            }
        }

        String message = "";



        Intent serviceIntent = null;
        if(type.equals("announce")){
            serviceIntent = new Intent(getActivity(), NotificationService.class);
            for(Films f:announce_films) {
                message = message + f.getFilm_orig_name() + ", \n";
            }
            serviceIntent.putExtra("message",message + "\n");
            type_notif = "announce";
            getActivity().startService(serviceIntent);
        }else if(type.equals("new")){
            serviceIntent = new Intent(getActivity(), NotificationService.class);
            for(Films f:new_films) {
                message = message + f.getFilm_orig_name() + ": "+f.getFilm_date() + ", \n";
            }
            serviceIntent.putExtra("message",message + "\n");
            type_notif = "new";
            getActivity().startService(serviceIntent);
        }





    }
}