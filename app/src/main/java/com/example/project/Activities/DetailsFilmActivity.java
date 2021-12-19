package com.example.project.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.App;
import com.example.project.Entities.Countries;
import com.example.project.Entities.Films;
import com.example.project.Entities.Genres;
import com.example.project.Entities.Trailers;
import com.example.project.ExpandableTextView;
import com.example.project.R;
import com.example.project.bottom_menu.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailsFilmActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView detailsFilm, orig_name, limit_of_age,
            textGenreView, textDirectorView, textDurationView, textCountryView;
    ImageView image;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_film);
        Films films = (Films) getIntent().getSerializableExtra("films");

        List<Trailers> trailers = HttpGetByTrailers();

        detailsFilm = findViewById(R.id.detailsFilm);
        orig_name = findViewById(R.id.orig_name);
        limit_of_age = findViewById(R.id.limit_of_age);
        youTubePlayerView = findViewById(R.id.youtube_id);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String video_id = trailers.get(trailers.size() - 1).getTrailer_url();
                youTubePlayer.loadVideo(video_id, 0);
                youTubePlayer.pause();
            }
        });


        if(films.getRestriction() == null){
            films.setRestriction(0);
        }
        limit_of_age.setText(films.getRestriction() + "+");
        orig_name.setText(films.getFilm_orig_name());
        detailsFilm.setText(films.getFilm_ru_name());

        textCountryView = findViewById(R.id.textView);
        textDirectorView = findViewById(R.id.textView2);
        textGenreView = findViewById(R.id.film_genre);
        textDurationView = findViewById(R.id.textView3);

        image = findViewById(R.id.image);

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

        String genres = "";
        for(Genres g:films.getGenres()){
            genres += g.getGenre_name() + ", ";
        }
        ExpandableTextView textView = findViewById(R.id.tv_text);
        try {
            textGenreView.setText(genres.substring(0, genres.length()-2));
            textCountryView.setText(countries.substring(0, countries.length()-2));
            textDirectorView.setText(films.getDirectors().get(0).getFull_name());
            textDurationView.setText(films.getFilm_duration());
            textView.setText(films.getDescription());
            System.out.println(films.getDescription());
        }catch (Exception e){
            e.printStackTrace();
        }


        MaterialButton buy = findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsFilmActivity.this, FilmSessionsActivity.class);
                intent.putExtra("film", films);
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
                    intent = new Intent(DetailsFilmActivity.this, NotificationsFragment.class);
                    startActivity(intent);
                    break;
                case R.id.thirdFragment:
                    intent = new Intent(DetailsFilmActivity.this, PurchasesFragment.class);
                    startActivity(intent);
                    break;
                case R.id.forthFragment:
                    intent = new Intent(DetailsFilmActivity.this, ProfileFragment.class);
                    startActivity(intent);
                    break;
            }

            return true;
        });
    }

    public static List<Trailers> HttpGetByTrailers() {

        URL url = null;
        BufferedReader reader = null;
        String URL = App.url;
        List<Trailers> trailers = new ArrayList<>();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //ip ---------------------------------------------
            url = new URL("http://" + URL + ":8080/api/allTrailers");
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
                Type listType = new TypeToken<List<Trailers>>(){}.getType();
                List<Trailers> trailersList = gson.fromJson(jsonOutput, listType);
                trailers.addAll(trailersList);
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

        return trailers;
    }

}