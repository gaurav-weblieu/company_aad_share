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
import com.company.companyadda.Activities.Company_search;
import com.company.companyadda.ApiModels.CompanySearchListPojoModel;
import com.company.companyadda.Fragment.HomeFragment;
import com.company.companyadda.Pojo.ComapnySearchPOjo;
import com.company.companyadda.R;

import java.util.ArrayList;
import java.util.List;

public class Search_Com_list_adapter extends RecyclerView.Adapter<Search_Com_list_adapter.ViewHolder> {
    Context context;
    List<ComapnySearchPOjo> arrayList_name;
    private String radio_select;
    HomeFragment homeFragment;


    public Search_Com_list_adapter(Context context, List<ComapnySearchPOjo> arrayList_name,String radio_select,HomeFragment homeFragment) {
        this.context = context;
        this.arrayList_name = arrayList_name;
        this.radio_select = radio_select;
        this.homeFragment = homeFragment;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.search_list_com_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView_name.setText(arrayList_name.get(position).getName());
        holder.linear_layout_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Company_search.class);
                intent.putExtra("no", arrayList_name.get(position).getDataid());
                intent.putExtra("radio", radio_select);
                context.startActivity(intent);

                homeFragment.onSearchClick();

            }
        });



    }



    @Override
    public int getItemCount() {
        return arrayList_name.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linear_layout_com;
        ImageView image_View_delete;
        CardView cardView_share;
        TextView textView_name;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_name=itemView.findViewById(R.id.textView_name);
            linear_layout_com=itemView.findViewById(R.id.linear_layout_com);

        }

    }
}