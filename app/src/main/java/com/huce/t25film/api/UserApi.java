package com.huce.t25film.api;

import com.huce.t25film.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserApi {
    @GET("api/users")
    Call<List<User>> getUsers();

    @GET("api/user/{id}")
    Call<User> getUser(@Path("user") int uid);

//    @POST("api/user/")
}
