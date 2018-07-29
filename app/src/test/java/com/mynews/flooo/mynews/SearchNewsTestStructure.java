package com.mynews.flooo.mynews;

import android.support.annotation.Nullable;

import com.mynews.flooo.mynews.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.ApiRest.Results;

import org.junit.Before;
import org.junit.Test;

public class SearchNewsTestStructure implements ApiCalls.Callbacks
{
    @Before
    public void setUp() throws Exception
    {
        //.MockitoAnnotations.initMocks(this);
        ApiCalls.getNews((ApiCalls.Callbacks) this);
    }

    @Override
    public void onResponse(@Nullable Results listNews)
    {

        if(listNews !=null)
        {
            creationAlarm();
        }
    }

    @Override
    public void onFailure()
    {

    }

    @Test
    public void creationAlarm()
    {
        // Creation with mock ect..
    }

    @Test
    public void verifySharedPreferences()
    {
        // Creation with mock ect..
    }


}
