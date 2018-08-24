package com.mynews.flooo.mynews.ApiRest;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mynews.flooo.mynews.Models.Results;

import java.text.DateFormat;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterfaceEndPoints
{
    // Top stories
    @GET("svc/topstories/v2/{section}.json")
    Call<Results>getTopStories(@Path("section") String section,
                               @Query("api-key") String API_KEY);

    // Most Popular API
    @GET("svc/mostpopular/v2/mostshared/all-sections/30.json")
    Call<Results> getMostPopular(@Query("api-key") String apiKey);


    @GET("svc/search/v2/articlesearch.json")
    Call<Results> getNews(@Query("api-key") String apiKey,
                          @QueryMap Map<String,String> filters);

    GsonAdapterLists gsonAdapterLists = new GsonAdapterLists();

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Results.class, gsonAdapterLists)
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
