package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.TicketService;
import com.huce.t25film.repository.DetailKmRepository;
import com.huce.t25film.repository.DetailTicketRepository;
import com.huce.t25film.repository.TicketRepository;
import com.huce.t25film.resources.PromotionResource;
import com.huce.t25film.resources.TicketResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DetailTicketViewModel extends ViewModel {
    private MutableLiveData<TicketResource> ticketLiveData = new MutableLiveData<>();
    private DetailTicketRepository ticketRepository; // Tùy chọn: nơi bạn thực hiện gọi API
    private RecyclerView.Adapter adapterticket;

    private int id,uid;

    private PromotionService kmService;


    public DetailTicketViewModel(int id,int uid) {
        ticketRepository = new DetailTicketRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        fetchkms(id,uid);
    }

    public LiveData<TicketResource> getticketLiveData() {
        return ticketLiveData;
    }

    private void fetchkms(int id,int uid) {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        TicketService ticketService = retrofit.create(TicketService.class);
        // Gọi API
        Call<TicketResource> call = ticketService.getTicket(id,uid);
        call.enqueue(new Callback<TicketResource>() {

            @Override
            public void onResponse(Call<TicketResource> call, retrofit2.Response<TicketResource> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    TicketResource items = response.body();
                    ticketLiveData.postValue(items);


                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<TicketResource> call, Throwable t) {

            }

        });
    }
}
