package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.repository.DetailKmRepository;
import com.huce.t25film.resources.PromotionResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DetailKmViewModel extends ViewModel {
    private MutableLiveData<PromotionResource> kmLiveData = new MutableLiveData<>();
    private DetailKmRepository kmRepository; // Tùy chọn: nơi bạn thực hiện gọi API
    private RecyclerView.Adapter adapterKm;

    private int id;

    private PromotionService kmService;


    public DetailKmViewModel(int id) {
        kmRepository = new DetailKmRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        fetchkms(id);
    }

    public LiveData<PromotionResource> getKmLiveData() {
        return kmLiveData;
    }

    private void fetchkms(int id) {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        PromotionService kmService = retrofit.create(PromotionService.class);
        // Gọi API
        Call<PromotionResource> call = kmService.getPromotionsId(id);
        call.enqueue(new Callback<PromotionResource>() {

            @Override
            public void onResponse(Call<PromotionResource> call, retrofit2.Response<PromotionResource> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    PromotionResource items = response.body();
                    kmLiveData.postValue(items);


                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<PromotionResource> call, Throwable t) {
                Log.e("Error", t.getMessage());
                fetchkms(id);
            }

        });
    }
}
