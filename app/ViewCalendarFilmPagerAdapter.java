package com.huce.t25film;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewCalendarFilmPagerAdapter extends FragmentStateAdapter {
    public ViewCalendarFilmPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new DetailCalendarFilmFragment();
            case 1:
                return new CalendarFilm1Fragment();
            case 2:
                return new CalendarFilm2Fragment();
            case 3:
                return new CalendarFilm3Fragment();
            default:
                return new DetailCalendarFilmFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4; // Số lượng mục trong BottomNavigationView
    }
}
