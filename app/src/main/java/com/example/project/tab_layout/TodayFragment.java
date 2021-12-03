package com.example.project.tab_layout;

import static android.content.Context.WIFI_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activities.DetailsFilmActivity;
import com.example.project.Entities.Cinemas;
import com.example.project.Entities.Countries;
import com.example.project.Entities.Directors;
import com.example.project.Entities.Films;
import com.example.project.Entities.Genres;
import com.example.project.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TodayFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    View view;
    Gson gson = new Gson();

    private String mParam1;
    private String mParam2;

    public TodayFragment() {
    }

    public static TodayFragment  newInstance(String param1, String param2) {
        TodayFragment  fragment = new TodayFragment ();
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
        view = inflater.inflate(R.layout.fragment_today, container, false);

        List<Films> films = HttpRequest();
        int total = films.size();
        int column = 3;
        int row = total/column;

        GridLayout grid = view.findViewById(R.id.grid);
        grid.setColumnCount(column);
        grid.setRowCount(row + 1);

        for (int i = 0, c = 0, r = 0; i < total; i++, c++){
            if (c == column){
                c = 0;
                r++;
            }

            CardView card = new CardView(this.getContext());
            GridLayout.LayoutParams params_card = new GridLayout.LayoutParams();

            params_card.height = GridLayout.LayoutParams.MATCH_PARENT;
            params_card.width = GridLayout.LayoutParams.WRAP_CONTENT;
            params_card.setGravity(Gravity.FILL);
            params_card.columnSpec = GridLayout.spec(c);
            params_card.rowSpec = GridLayout.spec(r);
            params_card.leftMargin = 32;
            params_card.rightMargin = 32;
            params_card.bottomMargin = 110;

            card.setBackgroundColor(getResources().getColor(R.color.background));
            card.setRadius(8);
            card.setLayoutParams(params_card);

            LinearLayout layout = new LinearLayout(this.getContext());
            LinearLayout.LayoutParams params_layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setOrientation(LinearLayout.VERTICAL);
            int finalI = i;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TodayFragment.super.getContext(), DetailsFilmActivity.class)
                            .putExtra("films", films.get(finalI));
                    startActivity(intent);
                }
            });
            params_layout.gravity = Gravity.CLIP_HORIZONTAL|Gravity.CENTER_HORIZONTAL;

            URL url = null;
            Bitmap bmp = null;
            try {
                url = new URL(films.get(finalI).getImage_url());
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageView image = new ImageView(this.getContext());
            image.setImageBitmap(bmp);

            TextView text_name = new TextView(this.getContext());
            text_name.setText(films.get(finalI).getFilm_ru_name());
            text_name.setTextSize(12);
            text_name.setTextColor(getResources().getColor(R.color.text_white));

            TextView text_genre = new TextView(this.getContext());
            text_genre.setText(films.get(finalI).getGenres().get(finalI).getGenre_name());
            text_genre.setTextSize(10);
            text_genre.setTextColor(getResources().getColor(R.color.text_gray));

            LinearLayout.LayoutParams params_image = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params_image.gravity = Gravity.CENTER;
            params_image.width = 224;
            params_image.height = 336;
            params_image.setMargins(0,0,0,20);

            LinearLayout.LayoutParams params_text = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params_text.gravity = Gravity.CENTER;

            layout.addView(image, params_image);
            layout.addView(text_name, params_text);
            layout.addView(text_genre, params_text);
            card.addView(layout, params_layout);

            grid.addView(card);
        }
        return view;
    }

    public static List<Films> HttpRequest() {

        URL url = null;
        BufferedReader reader = null;
        List<Films> films = new ArrayList<>();
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //ip ---------------------------------------------
            url = new URL("http://10.10.17.195:8080/api/allFilms");
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
                JSONArray jsonarray = new JSONArray(String.valueOf(buffer));

                Long id;
                String ru_name;
                String orig_name;
                String image_url;
                String description;
                int restriction;
                String duration;
                String date;
                List<Countries> countries = new ArrayList<>();
                List<Directors> directors = new ArrayList<>();
                List<Genres> genres = new ArrayList<>();
                List<Cinemas> cinemas = new ArrayList<>();

                JSONArray countries_json = null;
                Long country_id = null;
                String country_name = null;

                JSONArray directors_json = null;
                Long director_id = null;
                String full_name = null;

                JSONArray genre_json = null;
                Long genre_id = null;
                String genre_name = null;

                JSONArray cinema_json = null;
                Long cinema_id = null;
                String ciname_name = null;
                String ciname_address = null;

                JSONArray cities_json = null;
                Long city_id = null;
                String city_name = null;

                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject object = jsonarray.getJSONObject(i);

                    id = object.getLong("film_id");
                    ru_name = object.getString("film_ru_name");
                    orig_name = object.getString("film_orig_name");
                    description = object.getString("description");
                    image_url = object.getString("image_url");
                    restriction = object.getInt("restriction");
                    duration = object.getString("film_duration");
                    date = object.getString("film_date");

                    countries_json = object.optJSONArray("countries");
                    for (int l = 0; l < countries_json.length(); l++) {
                        JSONObject country = countries_json.getJSONObject(l);
                        country_id = country.getLong("country_id");
                        System.out.println("country_id: " + country_id);
                        country_name = country.getString("country_name");
                        countries.add(new Countries(country_id, country_name));
                    }

                    directors_json = object.optJSONArray("directors");
                    for (int j = 0; j < directors_json.length(); j++) {
                        JSONObject director = directors_json.getJSONObject(j);
                        director_id = director.getLong("director_id");
                        full_name = director.getString("full_name");
                        directors.add(new Directors(director_id, full_name));
                    }

                    genre_json = object.optJSONArray("genres");
                    for (int j = 0; j < genre_json.length(); j++) {
                        JSONObject genre = genre_json.getJSONObject(j);
                        genre_id = genre.getLong("genre_id");
                        genre_name = genre.getString("genre_name");
                        genres.add(new Genres(genre_id, genre_name));
                    }

                    films.add(new Films(id, ru_name, orig_name, image_url, description, restriction, Time.valueOf(duration), Date.valueOf(date), countries, directors, genres, cinemas));
                }


            } else {
                // ошибка
                System.out.println("########    ######       ######         ######      ######");
                System.out.println("##          ##    ##     ##    ##     ##      ##    ##    ##");
                System.out.println("########    ######       ######       ##      ##    ######");
                System.out.println("##          ##    ##     ##    ##     ##      ##    ##    ##");
                System.out.println("########    ##     ##    ##     ##      ######      ##     ##");
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return films;
    }
}
