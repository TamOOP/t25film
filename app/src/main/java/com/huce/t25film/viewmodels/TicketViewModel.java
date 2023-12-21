package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.PromotionListAdapter;
import com.huce.t25film.Adapters.TicketListAdapter;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.TicketService;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.model.Ticket;
import com.huce.t25film.repository.KmFragmentRepository;
import com.huce.t25film.repository.TicketRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class TicketViewModel extends ViewModel {
    private MutableLiveData<List<Ticket>> TicketLiveData = new MutableLiveData<>();
    private TicketRepository ticketRepository; // Tùy chọn: nơi bạn thực hiện gọi API

    private RecyclerView.Adapter adapterticket;

    private TicketService ticketService;


    public TicketViewModel(int uid) {
        ticketRepository = new TicketRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        fetchticket(uid);
    }

    public LiveData<List<Ticket>> getticketLiveData() {
        return TicketLiveData;
    }

    private void fetchticket(int uid) {

        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        TicketService ticketService = retrofit.create(TicketService.class);

        // Gọi API
        Call<List<Ticket>> call = ticketService.getListTickets(uid);
        call.enqueue(new Callback<List<Ticket>>() {

            @Override
            public void onResponse(Call<List<Ticket>> call, retrofit2.Response<List<Ticket>> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    List<Ticket> items = response.body();
                    TicketLiveData.postValue(items);
                    // Tạo Adapter và thiết lập RecyclerView
                    adapterticket = new TicketListAdapter(items);
                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {

            }

        });
    }
}
