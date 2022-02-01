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

public class NewListAdapter extends RecyclerView.Adapter<NewListAdapter.ViewHolder> {
    Context context;
    //private ArrayList<Address_list> Address_list;
    int lastPos=101;
    private int position;
    ArrayList<ArticlesListPojo> list;
   // FirebaseTranslator englishGermanTranslator;
   Translator englishGermanTranslator;
    boolean is_hindi=false;



    public NewListAdapter(Context context, ArrayList<ArticlesListPojo> list, boolean is_hindi) {
        this.context = context;
        this.list = list;
        this.is_hindi = is_hindi;

        TranslatorOptions options =
                new TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.HINDI)
                        .build();
        englishGermanTranslator =
                Translation.getClient(options);


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.news_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)

    {

        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_date.setText(list.get(position).getPublished_date());
        holder.textView_des.setText(list.get(position).getDescription());
        holder.textView_cradit.setText(list.get(position).getSource().getTitle());


        if (is_hindi){
            downloadModal(holder.textView_title.getText().toString(),
                    holder.textView_des.getText().toString(),
                    holder);
        }




        /*String string = holder.textView_title.getText().toString();
        downloadModal(string,holder);*/

        Glide.with(context)
                .load(list.get(position).getThumbnail())
                .fitCenter()
                .into(holder.imageView_thubnail);

        Glide.with(context)
                .load(list.get(position).getSource().getFavicon())
                .fitCenter()
                .into(holder.imageView_fav);

        holder.textView_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebTrial.class);
                intent.putExtra("url",list.get(position).getLink());
                context.startActivity(intent);
            }
        });

        holder.linearLayout_cradit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, WebTrial.class);
                intent.putExtra("url",list.get(position).getSource().getUrl());
                context.startActivity(intent);


            }
        });


        holder.imageView_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Company Adda News");
                    String shareMessage= "\nThis News Reference is from CompanyAdda \n\n";
                    shareMessage = shareMessage + list.get(position).getLink();
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    context.startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    //e.toString();
                }



            }
        });


        holder.imageView_whatapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,list.get(position).getLink() );
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                context.startActivity(sendIntent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView_cilty;
        ImageView imageView_thubnail,imageView_fav,imageView_share,imageView_whatapp;
        LinearLayout linearLayout_cradit;
        TextView textView_title,textView_date,textView_des,textView_cradit;

        public ViewHolder(View itemView) {
            super(itemView);

            textView_title=itemView.findViewById(R.id.textView_title);
            textView_date=itemView.findViewById(R.id.textView_date);
            imageView_thubnail=itemView.findViewById(R.id.imageView_thubnail);
            textView_des=itemView.findViewById(R.id.textView_des);
            textView_cradit=itemView.findViewById(R.id.textView_cradit);
            imageView_fav=itemView.findViewById(R.id.imageView_fav);
            linearLayout_cradit=itemView.findViewById(R.id.linearLayout_cradit);
            imageView_share=itemView.findViewById(R.id.imageView_share);
            imageView_whatapp=itemView.findViewById(R.id.imageView_whatapp);



        }

    }



    private void downloadModal(String input, String s, ViewHolder holder) {
        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();
        englishGermanTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(
                        new OnSuccessListener() {
                            @Override
                            public void onSuccess(@NonNull Object o) {
                                translateLanguage(input,holder);
                                translateLanguage2(s,holder);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Model couldn’t be downloaded or other internal error.
                                // ...
                            }
                        });
    }

    private void translateLanguage(String input, ViewHolder holder) {
        englishGermanTranslator.translate(input)
                .addOnSuccessListener(
                        new OnSuccessListener() {
                            @Override
                            public void onSuccess(@NonNull Object o) {
                                holder.textView_title.setText(o.toString());
                              //  Toast.makeText(context, "ssssss"+o.toString(), Toast.LENGTH_SHORT).show();
                            }

                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Error"+e, Toast.LENGTH_SHORT).show();

                            }
                        });
    }

    private void translateLanguage2(String input, ViewHolder holder) {
        englishGermanTranslator.translate(input)
                .addOnSuccessListener(
                        new OnSuccessListener() {
                            @Override
                            public void onSuccess(@NonNull Object o) {
                                holder.textView_des.setText(o.toString());
                                //  Toast.makeText(context, "ssssss"+o.toString(), Toast.LENGTH_SHORT).show();
                            }

                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Error"+e, Toast.LENGTH_SHORT).show();

                            }
                        });
    }


}