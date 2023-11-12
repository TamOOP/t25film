package com.huce.t25film;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login1Activity extends AppCompatActivity {
    private Button btnRegister,btnLogin;
    private EditText emailEdit,passEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //khai báo Intent
                Intent registerIntent = new Intent(Login1Activity.this,Register1Activity.class);
                //khởi đụng
                startActivity(registerIntent);

            }
        });
        initView();
    }
    private void initView(){
        emailEdit=findViewById(R.id.txtEmail);
        passEdit=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emailEdit.getText().toString().isEmpty() || passEdit.getText().toString().isEmpty()){
                    Toast.makeText(Login1Activity.this,"Please Fill your email and password",Toast.LENGTH_SHORT).show();
                } else if (emailEdit.getText().toString().equals("test") && passEdit.getText().toString().equals("test")) {
                    //khai báo Intent
                    Intent loginIntent = new Intent(Login1Activity.this,HomeActivity.class);
                    //khởi đụng
                    startActivity(loginIntent);
                }
                else {
                    Toast.makeText(Login1Activity.this,"Your email and password is not correct",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}