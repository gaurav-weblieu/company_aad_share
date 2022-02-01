package com.company.companyadda.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.company.companyadda.Fragment.DetaislFirsFragment;
import com.company.companyadda.Fragment.DetaislSecondFragment;
import com.company.companyadda.Fragment.DetaislThirdFragment;
import com.company.companyadda.Fragment.LLPFive;
import com.company.companyadda.Fragment.LLPFour;
import com.company.companyadda.Fragment.LLPfirst;
import com.company.companyadda.Fragment.LLPsecond;
import com.company.companyadda.Fragment.LLPthird;

public class LLPAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public LLPAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LLPfirst detaislFirsFragment = new LLPfirst();
                return detaislFirsFragment;
            case 1:
                LLPsecond detaislFirsFragment1 = new LLPsecond();
                return detaislFirsFragment1;

            case 2:
                LLPthird detaislFirsFragment2 = new LLPthird();
                return detaislFirsFragment2;

            case 3:
                LLPFour detaislFirsFragment3 = new LLPFour();
                return detaislFirsFragment3;

            case 4:
                LLPFive detaislFirsFragment4 = new LLPFive();
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