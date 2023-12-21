package com.huce.t25film.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.CalendarFilmListAdapter;
import com.huce.t25film.R;
import com.huce.t25film.Utils.NetworkUtils;
import com.huce.t25film.resources.ShowResource;
import com.huce.t25film.viewmodels.CalendarFilm1FragmentViewModel;

public class CalendarFilm1Fragment extends Fragment {

    private RecyclerView.Adapter adapterMovies;
    private RecyclerView recyclerViewMovies;
    private ProgressBar loading;
    private CalendarFilm1FragmentViewModel CalendarFilm1ViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_film1, container, false);



        //recyclerView Movies

        recyclerViewMovies=view.findViewById(R.id.recyclerviewFilmCal);
        recyclerViewMovies.setLayoutManager(new GridLayoutManager(requireContext(),1));
        loading=view.findViewById(R.id.progressBarFilmCal);

        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            sendRequest();
        } else {
            Toast.makeText(requireContext(), "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
        }


        return view;
    }
    private void sendRequest(){
        CalendarFilm1ViewModel = new CalendarFilm1FragmentViewModel(getActivity());

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        CalendarFilm1ViewModel.getShowLiveData().observe(getViewLifecycleOwner(), new Observer<ShowResource>() {
            @Override
            public void onChanged(ShowResource showResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                adapterMovies=new CalendarFilmListAdapter(getActivity(), showResource);
                adapterMovies.notifyDataSetChanged();

                loading.setVisibility(View.GONE);
                recyclerViewMovies.setAdapter(adapterMovies);
            }
        });
    }
}