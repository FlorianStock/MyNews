package com.mynews.flooo.mynews.ApiRest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Results extends ArrayList<News>
{

    @SerializedName("num_results")
    @Expose
    private  int num_results;


    public void setNumResults(int num) {num_results=num;}
    public int getNumResults(){return num_results;}



}
