package com.mynews.flooo.mynews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        String optionsStatus = getIntent().getStringExtra("FragmentLoad");
        toolbar.setTitle(optionsStatus);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LinearLayout mainLayout = findViewById(R.id.options);

       //loadDisplayLayout(optionsStatus);

        switch (optionsStatus)
        {
            case "Notifications":
                View view = getLayoutInflater().inflate(R.layout.notifications, mainLayout,false);
                mainLayout.addView(view);
                break;

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
