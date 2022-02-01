package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.company.companyadda.Adapter.IntroSliderAdapter;
import com.company.companyadda.R;

public class Intro_Slider extends AppCompatActivity {


    ViewPager mViewPager;

    int[] images = {R.drawable.slider1_image, R.drawable.slider2_image};

    IntroSliderAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro__slider);



        // Initializing the ViewPager Object
        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new IntroSliderAdapter(Intro_Slider.this, images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}