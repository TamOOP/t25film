package com.huce.t25film.api;

import com.huce.t25film.model.Show;
import com.huce.t25film.resources.ShowResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShowService {
    @GET("show")
    Call<List<Show>> getListShow();

    // get user theo id
    @GET("show/findByDate")
    Call<ShowResource> getShowsId(@Query("date") String findbyDate);
}
