package com.huce.t25film.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.huce.t25film.Adapters.CalendarFilmListAdapter;
import com.huce.t25film.Adapters.FilmListAdapter;
import com.huce.t25film.api.FilmService;
import com.huce.t25film.api.RetrofitBuilder;
import com.huce.t25film.api.ShowService;
import com.huce.t25film.model.Film;
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.R;
import com.huce.t25film.resources.ShowResource;
import com.huce.t25film.viewmodels.CalendarFilm1FragmentViewModel;
import com.huce.t25film.viewmodels.DCFragmentViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CalendarFilm1Fragment extends Fragment {

    private RecyclerView.Adapter adapterMovies;
    private RecyclerView recyclerViewMovies;
    private ProgressBar loading;
    private com.huce.t25film.viewmodels.CalendarFilm1FragmentViewModel CalendarFilm1ViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_film1, container, false);



        //recyclerView Movies

        recyclerViewMovies=view.findViewById(R.id.recyclerviewFilmCal);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(requireContext(),1));
        loading=view.findViewById(R.id.progressBarFilmCal);

        sendRequest();


        return view;
    }
    private void sendRequest(){
        //sendRequest
//        mRequestQueue= Volley.newRequestQueue(requireContext());
//        loading.setVisibility(View.VISIBLE);
//        try {
//            mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Gson gson = new Gson();
//                    loading.setVisibility(View.GONE);
//                    ListFilm items = gson.fromJson(response, ListFilm.class);
//                    adapterMovies = new CalendarFilmListAdapter(items);
//                    recyclerViewMovies.setAdapter(adapterMovies);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    // Xử lý khi có lỗi
//                    loading.setVisibility(View.GONE);
//                    Log.i("T25", "onErrorRessponse:" + error.toString());
//                }
//            });
//            mRequestQueue.add(mStringRequest);
//        }
//        catch (Exception e){
//            System.out.println("Exception: "+e.toString());
//        }
//        finally {
//            System.out.println("Finally block executed");
//        }

        // Khởi tạo Retrofit Client
        Retrofit retrofit = RetrofitBuilder.buildRetrofit();
        ShowService showService = retrofit.create(ShowService.class);

        //Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
        //String formattedDate1 = dateFormat.format(currentDate);

        // Lấy ngày mai
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Thêm 1 ngày để lấy ngày mai
        Date tomorrow = calendar.getTime();

        String formattedDate2 = dateFormat.format(tomorrow);

        // Gọi API
        Call<ShowResource> call = showService.getShowsId(formattedDate2);
        call.enqueue(new Callback<ShowResource>() {

            @Override
            public void onResponse(Call<ShowResource> call, retrofit2.Response<ShowResource> response) {
                if (response.isSuccessful()) {
                    // Ẩn loading khi nhận được dữ liệu
                    loading.setVisibility(View.GONE);

                    // Lấy đối tượng ListFilm từ response.body()
                    ShowResource item = response.body();


                    // Tạo Adapter và thiết lập RecyclerView
                    adapterMovies = new CalendarFilmListAdapter(item);
                    recyclerViewMovies.setAdapter(adapterMovies);
                } else {

                    // Xử lý khi phản hồi không thành công
                    //int statusCode = response.code();
                    //String errorBody = response.errorBody().string();
                    Log.e("Error", "Status Code: ");
                    // ...
                }
            }

            @Override
            public void onFailure(Call<ShowResource> call, Throwable t) {

            }

        });
//        CalendarFilm1ViewModel = new CalendarFilm1FragmentViewModel();
//
//        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
//        CalendarFilm1ViewModel.getShowLiveData().observe(getViewLifecycleOwner(), new Observer<ShowResource>() {
//            @Override
//            public void onChanged(List<ShowResource> filmList) {
//                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
//                adapterMovies=new FilmListAdapter(filmList);
//                adapterMovies.notifyDataSetChanged();
//            }
//        });
    }
}