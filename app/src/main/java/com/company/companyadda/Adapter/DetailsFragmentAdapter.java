package com.company.companyadda.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.company.companyadda.Fragment.Active_complaint_Fragment;
import com.company.companyadda.Fragment.Complete_complaint_Fragment;
import com.company.companyadda.Fragment.DetaislFirsFragment;
import com.company.companyadda.Fragment.DetaislSecondFragment;
import com.company.companyadda.Fragment.DetaislThirdFragment;
import com.company.companyadda.Fragment.OnePersonFive;
import com.company.companyadda.Fragment.OnePersonFour;

public class DetailsFragmentAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public DetailsFragmentAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetaislFirsFragment detaislFirsFragment = new DetaislFirsFragment();
                return detaislFirsFragment;
            case 1:
                DetaislSecondFragment detaislFirsFragment1 = new DetaislSecondFragment();
                return detaislFirsFragment1;

            case 2:
                DetaislThirdFragment detaislFirsFragment2 = new DetaislThirdFragment();
                return detaislFirsFragment2;

            case 3:
                OnePersonFour detaislFirsFragment3 = new OnePersonFour();
                return detaislFirsFragment3;

            case 4:
                OnePersonFive detaislFirsFragment4 = new OnePersonFive();
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