package com.huce.t25film.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.resources.UserResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeRepository {
    private UserService userService;
    private static HomeRepository instance;

    private MutableLiveData<UserResource> user = new MutableLiveData<>();

    public static HomeRepository getInstance(){
        if(instance == null)
            instance = new HomeRepository();
        return instance;
    }

    private HomeRepository(){
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        userService = retrofit.create(UserService.class);
    }

    public MutableLiveData<UserResource> getUser(int id) {
        Call<UserResource> userCall = userService.getUser(id);
        userCall.enqueue(new Callback<UserResource>() {
            @Override
            public void onResponse(Call<UserResource> call, Response<UserResource> response) {
                UserResource resource = response.body();
                user.setValue(resource);
            }

            @Override
            public void onFailure(Call<UserResource> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });
        return user;
    }
}
