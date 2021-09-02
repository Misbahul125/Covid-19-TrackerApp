package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.covid_19tracker.api.ApiUtilities;
import com.example.covid_19tracker.api.CountryAdapter;
import com.example.covid_19tracker.api.CountryModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText editTextSearch;
    private ImageButton imageButtonBack , imageButtonSearch;
    private ArrayList<CountryModel> countryModelList;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        imageButtonBack = findViewById(R.id.image_button_back);
        editTextSearch = findViewById(R.id.edit_text_search);
        imageButtonSearch = findViewById(R.id.image_button_search);
        recyclerView = findViewById(R.id.recycler_country_list);

        countryModelList = new ArrayList<>();

        ApiUtilities.getApiInterface()
                .getCountryData()
                .enqueue(new Callback<List<CountryModel>>() {
                    @Override
                    public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                        countryModelList.addAll(response.body());

                        countryAdapter = new CountryAdapter(CountryListActivity.this , countryModelList);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(CountryListActivity.this));
                        recyclerView.setAdapter(countryAdapter);
                        countryAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<CountryModel>> call, Throwable t) {
                        Log.d("ERROR" , t.getMessage());
                        Toast.makeText(CountryListActivity.this, "Error in fetching data. Please try again after sometime.",
                                Toast.LENGTH_LONG).show();
                    }
                });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterCountry(s.toString());
            }
        });

        imageButtonSearch.setOnClickListener(v ->
                filterCountry(editTextSearch.getText().toString()));

        imageButtonBack.setOnClickListener(v ->
            super.onBackPressed());
    }

    private void filterCountry(String searchText) {
        ArrayList<CountryModel> arrayList = new ArrayList<>();

        for (CountryModel items : countryModelList) {
            if (items.getCountry().toLowerCase().contains(searchText.toLowerCase())) {
                arrayList.add(items);
            }
        }

        countryAdapter.searchCountry(arrayList);
    }

}