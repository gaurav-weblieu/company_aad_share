package com.company.companyadda.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.company.companyadda.ApiModels.AllDetaislModels;
import com.company.companyadda.ApiModels.LoginModels;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;

public class About_Us extends AppCompatActivity {

    WebView webView;
    private SharedPreferences sharedPreferences_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);

        sharedPreferences_login = getSharedPreferences("login_details", MODE_PRIVATE);


        webView=findViewById(R.id.webView);

        new Thread(runnable).start();


       /* FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = token;
                        Log.d("TAG", msg);
                        Toast.makeText(About_Us.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });*/

    }

    Runnable runnable= new Runnable() {
        @Override
        public void run() {

            Call<AllDetaislModels> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .AllDetails(sharedPreferences_login.getString("phone",""));

            myProductsCall.enqueue(new Callback<AllDetaislModels>() {
                @Override
                public void onResponse(Call<AllDetaislModels> call, retrofit2.Response<AllDetaislModels> response) {
                    if (response.body() != null) {
                        AllDetaislModels loginModels = response.body();
                        if (loginModels.getResult().getSTATUS().equals("true")) {

                            String str=loginModels.getPages_Details().get(0).getContent();

                            webView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);


                        } else if (loginModels.getResult().getSTATUS().equals("false")) {
                            // Toast.makeText(Address_Activity.this, "empty", Toast.LENGTH_SHORT).show();
                            Log.d("tag","empty");
                        }
                    } else if (response.body() == null) {
                        //  Toast.makeText(Address_Activity.this, "null", Toast.LENGTH_SHORT).show();

                        Log.d("tag","null");

                    }
                }

                @Override
                public void onFailure(Call<AllDetaislModels> call, Throwable t) {


                    Log.d("tag",t.toString());

                }
            });



        }
    };

    public void back(View view) {
        finish();
    }
}