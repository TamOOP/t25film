package com.huce.t25film.api;

import com.huce.t25film.resources.CinemaResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CinemaService {
    @GET("cinema/{cinemaId}")
    Call<CinemaResource> getCinema(@Path("cinemaId") int cinemaId, @Query("showId") int showId);
}
