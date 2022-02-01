package com.company.companyadda.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.company.companyadda.Activities.Fill_form;
import com.company.companyadda.R;


public class GST_first extends Fragment {


    private CardView cardView_started;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.gst_first, container, false);

        cardView_started=view.findViewById(R.id.cardView_start);

        cardView_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), Fill_form.class);
                intent.putExtra("pos",String.valueOf(30));
                getActivity().startActivity(intent);
            }
        });


        return  view;
    }


}