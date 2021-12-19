package com.example.project.bottom_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.project.Entities.Films;
import com.example.project.R;
import com.example.project.tab_layout.TodayFragment;

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

    }
}