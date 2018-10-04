package com.mynews.flooo.mynews.Models;




import java.util.ArrayList;

public class News extends ArrayList<FormatDataImage>
{

    // This class is the model for any news, with the elements what we need

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
