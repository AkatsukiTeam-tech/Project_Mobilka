package com.example.project.bottom_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.project.CustomListAdapter;
import com.example.project.Entities.Purchases;
import com.example.project.R;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class PurchasesFragment extends Fragment {

    View view;
    LinearLayout layout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PurchasesFragment() {
    }

    public static PurchasesFragment newInstance(String param1, String param2) {
        PurchasesFragment fragment = new PurchasesFragment();
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
        view = inflater.inflate(R.layout.fragment_purchases, container, false);

        List<Purchases> purchases = getListData();
        final ListView listView = (ListView) view.findViewById(R.id.list_purchases);
        listView.setAdapter(new CustomListAdapter(purchases, inflater, getContext()));



        return  view;
    }

    private List<Purchases> getListData(){
        List<Purchases> list = new ArrayList<Purchases>();
        Purchases p1 = new Purchases(new Date(2021, 11, 15), 2600, "Человек-паук 3", new Time(2, 30, 0), null);
        Purchases p2 = new Purchases(new Date(2021, 11, 15), 2600, "Человек-паук 3", new Time(2, 30, 0), null);
        Purchases p3 = new Purchases(new Date(2021, 11, 15), 2600, "Человек-паук 3", new Time(2, 30, 0), null);


        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;

    }
}