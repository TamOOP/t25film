package com.huce.t25film.repository;

import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.api.PromotionService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.TicketService;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.model.Ticket;

import java.util.List;

import retrofit2.Retrofit;

public class TicketRepository {
    private MutableLiveData<List<Ticket>> ticketLiveData = new MutableLiveData<>();
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

    // build retrofit va tao user api khi instance
    public TicketRepository(){
        if(retrofit == null){
            retrofit = RetrofitBuilder.buildRetrofit();
        }
        ticketService = retrofit.create(TicketService.class);
    }
}
