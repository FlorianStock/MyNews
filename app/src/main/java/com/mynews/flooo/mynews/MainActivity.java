package com.mynews.flooo.mynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.mynews.flooo.mynews.ApiRest.News;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager pager = findViewById(R.id.viewpager);
        pager.setAdapter(new AdapterPageActuality(getSupportFragmentManager(),tabLayout.getTabCount()));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //AsyncTask apiTimes = new ApiNewYorkTimes(this).execute();


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
                intent.putExtra("FragmentLoad","Notifications");
                startActivity(intent);
                return true;
            case R.id.action_search:
                Intent intentSearch = new Intent(MainActivity.this, OptionsActivity.class);
                intentSearch.putExtra("FragmentLoad","Search Articles");
                startActivity(intentSearch);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }





}
