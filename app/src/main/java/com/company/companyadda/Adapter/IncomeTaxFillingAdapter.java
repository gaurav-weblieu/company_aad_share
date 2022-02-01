package com.company.companyadda.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.company.companyadda.Fragment.DetaislFirsFragment;
import com.company.companyadda.Fragment.DetaislSecondFragment;
import com.company.companyadda.Fragment.DetaislThirdFragment;
import com.company.companyadda.Fragment.IncomeTaxFillingFirst;
import com.company.companyadda.Fragment.IncomeTaxFillingFive;
import com.company.companyadda.Fragment.IncomeTaxFillingFour;
import com.company.companyadda.Fragment.IncomeTaxFillingSec;
import com.company.companyadda.Fragment.IncomeTaxFillingThird;

public class IncomeTaxFillingAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public IncomeTaxFillingAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                IncomeTaxFillingFirst detaislFirsFragment = new IncomeTaxFillingFirst();
                return detaislFirsFragment;
            case 1:
                IncomeTaxFillingSec detaislFirsFragment1 = new IncomeTaxFillingSec();
                return detaislFirsFragment1;

            case 2:
                IncomeTaxFillingThird detaislFirsFragment2 = new IncomeTaxFillingThird();
                return detaislFirsFragment2;

            case 3:
                IncomeTaxFillingFour detaislFirsFragment3 = new IncomeTaxFillingFour();
                return detaislFirsFragment3;

            case 4:
                IncomeTaxFillingFive detaislFirsFragment4 = new IncomeTaxFillingFive();
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