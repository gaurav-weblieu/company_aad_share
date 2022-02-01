package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.Adapter.ConteintListAdapter;
import com.company.companyadda.Adapter.NewListAdapter;
import com.company.companyadda.ApiModels.ContientPojoModel;
import com.company.companyadda.ApiModels.NewListModel;
import com.company.companyadda.Pojo.AllcontientPojo;
import com.company.companyadda.Pojo.ArticlesListPojo;
import com.company.companyadda.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConteientList extends AppCompatActivity {

    RecyclerView recycleView_content_list;
    ProgressBar progress_bar;
    private RequestQueue mRequestQueue;
    private List<AllcontientPojo> list;
    ConteintListAdapter conteintListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteient_list);


        recycleView_content_list=findViewById(R.id.recycleView_content_list);
        progress_bar=findViewById(R.id.progress_bar);

        getSearchCompany();


    }

    void   getSearchCompany(){

        progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(ConteientList.this);


        String url = "https://corona.lmao.ninja/v2/continents?yesterday&sort";

        // prepare the Request
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response) {




                        ArrayList<AllcontientPojo> res = new ArrayList<>();
                        for (int i = 0; i < response.length(); ++i)
                        {
                            JSONObject beacon = null;
                            try {
                                beacon = response.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                assert beacon != null;
                                res.add(new AllcontientPojo(
                                        beacon.getString("updated"),
                                        beacon.getString("cases"),
                                        beacon.getString("todayCases"),
                                        beacon.getString("deaths"),
                                        beacon.getString("todayDeaths"),
                                        beacon.getString("recovered"),
                                        beacon.getString("todayRecovered"),
                                        beacon.getString("active"),
                                        beacon.getString("critical"),
                                        beacon.getString("casesPerOneMillion"),
                                        beacon.getString("deathsPerOneMillion"),
                                        beacon.getString("tests"),
                                        beacon.getString("testsPerOneMillion"),
                                        beacon.getString("population"),
                                        beacon.getString("continent"),
                                        beacon.getString("activePerOneMillion"),
                                        beacon.getString("recoveredPerOneMillion"),
                                        beacon.getString("criticalPerOneMillion")
                                        ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        recycleView_content_list.setLayoutManager(new LinearLayoutManager(ConteientList.this ));
                        conteintListAdapter = new ConteintListAdapter(ConteientList.this,res);
                        recycleView_content_list.setAdapter(conteintListAdapter);


                        //  Toast.makeText(getActivity(), "Response"+response.toString(), Toast.LENGTH_SHORT).show();

                        progress_bar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ConteientList.this, " error"+error, Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);
                        Log.e("error",error.toString());
                    }
                }
        );

        getRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(getRequest);


    }

    public void back(View view) {
        finish();
    }


    public void send_home(View view) {


        Intent intent = new Intent(this, DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        ConteientList.this.finish();



    }


}