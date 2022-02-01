package com.company.companyadda.api_service;

import android.util.Base64;

import com.company.companyadda.api_interface.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class
RetrofitServicesHandler {

    private static RetrofitServicesHandler registrationRetrofitClient;
    private Retrofit retrofit;
    private static final String AUTH = "Basic " + Base64.encodeToString(("acubeapps@123:wel@123").getBytes(), Base64.NO_WRAP);

    private RetrofitServicesHandler(){


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request originalRequest = chain.request();
                                Request.Builder builder = originalRequest.newBuilder()
                                        .addHeader("Authorization", AUTH)
                                        .method(originalRequest.method(), originalRequest.body());
                                Request request = builder.build();
                                return chain.proceed(request);
                            }
                        }
                ).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(APIUrls.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

    }


    public static synchronized RetrofitServicesHandler getInstance(){
        if (registrationRetrofitClient == null){
            registrationRetrofitClient = new RetrofitServicesHandler();
        }
        return registrationRetrofitClient;
    }

    public Apis getApi(){
        return retrofit.create(Apis.class);
    }
}
