package com.mynews.flooo.mynews.ApiRest;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaceEndPoints
{

    @GET("svc/topstories/v2/home.json")
    Call <ObjectResults> getTopStories(@Query("api-key") String API_KEY);

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(ObjectResults.class, new GsonAdapterLists())
            .setDateFormat(DateFormat.LONG)
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .setVersion(1.0)
            .create();


     Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

}
