package com.company.companyadda.Reposetry;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.company.companyadda.ApiModels.MyRequestDetailsModel;
import com.company.companyadda.ApiModels.WalletListModel;
import com.company.companyadda.Pojo.MyRequestDetailsPojo;
import com.company.companyadda.Pojo.Wallet_history_pojo;
import com.company.companyadda.api_service.RetrofitServicesHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Request_list_Repo {

    String User_id;


    public MutableLiveData<List<MyRequestDetailsPojo>> myRequesList;

    public MutableLiveData<List<MyRequestDetailsPojo>> getMyRequesList(){

        myRequesList = new MutableLiveData<>();

        Call<MyRequestDetailsModel> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .Get_Myrequest_Details("16","active");

        myProductsCall.enqueue(new Callback<MyRequestDetailsModel>() {
            @Override
            public void onResponse(Call<MyRequestDetailsModel> call, Response<MyRequestDetailsModel> response) {
                if (response.body() != null) {
                    MyRequestDetailsModel myRequestDetailsModel = response.body();
                    if (myRequestDetailsModel.getResult().getSTATUS().equals("true")) {

                        myRequesList.postValue(myRequestDetailsModel.getRequest_List());

                        Log.d("tag","Goog");


                    } else if (!myRequestDetailsModel.getResult().getSTATUS().equals("true")) {
                        // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();

                        myRequesList=null;
                        Log.d("tag","empty");

                    }
                } else if (response.body() == null) {

                    Log.d("tag","null");
                }
            }

            @Override
            public void onFailure(Call<MyRequestDetailsModel> call, Throwable t) {


                Log.d("tag",t.toString());


            }
        });

        return myRequesList;
    }

}
