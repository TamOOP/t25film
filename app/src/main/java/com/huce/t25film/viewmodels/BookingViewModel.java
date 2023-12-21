package com.huce.t25film.viewmodels;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.model.Seat;
import com.huce.t25film.repository.BookingRepository;
import com.huce.t25film.repository.NetWorkConnection;
import com.huce.t25film.resources.CinemaResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingViewModel extends ViewModel {
    private MutableLiveData<CinemaResource> cinemaResource = new MutableLiveData<>();
    private MutableLiveData<Map<String, Seat>> seatSelectStatus = new MutableLiveData<>();

    private MutableLiveData<Integer> load;
    private MutableLiveData<String> message;

    public MutableLiveData<String> getMessage() {
        if (message == null){
            message = new MutableLiveData<>();
        }
        return message;
    }
    public MutableLiveData<Integer> getLoad() {
        if (load == null){
            load = new MutableLiveData<>(View.GONE);
        }
        return load;
    }

    public LiveData<CinemaResource> getCinemaInfo(Context context, @NonNull int showId){
        load.setValue(View.VISIBLE);
        if(!NetWorkConnection.isNetworkAvailable(context)) {
            message.setValue("Không có kết nối mạng, vui lòng thử lại");
            load.setValue(View.GONE);
            return new MutableLiveData<>();
        }
        cinemaResource = BookingRepository.getInstance().getCinema(showId);
        return cinemaResource;
    }


    public LiveData<Map<String, Seat>> getSeatSelected(){
        return seatSelectStatus;
    }
    public void toggleSeatChooseState(String name){
        for(Seat seat:  cinemaResource.getValue().getCinema().getSeats()){
            if(!seat.getName().equals(name)) continue;
            if(seat.getIsBooked() == 1 || seat.getIsSelected() == 1) break;

            Map<String, Seat> map = seatSelectStatus.getValue() == null ? new HashMap<>() : seatSelectStatus.getValue();
            seat.setIsChoosed(!seat.getIsChoosed());
            map.put(name,seat);

            seatSelectStatus.setValue(map);
            break;
        }
    }

    public String getSeatNameSelected(){
        List<String> nameList = new ArrayList<>();
        for (Seat seat:  cinemaResource.getValue().getCinema().getSeats()){
            if(!seat.getIsChoosed()) continue;
            nameList.add(seat.getName());
        }
        return String.join(",", nameList);
    }

    public int getTotalPrice(){
        int total = 0;
        for (Seat seat:  cinemaResource.getValue().getCinema().getSeats()){
            if(!seat.getIsChoosed()) continue;
            total += 50000;
        }
        return total;
    }

    public void clearData(){
        cinemaResource.setValue(null);
        seatSelectStatus.setValue(null);
    }
}

