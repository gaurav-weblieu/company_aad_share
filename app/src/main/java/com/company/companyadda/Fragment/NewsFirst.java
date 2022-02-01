package com.company.companyadda.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.Adapter.NewListAdapter;
import com.company.companyadda.ApiModels.NewListModel;
import com.company.companyadda.Pojo.ArticlesListPojo;
import com.company.companyadda.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class NewsFirst extends Fragment {

    private RequestQueue mRequestQueue;
    RecyclerView recycleView_news_list;
    private NewListAdapter news_list_adapter;
    private ArrayList<ArticlesListPojo> list;
    private boolean is_hindi=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_news_first, container, false);

        recycleView_news_list=view.findViewById(R.id.recycleView_news_list);






       // String string = holder.textView_title.getText().toString();

        getSearchCompany();
        return view;
    }


    void   getSearchCompany(){

        //progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(getActivity());

        String url = "https://google-news1.p.rapidapi.com/topic-headlines?topic=NATION&country=IN&lang=en&limit=50&media=true";
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
                        NewListModel newListModel;
                        newListModel = gson.fromJson(response.toString(),NewListModel.class);

                        list= (ArrayList<ArticlesListPojo>) newListModel.getArticles();

                        recycleView_news_list.setLayoutManager(new LinearLayoutManager(getActivity() ));
                        news_list_adapter = new NewListAdapter(getActivity(),list, is_hindi);
                        recycleView_news_list.setAdapter(news_list_adapter);


                        //  Toast.makeText(getActivity(), "Response"+response.toString(), Toast.LENGTH_SHORT).show();

                      //  progress_bar.setVisibility(View.GONE);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getActivity(), " error"+error, Toast.LENGTH_SHORT).show();
                       // progress_bar.setVisibility(View.GONE);

                    }
                })

        {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("x-rapidapi-host", "google-news1.p.rapidapi.com");
                paramss.put("x-rapidapi-key", "1a0e6ced18mshea6371c24c8bab3p184517jsn3b522218742f");
                return paramss;
            }
            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
/*
                params.put("query", editText_search.getText().toString());
*/
                return params;
            }
        };
        mRequestQueue.add(jsonObjectRequest);

    }




}