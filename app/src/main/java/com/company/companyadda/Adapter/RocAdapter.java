package com.company.companyadda.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.company.companyadda.Fragment.DetaislFirsFragment;
import com.company.companyadda.Fragment.DetaislSecondFragment;
import com.company.companyadda.Fragment.DetaislThirdFragment;
import com.company.companyadda.Fragment.ROCFive;
import com.company.companyadda.Fragment.ROCFour;
import com.company.companyadda.Fragment.RocFirst;
import com.company.companyadda.Fragment.RocSec;
import com.company.companyadda.Fragment.RocThird;

public class RocAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public RocAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RocFirst detaislFirsFragment = new RocFirst();
                return detaislFirsFragment;
            case 1:
                RocSec detaislFirsFragment1 = new RocSec();
                return detaislFirsFragment1;

            case 2:
                RocThird detaislFirsFragment2 = new RocThird();
                return detaislFirsFragment2;

            case 3:
                ROCFour detaislFirsFragment3 = new ROCFour();
                return detaislFirsFragment3;
            case 4:
                ROCFive detaislFirsFragment4 = new ROCFive();
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