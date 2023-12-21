package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huce.t25film.R;
import com.huce.t25film.Utils.NetworkUtils;
import com.huce.t25film.model.User;
import com.huce.t25film.viewmodels.RegisterViewModel;

public class Register1Activity extends AppCompatActivity {
    Button btnBack;
    private EditText emailEditText, passwordEditText,usernameEditText, phoneNumberEditText;
    private Button registerButton;
    private RegisterViewModel registrationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        if (NetworkUtils.isNetworkAvailable(this)) {
            emailEditText = findViewById(R.id.txtEmail);
            passwordEditText = findViewById(R.id.txtPassword);
            usernameEditText = findViewById(R.id.txtNameUser);
            phoneNumberEditText = findViewById(R.id.txtPhone);
            registerButton = findViewById(R.id.btnRegister);
            btnBack = findViewById(R.id.btnBack);

            registrationViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Lấy dữ liệu từ EditText và tạo RegistrationModel
                    User registrationModel = new User();
                    registrationModel.setEmail(emailEditText.getText().toString());
                    registrationModel.setPassword(passwordEditText.getText().toString());
                    registrationModel.setName(usernameEditText.getText().toString());
                    registrationModel.setPhone(phoneNumberEditText.getText().toString());

                    // Gọi phương thức đăng ký trong ViewModel
                    registrationViewModel.registerUser(registrationModel);
                }
            });

            // Lắng nghe sự thay đổi trong LiveData để cập nhật UI
            registrationViewModel.getValidationError().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String validationError) {
                    // Hiển thị thông báo lỗi đăng ký
                    Toast.makeText(Register1Activity.this, validationError, Toast.LENGTH_SHORT).show();
                }
            });

            registrationViewModel.getRegistrationSuccess().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String registrationSuccess) {
                    // Hiển thị thông báo đăng ký thành công
                    Toast.makeText(Register1Activity.this, registrationSuccess, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register1Activity.this, Login1Activity.class);
                    startActivity(intent);
                }
            });

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
        }

    }
}