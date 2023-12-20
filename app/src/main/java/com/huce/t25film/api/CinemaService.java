package com.huce.t25film.api;

import com.huce.t25film.resources.CinemaResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CinemaService {
    @GET("cinema/findByShow")
    Call<CinemaResource> getCinema(@Query("showId") int showId);
}
