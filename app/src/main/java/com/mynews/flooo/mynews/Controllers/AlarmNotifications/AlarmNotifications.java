package com.mynews.flooo.mynews.Controllers.AlarmNotifications;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.mynews.flooo.mynews.Models.Results;
import com.mynews.flooo.mynews.R;
import com.mynews.flooo.mynews.Controllers.ResultsActivity;

import java.util.Calendar;
import java.util.HashMap;


public class AlarmNotifications
{


    // I set alarm with AlarmManager and PendingIntent class

    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmOnReceive.class);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 7); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY ,pi); // Millisec * Second * Minute
    }

    // I used the method cancel to delete the alarm

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, AlarmNotifications.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }



    // The creation of notification is complicate, because we have many versions of Android, and libraries.
    //  I used Notification manager and the Notification Channel if and only if the version of Android is > version 8.0
    // A intent is create to go in the application to the ResultsActivity

    public boolean createNotification(Results results,Context context)
    {


        if(results.size()>1)
        {
            Intent intent = new Intent(context, ResultsActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            inboxStyle.setBigContentTitle("News Actualities");


            String sizeNews = Integer.toString(results.size());

            inboxStyle.addLine(sizeNews + " articles newest may  to be interest you !");

            // Create a Channel (Android 8)
            String channelId = "0";

            //Build a Notification object
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(context, channelId)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("My News")
                            .setContentText("content")
                            .setAutoCancel(true)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setContentIntent(pendingIntent)
                            .setStyle(inboxStyle);

            //Add the Notification to the Notification Manager and show it.
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Support Version >= Android 8
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence channelName = "Message provenant de Firebase";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
                notificationManager.createNotificationChannel(mChannel);
            }

            // Show notification
            notificationManager.notify("test", 0, notificationBuilder.build());
            return true;
        }
        else
        {
            return false;
        }
    }



}
