package com.mynews.flooo.mynews;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mynews.flooo.mynews.ApiRest.FormatDataImage;
import com.mynews.flooo.mynews.ApiRest.News;
import com.mynews.flooo.mynews.ApiRest.ObjectResults;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>
{


    private Context context;
    private ObjectResults objectResults;

    public AdapterRecyclerView(ObjectResults obj,Context context)
    {
        this.objectResults = obj;
        this.context = context;
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
        //System.out.println(this.objectResults.size());
        return this.objectResults.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private  TextView name;
        private  TextView description;
        private ImageView imageIcon;
        private TextView dateText;


        public MyViewHolder(final View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.folder);
            description = itemView.findViewById(R.id.description);
            imageIcon = itemView.findViewById(R.id.imageViewIcon);
            dateText = itemView.findViewById(R.id.date);

         /*   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentPair.first)
                            .setMessage(currentPair.second)
                            .show();
                }
            }); */
        }

        public void updateView(News displayList)  {

            String textFolder = displayList.getSection();

            if(!displayList.getSubsection().equals(""))
            {
                textFolder = displayList.getSection()+" > "+displayList.getSubsection();
            }

            name.setText(textFolder);
            description.setText(displayList.getTitle());


            try
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
                SimpleDateFormat formatDisplay = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse(displayList.getDate());
                String currentDateandTime = formatDisplay.format(date);
                dateText.setText(currentDateandTime);

            }
            catch(ParseException e)
            {
                System.out.println(e);
            }

            for(FormatDataImage dataImage: displayList)
            {



                    if (dataImage.getFormat().equals("Standard Thumbnail"))
                    {
                        Picasso.with(context).load(dataImage.getUrl()).into(imageIcon);
                    }

            }

        }
    }

}


