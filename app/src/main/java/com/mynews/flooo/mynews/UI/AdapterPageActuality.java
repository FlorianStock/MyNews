package com.mynews.flooo.mynews.UI;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class AdapterPageActuality extends FragmentPagerAdapter
{


    private ArrayList<String> tabTitles ;


    // 2 - Default Constructor
    public AdapterPageActuality(FragmentManager mgr, ArrayList<String> tabTitles)
    {
        super(mgr);

        this.tabTitles = tabTitles;
    }

    @Override
    public int getCount()
    {
        return (tabTitles.size());
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
    return tabTitles.get(position);
    }

    @Override
    public Fragment getItem(int position)
    {
        // 4 - Page to return
        return FragmentPageActuality.newInstance(tabTitles.get(position));
    }







}
