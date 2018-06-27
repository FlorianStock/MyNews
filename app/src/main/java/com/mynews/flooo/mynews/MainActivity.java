package com.mynews.flooo.mynews;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


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

            case R.id.action_add:
                /* DO ADD */
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
