package com.mynews.flooo.mynews.Controllers;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


// This is my adapter for the pages for the  ViewPager
//LINK Fragments with ViewPager.

public class AdapterPageActuality extends FragmentPagerAdapter
{


    private ArrayList<String> tabTitles ;


    // Default Constructor
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
