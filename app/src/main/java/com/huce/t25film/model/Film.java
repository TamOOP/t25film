package com.huce.t25film.model;

import java.util.Date;

public class Film {
    private String status;
    private String idPhim;
    private String tenphim;
    private Date congchieu;
    private String kiemduyet;
    private String theloai;
    private String daodien;
    private String dienvien;
    private String ngonngu;
    private String anh;

    public Film(String status, String idPhim, String tenphim, Date congchieu, String kiemduyet, String theloai, String daodien, String dienvien, String ngonngu, String anh) {
        this.status = status;
        this.idPhim = idPhim;
        this.tenphim = tenphim;
        this.congchieu = congchieu;
        this.kiemduyet = kiemduyet;
        this.theloai = theloai;
        this.daodien = daodien;
        this.dienvien = dienvien;
        this.ngonngu = ngonngu;
        this.anh = anh;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getCongchieu() {
        return congchieu;
    }

    public void setCongchieu(Date congchieu) {
        this.congchieu = congchieu;
    }

    public String getKiemduyet() {
        return kiemduyet;
    }

    public void setKiemduyet(String kiemduyet) {
        this.kiemduyet = kiemduyet;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getDaodien() {
        return daodien;
    }

    public void setDaodien(String daodien) {
        this.daodien = daodien;
    }

    public String getDienvien() {
        return dienvien;
    }

    public void setDienvien(String dienvien) {
        this.dienvien = dienvien;
    }

    public String getNgonngu() {
        return ngonngu;
    }

    public void setNgonngu(String ngonngu) {
        this.ngonngu = ngonngu;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
