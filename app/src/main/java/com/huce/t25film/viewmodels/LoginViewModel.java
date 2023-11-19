package com.huce.t25film.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.huce.t25film.BR;

public class LoginViewModel extends BaseObservable {
    private String  email;
    private String password;

    @Bindable
    @NonNull
    public String getEmail() {
        return this.email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    @NonNull
    public String getPassword() {
        return this.password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void onLoginClicked(){
        Log.e("anno","clicked");
    }
}
