package com.mynews.flooo.mynews;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.mynews.flooo.mynews.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.ApiRest.News;
import com.mynews.flooo.mynews.ApiRest.Results;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmNotifications extends BroadcastReceiver implements ApiCalls.Callbacks
{

    SharedPreferences sharedPreferences;
    HashMap<String, Boolean> checkBoxStates;
    Context context;

    public AlarmNotifications(Context c)
    {
        context=c;
    }
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



        Map counterArticlesbySection = new HashMap<String, Boolean>();

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

        createNotification();
        //System.out.println(listNews.size());

    }

    @Override
    public void onFailure()
    {

    }


    public void createNotification()
    {


        // 1 - Create an Intent that will be shown when user will click on the Notification
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // 2 - Create a Style for the Notification
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("News Actualities");
        inboxStyle.addLine("ee");

        // 3 - Create a Channel (Android 8)
        String channelId = "0";

        // 4 - Build a Notification object
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My News")
                        .setContentText("content")
                        .setAutoCancel(true)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setContentIntent(pendingIntent)
                        .setStyle(inboxStyle);

        // 5 - Add the Notification to the Notification Manager and show it.
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 6 - Support Version >= Android 8
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            CharSequence channelName = "Message provenant de Firebase";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        // 7 - Show notification
        notificationManager.notify("test", 0, notificationBuilder.build());
    }



}
