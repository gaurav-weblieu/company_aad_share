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
import com.company.companyadda.Fragment.HomeFragment;
import com.company.companyadda.Pojo.ComapnySearchPOjo;
import com.company.companyadda.Pojo.CompanyChargesPojo;
import com.company.companyadda.R;

import java.util.List;

public class Compnay_charges_list_adapter extends RecyclerView.Adapter<Compnay_charges_list_adapter.ViewHolder> {
    Context context;
    List<CompanyChargesPojo> arrayList_name;


    public Compnay_charges_list_adapter( Context context,List<CompanyChargesPojo> arrayList_name) {
        this.context = context;
        this.arrayList_name = arrayList_name;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.comnay_charges_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.company_car_id.setText(arrayList_name.get(position).getCHARGE_ID());
        holder. company_car_cin.setText(arrayList_name.get(position).getCIN());
        holder.company_char_sol_no.setText(arrayList_name.get(position).getSLNO());
        holder.company_char_id.setText(arrayList_name.get(position).getCHARGE_ID());
        holder.company_char_srn.setText(arrayList_name.get(position).getSRN());
        holder.company_char_holder.setText(arrayList_name.get(position).getCHARGE_HOLDER());
        holder.company_date_mod.setText(arrayList_name.get(position).getDATE_MODIFIED());
        holder.company_char_date_sat.setText(arrayList_name.get(position).getDATE_SATISFIED());
        holder.company_char_amount.setText(arrayList_name.get(position).getAMOUNT());
        holder. company_char_addres.setText(arrayList_name.get(position).getADDRESS());
        holder. company_char_hash.setText(arrayList_name.get(position).getHASH());
        holder.company_char_timestamp.setText(arrayList_name.get(position).getTIMESTAMP());
        holder.company_char_status.setText(arrayList_name.get(position).getSTATUS());


    }



    @Override
    public int getItemCount() {
        return arrayList_name.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView company_car_id,company_car_cin,company_char_sol_no,company_char_id,company_char_srn,company_char_holder,company_date_mod,
                company_char_date_sat,company_char_amount,company_char_addres,company_char_hash,company_char_timestamp,company_char_status;


        public ViewHolder(View itemView) {
            super(itemView);

            company_car_id=itemView.findViewById(R.id.company_car_id);
            company_car_cin=itemView.findViewById(R.id.company_car_cin);
            company_char_sol_no=itemView.findViewById(R.id.company_char_sol_no);
            company_char_id=itemView.findViewById(R.id.company_char_id);
            company_char_srn=itemView.findViewById(R.id.company_char_srn);
            company_char_holder=itemView.findViewById(R.id.company_char_holder);
            company_date_mod=itemView.findViewById(R.id.company_date_mod);
            company_char_date_sat=itemView.findViewById(R.id.company_char_date_sat);
            company_char_amount=itemView.findViewById(R.id.company_char_amount);
            company_char_addres=itemView.findViewById(R.id.company_char_addres);
            company_char_hash=itemView.findViewById(R.id.company_char_hash);
            company_char_timestamp=itemView.findViewById(R.id.company_char_timestamp);
            company_char_status=itemView.findViewById(R.id.company_char_status);

        }

    }
}