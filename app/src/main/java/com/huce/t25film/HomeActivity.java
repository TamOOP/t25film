package com.huce.t25film;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigation;
    private int uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        uid = getIntent().getExtras().getInt("uid");

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