package com.mynews.flooo.mynews;

public class NewsModel
{


    public String date;
    public String title;
    public String description;
    public String url;
    public String thumbnail;

    public NewsModel (String title, String date, String description, String thumbnail, String url)
    {
        this.title = title;
        this.date = date;
        this.description = description;
        this.thumbnail = thumbnail;
        this.url = url;

    }
}
