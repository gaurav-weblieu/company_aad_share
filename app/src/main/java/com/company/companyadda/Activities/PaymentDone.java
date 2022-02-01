package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.company.companyadda.ApiModels.PaymentPojoModel;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDone extends AppCompatActivity {

    String usre_id,payment_id,payment_via,order_id,signatue,status,amount,note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_done);

        usre_id=getIntent().getStringExtra("user_id");
        payment_id=getIntent().getStringExtra("payment_id");
        payment_via=getIntent().getStringExtra("payment_via");
        order_id=getIntent().getStringExtra("order_id");
        signatue=getIntent().getStringExtra("sig");
        status=getIntent().getStringExtra("status");
        amount=getIntent().getStringExtra("amount");
        note=getIntent().getStringExtra("note");


        Upload_payment(usre_id,payment_id,payment_via,order_id,signatue,status,amount,note);


    }


    public void Upload_payment(String user_id,String razorpay_payment_id,String payment_via,String razorpay_order_id,
                               String razorpay_signature,String Status,String amount,String note){


        Call<PaymentPojoModel> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .Payment(user_id,razorpay_payment_id,payment_via,razorpay_order_id,razorpay_signature,Status,amount,note);

        myProductsCall.enqueue(new Callback<PaymentPojoModel>() {
            @Override
            public void onResponse(Call<PaymentPojoModel> call, Response<PaymentPojoModel> response) {
                if (response.body() != null) {
                    PaymentPojoModel paymentPojoModel = response.body();
                    if (paymentPojoModel.getResult().getSTATUS().equals("true")){
                        //    Toast.makeText(Fill_form.this, "true", Toast.LENGTH_SHORT).show();

                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                startActivity(new Intent(PaymentDone.this,Payment.class));
                                finish();
                            }
                        }, 2000);

                    }
                    else if (paymentPojoModel.getResult().getSTATUS().equals("false")) {
                        Toast.makeText(PaymentDone.this, "false", Toast.LENGTH_SHORT).show();

                    }
                } else if (response.body() == null) {
                    Toast.makeText(PaymentDone.this, "null", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<PaymentPojoModel> call, Throwable t) {

                Toast.makeText(PaymentDone.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    }
