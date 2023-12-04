package com.huce.t25film;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.huce.t25film.views.HomeActivity;

public class AccountActivity extends AppCompatActivity {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //khai báo Intent
                Intent registerIntent = new Intent(AccountActivity.this, HomeActivity.class);
                //khởi đụng
                startActivity(registerIntent);
            }
        });
    }
}