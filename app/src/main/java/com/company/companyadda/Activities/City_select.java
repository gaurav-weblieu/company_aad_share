package com.company.companyadda.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.company.companyadda.Adapter.City_list_adapter;
import com.company.companyadda.Interface.*;
import com.company.companyadda.R;

import java.util.ArrayList;

public class City_select extends AppCompatActivity implements OnCitySelect{

    private RecyclerView recycleView_city;
    City_list_adapter adapter;
    ArrayList<String> arrayList_name= new ArrayList<>();
    private String cityName=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);

        arrayList_name.add("Delhi NCR");
        arrayList_name.add("Delhi");
        arrayList_name.add("Noida");
        arrayList_name.add("Greater Noida");
        arrayList_name.add("Gurugram");
        arrayList_name.add("Ghaziabad");
        arrayList_name.add("Other");

        recycleView_city=findViewById(R.id.recycleView_city);

        recycleView_city.setLayoutManager(new LinearLayoutManager(City_select.this ));
        adapter = new City_list_adapter(City_select.this,arrayList_name,City_select.this);
        recycleView_city.setAdapter(adapter);

    }

    public void continueCitySelect(View view) {
        if (cityName != null) {
            startActivity(new Intent(this, DashBoard.class));
            finish();
        }else {
            Toast.makeText(this, "Please select any one city !", Toast.LENGTH_SHORT).show();
        }
    }

    public void back(View view) {
        finish();
    }


    @Override
    public void onCityClick(String Name) {
        cityName=Name;
    }
}