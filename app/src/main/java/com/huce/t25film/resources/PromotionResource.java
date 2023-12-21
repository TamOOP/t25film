
package com.huce.t25film.resources;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.huce.t25film.model.Promotion__1;

public class PromotionResource {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("promotion")
    @Expose
    private Promotion__1 promotion;
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Promotion__1 getPromotion() {
        return promotion;
    }


    public void setPromotion(Promotion__1 promotion) {
        this.promotion = promotion;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
