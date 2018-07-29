package com.mynews.flooo.mynews;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class AdapterPageActuality extends FragmentPagerAdapter
{


    private String[] tabTitles = new String[]{"Top Stories", "Most Popular","world"};
    private int mNumOfTabs;

    // 2 - Default Constructor
    public AdapterPageActuality(FragmentManager mgr,int NumofTabs)
    {
        super(mgr);
        this.mNumOfTabs = NumofTabs;
        //this.colors = colors;
    }

    @Override
    public int getCount()
    {
        return (tabTitles.length);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
    return tabTitles[position];
    }

    @Override
    public Fragment getItem(int position)
    {
        // 4 - Page to return
        return FragmentPageActuality.newInstance(tabTitles[position]);
    }




}
