package com.company.companyadda.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.company.companyadda.Fragment.CompanyResFirst;
import com.company.companyadda.Fragment.CompanyResSecond;
import com.company.companyadda.Fragment.CompanyResThird;
import com.company.companyadda.Fragment.CompanyResfiv;
import com.company.companyadda.Fragment.DetaislFirsFragment;
import com.company.companyadda.Fragment.DetaislSecondFragment;
import com.company.companyadda.Fragment.DetaislThirdFragment;
import com.company.companyadda.Fragment.companyResFour;

public class CompanyResAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public CompanyResAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CompanyResFirst detaislFirsFragment = new CompanyResFirst();
                return detaislFirsFragment;
            case 1:
                CompanyResSecond detaislFirsFragment1 = new CompanyResSecond();
                return detaislFirsFragment1;

            case 2:
                CompanyResThird detaislFirsFragment2 = new CompanyResThird();
                return detaislFirsFragment2;

            case 3:
                companyResFour detaislFirsFragment3 = new companyResFour();
                return detaislFirsFragment3;

            case 4:
                CompanyResfiv detaislFirsFragment4 = new CompanyResfiv();
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