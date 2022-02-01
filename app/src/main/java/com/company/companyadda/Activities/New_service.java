package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.company.companyadda.Adapter.City_list_adapter;
import com.company.companyadda.Adapter.MySimpleArrayAdapter;
import com.company.companyadda.Adapter.Search_list_adapter;
import com.company.companyadda.ApiModels.SearchServiceApiModel;
import com.company.companyadda.Pojo.SearchServicePoJo;
import com.company.companyadda.Pojo.SearchServicePoJoService;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.R;
import com.company.companyadda.ViewModel.Category_View_Model;
import com.company.companyadda.api_service.RetrofitServicesHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class New_service extends AppCompatActivity {

    private AutoCompleteTextView autocomplete;
    Category_View_Model model;
    ArrayList<String> arrayList_service_name=new ArrayList<>();
    ArrayList<String> arrayList_service_id=new ArrayList<>();
    private List<ServiceListPojo> service_lists_main;
    private String service_id;
    ArrayAdapter<String> adapter;
    RecyclerView recycleView_service,recycleView_service1,recycleView_service2;
    RecyclerView recycleView_service3,recycleView_service4,recycleView_service5,recycleView_service6;
    Search_list_adapter search_list_adapter;
    ProgressBar progress_bar;
    TextView textView_city_name;
     SharedPreferences sharedPreferences_login;
    private String city;
    Parcelable recyclerViewState;

    ListView listView;

    public List<SearchServicePoJo> category;
    public List<SearchServicePoJoService> START_REGISTRATION;
    public List<SearchServicePoJoService> LICENSE;
    public List<SearchServicePoJoService> TAX;
    public List<SearchServicePoJoService> GST;
    public List<SearchServicePoJoService> ROC_FILING;
    public List<SearchServicePoJoService> LLP;
    public List<SearchServicePoJoService> INVESTMENT_TAX_PLANNING;

    LinearLayout linearLayout_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);


        city=sharedPreferences_login.getString("city","");



        String[] arr = { "Paries,France", "PA,United States","Parana,Brazil",
                "Padua,Italy", "Pasadena,CA,United States"};

        autocomplete = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView1);


        recycleView_service=findViewById(R.id.recycleView_service);
        recycleView_service1=findViewById(R.id.recycleView_service1);
        recycleView_service2=findViewById(R.id.recycleView_service2);
        recycleView_service3=findViewById(R.id.recycleView_service3);
        recycleView_service4=findViewById(R.id.recycleView_service4);
        recycleView_service5=findViewById(R.id.recycleView_service5);
        recycleView_service6=findViewById(R.id.recycleView_service6);


        linearLayout_list=findViewById(R.id.linearLayout_list);
        linearLayout_list.setVisibility(View.GONE);


        progress_bar=findViewById(R.id.progress_bar);
        textView_city_name=findViewById(R.id.textView_city_name);

        progress_bar.setVisibility(View.VISIBLE);

        textView_city_name.setText(city);




       /* ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, arr);

        autocomplete.setAdapter(adapter);*/


        model = new ViewModelProvider(this).get(Category_View_Model.class);
        model.getAllService().observe(this, new Observer<List<ServiceListPojo>>() {
            @Override
            public void onChanged(List<ServiceListPojo> address_lists) {
                if (address_lists!=null){
                    service_lists_main=address_lists;
                    for (int i=0;i<service_lists_main.size();i++){
                        arrayList_service_name.add(service_lists_main.get(i).getService_name());
                        arrayList_service_id.add(service_lists_main.get(i).getService_id());
                    }



                    MySimpleArrayAdapter mySimpleArrayAdapter =
                            new MySimpleArrayAdapter(service_lists_main,New_service.this);


                    progress_bar.setVisibility(View.GONE);

                    adapter = new ArrayAdapter<String>
                            (New_service.this,android.R.layout.select_dialog_item, arrayList_service_name);

                    autocomplete.setAdapter(adapter);

                    //  Toast.makeText(Fill_form.this, "size"+address_lists.size(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(New_service.this, "null data main", Toast.LENGTH_SHORT).show();
                }
            }
        });


        autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedText=adapter.getItem(position);

                int pos_hai= arrayList_service_name.indexOf(selectedText);
                Intent intent= new Intent(New_service.this,Fill_form.class);
                intent.putExtra("pos",String.valueOf(pos_hai));
                startActivity(intent);

            }
        });

        autocomplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    linearLayout_list.setVisibility(View.GONE);
                }else {
                    linearLayout_list.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        getService();
    }



    public void continueNewService(View view) {
        startActivity(new Intent(this, Details.class));
    }


    public void back(View view) {
        finish();
    }

    public void send_home(View view) {
        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        New_service.this.finish();
    }

    public void to_profile(View view) {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }


    void  getService(){

           category= new ArrayList<>();

            Call<SearchServiceApiModel> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .getSearchServices();

            myProductsCall.enqueue(new Callback<SearchServiceApiModel>() {
                @Override
                public void onResponse(Call<SearchServiceApiModel> call, Response<SearchServiceApiModel> response) {
                    if (response.body() != null) {
                        SearchServiceApiModel serviceModels = response.body();
                        if (!serviceModels.getCategory().isEmpty()) {


                            category=serviceModels.getCategory();
                            START_REGISTRATION=serviceModels.getSTART_REGISTRATION();
                            LICENSE=serviceModels.getLICENSE();
                            TAX=serviceModels.getTAX();
                            GST=serviceModels.getGST();
                            ROC_FILING=serviceModels.getROC_FILING();
                            LLP=serviceModels.getLLP();
                            INVESTMENT_TAX_PLANNING=serviceModels.getINVESTMENT_TAX_PLANNING();


                            recycleView_service.setLayoutManager(new LinearLayoutManager(New_service.this ));
                            search_list_adapter = new Search_list_adapter(New_service.this,START_REGISTRATION);
                            recycleView_service.setAdapter(search_list_adapter);

                            recycleView_service1.setLayoutManager(new LinearLayoutManager(New_service.this ));
                            search_list_adapter = new Search_list_adapter(New_service.this,LICENSE);
                            recycleView_service1.setAdapter(search_list_adapter);

                            recycleView_service2.setLayoutManager(new LinearLayoutManager(New_service.this ));
                            search_list_adapter = new Search_list_adapter(New_service.this,TAX);
                            recycleView_service2.setAdapter(search_list_adapter);

                            recycleView_service3.setLayoutManager(new LinearLayoutManager(New_service.this ));
                            search_list_adapter = new Search_list_adapter(New_service.this,GST);
                            recycleView_service3.setAdapter(search_list_adapter);

                            recycleView_service4.setLayoutManager(new LinearLayoutManager(New_service.this ));
                            search_list_adapter = new Search_list_adapter(New_service.this,ROC_FILING);
                            recycleView_service4.setAdapter(search_list_adapter);

                            recycleView_service5.setLayoutManager(new LinearLayoutManager(New_service.this ));
                            search_list_adapter = new Search_list_adapter(New_service.this,LLP);
                            recycleView_service5.setAdapter(search_list_adapter);

                            recycleView_service6.setLayoutManager(new LinearLayoutManager(New_service.this ));
                            search_list_adapter = new Search_list_adapter(New_service.this,INVESTMENT_TAX_PLANNING);
                            recycleView_service6.setAdapter(search_list_adapter);


                            linearLayout_list.setVisibility(View.VISIBLE);

                            Log.d("tag","Goog");


                        } else if (serviceModels.getCategory().isEmpty()) {
                            // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();

                            category=null;
                            Log.d("tag","empty");


                       /* subcategoryProductsDataBinding.subSecondLabelTextView.setVisibility(View.GONE);
                        subcategoryProductsDataBinding.subSecondRecyclerView.setVisibility(View.GONE);*/
                        }
                    } else if (response.body() == null) {
                        //  Toast.makeText(Address_Activity.this, "null", Toast.LENGTH_SHORT).show();

                        Log.d("tag","null");



                   /* subcategoryProductsDataBinding.subSecondLabelTextView.setVisibility(View.GONE);
                    subcategoryProductsDataBinding.subSecondRecyclerView.setVisibility(View.GONE);*/
                    }
                }

                @Override
                public void onFailure(Call<SearchServiceApiModel> call, Throwable t) {


                    Log.d("tag",t.toString());

                    //  Toast.makeText(Address_Activity.this, "EROOR "+t, Toast.LENGTH_SHORT).show();


                /*if (t.toString().contains("No address associated with hostname")){
                    Toast.makeText(Dashboard_Activity.this, "No Internet", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Dashboard_Activity.this,NoInternet.class));
                }*/
               /* subcategoryProductsDataBinding.subSecondLabelTextView.setVisibility(View.GONE);
                subcategoryProductsDataBinding.subSecondRecyclerView.setVisibility(View.GONE);*/
                }
            });


    }
}