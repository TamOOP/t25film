package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.resources.ShowResource;

import java.util.List;

import retrofit2.Retrofit;

public class KmFragmentRepository {
    private MutableLiveData<List<Promotion>> showLiveData = new MutableLiveData<>();
    private static KmFragmentRepository instance;
    private Retrofit retrofit;
    private PromotionService promotionService;

    // instance
    public static synchronized KmFragmentRepository getInstance(){
        if(instance == null){
            instance = new KmFragmentRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public KmFragmentRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        promotionService = retrofit.create(PromotionService.class);
    }
}
