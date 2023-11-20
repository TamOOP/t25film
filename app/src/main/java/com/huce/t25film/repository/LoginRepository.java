package com.huce.t25film.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserApi;
import com.huce.t25film.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginRepository {
    public class LoginData{
        private String email;
        private String password;

        public LoginData(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    private MutableLiveData<List<LoginData>> loginDatas = new MutableLiveData<>();
    private static LoginRepository instance;
    private Retrofit retrofit;
    private UserApi userApi;

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
        userApi = retrofit.create(UserApi.class);
    }

    // fetch api va xu ly
    public MutableLiveData<List<LoginData>> getLoginDatas(){
        List<LoginData> _loginDataList = new ArrayList<>();
        Call<List<User>> _usersCall = userApi.getUsers();

        // call async api
        _usersCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                // parse json sang POJO object
                List<User> users = response.body();
                // filter data, lay email va password
                for (User user: users){
                    LoginData loginData = new LoginData(user.getEmail(), user.getPassword());
                    _loginDataList.add(loginData);
                }
                // thay doi du lieu cua live data
                loginDatas.setValue(_loginDataList);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });

        return loginDatas;
    }
}


