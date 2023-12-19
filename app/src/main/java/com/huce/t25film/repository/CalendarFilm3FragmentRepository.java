package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.resources.ShowResource;

import retrofit2.Retrofit;

public class CalendarFilm3FragmentRepository {
    private MutableLiveData<ShowResource> showLiveData = new MutableLiveData<>();
    private static CalendarFilm3FragmentRepository instance;
    private Retrofit retrofit;
    private ShowService showService;

    // instance
    public static synchronized CalendarFilm3FragmentRepository getInstance(){
        if(instance == null){
            instance = new CalendarFilm3FragmentRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public CalendarFilm3FragmentRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        showService = retrofit.create(ShowService.class);
    }
}
