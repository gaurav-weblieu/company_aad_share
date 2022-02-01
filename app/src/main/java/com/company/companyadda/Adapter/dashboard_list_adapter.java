package com.company.companyadda.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.company.companyadda.Activities.CompanyRegister;
import com.company.companyadda.Activities.Details;
import com.company.companyadda.Activities.GST;
import com.company.companyadda.Activities.IncomeTaxFilling;
import com.company.companyadda.Activities.LLP;
import com.company.companyadda.Activities.New_service;
import com.company.companyadda.Activities.ROC;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.R;

import java.util.ArrayList;
import java.util.List;

public class dashboard_list_adapter extends RecyclerView.Adapter<dashboard_list_adapter.ViewHolder> {
    Context context;
    //private ArrayList<Address_list> Address_list;
    int lastPos=101;
    private int position;
    ArrayList<Integer> image_list;
    ArrayList<String> name_list;


    public dashboard_list_adapter(Context context,ArrayList<String> name_list,ArrayList<Integer> image_list) {
        this.context = context;
        this.image_list = image_list;
        this.name_list = name_list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.dashboard_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.image_main.setImageResource(image_list.get(position));
        holder.textView_name.setText(name_list.get(position));

        holder.cardView_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (position){
                    case 0:
                        context.startActivity(new Intent(context, CompanyRegister.class));

                        break;

                    case 1:
                        context.startActivity(new Intent(context, LLP.class));

                        break;

                    case 2:
                        context.startActivity(new Intent(context, Details.class));

                        break;

                    case 3:
                        context.startActivity(new Intent(context, GST.class));

                        break;

                    case 4:
                        context.startActivity(new Intent(context, ROC.class));

                        break;

                    case 5:
                        context.startActivity(new Intent(context, IncomeTaxFilling



                                .class));

                        break;
                }


            }
        });

    }



    @Override
    public int getItemCount() {
        return image_list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_dash;
        ImageView image_main;
        CardView cardView_share;
        TextView textView_name;

        public ViewHolder(View itemView) {
            super(itemView);

            image_main=itemView.findViewById(R.id.image_main);
            textView_name=itemView.findViewById(R.id.textView_name);
            cardView_dash=itemView.findViewById(R.id.cardView_dash);


        }

    }




}