

package com.huce.t25film.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Ticket__1;

public class TicketResource {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ticket__1 getTicket() {
        return ticket;
    }

    public void setTicket(Ticket__1 ticket) {
        this.ticket = ticket;
    }

    @SerializedName("ticket")
    @Expose
    private Ticket__1 ticket;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
