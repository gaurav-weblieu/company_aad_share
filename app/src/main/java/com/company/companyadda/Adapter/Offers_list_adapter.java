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

import com.company.companyadda.R;

public class Offers_list_adapter extends RecyclerView.Adapter<Offers_list_adapter.ViewHolder> {
    Context context;
    //private ArrayList<Address_list> Address_list;
    int lastPos=101;
    private int position;


    public Offers_list_adapter(Context context) {
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.offers_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

    }



    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_address;
        ImageView image_View_delete;
        CardView cardView_share;
        TextView text_Viw_address,text_Viw_name;

        public ViewHolder(View itemView) {
            super(itemView);


        }

    }




}