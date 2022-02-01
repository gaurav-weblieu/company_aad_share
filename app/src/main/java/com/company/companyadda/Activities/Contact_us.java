package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.company.companyadda.R;

public class Contact_us extends AppCompatActivity {

    TextView textView_email,textView_phone;
    private SharedPreferences sharedPreferences_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        textView_email=findViewById(R.id.textView_email);
        textView_phone=findViewById(R.id.textView_phone);

        sharedPreferences_login = getSharedPreferences("login_details", MODE_PRIVATE);

        textView_email.setText(sharedPreferences_login.getString("con_email",""));
        textView_phone.setText("9524872487");

        textView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse("tel:" +"9524872487"));
                startActivity(callIntent);


            }
        });


        textView_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.parse("mailto:" + sharedPreferences_login.getString("con_email","")));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "body");
                startActivity(Intent.createChooser(emailIntent, "Select Email App"));

            }
        });



    }

    public void back(View view) {
        finish();
    }

    public void continue_fill_form(View view) {
        finish();
    }


    public void send_home(View view) {

        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Contact_us.this.finish();
    }
}