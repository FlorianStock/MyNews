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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mynews.flooo.mynews.ApiRest.ApiCalls;

import com.mynews.flooo.mynews.ApiRest.News;
import com.mynews.flooo.mynews.ApiRest.ObjectResults;


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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AdapterRecyclerView adapterRecyclerView;
    private ObjectResults listnews;



    // TODO: Rename and change types and number of parameters
    public static FragmentPageActuality newInstance()
    {
        FragmentPageActuality fragment = new FragmentPageActuality();

        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_page_actuality, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.listNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        this.listnews = new ObjectResults();

        //ApiCalls.fetchUserFollowing(this, "JakeWharton");
        ApiCalls.getTopStories(this);

        this.adapterRecyclerView = new AdapterRecyclerView(this.listnews,this.getContext());
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
    public void onResponse(@Nullable ObjectResults listResultsJson)
    {
        if(listResultsJson!=null)
        {
            //Log.e("CallBackOnSucess", listResultsJson.get(0).getTitle());
            System.out.println(listResultsJson.size());
            listnews.addAll(listResultsJson) ;
            adapterRecyclerView.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure()
    {


    }



}
