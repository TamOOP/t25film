package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;

    public MutableLiveData<String> getEmail() {
        //instance live data
        if (email == null){
            email = new MutableLiveData<>();
        }
        return email;
    }

    public MutableLiveData<String> getPassword() {
        //instance live data
        if (password == null){
            password = new MutableLiveData<>();
        }
        return password;
    }

    public void onClicked(){
//        Log.e("email",email.getValue());
        Log.e("email","click");
    }
}
