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

import com.company.companyadda.Activities.City_select;
import com.company.companyadda.Activities.Company_search;
import com.company.companyadda.Pojo.DirRelatedPojo;
import com.company.companyadda.Pojo.PaymentPojo;
import com.company.companyadda.R;

import java.util.ArrayList;
import java.util.List;

public class Dir_related_list_adapter extends RecyclerView.Adapter<Dir_related_list_adapter.ViewHolder> {
    Context context;
    //private ArrayList<Address_list> Address_list;
    int lastPos=101;
    private int position;
    ArrayList<String> arrayList_name;
    City_select city_select;
    List<DirRelatedPojo> dirRelatedPojoList;


    public Dir_related_list_adapter(Context context, List<DirRelatedPojo> dirRelatedPojoList) {
        this.context = context;
        this.dirRelatedPojoList = dirRelatedPojoList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.direcor_related_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


        holder.company_cin.setText(dirRelatedPojoList.get(position).getCIN());
        holder.company_join.setText(dirRelatedPojoList.get(position).getDATE_JOIN());
        holder.company_date_regin.setText(dirRelatedPojoList.get(position).getDATE_RESIGN());
        holder.company_des.setText(dirRelatedPojoList.get(position).getDESIGNATION());
        holder.company_com_name.setText(dirRelatedPojoList.get(position).getCOMPANY_NAME());
        String str=dirRelatedPojoList.get(position).getCOMPANY_STATUS();
        if (str!=null){
            if (str.length()>=6){
                String str1=str.substring(0,6);
                holder.company_status.setText(str1);
            }
        }


        holder.company_paid_cap.setText(dirRelatedPojoList.get(position).getPAIDUP_CAPITAL());

        holder.company_cin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Company_search.class);
                intent.putExtra("no", holder.company_cin.getText().toString());
                intent.putExtra("radio", "Company");
                context.startActivity(intent);

            }
        });

        holder.company_com_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Company_search.class);
                intent.putExtra("no", holder.company_cin.getText().toString());
                intent.putExtra("radio", "Company");
                context.startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {
        return  dirRelatedPojoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_cilty;
        ImageView image_View_delete;
        CardView cardView_share;
        TextView company_cin,company_join,company_date_regin,company_des,company_com_name,company_status,company_paid_cap;

        public ViewHolder(View itemView) {
            super(itemView);

            company_cin=itemView.findViewById(R.id.company_cin);
            company_join=itemView.findViewById(R.id.company_join);
            company_date_regin=itemView.findViewById(R.id.company_date_regin);
            company_des=itemView.findViewById(R.id.company_des);
            company_com_name=itemView.findViewById(R.id.company_com_name);
            company_status=itemView.findViewById(R.id.company_status);
            company_paid_cap=itemView.findViewById(R.id.company_paid_cap);


        }

    }




}