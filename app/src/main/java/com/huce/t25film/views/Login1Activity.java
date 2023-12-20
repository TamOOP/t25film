package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.databinding.ActivityLogin1Binding;
import com.huce.t25film.model.UserDataHolder;
import com.huce.t25film.resources.UserResource;
import com.huce.t25film.viewmodels.LoginViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class Login1Activity extends AppCompatActivity {
    private ActivityLogin1Binding binding;
    private LoginViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hien thi view
        binding = ActivityLogin1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.txtEmail.getText().toString();
                String pass = binding.txtPassword.getText().toString();
                loginViewModel.onLoginClicked(email, pass);
            }
        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login1Activity.this, Register1Activity.class);
                startActivity(intent);
            }
        });
        // quan sat du lieu api gui ve
        loginViewModel.getAllUsers().observe(this, users
                -> loginViewModel.checkLoginInfo(users));

        // quan sat livedata message
        loginViewModel.getMessage().observe(this, message
                -> Toast.makeText(Login1Activity.this, message, Toast.LENGTH_SHORT).show());

        loginViewModel.getLoad().observe(this, load
                -> binding.progressBar.setVisibility(load));

        //quan sat livedata isLogin
        loginViewModel.getIsLogin().observe(this, isLogin -> {
            if(isLogin){
                // chuyen view
                Intent homeIntent = new Intent(Login1Activity.this, HomeActivity.class);
                // send uid
                homeIntent.putExtra("uid", loginViewModel.getUser().getId());
                startActivity(homeIntent);
                SharedReferenceData.getInstance().setInt(this,"uid",1);
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