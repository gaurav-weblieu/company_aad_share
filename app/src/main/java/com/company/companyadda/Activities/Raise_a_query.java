package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.company.companyadda.ApiModels.FormModels;
import com.company.companyadda.ApiModels.RaiseAQueryModel;
import com.company.companyadda.ApiModels.ReferModel;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.R;
import com.company.companyadda.ViewModel.Category_View_Model;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.company.companyadda.Activities.Fill_form.isValidEmail;

public class Raise_a_query extends AppCompatActivity {

    Spinner spinner_service_type;
    Category_View_Model model;
    ArrayList<String> arrayList_service_name=new ArrayList<>();
    ArrayList<String> arrayList_service_id=new ArrayList<>();
    private String service_id="null";
    private List<ServiceListPojo> service_lists_main;
    String pos;
    ProgressBar progress_bar;
    EditText editText_name,editText_phone,editText_email,editText_message;
    MaterialButton material_button_save;
    private SharedPreferences sharedPreferences_login;
    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_a_query);

        spinner_service_type=findViewById(R.id.spinner_service_type);

        editText_name=findViewById(R.id.editText_name);
        editText_phone=findViewById(R.id.editText_phone);
        editText_email=findViewById(R.id.editText_email);
        editText_message=findViewById(R.id.editText_message);
        material_button_save=findViewById(R.id.material_button_save);
        progress_bar=findViewById(R.id.progress_bar);


        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        user_id=sharedPreferences_login.getString("user_id","");

        editText_name.setText(sharedPreferences_login.getString("name","").toString());
        String phone=sharedPreferences_login.getString("phone","").toString();
        String fina_phone= phone.substring(3,13);
        editText_phone.setText(fina_phone);
        editText_email.setText(sharedPreferences_login.getString("email","").toString());


        model = new ViewModelProvider(this).get(Category_View_Model.class);
        model.getAllService().observe(this, new Observer<List<ServiceListPojo>>() {
            @Override
            public void onChanged(List<ServiceListPojo> address_lists) {
                if (address_lists!=null){
                    service_lists_main=address_lists;
                    for (int i=0;i<service_lists_main.size();i++){
                        arrayList_service_name.add(service_lists_main.get(i).getService_name());
                        arrayList_service_id.add(service_lists_main.get(i).getService_id());
                    }

                    ArrayAdapter aa = new ArrayAdapter(Raise_a_query.this,android.R.layout.simple_spinner_item,arrayList_service_name);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spinner_service_type.setAdapter(aa);
                    if (pos!=null){
                        spinner_service_type.setSelection(Integer.parseInt(pos));

                    }

                    //  Toast.makeText(Fill_form.this, "size"+address_lists.size(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Raise_a_query.this, "null data main", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner_service_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                service_id=arrayList_service_id.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        material_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText_name.getText().toString().length()<=0){
                    editText_name.setError("Please Enter Name");
                }else
                if (editText_phone.getText().toString().length()<=0){
                    editText_phone.setError("Please Enter Phone");
                }else
                if (editText_phone.getText().toString().length()<10){
                    editText_phone.setError("Please Valid Phone");
                }else
                if (editText_message.getText().toString().length()<=0){
                    editText_message.setError("Please Enter Message");
                }else
                if (editText_email.getText().toString().length()<=0){
                    editText_email.setError("Please Enter Email");
                }else
                if (!isValidEmail(editText_email.getText().toString())) {
                    editText_email.setError("Please enter valid email");
                } else if (service_id=="null"){
                    Toast.makeText(Raise_a_query.this, "Please Select Service Type", Toast.LENGTH_SHORT).show();
                }else {

                    progress_bar.setVisibility(View.VISIBLE);
                    new Thread(runnable).start();

                }

            }
        });

    }


    Runnable runnable= new Runnable() {
        @Override
        public void run() {

            String name=editText_name.getText().toString();
            String emil=editText_email.getText().toString();
            String phone=editText_phone.getText().toString();
            String message=editText_message.getText().toString();

            Call<FormModels> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .quotes(name,emil,phone,service_id,user_id,message);

            myProductsCall.enqueue(new Callback<FormModels>() {
                @Override
                public void onResponse(Call<FormModels> call, Response<FormModels> response) {
                    if (response.body() != null) {
                        FormModels formModels = response.body();
                        if (formModels.getResult().getSTATUS().equals("true")){
                            //    Toast.makeText(Fill_form.this, "true", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(Raise_a_query.this,Request_successfully.class));
                            finish();
                            progress_bar.setVisibility(View.GONE);

                        }
                        else if (formModels.getResult().getSTATUS().equals("false")) {
                            Toast.makeText(Raise_a_query.this, "false"+formModels.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);

                        }
                    } else if (response.body() == null) {
                        Toast.makeText(Raise_a_query.this, "null", Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);



                    }
                }

                @Override
                public void onFailure(Call<FormModels> call, Throwable t) {
                    progress_bar.setVisibility(View.GONE);

                    Toast.makeText(Raise_a_query.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();

                }
            });



        }
    };

    public void back(View view) {
        finish();
    }
}