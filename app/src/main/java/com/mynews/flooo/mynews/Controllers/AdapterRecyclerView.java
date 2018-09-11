package com.mynews.flooo.mynews.Controllers;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mynews.flooo.mynews.Models.FormatDataImage;
import com.mynews.flooo.mynews.Models.News;
import com.mynews.flooo.mynews.Models.Results;
import com.mynews.flooo.mynews.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>
{


    private Context context;
    private Results objectResults;
    private String  page;

    public AdapterRecyclerView(Results obj, Context context,String page)
    {
        this.objectResults = obj;
        this.context = context;
        this.page = page;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.updateView(objectResults.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.objectResults.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private  TextView name;
        private  TextView description;
        private ImageView imageIcon;
        private TextView dateText;
        private String webUrl;

        public MyViewHolder(final View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.folder);
            description = itemView.findViewById(R.id.description);
            imageIcon = itemView.findViewById(R.id.imageViewIcon);
            dateText = itemView.findViewById(R.id.date);



            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("URL",webUrl);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

        public void updateView(News displayList)
        {

            String textFolder = displayList.getSection();


                if (!displayList.getSubsection().equals(""))
                {
                    textFolder = displayList.getSection() + " > " + displayList.getSubsection();
                }


            name.setText(textFolder);
            description.setText(displayList.getTitle());
            dateText.setText(ChangeDate(displayList.getDate()));

            webUrl = displayList.getUrl();

            for(FormatDataImage dataImage: displayList)
            {


                    if (dataImage.getFormat().equals("Standard Thumbnail") || dataImage.getFormat().equals("thumbnail") )
                    {
                        Picasso.with(context).load(dataImage.getUrl()).into(imageIcon);
                    }

            }

        }

        private String ChangeDate(String dateData)
        {
            String dateChanged=null;

            if(page.equals("world") || page.equals("Top Stories"))
            {

                try
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                    SimpleDateFormat formatDisplay = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = format.parse(dateData);
                    dateChanged = formatDisplay.format(date);
                }
                catch(ParseException e)
                {

                }

            }
            else if(page.equals("Most Popular"))
            {
                try
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatDisplay = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = format.parse(dateData);
                    dateChanged = formatDisplay.format(date);
                }
                catch(ParseException e)
                {

                }
            }
            else if(page.equals("Search Articles") || page.equals("Notifications"))
            {

                try
                {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+SSS");
                    SimpleDateFormat formatDisplay = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = format.parse(dateData);
                    dateChanged = formatDisplay.format(date);
                }
                catch (ParseException e)
                {

                }
            }


            return dateChanged;


        }
    }

}


