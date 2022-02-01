package com.company.companyadda.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.company.companyadda.Activities.Fill_form;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.R;

import java.util.ArrayList;
import java.util.List;

public class MySimpleArrayAdapter extends ArrayAdapter<ServiceListPojo> {
    Context context;
    List<ServiceListPojo> service_lists_main;
    String cat_name="START REGISTRATION";
    int last_pos=0;



    public MySimpleArrayAdapter(List<ServiceListPojo> data, Context context) {
        super(context,R.layout.serach_list_item,data);
        this.service_lists_main = data;
        this.context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.serach_list_item, parent, false);
        CardView cardView2;
        TextView service_name,service_type;

        service_name=rowView.findViewById(R.id.service_name);
        service_type=rowView.findViewById(R.id.service_type);
        cardView2=rowView.findViewById(R.id.cardView2);

       service_name.setText(service_lists_main.get(position).getService_name());
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, Fill_form.class);
                intent.putExtra("pos",String.valueOf(position));
                context.startActivity(intent);
            }
        });



            if (cat_name.equals(service_lists_main.get(position).getCategory_name())) {
                service_type.setVisibility(View.GONE);
                service_type.setText(cat_name);

            } else {
                cat_name = service_lists_main.get(position).getCategory_name();
                service_type.setVisibility(View.VISIBLE);
                service_type.setText(cat_name);

            }


        return rowView;
    }
}
