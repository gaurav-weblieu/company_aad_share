package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.company.companyadda.ApiModels.LoginModels;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.company.companyadda.databinding.ActivityForgitLockPinBinding;
import com.company.companyadda.databinding.ActivityLockScreenBinding;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgitLockPin extends AppCompatActivity {

    MaterialButton material_button_start;
    EditText editText_phone;
    ProgressBar progress_bar;
    private SharedPreferences sharedPreferences_login;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgit_lock_pin);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

         phone=sharedPreferences_login.getString("phone","");


        material_button_start=findViewById(R.id.material_button_start);
        editText_phone=findViewById(R.id.editText_phone);
        editText_phone.setFocusable(false);
        progress_bar=findViewById(R.id.progress_bar);


        String fina_phone= phone.substring(3,13);

        editText_phone.setText(fina_phone);





        material_button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });

    }



    public void Validate() {

       /* if (editText_phone.getText().toString().length()==10){



        }else {
            editText_phone.setError("Enter Valid Number");
            // Toast.makeText(this, "low", Toast.LENGTH_SHORT).show();
        }*/

        new Thread(runnable).start();
        progress_bar.setVisibility(View.VISIBLE);


    }




    Runnable runnable= new Runnable() {
        @Override
        public void run() {

            Call<LoginModels> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .login(phone);

            myProductsCall.enqueue(new Callback<LoginModels>() {
                @Override
                public void onResponse(Call<LoginModels> call, Response<LoginModels> response) {
                    if (response.body() != null) {
                        LoginModels loginModels = response.body();
                        if (loginModels.getResult().getSTATUS().equals("true")) {

                            progress_bar.setVisibility(View.GONE);

                            Intent intent =new Intent(ForgitLockPin.this,ForgetPinPassword.class);
                            intent.putExtra("otp",loginModels.getResult().getCode());
                            intent.putExtra("phone",editText_phone.getText().toString());
                            startActivity(intent);

                        } else if (loginModels.getResult().getSTATUS().equals("false")) {
                            // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();
                            Log.d("tag","empty");
                            progress_bar.setVisibility(View.GONE);
                        }
                    } else if (response.body() == null) {
                        //  Toast.makeText(Address_Activity.this, "null", Toast.LENGTH_SHORT).show();

                        Log.d("tag","null");

                        progress_bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<LoginModels> call, Throwable t) {

                    progress_bar.setVisibility(View.GONE);

                    Log.d("tag",t.toString());

                }
            });



        }
    };



}