package com.example.project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.project.ExpandableTextView;
import com.example.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class DetailsFilmActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_film);

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
}