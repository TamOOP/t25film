package com.huce.t25film.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Seat implements Parcelable {
    private String seatNumber;
    private boolean isBooked;
    private boolean isSelected;

    public Seat(String seatNumber, boolean isBooked) {
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    protected Seat(Parcel in) {
        seatNumber = in.readString();
        isBooked = in.readByte() != 0;
    }

    public static final Creator<Seat> CREATOR = new Creator<Seat>() {
        @Override
        public Seat createFromParcel(Parcel in) {
            return new Seat(in);
        }

        @Override
        public Seat[] newArray(int size) {
            return new Seat[size];
        }
    };

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(seatNumber);
        dest.writeByte((byte) (isBooked ? 1 : 0));
    }
}


