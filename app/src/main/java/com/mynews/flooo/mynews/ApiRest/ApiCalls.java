package com.mynews.flooo.mynews.ApiRest;


import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiCalls
{

        private static final String API_KEY = "f06e19cbd14d467d9f345b263f9c3cfe";

        // 1 - Creating a callback
        public interface Callbacks
        {
            void onResponse(@Nullable Results listNews);
            void onFailure();
        }

        // 2 - Public method to start fetching users following by Jake Wharton


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

        private static void EnqueueCall(Call call, Callbacks callbacks )
        {
            final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<>(callbacks);

            call.enqueue(new Callback <Results>()
            {

                @Override
                public void onResponse(Call<Results> call, Response<Results>response)
                {
                    // 2.5 - Call the proper callback used in controller (MainFragment)
                    if (callbacksWeakReference.get() != null)
                    {
                        //System.out.println(response.body().getNumResults());
                        callbacksWeakReference.get().onResponse(response.body());
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

