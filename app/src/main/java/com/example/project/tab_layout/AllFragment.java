package com.example.project.tab_layout;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.project.App;
import com.example.project.CustomListAdapter;
import com.example.project.Entities.Films;
import com.example.project.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View view;
    private GridView gridView;

    private String mParam1;
    private String mParam2;

    public AllFragment() {
    }

    public static AllFragment  newInstance(String param1, String param2) {
        AllFragment  fragment = new AllFragment ();
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
        view = inflater.inflate(R.layout.fragment_all, container, false);

        List<Films> films = HttpRequest();

        gridView = view.findViewById(R.id.grid_view);
        gridView.setAdapter(new CustomListAdapter(films, inflater, this.getContext()));

        return view;
    }

    public static List<Films> HttpRequest() {

        URL url = null;
        BufferedReader reader = null;
        String URL = App.url;
        List<Films> films = new ArrayList<>();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //ip ---------------------------------------------
            url = new URL("http://" + URL + ":8080/api/allFilms");
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // все ок
                InputStream inputStream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }
                System.out.println(buffer);
                Gson gson = new Gson();
                String jsonOutput = String.valueOf(buffer);
                Type listType = new TypeToken<List<Films>>(){}.getType();
                List<Films> filmsList = gson.fromJson(jsonOutput, listType);
                films.addAll(filmsList);

            } else {
                // ошибка
                System.out.println("--------------------------------------------------------------");
                System.out.println("########    ######       ######         ######      ######");
                System.out.println("##          ##    ##     ##    ##     ##      ##    ##    ##");
                System.out.println("########    ######       ######       ##      ##    ######");
                System.out.println("##          ##    ##     ##    ##     ##      ##    ##    ##");
                System.out.println("########    ##     ##    ##     ##      ######      ##     ##");
            }


        } catch (IOException protocolException) {
            protocolException.printStackTrace();
        }

        return films;
    }
}
