
package com.huce.t25film.model;

import com.google.gson.annotations.SerializedName;

public class Seat {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("isSelected")
    private int isSelected;
    @SerializedName("isBooked")
    private int isBooked;

    private boolean isChoosed = Boolean.FALSE;

    public boolean getIsChoosed() {
        return isChoosed;
    }

    public void setIsChoosed(boolean isChoosed) {
        this.isChoosed = isChoosed;
    }

    public Seat(int id, String name, int isSelected, int isBooked) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
        this.isBooked = isBooked;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public int getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(int isBooked) {
        this.isBooked = isBooked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
