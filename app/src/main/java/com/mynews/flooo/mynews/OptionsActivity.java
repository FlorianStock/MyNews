package com.mynews.flooo.mynews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity implements CheckBox.OnCheckedChangeListener
{


    private SharedPreferences sharedPreferences;
    private ArrayList<CheckBox> boxes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Toolbar toolbar = findViewById(R.id.toolbar);
        String optionsStatus = getIntent().getStringExtra("FragmentLoad");
        toolbar.setTitle(optionsStatus);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LinearLayout mainLayout = findViewById(R.id.options);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boxes = new ArrayList<>();
        boxes.add((CheckBox)findViewById(R.id.checkArts));
        boxes.add((CheckBox)findViewById(R.id.checkSports));
        boxes.add((CheckBox)findViewById(R.id.checkPolitics));
        boxes.add((CheckBox)findViewById(R.id.checkEntrepreneur));
        boxes.add((CheckBox)findViewById(R.id.checBusiness));
        boxes.add((CheckBox)findViewById(R.id.checkTravel));

        for(CheckBox checkBox:boxes)
        {
            checkBox.setChecked(sharedPreferences.getBoolean(checkBox.getTag().toString(),true));
            checkBox.setOnCheckedChangeListener(this);
        }


        switch (optionsStatus)
        {
            case "Notifications":
                View view = getLayoutInflater().inflate(R.layout.notifications, mainLayout,false);
                mainLayout.addView(view);
                notificationState(this.getBaseContext(),view);
                break;
            case "Search Articles":
                View view2 = getLayoutInflater().inflate(R.layout.search_articles, mainLayout,false);
                mainLayout.addView(view2);
                break;

        }




    }







    private void notificationState(Context getcontext,View view)
    {
        Switch notificationSwitch = view.findViewById(R.id.switch_notif);
        notificationSwitch.setChecked(sharedPreferences.getBoolean("Notifications",false));


        final AlarmNotifications alarmNotifs = new AlarmNotifications(getcontext);
        final Context context = getcontext;

        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    sharedPreferences.edit().putBoolean("Notifications",true).apply();
                    alarmNotifs.setAlarm(context);
                    Intent i = new Intent(context, AlarmNotifications.class);
                    alarmNotifs.onReceive(context,i);

                    //alarmNotifs.test;
                }
                else
                {
                    sharedPreferences.edit().putBoolean("Notifications",false).apply();
                    alarmNotifs.cancelAlarm(context);
                }
                }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {

        for(CheckBox checkBox:boxes)
        {
            if (checkBox==compoundButton)
            {
                if(b)
                {

                    sharedPreferences.edit().putBoolean(checkBox.getTag().toString(),true).apply();
                }
                else
                {
                    sharedPreferences.edit().putBoolean(checkBox.getTag().toString(),false).apply();
                }
            }
        }

    }
}
