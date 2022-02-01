package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.company.companyadda.CheckNetwork;
import com.company.companyadda.R;

public class NoInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

}

    public void retry(View view) {

        if(CheckNetwork.isInternetAvailable(NoInternet.this)) //returns true if internet available
        {
            startActivity(new Intent(NoInternet.this,DashBoard.class));
            finish();
        }
        else
        {
            Toast.makeText(NoInternet.this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }

    }
    }