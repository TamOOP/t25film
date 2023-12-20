package com.huce.t25film.viewmodels;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.model.User;
import com.huce.t25film.repository.AccountRepository;
import com.huce.t25film.resources.UserResource;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccountViewModel extends ViewModel {
    private MutableLiveData<UserResource> userLiveData = new MutableLiveData<>();
    private MutableLiveData<String> validationError = new MutableLiveData<>();
    private MutableLiveData<String> changeSuccess = new MutableLiveData<>();
    private AccountRepository userRepository; // Tùy chọn: nơi bạn thực hiện gọi API

    private UserService userService;


    public AccountViewModel() {
        userService = RetrofitBuilder.buildRetrofit().create(UserService.class);
        userRepository = new AccountRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
    }

    public LiveData<UserResource> getUserLiveData() {
        return userLiveData;
    }

//    private void fetchuser(int id) {
//        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
//        UserService userService = retrofit.create(UserService.class);
//        // Gọi API
//        Call<UserResource> call = userService.getUser(id);
//        call.enqueue(new Callback<UserResource>() {
//
//            @Override
//            public void onResponse(Call<UserResource> call, retrofit2.Response<UserResource> response) {
//                if (response.isSuccessful()) {
//
//                    // Lấy đối tượng ListFilm từ response.body()
//                    UserResource items = response.body();
//                    userLiveData.postValue(items);
//
//
//                } else {
//
//                    Log.e("Error", "Status Code: ");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResource> call, Throwable t) {
//
//            }
//
//        });
//    }
    public LiveData<String> getValidationError() {
        return validationError;
    }

    public LiveData<String> getChangeSuccess() {
        return changeSuccess;
    }

    public void ChangeUser(Integer id,User registrationModel) {
        if (TextUtils.isEmpty(registrationModel.getName())
                || TextUtils.isEmpty(registrationModel.getPhone())) {
            validationError.setValue("Vui lòng nhập đầy đủ thông tin.");
            return;
        }
        if (!isValidName(registrationModel.getName())) {
            validationError.setValue("Tên không hợp lệ. Tên không chứa ký tự số");
            return;
        }

        if (!isValidPhoneNumber(registrationModel.getPhone())) {
            validationError.setValue("SĐT-Chứa đúng 11 chữ số-Không chứa ký tự đặc biệt hoặc chữ cái");
            return;
        }


        // Gọi API đăng ký
        Call<User> call = userService.updateUser(id,registrationModel);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    changeSuccess.setValue("Thay đổi thành công");
                } else {
                    validationError.setValue("Thay đổi thất bại");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                validationError.setValue("Thay đổi thất bại: " + t.getMessage());
            }
        });
    }
    private boolean isValidName(String name){
        // Biểu thức chính quy kiểm tra tên không chứa ký tự số
        String nameRegex = "^[^0-9]+$";

        // Tạo Pattern object
        Pattern pattern = Pattern.compile(nameRegex);

        // Kiểm tra tên có khớp với biểu thức chính quy không
        return pattern.matcher(name).matches();
    }
    private boolean isValidPhoneNumber(String phone){
        // - Chứa đúng 11 chữ số
        // - Không chứa ký tự đặc biệt hoặc chữ cái
        String phoneNumberRegex = "^[0-9]{11}$";

        // Tạo Pattern object
        Pattern pattern = Pattern.compile(phoneNumberRegex);

        // Kiểm tra số điện thoại có khớp với biểu thức chính quy không
        return pattern.matcher(phone).matches();
    }
}
