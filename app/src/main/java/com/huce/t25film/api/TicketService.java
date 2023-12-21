package com.huce.t25film.api;

import com.huce.t25film.model.TicketPost;
import com.huce.t25film.resources.TicketResource;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TicketService {
    @POST("tickets")
    Call<TicketResource> createTicket(@Body TicketPost ticketPost);
}
