package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.Adapter.Search_Com_list_adapter;
import com.company.companyadda.Adapter.Search_Screen_Com_list_adapter;
import com.company.companyadda.ApiModels.CompanySearchListPojoModel;
import com.company.companyadda.Pojo.ComapnySearchPOjo;
import com.company.companyadda.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCompmayList extends AppCompatActivity {


    private Search_Screen_Com_list_adapter search_Com_list_adapter;
    private RecyclerView recycleView_search_com;
    private List<ComapnySearchPOjo> list;
    private String radio_select;
    private RequestQueue mRequestQueue;
    private String data;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_compmay_list);

        recycleView_search_com=findViewById(R.id.recycleView_search_com);
        progress_bar=findViewById(R.id.progress_bar);

        radio_select=getIntent().getStringExtra("radio");
        data=getIntent().getStringExtra("data");


        getSearchCompany();

    }


    void   getSearchCompany(){


        progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.finanvo.in/search/company?query="+data;
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
                        CompanySearchListPojoModel companyDetailsPojoClass;
                        companyDetailsPojoClass = gson.fromJson(response.toString(),CompanySearchListPojoModel.class);

                        list= (ArrayList<ComapnySearchPOjo>) companyDetailsPojoClass.getData();

                        recycleView_search_com.setLayoutManager(new LinearLayoutManager(SearchCompmayList.this));
                        search_Com_list_adapter = new Search_Screen_Com_list_adapter(SearchCompmayList.this,list,radio_select);
                        recycleView_search_com.setAdapter(search_Com_list_adapter);

                        //  Toast.makeText(getActivity(), "Response"+response.toString(), Toast.LENGTH_SHORT).show();

                         progress_bar.setVisibility(View.GONE);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(SearchCompmayList.this, " error"+error, Toast.LENGTH_SHORT).show();
                         progress_bar.setVisibility(View.GONE);

                    }
                })

        {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("x-api-key", "j9g1z0f92C");
                paramss.put("x-api-secret-key", "sVgSRmUs54i56tcBIsLjgd1trdLsSPbZCZKiRu5c");
                return paramss;
            }
            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("query", data);
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);

    }

    public void back(View view) {
        finish();
    }

    public void send_home(View view) {
        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        SearchCompmayList.this.finish();
    }
}