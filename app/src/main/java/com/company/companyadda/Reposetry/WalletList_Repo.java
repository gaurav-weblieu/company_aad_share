package com.company.companyadda.Reposetry;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.company.companyadda.ApiModels.ServiceModels;
import com.company.companyadda.ApiModels.WalletListModel;
import com.company.companyadda.Interface.WalletInterface;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.Pojo.Wallet_history_pojo;
import com.company.companyadda.api_service.RetrofitServicesHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletList_Repo {

   /* String User_id;


    public MutableLiveData<List<Wallet_history_pojo>> Service_list;

    public MutableLiveData<List<Wallet_history_pojo>> getWalletList(){

        Service_list = new MutableLiveData<>();

        Call<WalletListModel> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .getWalletList(User_id);

        myProductsCall.enqueue(new Callback<WalletListModel>() {
            @Override
            public void onResponse(Call<WalletListModel> call, Response<WalletListModel> response) {
                if (response.body() != null) {
                    WalletListModel serviceModels = response.body();
                    if (!serviceModels.getBanner().isEmpty()) {

                        Service_list.postValue(serviceModels.getBanner());

                        Log.d("tag",User_id);


                    } else if (serviceModels.getBanner().isEmpty()) {
                        // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();

                        Service_list=null;
                        Log.d("tag","empty");
                        Log.d("tag",User_id);


                    }
                } else if (response.body() == null) {

                    Log.d("tag","null");
                    Log.d("tag",User_id);

                }
            }

            @Override
            public void onFailure(Call<WalletListModel> call, Throwable t) {


                Log.d("tag",t.toString());


            }
        });

        return Service_list;
    }

    @Override
    public void getWalletInterface(String id) {
        this.User_id=id;
    }*/
}
