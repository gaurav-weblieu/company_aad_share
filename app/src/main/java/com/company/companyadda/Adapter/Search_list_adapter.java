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

import com.company.companyadda.Activities.Fill_form;
import com.company.companyadda.Activities.New_service;
import com.company.companyadda.Pojo.SearchServicePoJo;
import com.company.companyadda.Pojo.SearchServicePoJoService;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.R;

import java.util.ArrayList;
import java.util.List;

public class Search_list_adapter extends RecyclerView.Adapter<Search_list_adapter.ViewHolder> {
    Context context;
    //private ArrayList<Address_list> Address_list;
    int lastPos=101;
    private int position;
    ArrayList<String> arrayList_name;
    List<SearchServicePoJoService> service_lists_main;

    String cat_name="START REGISTRATION";
    int one_check=0;


    public Search_list_adapter(Context context, List<SearchServicePoJoService> service_lists_main) {
        this.context = context;
        this.service_lists_main = service_lists_main;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.serach_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.service_name.setText(service_lists_main.get(position).getService_name());
        holder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int posistion= Integer.parseInt(service_lists_main.get(position).getService_id());

                Intent intent= new Intent(context, Fill_form.class);
                intent.putExtra("pos",String.valueOf(posistion-1));
                context.startActivity(intent);
            }
        });

           /* if (position > 0) {

                if (cat_name.equals(service_lists_main.get(position).getCategory_name())) {
                    holder.service_type.setVisibility(View.GONE);
                    holder.service_type.setText(cat_name);

                } else {
                    cat_name = service_lists_main.get(position).getCategory_name();
                    holder.service_type.setVisibility(View.VISIBLE);
                    holder.service_type.setText(cat_name);

                }
            } else {
                holder.service_type.setText(cat_name);

            }*/

    }



    @Override
    public int getItemCount() {
        return service_lists_main.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_View_delete;
        CardView cardView2;
        TextView service_name,service_type;

        public ViewHolder(View itemView) {
            super(itemView);

            service_name=itemView.findViewById(R.id.service_name);
            service_type=itemView.findViewById(R.id.service_type);
            cardView2=itemView.findViewById(R.id.cardView2);


        }

    }




}