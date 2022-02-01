package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.company.companyadda.ApiModels.FormModels;
import com.company.companyadda.Pojo.CapitalResult;
import com.company.companyadda.Pojo.SettingPinPojo;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.company.companyadda.databinding.ActivityOtpBinding;
import com.company.companyadda.databinding.ActivitySettingPinBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingPin extends AppCompatActivity {

    private ProgressBar progress_bar;
    ActivitySettingPinBinding activitySettingPinBinding;
    private SharedPreferences sharedPreferences_login;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setting_pin);
        activitySettingPinBinding= ActivitySettingPinBinding.inflate(getLayoutInflater());
        View view=activitySettingPinBinding.getRoot();
        setContentView(view);

        progress_bar=findViewById(R.id.progress_bar);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);


        user_id=sharedPreferences_login.getString("user_id","");

        activitySettingPinBinding.newPinSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Upload_Form();
            }
        });


        activitySettingPinBinding.editNewOnePin.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);



        activitySettingPinBinding.editNewOnePin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activitySettingPinBinding.editNewSecPin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activitySettingPinBinding.editNewSecPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activitySettingPinBinding.editNewThreePin.requestFocus();
                } else  if (s.length()==0){
                    activitySettingPinBinding.editNewOnePin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activitySettingPinBinding.editNewThreePin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activitySettingPinBinding.editNewFourPin.requestFocus();
                }else  if (s.length()==0){
                    activitySettingPinBinding.editNewSecPin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        activitySettingPinBinding.editNewFourPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    activitySettingPinBinding.editNewThreePin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

       /* activitySettingPinBinding.pin1.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
*/


        activitySettingPinBinding.editConOnePin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activitySettingPinBinding.editConSecPin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activitySettingPinBinding.editConSecPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activitySettingPinBinding.editConThreePin.requestFocus();
                } else  if (s.length()==0){
                    activitySettingPinBinding.editConOnePin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activitySettingPinBinding.editConThreePin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activitySettingPinBinding.editConFourPin.requestFocus();
                }else  if (s.length()==0){
                    activitySettingPinBinding.editConSecPin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        activitySettingPinBinding.editConFourPin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    activitySettingPinBinding.editConThreePin.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }



    public void Upload_Form(){

        progress_bar.setVisibility(View.VISIBLE);

        String new_pin=activitySettingPinBinding.editNewOnePin.getText().toString()+
                activitySettingPinBinding.editNewSecPin.getText().toString()
                +activitySettingPinBinding.editNewThreePin.getText().toString()
                +activitySettingPinBinding.editNewFourPin.getText().toString();

        String con_new_pin=activitySettingPinBinding.editConOnePin.getText().toString()+
                activitySettingPinBinding.editConSecPin.getText().toString()
                +activitySettingPinBinding.editConThreePin.getText().toString()
                +activitySettingPinBinding.editConFourPin.getText().toString();



        Call<SettingPinPojo> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .SetforgotDocsPin(user_id,new_pin,con_new_pin);

        myProductsCall.enqueue(new Callback<SettingPinPojo>() {
            @Override
            public void onResponse(Call<SettingPinPojo> call, Response<SettingPinPojo> response) {
                if (response.body() != null) {
                    SettingPinPojo formModels = response.body();
                    if (formModels.getResult().getSTATUS().equals("true")){



                        Toast.makeText(SettingPin.this, ""+formModels.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                          progress_bar.setVisibility(View.GONE);

                          finish();

                    }
                    else if (formModels.getResult().getSTATUS().equals("false")) {
                        Toast.makeText(SettingPin.this, "false"+formModels.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                } else if (response.body() == null) {
                    Toast.makeText(SettingPin.this, "null", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);



                }
            }

            @Override
            public void onFailure(Call<SettingPinPojo> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);

                Toast.makeText(SettingPin.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void send_home(View view) { }

    public void back(View view) {
        finish();
    }
}