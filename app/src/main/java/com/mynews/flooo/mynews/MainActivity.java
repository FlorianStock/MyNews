package com.mynews.flooo.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mynews.flooo.mynews.ApiRest.News;
import com.mynews.flooo.mynews.ApiRest.Results;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Serializable {

    @SerializedName("CheckBoxArray")
    @Expose
    public ArrayList<CheckBox> CheckBoxArray;

    private ArrayList<String> tabTitles = new ArrayList<>();
    //private String[] tabTitles = new String[]{"Top Stories", "Most Popular","world"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        tabTitles.add("Top Stories");
        tabTitles.add("Most Popular");
        tabTitles.add("world");

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager pager = findViewById(R.id.viewpager);
        pager.setAdapter(new AdapterPageActuality(getSupportFragmentManager(),tabTitles));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setInlineLabel(true);

        DrawerLayout drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {

            case R.id.notifications:
                Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
                intent.putExtra("LoadLayout","Notifications");
                startActivity(intent);
                return true;
            case R.id.action_search:
                Intent intentSearch = new Intent(MainActivity.this, OptionsActivity.class);
                intentSearch.putExtra("LoadLayout","Search Articles");
                startActivity(intentSearch);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }





}
