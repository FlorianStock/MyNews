package com.mynews.flooo.mynews.Models;

public class FormatDataImage
{

    // The news extends FormatDatImage for a second list for the images


    private String url;
    private String format;
    private int width;
    private int height;

    public void setUrl(String url){this.url=url;}
    public void setFormat(String format){this.format=format;}
    public void setWidth(int width){this.width=width;}
    public void setHeight(int height){this.height=height;}

    public String getUrl(){return url;}
    public String getFormat(){return format;}
    public int getwidth(){return width;}
    public int getHeight(){return height;}

}
