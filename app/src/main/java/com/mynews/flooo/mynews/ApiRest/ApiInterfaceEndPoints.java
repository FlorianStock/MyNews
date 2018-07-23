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
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterfaceEndPoints
{
    // Top stories
    @GET("svc/topstories/v2/{section}.json")
    Call<Results>getTopStories(@Path("section") String section,
                                @Query("api-key") String API_KEY);

    // Most Popular API
    @GET("svc/mostpopular/v2/mostshared/all-sections/30.json")
    Call<Results> getMostPopular(@Query("api-key") String apiKey);


    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Results.class, new GsonAdapterLists())
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
