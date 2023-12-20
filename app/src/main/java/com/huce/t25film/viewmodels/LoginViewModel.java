package com.huce.t25film.viewmodels;

import android.content.Context;
import android.util.Patterns;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.model.User;
import com.huce.t25film.repository.LoginRepository;
import com.huce.t25film.repository.NetWorkConnection;

import java.util.List;

public class LoginViewModel extends ViewModel {
    private User user = new User();
    private MutableLiveData<Integer> load;
    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> isLogin;
    public LoginViewModel() {}

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LiveData<Boolean> getIsLogin(){
        if(isLogin == null){
            isLogin = new MutableLiveData<>(Boolean.FALSE);
        }
        return isLogin;
    }

    public LiveData<String> getMessage(){
        if(message == null){
            message = new MutableLiveData<>();
        }
        return message;
    }

    public LiveData<Integer> getLoad(){
        if(load == null){
            load = new MutableLiveData<>(View.GONE);
        }
        return load;
    }

    public LiveData<List<User>> getAllUsers(Context context){
        if(!NetWorkConnection.isNetworkAvailable(context)) {
            message.setValue("Không có kết nối mạng, vui lòng thử lại");
            load.setValue(View.GONE);
            return new MutableLiveData<>();
        }
        return LoginRepository.getInstance().getAllUsers();
    }

    public void checkLoginInfo(List<User> users){
        if(user.getPassword() == null && user.getEmail() == null) return;
        // kiem tra thong tin dang nhap
        for(User user: users){
            if(user.getEmail().equals(this.user.getEmail()) &&
                    user.getPassword().equals(this.user.getPassword())){
                this.user = user;
                isLogin.setValue(Boolean.TRUE);
                load.setValue(View.GONE);
                return;
            }
        }
        message.setValue("Tài khoản hoặc mật khẩu không đúng");
        // hide progress bar
    }

    public void onLoginClicked(Context context, String email, String password){
        if(email == null || password == null)
            message.setValue("Hãy nhập đủ tài khoản và mật khẩu");
        else if(!isEmailValid(email))
            message.setValue("Email không đúng định dạng");
        else{
            user.setPassword(password);
            user.setEmail(email);
            // hien thi progress bar
            load.setValue(View.VISIBLE);
            getAllUsers(context);
        }
    }

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
