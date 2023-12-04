package com.huce.t25film.api;

import com.huce.t25film.model.User;
import com.huce.t25film.resources.UserResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    // all user
    @GET("users")
    Call<List<User>> getUsers();

    // get user theo id
    @GET("users/{id}")
    Call<UserResource> getUser(@Path("id") int id);

    // them moi user
    @POST("users")
    Call<User> createUser(@Body User user);

    // update user password
    @POST("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);
}
