package com.huce.t25film.views;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.huce.t25film.Adapters.FilmListAdapter;
import com.huce.t25film.Adapters.SliderAdapters;
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.model.SilderItems;
import com.huce.t25film.R;

import java.util.ArrayList;
import java.util.List;

public class DCFragment extends Fragment {

    private RecyclerView.Adapter adapterMovies;
    private RecyclerView recyclerViewMovies;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar loading;
    private ViewPager2 viewPager2;
    private Handler slideHander = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d_c, container, false);

        viewPager2=view.findViewById(R.id.viewpagerSliderDC);

        //recyclerView Movies

        recyclerViewMovies=view.findViewById(R.id.recyclerviewDC);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(requireContext(),3));
        loading=view.findViewById(R.id.progressBarDC);

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


        sendRequest();


        return view;
    }
    private void sendRequest(){
        //sendRequest
        mRequestQueue= Volley.newRequestQueue(requireContext());
        loading.setVisibility(View.VISIBLE);
        try {
            mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    loading.setVisibility(View.GONE);
                    ListFilm items = gson.fromJson(response, ListFilm.class);
                    adapterMovies = new FilmListAdapter(items);
                    recyclerViewMovies.setAdapter(adapterMovies);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Xử lý khi có lỗi
                    loading.setVisibility(View.GONE);
                    Log.i("T25", "onErrorRessponse:" + error.toString());
                }
            });
            mRequestQueue.add(mStringRequest);
        }
        catch (Exception e){
            System.out.println("Exception: "+e.toString());
        }
        finally {
            System.out.println("Finally block executed");
        }
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