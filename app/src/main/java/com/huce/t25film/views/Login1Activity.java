package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.huce.t25film.R;
import com.huce.t25film.databinding.ActivityLogin1Binding;
import com.huce.t25film.viewmodels.LoginViewModel;

public class Login1Activity extends AppCompatActivity {
    private Button btnRegister,btnLogin;
    private EditText emailEdit,passEdit;
    private ActivityLogin1Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hien thi view
        binding = ActivityLogin1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        initView();

        LoginViewModel loginViewModel = new LoginViewModel();
        // xac dinh viewmodel cho view
        binding.setViewModel(loginViewModel);

        // quan sat du lieu api gui ve
        loginViewModel.getAllUsers().observe(this, users
                -> loginViewModel.checkLoginInfo(users));

        // quan sat livedata message
        loginViewModel.getMessage().observe(this, message
                -> Toast.makeText(Login1Activity.this, message, Toast.LENGTH_SHORT).show());

        //quan sat livedata isLogin
        loginViewModel.getIsLogin().observe(this, isLogin -> {
            if(isLogin){
                // chuyen view
                Intent loginIntent = new Intent(Login1Activity.this, HomeActivity.class);
                // send uid
                loginIntent.putExtra("uid", loginViewModel.getUser().getId());
                startActivity(loginIntent);
                //destroy activity
                finish();
            }
        });

    }

    @Override
    // for draw visual element, running animation
    protected void onStart() {
        super.onStart();
        Log.e("state","start");
    }

    @Override
    // for stop refreshing UI, running animations and other visual things.
    protected void onStop() {
        super.onStop();
        Log.e("state","stop");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("state","resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("state","pause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("state","restart");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("state","saveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("state","destroy");
    }


}