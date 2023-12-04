package com.huce.t25film.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.model.User;
import com.huce.t25film.repository.HomeRepository;
import com.huce.t25film.resources.UserResource;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> username = new MutableLiveData<>();

    public LiveData<UserResource> getUser(int id){
        return HomeRepository.getInstance().getUser(id);
    }

    public void setUser(User user) {
        username.setValue(user.getName());
    }

    public LiveData<String> getUsername() {
        return username;
    }
}
