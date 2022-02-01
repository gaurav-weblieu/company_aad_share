package com.company.companyadda.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.company.companyadda.ApiModels.FormModels;
import com.company.companyadda.Pojo.ModelFeedback;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedBack extends AppCompatActivity {

    ProgressBar progress_bar;
    EditText editText_message;
    MaterialButton material_button_save;
    private SharedPreferences sharedPreferences_login;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        editText_message=findViewById(R.id.editText_message);
        progress_bar=findViewById(R.id.progress_bar);
        material_button_save=findViewById(R.id.material_button_save);


        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        user_id=sharedPreferences_login.getString("user_id","");


        material_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
                progress_bar.setVisibility(View.VISIBLE);
            }
        });





    }

    public void back(View view) {
        finish();
    }


    Runnable runnable= new Runnable() {
        @Override
        public void run() {

            String message=editText_message.getText().toString();

            Call<ModelFeedback> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .feedBack(user_id,message);

            myProductsCall.enqueue(new Callback<ModelFeedback>() {
                @Override
                public void onResponse(Call<ModelFeedback> call, Response<ModelFeedback> response) {
                    if (response.body() != null) {
                        ModelFeedback formModels = response.body();
                        if (formModels.getResult().getSTATUS().equals("true")){

                            progress_bar.setVisibility(View.GONE);
                            Toast.makeText(FeedBack.this, formModels.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();

                            AlertDialog.Builder builder = new AlertDialog.Builder(FeedBack.this);

                            ViewGroup viewGroup = findViewById(android.R.id.content);
                            View dialogView = LayoutInflater.from(FeedBack.this).inflate(R.layout.customview, viewGroup, false);
                            builder.setView(dialogView);
                            AlertDialog alertDialog = builder.create();
                            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                            alertDialog.show();


                            new Handler().postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    alertDialog.dismiss();

                                    finish();

                                }
                            }, 2000);

                        }
                        else if (formModels.getResult().getSTATUS().equals("false")) {
                            Toast.makeText(FeedBack.this, "false"+formModels.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);

                        }
                    } else if (response.body() == null) {
                        Toast.makeText(FeedBack.this, "null", Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);



                    }
                }

                @Override
                public void onFailure(Call<ModelFeedback> call, Throwable t) {
                    progress_bar.setVisibility(View.GONE);

                    Toast.makeText(FeedBack.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();

                }
            });



        }
    };

}