package com.huce.t25film.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.repository.DetailFilmRepository;
import com.huce.t25film.resources.FilmResource;

public class DetailViewModel extends BaseObservable {
    private MutableLiveData<FilmResource> filmLiveData = new MutableLiveData<>();
    private DetailFilmRepository filmRepository; // Tùy chọn: nơi bạn thực hiện gọi API

    public DetailViewModel() {
        filmRepository = new DetailFilmRepository(); // Khởi tạo repository
        //loadFilm(1); // Gọi API khi ViewModel được tạo
    }

    public LiveData<FilmResource> getFilmLiveData() {
        return filmLiveData;
    }

    public void loadFilm(Integer filmId) {
        // Thực hiện gọi API và cập nhật LiveData khi có dữ liệu mới
        filmRepository.getFilm(filmId,new DetailFilmRepository.OnFilmCallback() {
            @Override
            public void onSuccess(FilmResource film) {
                filmLiveData.postValue(film);
            }

            @Override
            public void onError(String errorMessage) {
                // Xử lý khi gọi API thất bại

            }
        }
        );
    }
}
