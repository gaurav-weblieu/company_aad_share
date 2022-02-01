package com.company.companyadda.Activities;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.company.companyadda.ApiModels.LoginModels;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.company.companyadda.databinding.ActivityOtpBinding;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Otp extends AppCompatActivity {

    ActivityOtpBinding activityOtpBinding;
    private String get_otp;
    private SharedPreferences sharedPreferences_login;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_otp);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        activityOtpBinding= ActivityOtpBinding.inflate(getLayoutInflater());
         View view=activityOtpBinding.getRoot();
         setContentView(view);


        activityOtpBinding.editTextOtp1.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

         get_otp=   getIntent().getStringExtra("otp");
         phone=   getIntent().getStringExtra("phone");

         activityOtpBinding.materialButtonContinue.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 validate();
             }
         });


        activityOtpBinding.editTextOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityOtpBinding.editTextOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activityOtpBinding.editTextOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityOtpBinding.editTextOtp3.requestFocus();
                } else  if (s.length()==0){
                    activityOtpBinding.editTextOtp1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activityOtpBinding.editTextOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityOtpBinding.editTextOtp4.requestFocus();
                }else  if (s.length()==0){
                    activityOtpBinding.editTextOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activityOtpBinding.editTextOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                }else  if (s.length()==0){
                    activityOtpBinding.editTextOtp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        activityOtpBinding.textViewResendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
                activityOtpBinding.progressBar.setVisibility(View.VISIBLE);
            }
        });




    }

    Runnable runnable= new Runnable() {
        @Override
        public void run() {

            Call<LoginModels> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .login("+91"+phone);

            myProductsCall.enqueue(new Callback<LoginModels>() {
                @Override
                public void onResponse(Call<LoginModels> call, Response<LoginModels> response) {
                    if (response.body() != null) {
                        LoginModels loginModels = response.body();
                        if (loginModels.getResult().getSTATUS().equals("true")) {


                            String ref=loginModels.getResult().getReferal_code();

                            sharedPreferences_login.edit().putString("ref_code",ref).apply();
                            sharedPreferences_login.edit().putString("name",loginModels.getResult().getName()).apply();
                            sharedPreferences_login.edit().putString("email",loginModels.getResult().getEmail()).apply();
                            String phone=loginModels.getResult().getPhone();
                            sharedPreferences_login.edit().putString("phone",phone).apply();
                            sharedPreferences_login.edit().putString("user_id",loginModels.getResult().getUser_id()).apply();

                            activityOtpBinding.progressBar.setVisibility(View.GONE);



                        } else if (loginModels.getResult().getSTATUS().equals("false")) {
                            // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();
                            Log.d("tag","empty");
                            activityOtpBinding.progressBar.setVisibility(View.GONE);
                        }
                    } else if (response.body() == null) {
                        //  Toast.makeText(Address_Activity.this, "null", Toast.LENGTH_SHORT).show();

                        Log.d("tag","null");

                        activityOtpBinding.progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<LoginModels> call, Throwable t) {

                    activityOtpBinding.progressBar.setVisibility(View.GONE);

                    Log.d("tag",t.toString());

                }
            });



        }
    };



    private void validate() {

        if (activityOtpBinding.editTextOtp1.getText().length()<=0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityOtpBinding.editTextOtp1.getText().length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityOtpBinding.editTextOtp1.getText().length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityOtpBinding.editTextOtp1.getText().length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityOtpBinding.editTextOtp1.getText().length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityOtpBinding.editTextOtp1.getText().length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityOtpBinding.editTextOtp1.getText().length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityOtpBinding.editTextOtp1.getText().length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else {

            String full_otp=activityOtpBinding.editTextOtp1.getText().toString()+
                    activityOtpBinding.editTextOtp2.getText().toString()+
                    activityOtpBinding.editTextOtp3.getText().toString()+
                    activityOtpBinding.editTextOtp4.getText().toString();

            if (full_otp.equals(get_otp)){

                sharedPreferences_login.edit().putBoolean("isLogin",true).apply();
                Intent intent = new Intent(Otp.this,DashBoard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                Otp.this.finish();
            }
            else {
                Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void continueVeri(View view) {
        startActivity(new Intent(this,City_select.class));
    }

    public void back(View view) {
        finish();
    }


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

    /**
     * need for Android 6 real time permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    SmsVerifyCatcher smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
        @Override
        public void onSmsCatch(String message) {
            int start=message.length()-4;
            int end=message.length();
            String od=message.substring(start,end);
            String che= String.valueOf(od.charAt(1));
            activityOtpBinding.editTextOtp1.setText(String.valueOf(od.charAt(0)));
            activityOtpBinding.editTextOtp2.setText(String.valueOf(od.charAt(1)));
            activityOtpBinding.editTextOtp3.setText(String.valueOf(od.charAt(2)));
            activityOtpBinding.editTextOtp4.setText(String.valueOf(od.charAt(3)));


        }
    });


}