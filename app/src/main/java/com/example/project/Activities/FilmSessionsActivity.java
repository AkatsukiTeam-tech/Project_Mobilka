package com.example.project.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project.App;
import com.example.project.Entities.Films;
import com.example.project.Entities.Sessions;
import com.example.project.R;
import com.example.project.bottom_menu.HomeFragment;
import com.example.project.bottom_menu.NotificationsFragment;
import com.example.project.bottom_menu.ProfileFragment;
import com.example.project.bottom_menu.PurchasesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

public class FilmSessionsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView filmName;
    Fragment fragment;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_7);

        LinearLayout list = findViewById(R.id.cinema_list);
        ImageView view = findViewById(R.id.back);
        Films film = (Films) getIntent().getSerializableExtra("film");
        List<Sessions> sessions =  HttpGetBySessions();
        List<Sessions> sessionsList = new ArrayList<>();

        filmName = findViewById(R.id.film_name);
        filmName.setText(film.getFilm_orig_name());

        for (Sessions s : sessions){
            if(s.getFilms().getFilm_id().equals(film.getFilm_id())){
                sessionsList.add(s);
            }
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilmSessionsActivity.this, DetailsFilmActivity.class);
                intent.putExtra("films", film);
                startActivity(intent);
            }
        });
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    fragment = new HomeFragment();
                    break;
                case R.id.secondFragment:
                    fragment = new NotificationsFragment();
                    break;
                case R.id.thirdFragment:
                    fragment = new PurchasesFragment();
                    break;
                case R.id.forthFragment:
                    fragment = new ProfileFragment();
                    break;
            }

            return true;
        });

        for (int i = 0; i < sessionsList.size(); i++){
            LinearLayout session = new LinearLayout(this);
            LinearLayout.LayoutParams params_session = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params_session.setMargins(0,0,0,10);
            session.setOrientation(LinearLayout.HORIZONTAL);
            int finalI = i;
            session.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FilmSessionsActivity.this, PlaceAndPayActivity.class);
                    intent.putExtra("cinemaObject", sessionsList.get(finalI).getCinemas())
                    .putExtra("filmObject", film);
                    startActivity(intent);
                }
            });

            LinearLayout name_address = new LinearLayout(this);
            name_address.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            name_address.setOrientation(LinearLayout.VERTICAL);

            LinearLayout time_price = new LinearLayout(this);
            time_price.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            time_price.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams params_text = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params_text.gravity = Gravity.CENTER;

            TextView name = new TextView(this);
            //name.setTypeface(CustomFontsLoader.getTypeface(this, 2));
            name.setTextColor(getResources().getColor(R.color.text_white));
            name.setText(sessionsList.get(i).getCinemas().getCinema_name());
            name.setTextSize(16);

            TextView address = new TextView(this);
            //address.setTypeface(CustomFontsLoader.getTypeface(this, 1));
            address.setTextColor(getResources().getColor(R.color.text_gray));
            address.setText(sessionsList.get(i).getCinemas().getCinema_address());
            address.setTextSize(10);

            TextView time = new TextView(this);
            //time.setTypeface(CustomFontsLoader.getTypeface(this, 1));
            time.setTextColor(getResources().getColor(R.color.text_gray));
            time.setText(sessionsList.get(i).getSession_start_time() + " - " + sessionsList.get(i).getSession_end_time());
            time.setTextSize(12);

            TextView price = new TextView(this);
            //price.setTypeface(CustomFontsLoader.getTypeface(this, 1));
            price.setTextColor(getResources().getColor(R.color.text_white));
            price.setText(sessionsList.get(i).getSession_price()+" tg");
            price.setTextSize(16);

            View divider = new View(this);
            divider.setBackgroundResource(R.drawable.separator);
            LinearLayout.LayoutParams params_divider = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_divider.setMargins(60,20,60,40);

            name_address.addView(name, params_text);
            name_address.addView(address, params_text);

            time_price.addView(time, params_text);
            time_price.addView(price, params_text);

            session.addView(name_address);
            session.addView(time_price);

            list.addView(session);
            list.addView(divider, params_divider);

        }
    }

    public static List<Sessions> HttpGetBySessions() {

        URL url = null;
        BufferedReader reader = null;
        String URL = App.url;
        List<Sessions> sessions = new ArrayList<>();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //ip ---------------------------------------------
            url = new URL("http://"+ URL +":8080/api/allSessions");
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
                Type listType = new TypeToken<List<Sessions>>(){}.getType();
                List<Sessions> sessionsList = gson.fromJson(jsonOutput, listType);
                sessions.addAll(sessionsList);
                System.out.println("----------------------------------------");

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

        return sessions;
    }
}
