package com.company.companyadda.Reposetry;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;


import com.company.companyadda.ApiModels.ServiceModels;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.api_service.RetrofitServicesHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceList_Repo {


    public MutableLiveData<List<ServiceListPojo>> Service_list;

    public MutableLiveData<List<ServiceListPojo>> getService_list(){

        Service_list = new MutableLiveData<>();

        Call<ServiceModels> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .getServices();

        myProductsCall.enqueue(new Callback<ServiceModels>() {
            @Override
            public void onResponse(Call<ServiceModels> call, Response<ServiceModels> response) {
                if (response.body() != null) {
                    ServiceModels serviceModels = response.body();
                    if (!serviceModels.getService_list().isEmpty()) {

                        Service_list.postValue(serviceModels.getService_list());

                      /*  String[] address_id = new String[AddressList_response.getAddress().size()];
                        String[] user_id = new String[AddressList_response.getAddress().size()];
                        String[] name_on_address = new String[AddressList_response.getAddress().size()];
                        String[] address = new String[AddressList_response.getAddress().size()];
                        String[] pincode = new String[AddressList_response.getAddress().size()];
                        String[] landmark = new String[AddressList_response.getAddress().size()];
                        String[] note = new String[AddressList_response.getAddress().size()];
                        String[] created_at = new String[AddressList_response.getAddress().size()];

                        for (int i = 0; i < AddressList_response.getAddress().size(); i++) {
                            address_id[i] = AddressList_response.getAddress().get(i).getAddress_id();
                            user_id[i] = AddressList_response.getAddress().get(i).getUser_id();
                            name_on_address[i] = AddressList_response.getAddress().get(i).getName_on_address();
                            address[i] = AddressList_response.getAddress().get(i).getAddress();
                            pincode[i] = AddressList_response.getAddress().get(i).getPincode();
                            landmark[i] = AddressList_response.getAddress().get(i).getLandmark();
                            note[i] = AddressList_response.getAddress().get(i).getNote();
                            created_at[i] = AddressList_response.getAddress().get(i).getCreated_at();
                        }
                        for (int i = 0; i < AddressList_response.getAddress().size(); i++) {
                            address_list.add(new Address_list(
                                    address_id[i],
                                    user_id[i],
                                    name_on_address[i],
                                    address[i],
                                    pincode[i],
                                    landmark[i],
                                    note[i],
                                    created_at[i]
                            ));
                        }*/

                        Log.d("tag","Goog");


                    } else if (serviceModels.getService_list().isEmpty()) {
                        // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();

                        Service_list=null;
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
            public void onFailure(Call<ServiceModels> call, Throwable t) {


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

        return Service_list;
    }

}
