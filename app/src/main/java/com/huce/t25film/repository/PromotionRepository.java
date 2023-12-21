package com.huce.t25film.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.resources.PromotionResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PromotionRepository {
    private MutableLiveData<PromotionResource> promotion;
    private static PromotionRepository instance;
    private Retrofit retrofit;
    private PromotionService promotionService;

    // instance
    public static synchronized PromotionRepository getInstance(){
        if(instance == null){
            instance = new PromotionRepository();
        }
        return instance;
    }

    // build retrofit va tao api khi instance
    private PromotionRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        promotionService = retrofit.create(PromotionService.class);
    }

    public MutableLiveData<PromotionResource> getPromotionByCode(String code){
        if(promotion == null){
            promotion = new MutableLiveData<>();
        }
        Call<PromotionResource> call = promotionService.getPromotionByCode(code);
        call.enqueue(new Callback<PromotionResource>() {
            @Override
            public void onResponse(Call<PromotionResource> call, Response<PromotionResource> response) {
                if (response.isSuccessful()){
                    promotion.setValue(response.body());
                }else {
                    Log.e("error seat repository", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<PromotionResource> call, Throwable t) {
                Log.e("error", t.getMessage());
                getPromotionByCode(code);
            }
        });
        return promotion;
    }
}
