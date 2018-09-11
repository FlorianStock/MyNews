package com.mynews.flooo.mynews;



import com.mynews.flooo.mynews.Controllers.ApiRest.BuildRequest;


import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class BuildRequestTest
{





    @Before
    public void before() throws Exception
    {

    }

    @Test
    public void QueryTermBuildForRequest_WithExemple() throws Exception
    {
        String queryTerm = "This is a test for a query request.";
        String queryTermbuilded = new BuildRequest().queryTermBuild(queryTerm);

        assertEquals("This+is+a+test+for+query+request+.", queryTermbuilded);

    }

    @Test
    public void QueryTermBuildDesk_withExample() throws Exception
    {
        ArrayList<String> listSections = new ArrayList<>() ;

        listSections.clear();
        listSections.add("Arts");
        listSections.add("Politics");

        String deskbuilded = new BuildRequest().buildSectionsStringForSearch(listSections);
        assertEquals("news_desk:(\"Politics\" \"Arts\" )", deskbuilded);

    }



}
