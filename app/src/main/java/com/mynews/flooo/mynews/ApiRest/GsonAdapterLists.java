package com.mynews.flooo.mynews.ApiRest;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

class GsonAdapterLists extends TypeAdapter<ObjectResults>
{

    @Override
    public void write(JsonWriter out, ObjectResults value) throws IOException
    {

    }

    @Override
    public ObjectResults read(JsonReader reader) throws IOException
    {


        ObjectResults list = new ObjectResults();
        ArrayList<News> listNews = new ArrayList<>();

        String name = null;


        reader.beginObject();

            while (reader.hasNext())
            {


                JsonToken token = reader.peek();

                if (token.equals(JsonToken.NAME))
                {
                    //get the current token
                    name = reader.nextName();
                }


                if ("num_results".equals(name))
                {
                    token = reader.peek();
                    list.setNumResults(reader.nextInt());
                }
                else if ("results".equals(name))
                {

                    reader.beginArray();



                    while(reader.hasNext())
                    {
                        reader.beginObject();
                        News article = new News();

                        while(reader.hasNext())
                        {



                            //Log.e("CallBackOnSuccess", listResultsJson.toString());
                            // Log.e("CallBackOnSuccess", reader.nextName());

                            switch (reader.nextName())
                            {
                                case "section":

                                    token = reader.peek();
                                    article.setSection(reader.nextString());
                                    break;
                                case "title":
                                    token = reader.peek();
                                    article.setTitle(reader.nextString());
                                    break;
                                case "subsection":
                                    token = reader.peek();
                                    article.setSubsection(reader.nextString());
                                    break;
                                case "published_date":
                                    token = reader.peek();
                                    article.setDate(reader.nextString());
                                    break;
                                case "url":
                                    token = reader.peek();
                                    article.setUrl(reader.nextString());
                                    break;

                                default:
                                    token = reader.peek();
                                    reader.skipValue();
                                    break;
                            }
                        }
                        reader.endObject();
                        list.add(article);







                    }

                   reader.endArray();

                }
                else {
                    reader.skipValue(); //avoid some unhandle events
                }


            }

            reader.endObject();
            reader.close();


            return list;
    }


}
