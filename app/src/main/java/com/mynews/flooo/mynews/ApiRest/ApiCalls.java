package com.mynews.flooo.mynews.ApiRest;


import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiCalls {

        private static final String API_KEY = "f06e19cbd14d467d9f345b263f9c3cfe";

        // 1 - Creating a callback
        public interface Callbacks
        {
            void onResponse(@Nullable ObjectResults listnews);
            void onFailure();
        }

        // 2 - Public method to start fetching users following by Jake Wharton


        public  static void getTopStories(Callbacks callbacks)
        {
            final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

            ApiInterfaceEndPoints apiInterfaceEndPoints = ApiInterfaceEndPoints.retrofit.create(ApiInterfaceEndPoints.class);
            Call <ObjectResults> call = apiInterfaceEndPoints.getTopStories(API_KEY);

            call.enqueue(new Callback <ObjectResults>()
            {

                @Override
                public void onResponse(Call<ObjectResults> call, Response<ObjectResults>response)
                {
                    // 2.5 - Call the proper callback used in controller (MainFragment)


                    if (callbacksWeakReference.get() != null)
                    {
                        callbacksWeakReference.get().onResponse(response.body());
                        Log.e("CallBackOnSucess", "Sucess JSON PARSE");
                    }
                }

                @Override
                public void onFailure(Call<ObjectResults> call, Throwable t)
                {

                    // 2.5 - Call the proper callback used in controller (MainFragment)
                    if (callbacksWeakReference.get() != null){Log.e("CallBackOnFailure", t.getMessage());}


                }
            });
        }

}

