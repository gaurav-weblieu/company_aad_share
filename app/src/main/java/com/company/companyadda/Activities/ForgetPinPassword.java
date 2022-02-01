package com.company.companyadda.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.company.companyadda.R;
import com.company.companyadda.databinding.ActivityForgetPinPasswordBinding;
import com.company.companyadda.databinding.ActivityOtpBinding;
import com.stfalcon.smsverifycatcher.OnSmsCatchListener;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

public class ForgetPinPassword extends AppCompatActivity {

    ActivityForgetPinPasswordBinding  activityForgetPinPasswordBinding;
    private String get_otp;
    String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pin_password);

        activityForgetPinPasswordBinding= ActivityForgetPinPasswordBinding.inflate(getLayoutInflater());
        View view=activityForgetPinPasswordBinding.getRoot();
        setContentView(view);

        activityForgetPinPasswordBinding.editTextfOtp1.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        get_otp=   getIntent().getStringExtra("otp");
        phone=   getIntent().getStringExtra("phone");

        activityForgetPinPasswordBinding.textphonesend.setText("An 4 digit has been sent to \n"+phone);

        activityForgetPinPasswordBinding.materialButtonContinuef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


        activityForgetPinPasswordBinding.editTextfOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityForgetPinPasswordBinding.editTextfOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activityForgetPinPasswordBinding.editTextfOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityForgetPinPasswordBinding.editTextfOtp3.requestFocus();
                } else  if (s.length()==0){
                    activityForgetPinPasswordBinding.editTextfOtp1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        activityForgetPinPasswordBinding.editTextfOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    activityForgetPinPasswordBinding.editTextfOtp4.requestFocus();
                }else  if (s.length()==0){
                    activityForgetPinPasswordBinding.editTextfOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        activityForgetPinPasswordBinding.editTextfOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    activityForgetPinPasswordBinding.editTextfOtp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }

    public void sumit_next(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPinPassword.this);

        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(ForgetPinPassword.this).inflate(R.layout.customview, viewGroup, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                alertDialog.dismiss();

                Intent intent = new Intent(ForgetPinPassword.this,DocUpload.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                ForgetPinPassword.this.finish();

            }
        }, 2000);
    }




    private void validate() {

        if (activityForgetPinPasswordBinding.editTextfOtp1.getText().length()<=0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityForgetPinPasswordBinding.editTextfOtp1.length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityForgetPinPasswordBinding.editTextfOtp1.length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityForgetPinPasswordBinding.editTextfOtp1.length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityForgetPinPasswordBinding.editTextfOtp1.length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityForgetPinPasswordBinding.editTextfOtp1.length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityForgetPinPasswordBinding.editTextfOtp1.length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else if (activityForgetPinPasswordBinding.editTextfOtp1.length()==0){
            Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();

        }else {

            String full_otp=activityForgetPinPasswordBinding.editTextfOtp1.getText().toString()+
                    activityForgetPinPasswordBinding.editTextfOtp2.getText().toString()+
                    activityForgetPinPasswordBinding.editTextfOtp3.getText().toString()+
                    activityForgetPinPasswordBinding.editTextfOtp4.getText().toString();

            if (full_otp.equals(get_otp)){
                Intent intent = new Intent(ForgetPinPassword.this,DocUpload.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                ForgetPinPassword.this.finish();
            }
            else {
                Toast.makeText(this, "Invalid OTP !", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void continueVeri(View view) {
        startActivity(new Intent(this,City_select.class));
    }

    public void back(View view) {
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }

    /**
     * need for Android 6 real time permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    SmsVerifyCatcher smsVerifyCatcher = new SmsVerifyCatcher(this, new OnSmsCatchListener<String>() {
        @Override
        public void onSmsCatch(String message) {
            int start=message.length()-4;
            int end=message.length();
            String od=message.substring(start,end);
            String che= String.valueOf(od.charAt(1));
            activityForgetPinPasswordBinding.editTextfOtp1.setText(String.valueOf(od.charAt(0)));
            activityForgetPinPasswordBinding.editTextfOtp2.setText(String.valueOf(od.charAt(1)));
            activityForgetPinPasswordBinding.editTextfOtp3.setText(String.valueOf(od.charAt(2)));
            activityForgetPinPasswordBinding.editTextfOtp4.setText(String.valueOf(od.charAt(3)));


        }
    });

    public void send_home(View view) {

    }
}