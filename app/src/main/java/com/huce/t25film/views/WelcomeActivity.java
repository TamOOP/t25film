package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.huce.t25film.R;
import com.huce.t25film.Utils.NetworkUtils;

public class WelcomeActivity extends AppCompatActivity {
    Button btnGetstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (NetworkUtils.isNetworkAvailable(this)) {
            btnGetstart = findViewById(R.id.btnGetStart);
            btnGetstart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //khai báo Intent
                    Intent getstartIntent = new Intent(WelcomeActivity.this, Login1Activity.class);
                    //khởi đụng
                    startActivity(getstartIntent);

                }
            });
        } else {
            Toast.makeText(this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
        }

    }
}