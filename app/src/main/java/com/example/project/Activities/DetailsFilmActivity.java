package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.Entities.Countries;
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
    TextView detailsFilm, orig_name, limit_of_age,
            textGenreView, textDirectorView, textDurationView, textCountryView;
    ImageView image, back_film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_film);
        Films films = (Films) getIntent().getSerializableExtra("films");

        detailsFilm = findViewById(R.id.detailsFilm);
        orig_name = findViewById(R.id.orig_name);
        limit_of_age = findViewById(R.id.limit_of_age);

        limit_of_age.setText(String.valueOf(films.getRestriction()) + "+");
        orig_name.setText(films.getFilm_orig_name());
        detailsFilm.setText(films.getFilm_ru_name());

        textCountryView = findViewById(R.id.textView);
        textDirectorView = findViewById(R.id.textView2);
        textGenreView = findViewById(R.id.film_genre);
        textDurationView = findViewById(R.id.textView3);

        image = findViewById(R.id.image);
        back_film = findViewById(R.id.back_film);

        String countries = "";
        for (Countries country : films.getCountries()){
            countries += country.getCountry_name() + ", ";
        }

        URL url = null;
        Bitmap bmp = null;
        try {
            url = new URL(films.getImage_url());
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        image.setImageBitmap(bmp);
        textCountryView.setText(countries.substring(0, countries.length()-2));
        textDirectorView.setText(films.getDirectors().get(0).getFull_name());
        textDurationView.setText(films.getFilm_duration().toString());

        String genres = "";
        for(Genres g:films.getGenres()){
            genres += g.getGenre_name() + ", ";
        }
        textGenreView.setText(genres.substring(0, genres.length()-2));

        ExpandableTextView textView = findViewById(R.id.tv_text);
        textView.setText(films.getDescription());

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
}