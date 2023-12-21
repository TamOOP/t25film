package com.huce.t25film.api;

import com.huce.t25film.model.SeatStatus;
import com.huce.t25film.resources.SeatResource;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SeatService {
    @GET("seat/{seatId}")
    Call<SeatResource> getSeat(@Path("seatId") int seatId);

    @POST("seat/{seatId}")
    Call<SeatResource> updateSeat(@Path("seatId") int seatId, @Body SeatStatus status);
}
