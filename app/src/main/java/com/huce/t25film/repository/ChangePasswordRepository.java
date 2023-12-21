package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.resources.UserResource;

import retrofit2.Retrofit;

public class ChangePasswordRepository {
    private MutableLiveData<UserResource> kmLiveData = new MutableLiveData<>();
    private static ChangePasswordRepository instance;
    private Retrofit retrofit;
    private UserService userService;

    // instance
    public static synchronized ChangePasswordRepository getInstance(){
        if(instance == null){
            instance = new ChangePasswordRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public ChangePasswordRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        userService = retrofit.create(UserService.class);
    }
}
