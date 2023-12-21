package com.huce.t25film.resources;

import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Ticket;

public class TicketResource {
    @SerializedName("status")
    private String status;
    @SerializedName("ticket")
    private Ticket ticket;
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
