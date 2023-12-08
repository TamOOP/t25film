package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huce.t25film.R;

public class ChangePasswordActivity extends AppCompatActivity {
    ConstraintLayout constraintLayoutChangePassword,constraintLayoutBackPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        constraintLayoutChangePassword=findViewById(R.id.ContraintLayoutChangePassword);
        constraintLayoutBackPass=findViewById(R.id.constraintLayoutBackPass);

        constraintLayoutChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ChangePasswordActivity.this, Login1Activity.class);
                startActivity(intent1);
            }
        });
        constraintLayoutBackPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                startActivity(intent2);
            }
        });
    }
}