package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.FilmListAdapter;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.model.Film;
import com.huce.t25film.repository.SCFragmentRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class SCFragmentViewModel extends ViewModel {
    private MutableLiveData<List<Film>> filmListLiveData = new MutableLiveData<>();
    private SCFragmentRepository filmRepository; // Tùy chọn: nơi bạn thực hiện gọi API
    private RecyclerView.Adapter adapterMovies;


    private FilmService filmService;


    public SCFragmentViewModel() {
        filmRepository = new SCFragmentRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        fetchFilms();
    }

    public LiveData<List<Film>> getFilmListLiveData() {
        return filmListLiveData;
    }

//    private void loadFilmList() {
//        // Thực hiện gọi API và cập nhật LiveData khi có dữ liệu mới
//        filmRepository.getAllFilms(new SCFragmentRepository.OnFilmListCallback() {
//            @Override
//            public void onSuccess(List<Film> filmList) {
//                filmListLiveData.postValue(filmList);
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                // Xử lý khi gọi API thất bại
//            }
//        });
//    }




//    public SCFragmentViewModel(@NonNull Application application){
//        super(application);
//        filmRepository = new SCFragmentRepository(application);
//    }
//
//    public LiveData<List<Film>> getAllFilmsSC(){
//        return filmRepository.getAllFilms(new SCFragmentRepository.OnFilmListCallback() {
//            @Override
//            public void onSuccess(List<Film> filmList) {
//                filmListLiveData.postValue(filmList);
//            }
//
//            @Override
//            public void onError(String errorMessage) {                 // Xử lý khi gọi API thất bại             }         }
//        );
//    }





//    public SCFragmentViewModel(){
//        filmService = RetrofitBuilder.getApiService().create(FilmService.class);
//        fetchFilms();
//    }
//
//    public LiveData<List<Film>> getFilms(){
//        return filmListLiveData;
//    }
    private void fetchFilms() {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        FilmService filmService = retrofit.create(FilmService.class);

        // Gọi API
        Call<List<Film>> call = filmService.getListFilmsSC();
        call.enqueue(new Callback<List<Film>>() {

            @Override
            public void onResponse(Call<List<Film>> call, retrofit2.Response<List<Film>> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    List<Film> items = response.body();
                    filmListLiveData.postValue(items);


                    // Tạo Adapter và thiết lập RecyclerView
                    adapterMovies = new FilmListAdapter(items);
                } else {

                    // Xử lý khi phản hồi không thành công
                    //int statusCode = response.code();
                    //String errorBody = response.errorBody().string();
                    Log.e("Error", "Status Code: ");
                    // ...
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Log.e("Error", t.getMessage());
                fetchFilms();
            }

        });
    }
}
