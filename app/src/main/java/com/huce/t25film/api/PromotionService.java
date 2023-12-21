package com.huce.t25film.api;

import com.huce.t25film.model.Promotion;
import com.huce.t25film.resources.PromotionResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PromotionService {
    @GET("promotions")
    Call<List<Promotion>> getListPromotions();

    // get user theo id
    @GET("promotions/{id}")
    Call<PromotionResource> getPromotionsId(@Path("id") int id);

    @GET("promotions/findByCode")
    Call<PromotionResource> getPromotionByCode(@Query("code") String code);
}
