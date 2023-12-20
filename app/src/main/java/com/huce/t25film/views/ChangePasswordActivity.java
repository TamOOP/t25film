package com.huce.t25film.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.model.User;
import com.huce.t25film.resources.UserResource;
import com.huce.t25film.viewmodels.AccountViewModel;
import com.huce.t25film.viewmodels.ChangePasswordViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ChangePasswordActivity extends AppCompatActivity {
    ChangePasswordViewModel changePasswordViewModel;
    TextView txtPassNew,txtConfirmPassNew;
    private int uid;
    ConstraintLayout constraintLayoutChangePassword,constraintLayoutBackPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        uid = SharedReferenceData.getInstance().getInt(this,"uid");

        initView();


    }


    private void initView() {
        constraintLayoutChangePassword=findViewById(R.id.ContraintLayoutChangePassword);
        constraintLayoutBackPass=findViewById(R.id.constraintLayoutBackPass);
        txtPassNew=findViewById(R.id.txtNewPassword);
        txtConfirmPassNew=findViewById(R.id.txtReNewPassword);


        changePasswordViewModel = new ViewModelProvider(this).get(ChangePasswordViewModel.class);

        constraintLayoutBackPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                startActivity(intent1);
            }
        });
        constraintLayoutChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ EditText và tạo RegistrationModel
                User registrationModel = new User();
                registrationModel.setPassword(txtPassNew.getText().toString());
                registrationModel.setConfirmpassword(txtConfirmPassNew.getText().toString());

                // Gọi phương thức đăng ký trong ViewModel
                changePasswordViewModel.ChangeUser(uid,registrationModel);

            }
        });

        changePasswordViewModel.getValidationError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String validationError) {
                // Hiển thị thông báo lỗi đăng ký
                Toast.makeText(ChangePasswordActivity.this, validationError, Toast.LENGTH_SHORT).show();
            }
        });

        changePasswordViewModel.getChangeSuccess().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String registrationSuccess) {
                // Hiển thị thông báo đăng ký thành công
                Toast.makeText(ChangePasswordActivity.this, registrationSuccess, Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                startActivity(intent2);
            }
        });
    }
}