package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.huce.t25film.R;


public class AccountActivity extends AppCompatActivity {
    ConstraintLayout constraintLayoutChangeAccount,constraintLayoutBackAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        constraintLayoutChangeAccount=findViewById(R.id.ContraintLayoutChangeAccount);
        constraintLayoutBackAccount=findViewById(R.id.constraintLayoutBackAccount);

        constraintLayoutChangeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AccountActivity.this, Login1Activity.class);
                startActivity(intent1);
            }
        });
        constraintLayoutBackAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AccountActivity.this, HomeActivity.class);
                startActivity(intent2);
            }
        });
    }
}