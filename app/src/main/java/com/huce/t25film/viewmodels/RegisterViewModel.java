package com.huce.t25film.viewmodels;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huce.t25film.Adapters.FilmListAdapter;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.model.Film;
import com.huce.t25film.model.User;
import com.huce.t25film.repository.LoginRepository;
import com.huce.t25film.resources.UserResource;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<String> validationError = new MutableLiveData<>();
    private MutableLiveData<String> registrationSuccess = new MutableLiveData<>();
    private UserService userService;
    private int isExistPhone=1;
    private int isExistEmail=1;


    public RegisterViewModel() {
        userService = RetrofitBuilder.buildRetrofit().create(UserService.class);
    }

    public LiveData<String> getValidationError() {
        return validationError;
    }

    public LiveData<String> getRegistrationSuccess() {
        return registrationSuccess;
    }

    public void registerUser(User registrationModel) {
        if (TextUtils.isEmpty(registrationModel.getEmail())
                || TextUtils.isEmpty(registrationModel.getName())
                || TextUtils.isEmpty(registrationModel.getPhone())
                || TextUtils.isEmpty(registrationModel.getPassword())) {
            validationError.setValue("Vui lòng nhập đầy đủ thông tin.");
            return;
        }
        // Thực hiện các kiểm tra dữ liệu và gọi API đăng ký
        if (!isValidEmail(registrationModel.getEmail())) {
            validationError.setValue("Email không hợp lệ");
            return;
        }

        if (!isValidPassword(registrationModel.getPassword())) {
            validationError.setValue("Password-Ít nhất 8 ký tự-Chứa ít nhất một chữ cái-Chứa ít nhất một chữ số");
            return;
        }
        if (!isValidName(registrationModel.getName())) {
            validationError.setValue("Tên không hợp lệ. Tên không chứa ký tự số");
            return;
        }

        checkUserInfo(registrationModel);
        if (isExistPhone==1){
            validationError.setValue("Số điện thoại đã bị trùng");
            return;
        }
        if (isExistEmail==1){
            validationError.setValue("Email đã bị trùng");
            return;
        }

        if (!isValidPhoneNumber(registrationModel.getPhone())) {
            validationError.setValue("SĐT-Chứa đúng 11 chữ số-Không chứa ký tự đặc biệt hoặc chữ cái");
            return;
        }



        // Kiểm tra các điều kiện khác và gọi API khi dữ liệu hợp lệ
        // ...

        // Gọi API đăng ký
        Call<User> call = userService.createUser(registrationModel);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    registrationSuccess.setValue("Đăng ký thành công");
                } else {
                    validationError.setValue("Đăng ký thất bại");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                validationError.setValue("Đăng ký thất bại: " + t.getMessage());
            }
        });
    }

    private boolean isValidEmail(String email) {
        // Biểu thức chính quy kiểm tra định dạng email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Tạo Pattern object
        Pattern pattern = Pattern.compile(emailRegex);

        // Kiểm tra email có khớp với biểu thức chính quy không
        return pattern.matcher(email).matches();
    }
    private boolean isValidName(String name){
        // Biểu thức chính quy kiểm tra tên không chứa ký tự số
        String nameRegex = "^[^0-9]+$";

        // Tạo Pattern object
        Pattern pattern = Pattern.compile(nameRegex);

        // Kiểm tra tên có khớp với biểu thức chính quy không
        return pattern.matcher(name).matches();
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

    private boolean isValidPhoneNumber(String phone){
    // - Chứa đúng 11 chữ số
        // - Không chứa ký tự đặc biệt hoặc chữ cái
        String phoneNumberRegex = "^[0-9]{11}$";

        // Tạo Pattern object
        Pattern pattern = Pattern.compile(phoneNumberRegex);

        // Kiểm tra số điện thoại có khớp với biểu thức chính quy không
        return pattern.matcher(phone).matches();
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
                        if (user.getPhone().equals(registrationModel.getPhone())){
                            isExistPhone=1;
                        }else{
                            isExistPhone=0;
                        }
                        if(user.getEmail().equals(registrationModel.getEmail())){
                            isExistEmail=1;
                        }
                        else{
                            isExistEmail=0;
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
