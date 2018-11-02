package com.mynews.flooo.mynews.Models;


//This is my "news model" who contains informations of news

import java.util.ArrayList;

public class News extends ArrayList<FormatDataImage>
{


    private String section;
    private String subsection;
    private  String title;
    private  String url;
    private  String date;


    public String getDate(){return date;}
    public String getUrl(){return url;}
    public String getSubsection(){return subsection;}
    public String getTitle(){return title;}
    public String getSection(){return section;}

    public void setSection(String section){this.section=section;}
    public void setSubsection(String subsection){this.subsection=subsection;}
    public void setUrl(String url){this.url=url;}
    public void setTitle(String title){this.title=title;}
    public void setDate(String date){this.date=date;}

}
