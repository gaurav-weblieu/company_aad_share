package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.company.companyadda.Adapter.Wallet_list_adapter;
import com.company.companyadda.ApiModels.WalletListModel;
import com.company.companyadda.Pojo.Wallet_history_pojo;
import com.company.companyadda.R;
import com.company.companyadda.Reposetry.WalletList_Repo;
import com.company.companyadda.ViewModel.Wallet_list_View_Model;
import com.company.companyadda.api_service.RetrofitServicesHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Wallet extends AppCompatActivity {

    TextView textView_balance;
    TextView textView_symbol;
    private SharedPreferences sharedPreferences_login;
     Wallet_list_View_Model model;
    RecyclerView recycleView_wallet_list;
    private Wallet_list_adapter adapter;
    public List<Wallet_history_pojo> Service_list=new ArrayList<>();
    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

         user_id=sharedPreferences_login.getString("user_id","");

        textView_balance=findViewById(R.id.textView_balance);
        textView_symbol=findViewById(R.id.textView_symbol);
        recycleView_wallet_list=findViewById(R.id.recycleView_wallet_list);

        textView_symbol.setText(R.string.Rs);

       /* if (sharedPreferences_login.getBoolean("is_refer_done",false)){
            textView_balance.setText("50");
        }else {
            textView_balance.setText("0");
        }
*/
        getWalletList();


    }

    public void back(View view) {
        finish();
    }



    public void  getWalletList(){


        Call<WalletListModel> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .getWalletList(user_id);

        myProductsCall.enqueue(new Callback<WalletListModel>() {
            @Override
            public void onResponse(Call<WalletListModel> call, Response<WalletListModel> response) {
                if (response.body() != null) {
                    WalletListModel serviceModels = response.body();

                    if (serviceModels.getResult().getSTATUS().equals("true")){

                        textView_balance.setText(serviceModels.getWallet_details().get(0).getWallet_amount());


                        List<Wallet_history_pojo> Service_list= serviceModels.getWallet_list();


                        recycleView_wallet_list.setLayoutManager(new LinearLayoutManager(Wallet.this ));
                        adapter = new Wallet_list_adapter(Wallet.this,Service_list);
                        recycleView_wallet_list.setAdapter(adapter);
                    }else {
                       // Toast.makeText(Wallet.this, "False", Toast.LENGTH_SHORT).show();
                    }



                } else if (response.body() == null) {

                    Toast.makeText(Wallet.this, "Null", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WalletListModel> call, Throwable t) {


                Toast.makeText(Wallet.this, "Error"+t.toString(), Toast.LENGTH_SHORT).show();


            }
        });

    }
}