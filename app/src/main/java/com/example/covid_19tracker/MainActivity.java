package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCountryName , textViewCountryLastUpdated;

    private TextView textViewTotalConfirmed , textViewTodayConfirmed , textViewTotalActive , textViewTotalRecovered , textViewTodayRecovered ,
            textViewTotalDeath , textViewTodayDeath , textViewTotalTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCountryName = findViewById(R.id.country_name);
        textViewCountryLastUpdated = findViewById(R.id.last_updated);
        textViewTotalConfirmed = findViewById(R.id.total_confirmed);
        textViewTodayConfirmed = findViewById(R.id.today_confirmed);
        textViewTotalActive = findViewById(R.id.total_active);
        textViewTotalRecovered = findViewById(R.id.total_recovered);
        textViewTodayRecovered = findViewById(R.id.today_recovered);
        textViewTotalDeath = findViewById(R.id.total_death);
        textViewTodayDeath = findViewById(R.id.today_death);
        textViewTotalTest = findViewById(R.id.total_tests);

    }
}