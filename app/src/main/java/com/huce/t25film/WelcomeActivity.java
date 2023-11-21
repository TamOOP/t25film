package com.huce.t25film;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.huce.t25film.views.Login1Activity;

public class WelcomeActivity extends AppCompatActivity {
    Button btnGetstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnGetstart = findViewById(R.id.btnGetStart);
        btnGetstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //khai báo Intent
                Intent getstartIntent = new Intent(WelcomeActivity.this, Login1Activity.class);
                //khởi đụng
                startActivity(getstartIntent);
                finish();
            }
        });
    }
}