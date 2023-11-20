package com.huce.t25film;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
}