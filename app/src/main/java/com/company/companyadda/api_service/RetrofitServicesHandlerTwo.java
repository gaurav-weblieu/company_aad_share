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
RetrofitServicesHandlerTwo {

    private static RetrofitServicesHandlerTwo registrationRetrofitClient;
    private Retrofit retrofit;
    private static final String AUTH =
            Base64.encodeToString(("eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJBUEkiLCJyZWZyZXNoX3Rva2VuIjoiZXlKaGJHY2lPaUpJVXpVeE1pSjkuZXlKaGRXUWlPaUpCVUVraUxDSnpkV0lpT2lKamIyMXdZVzU1WVdSa1lTNWpiMEJuYldGcGJDNWpiMjBpTENKaGNHbGZhMlY1SWpvaWEyVjVYMnhwZG1WZmJrZDZRbVpRUlRaSVJHTXpNMlJCTURKQ01VcEtVRk5FYVZKalMyOHdhblVpTENKcGMzTWlPaUpoY0drdWMyRnVaR0p2ZUM1amJ5NXBiaUlzSW1WNGNDSTZNVFkyTXpjek9UTTROeXdpYVc1MFpXNTBJam9pVWtWR1VrVlRTRjlVVDB0RlRpSXNJbWxoZENJNk1UWXpNakl3TXpNNE4zMC5lZW5XRnY3UE1lZTFybFYzaUhfNlBXNjBPU2JQVnNSTTRsYlhKVmo4MVdfRmxzaGM5OFBPSW0xamh5R2tiOVhUWVdWLWI4bnRXb2pGSVF5ZlBqUlJZZyIsInN1YiI6ImNvbXBhbnlhZGRhLmNvQGdtYWlsLmNvbSIsImFwaV9rZXkiOiJrZXlfbGl2ZV9uR3pCZlBFNkhEYzMzZEEwMkIxSkpQU0RpUmNLbzBqdSIsImlzcyI6ImFwaS5zYW5kYm94LmNvLmluIiwiZXhwIjoxNjMyMjg5Nzg3LCJpbnRlbnQiOiJBQ0NFU1NfVE9LRU4iLCJpYXQiOjE2MzIyMDMzODd9.jtIb_FbJtdT9p8G9Ust6DXyXT2zf5lVI7vnnHeE9vEgOeolSvS4FfPVms6FHBDgoGnQ2u59RErTlILBAGCCFnQ").getBytes(), Base64.NO_WRAP);
    private static final String KEY = Base64.encodeToString(("key_live_nGzBfPE6HDc33dA02B1JJPSDiRcKo0ju").getBytes(), Base64.NO_WRAP);
    private static final String VERSION =  Base64.encodeToString(("3.1").getBytes(), Base64.NO_WRAP);

    private RetrofitServicesHandlerTwo(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request originalRequest = chain.request();
                                Request.Builder builder = originalRequest.newBuilder()
                                        .addHeader("Authorization", AUTH)
                                        .addHeader("x-api-key", KEY)
                                        .addHeader("x-api-version", VERSION)
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
                .baseUrl(APIUrls.API_BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

    }


    public static synchronized RetrofitServicesHandlerTwo getInstance(){
        if (registrationRetrofitClient == null){
            registrationRetrofitClient = new RetrofitServicesHandlerTwo();
        }
        return registrationRetrofitClient;
    }

    public Apis getApi(){
        return retrofit.create(Apis.class);
    }
}
