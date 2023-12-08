package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.huce.t25film.R;

public class Register1Activity extends AppCompatActivity {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khai báo Intent
//                Intent backIntent = new Intent(Register1Activity.this, Login1Activity.class);
//                //Chạy
//                startActivity(backIntent);
                finish();
            }
        });
    }
}