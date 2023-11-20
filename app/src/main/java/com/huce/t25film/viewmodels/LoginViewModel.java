package com.huce.t25film.viewmodels;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.BR;
import com.huce.t25film.repository.LoginRepository;

import java.util.List;

public class LoginViewModel extends BaseObservable {
    private String  email;
    private String password;
    private int load = 8;
    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> isLogin = new MutableLiveData<>();

    public MutableLiveData<Boolean> getIsLogin(){
        return isLogin;
    }

    public MutableLiveData<String> getMessage(){
        if(message == null){
            message = new MutableLiveData<>();
        }
        return message;
    }

    @Bindable
    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
        notifyPropertyChanged(BR.load);
    }

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

    public LiveData<List<LoginRepository.LoginData>> getAllUser(){
        return LoginRepository.getInstance().getLoginDatas();
    }

    public void checkLoginInfo(List<LoginRepository.LoginData> loginDatas){
        if(email == null && password == null) return;
        // kiem tra thong tin dang nhap
        for(LoginRepository.LoginData loginData: loginDatas){
            if(email.equals(loginData.getEmail()) && password.equals(loginData.getPassword())){
                isLogin.setValue(Boolean.TRUE);
                setLoad(8);
                return;
            }
        }
        message.setValue("Tài khoản hoặc mật khẩu không đúng");
        // hide progress bar
        setLoad(8);
    }

    public void onLoginClicked(){
        if(email == null || password == null)
            message.setValue("Hãy nhập đủ thông tin");
        else if(!isEmailValid(email))
            message.setValue("Email không đúng định dạng");
        else{
            // hien thi progress bar
            setLoad(0);
            getAllUser();
        }
    }

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
