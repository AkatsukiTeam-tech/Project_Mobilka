package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project.Entities.Films;
import com.example.project.Entities.Genres;
import com.example.project.ExpandableTextView;
import com.example.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class DetailsFilmActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView detailsFilm,textGenreView,textDirectorView,textDurationView,textCountryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_film);
        Films films = (Films) getIntent().getSerializableExtra("films");

        detailsFilm = findViewById(R.id.detailsFilm);
        detailsFilm.setText(films.getFilm_name());

        textCountryView = findViewById(R.id.textView);
        textDirectorView = findViewById(R.id.textView2);
        textGenreView = findViewById(R.id.film_genre);
        textDurationView = findViewById(R.id.textView3);

        textCountryView.setText(films.getCinemas().getCities().getCity_name());
        textDirectorView.setText(films.getDirectors().get(0).getFull_name());
        textDurationView.setText(films.getFilm_date().toString());
        String genres = "";
        for(Genres g:films.getGenres()){
            genres = g.getGenre_name() + genres + "; ";
        }
        textGenreView.setText(genres);

        ExpandableTextView textView = findViewById(R.id.tv_text);
        textView.setText("Java - это объектно-ориентированный язык программирования, " +
                "который не только поглощает Различные преимущества языка C ++ также " +
                "отказываются от концепций множественного наследования и указателей, " +
                "которые трудно понять в C++ Поэтому язык Java имеет две особенности: " +
                "мощный и простой в использовании. Язык Java как представитель статического " +
                "объектно-ориентированного языка программирования Отличная реализация " +
                "объектно-ориентированной теории, позволяющая программистам выполнять " +
                "сложное программирование элегантным образом мышления.");

        MaterialButton buy = findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsFilmActivity.this, FilmSessionsActivity.class);
                startActivity(intent);
            }
        });

        ImageButton detailFilm = findViewById(R.id.back);
        detailFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsFilmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    Intent intent = new Intent(DetailsFilmActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.secondFragment:
                    break;
                case R.id.thirdFragment:
                    break;
                case R.id.forthFragment:
                    break;
            }

            return true;
        });
    }

    private Films getFilmFromRequest() {

        URL url = null;
        BufferedReader reader = null;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //ip ---------------------------------------------
            //url = new URL("http://192.168.1.212:8080/api/getFilm/"+);
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.setRequestMethod("GET");
            connection.connect();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}