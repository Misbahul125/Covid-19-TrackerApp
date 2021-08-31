package com.example.covid_19tracker.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit = null;

    public static RestAPI getApiInterface() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(RestAPI.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(RestAPI.class);
    }
}
