package com.huce.t25film.viewmodels;

import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.huce.t25film.BR;
import com.huce.t25film.model.User;
import com.huce.t25film.repository.LoginRepository;

import java.util.List;

public class LoginViewModel extends BaseObservable {
    private User user = new User();
    private int load = 8;
    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> isLogin = new MutableLiveData<>();

    public LoginViewModel() {}

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
    public String getUserEmail() {
        return user.getEmail();
    }

    public void setUserEmail(@NonNull String email) {
        user.setEmail(email);
        notifyPropertyChanged(BR.userEmail);
    }

    @Bindable
    @NonNull
    public String getUserPassword() {
        return user.getPassword();
    }

    public void setUserPassword(@NonNull String password) {
        user.setPassword(password);
        notifyPropertyChanged(BR.userPassword);
    }

    public LiveData<List<User>> getAllUsers(){
        return LoginRepository.getInstance().getAllUsers();
    }

    public void checkLoginInfo(List<User> users){
        if(user.getPassword() == null && user.getEmail() == null) return;
        // kiem tra thong tin dang nhap
        for(User user: users){
            if(user.getEmail().equals(this.user.getEmail()) &&
                    user.getPassword().equals(this.user.getPassword())){
                setUser(user);
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
        if(user.getEmail() == null || user.getPassword() == null)
            message.setValue("Hãy nhập đủ thông tin");
        else if(!isEmailValid(user.getEmail()))
            message.setValue("Email không đúng định dạng");
        else{
            // hien thi progress bar
            setLoad(0);
            getAllUsers();
        }
    }

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
