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

import com.bumptech.glide.Glide;
import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.model.User;
import com.huce.t25film.model.UserDataHolder;
import com.huce.t25film.resources.PromotionResource;
import com.huce.t25film.resources.UserResource;
import com.huce.t25film.viewmodels.AccountViewModel;
import com.huce.t25film.viewmodels.DetailKmViewModel;
import com.huce.t25film.viewmodels.RegisterViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class AccountActivity extends AppCompatActivity {
    AccountViewModel accountViewModel;
    TextView txtName,txtPhone;
    private int uid;



    ConstraintLayout constraintLayoutChangeAccount,constraintLayoutBackAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        uid = SharedReferenceData.getInstance().getInt(this,"uid");

        fetchuser(uid);
        initView();

    }

//    private void sendRequest() {
//
//
//        accountViewModel = new AccountViewModel(uid);
//
//        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
//        accountViewModel.getUserLiveData().observe(this, new Observer<UserResource>() {
//            @Override
//            public void onChanged(UserResource userResource) {
//
//                txtPhone.setText(userResource.getUser().getPhone());
//                txtName.setText(userResource.getUser().getName());
//            }
//        });
//    }

    private void initView() {
        constraintLayoutChangeAccount=findViewById(R.id.ContraintLayoutChangeAccount);
        constraintLayoutBackAccount=findViewById(R.id.constraintLayoutBackAccount);
        txtName=findViewById(R.id.txtNameAccount);
        txtPhone=findViewById(R.id.txtPhoneAccount);

        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);

        constraintLayoutBackAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AccountActivity.this, HomeActivity.class);
                startActivity(intent1);
            }
        });
        constraintLayoutChangeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ EditText và tạo RegistrationModel
                User registrationModel = new User();
                registrationModel.setName(txtName.getText().toString());
                registrationModel.setPhone(txtPhone.getText().toString());

                // Gọi phương thức đăng ký trong ViewModel
                accountViewModel.ChangeUser(uid,registrationModel);
            }
        });
        accountViewModel.getValidationError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String validationError) {
                // Hiển thị thông báo lỗi đăng ký
                Toast.makeText(AccountActivity.this, validationError, Toast.LENGTH_SHORT).show();
            }
        });

        accountViewModel.getChangeSuccess().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String registrationSuccess) {
                // Hiển thị thông báo đăng ký thành công
                Toast.makeText(AccountActivity.this, registrationSuccess, Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(AccountActivity.this, HomeActivity.class);
                startActivity(intent2);
            }
        });
    }
    private void fetchuser(int id) {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        UserService userService = retrofit.create(UserService.class);
        // Gọi API
        Call<UserResource> call = userService.getUser(id);
        call.enqueue(new Callback<UserResource>() {

            @Override
            public void onResponse(Call<UserResource> call, retrofit2.Response<UserResource> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    UserResource items = response.body();
                    txtPhone.setText(items.getUser().getPhone());
                    txtName.setText(items.getUser().getName());


                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<UserResource> call, Throwable t) {

            }

        });
    }
}