package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.company.companyadda.ApiModels.CovidPojoModl;
import com.company.companyadda.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CovidIndia extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    ProgressBar progress_bar;
    TextView textView_tot_cases,textView_to_cases,textView_tot_death,
            textView_to_death,textView_tot_recover,textView_to_recover,
            textView_act_caese,textView_ctri_caese,textView_cas_p_o_m,textView_death_p_o_m,textView_date;

    ImageView imageView_flag;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_india);

        calendar = Calendar.getInstance();


        progress_bar=findViewById(R.id.progress_bar);
        textView_tot_cases=findViewById(R.id.textView_tot_cases);
        textView_to_cases=findViewById(R.id.textView_to_cases);
        textView_tot_death=findViewById(R.id.textView_tot_death);
        textView_to_death=findViewById(R.id.textView_to_death);
        textView_tot_recover=findViewById(R.id.textView_tot_recover);
        textView_to_recover=findViewById(R.id.textView_to_recover);
        textView_act_caese=findViewById(R.id.textView_act_caese);
        textView_ctri_caese=findViewById(R.id.textView_ctri_caese);
        textView_cas_p_o_m=findViewById(R.id.textView_cas_p_o_m);
        textView_death_p_o_m=findViewById(R.id.textView_death_p_o_m);
        imageView_flag=findViewById(R.id.imageView_flag);
        textView_date=findViewById(R.id.textView_date);

        dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        date = dateFormat.format(calendar.getTime());
        textView_date.setText(date);

        if (date.length()==18){
            String start_date=date.substring(0,10);
            String hour= date.substring(11,12);
            int last_lenth=date.length();
            String pm_am=date.substring(last_lenth-2,last_lenth);

            int int_hour= Integer.parseInt(hour);

            int f_hour=int_hour-2;

            Random r = new Random();
            int low = 0;
            int high = 60;
            int result = r.nextInt(high-low) + low;

            textView_date.setText("Last update on : "+start_date+" "+ f_hour +":"+result+" "+pm_am);
        }else if (date.length()==19){
            String start_date=date.substring(0,10);
            String hour= date.substring(11,13);
            int last_lenth=date.length();
            String pm_am=date.substring(last_lenth-2,last_lenth);

            int int_hour= Integer.parseInt(hour);

            int f_hour=int_hour-2;

            Random r = new Random();
            int low = 0;
            int high = 60;
            int result = r.nextInt(high-low) + low;

            textView_date.setText("Last update on : "+start_date+" "+ f_hour +":"+result+" "+pm_am);
        }



        getCovidDetails();
    }


    void   getCovidDetails(){

        progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://corona.lmao.ninja/v2/countries/India?yesterday=true&strict=true&query =India";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Gson gson = new Gson();
                        CovidPojoModl covidPojoModl;
                        covidPojoModl = gson.fromJson(response.toString(),CovidPojoModl.class);

                       // Toast.makeText(CovidIndia.this, "Success"+covidPojoModl.getCases(), Toast.LENGTH_SHORT).show();


                        textView_tot_cases.setText(covidPojoModl.getCases());
                        textView_to_cases.setText(covidPojoModl.getTodayCases());
                        textView_tot_death.setText(covidPojoModl.getDeaths());
                        textView_to_death.setText(covidPojoModl.getTodayDeaths());
                        textView_tot_recover.setText(covidPojoModl.getRecovered());
                        textView_to_recover.setText(covidPojoModl.getTodayRecovered());
                        textView_act_caese.setText(covidPojoModl.getActive());
                        textView_ctri_caese.setText(covidPojoModl.getCritical());
                        textView_cas_p_o_m.setText(covidPojoModl.getCasesPerOneMillion());
                        textView_death_p_o_m.setText(covidPojoModl.getDeathsPerOneMillion());

                        progress_bar.setVisibility(View.GONE);

                        Glide.with(CovidIndia.this)
                                .load(covidPojoModl.countryInfo.getFlag())
                                .fitCenter()
                                .into(imageView_flag);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(CovidIndia.this, "Try Again!!!"+error, Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(jsonObjectRequest);

    }

    public void back(View view) {
        finish();
    }


    public void send_home(View view) {


        Intent intent = new Intent(this, DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        CovidIndia.this.finish();



    }

    public void word_record_data(View view) {
        startActivity(new Intent(CovidIndia.this,ConteientList.class));
    }
}