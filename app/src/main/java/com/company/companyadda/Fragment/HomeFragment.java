package com.company.companyadda.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.Activities.CheckEfileStatus;
import com.company.companyadda.Activities.CheckPF;
import com.company.companyadda.Activities.City_select;
import com.company.companyadda.Activities.CompanyRegister;
import com.company.companyadda.Activities.Company_search;
import com.company.companyadda.Activities.CovidIndia;
import com.company.companyadda.Activities.Details;
import com.company.companyadda.Activities.DocUpload;
import com.company.companyadda.Activities.GST;
import com.company.companyadda.Activities.IncomeTaxFilling;
import com.company.companyadda.Activities.LLP;
import com.company.companyadda.Activities.LockScreen;
import com.company.companyadda.Activities.Login;
import com.company.companyadda.Activities.My_request;
import com.company.companyadda.Activities.New_service;
import com.company.companyadda.Activities.Otp;
import com.company.companyadda.Activities.PanValid;
import com.company.companyadda.Activities.PanValidation;
import com.company.companyadda.Activities.Payment;
import com.company.companyadda.Activities.ROC;
import com.company.companyadda.Activities.RTI;
import com.company.companyadda.Activities.SearchCompmayList;
import com.company.companyadda.Activities.TDS_Return_Filling;
import com.company.companyadda.Adapter.City_list_adapter;
import com.company.companyadda.Adapter.Search_Com_list_adapter;
import com.company.companyadda.Adapter.SliderAdapterExample;
import com.company.companyadda.Adapter.dashboard_list_adapter;
import com.company.companyadda.ApiModels.AllDetaislModels;
import com.company.companyadda.ApiModels.CompanySearchListPojoModel;
import com.company.companyadda.ApiModels.CovidPojoModl;
import com.company.companyadda.ApiModels.LoginModels;
import com.company.companyadda.ApiModels.SliderItem;
import com.company.companyadda.Pojo.BannerPojo;
import com.company.companyadda.Pojo.ComapnySearchPOjo;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.R;
import com.company.companyadda.api_interface.Search_click_Interface;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.google.gson.Gson;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment implements Search_click_Interface {

    RecyclerView recycleView_dash_main;
    dashboard_list_adapter adapterdas;
    ArrayList<Integer> image_list = new ArrayList<>();
    ArrayList<String> name_list = new ArrayList<>();
    private List<ServiceListPojo> service_lists_main;
    private SharedPreferences sharedPreferences_login;
    private SliderView sliderView;
    private SliderAdapterExample adapter;
    List<BannerPojo> arrayList_banner = new ArrayList<>();
    ArrayList<String> arrayList_search = new ArrayList<>();
    int searchVariable = 0;
    private HTextView textView_search;
    CardView cardView_new_request, cardView_my_request, cardView_payment,cardView_company_search;
    EditText editText_search;
    RadioGroup radio_Group;
    private RadioButton radio_dirctor,radio_company,radio_llp;
    private int selectedId;
    private String radio_select="Company";
    LinearLayout search_list_linearLayout;
    private RequestQueue mRequestQueue;
    private Search_Com_list_adapter search_Com_list_adapter;
    private RecyclerView recycleView_search_com;
    private ArrayList<ComapnySearchPOjo> list=new ArrayList<>();
    ProgressBar progress_bar;
    ProgressBar progress_bar_s;
    CardView  cardView_doc_lock,cardView_check_pf,cardView_cibal_check;
    CardView  cardView_doc_pvt_reg,cardView_check_llp_reg,cardView_opc_reg,
            cardView_gst_filling,cardView_roc,cardView_itr_filling,cardView_tds_file,cardView_check_e_status;

    HorizontalScrollView hori_scroll;

    LinearLayout linear_layout_covid_report;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sharedPreferences_login = getActivity().getSharedPreferences("login_details", MODE_PRIVATE);

        new Thread(runnable).start();

        sliderView = view.findViewById(R.id.imageSlider);
        linear_layout_covid_report = view.findViewById(R.id.linear_layout_covid_report);
        textView_search = (HTextView) view.findViewById(R.id.textView_search);

        cardView_new_request = view.findViewById(R.id.cardView_new_request);
        cardView_my_request = view.findViewById(R.id.cardView_my_request);
        cardView_payment = view.findViewById(R.id.cardView_payment);
        cardView_company_search = view.findViewById(R.id.cardView_company_search);
        editText_search = view.findViewById(R.id.editText_search);
        radio_Group = view.findViewById(R.id.radio_Group);
        search_list_linearLayout = view.findViewById(R.id.search_list_linearLayout);
        search_list_linearLayout.setVisibility(View.GONE);

        radio_dirctor = view.findViewById(R.id.radio_dirctor);
        radio_company = view.findViewById(R.id.radio_company);
        radio_llp = view.findViewById(R.id.radio_llp);
        recycleView_search_com = view.findViewById(R.id.recycleView_search_com);
        progress_bar = view.findViewById(R.id.progress_bar);
        progress_bar_s = view.findViewById(R.id.progress_bar_s);

        cardView_doc_lock = view.findViewById(R.id.cardView_doc_lock);
        cardView_check_pf = view.findViewById(R.id.cardView_check_pf);
        cardView_cibal_check = view.findViewById(R.id.cardView_cibal_check);



        cardView_doc_pvt_reg = view.findViewById(R.id.cardView_doc_pvt_reg);
        cardView_check_llp_reg = view.findViewById(R.id.cardView_check_llp_reg);
        cardView_opc_reg = view.findViewById(R.id.cardView_opc_reg);
        cardView_gst_filling = view.findViewById(R.id.cardView_gst_filling);
        cardView_roc = view.findViewById(R.id.cardView_roc);
        cardView_itr_filling = view.findViewById(R.id.cardView_itr_filling);


        cardView_tds_file = view.findViewById(R.id.cardView_tds_file);
        cardView_check_e_status = view.findViewById(R.id.cardView_check_e_status);

        linear_layout_covid_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CovidIndia.class));

            }
        });

        cardView_tds_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TDS_Return_Filling.class));

            }
        });

        cardView_check_e_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CheckEfileStatus.class));

            }
        });


        cardView_doc_pvt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CompanyRegister.class));
            }
        });

        cardView_check_llp_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LLP.class));
            }
        });

        cardView_opc_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Details.class));
            }
        });

        cardView_gst_filling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GST.class));
            }
        });

        cardView_roc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ROC.class));
            }
        });

        cardView_itr_filling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), IncomeTaxFilling .class));
            }
        });

        ////////////////////////////////////////

        cardView_doc_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LockScreen.class));
            }
        });

        cardView_check_pf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CheckPF.class));

            }
        });

        cardView_cibal_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PanValid.class));

            }
        });


        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    search_list_linearLayout.setVisibility(View.VISIBLE);
                    getSearchCompany();
                }else {
                    search_list_linearLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        radio_company.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editText_search.setHint("Enter Company Name");
                    radio_select=radio_company.getText().toString();

                }
            }
        });

        radio_llp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editText_search.setHint("Enter Company Name");
                    radio_select=radio_company.getText().toString();

                }
            }
        });

        radio_dirctor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editText_search.setHint("Enter Company Name");
                    radio_select=radio_dirctor.getText().toString();

                }
            }
        });

            textView_search.setAnimateType(HTextViewType.TYPER);
        textView_search.animateText("Search for any legal service");

        /*arrayList_banner.add(R.drawable.banner1);
        arrayList_banner.add(R.drawable.banner2);
        arrayList_banner.add(R.drawable.banner3);
        arrayList_banner.add(R.drawable.banner1);
        arrayList_banner.add(R.drawable.banner2);*/


        arrayList_search.add("Search here for Private Limited Registration");
        arrayList_search.add("Search here for LLP  Registration");
        arrayList_search.add("Search here for One person company");

        changeText();


        image_list.add(R.drawable.com_reg_f);
        image_list.add(R.drawable.llp_f);
        image_list.add(R.drawable.one_person_f);
        image_list.add(R.drawable.gst_f);
        image_list.add(R.drawable.roc_filling_f);
        image_list.add(R.drawable.tax_return_f);


        name_list.add("PVT Reg");
        name_list.add("LLP Reg");
        name_list.add("OPC Reg");
        name_list.add("GST Filing");
        name_list.add("Roc Filing");
        name_list.add("ITR Filing");


        recycleView_dash_main = view.findViewById(R.id.recycleView_dash_main);


        recycleView_dash_main.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapterdas = new dashboard_list_adapter(getActivity(), name_list, image_list);
        recycleView_dash_main.setAdapter(adapterdas);




        cardView_new_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), New_service.class));
            }
        });

        cardView_my_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), My_request.class));
            }
        });

        cardView_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Payment.class));
            }
        });

        cardView_company_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_search.getText().toString().length()>0) {

                    onSearchClickclass();

                    Intent intent = new Intent(getActivity(), SearchCompmayList.class);
                    intent.putExtra("radio", radio_select);
                    intent.putExtra("data", editText_search.getText().toString().trim());
                    startActivity(intent);



                }else {
                    editText_search.setError("Enter CIN Number");
                }
            }
        });

        isStoragePermissionGranted();

       // getCovidDetails();

        return view;
    }


    void changeText() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //textView_search.setText(arrayList_search.get(searchVariable));
                textView_search.setAnimateType(HTextViewType.TYPER);
                textView_search.animateText(arrayList_search.get(searchVariable));


                changeText();


            }
        }, 5000);

        if (searchVariable == 2) {
            searchVariable = 0;
        } else {
            searchVariable++;

        }
    }


    public void searchClick(View view) {
        startActivity(new Intent(getActivity(), New_service.class));
    }



    void   getSearchCompany(){

        progress_bar.setVisibility(View.VISIBLE);
        progress_bar_s.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(getActivity());

        String url = "https://api.finanvo.in/search/company?query="+editText_search.getText().toString();
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

                        recycleView_search_com.setLayoutManager(new LinearLayoutManager(getActivity() ));
                        search_Com_list_adapter = new Search_Com_list_adapter(getActivity(),list,radio_select,HomeFragment.this);
                        recycleView_search_com.setAdapter(search_Com_list_adapter);


                      //  Toast.makeText(getActivity(), "Response"+response.toString(), Toast.LENGTH_SHORT).show();

                        progress_bar.setVisibility(View.GONE);
                        progress_bar_s.setVisibility(View.GONE);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getActivity(), "Try Again!!!", Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);
                        progress_bar_s.setVisibility(View.GONE);

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
                params.put("query", editText_search.getText().toString());
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);

    }


    void   getCovidDetails(){

        mRequestQueue = Volley.newRequestQueue(getActivity());

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

                        Toast.makeText(getActivity(), "Success"+covidPojoModl.getCases(), Toast.LENGTH_SHORT).show();


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(getActivity(), "Try Again!!!"+error, Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);
                        progress_bar_s.setVisibility(View.GONE);

                    }
                });

        mRequestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onSearchClick() {
        list.clear();
        search_list_linearLayout.setVisibility(View.GONE);
        editText_search.setText("");

    }

    public void onSearchClickclass() {
        list.clear();
        search_list_linearLayout.setVisibility(View.GONE);
        editText_search.setText("");

    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                //  Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }


    Runnable runnable= new Runnable() {
        @Override
        public void run() {

            Call<AllDetaislModels> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .AllDetails(sharedPreferences_login.getString("phone",""));

            myProductsCall.enqueue(new Callback<AllDetaislModels>() {
                @Override
                public void onResponse(Call<AllDetaislModels> call, retrofit2.Response<AllDetaislModels> response) {
                    if (response.body() != null) {
                        AllDetaislModels loginModels = response.body();
                        if (loginModels.getResult().getSTATUS().equals("true")) {

                            String con_email=loginModels.getPayments_Keys().getContact_us_email();
                            String con_phone=loginModels.getPayments_Keys().getContact_us_mobile();

                            sharedPreferences_login.edit().putString("con_email",con_email).apply();
                            sharedPreferences_login.edit().putString("con_phone",con_phone).apply();

                            arrayList_banner=loginModels.getBanner_Details();


                            adapter = new SliderAdapterExample(getActivity());
                            sliderView.setSliderAdapter(adapter);
                            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                            sliderView.setIndicatorSelectedColor(Color.WHITE);
                            sliderView.setIndicatorUnselectedColor(Color.GRAY);
                            sliderView.setScrollTimeInSec(3);
                            sliderView.setAutoCycle(true);
                            sliderView.startAutoCycle();


                            sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                                @Override
                                public void onIndicatorClicked(int position) {
                                }
                            });

                            List<SliderItem> sliderItemList = new ArrayList<>();
                            //dummy data

                            for (int i = 0; i < arrayList_banner.size(); i++) {
                                SliderItem sliderItem = new SliderItem();
                                sliderItem.setImageUrl(arrayList_banner.get(i).getBanner_image());
                                sliderItemList.add(sliderItem);
                            }

                            adapter.renewItems(sliderItemList);


                            sharedPreferences_login.edit().putString("razorpay_key_id",loginModels.getPayments_Keys().getRazorpay_key_id()).apply();
                            sharedPreferences_login.edit().putString("razorpay_key_secret",loginModels.getPayments_Keys().getRazorpay_key_secret()).apply();


                        } else if (loginModels.getResult().getSTATUS().equals("false")) {
                            // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();
                            Log.d("tag","empty");
                            progress_bar.setVisibility(View.GONE);
                        }
                    } else if (response.body() == null) {
                        //  Toast.makeText(Address_Activity.this, "null", Toast.LENGTH_SHORT).show();

                        Log.d("tag","null");

                    }
                }

                @Override
                public void onFailure(Call<AllDetaislModels> call, Throwable t) {


                    Log.d("tag",t.toString());

                }
            });



        }
    };
}