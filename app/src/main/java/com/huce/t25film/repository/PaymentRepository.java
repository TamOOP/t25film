package com.huce.t25film.repository;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.SeatService;

import retrofit2.Retrofit;

public class PaymentRepository {
    private static PaymentRepository instance;
    private Retrofit retrofit;
    private SeatService seatService;

    // instance
    public static synchronized PaymentRepository getInstance(){
        if(instance == null){
            instance = new PaymentRepository();
        }
        return instance;
    }

    // build retrofit va tao api khi instance
    private PaymentRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        seatService = retrofit.create(SeatService.class);
    }
}
