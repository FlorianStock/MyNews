package com.mynews.flooo.mynews;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;


import com.mynews.flooo.mynews.AlarmNotifications.GetInfoPreferences;
import com.mynews.flooo.mynews.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.ApiRest.Results;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchNewsTestStructure
{
    @Mock Context context;
    @Mock SharedPreferences prefs;

    //SharedPreferences sharedPrefs = Mockito.mock(SharedPreferences.class);
    String queryTerm = "This is a test for a query request.";

    @Before
    public void before() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        this.prefs = Mockito.mock(SharedPreferences.class);
        this.context = Mockito.mock(Context.class);

        Mockito.when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(prefs);
    }

    @Test
    public void QueryTermBuildForRequest_WithSample() throws Exception
    {

        String queryTermbuilded = new GetInfoPreferences().queryTermBuild(queryTerm);
        assertEquals("This+is+a+test+for+query+request+.", queryTermbuilded);

    }

    @Test
    public void QueryTermBuildForRequest_WithSharedPrefRecorded() throws Exception
    {

        Mockito.when(prefs.getString(anyString(), anyString())).thenReturn("QueryTerm");
        /*
        Mockito.when(sharedPreferences.getString(anyString(), anyString())).thenReturn("QueryTerm");
        String queryTermbuilded = new GetInfoPreferences().queryTermBuild(sharedPreferences.);

        assertEquals("This+is+a+test+for+query+request+.", queryTermbuilded);
        */
    }

}
