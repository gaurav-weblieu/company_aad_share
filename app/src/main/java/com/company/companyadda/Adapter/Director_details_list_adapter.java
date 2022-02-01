package com.company.companyadda.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.companyadda.Activities.Director_Related_comp;
import com.company.companyadda.Pojo.CompanyChargesPojo;
import com.company.companyadda.Pojo.SearchDirectorPojo;
import com.company.companyadda.R;

import java.util.List;

public class Director_details_list_adapter extends RecyclerView.Adapter<Director_details_list_adapter.ViewHolder> {
    Context context;
    List<SearchDirectorPojo> arrayList_name;


    public Director_details_list_adapter(Context context, List<SearchDirectorPojo> arrayList_name) {
        this.context = context;
        this.arrayList_name = arrayList_name;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.diractor_details_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.textView_din.setText(arrayList_name.get(position).getDIN());
        holder.textView_dir_name.setText(arrayList_name.get(position).getNAME());
        holder.textView_design.setText(arrayList_name.get(position).getDESIGNATION());
        holder.textView_date.setText(arrayList_name.get(position).getDATE_JOIN());
        holder.textView_date_reg.setText(arrayList_name.get(position).getDATE_RESIGN());


        holder.textView_dir_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Director_Related_comp.class);
                intent.putExtra("din",holder.textView_din.getText().toString());
                intent.putExtra("name",holder.textView_dir_name.getText().toString());
                intent.putExtra("design",holder.textView_design.getText().toString());
                intent.putExtra("date_join",holder.textView_date.getText().toString());
                intent.putExtra("date_regin",holder.textView_date_reg.getText().toString());
                context.startActivity(intent);
            }
        });

        holder.textView_din.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Director_Related_comp.class);
                intent.putExtra("din",holder.textView_din.getText().toString());
                intent.putExtra("name",holder.textView_dir_name.getText().toString());
                intent.putExtra("design",holder.textView_design.getText().toString());
                intent.putExtra("date_join",holder.textView_date.getText().toString());
                intent.putExtra("date_regin",holder.textView_date_reg.getText().toString());
                context.startActivity(intent);
            }
        });

        holder.imageView_refresh_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }



    @Override
    public int getItemCount() {
        return arrayList_name.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_date,textView_design,textView_dir_name,textView_din,textView_date_reg;
        TextView imageView_refresh_dir;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_din=itemView.findViewById(R.id.textView_din);
            textView_design=itemView.findViewById(R.id.textView_design);
            textView_dir_name=itemView.findViewById(R.id.textView_dir_name);
            textView_date=itemView.findViewById(R.id.textView_date);
            textView_date_reg=itemView.findViewById(R.id.textView_date_reg);
            imageView_refresh_dir=itemView.findViewById(R.id.imageView_refresh_dir);

        }

    }
}