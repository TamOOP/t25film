package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.TicketService;
import com.huce.t25film.resources.PromotionResource;
import com.huce.t25film.resources.TicketResource;

import retrofit2.Retrofit;

public class DetailTicketRepository {
    private MutableLiveData<TicketResource> ticketLiveData = new MutableLiveData<>();
    private static DetailTicketRepository instance;
    private Retrofit retrofit;
    private TicketService ticketService;

    // instance
    public static synchronized DetailTicketRepository getInstance(){
        if(instance == null){
            instance = new DetailTicketRepository();
        }
        return instance;
    }

    public DetailTicketRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        ticketService = retrofit.create(TicketService.class);
    }
}
