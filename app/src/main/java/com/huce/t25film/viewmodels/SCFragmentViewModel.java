package com.huce.t25film.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.model.Film;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.repository.SCFragmentRepository;

import java.util.List;

public class SCFragmentViewModel extends BaseObservable {
    private MutableLiveData<List<Film>> filmListLiveData = new MutableLiveData<>();
    private SCFragmentRepository filmRepository; // Tùy chọn: nơi bạn thực hiện gọi API

    public SCFragmentViewModel() {
        filmRepository = new SCFragmentRepository(); // Khởi tạo repository
        loadFilmList(); // Gọi API khi ViewModel được tạo
    }

    public LiveData<List<Film>> getFilmListLiveData() {
        return filmListLiveData;
    }

    private void loadFilmList() {
        // Thực hiện gọi API và cập nhật LiveData khi có dữ liệu mới
        filmRepository.getAllFilms(new SCFragmentRepository.OnFilmListCallback() {
            @Override
            public void onSuccess(List<Film> filmList) {
                filmListLiveData.postValue(filmList);
            }

            @Override
            public void onError(String errorMessage) {
                // Xử lý khi gọi API thất bại
            }
        });
    }
}
