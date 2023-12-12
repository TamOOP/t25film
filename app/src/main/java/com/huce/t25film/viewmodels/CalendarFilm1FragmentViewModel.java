package com.huce.t25film.viewmodels;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.model.Film;
import com.huce.t25film.resources.ShowResource;
import com.huce.t25film.repository.CalendarFilm1FragmentRepository;
import com.huce.t25film.repository.DCFragmentRepository;

import java.util.List;

public class CalendarFilm1FragmentViewModel extends BaseObservable {
    private MutableLiveData<ShowResource> showLiveData = new MutableLiveData<>();
    private CalendarFilm1FragmentRepository showRepository; // Tùy chọn: nơi bạn thực hiện gọi API

    public CalendarFilm1FragmentViewModel() {
        showRepository = new CalendarFilm1FragmentRepository(); // Khởi tạo repository
        loadShow("2023-12-10"); // Gọi API khi ViewModel được tạo
    }

    public LiveData<ShowResource> getShowLiveData() {
        return showLiveData;
    }

    private void loadShow(String findbyDate) {
        // Thực hiện gọi API và cập nhật LiveData khi có dữ liệu mới
        showRepository.getShow(findbyDate,new CalendarFilm1FragmentRepository.OnShowCallback() {
            @Override
            public void onSuccess(ShowResource ShowResource) {
                showLiveData.postValue(ShowResource);

            }

            @Override
            public void onError(String errorMessage) {
                // Xử lý khi gọi API thất bại
            }
        }
        );
    }
}
