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
import com.huce.t25film.Adapters.KMListAdapter;
import com.huce.t25film.Adapters.SliderAdapters;
import com.huce.t25film.model.ListFilm;
import com.huce.t25film.model.SilderItems;
import com.huce.t25film.R;

import java.util.ArrayList;
import java.util.List;

public class KmFragment extends Fragment {
    private RecyclerView.Adapter adapterKM;
    private RecyclerView recyclerViewKM;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ProgressBar loading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_km, container, false);



        recyclerViewKM=view.findViewById(R.id.recyclerviewKM);
        recyclerViewKM.setLayoutManager(new GridLayoutManager(requireContext(),2));
        loading=view.findViewById(R.id.progressBarKM);


        sendRequest();


        return view;
    }
    private void sendRequest(){
        //sendRequest
        mRequestQueue= Volley.newRequestQueue(requireContext());
        loading.setVisibility(View.VISIBLE);
        try {
            mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    loading.setVisibility(View.GONE);
                    ListFilm items = gson.fromJson(response, ListFilm.class);
                    adapterKM = new KMListAdapter(items);
                    recyclerViewKM.setAdapter(adapterKM);
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

}