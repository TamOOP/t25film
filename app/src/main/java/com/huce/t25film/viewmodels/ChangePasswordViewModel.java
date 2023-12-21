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

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePasswordViewModel extends ViewModel {
    private MutableLiveData<UserResource> userLiveData = new MutableLiveData<>();
    private MutableLiveData<String> validationError = new MutableLiveData<>();
    private MutableLiveData<String> changeSuccess = new MutableLiveData<>();
    private AccountRepository userRepository; // Tùy chọn: nơi bạn thực hiện gọi API

    private UserService userService;
    private int isExistPassword=0;



    public ChangePasswordViewModel() {
        userService = RetrofitBuilder.buildRetrofit().create(UserService.class);
        userRepository = new AccountRepository(); // Khởi tạo repository
        //loadFilmList(); // Gọi API khi ViewModel được tạo
        //fetchuser(id);
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
//            public void onResponse(Call<UserResource> call, Response<UserResource> response) {
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
        if (TextUtils.isEmpty(registrationModel.getPasswordOld())
                || TextUtils.isEmpty(registrationModel.getPassword())
                || TextUtils.isEmpty(registrationModel.getConfirmpassword())) {
            validationError.setValue("Vui lòng nhập đầy đủ thông tin.");
            return;
        }
        if (!isValidPassword(registrationModel.getPassword())) {
            validationError.setValue("Password-Ít nhất 8 ký tự-Chứa ít nhất một chữ cái-Chứa ít nhất một chữ số");
            return;
        }
        if (!isValidConfirmPassword(registrationModel.getPassword(), registrationModel.getConfirmpassword())) {
            validationError.setValue("Nhập lại password không khớp");
            return;
        }
        checkUserInfo(registrationModel);
        if (isExistPassword==0){
            validationError.setValue("Password nhập không đúng");
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
    private boolean isValidPassword(String password) {
        // - Ít nhất 8 ký tự
        // - Chứa ít nhất một chữ cái
        // - Chứa ít nhất một chữ số
        // - (Thêm các yêu cầu khác nếu cần)
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";

        // Tạo Pattern object
        Pattern pattern = Pattern.compile(passwordRegex);

        // Kiểm tra mật khẩu có khớp với biểu thức chính quy không
        return pattern.matcher(password).matches();
    }
    private boolean isValidConfirmPassword(String password, String confirmPassword) {
        // Thực hiện kiểm tra password và nhập lại password có khớp nhau không
        return password.equals(confirmPassword);
    }

    public void checkUserInfo(User registrationModel){
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        UserService userService = retrofit.create(UserService.class);
        // Gọi API
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                if (response.isSuccessful()) {

                    // Lấy đối tượng ListFilm từ response.body()
                    List<User> items = response.body();
                    for(User user: items){
                        if (user.getPassword().equals(registrationModel.getPasswordOld())){
                            isExistPassword=1;
                        }else{
                            isExistPassword=0;
                        }
                    }


                } else {

                    Log.e("Error", "Status Code: ");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }

        });
    }
}
