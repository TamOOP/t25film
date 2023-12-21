package com.huce.t25film.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.huce.t25film.R;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.ViewFilmPagerAdapter;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.UserService;
import com.huce.t25film.model.UserDataHolder;
import com.huce.t25film.resources.UserResource;
import com.huce.t25film.viewmodels.HomeViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;


public class HomeFragment extends Fragment{

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigation;
    private DrawerLayout drawerLayout;
    private HomeViewModel viewModel;
    private int uid;
    TextView txtname;




//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }
    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        viewModel.getUsername().observe(this, name->Log.e("username",""+name));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        uid = SharedReferenceData.getInstance().getInt(getActivity(),"uid");

        txtname=view.findViewById(R.id.txtNameUserHome);

        viewPager = view.findViewById(R.id.viewpagerhome);
        bottomNavigation = view.findViewById(R.id.top_nav);


        drawerLayout = view.findViewById(R.id.drawerLayout);

        Button btnMenu = view.findViewById(R.id.btnMenu);

        // Gắn menu vào NavigationView
        NavigationView navigationView = view.findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Xử lý sự kiện click trên mục menu
                    if(item.getItemId()==R.id.nav_tttk){
                        startActivity(new Intent(getActivity(), AccountActivity.class));
                    }
                    else if(item.getItemId()==R.id.nav_lsgd){
                        startActivity(new Intent(getActivity(), LSGDActivity.class));
                    }
                    else if(item.getItemId()==R.id.nav_tdmk){
                        startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
                    }
                    else if(item.getItemId()==R.id.nav_dangxuat){
                        startActivity(new Intent(getActivity(), Login1Activity.class));
                    }

                // Đóng DrawerLayout sau khi chọn mục
                DrawerLayout drawer = getActivity().findViewById(R.id.drawerLayout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }

        });
        sendRequest();
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở hoặc đóng navigation drawer khi button được click
                drawerLayout.closeDrawers();
                toggleDrawer();
            }
        });

        // Khởi tạo Adapter cho ViewPager2 và đặt Adapter cho ViewPager2
        ViewFilmPagerAdapter adapter = new ViewFilmPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

        // Xử lý sự kiện khi một mục được chọn trên BottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_sc){
                    viewPager.setCurrentItem(0, true);
                    return true;
                }
                else if(item.getItemId()==R.id.menu_dc){
                    viewPager.setCurrentItem(1, true);
                    return true;
                }
                else if(item.getItemId()==R.id.menu_scs){
                    viewPager.setCurrentItem(2, true);
                    return true;
                }
                else {
                    return false;
                }
            }
        });

        // Lắng nghe sự kiện thay đổi trang trên ViewPager2
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                // Cập nhật BottomNavigationView khi trang ViewPager2 được thay đổi
                bottomNavigation.getMenu().getItem(position).setChecked(true);
            }
        });



        return view;
    }

    private void sendRequest() {
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        UserService userService = retrofit.create(UserService.class);

        // Gọi API
        Call<UserResource> call = userService.getUser(uid);
        call.enqueue(new Callback<UserResource>() {

            @Override
            public void onResponse(Call<UserResource> call, retrofit2.Response<UserResource> response) {
                if (response.isSuccessful()) {

                    UserResource item = response.body();

                    txtname.setText(item.getUser().getName());

                } else {

                }
            }

            @Override
            public void onFailure(Call<UserResource> call, Throwable t) {
            }

        });
    }

    private void toggleDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
            drawerLayout.closeDrawer(GravityCompat.END);
        } else {
            drawerLayout.openDrawer(GravityCompat.END);
        }
    }


//    public class HomeFragment extends Fragment{
//
//        private ViewPager2 viewPager;
//        private BottomNavigationView bottomNavigation;
//
//        private DrawerLayout drawerLayout;
//
//
//
//
//        //    @Override
////    public View onCreateView(LayoutInflater inflater, ViewGroup container,
////                             Bundle savedInstanceState) {
////        // Inflate the layout for this fragment
////        return inflater.inflate(R.layout.fragment_home, container, false);
////    }
//        public HomeFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//            viewPager = view.findViewById(R.id.viewpagerhome);
//            bottomNavigation = view.findViewById(R.id.top_nav);
//
//            viewPager.setUserInputEnabled(false);
//
//            drawerLayout = view.findViewById(R.id.drawerLayout);
//
//            Button btnMenu = view.findViewById(R.id.btnMenu);
//
//            // Gắn menu vào NavigationView
//            NavigationView navigationView = view.findViewById(R.id.navigationView);
//            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    // Xử lý sự kiện click trên mục menu
//                    if(item.getItemId()==R.id.nav_tttk){
//                        startActivity(new Intent(getActivity(), AccountActivity.class));
//                    }
//                    else if(item.getItemId()==R.id.nav_lsgd){
//                        startActivity(new Intent(getActivity(), HistoryActivity.class));
//                    }
//                    else if(item.getItemId()==R.id.nav_tdmk){
//                        startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
//                    }
//                    else if(item.getItemId()==R.id.nav_dangxuat){
//                        startActivity(new Intent(getActivity(), Login1Activity.class));
//                    }
//
//                    // Đóng DrawerLayout sau khi chọn mục
//                    DrawerLayout drawer = getActivity().findViewById(R.id.drawerLayout);
//                    drawer.closeDrawer(GravityCompat.START);
//                    return true;
//                }
//
//            });
//            btnMenu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Mở hoặc đóng navigation drawer khi button được click
//                    drawerLayout.closeDrawers();
//                    toggleDrawer();
//                }
//            });
//
//            // Khởi tạo Adapter cho ViewPager2 và đặt Adapter cho ViewPager2
//            ViewFilmPagerAdapter adapter = new ViewFilmPagerAdapter(requireActivity());
//            viewPager.setAdapter(adapter);
//
//            // Xử lý sự kiện khi một mục được chọn trên BottomNavigationView
//            bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    if(item.getItemId()==R.id.menu_sc){
//                        viewPager.setCurrentItem(0, true);
//                        return true;
//                    }
//                    else if(item.getItemId()==R.id.menu_dc){
//                        viewPager.setCurrentItem(1, true);
//                        return true;
//                    }
//                    else if(item.getItemId()==R.id.menu_scs){
//                        viewPager.setCurrentItem(2, true);
//                        return true;
//                    }
//                    else {
//                        return false;
//                    }
//                }
//            });
//
//            // Lắng nghe sự kiện thay đổi trang trên ViewPager2
//            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//                @Override
//                public void onPageSelected(int position) {
//                    // Cập nhật BottomNavigationView khi trang ViewPager2 được thay đổi
//                    bottomNavigation.getMenu().getItem(position).setChecked(true);
//                }
//            });
//
//
//
//            return view;
//        }
//        private void toggleDrawer() {
//            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
//                drawerLayout.closeDrawer(GravityCompat.END);
//            } else {
//                drawerLayout.openDrawer(GravityCompat.END);
//            }
//        }
//
//    }
}