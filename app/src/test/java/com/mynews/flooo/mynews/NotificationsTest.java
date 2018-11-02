package com.mynews.flooo.mynews;

import android.content.Context;
import android.support.annotation.Nullable;

import com.mynews.flooo.mynews.Controllers.AlarmNotifications.AlarmNotifications;
import com.mynews.flooo.mynews.Controllers.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.Models.FormatDataImage;
import com.mynews.flooo.mynews.Models.News;
import com.mynews.flooo.mynews.Models.Results;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Test my Notifications class with real list, and list empty
 *
 *
 */

public class NotificationsTest implements ApiCalls.Callbacks
{

    @Mock Context context;
    @Mock AlarmNotifications alarmNotifications;

    @Before
    public void setUp() throws Exception
    {

        alarmNotifications = mock(AlarmNotifications.class);
        context = mock(Context.class);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void sendNotification()
    {
        //Test with a real list.
        ApiCalls.getArticleSearch(this,"All","");
        // Test with a  Empty List.
        Results listEmpty = new Results();
        Boolean stateFalse = alarmNotifications.createNotification(listEmpty,context);
        assertEquals(false,stateFalse);


    }

    @Override
    public void onResponse(@Nullable Results listNews) throws ParseException
    {
        Boolean stateTrue = alarmNotifications.createNotification(listNews,context);
        assertEquals(true,stateTrue);
    }

    @Override
    public void onFailure()
    {

    }



}
