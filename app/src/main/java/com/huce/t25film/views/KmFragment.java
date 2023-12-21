package com.huce.t25film.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huce.t25film.Adapters.PromotionListAdapter;
import com.huce.t25film.Adapters.TicketListAdapter;
import com.huce.t25film.SharedReferenceData;
import com.huce.t25film.Utils.NetworkUtils;
import com.huce.t25film.model.Promotion;
import com.huce.t25film.R;
import com.huce.t25film.model.Ticket;
import com.huce.t25film.viewmodels.KmFragmentViewModel;
import com.huce.t25film.viewmodels.TicketViewModel;

import java.util.List;

public class KmFragment extends Fragment {
    private RecyclerView.Adapter adapterKM;
    private RecyclerView recyclerViewKM;
    private ProgressBar loading;
    private KmFragmentViewModel KmFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_km, container, false);


        recyclerViewKM=view.findViewById(R.id.recyclerviewKM);
        recyclerViewKM.setLayoutManager(new GridLayoutManager(requireContext(),2));
        loading=view.findViewById(R.id.progressBarKM);

        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            sendRequest();
        } else {
            Toast.makeText(requireContext(), "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
        }


        return view;
    }
    private void sendRequest(){
        KmFragmentViewModel = new KmFragmentViewModel();

        // Quan sát LiveData để cập nhật UI khi có dữ liệu mới
        KmFragmentViewModel.getKmLiveData().observe(getViewLifecycleOwner(), new Observer<List<Promotion>>() {
            @Override
            public void onChanged(List<Promotion> kmResource) {
                // Cập nhật dữ liệu trong Adapter và thông báo thay đổi
                adapterKM=new PromotionListAdapter(kmResource);
                adapterKM.notifyDataSetChanged();

                loading.setVisibility(View.GONE);
                recyclerViewKM.setAdapter(adapterKM);
            }
        });
    }

}