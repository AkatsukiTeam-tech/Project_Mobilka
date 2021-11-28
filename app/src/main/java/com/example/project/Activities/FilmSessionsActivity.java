package com.example.project.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.project.CustomFontsLoader;
import com.example.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FilmSessionsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.object_7);

        ImageView view = findViewById(R.id.back);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilmSessionsActivity.this, DetailsFilmActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    Intent intent = new Intent(FilmSessionsActivity.this, MainActivity.class);
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

        int length = 12;

        LinearLayout list = findViewById(R.id.cinema_list);
        for (int i = 0; i < length; i++){
            LinearLayout session = new LinearLayout(this);
            LinearLayout.LayoutParams params_session = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params_session.setMargins(0,0,0,10);
            session.setOrientation(LinearLayout.HORIZONTAL);
            session.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FilmSessionsActivity.this, PlaceAndPayActivity.class);
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
            name.setText("Kinopark 6");
            name.setTextSize(16);

            TextView address = new TextView(this);
            //address.setTypeface(CustomFontsLoader.getTypeface(this, 1));
            address.setTextColor(getResources().getColor(R.color.text_gray));
            address.setText("address");
            address.setTextSize(10);

            TextView time = new TextView(this);
            //time.setTypeface(CustomFontsLoader.getTypeface(this, 1));
            time.setTextColor(getResources().getColor(R.color.text_white));
            time.setText("16:00 - 18:00");
            time.setTextSize(12);

            TextView price = new TextView(this);
            //price.setTypeface(CustomFontsLoader.getTypeface(this, 1));
            price.setTextColor(getResources().getColor(R.color.text_white));
            price.setText("1200 тг.");
            price.setTextSize(16);

            View divider = new View(this);
            divider.setBackgroundResource(R.drawable.separator);
            LinearLayout.LayoutParams params_divider = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params_divider.setMargins(60,0,60,40);

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
}
