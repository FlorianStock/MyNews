package com.mynews.flooo.mynews;

import android.support.annotation.Nullable;

import com.mynews.flooo.mynews.Controllers.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.Models.Results;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;


/**
 * Test the API if we have a response
 *
 *
 */



public class ApiTestDataReceive implements ApiCalls.Callbacks
{



    @Before
    public void setUp() throws Exception
    {


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