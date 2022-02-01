package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.companyadda.R;

public class MainActivity extends AppCompatActivity {

    ImageView imageView_heart_mid,imageView_top,imageView_bottom;
    TextView textView_name,textView_name2;
    CharSequence charSequence;
    CharSequence charSequence1;
    int index;
    int index1;
    long delay=100;
    Handler handler = new Handler();
    Handler handler1 = new Handler();

    SharedPreferences sharedPreferences_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        imageView_heart_mid=findViewById(R.id.imageView_heart_mid);
        imageView_top=findViewById(R.id.imageView_top);
        imageView_bottom=findViewById(R.id.imageView_bottom);
        textView_name=findViewById(R.id.textView_name);
        textView_name2=findViewById(R.id.textView_name2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_wave);
        imageView_top.setAnimation(topAnimation);

        Animation bottomAnimation= AnimationUtils.loadAnimation(this,R.anim.bottom_wave);
        imageView_bottom.setAnimation(bottomAnimation);



        ObjectAnimator objectAnimator= ObjectAnimator.ofPropertyValuesHolder(
                imageView_heart_mid,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );

        objectAnimator.setDuration(500);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();

        animatText("Company");

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (sharedPreferences_login.getBoolean("isLogin",false)) {

                    Intent mainIntent = new Intent(MainActivity.this, DashBoard.class);
                    startActivity(mainIntent);
                    finish();

                }else {
                    Intent mainIntent = new Intent(MainActivity.this, Login.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, 2000);

    }

    Runnable runnable= new Runnable() {
        @Override
        public void run() {
            textView_name.setText(charSequence.subSequence(0,index++));

            if (index<=charSequence.length()){
                handler.postDelayed(runnable,delay);
            }

            animatText1("Adda");


        }
    };

    Runnable runnable2= new Runnable() {
        @Override
        public void run() {
            textView_name2.setText(charSequence1.subSequence(0,index1++));

            if (index1<=charSequence1.length()){
                handler1.postDelayed(runnable2,delay);
            }

        }
    };


    void animatText(CharSequence ch){
        charSequence=ch;
        index=0;
        textView_name.setText("");

        handler.removeCallbacks(runnable);

        handler.postDelayed(runnable,delay);

    }

    void animatText1(CharSequence ch){
        charSequence1=ch;
        index1=0;
        textView_name2.setText("");

        handler1.removeCallbacks(runnable2);

        handler1.postDelayed(runnable2,delay);

    }

}