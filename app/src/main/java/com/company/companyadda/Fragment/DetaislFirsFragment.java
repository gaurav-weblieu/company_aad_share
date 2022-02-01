package com.company.companyadda.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.company.companyadda.Activities.Details;
import com.company.companyadda.Activities.Fill_form;
import com.company.companyadda.Activities.Notification;
import com.company.companyadda.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class DetaislFirsFragment extends Fragment {


    private CardView cardView_started;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_detaisl_firs, container, false);

        cardView_started=view.findViewById(R.id.cardView_start);

        cardView_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), Fill_form.class);
                intent.putExtra("pos",String.valueOf(2));
                getActivity().startActivity(intent);
            }
        });


        return  view;
    }




}