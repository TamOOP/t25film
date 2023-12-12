package com.huce.t25film.api;

import com.huce.t25film.model.Film;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.resources.PromotionResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PromotionService {
    @GET("promotions")
    Call<List<Promotion>> getListPromotions();

    // get user theo id
    @GET("promotions/{id}")
    Call<PromotionResource> getPromotionsId(@Path("id") int id);

//    @GET("films/find/showing")
//    Call<List<Film>> getListFilmsDC();
//
//    @GET("films/find/upComing")
//    Call<List<Film>> getListFilmsSC();
//
//    @GET("films/find/earlyShow")
//    Call<List<Film>> getListFilmsSCS();
}
