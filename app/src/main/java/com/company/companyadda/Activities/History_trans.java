  package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.company.companyadda.Adapter.City_list_adapter;
import com.company.companyadda.Adapter.History_trans_list_adapter;
import com.company.companyadda.ApiModels.PaymentPojoModel;
import com.company.companyadda.Pojo.PaymentPojo;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

  public class History_trans extends AppCompatActivity {

      RecyclerView recycleView_trans_his;
      private History_trans_list_adapter adapter;
      List<PaymentPojo> paymentList= new ArrayList<>();
      private SharedPreferences sharedPreferences_login;
      private String user_id;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_trans);


        recycleView_trans_his=findViewById(R.id.recycleView_trans_his);


          sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

          user_id=sharedPreferences_login.getString("user_id","");


          Upload_payment(user_id);

    }

      public void back(View view) {
        finish();
      }



      public void Upload_payment(String user_id){


          Call<PaymentPojoModel> myProductsCall = RetrofitServicesHandler
                  .getInstance()
                  .getApi()
                  .Payment(user_id);

          myProductsCall.enqueue(new Callback<PaymentPojoModel>() {
              @Override
              public void onResponse(Call<PaymentPojoModel> call, Response<PaymentPojoModel> response) {
                  if (response.body() != null) {
                      PaymentPojoModel paymentPojoModel = response.body();
                      if (paymentPojoModel.getResult().getSTATUS().equals("true")){

                          paymentList=paymentPojoModel.getProfile();

                          recycleView_trans_his.setLayoutManager(new LinearLayoutManager(History_trans.this ));
                          adapter = new History_trans_list_adapter(History_trans.this,paymentList);
                          recycleView_trans_his.setAdapter(adapter);

                      }
                      else if (paymentPojoModel.getResult().getSTATUS().equals("false")) {
                          Toast.makeText(History_trans.this, "false", Toast.LENGTH_SHORT).show();
                          //   progress_bar.setVisibility(View.GONE);

                      }
                  } else if (response.body() == null) {
                      Toast.makeText(History_trans.this, "null", Toast.LENGTH_SHORT).show();
                      // progress_bar.setVisibility(View.GONE);



                  }
              }

              @Override
              public void onFailure(Call<PaymentPojoModel> call, Throwable t) {
                  //progress_bar.setVisibility(View.GONE);

                  Toast.makeText(History_trans.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();

              }
          });

      }
  }