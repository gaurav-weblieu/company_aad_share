package com.company.companyadda.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.company.companyadda.Pojo.SettingPinPojo;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.company.companyadda.databinding.ActivityForgetPinPasswordBinding;
import com.company.companyadda.databinding.ActivityLockScreenBinding;
import com.company.companyadda.databinding.ActivitySettingPinBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LockScreen extends AppCompatActivity {

    ActivityLockScreenBinding activityLockScreenBinding;
    private SharedPreferences sharedPreferences_login;
    private String user_id;
    private ProgressBar progress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lock_screen);

        activityLockScreenBinding= ActivityLockScreenBinding.inflate(getLayoutInflater());
        View view=activityLockScreenBinding.getRoot();
        setContentView(view);


        progress_bar=findViewById(R.id.progress_bar);


        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);


        user_id=sharedPreferences_login.getString("user_id","");

        activityLockScreenBinding.buttonPinSubmitLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Upload_Form();
            }
        });



        activityLockScreenBinding.pin1.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);



        activityLockScreenBinding.pin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityLockScreenBinding.pin2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activityLockScreenBinding.pin2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityLockScreenBinding.pin3.requestFocus();
                } else  if (s.length()==0){
                    activityLockScreenBinding.pin1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activityLockScreenBinding.pin3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityLockScreenBinding.pin4.requestFocus();
                }else  if (s.length()==0){
                    activityLockScreenBinding.pin2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        activityLockScreenBinding.pin4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    activityLockScreenBinding.pin3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



    public void forget(View view) {
        startActivity(new Intent(this,ForgitLockPin.class));

    }


    public void Upload_Form(){

        progress_bar.setVisibility(View.VISIBLE);

        String new_pin=activityLockScreenBinding.pin1.getText().toString()+
                activityLockScreenBinding.pin2.getText().toString()
                +activityLockScreenBinding.pin3.getText().toString()
                +activityLockScreenBinding.pin4.getText().toString();


        Call<SettingPinPojo> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .forgotDocsPin(user_id,new_pin);

        myProductsCall.enqueue(new Callback<SettingPinPojo>() {
            @Override
            public void onResponse(Call<SettingPinPojo> call, Response<SettingPinPojo> response) {
                if (response.body() != null) {
                    SettingPinPojo formModels = response.body();
                    if (formModels.getResult().getSTATUS().equals("true")){

                        Toast.makeText(LockScreen.this, ""+formModels.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                        AlertDialog.Builder builder = new AlertDialog.Builder(LockScreen.this);

                        ViewGroup viewGroup = findViewById(android.R.id.content);
                        View dialogView = LayoutInflater.from(LockScreen.this).inflate(R.layout.customview, viewGroup, false);
                        builder.setView(dialogView);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                        alertDialog.show();


                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                alertDialog.dismiss();

                                startActivity(new Intent(LockScreen.this,DocUpload.class));
                                finish();

                            }
                        }, 2000);

                    }
                    else if (formModels.getResult().getSTATUS().equals("false")) {
                        Toast.makeText(LockScreen.this, "false"+formModels.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                } else if (response.body() == null) {
                    Toast.makeText(LockScreen.this, "null", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);



                }
            }

            @Override
            public void onFailure(Call<SettingPinPojo> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);

                Toast.makeText(LockScreen.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void send_home(View view) { }

    public void back(View view) {
        finish();
    }
}