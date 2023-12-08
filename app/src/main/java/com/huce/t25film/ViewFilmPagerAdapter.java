package com.huce.t25film;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.huce.t25film.views.DCFragment;
import com.huce.t25film.views.SCFragment;
import com.huce.t25film.views.SCSFragment;

public class ViewFilmPagerAdapter extends FragmentStateAdapter {


    //    public ViewFilmPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
//        super(fragmentManager, lifecycle);
//    }
    public ViewFilmPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SCFragment();
            case 1:
                return new DCFragment();
            case 2:
                return new SCSFragment();
            default:
                return new DCFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Số lượng mục trong BottomNavigationView
    }
}
