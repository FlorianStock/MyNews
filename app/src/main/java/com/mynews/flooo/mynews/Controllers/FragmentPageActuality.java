package com.mynews.flooo.mynews.Controllers;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mynews.flooo.mynews.Controllers.ApiRest.ApiCalls;
import com.mynews.flooo.mynews.Models.Results;
import com.mynews.flooo.mynews.R;


public class FragmentPageActuality extends Fragment implements ApiCalls.Callbacks
{

    private static final String PAGE = "PageName";
    private String page;


    private AdapterRecyclerView adapterRecyclerView;
    private Results listnews;


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


        }

        this.adapterRecyclerView = new AdapterRecyclerView(this.listnews,this.getContext(),page);
        recyclerView.setAdapter(this.adapterRecyclerView);


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
