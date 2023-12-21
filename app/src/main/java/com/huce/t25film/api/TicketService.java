package com.huce.t25film.api;

import com.huce.t25film.model.Promotion;
import com.huce.t25film.model.Ticket;
import com.huce.t25film.resources.PromotionResource;
import com.huce.t25film.resources.TicketResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TicketService {
    @GET("tickets")
    Call<List<Ticket>> getListTickets(@Query("userId") int userId);

    @GET("tickets/{ticketId}")
    Call<TicketResource> getTicket(@Path("ticketId") int ticketId, @Query("userId") int userId);
}
