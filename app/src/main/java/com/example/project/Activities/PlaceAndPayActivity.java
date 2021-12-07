package com.example.project.Activities;

import static com.example.project.R.drawable.armchair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class PlaceAndPayActivity extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_pay);

        ImageView view = findViewById(R.id.back);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlaceAndPayActivity.this, FilmSessionsActivity.class);
                startActivity(intent);
            }
        });

        int rows = 12;
        int columns = 10;

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        for (int i = 0; i < rows; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.setHorizontalScrollBarEnabled(true);

            for (int j = 0; j < columns; j++){
                TextView textView = new TextView(this);
                textView.setText(String.valueOf(j+1));
                textView.setBackgroundResource(armchair);
                textView.setTextColor(getResources().getColor(R.color.text_white));
                textView.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(10);
                textView.setLinksClickable(true);
                




                tableRow.addView(textView, j);
            }
            tableLayout.addView(tableRow, i);
        }
    }
}
