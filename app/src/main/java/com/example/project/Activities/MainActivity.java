package com.example.project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.project.Entities.Films;
import com.example.project.ExampleService;
import com.example.project.R;
import com.example.project.bottom_menu.HomeFragment;
import com.example.project.bottom_menu.NotificationsFragment;
import com.example.project.bottom_menu.ProfileFragment;
import com.example.project.bottom_menu.PurchasesFragment;
import com.example.project.tab_layout.TodayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment = new HomeFragment();
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Films> films = TodayFragment.HttpRequest();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    fragment = new HomeFragment();
                    break;
                case R.id.secondFragment:
                    intent = new Intent(this, NotificationsFragment.class)
                            .putExtra("films_list", (Serializable) films);
                    fragment = new NotificationsFragment();
                    break;
                case R.id.thirdFragment:
                    fragment = new PurchasesFragment();
                    break;
                case R.id.forthFragment:
                    fragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            if(intent!=null){
                startService(intent);
            }
            return true;
        });
    }

    public void startService(View view) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
    }
}