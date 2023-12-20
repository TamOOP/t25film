package com.huce.t25film.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.model.Seat;
import com.huce.t25film.repository.BookingRepository;
import com.huce.t25film.resources.CinemaResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingViewModel extends ViewModel {
    private LiveData<CinemaResource> cinemaResource = new MutableLiveData<>();
    private MutableLiveData<Map<String, Seat>> seatSelectStatus = new MutableLiveData<>();
    public LiveData<CinemaResource> getCinemaInfo(@NonNull int showId){
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
        return String.join(", ", nameList);
    }

    public int getTotalPrice(){
        int total = 0;
        for (Seat seat:  cinemaResource.getValue().getCinema().getSeats()){
            if(!seat.getIsChoosed()) continue;
            total += 50000;
        }
        return total;
    }
}

