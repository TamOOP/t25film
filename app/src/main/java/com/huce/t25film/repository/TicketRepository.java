package com.huce.t25film.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.TicketService;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.model.Ticket;
import com.huce.t25film.model.TicketPost;
import com.huce.t25film.resources.TicketResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketRepository {
    private MutableLiveData<List<Ticket>> ticketLiveData = new MutableLiveData<>();
    private MutableLiveData<TicketResource> ticketResource;
    private static TicketRepository instance;
    private Retrofit retrofit;
    private TicketService ticketService;

    // instance
    public static synchronized TicketRepository getInstance(){
        if(instance == null){
            instance = new TicketRepository();
        }
        return instance;
    }

    public TicketRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        ticketService = retrofit.create(TicketService.class);
    }
    public MutableLiveData<TicketResource> createTicket(TicketPost ticketPost){
        Call<TicketResource> call = ticketService.createTicket(ticketPost);
        call.enqueue(new Callback<TicketResource>() {
            @Override
            public void onResponse(Call<TicketResource> call, Response<TicketResource> response) {
                if (response.isSuccessful()){
                    ticketResource.setValue(response.body());
                }else {
                    Log.e("error seat repository", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<TicketResource> call, Throwable t) {
                Log.e("error", t.getMessage());
                createTicket(ticketPost);
            }
        });
        return ticketResource;
    }
}
