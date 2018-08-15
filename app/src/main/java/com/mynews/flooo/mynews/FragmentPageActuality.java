package com.mynews.flooo.mynews;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.service.autofill.Dataset;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mynews.flooo.mynews.AlarmNotifications.GetInfoPreferences;
import com.mynews.flooo.mynews.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.ApiRest.News;
import com.mynews.flooo.mynews.ApiRest.Results;


import org.json.JSONException;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class FragmentPageActuality extends Fragment implements ApiCalls.Callbacks
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String PAGE = "PageName";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String page;
    private String mParam2;

    private AdapterRecyclerView adapterRecyclerView;
    private Results listnews;



    // TODO: Rename and change types and number of parameters
    public static FragmentPageActuality newInstance(String page)
    {
        FragmentPageActuality fragment = new FragmentPageActuality();

        Bundle args = new Bundle();
        args.putString(PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_page_actuality, container, false);
        page = getArguments().getString(PAGE);


        RecyclerView recyclerView = view.findViewById(R.id.listNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        if(listnews!=null){this.listnews.clear();}

        this.listnews = new Results();


        switch(page)
        {
            case "Top Stories":
                ApiCalls.getTopStories(this);
                break;
            case "Most Popular":
                ApiCalls.getMostPopular(this);
                break;
            case "world":
                ApiCalls.getSection(this,"world");
                break;

            //default:  ApiCalls.getTopStories(this);
        }

        this.adapterRecyclerView = new AdapterRecyclerView(this.listnews,this.getContext(),page);
        recyclerView.setAdapter(this.adapterRecyclerView);

        // Inflate the layout for this fragment
        return view;


    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {

    }



    @Override
    public void onDetach()
    {
        super.onDetach();

    }

    @Override
    public void onResponse(@Nullable Results listResultsJson)
    {

        //System.out.println(listResultsJson.size());
        if(listResultsJson!=null)
        {
            //Log.e("CallBackOnSucess", listResultsJson.get(0).get(0).getFormat());


            listnews.clear();
            listnews.addAll(listResultsJson) ;
            adapterRecyclerView.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure()
    {


    }



}
