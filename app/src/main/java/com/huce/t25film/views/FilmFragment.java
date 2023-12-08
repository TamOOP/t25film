package com.huce.t25film.views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.huce.t25film.R;
import com.huce.t25film.ViewCalendarFilmPagerAdapter;

public class FilmFragment extends Fragment {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigation;


    public FilmFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);

        viewPager = view.findViewById(R.id.viewpagerCalFilm);
        bottomNavigation = view.findViewById(R.id.bottom_navigationviewCal);

        viewPager.setUserInputEnabled(false);


        //Bottomnavigation
        // Lấy ngày hôm nay
        Date currentDate = Calendar.getInstance().getTime();

        // Định dạng ngày theo định dạng mong muốn (ví dụ: "dd/MM")
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM", Locale.getDefault());
        String formattedDate1 = dateFormat.format(currentDate);

        // Đặt title cho item menu trong BottomNavigationView
        bottomNavigation.getMenu().getItem(0).setTitle(formattedDate1);

        // Lấy ngày mai
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Thêm 1 ngày để lấy ngày mai
        Date tomorrow = calendar.getTime();

        String formattedDate2 = dateFormat.format(tomorrow);

        bottomNavigation.getMenu().getItem(1).setTitle(formattedDate2);

        // Lấy ngày kia
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_YEAR, 2); // Thêm 1 ngày để lấy ngày mai
        Date thedayaftertomorrow = calendar1.getTime();

        String formattedDate3 = dateFormat.format(thedayaftertomorrow);

        bottomNavigation.getMenu().getItem(2).setTitle(formattedDate3);

        // Lấy ngày kìa
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, 3); // Thêm 1 ngày để lấy ngày mai
        Date thedayafterthedayaftertomorrow = calendar2.getTime();

        String formattedDate4 = dateFormat.format(thedayafterthedayaftertomorrow);

        bottomNavigation.getMenu().getItem(3).setTitle(formattedDate4);


        // Khởi tạo Adapter cho ViewPager2 và đặt Adapter cho ViewPager2
        ViewCalendarFilmPagerAdapter adapter = new ViewCalendarFilmPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

        // Xử lý sự kiện khi một mục được chọn trên BottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.menu_hn){
                    viewPager.setCurrentItem(0, true);
                    return true;
                }
                else if(item.getItemId()==R.id.menu_n1){
                    viewPager.setCurrentItem(1, true);
                    return true;
                }
                else if(item.getItemId()==R.id.menu_n2){
                    viewPager.setCurrentItem(2, true);
                    return true;
                }
                else if(item.getItemId()==R.id.menu_n3){
                    viewPager.setCurrentItem(3, true);
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


}