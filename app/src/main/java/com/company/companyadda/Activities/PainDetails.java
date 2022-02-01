package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.ApiModels.PanDetailsModel;
import com.company.companyadda.R;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class PainDetails extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private ProgressBar progress_bar;
    private String name;
    private String num;
    private String sta;
    private String cat;

    private String f_name;
    private String l_name;
    private String ad_sta;
    private String last_up;

    private TextView textView_f_name;
    private TextView textView_l_name;
    private TextView textView_ad_status;
    private TextView textView_last_up;

    TextView textView_pan_number,textView_name,textView_status,textView_cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pain_details);


        progress_bar=findViewById(R.id.progress_bar);
        textView_pan_number=findViewById(R.id.textView_pan_number);
        textView_name=findViewById(R.id.textView_name);
        textView_status=findViewById(R.id.textView_status);
        textView_cat=findViewById(R.id.textView_cat);

        textView_f_name=findViewById(R.id.textView_f_name);
        textView_l_name=findViewById(R.id.textView_l_name);
        textView_ad_status=findViewById(R.id.textView_ad_status);
        textView_last_up=findViewById(R.id.textView_last_up);


         name=getIntent().getStringExtra("nam");
         num=getIntent().getStringExtra("num");
        sta=getIntent().getStringExtra("sta");
        cat=getIntent().getStringExtra("cat");


        f_name=getIntent().getStringExtra("f_name");
        l_name=getIntent().getStringExtra("l_name");
        ad_sta=getIntent().getStringExtra("add");
        last_up=getIntent().getStringExtra("las");


        textView_pan_number.setText(num);
        textView_name.setText(name);
        textView_status.setText(sta);
        textView_cat.setText(cat);

        textView_f_name.setText(f_name);
        textView_l_name.setText(l_name);
        textView_ad_status.setText(ad_sta);
        textView_last_up.setText(last_up);



    }

    public void back(View view) {
        finish();
    }

    public void send_home(View view) {


        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        PainDetails.this.finish();
    }



}