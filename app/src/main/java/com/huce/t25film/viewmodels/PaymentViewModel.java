package com.huce.t25film.viewmodels;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.model.TicketPost;
import com.huce.t25film.repository.PromotionRepository;
import com.huce.t25film.repository.SeatRepository;
import com.huce.t25film.repository.TicketRepository;
import com.huce.t25film.resources.PromotionResource;
import com.huce.t25film.resources.TicketResource;

public class PaymentViewModel extends ViewModel {
    private MutableLiveData<String> message;
    private MutableLiveData<Integer> load;
    private MutableLiveData<PromotionResource> promotion;
    private MutableLiveData<TicketResource> ticket;

    public LiveData<PromotionResource> getPromotion(String code) {
        if (promotion == null){
            promotion = new MutableLiveData<>();
        }
        if (code.isEmpty()) return promotion;
        load.setValue(View.VISIBLE);
        promotion = PromotionRepository.getInstance().getPromotionByCode(code);
        return promotion;
    }

    public MutableLiveData<Integer> getLoad() {
        if (load == null){
            load = new MutableLiveData<>(View.GONE);
        }
        return load;
    }

    public MutableLiveData<String> getMessage() {
        if (message == null){
            message = new MutableLiveData<>();
        }
        return message;
    }

    public void clearSeatHold(int[] seatIds, int showId){
        if (seatIds == null || showId == 0) return;
        for (int id: seatIds){
            SeatRepository.getInstance().updateSeat(id, 0, showId);
        }
    }

    public int getDiscountPrice(String code){
        if (code.isEmpty()){
            message.setValue("Bạn chưa nhập mã");
            return 0;
        }
        load.setValue(View.VISIBLE);

        if (promotion.getValue() == null) {
            load.setValue(View.GONE);
            return 0;
        }
        if (promotion.getValue().getStatus().equals("success")){
            load.setValue(View.GONE);
            return promotion.getValue().getPromotion().getDiscount();
        }else{
            load.setValue(View.GONE);
            message.setValue(promotion.getValue().getMessage());
            return 0;
        }
    }

    public LiveData<TicketResource> createTicket(int idtk, String idghe, int idshow, int cost){
        if (ticket == null){
            ticket = new MutableLiveData<>();
        }
        TicketPost ticketPost = new TicketPost(idtk, idghe, idshow, cost);
        ticket = TicketRepository.getInstance().createTicket(ticketPost);
        load.setValue(View.VISIBLE);
        return ticket;
    }
}
