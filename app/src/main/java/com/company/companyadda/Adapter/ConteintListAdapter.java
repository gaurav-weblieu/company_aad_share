package com.company.companyadda.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.company.companyadda.Activities.WebTrial;
import com.company.companyadda.Pojo.AllcontientPojo;
import com.company.companyadda.Pojo.ArticlesListPojo;
import com.company.companyadda.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.List;

public class ConteintListAdapter extends RecyclerView.Adapter<ConteintListAdapter.ViewHolder> {
    Context context;
    List<AllcontientPojo> list;



    public ConteintListAdapter(Context context, List<AllcontientPojo> list) {
        this.context = context;
        this.list = list;



    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.all_contient_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)

    {
        AllcontientPojo allcontientPojo=  list.get(position);

        holder.textView_tot_cases.setText(allcontientPojo.getCases());
        holder.textView_to_cases.setText(allcontientPojo.getTodayCases());
        holder.textView_tot_death.setText(allcontientPojo.getDeaths());
        holder.textView_to_death.setText(allcontientPojo.getTodayDeaths());
        holder.textView_tot_recover.setText(allcontientPojo.getRecovered());
        holder.textView_to_recover.setText(allcontientPojo.getTodayRecovered());
        holder.textView_act_caese.setText(allcontientPojo.getActive());
        holder.textView_ctri_caese.setText(allcontientPojo.getCritical());
        holder.textView_cas_p_o_m.setText(allcontientPojo.getCasesPerOneMillion());
        holder.textView_death_p_o_m.setText(allcontientPojo.getDeathsPerOneMillion());
        holder.textView_country.setText(allcontientPojo.getContinent());

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView textView_tot_cases,textView_to_cases,textView_tot_death,
                textView_to_death,textView_tot_recover,textView_to_recover,
                textView_act_caese,textView_ctri_caese,textView_cas_p_o_m,textView_death_p_o_m,textView_country;


        public ViewHolder(View itemView) {
            super(itemView);

            textView_tot_cases=itemView.findViewById(R.id.textView_tot_cases);
            textView_to_cases=itemView.findViewById(R.id.textView_to_cases);
            textView_tot_death=itemView.findViewById(R.id.textView_tot_death);
            textView_to_death=itemView.findViewById(R.id.textView_to_death);
            textView_tot_recover=itemView.findViewById(R.id.textView_tot_recover);
            textView_to_recover=itemView.findViewById(R.id.textView_to_recover);
            textView_act_caese=itemView.findViewById(R.id.textView_act_caese);
            textView_ctri_caese=itemView.findViewById(R.id.textView_ctri_caese);
            textView_cas_p_o_m=itemView.findViewById(R.id.textView_cas_p_o_m);
            textView_death_p_o_m=itemView.findViewById(R.id.textView_death_p_o_m);
            textView_country=itemView.findViewById(R.id.textView_country);




        }

    }





}