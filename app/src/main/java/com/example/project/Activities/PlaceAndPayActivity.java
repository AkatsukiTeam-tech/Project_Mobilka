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
import com.example.project.R;

public class PlaceAndPayActivity extends AppCompatActivity {

    TextView cinema_name;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_pay);

        Cinemas cinemas = (Cinemas) getIntent().getSerializableExtra("cinemaObject");
        Films film = (Films) getIntent().getSerializableExtra("filmObject");
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

                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Place place = new Place(null , 2,2, null);

                        buttonView.setBackgroundResource(armchair_active);
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

    }


}
