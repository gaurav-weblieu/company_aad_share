package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.company.companyadda.Adapter.DetailsFragmentAdapter;
import com.company.companyadda.Adapter.IncomeTaxFillingAdapter;
import com.company.companyadda.R;
import com.google.android.material.tabs.TabLayout;

public class IncomeTaxFilling extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_tax_filling);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Benefits"));
        tabLayout.addTab(tabLayout.newTab().setText("Document Required"));
        tabLayout.addTab(tabLayout.newTab().setText("Deliverables"));
        tabLayout.addTab(tabLayout.newTab().setText("FAQ,s"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final IncomeTaxFillingAdapter adapter = new IncomeTaxFillingAdapter(this,getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    public void back(View view) {
        finish();
    }


    public void noti(View view) {
        startActivity(new Intent(IncomeTaxFilling.this,Notification.class));

    }

    public void send_home(View view) {
        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        IncomeTaxFilling.this.finish();
    }
}