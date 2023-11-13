package com.huce.t25film.model;

import java.sql.Time;
import java.util.Date;

public class Promotion {
    private String status;
    private String idVe;
    private String tieude;
    private String noidung;
    private Date ngay;
    private Time gio;
    private Float giagiam;

    public Promotion(String status, String idVe, String tieude, String noidung, Date ngay, Time gio, Float giagiam) {
        this.status = status;
        this.idVe = idVe;
        this.tieude = tieude;
        this.noidung = noidung;
        this.ngay = ngay;
        this.gio = gio;
        this.giagiam = giagiam;
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

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
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

    public Float getGiagiam() {
        return giagiam;
    }

    public void setGiagiam(Float giagiam) {
        this.giagiam = giagiam;
    }
}
