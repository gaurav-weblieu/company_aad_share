package com.company.companyadda.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.company.companyadda.Activities.City_select;
import com.company.companyadda.Activities.DocUpload;
import com.company.companyadda.Pojo.DocPojo;
import com.company.companyadda.Pojo.MyRequestDetailsPojo;
import com.company.companyadda.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Doc_list_adapter extends RecyclerView.Adapter<Doc_list_adapter.ViewHolder> {
    Context context;
    List<DocPojo> myRequesList;
    City_select city_select;
    DocUpload docUpload;


    public Doc_list_adapter(Context context, List<DocPojo> myRequesList,DocUpload docUpload) {
        this.context = context;
        this.myRequesList = myRequesList;
        this.docUpload = docUpload;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.doc_ulpoaded_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView_name.setText(myRequesList.get(position).getDocument_name());
        holder.date.setText(myRequesList.get(position).getCreated_at());
        Glide
                .with(context)
                .load(myRequesList.get(position).getDocument_image())
                .centerCrop()
                .into(holder.image);

        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docUpload.onDocClick(myRequesList.get(position).getDocument_image(),"1");
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docUpload.onDocClick(myRequesList.get(position).getDocument_image(),"2");
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docUpload.onDeleteClick(myRequesList.get(position).getUser_document_id());
            }
        });
    }





    @Override
    public int getItemCount() {
        return myRequesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView date,textView_name;
        ImageView image,download,share,delete;

        public ViewHolder(View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.date);
            textView_name=itemView.findViewById(R.id.textView_name);
            image=itemView.findViewById(R.id.image);
            download=itemView.findViewById(R.id.download);
            share=itemView.findViewById(R.id.share);
            delete=itemView.findViewById(R.id.delete);


        }

    }




}