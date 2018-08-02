package com.mynews.flooo.mynews.AlarmNotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.annotation.Nullable;

import com.mynews.flooo.mynews.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.ApiRest.Results;

public class AlarmOnReceive extends BroadcastReceiver implements  ApiCalls.Callbacks
{

    private Context context;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        this.context =context;



        GetInfoPreferences getInfo = new GetInfoPreferences();
        String sections = getInfo.buildSectionsStringForNotification(context);
        String queryTerm = getInfo.getQueryTerm();

        ApiCalls.getArticleSearch(this,sections,queryTerm);
    }

    @Override
    public void onResponse(@Nullable Results listNews)
    {
        AlarmNotifications alarmNotifications = new AlarmNotifications();
        alarmNotifications.createNotification(listNews,context);
    }

    @Override
    public void onFailure()
    {

    }
}
