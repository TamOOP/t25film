package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.huce.t25film.HomeActivity;
import com.huce.t25film.R;
import com.huce.t25film.Register1Activity;
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
        loginViewModel.getAllUser().observe(this, loginDatas
                -> loginViewModel.checkLoginInfo(loginDatas));

        // quan sat livedata message
        loginViewModel.getMessage().observe(this, message
                -> Toast.makeText(Login1Activity.this, message, Toast.LENGTH_SHORT).show());

        //quan sat livedata isLogin
        loginViewModel.getIsLogin().observe(this, isLogin -> {
            if(isLogin){
                // chuyen view
                Intent loginIntent = new Intent(Login1Activity.this, HomeActivity.class);
                startActivity(loginIntent);
            }
        });

    }
    private void initView(){
        emailEdit=findViewById(R.id.txtEmail);
        passEdit=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(view -> {
            //khai báo Intent
            Intent registerIntent = new Intent(Login1Activity.this, Register1Activity.class);
            //khởi đụng
            startActivity(registerIntent);

        });

        btnLogin.setOnClickListener(view -> {
//            if(emailEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()){
//                Toast.makeText(Login1Activity.this,"Please Fill your email and password",Toast.LENGTH_SHORT).show();
//            } else if (emailEdit.getText().toString().equals("test") && passEdit.getText().toString().equals("test")) {
//                //khai báo Intent
//                Intent loginIntent = new Intent(Login1Activity.this, HomeActivity.class);
//                //khởi đụng
//                startActivity(loginIntent);
//            }
//            else {
//                Toast.makeText(Login1Activity.this,"Your email and password is not correct",Toast.LENGTH_SHORT).show();
//            }
        });
    }
}