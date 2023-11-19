package com.huce.t25film.repository;

import android.util.Patterns;

public class LoginRepository {
    private String email;
    private String password;
    private LoginRepository loginRepository;

    public void getInstance(){
        if(loginRepository == null){
            loginRepository = new LoginRepository();
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }
}
