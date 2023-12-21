package com.huce.t25film.model;

import com.google.gson.annotations.SerializedName;

public class SeatStatus {
    @SerializedName("isSelected")
    private int isSelected;

    @SerializedName("showId")
    private int showId;

    public SeatStatus(int isSelected, int showId) {
        this.isSelected = isSelected;
        this.showId = showId;
    }

    public SeatStatus() {
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }
}
