package com.example.project.Activities;

import static com.example.project.R.drawable.armchair;
import static com.example.project.R.drawable.armchair_active;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Entities.Cinemas;
import com.example.project.Entities.Films;
import com.example.project.Entities.Place;
import com.example.project.Entities.Sessions;
import com.example.project.Frames.BottomSheetFragment;
import com.example.project.R;

import java.util.ArrayList;
import java.util.List;

public class PlaceAndPayActivity extends AppCompatActivity {

    TextView cinema_name;
    Button payButton;
    TextView place;
    String result = "";
    List<Place> places = new ArrayList<>();
    Films film;
    Cinemas cinemas;
    Sessions session;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_pay);

        cinemas = (Cinemas) getIntent().getSerializableExtra("cinemaObject");
        film = (Films) getIntent().getSerializableExtra("filmObject");
        session = (Sessions) getIntent().getSerializableExtra("session");
        ImageView view = findViewById(R.id.back);
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        cinema_name = findViewById(R.id.cinema_name);
        cinema_name.setText(cinemas.getCinema_name());
        System.out.println(cinemas.getCinema_name());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceAndPayActivity.this, FilmSessionsActivity.class);
                intent.putExtra("film", film);
                startActivity(intent);
            }
        });

        int rows = 12;
        int columns = 10;

        boolean busy = false;
        for (int i = 0; i < rows; i++){

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.setWeightSum(1);
            tableRow.setClickable(true);
            tableRow.setHorizontalScrollBarEnabled(true);

            LinearLayout layout_text = new LinearLayout(this);
            TextView row = new TextView(this);
            LinearLayout.LayoutParams params_text = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params_text.gravity = Gravity.CENTER;
            params_text.setMargins(18, 8, 0,0);
            row.setText(String.valueOf(i+1));
            row.setTextColor(getResources().getColor(R.color.text_gray));
            layout_text.addView(row, params_text);

            for (int j = 0; j < columns; j++){
                LinearLayout layout = new LinearLayout(this);
                Button buttonView = new Button(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                params.width = 40;
                params.height = 40;
                params.setMargins(20,20,20,20);
                buttonView.setBackgroundResource(armchair);

                buttonView.setText(String.valueOf(j));
                buttonView.setTextSize(10);
                buttonView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                buttonView.setPadding(0,0,0,0);
                buttonView.setTextColor(getResources().getColor(R.color.text_white));

                buttonView.setLinksClickable(true);

                int finalI = i;
                int finalJ = j;
                buttonView.setOnClickListener(new View.OnClickListener() {
                    boolean busy = false;

                    @Override
                    public void onClick(View view) {
                        if(busy){
                            busy = false;
                            buttonView.setBackgroundResource(armchair);
                            for(int i = 0; i < places.size(); i++){
                                if(places.get(i).getPlaceX().equals(Integer.parseInt(String.valueOf(finalI))) && places.get(i).getPlaceY().equals(Integer.parseInt(String.valueOf(finalJ)))){
                                    places.remove(places.get(i));
                                    System.out.println(places.toString());
                                }
                            }
                        }else{
                            busy = true;
                            buttonView.setBackgroundResource(armchair_active);
                            result = " "+ finalI +"," + finalJ;
                            places.add(new Place(Long.parseLong(String.valueOf(places.size())),finalI,finalJ));
                            System.out.println(places.toString());
                        }
                    }
                });

                layout.addView(buttonView, params);

                if (j == 0){
                    tableRow.addView(layout_text, j);
                }
                else {
                    tableRow.addView(layout, j);
                }
            }

            tableLayout.addView(tableRow, i);

        }

        payButton = findViewById(R.id.buy);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragment dialog = new BottomSheetFragment();
                BottomSheetFragment.cinema = cinemas;
                BottomSheetFragment.film = film;
                BottomSheetFragment.places = places;
                BottomSheetFragment.session = session;
                dialog.show(getSupportFragmentManager(),dialog.getTag());



            }
        });
    }

    public Place createPlace(Integer place_x, Integer place_y){
        Place place = new Place(null, place_x, place_y, null);
        return place;
    }


}
