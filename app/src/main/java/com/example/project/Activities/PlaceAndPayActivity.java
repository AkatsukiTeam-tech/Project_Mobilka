package com.example.project.Activities;

import static com.example.project.R.drawable.armchair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Entities.Films;
import com.example.project.R;

public class PlaceAndPayActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_pay);


        Films film = (Films) getIntent().getSerializableExtra("filmsObject");
        ImageView view = findViewById(R.id.back);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceAndPayActivity.this, FilmSessionsActivity.class);
                intent.putExtra("film", film);
                startActivity(intent);
            }
        });

        int rows = 6;
        int columns = 10;

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        for (int i = 0; i < rows; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.setPadding(0,20,0,20);
            tableRow.setWeightSum(2);
            tableRow.setMinimumHeight(10);
            tableRow.setClickable(true);
            tableRow.setHorizontalScrollBarEnabled(true);


            for (int j = 0; j < columns; j++){

                Button buttonView = new Button(this);
                buttonView.setBackgroundResource(armchair);
                buttonView.setText(String.valueOf(j+1));
                buttonView.setTextColor(getResources().getColor(R.color.text_white));

                buttonView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                buttonView.setTextSize(10);
                buttonView.setPadding(0,10,0,10);
                buttonView.setLinksClickable(true);

                buttonView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        buttonView.setBackgroundColor(Color.RED);
                    }
                });
                tableRow.addView(buttonView, j);
            }
            tableLayout.addView(tableRow, i);
        }
    }
}
