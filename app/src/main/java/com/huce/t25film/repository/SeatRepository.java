package com.huce.t25film.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.SeatService;
import com.huce.t25film.model.SeatStatus;
import com.huce.t25film.resources.SeatResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SeatRepository {
    private static SeatRepository instance;
    private Retrofit retrofit;
    private SeatService seatService;

    // instance
    public static synchronized SeatRepository getInstance(){
        if(instance == null){
            instance = new SeatRepository();
        }
        return instance;
    }

    // build retrofit va tao api khi instance
    private SeatRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        seatService = retrofit.create(SeatService.class);
    }

    public void updateSeat(@NonNull int seatId, int isSelected, int showId){
        SeatStatus status = new SeatStatus(isSelected, showId);
        Call<SeatResource> call = seatService.updateSeat(seatId, status);
        call.enqueue(new Callback<SeatResource>() {
            @Override
            public void onResponse(Call<SeatResource> call, Response<SeatResource> response) {
                if (response.isSuccessful()){
                    return;
                }else {
                    Log.e("error seat repository", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<SeatResource> call, Throwable t) {
                Log.e("error", t.getMessage());
                updateSeat(seatId, isSelected, showId);
            }
        });
    }
}
