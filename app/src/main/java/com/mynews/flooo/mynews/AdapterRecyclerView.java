package com.mynews.flooo.mynews;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mynews.flooo.mynews.ApiRest.News;
import com.mynews.flooo.mynews.ApiRest.ObjectResults;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MyViewHolder>
{


    private ObjectResults objectResults;

    public AdapterRecyclerView(ObjectResults obj)
    {
        this.objectResults = obj;
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



        public MyViewHolder(final View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.folder);
            description = itemView.findViewById(R.id.description);

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

        public void updateView(News displayList)
        {
            Log.e("CallBackOnSuccess", displayList.getTitle());


            String textFolder = displayList.getSection()+" > "+displayList.getSubsection();
            name.setText(textFolder);
            description.setText(displayList.getTitle());

        }
    }

}


