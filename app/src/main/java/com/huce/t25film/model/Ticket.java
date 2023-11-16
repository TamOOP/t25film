package com.huce.t25film.model;

import java.sql.Time;
import java.util.Date;

public class Ticket {
    private String status;
    private String idVe;
    private String idPhim;
    private String tenphim;
    private String ghe;
    private Float gia;
    private Date ngay;
    private Time gio;
    private String phude;

    public Ticket(String status, String idVe, String idPhim, String tenphim, String ghe, Float gia, Date ngay, Time gio, String phude) {
        this.status = status;
        this.idVe = idVe;
        this.idPhim = idPhim;
        this.tenphim = tenphim;
        this.ghe = ghe;
        this.gia = gia;
        this.ngay = ngay;
        this.gio = gio;
        this.phude = phude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdVe() {
        return idVe;
    }

    public void setIdVe(String idVe) {
        this.idVe = idVe;
    }

    public String getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(String idPhim) {
        this.idPhim = idPhim;
    }

    public String getTenphim() {
        return tenphim;
    }

    public void setTenphim(String tenphim) {
        this.tenphim = tenphim;
    }

    public String getGhe() {
        return ghe;
    }

    public void setGhe(String ghe) {
        this.ghe = ghe;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Time getGio() {
        return gio;
    }

    public void setGio(Time gio) {
        this.gio = gio;
    }

    public String getPhude() {
        return phude;
    }

    public void setPhude(String phude) {
        this.phude = phude;
    }
}
