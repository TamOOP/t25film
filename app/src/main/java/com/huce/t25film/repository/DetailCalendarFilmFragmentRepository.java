package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.model.Film;
import com.huce.t25film.resources.ShowResource;

import java.util.List;

import retrofit2.Retrofit;

public class DetailCalendarFilmFragmentRepository {
    private MutableLiveData<ShowResource> showLiveData = new MutableLiveData<>();
    private static DetailCalendarFilmFragmentRepository instance;
    private Retrofit retrofit;
    private ShowService showService;

    // instance
    public static synchronized DetailCalendarFilmFragmentRepository getInstance(){
        if(instance == null){
            instance = new DetailCalendarFilmFragmentRepository();
        }
        return instance;
    }

    // build retrofit va tao user api khi instance
    public DetailCalendarFilmFragmentRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        showService = retrofit.create(ShowService.class);
    }
}
