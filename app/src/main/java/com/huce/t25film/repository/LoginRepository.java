package com.huce.t25film.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepository {

    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();
    private static LoginRepository instance;
    private Retrofit retrofit;
    private UserService userService;

    // instance
    public static synchronized LoginRepository getInstance(){
        if(instance == null){
            instance = new LoginRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    private LoginRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        userService = retrofit.create(UserService.class);
    }

    // fetch api va xu ly
    public MutableLiveData<List<User>> getAllUsers(){
        Call<List<User>> _usersCall = userService.getUsers();

        // call async api
        _usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                // parse json sang POJO object
                List<User> users = response.body();

                // thay doi du lieu cua live data
                usersLiveData.setValue(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("error", t.getMessage());
                getAllUsers();
            }
        });

        return usersLiveData;
    }
}


