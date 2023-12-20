package com.huce.t25film.api;

import com.huce.t25film.model.Promotion;
import com.huce.t25film.model.Ticket;
import com.huce.t25film.resources.PromotionResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TicketService {
    @GET("tickets")
    Call<List<Ticket>> getListTickets();

    // get user theo id
    @GET("tickets/{id}")
    Call<Ticket> getTicketsId(@Path("id") int id);
}
