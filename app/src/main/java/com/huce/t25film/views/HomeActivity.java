package com.huce.t25film.views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.ViewPagerAdapter;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.model.UserDataHolder;
import com.huce.t25film.resources.UserResource;
import com.huce.t25film.viewmodels.HomeViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity{

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigation;
    private int uid;
    //UserDataHolder userDataHolder = UserDataHolder.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //uid = getIntent().getExtras().getInt("uid");
        //sendRequest();
////        uid = 11;
//        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//
//        viewModel.getUser(uid).observe(this, resource -> {
//            if(resource.getStatus().equals("success")){
//                viewModel.setUser(resource.getUser());
//            }
//            else{
//                Toast.makeText(this, resource.getMessage(), Toast.LENGTH_SHORT);
//            }
//        });


        viewPager = findViewById(R.id.view_pager);
        bottomNavigation = findViewById(R.id.bottom_navigation);


        // Khởi tạo và thiết lập adapter cho ViewPager2
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigation.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigation.getMenu().findItem(R.id.menu_film).setChecked(true);
                        break;
                    case 2:
                        bottomNavigation.getMenu().findItem(R.id.menu_km).setChecked(true);
                        break;
                }
            }
        });
        // Xử lý sự kiện click trên BottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId()==R.id.menu_home){
                viewPager.setCurrentItem(0, true);
                return true;
            }
            else if(item.getItemId()==R.id.menu_film){
                viewPager.setCurrentItem(1, true);
                return true;
            }
            else if(item.getItemId()==R.id.menu_km){
                viewPager.setCurrentItem(2, true);
                return true;
            }
            else {
                return false;
            }
//            switch (item.getItemId()) {
//                case R.id.menu_home:
//                    viewPager.setCurrentItem(0, true);
//                    return true;
//                case R.id.menu_film:
//                    viewPager.setCurrentItem(1, true);
//                    return true;
//                case R.id.menu_km:
//                    viewPager.setCurrentItem(2, true);
//                    return true;
//                default:
//                    return false;
//            }
        });
    }



//        private ViewPager2 viewPager;
//        private BottomNavigationView bottomNavigation;
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_home);
//
//            viewPager = findViewById(R.id.view_pager);
//            bottomNavigation = findViewById(R.id.bottom_navigation);
//
//
//            viewPager.setUserInputEnabled(false);
//
//
//            // Khởi tạo và thiết lập adapter cho ViewPager2
//            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
//            viewPager.setAdapter(adapter);
//
//            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//                @Override
//                public void onPageSelected(int position) {
//                    super.onPageSelected(position);
//                    switch (position){
//                        case 0:
//                            bottomNavigation.getMenu().findItem(R.id.menu_home).setChecked(true);
//                            break;
//                        case 1:
//                            bottomNavigation.getMenu().findItem(R.id.menu_film).setChecked(true);
//                            break;
//                        case 2:
//                            bottomNavigation.getMenu().findItem(R.id.menu_km).setChecked(true);
//                            break;
//                    }
//                }
//            });
//            // Xử lý sự kiện click trên BottomNavigationView
//            bottomNavigation.setOnNavigationItemSelectedListener(item -> {
//                if(item.getItemId()==R.id.menu_home){
//                    viewPager.setCurrentItem(0, true);
//                    return true;
//                }
//                else if(item.getItemId()==R.id.menu_film){
//                    viewPager.setCurrentItem(1, true);
//                    return true;
//                }
//                else if(item.getItemId()==R.id.menu_km){
//                    viewPager.setCurrentItem(2, true);
//                    return true;
//                }
//                else {
//                    return false;
//                }
////            switch (item.getItemId()) {
////                case R.id.menu_home:
////                    viewPager.setCurrentItem(0, true);
////                    return true;
////                case R.id.menu_film:
////                    viewPager.setCurrentItem(1, true);
////                    return true;
////                case R.id.menu_km:
////                    viewPager.setCurrentItem(2, true);
////                    return true;
////                default:
////                    return false;
////            }
//            });
//        }
//    }
}