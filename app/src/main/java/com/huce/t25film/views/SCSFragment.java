package com.huce.t25film.views;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.huce.t25film.Adapters.FilmListAdapter;
import com.huce.t25film.Adapters.SliderAdapters;
import com.huce.t25film.Utils.NetworkUtils;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.model.Film;
import com.huce.t25film.resources.FilmResource;
import com.huce.t25film.model.SilderItems;
import com.huce.t25film.R;
import com.huce.t25film.viewmodels.SCFragmentViewModel;
import com.huce.t25film.viewmodels.SCSFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class SCSFragment extends Fragment {

    private RecyclerView.Adapter adapterMovies;
    private RecyclerView recyclerViewMovies;
    private ProgressBar loading;
    private ViewPager2 viewPager2;
    private Handler slideHander = new Handler();
    private SCSFragmentViewModel SCSFragmentViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scs, container, false);

        viewPager2=view.findViewById(R.id.viewpagerSliderSCS);

        //recyclerView Movies

        recyclerViewMovies=view.findViewById(R.id.recyclerviewSCS);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(requireContext(),3));
        loading=view.findViewById(R.id.progressBarSCS);

        List<SilderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SilderItems(R.drawable.introfilm1));
        sliderItems.add(new SilderItems(R.drawable.introfilm2));
        sliderItems.add(new SilderItems(R.drawable.introfilm3));

        viewPager2.setAdapter(new SliderAdapters(sliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);//tạo hiệu ứng các con
        viewPager2.setOffscreenPageLimit(3);//giới hạn phim trong slider
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHander.removeCallbacks(sliderRunnable);
            }
        });


        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            sendRequest();
        } else {
            Toast.makeText(requireContext(), "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
        }


        return view;
    }
    private void sendRequest(){
        SCSFragmentViewModel = new SCSFragmentViewModel();

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        SCSFragmentViewModel.getFilmListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> filmList) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                adapterMovies=new FilmListAdapter(filmList);
                adapterMovies.notifyDataSetChanged();

                loading.setVisibility(View.GONE);
                recyclerViewMovies.setAdapter(adapterMovies);
            }
        });
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPager2.getCurrentItem();
            int totalItems = viewPager2.getAdapter() != null ? viewPager2.getAdapter().getItemCount() : 0;

            // Di chuyển đến item tiếp theo (nếu có)
            if (currentItem < totalItems - 1) {
                viewPager2.setCurrentItem(currentItem + 1);
            } else {
                viewPager2.setCurrentItem(0); // Quay lại item đầu tiên nếu đang ở cuối cùng
            }

            // Lập lại quá trình sau 2000 milliseconds (2 giây)
            slideHander.postDelayed(this, 2000);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        slideHander.postDelayed(sliderRunnable,2000);

    }

    @Override
    public void onPause() {
        super.onPause();
        slideHander.removeCallbacks(sliderRunnable);
    }
}