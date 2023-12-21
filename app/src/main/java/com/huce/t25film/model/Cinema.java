
package com.huce.t25film.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cinema {

    @SerializedName("idphong")
    @Expose
    private Integer idphong;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount_of_seat")
    @Expose
    private Integer amountOfSeat;
    @SerializedName("seat_per_row")
    @Expose
    private Integer seatPerRow;

    @SerializedName("seats")
    private List<Seat> seats;

    public Cinema(Integer idphong, String name, Integer amountOfSeat, Integer seatPerRow, List<Seat> seats) {
        this.idphong = idphong;
        this.name = name;
        this.amountOfSeat = amountOfSeat;
        this.seatPerRow = seatPerRow;
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Integer getIdphong() {
        return idphong;
    }

    public void setIdphong(Integer idphong) {
        this.idphong = idphong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountOfSeat() {
        return amountOfSeat;
    }

    public void setAmountOfSeat(Integer amountOfSeat) {
        this.amountOfSeat = amountOfSeat;
    }

    public Integer getSeatPerRow() {
        return seatPerRow;
    }

    public void setSeatPerRow(Integer seatPerRow) {
        this.seatPerRow = seatPerRow;
    }

}
