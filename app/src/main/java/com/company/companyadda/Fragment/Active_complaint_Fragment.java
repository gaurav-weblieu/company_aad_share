package com.company.companyadda.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.company.companyadda.Activities.Wallet;
import com.company.companyadda.Adapter.Active_list_adapter;
import com.company.companyadda.Adapter.Wallet_list_adapter;
import com.company.companyadda.ApiModels.MyRequestDetailsModel;
import com.company.companyadda.Pojo.MyRequestDetailsPojo;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class Active_complaint_Fragment extends Fragment {


    RecyclerView recycleView_req_list;
    public List<MyRequestDetailsPojo> myRequesList;
    private Active_list_adapter adapter;
    private SharedPreferences sharedPreferences_login;
    private String user_id;
    LottieAnimationView animationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_active_complaint_, container, false);

        sharedPreferences_login=getActivity().getSharedPreferences("login_details",MODE_PRIVATE);

        user_id=sharedPreferences_login.getString("user_id","");

        recycleView_req_list=view.findViewById(R.id.recycleView_req_list);
        animationView=view.findViewById(R.id.animationView);
        animationView.setVisibility(View.GONE);

        getMyRequesList();

        return view;
    }

    public void getMyRequesList(){

        myRequesList = new ArrayList<>();

        Call<MyRequestDetailsModel> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .Get_Myrequest_Details(user_id,"active");

        myProductsCall.enqueue(new Callback<MyRequestDetailsModel>() {
            @Override
            public void onResponse(Call<MyRequestDetailsModel> call, Response<MyRequestDetailsModel> response) {
                if (response.body() != null) {
                    MyRequestDetailsModel myRequestDetailsModel = response.body();
                    if (myRequestDetailsModel.getResult().getSTATUS().equals("true")) {

                        myRequesList=myRequestDetailsModel.getRequest_List();

                        recycleView_req_list.setLayoutManager(new LinearLayoutManager(getActivity() ));
                        adapter = new Active_list_adapter(getActivity(),myRequesList);
                        recycleView_req_list.setAdapter(adapter);


                        animationView.setVisibility(View.GONE);

                    } else if (!myRequestDetailsModel.getResult().getSTATUS().equals("true")) {
                        //  Toast.makeText(getActivity(), "empty", Toast.LENGTH_SHORT).show();

                        myRequesList=null;
                        Log.d("tag","empty");

                        animationView.setVisibility(View.VISIBLE);

                    }
                } else if (response.body() == null) {

                    animationView.setVisibility(View.VISIBLE);


                    Log.d("tag","null");
                }
            }

            @Override
            public void onFailure(Call<MyRequestDetailsModel> call, Throwable t) {


                Log.d("tag",t.toString());

                animationView.setVisibility(View.VISIBLE);


            }
        });

    }

}