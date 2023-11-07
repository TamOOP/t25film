package com.huce.t25film;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }
}

//public class Login extends AppCompatActivity {
//
//    private Button btnLogin;
//
//    private Button btnCall;
//    private EditText edUsername,edPassword;
//
//    private ActivityResultLauncher<Intent> callBackForLogin;
//
//    private ActivityResultLauncher<String> callPhoneLauncher;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        init();
//        processEvents();
//        proccessCall();
//    }
//
//    public void init(){
//        try {
//            btnLogin = findViewById(R.id.btnLogin);
//            edUsername = findViewById(R.id.edUsername);
//            edPassword = findViewById(R.id.edPassword);
//
//        }
//        catch (Exception e){
//            Log.e("Init",e.getMessage());
//        }
//    }
//    private void call() {
//        Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:098789999"));
//        try {
//            startActivity(in);
//        } catch (android.content.ActivityNotFoundException ex){
//            Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void processEvents(){
//        try {
//            //Đăng ký sự kiện khi nào MainActivity trả về kết quả
//            //Khi kết quả trả về thì màn hình Login lưu thêm 1 kết quả
//            callBackForLogin = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//                //Code here
//                if(result != null && result.getResultCode()==RESULT_OK){
//                    //Toast.makeText(getApplicationContext(),"Result return ",Toast.LENGTH_SHORT).show();
//
//                    //Lấy về giá trị result
//                    String value = result.getData().getStringExtra("nuce");
//                    if(value != null){
//                        edUsername.setText(value);
//                        edPassword.setText("");
//                    }
//                }
//            });
//
//
//            btnLogin.setOnClickListener(v->{
//                try{
//                    String username,password;
//                    username = edUsername.getText().toString();
//                    password = edPassword.getText().toString();
//                    Log.e("Username",username);
//                    Log.e("Password",password);
//                    if (validate(username, password)) {
//                        //đăng nhập thành công
//                        if(username.equals("huce")&&password.equals("123456")){
//                            Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Login.this, MainActivity.class);
//
//                            intent.putExtra("username","Đại học xây dựng HN");
//                            startActivity(intent);
//                            finish();
//                            //callBackForLogin.launch(intent);
//                        }else{
//                            Toast.makeText(getApplicationContext(),getString(R.string.errUsername),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//                catch (Exception ex){
//                    Log.e("btnLogin Clicked:",ex.getMessage());
//                }
//            });
//
//
//        }
//        catch (Exception e){
//            Toast.makeText(getApplicationContext(),"Có lỗi xảy ra vui lòng thử lại sau",Toast.LENGTH_SHORT).show();
//            Log.e("Button login clicked",e.getMessage());
//        }
//    }
//
//    public void proccessCall(){
//        try {
//            callPhoneLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),isGranted->{
//                if(isGranted){
//                    call();
//                }else{
//                    Toast.makeText(getApplicationContext(), "Please accept permission to  call  to the number", Toast.LENGTH_SHORT).show();
//                }
//            });
//            btnCall.setOnClickListener(v->{
//                if(ContextCompat.checkSelfPermission(Login.this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
//                    callPhoneLauncher.launch(Manifest.permission.CALL_PHONE);
//                    Toast.makeText(getApplicationContext(), "Ấn thành công", Toast.LENGTH_SHORT).show();
//
//                }
//                else{
//                    call();
//                    Toast.makeText(getApplicationContext(), "Ấn thành công", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        }
//        catch (Exception e){
//            Toast.makeText(getApplicationContext(),"Có lỗi xảy ra vui lòng thử lại sau",Toast.LENGTH_SHORT).show();
//            Log.e("Button login clicked",e.getMessage());
//        }
//    }
//
//
//    public boolean validate(String username,String password){
//        if(username == null || username.equals("")){
//            edUsername.setError("");
//            edUsername.requestFocus();
//            return false;
//        }
//        else if(password == null || password.equals("")){
//            edPassword.setError("");
//            edPassword.requestFocus();
//            return false;
//        }
//        return true;
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i("onStart","onStart call after onCreate");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i("onRemuse","onRemuse call before running app and after onStart");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i("onPause","onPause call when activity disable");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.i("onStop","onStop call when activity cover");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i("onDestroy","The activity is about to be destroyed ");
//    }
//}
