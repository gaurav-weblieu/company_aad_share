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

import com.company.companyadda.Activities.City_select;
import com.company.companyadda.Pojo.Wallet_history_pojo;
import com.company.companyadda.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Wallet_list_adapter extends RecyclerView.Adapter<Wallet_list_adapter.ViewHolder> {
    Context context;
    List<Wallet_history_pojo> wallet_history_pojos;
    City_select city_select;


    public Wallet_list_adapter(Context context, List<Wallet_history_pojo> wallet_history_pojos) {
        this.context = context;
        this.wallet_history_pojos = wallet_history_pojos;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.history_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        String str_date= wallet_history_pojos.get(position).getCreated_at();
        String deliveryDate=str_date;
        SimpleDateFormat dateFormatprev = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d = null;
        try {
            d = dateFormatprev.parse(deliveryDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //EEE for day
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM");
        String changedDate = dateFormat.format(d);


        String deliveryDate1=str_date;
        SimpleDateFormat dateFormatprev1= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d1 = null;
        try {
            d1 = dateFormatprev1.parse(deliveryDate1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm a");
        String changedDate1 = dateFormat1.format(d1);

        holder.textView_time.setText(changedDate+","+changedDate1);

        holder.textView_time.setText(changedDate+","+changedDate1);
        holder.textView_rupee_symbol.setText(R.string.Rs);

        holder.ref_id.setText(wallet_history_pojos.get(position).getReferal_code_id());
        holder.refer_code.setText(wallet_history_pojos.get(position).getReferal_code());
        holder.text_Price.setText(wallet_history_pojos.get(position).getAmount());


    }



    @Override
    public int getItemCount() {
        return wallet_history_pojos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_View_delete;
        CardView cardView_share;
        TextView ref_id,refer_code,text_Price,textView_rupee_symbol,textView_time;

        public ViewHolder(View itemView) {
            super(itemView);

            ref_id=itemView.findViewById(R.id.ref_id);
            textView_time=itemView.findViewById(R.id.textView_time);
            refer_code=itemView.findViewById(R.id.note);
            text_Price=itemView.findViewById(R.id.text_Price);
            textView_rupee_symbol=itemView.findViewById(R.id.textView_rupee_symbol);


        }

    }




}