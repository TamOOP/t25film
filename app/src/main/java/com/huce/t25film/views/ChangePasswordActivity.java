package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.Utils.NetworkUtils;
import com.huce.t25film.model.User;
import com.huce.t25film.viewmodels.ChangePasswordViewModel;

public class ChangePasswordActivity extends AppCompatActivity {
    ChangePasswordViewModel changePasswordViewModel;
    TextView txtPassNew,txtConfirmPassNew,txtPasswordOld;
    private int uid;
    ConstraintLayout constraintLayoutChangePassword,constraintLayoutBackPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        uid = SharedReferenceData.getInstance().getInt(this,"uid");

        if (NetworkUtils.isNetworkAvailable(this)) {
            initView();
        } else {
            Toast.makeText(this, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("state","start");
        // kiem tra dang nhap
        if(SharedReferenceData.getInstance().getInt(this,"uid") == 0){
            Intent login = new Intent(this, Login1Activity.class);
            startActivity(login);
            finish();
        }
    }


    private void initView() {
        constraintLayoutChangePassword=findViewById(R.id.ContraintLayoutChangePassword);
        constraintLayoutBackPass=findViewById(R.id.constraintLayoutBackPass);
        txtPassNew=findViewById(R.id.txtNewPassword);
        txtConfirmPassNew=findViewById(R.id.txtReNewPassword);
        txtPasswordOld=findViewById(R.id.txtPasswordOld);


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
                registrationModel.setPasswordOld(txtPasswordOld.getText().toString());
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