package com.mynews.flooo.mynews.ApiRest;


import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;


class GsonAdapterLists extends TypeAdapter<Results>
{

    private String name = null;
    Results list;

    @Override
    public void write(JsonWriter out, Results value) throws IOException
    {

    }

    @Override
    public Results read(JsonReader reader) throws IOException {


        list = new Results();
        ArrayList<News> listNews = new ArrayList<>();

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
                else if ("response".equals(name))
                {
                    reader.beginObject();


                        while (reader.hasNext())
                        {

                            //

                            if(reader.nextName().equals("docs"))
                            {

                                SearchArticles(reader);

                            }
                            else
                            {
                                reader.skipValue();
                            }


                        }

                    reader.endObject();






                }
                else if ("results".equals(name))
                {

                    reader.beginArray();


                    while (reader.hasNext())
                    {
                        reader.beginObject();
                        News article = new News();

                        while (reader.hasNext())
                        {
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
                                case "multimedia":
                                    SearchImagesTopStories(reader, article);
                                    break;
                                case "media":
                                    SearchImagesMostPopular(reader, article);
                                    break;
                                default:
                                    token = reader.peek();
                                    reader.skipValue();
                            }


                        }
                        reader.peek();
                        reader.endObject();
                        list.add(article);


                    }

                    reader.endArray();

                }
                else
                {
                    reader.skipValue(); //avoid some unhandle events
                }

            }

            reader.endObject();

            //

            return list;


    }

    private void SearchArticles(JsonReader reader) throws IOException
    {
        reader.peek();
        reader.beginArray();



        while (reader.hasNext())
        {
            reader.beginObject();
            News article = new News();

            while (reader.hasNext())
            {

                switch (reader.nextName())
                {
                    case "web_url":
                        article.setUrl(reader.nextString());
                        break;
                    case "multimedia":

                        reader.beginArray();

                        while (reader.hasNext())
                        {
                            reader.beginObject();
                            FormatDataImage image = new FormatDataImage();

                            while (reader.hasNext())
                            {
                                switch (reader.nextName())
                                {
                                    case "subtype":
                                        image.setFormat(reader.nextString());
                                        break;
                                    case "url":
                                        image.setUrl(reader.nextString());
                                        break;
                                    default:reader.skipValue();

                                }
                            }

                            reader.endObject();
                            article.add(image);
                        }

                        reader.endArray();

                        break;
                    case "section_name":article.setSection(reader.nextString());
                        break;
                    case "headline":
                        reader.beginObject();

                        while (reader.hasNext())
                        {

                            switch (reader.nextName())
                            {
                                case "main":article.setTitle(reader.nextString());
                                    break;
                                default: reader.skipValue();
                            }


                        }
                        reader.endObject();

                    break;
                    default:reader.skipValue();



                }
            }

            list.add(article);
            reader.endObject();
        }

        reader.endArray();

    }


    private void SearchImagesTopStories(JsonReader reader,News article) throws IOException
        {

            reader.peek();
            reader.beginArray();

            while (reader.hasNext())
            {
                reader.beginObject();
                FormatDataImage formatDataImage = new FormatDataImage();

                while (reader.hasNext())
                {

                    switch (reader.nextName())
                    {
                        case "url":
                            reader.peek();
                            formatDataImage.setUrl(reader.nextString());
                            break;
                        case "format":
                            reader.peek();
                            formatDataImage.setFormat(reader.nextString());
                            break;
                        case "height":
                            reader.peek();
                            formatDataImage.setHeight(reader.nextInt());
                            break;
                        case "width":
                            reader.peek();
                            formatDataImage.setWidth(reader.nextInt());
                            break;
                        default:
                            reader.peek();
                            reader.skipValue();

                    }

                }
                article.add(formatDataImage);
                reader.endObject();
            }

            reader.endArray();


        }

    private void SearchImagesMostPopular(JsonReader reader,News article) throws IOException
    {

        reader.peek();
        reader.beginArray();

        while (reader.hasNext())
        {
            reader.beginObject();


            while (reader.hasNext())
            {
                //System.out.println(name);

                if(reader.nextName().equals("media-metadata"))
                {
                    reader.beginArray();


                    while (reader.hasNext())
                    {
                        FormatDataImage formatDataImage = new FormatDataImage();
                        reader.beginObject();

                        while(reader.hasNext())
                        {

                            switch (reader.nextName())
                            {
                                case "url":
                                    reader.peek();
                                    formatDataImage.setUrl(reader.nextString());
                                    break;
                                case "format":
                                    reader.peek();
                                    formatDataImage.setFormat(reader.nextString());
                                    break;
                                case "height":
                                    reader.peek();
                                    formatDataImage.setHeight(reader.nextInt());
                                    break;
                                case "width":
                                    reader.peek();
                                    formatDataImage.setWidth(reader.nextInt());
                                    break;
                                default:
                                    reader.peek();
                                    reader.skipValue();
                            }
                        }

                        reader.endObject();
                        article.add(formatDataImage);
                    }


                    reader.endArray();

                }
                else
                {
                    reader.peek();
                    reader.skipValue();
                }


            }

            reader.endObject();
        }

        reader.endArray();


    }

}

