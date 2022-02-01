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
import com.company.companyadda.Pojo.MyRequestDetailsPojo;
import com.company.companyadda.Pojo.Wallet_history_pojo;
import com.company.companyadda.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Active_list_adapter extends RecyclerView.Adapter<Active_list_adapter.ViewHolder> {
    Context context;
    List<MyRequestDetailsPojo> myRequesList;
    City_select city_select;


    public Active_list_adapter(Context context, List<MyRequestDetailsPojo> myRequesList) {
        this.context = context;
        this.myRequesList = myRequesList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.my_request_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.user_name.setText(myRequesList.get(position).getUser_name());
        holder.user_sub.setText(myRequesList.get(position).getUser_subject());
        holder.user_service.setText(myRequesList.get(position).getService_name());
        String str_date= myRequesList.get(position).getCreated_at();
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

        holder.date.setText(changedDate+","+changedDate1);


        holder.mobile.setText(myRequesList.get(position).getUser_mobile());
        holder.email.setText(myRequesList.get(position).getUser_email());

        holder.textView_number.setText(String.valueOf(position+1));

    }


    @Override
    public int getItemCount() {
        return myRequesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_View_delete;
        CardView cardView_share;
        TextView user_name,user_sub,date,mobile,email,textView_number,user_service;

        public ViewHolder(View itemView) {
            super(itemView);

            user_name=itemView.findViewById(R.id.user_name);
            user_sub=itemView.findViewById(R.id.user_sub);
            user_service=itemView.findViewById(R.id.user_service);
            date=itemView.findViewById(R.id.date);
            mobile=itemView.findViewById(R.id.mobile);
            email=itemView.findViewById(R.id.email);
            textView_number=itemView.findViewById(R.id.number_req);


        }

    }




}