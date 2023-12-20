package com.huce.t25film.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String BASE_URL = "https://t25film.000webhostapp.com/api/";


    public static Retrofit buildRetrofit() {
        Retrofit  sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return sRetrofit;
    }
}
