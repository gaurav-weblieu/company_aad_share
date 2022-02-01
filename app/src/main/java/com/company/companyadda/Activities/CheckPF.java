package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.company.companyadda.R;

public class CheckPF extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL = 1;
    CardView cardView_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pf);

        cardView_call=findViewById(R.id.cardView_call);

        cardView_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_intent();
            }
        });

    }

    public void call_intent() {

      /*  Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:011-22901406"));

        if (ActivityCompat.checkSelfPermission(CheckPF.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);*/
/*
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "011-22901406"));// Initiates the Intent
        startActivity(intent);*/

        if (ContextCompat.checkSelfPermission(CheckPF.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CheckPF.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
        }
        else
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:011-22901406"));
            startActivity(callIntent);
        }

    }

    public void send_home(View view) {
        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        CheckPF.this.finish();
    }

    public void back(View view) {
        finish();
    }
}