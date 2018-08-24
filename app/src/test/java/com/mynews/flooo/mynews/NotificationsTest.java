package com.mynews.flooo.mynews;

import android.content.Context;

import com.mynews.flooo.mynews.AlarmNotifications.AlarmNotifications;
import com.mynews.flooo.mynews.Models.Results;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;

public class NotificationsTest
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
    public void sendNotificationEmpty()
    {
        Results testEmpty = new Results();
        AlarmNotifications alarmNotifications = Mockito.mock(AlarmNotifications.class);
        alarmNotifications.createNotification(testEmpty,context);

    }
}
