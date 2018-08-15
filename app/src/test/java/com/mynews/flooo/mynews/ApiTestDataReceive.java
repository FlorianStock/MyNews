package com.mynews.flooo.mynews;

import android.support.annotation.Nullable;

import com.mynews.flooo.mynews.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.ApiRest.Results;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */



public class ApiTestDataReceive implements ApiCalls.Callbacks
{



    @Before
    public void setUp() throws Exception
    {
        //.MockitoAnnotations.initMocks(this);

    }

    @Test
    public void callGetTopStories()
    {
        ApiCalls.getTopStories(this);
    }

    @Test
    public void callGetMostPopular()
    {
        ApiCalls.getMostPopular(this);
    }

    @Test
    public void callGetSection()
    {
        ApiCalls.getSection(this,"world");

    }


    @Override
    public void onResponse(@Nullable Results listNews)
    {

        if(listNews!=null)
        {
            System.out.println(listNews.size());
        }

    }

    @Override
    public void onFailure()
    {

    }
}