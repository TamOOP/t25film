package com.huce.t25film.model;

import com.google.gson.annotations.SerializedName;

public class TicketPost {
    @SerializedName("idtk")
    private int idtk;
    @SerializedName("idghe")
    private String idghe;
    @SerializedName("idshow")
    private int idshow;
    @SerializedName("cost")
    private int cost;

    public TicketPost(int idtk, String idghe, int idshow, int cost) {
        this.idtk = idtk;
        this.idghe = idghe;
        this.idshow = idshow;
        this.cost = cost;
    }

    public TicketPost() {
    }

    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
    }

    public String getIdghe() {
        return idghe;
    }

    public void setIdghe(String idghe) {
        this.idghe = idghe;
    }

    public int getIdshow() {
        return idshow;
    }

    public void setIdshow(int idshow) {
        this.idshow = idshow;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
