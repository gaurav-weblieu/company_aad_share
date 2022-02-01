package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.company.companyadda.Adapter.City_list_adapter;
import com.company.companyadda.Adapter.Offers_list_adapter;
import com.company.companyadda.R;

public class Offers extends AppCompatActivity {

    private RecyclerView recycleView_offer;
    private Offers_list_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        recycleView_offer=findViewById(R.id.recycleView_offer);


        recycleView_offer.setLayoutManager(new LinearLayoutManager(Offers.this ));
        adapter = new Offers_list_adapter(Offers.this);
        recycleView_offer.setAdapter(adapter);

    }

    public void back(View view) {
        finish();
    }


    public void noti(View view) {
        startActivity(new Intent(Offers.this,Notification.class));

    }

    public void send_home(View view) {
             Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Offers.this.finish();
    }
}