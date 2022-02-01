package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.company.companyadda.R;

public class Request_successfully extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_successfully);
    }

    public void to_home(View view) {
        Intent intent = new Intent(Request_successfully.this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Request_successfully.this.finish();
    }

    public void all_request(View view) {

        Intent intent = new Intent(Request_successfully.this,My_request.class);
        startActivity(intent);
        Request_successfully.this.finish();
    }
}