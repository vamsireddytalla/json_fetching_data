package com.example.naveen.jsondata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>
{
    List<Model> modelList;
    Context context;

    public MyAdapter(List<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_xml,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i)
    {
       myHolder.textView.setText(modelList.get(i).getName());
        Picasso.with(context).load(modelList.get(i).getImage_url()).fit()
                .into(myHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelList!=null?modelList.size():0;
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView imageView;
        public MyHolder(@NonNull View itemView)
        {
            super(itemView);
            textView= itemView.findViewById(R.id.textViewName);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
