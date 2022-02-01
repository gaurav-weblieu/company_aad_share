package com.company.companyadda.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.company.companyadda.Fragment.CompanyResFirst;
import com.company.companyadda.Fragment.CompanyResSecond;
import com.company.companyadda.Fragment.CompanyResThird;
import com.company.companyadda.Fragment.CompanyResfiv;
import com.company.companyadda.Fragment.NewsFirst;
import com.company.companyadda.Fragment.companyResFour;

public class NewsFragmentAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public NewsFragmentAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NewsFirst detaislFirsFragment = new NewsFirst();
                return detaislFirsFragment;
            case 1:
                NewsFirst detaislFirsFragment1 = new NewsFirst();
                return detaislFirsFragment1;

            case 2:
                NewsFirst detaislFirsFragment2 = new NewsFirst();
                return detaislFirsFragment2;

            case 3:
                NewsFirst detaislFirsFragment3 = new NewsFirst();
                return detaislFirsFragment3;


            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}