package com.company.companyadda.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.company.companyadda.Fragment.Active_complaint_Fragment;
import com.company.companyadda.Fragment.Complete_complaint_Fragment;

public class MyRequestFragmentAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public MyRequestFragmentAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Active_complaint_Fragment active_complaint_fragment = new Active_complaint_Fragment();
                return active_complaint_fragment;
            case 1:
                Complete_complaint_Fragment complete_complaint_fragment = new Complete_complaint_Fragment();
                return complete_complaint_fragment;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}