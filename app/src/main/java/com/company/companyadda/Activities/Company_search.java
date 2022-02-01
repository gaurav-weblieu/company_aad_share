package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.Fragment.CompanyDetils;
import com.company.companyadda.Fragment.Director_Detaisl;
import com.company.companyadda.Pojo.AuthPojo;
import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.R;
import com.company.companyadda.ApiModels.CompanyDetailsPojoClass;
import com.company.companyadda.api_service.RetrofitServicesHandlerTwo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class Company_search extends AppCompatActivity {

    /*private RequestQueue mRequestQueue;
    private String auth_Key;
    TextView textView_name,textView_address,textView_cin_no;
    private String intent_no;
    private String intent_radio;*/

    private String intent_no;
    private String intent_radio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_search);

        intent_no=getIntent().getStringExtra("no");
        intent_radio=getIntent().getStringExtra("radio");

       /* textView_name=findViewById(R.id.textView_name);
        textView_address=findViewById(R.id.textView_address);
        textView_cin_no=findViewById(R.id.textView_cin_no);

         intent_no=getIntent().getStringExtra("no");
         intent_radio=getIntent().getStringExtra("radio");

        getAuth();*/

      //  openFragment(new CompanyDetils());

        if (intent_radio.equals("Company")){
            openFragment(new CompanyDetils());
        }else {
            openFragment(new Director_Detaisl());

        }

    }



    public void send_home(View view) {
        startActivity(new Intent(Company_search.this,DashBoard.class));

    }

    public void openFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putString("no", intent_no);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_company_search, fragment);
        transaction.commit();
    }

    public void back(View view) {
        finish();
    }

    public void noti(View view) {
        startActivity(new Intent(this,Notification.class));
    }

   /* void getDataWithRetro(){

        Call<CapitalResult> myProductsCall = RetrofitServicesHandlerTwo
                .getInstance()
                .getApi()
                .getCompany();

        myProductsCall.enqueue(new Callback<CapitalResult>() {
            @Override
            public void onResponse(Call<CapitalResult> call, retrofit2.Response<CapitalResult> response) {
                if (response.body() != null) {
                    CapitalResult serviceModels = response.body();

                    Toast.makeText(Company_search.this, "response"+response.toString(), Toast.LENGTH_SHORT).show();

                    *//*if (!serviceModels.getSTATUS()=="TRUE") {


                        Log.d("tag","Goog");


                    } else if (serviceModels.getService_list().isEmpty()) {
                        // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();
                    }*//*
                } else if (response.body() == null) {
                    Toast.makeText(Company_search.this, "null"+response.toString(), Toast.LENGTH_SHORT).show();

                    Log.d("tag","null");

                }
            }

            @Override
            public void onFailure(Call<CapitalResult> call, Throwable t) {


                Log.d("tag",t.toString());

                  Toast.makeText(Company_search.this, "EROOR "+t, Toast.LENGTH_SHORT).show();

            }
        });

    }


    void getAuth(){
            mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.sandbox.co.in/authenticate";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Gson gson = new Gson();
                        AuthPojo myBean;
                        myBean = gson.fromJson(response.toString(),AuthPojo.class);
                         auth_Key=myBean.getAccess_token();

                        Toast.makeText(Company_search.this, "Response"+auth_Key.toString(), Toast.LENGTH_SHORT).show();


                        if ("Company".equals(intent_radio)){
                            getCompanyDetails(auth_Key);

                        }else  if (intent_radio.equals("Director")){
                            getDirDetails(auth_Key);
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(Company_search.this, "Error "+error, Toast.LENGTH_SHORT).show();
                    }
                })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("x-api-key", "key_live_nGzBfPE6HDc33dA02B1JJPSDiRcKo0ju");
                paramss.put("x-api-secret", "secret_live_85An4Sq0De06R2vGwCQoMbOhyxqKqwBh");
                paramss.put("x-api-version", "3.1");
                return paramss;
            }

        };
        mRequestQueue.add(jsonObjectRequest);
    }


   void   getCompanyDetails(String auth_Key){

            mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.sandbox.co.in/mca/companies/"+intent_no+"?consent=Y&reason=Forproject";
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
                        CompanyDetailsPojoClass companyDetailsPojoClass;
                        companyDetailsPojoClass = gson.fromJson(response.toString(),CompanyDetailsPojoClass.class);

                        textView_name.setText(companyDetailsPojoClass.getData().getCompany_master_data().getCompany_name());
                        textView_address.setText(companyDetailsPojoClass.getData().getCompany_master_data().getRegistered_address());
                        textView_cin_no.setText(companyDetailsPojoClass.getData().getCompany_master_data().getCin());

                        Toast.makeText(Company_search.this, "Response"+response.toString(), Toast.LENGTH_SHORT).show();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(Company_search.this, " error"+error, Toast.LENGTH_SHORT).show();
                    }
                })

        {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("Authorization", auth_Key);
                paramss.put("x-api-key", "key_live_nGzBfPE6HDc33dA02B1JJPSDiRcKo0ju");
                paramss.put("x-api-version", "3.1");
                return paramss;
            }
            //Pass Your Parameters here
           *//* @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "U72900UP2020PTC125930");
                return params;
            }*//*
        };
        mRequestQueue.add(jsonObjectRequest);

   }

    void   getDirDetails(String auth_Key){

        mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.sandbox.co.in/mca/directors/"+intent_no+"?consent=Y&reason=Forproject";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        *//*Gson gson = new Gson();
                        CompanyDetailsPojoClass companyDetailsPojoClass;
                        companyDetailsPojoClass = gson.fromJson(response.toString(),CompanyDetailsPojoClass.class);

                        textView_name.setText(companyDetailsPojoClass.getData().getCompany_master_data().getCompany_name());
                        textView_address.setText(companyDetailsPojoClass.getData().getCompany_master_data().getRegistered_address());
                        textView_cin_no.setText(companyDetailsPojoClass.getData().getCompany_master_data().getCin());*//*

                        Toast.makeText(Company_search.this, "Response"+response.toString(), Toast.LENGTH_SHORT).show();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(Company_search.this, "Invalid CIN Number !", Toast.LENGTH_SHORT).show();
                    }
                })

        {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("Authorization", auth_Key);
                paramss.put("x-api-key", "key_live_nGzBfPE6HDc33dA02B1JJPSDiRcKo0ju");
                paramss.put("x-api-version", "3.1");
                return paramss;
            }
            //Pass Your Parameters here
          *//*  @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "U72900UP2020PTC125930");
                return params;
            }*//*
        };
        mRequestQueue.add(jsonObjectRequest);

    }*/




}