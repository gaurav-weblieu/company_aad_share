package com.company.companyadda.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.companyadda.Activities.Offers;
import com.company.companyadda.Adapter.Offers_list_adapter;
import com.company.companyadda.R;

public class OffersFragment extends Fragment {

    private RecyclerView recycleView_offer;
    private Offers_list_adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_offers, container, false);

        recycleView_offer=view.findViewById(R.id.recycleView_offer);


        recycleView_offer.setLayoutManager(new LinearLayoutManager(getActivity() ));
        adapter = new Offers_list_adapter(getActivity());
        recycleView_offer.setAdapter(adapter);

        return view;
    }
}