package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.resources.UserResource;

import retrofit2.Retrofit;

public class AccountRepository {
    private MutableLiveData<UserResource> kmLiveData = new MutableLiveData<>();
    private static AccountRepository instance;
    private Retrofit retrofit;
    private UserService userService;

    // instance
    public static synchronized AccountRepository getInstance(){
        if(instance == null){
            instance = new AccountRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public AccountRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        userService = retrofit.create(UserService.class);
    }
}
