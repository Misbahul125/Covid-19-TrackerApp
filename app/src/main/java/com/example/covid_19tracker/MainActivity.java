package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19tracker.api.ApiUtilities;
import com.example.covid_19tracker.api.CountryModel;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCountryName , textViewCountryLastUpdated;

    private TextView textViewTotalConfirmed , textViewTodayConfirmed , textViewTotalActive , textViewTotalRecovered , textViewTodayRecovered ,
            textViewTotalDeath , textViewTodayDeath , textViewTotalTest;

    private long lastUpdated;
    private int totalConfirmed , todayConfirmed , totalActive , totalRecovered , todayRecovered , totalDeath , todayDeath , totalTest;

    private PieChart pieChart;

    private ProgressDialog progressDialog;

    private Button buttonSeeMore , buttonRefresh;

    private List<CountryModel> countryModelList;

    private String countryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        progressDialog.show();

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
        pieChart = findViewById(R.id.pie_chart);
        buttonSeeMore = findViewById(R.id.button_see_more);
        buttonRefresh = findViewById(R.id.button_refresh);

        countryModelList = new ArrayList<>();

        textViewCountryName.setOnClickListener(v ->
            startActivity(new Intent(MainActivity.this , CountryListActivity.class))
        );

        buttonSeeMore.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this , CountryListActivity.class));
        });

        buttonRefresh.setOnClickListener(v -> {
            progressDialog.show();
            loadCountryData();
        });

        loadCountryData();

    }

    private void loadCountryData() {
        ApiUtilities.getApiInterface()
                .getCountryData()
                .enqueue(new Callback<List<CountryModel>>() {
                    @Override
                    public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                        if (response.body() != null) {
                            countryModelList.addAll(response.body());

                            if((getIntent().getStringExtra("COUNTRY_NAME") != null) && (!getIntent().getStringExtra("COUNTRY_NAME").isEmpty())) {
                                countryName = getIntent().getStringExtra("COUNTRY_NAME");
                            }
                            else {
                                countryName = "India";
                            }

                            progressDialog.dismiss();

                            for (int i = 0 ; i<countryModelList.size() ; i++) {

                                if (countryModelList.get(i).getCountry().equals(countryName)) {

                                    textViewCountryName.setText(countryName);

                                    lastUpdated = Long.parseLong(countryModelList.get(i).getUpdated());
                                    totalConfirmed = Integer.parseInt(countryModelList.get(i).getCases());
                                    todayConfirmed = Integer.parseInt(countryModelList.get(i).getTodayCases());
                                    totalActive = Integer.parseInt(countryModelList.get(i).getActive());
                                    totalRecovered = Integer.parseInt(countryModelList.get(i).getRecovered());
                                    todayRecovered = Integer.parseInt(countryModelList.get(i).getTodayRecovered());
                                    totalDeath = Integer.parseInt(countryModelList.get(i).getDeaths());
                                    todayDeath = Integer.parseInt(countryModelList.get(i).getTodayDeaths());
                                    totalTest = Integer.parseInt(countryModelList.get(i).getTests());

                                    DateFormat dateFormat = DateFormat.getDateTimeInstance();
                                    textViewCountryLastUpdated.setText(("Last updated at  :  ")+dateFormat.format(lastUpdated));

                                    NumberFormat numberFormat = NumberFormat.getInstance();
                                    textViewTotalConfirmed.setText(numberFormat.format(totalConfirmed));
                                    textViewTodayConfirmed.setText(("+")+numberFormat.format(todayConfirmed));
                                    textViewTotalActive.setText(numberFormat.format(totalActive));
                                    textViewTotalRecovered.setText(numberFormat.format(totalRecovered));
                                    textViewTodayRecovered.setText(("+")+numberFormat.format(todayRecovered));
                                    textViewTotalDeath.setText(numberFormat.format(totalDeath));
                                    textViewTodayDeath.setText(("+")+numberFormat.format(todayDeath));
                                    textViewTotalTest.setText(numberFormat.format(totalTest));

                                    pieChart.addPieSlice(new PieModel("Confirmed", totalConfirmed, getResources().getColor(R.color.yellow)));
                                    pieChart.addPieSlice(new PieModel("Active", totalActive, getResources().getColor(R.color.blue_pie)));
                                    pieChart.addPieSlice(new PieModel("Recovered", totalRecovered, getResources().getColor(R.color.green_pie)));
                                    pieChart.addPieSlice(new PieModel("Deaths", totalDeath, getResources().getColor(R.color.red_pie)));

                                    pieChart.startAnimation();
                                }
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                        progressDialog.dismiss();
                        Log.d("ERROR" , t.getMessage());
                        Toast.makeText(MainActivity.this, "Error in fetching data. Please try again after sometime.",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}