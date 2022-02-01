package com.company.companyadda.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.Adapter.CompanyResAdapter;
import com.company.companyadda.Adapter.NewListAdapter;
import com.company.companyadda.Adapter.NewsFragmentAdapter;
import com.company.companyadda.ApiModels.NewListModel;
import com.company.companyadda.Pojo.ArticlesListPojo;
import com.company.companyadda.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class NewsContainer extends Fragment {

    private RequestQueue mRequestQueue;
    RecyclerView recycleView_news_list;
    private NewListAdapter news_list_adapter;
    private ArrayList<ArticlesListPojo> list;
    ProgressBar progress_bar;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8;
    SwipeRefreshLayout refreshLayout;
    private String type_cat="NATION";
    TextView textView_1,textView_2,textView_3,textView_4,textView_5,textView_6,textView_7,textView_8;

     Translator englishGermanTranslator;

     LinearLayout linear_layout_eng,linear_layout_hin;
    private boolean is_hindi=false;
    TextView textView_eng,textView_hindi;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_news_container, container, false);

        recycleView_news_list=view.findViewById(R.id.recycleView_news_list);
        progress_bar=view.findViewById(R.id.progress_bar);
        refreshLayout=view.findViewById(R.id.refreshLayout);

        progress_bar.setVisibility(View.VISIBLE);




        textView_eng=view.findViewById(R.id.textView_eng);
        textView_hindi=view.findViewById(R.id.textView_hindi);


        textView_1=view.findViewById(R.id.textView_1);
        textView_2=view.findViewById(R.id.textView_2);
        textView_3=view.findViewById(R.id.textView_3);
        textView_4=view.findViewById(R.id.textView_4);
        textView_5=view.findViewById(R.id.textView_5);
        textView_6=view.findViewById(R.id.textView_6);
        textView_7=view.findViewById(R.id.textView_7);
        textView_8=view.findViewById(R.id.textView_8);





        cardView1=view.findViewById(R.id.cardView1);
        cardView2=view.findViewById(R.id.cardView2);
        cardView3=view.findViewById(R.id.cardView3);
        cardView4=view.findViewById(R.id.cardView4);
        cardView5=view.findViewById(R.id.cardView5);
        cardView6=view.findViewById(R.id.cardView6);
        cardView7=view.findViewById(R.id.cardView7);
        cardView8=view.findViewById(R.id.cardView8);



        linear_layout_eng=view.findViewById(R.id.linear_layout_eng);
        linear_layout_hin=view.findViewById(R.id.linear_layout_hin);


        getSearchCompany("NATION");



        linear_layout_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_layout_eng.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                linear_layout_hin.setBackgroundColor(getResources().getColor(R.color.yellow));
                textView_hindi.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_eng.setTextColor(getResources().getColor(R.color.white));
                is_hindi=false;

                getSearchCompany(type_cat);
            }
        });

        linear_layout_hin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linear_layout_hin.setBackgroundColor(getResources().getColor(R.color.dark_blue));
                linear_layout_eng.setBackgroundColor(getResources().getColor(R.color.yellow));

                textView_hindi.setTextColor(getResources().getColor(R.color.white));
                textView_eng.setTextColor(getResources().getColor(R.color.dark_blue));
                is_hindi=true;

                getSearchCompany(type_cat);


            }
        });


        cardView2.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
        textView_2.setTextColor(getResources().getColor(R.color.white));





        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSearchCompany("WORLD");
                type_cat="WORLD";


                cardView1.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                cardView2.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView4.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView5.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView6.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView7.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView8.setCardBackgroundColor(getResources().getColor(R.color.yellow));


                textView_1.setTextColor(getResources().getColor(R.color.white));
                textView_2.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_3.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_4.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_5.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_6.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_7.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_8.setTextColor(getResources().getColor(R.color.dark_blue));

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSearchCompany("NATION");
                type_cat="NATION";

                textView_1.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_2.setTextColor(getResources().getColor(R.color.white));
                textView_3.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_4.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_5.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_6.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_7.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_8.setTextColor(getResources().getColor(R.color.dark_blue));

                cardView1.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView2.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                cardView3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView4.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView5.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView6.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView7.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView8.setCardBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSearchCompany("BUSINESS");
                type_cat="BUSINESS";

                textView_1.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_2.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_3.setTextColor(getResources().getColor(R.color.white));
                textView_4.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_5.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_6.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_7.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_8.setTextColor(getResources().getColor(R.color.dark_blue));


                cardView1.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView2.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView3.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                cardView4.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView5.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView6.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView7.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView8.setCardBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchCompany("TECHNOLOGY");
                type_cat="TECHNOLOGY";

                textView_1.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_2.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_3.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_4.setTextColor(getResources().getColor(R.color.white));
                textView_5.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_6.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_7.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_8.setTextColor(getResources().getColor(R.color.dark_blue));

                cardView1.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView2.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView4.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                cardView5.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView6.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView7.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView8.setCardBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchCompany("ENTERTAINMENT");
                type_cat="ENTERTAINMENT";


                textView_1.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_2.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_3.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_4.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_5.setTextColor(getResources().getColor(R.color.white));
                textView_6.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_7.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_8.setTextColor(getResources().getColor(R.color.dark_blue));

                cardView1.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView2.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView4.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView5.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                cardView6.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView7.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView8.setCardBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchCompany("SCIENCE");
                type_cat="SCIENCE";

                textView_1.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_2.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_3.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_4.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_5.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_6.setTextColor(getResources().getColor(R.color.white));
                textView_7.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_8.setTextColor(getResources().getColor(R.color.dark_blue));

                cardView1.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView2.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView4.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView5.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView6.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                cardView7.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView8.setCardBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchCompany("SPORTS");
                type_cat="SPORTS";


                textView_1.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_2.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_3.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_4.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_5.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_6.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_7.setTextColor(getResources().getColor(R.color.white));
                textView_8.setTextColor(getResources().getColor(R.color.dark_blue));


                cardView1.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView2.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView4.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView5.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView6.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView7.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
                cardView8.setCardBackgroundColor(getResources().getColor(R.color.yellow));
            }
        });
        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getSearchCompany("HEALTH");
                type_cat="HEALTH";


                textView_1.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_2.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_3.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_4.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_5.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_6.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_7.setTextColor(getResources().getColor(R.color.dark_blue));
                textView_8.setTextColor(getResources().getColor(R.color.white));


                cardView1.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView2.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView4.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView5.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView6.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView7.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                cardView8.setCardBackgroundColor(getResources().getColor(R.color.dark_blue));
            }
        });




       /* tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Cricket"));
        tabLayout.addTab(tabLayout.newTab().setText("Sensex"));
        tabLayout.addTab(tabLayout.newTab().setText("Astrology"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final NewsFragmentAdapter adapter = new NewsFragmentAdapter(getActivity(),getActivity().getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });*/



        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSearchCompany(type_cat);
            }
        });

        return  view;
    }

    void   getSearchCompany(String topic){

        progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(getActivity());

        String url = "https://google-news1.p.rapidapi.com/topic-headlines?topic="+topic+"&country=IN&lang=en&limit=50&media=true";
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
                        news_list_adapter = new NewListAdapter(getActivity(),list,is_hindi);
                        recycleView_news_list.setAdapter(news_list_adapter);


                        //  Toast.makeText(getActivity(), "Response"+response.toString(), Toast.LENGTH_SHORT).show();

                         progress_bar.setVisibility(View.GONE);
                        refreshLayout.setRefreshing(false);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getActivity(), " error"+error, Toast.LENGTH_SHORT).show();
                         progress_bar.setVisibility(View.GONE);
                        refreshLayout.setRefreshing(false);

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
        }; jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);

    }

}