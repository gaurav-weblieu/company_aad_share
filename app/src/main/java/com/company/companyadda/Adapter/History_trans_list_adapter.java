package com.company.companyadda.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.company.companyadda.Activities.City_select;
import com.company.companyadda.Activities.PaymentDetailsScreen;
import com.company.companyadda.Pojo.PaymentPojo;
import com.company.companyadda.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class History_trans_list_adapter extends RecyclerView.Adapter<History_trans_list_adapter.ViewHolder> {
    Context context;
    //private ArrayList<Address_list> Address_list;
    int lastPos=101;
    private int position;
    ArrayList<String> arrayList_name;
    City_select city_select;
    List<PaymentPojo> paymentList;


    public History_trans_list_adapter(Context context, List<PaymentPojo> paymentList) {
        this.context = context;
        this.paymentList = paymentList;


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

       String str_date= paymentList.get(position).getCreated_at();
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


        if (paymentList.get(position).getNote().equals("")){
            holder.note.setVisibility(View.GONE);
        }

        holder.textView_rupee_symbol.setText(R.string.Rs);
        //holder.ref_id.setText(paymentList.get(position).getApprovalRefNo());
        holder.text_Price.setText(paymentList.get(position).getAmount());
        holder.note.setText(paymentList.get(position).getNote());
       // holder.user_id.setText("User ID "+paymentList.get(position).getUser_id());
        holder.linear_layout_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PaymentDetailsScreen.class);
                intent.putExtra("amount",paymentList.get(position).getAmount());
                intent.putExtra("Date",paymentList.get(position).getCreated_at());
                intent.putExtra("pay_id",paymentList.get(position).getPayment_id());
                intent.putExtra("note",paymentList.get(position).getNote());
                context.startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {
        return  paymentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_cilty;
        ImageView image_View_delete;
        CardView cardView_share;
        TextView textView_rupee_symbol,ref_id,textView_time,text_Price,note;
        LinearLayout linear_layout_de;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_rupee_symbol=itemView.findViewById(R.id.textView_rupee_symbol);
            ref_id=itemView.findViewById(R.id.ref_id);
            textView_time=itemView.findViewById(R.id.textView_time);
            text_Price=itemView.findViewById(R.id.text_Price);
            linear_layout_de=itemView.findViewById(R.id.linear_layout_de);
            note=itemView.findViewById(R.id.note);


        }

    }




}