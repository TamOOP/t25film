
package com.huce.t25film.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cinema {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("amount_of_seat")
    @Expose
    private Integer amountOfSeat;
    @SerializedName("seat_per_row")
    @Expose
    private Integer seatPerRow;
    @SerializedName("deleted")
    @Expose
    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
