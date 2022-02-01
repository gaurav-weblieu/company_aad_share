package com.company.companyadda.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.company.companyadda.ApiModels.LoginModels;
import com.company.companyadda.ApiModels.ServiceModels;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.google.android.material.button.MaterialButton;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    MaterialButton material_button_start;
    EditText editText_phone;
    ProgressBar progress_bar;
    private SharedPreferences sharedPreferences_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);


        material_button_start=findViewById(R.id.material_button_start);
        editText_phone=findViewById(R.id.editText_phone);
        progress_bar=findViewById(R.id.progress_bar);



        material_button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
            }
        });

    }

    public void Validate() {

        if (editText_phone.getText().toString().length()==10){

            new Thread(runnable).start();
            progress_bar.setVisibility(View.VISIBLE);

        }else {
            editText_phone.setError("Enter Valid Number");
           // Toast.makeText(this, "low", Toast.LENGTH_SHORT).show();
        }


    }


    public void toSignUp(View view) {
        startActivity(new Intent(this,SignUp.class));
    }


    Runnable runnable= new Runnable() {
        @Override
        public void run() {

            Call<LoginModels> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .login("+91"+editText_phone.getText().toString());

            myProductsCall.enqueue(new Callback<LoginModels>() {
                @Override
                public void onResponse(Call<LoginModels> call, Response<LoginModels> response) {
                    if (response.body() != null) {
                        LoginModels loginModels = response.body();
                        if (loginModels.getResult().getSTATUS().equals("true")) {

                            progress_bar.setVisibility(View.GONE);

                            String ref=loginModels.getResult().getReferal_code();

                            sharedPreferences_login.edit().putString("ref_code",ref).apply();
                            sharedPreferences_login.edit().putString("name",loginModels.getResult().getName()).apply();
                            sharedPreferences_login.edit().putString("email",loginModels.getResult().getEmail()).apply();
                            String phone=loginModels.getResult().getPhone();
                            sharedPreferences_login.edit().putString("phone",phone).apply();
                            sharedPreferences_login.edit().putString("user_id",loginModels.getResult().getUser_id()).apply();

                            Intent intent =new Intent(Login.this,Otp.class);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    SmsVerifyCatcher smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
        @Override
        public void onSmsCatch(String message) {



        }
    });

    @Override
    protected void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }




}