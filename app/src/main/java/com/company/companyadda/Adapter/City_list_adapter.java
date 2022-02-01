package com.company.companyadda.Adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.company.companyadda.Activities.City_select;
import com.company.companyadda.R;

import java.util.ArrayList;

public class City_list_adapter extends RecyclerView.Adapter<City_list_adapter.ViewHolder> {
    Context context;
    //private ArrayList<Address_list> Address_list;
    int lastPos=101;
    private int position;
    ArrayList<String> arrayList_name;
    City_select city_select;


    public City_list_adapter( Context context, ArrayList<String> arrayList_name,City_select city_select) {
        this.context = context;
        this.arrayList_name = arrayList_name;
        this.city_select = city_select;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.city_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.city_name.setText(arrayList_name.get(position));
        holder.cardView_cilty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastPos=position;
                notifyDataSetChanged();

                city_select.onCityClick(arrayList_name.get(position));

            }
        });

        if (lastPos==position){
            holder.cardView_cilty.setCardBackgroundColor(context.getResources().getColor(R.color.yellow));
            holder.cardView_cilty.setElevation(3);
            holder.city_name.setTextColor(context.getResources().getColor(R.color.black));

        }else {
            holder.cardView_cilty.setCardBackgroundColor(context.getResources().getColor(R.color.white));
            holder.cardView_cilty.setElevation(0);
            holder.city_name.setTextColor(context.getResources().getColor(R.color.black));
        }
    }



    @Override
    public int getItemCount() {
        return arrayList_name.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_cilty;
        ImageView image_View_delete;
        CardView cardView_share;
        TextView city_name;

        public ViewHolder(View itemView) {
            super(itemView);

            city_name=itemView.findViewById(R.id.city_name);
            cardView_cilty=itemView.findViewById(R.id.cardView_cilty);


        }

    }




}