package com.mynews.flooo.mynews.Controllers.AlarmNotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.mynews.flooo.mynews.Controllers.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.Controllers.ApiRest.BuildRequest;
import com.mynews.flooo.mynews.Models.Results;


// This class is separate of the application.
// I use a receiver in the manifest file to run this thread.

// When the alarm is active, we run this class


public class AlarmOnReceive extends BroadcastReceiver implements  ApiCalls.Callbacks
{

    private Context context;


    //Creation of request and the notification

    @Override
    public void onReceive(Context context, Intent intent)
    {
        this.context =context;



        BuildRequest getInfo = new BuildRequest();
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
