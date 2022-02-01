package com.company.companyadda.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.company.companyadda.Fragment.DetaislFirsFragment;
import com.company.companyadda.Fragment.DetaislSecondFragment;
import com.company.companyadda.Fragment.DetaislThirdFragment;
import com.company.companyadda.Fragment.GST_Five;
import com.company.companyadda.Fragment.GST_first;
import com.company.companyadda.Fragment.GST_four;
import com.company.companyadda.Fragment.GST_sec;
import com.company.companyadda.Fragment.GST_third;

public class GSTAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public GSTAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                GST_first detaislFirsFragment = new GST_first();
                return detaislFirsFragment;
            case 1:
                GST_sec detaislFirsFragment1 = new GST_sec();
                return detaislFirsFragment1;

            case 2:
                GST_third detaislFirsFragment2 = new GST_third();
                return detaislFirsFragment2;

            case 3:
                GST_four detaislFirsFragment3 = new GST_four();
                return detaislFirsFragment3;

            case 4:
                GST_Five detaislFirsFragment4 = new GST_Five();
                return detaislFirsFragment4;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}