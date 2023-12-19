package com.huce.t25film.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.repository.HoursDetailFilmRepository;
import com.huce.t25film.resources.FilmResource;

public class HoursDetailFilmViewModel extends ViewModel {

    public LiveData<FilmResource> getFilmResource(int filmId){
        return HoursDetailFilmRepository.getInstance().getFilmResource(filmId);
    }


}
