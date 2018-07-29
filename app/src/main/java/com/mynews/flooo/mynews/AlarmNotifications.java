package com.mynews.flooo.mynews;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mynews.flooo.mynews.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.ApiRest.News;
import com.mynews.flooo.mynews.ApiRest.Results;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AlarmNotifications extends BroadcastReceiver implements ApiCalls.Callbacks
{

    SharedPreferences sharedPreferences;
    HashMap<String, Boolean> checkBoxStates;

    @Override
    public void onReceive(Context context, Intent intent)
    {

        //PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        //wl.acquire();


        // Put here YOUR code.

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);


        checkBoxStates=new HashMap<String, Boolean>();

        checkBoxStates.put("Arts", sharedPreferences.getBoolean("Arts",true));
        checkBoxStates.put("Sports", sharedPreferences.getBoolean("Sports",true));
        checkBoxStates.put("Politics", sharedPreferences.getBoolean("Politics",true));
        checkBoxStates.put("Entrepreneurs", sharedPreferences.getBoolean("Entrepreneurs",true));
        checkBoxStates.put("Business", sharedPreferences.getBoolean("Business",true));
        checkBoxStates.put("Travel", sharedPreferences.getBoolean("Travel",true));


        StringBuilder sectionsString = new StringBuilder();
        sectionsString.append("news_desk:(");



        for(Map.Entry<String, Boolean> entry : checkBoxStates.entrySet())
        {


            if(entry.getValue())
            {
                sectionsString.append(entry.getKey());
                sectionsString.append(", ");

            }

        }

        sectionsString.deleteCharAt(sectionsString.length()-2);
        sectionsString.append(")");
        String sections= sectionsString.toString();


        ApiCalls.getArticleSearch(this,sections,"");

    }




    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmNotifications.class);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 7); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY ,pi); // Millisec * Second * Minute
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmNotifications.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }

    @Override
    public void onResponse(@Nullable Results listNews)
    {

        for(News article:listNews)
        {

            System.out.println(  article.getSection());

        }

        //createNotification();
        //System.out.println(listNews.size());

    }

    @Override
    public void onFailure()
    {

    }



}
