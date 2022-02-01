package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.company.companyadda.R;

import java.text.CollationElementIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PaymentDetailsScreen extends AppCompatActivity {

    TextView textView_ruppe_sy;
    TextView amount,paymt_id,date;
    String str_amount,str_payId,str_date;
    private SharedPreferences sharedPreferences_login;
    private TextView textView_name,textView_number,textView_email,noteText;
    private String str_note;
    LinearLayout linear_layout_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details_screen);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);


        textView_ruppe_sy=findViewById(R.id.textView_ruppe_sy);
        amount=findViewById(R.id.amount);
        paymt_id=findViewById(R.id.paymt_id);
        date=findViewById(R.id.date);
        linear_layout_note=findViewById(R.id.linear_layout_note);

        textView_name=findViewById(R.id.textView_name);
        textView_number=findViewById(R.id.textView_number);
        textView_email=findViewById(R.id.textView_email);
        noteText=findViewById(R.id.noteText);


       str_amount= getIntent().getStringExtra("amount");
        str_payId= getIntent().getStringExtra("pay_id");
        str_date= getIntent().getStringExtra("Date");
        str_note= getIntent().getStringExtra("note");

        String deliveryDate=str_date;
        SimpleDateFormat dateFormatprev = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = null;
        try {
            d = dateFormatprev.parse(deliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //EEE for day
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");
        String changedDate = dateFormat.format(d);


        String deliveryDate1=str_date;
        SimpleDateFormat dateFormatprev1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d1 = null;
        try {
            d1 = dateFormatprev1.parse(deliveryDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm a");
        String changedDate1 = dateFormat1.format(d1);


        if (str_note.equals("")){
            linear_layout_note.setVisibility(View.GONE);
        }

        date.setText(changedDate+","+changedDate1);
        paymt_id.setText(str_payId);
        amount.setText(str_amount);
        noteText.setText(str_note);

        textView_ruppe_sy.setText(R.string.Rs);

        setData();

    }

    void setData() {

        textView_name.setText("From : " + sharedPreferences_login.getString("name", "").toString());
        textView_number.setText(sharedPreferences_login.getString("phone", "").toString());
        textView_email.setText(sharedPreferences_login.getString("email", "").toString());



    }
}