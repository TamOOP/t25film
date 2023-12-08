package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.huce.t25film.R;

public class HistoryActivity extends AppCompatActivity {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //khai báo Intent
                Intent registerIntent = new Intent(HistoryActivity.this,HomeActivity.class);
                //khởi đụng
                startActivity(registerIntent);
            }
        });

    }

}