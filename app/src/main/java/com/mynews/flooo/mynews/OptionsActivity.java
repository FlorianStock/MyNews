package com.mynews.flooo.mynews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.mynews.flooo.mynews.AlarmNotifications.AlarmNotifications;
import com.mynews.flooo.mynews.AlarmNotifications.AlarmOnReceive;
import com.mynews.flooo.mynews.AlarmNotifications.GetInfoPreferences;

import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity implements CheckBox.OnCheckedChangeListener
{


    private SharedPreferences sharedPreferences;
    private ArrayList<CheckBox> boxes;
    private EditText editTextQueryTerms;
    private  String optionsStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Toolbar toolbar = findViewById(R.id.toolbar);
        optionsStatus = getIntent().getStringExtra("LoadLayout");
        toolbar.setTitle(optionsStatus);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LinearLayout mainLayout = findViewById(R.id.options);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boxes = new ArrayList<CheckBox>();
        boxes.add((CheckBox)findViewById(R.id.checkArts));
        boxes.add((CheckBox)findViewById(R.id.checkSports));
        boxes.add((CheckBox)findViewById(R.id.checkPolitics));
        boxes.add((CheckBox)findViewById(R.id.checkEntrepreneur));
        boxes.add((CheckBox)findViewById(R.id.checBusiness));
        boxes.add((CheckBox)findViewById(R.id.checkTravel));


        editTextQueryTerms = findViewById(R.id.editTextQueryTerms);

        switch (optionsStatus)
        {
            case "Notifications":
                View view = getLayoutInflater().inflate(R.layout.notifications, mainLayout,false);
                mainLayout.addView(view);

                editTextQueryTerms.setText(sharedPreferences.getString("QueryTerm",""));

                for(CheckBox checkBox:boxes)
                {
                    checkBox.setChecked(sharedPreferences.getBoolean(checkBox.getTag().toString(),true));
                    checkBox.setOnCheckedChangeListener(this);
                }

                notificationState(this.getBaseContext(),view);
                break;

            case "Search Articles":
                View viewSearch = getLayoutInflater().inflate(R.layout.search_articles, mainLayout,false);
                mainLayout.addView(viewSearch);
                searchState(this.getBaseContext(),viewSearch);
                break;

        }




    }





    private void searchState(Context getContext,View view)
    {
        Button clickSearch = view.findViewById(R.id.searchButton);
        clickSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(VerifyAtLeastOneCheckBox())
                {
                    Intent intentSearch = new Intent(OptionsActivity.this, ResultsActivity.class);



                    ArrayList<String> checkBoxToSend= new ArrayList<>();

                    for(CheckBox box:boxes)
                    {
                        if(box.isChecked())
                        {
                            checkBoxToSend.add(box.getTag().toString());
                        }
                    }

                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("CheckBox",checkBoxToSend);
                    bundle.putString("EditText",editTextQueryTerms.getText().toString());
                    intentSearch.putExtras(bundle);

                    //GetInfoPreferences.declaresVariablesWidgets(boxes,editTextQueryTerms.getText().toString());
                    startActivity(intentSearch);
                }
                else
                {
                    Toast.makeText(getBaseContext(),"One checkbox must be checked for uses the search", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private boolean VerifyAtLeastOneCheckBox()
    {
        for(CheckBox checkBox:boxes)
        {
            if(checkBox.isChecked())
            {
                return true;
            }
        }
        return false;


    }
    private void notificationState(Context getContext,View view)
    {
        Switch notificationSwitch = view.findViewById(R.id.switch_notif);
        notificationSwitch.setChecked(sharedPreferences.getBoolean("Notifications",false));


        final AlarmNotifications alarmNotifs = new AlarmNotifications();
        final AlarmOnReceive  receive = new AlarmOnReceive();
        final Context context = getContext;



        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                Boolean activeNotification = VerifyAtLeastOneCheckBox();

                if(isChecked)
                {

                    if(activeNotification)
                    {
                        sharedPreferences.edit().putString("QueryTerm",editTextQueryTerms.getText().toString()).apply();
                        sharedPreferences.edit().putBoolean("Notifications", true).apply();
                        alarmNotifs.setAlarm(context);
                        Intent i = new Intent(context, AlarmNotifications.class);

                        //FOR TEST ONLY
                        receive.onReceive(context, i);
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"One checkbox must be checked for active the notifications", Toast.LENGTH_SHORT).show();
                        buttonView.setChecked(false);
                    }


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
    public boolean onSupportNavigateUp()
    {
        onBackPressed();

        if(optionsStatus.equals("Notifications"))
        {
            sharedPreferences.edit().putString("QueryTerm",editTextQueryTerms.getText().toString()).apply();
        }
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b)
    {

        for(CheckBox checkBox:boxes)
        {
            if (checkBox==compoundButton && optionsStatus.equals("Notifications"))
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
