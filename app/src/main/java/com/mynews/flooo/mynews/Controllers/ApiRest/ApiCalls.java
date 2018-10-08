package com.mynews.flooo.mynews.Controllers.ApiRest;


import android.support.annotation.Nullable;
import android.util.Log;
import com.mynews.flooo.mynews.Models.Results;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
//////////////////////////////////////////////////////////////////////////
This is a class what i used for Call Request Everywhere in my application with statics functions.
//////////////////////////////////////////////////////////////////////////

 */

public class ApiCalls
{


        //This is my key for API NY Times.
        private static final String API_KEY = "f06e19cbd14d467d9f345b263f9c3cfe";


        // Creating a callback for response , for the interface with Recyclerview




       // Creating a callback

        public interface Callbacks
        {
            void onResponse(@Nullable Results listNews) throws ParseException;
            void onFailure();
        }



        public  static void getTopStories(Callbacks callbacks)
        {

            ApiInterfaceEndPoints apiInterfaceEndPoints = ApiInterfaceEndPoints.retrofit.create(ApiInterfaceEndPoints.class);
            Call <Results> call = apiInterfaceEndPoints.getTopStories("home",API_KEY);
            EnqueueCall(call,callbacks);
        }
        public  static void getMostPopular(Callbacks callbacks)
        {
            ApiInterfaceEndPoints apiInterfaceEndPoints = ApiInterfaceEndPoints.retrofit.create(ApiInterfaceEndPoints.class);
            Call <Results> call = apiInterfaceEndPoints.getMostPopular(API_KEY);
            EnqueueCall(call,callbacks);
        }
        public  static void getSection(Callbacks callbacks,String section)
        {
            ApiInterfaceEndPoints apiInterfaceEndPoints = ApiInterfaceEndPoints.retrofit.create(ApiInterfaceEndPoints.class);
            Call <Results> call = apiInterfaceEndPoints.getTopStories(section,API_KEY);
            EnqueueCall(call,callbacks);
        }

    public  static void getArticleSearch(Callbacks callbacks,String sections, String QueryTerms)
    {

        Map<String,String> mapQueryInfo= new HashMap<>();

        //Section search
        mapQueryInfo.put("fq",sections);
        //Search news articles
        mapQueryInfo.put("sort","newest");
        //Search with date
        //mapQueryInfo.put("begin_date","newest");
        //mapQueryInfo.put("end_date","newest");

       if(QueryTerms!=null){mapQueryInfo.put("q", QueryTerms);}

        //Delimited list of fields
        mapQueryInfo.put("fl","multimedia,web_url,section_name,headline,news_desk,pub_date");
        //Enables HighLighting in search results
        mapQueryInfo.put("hl","true");
        //mapQueryInfo.put("facet_filter","true");

        ApiInterfaceEndPoints apiInterfaceEndPoints = ApiInterfaceEndPoints.retrofit.create(ApiInterfaceEndPoints.class);
        Call <Results> call = apiInterfaceEndPoints.getNews(API_KEY,mapQueryInfo);
        EnqueueCall(call,callbacks);
    }



        private static void EnqueueCall(Call call, Callbacks callbacks )
        {
            final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<>(callbacks);

            call.enqueue(new Callback <Results>()
            {

                @Override
                public void onResponse(Call<Results> call, Response<Results>response)
                {
                    // 2.5 - Call the proper callback used in controller (MainFragment)


                    if (callbacksWeakReference.get() != null && response.isSuccessful())
                    {

                        try {
                            callbacksWeakReference.get().onResponse(response.body());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        callbacksWeakReference.clear();
                        Log.e("CallBackOnSucess", "Sucess JSON PARSE");
                    }
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t)
                {
                    // 2.5 - Call the proper callback used in controller (MainFragment)
                    if (callbacksWeakReference.get() != null)
                    {
                        Log.e("CallBackOnFailure", t.getMessage());
                    }

                }
            });

        }

}

