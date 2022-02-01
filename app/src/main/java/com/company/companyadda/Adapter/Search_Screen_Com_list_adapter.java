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

import com.company.companyadda.Activities.Company_search;
import com.company.companyadda.Pojo.ComapnySearchPOjo;
import com.company.companyadda.R;

import java.util.List;

public class Search_Screen_Com_list_adapter extends RecyclerView.Adapter<Search_Screen_Com_list_adapter.ViewHolder> {
    Context context;
    List<ComapnySearchPOjo> arrayList_name;
    private String radio_select;


    public Search_Screen_Com_list_adapter(Context context, List<ComapnySearchPOjo> arrayList_name, String radio_select) {
        this.context = context;
        this.arrayList_name = arrayList_name;
        this.radio_select = radio_select;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.search_list_screen_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.textView_com_name.setText(arrayList_name.get(position).getName());
        holder.textView_cin_no.setText(arrayList_name.get(position).getDataid());
        holder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Company_search.class);
                intent.putExtra("no", arrayList_name.get(position).getDataid());
                intent.putExtra("radio", radio_select);
                context.startActivity(intent);

            }
        });


    }



    @Override
    public int getItemCount() {
        return arrayList_name.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image_View_delete;
        CardView cardView2;
        TextView textView_com_name,textView_cin_no;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_cin_no=itemView.findViewById(R.id.textView_cin_no);
            textView_com_name=itemView.findViewById(R.id.textView_com_name);
            cardView2=itemView.findViewById(R.id.cardView2);


        }

    }




}