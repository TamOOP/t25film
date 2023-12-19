package com.huce.t25film.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.CinemaService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.SeatService;
import com.huce.t25film.resources.CinemaResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BookingRepository {
    private MutableLiveData<CinemaResource> cinema = new MutableLiveData<>();
    private static BookingRepository instance;
    private Retrofit retrofit;
    private CinemaService cinemaService;
    private SeatService seatService;

    // instance
    public static synchronized BookingRepository getInstance(){
        if(instance == null){
            instance = new BookingRepository();
        }
        return instance;
    }

    // build retrofit va tao api khi instance
    private BookingRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        cinemaService = retrofit.create(CinemaService.class);
        seatService = retrofit.create(SeatService.class);
    }

    public MutableLiveData<CinemaResource> getCinema(@NonNull int cinemaId, @NonNull int showId) {
        Call<CinemaResource> cinemaCall = cinemaService.getCinema(cinemaId, showId);
        cinemaCall.enqueue(new Callback<CinemaResource>() {
            @Override
            public void onResponse(Call<CinemaResource> call, Response<CinemaResource> response) {
//                Log.e("status",""+response.body().getCinema().getAmountOfSeat());
                cinema.setValue(response.body());
            }

            @Override
            public void onFailure(Call<CinemaResource> call, Throwable t) {
                Log.e("error", t.getMessage());
                getCinema(cinemaId, showId);
            }
        });
        return cinema;
    }
}
