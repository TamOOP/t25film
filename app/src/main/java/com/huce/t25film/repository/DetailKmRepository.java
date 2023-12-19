package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.resources.PromotionResource;

import retrofit2.Retrofit;

public class DetailKmRepository {
    private MutableLiveData<PromotionResource> kmLiveData = new MutableLiveData<>();
    private static DetailKmRepository instance;
    private Retrofit retrofit;
    private PromotionService promotionService;

    // instance
    public static synchronized DetailKmRepository getInstance(){
        if(instance == null){
            instance = new DetailKmRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public DetailKmRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        promotionService = retrofit.create(PromotionService.class);
    }
}
