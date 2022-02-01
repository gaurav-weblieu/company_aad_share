package com.company.companyadda.Activities;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.company.companyadda.Adapter.Doc_list_adapter;
import com.company.companyadda.ApiModels.DocModel;
import com.company.companyadda.ApiModels.RTIModel;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RTI extends AppCompatActivity {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private SharedPreferences sharedPreferences_login;
    private String user_id;
    private EditText name,email,phone;
    ImageView imageView_image,upload;
    private String imagePath;
    private File file;
    private String s_name,s_email,s_phone;
    private MultipartBody.Part image;
    ProgressBar progress_bar;
    MaterialButton material_button_start;
    private String EncodedPdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rti);


        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        user_id=sharedPreferences_login.getString("user_id","");


        imageView_image=findViewById(R.id.image);
        upload=findViewById(R.id.upload);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        progress_bar=findViewById(R.id.progress_bar);
        material_button_start=findViewById(R.id.material_button_start);


        material_button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().length()==0){
                    name.setError("Enter Name");
                }else
                if (email.getText().toString().length()==0){
                    name.setError("Email Name");
                }else
                if (phone.getText().toString().length()==0){
                    name.setError("Phone Name");
                }else{
                    if (file!=null){
                        new Thread(runnable).start();
                        progress_bar.setVisibility(View.VISIBLE);
                    }else {
                        Toast.makeText(RTI.this, "Please Select Image to Upload !", Toast.LENGTH_SHORT).show();
                    }
                    progress_bar.setVisibility(View.VISIBLE);
                }
            }
        });

        imageView_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RTI.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.customview_select_image, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                LinearLayout linearLayout_camera = dialogView.findViewById(R.id.liner_camera);
                LinearLayout linearLayout_galley = dialogView.findViewById(R.id.liner_galley);

                linearLayout_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isStoragePermissionGrantedagain();
                        alertDialog.dismiss();
                    }
                });

                linearLayout_galley.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        Intent chooserIntent = Intent.createChooser(intent, "Please select an image");
                        startActivityForResult(chooserIntent, 1010);

                        alertDialog.dismiss();


                    }
                });



               /* Intent choosefile=new Intent(Intent.ACTION_GET_CONTENT);
                choosefile.setType("application/pdf");
                choosefile=Intent.createChooser(choosefile,"Chossee File");
                startActivityForResult(choosefile,21);*/




            }
        });
    }


    Runnable runnable= new Runnable() {


        @Override
        public void run() {

            s_name = name.getText().toString();
            s_email = email.getText().toString();
            s_phone = phone.getText().toString();

            RequestBody r_user_id = RequestBody.create(MediaType.parse("multipart/form-data"), user_id);
            RequestBody r_name = RequestBody.create(MediaType.parse("multipart/form-data"), s_name);
            RequestBody r_email = RequestBody.create(MediaType.parse("multipart/form-data"), s_email);
            RequestBody r_phone = RequestBody.create(MediaType.parse("multipart/form-data"), s_phone);

            if (file != null) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("Multipart/form-data"), file);
                image = MultipartBody.Part.createFormData("form16", file.getName(), requestFile);
            }


            Call<RTIModel> userLoginCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .Free_ITR(
                            r_user_id,
                            r_name,
                            r_phone,
                            r_email,
                            image
                    );

            userLoginCall.enqueue(new Callback<RTIModel>() {
                @Override
                public void onResponse(Call<RTIModel> call, Response<RTIModel> response) {
                    if (response.body() != null) {
                        RTIModel rtiModel = response.body();

                        if (rtiModel.getResult().getSTATUS().equals("true")) {
                            progress_bar.setVisibility(View.GONE);
                            Toast.makeText(RTI.this, "" + rtiModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RTI.this,Request_successfully.class));
                            finish();
                            progress_bar.setVisibility(View.GONE);


                        } else if (!rtiModel.getResult().getSTATUS().equals("true")) {
                            Toast.makeText(RTI.this, rtiModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);
                        }
                    } else if (response.body() == null) {

                        Toast.makeText(RTI.this, response.body().getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                }

                @Override
                public void onFailure(Call<RTIModel> call, Throwable t) {
                    progress_bar.setVisibility(View.GONE);
                    String s = t.toString();
                    Toast.makeText(RTI.this, "" + t, Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    Runnable runnablepdf= new Runnable() {


        @Override
        public void run() {

            s_name = name.getText().toString();
            s_email = email.getText().toString();
            s_phone = phone.getText().toString();


            Call<RTIModel> userLoginCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .Free_ITR_to_String(
                            user_id,
                            s_name,
                            s_phone,
                            s_email,
                            EncodedPdf
                    );

            userLoginCall.enqueue(new Callback<RTIModel>() {
                @Override
                public void onResponse(Call<RTIModel> call, Response<RTIModel> response) {
                    if (response.body() != null) {
                        RTIModel rtiModel = response.body();

                        if (rtiModel.getResult().getSTATUS().equals("true")) {
                            progress_bar.setVisibility(View.GONE);
                            Toast.makeText(RTI.this, "" + rtiModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(RTI.this,Request_successfully.class));
                            finish();
                            progress_bar.setVisibility(View.GONE);


                        } else if (!rtiModel.getResult().getSTATUS().equals("true")) {
                            Toast.makeText(RTI.this, rtiModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);
                        }
                    } else if (response.body() == null) {

                        Toast.makeText(RTI.this, response.body().getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                }

                @Override
                public void onFailure(Call<RTIModel> call, Throwable t) {
                    progress_bar.setVisibility(View.GONE);
                    String s = t.toString();
                    Toast.makeText(RTI.this, "" + t, Toast.LENGTH_SHORT).show();
                }
            });

        }
    };


    public boolean isStoragePermissionGrantedagain() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               /* fileUri = getOutputMediaFileUri(1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*/
                startActivityForResult(intent, 1001);
                return true;
            } else {
                Toast.makeText(RTI.this, "Permission is denied", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(RTI.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Toast.makeText(RTI.this, "Permission Granted", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // Permission Denied
                    Toast.makeText(RTI.this, "Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            if (requestCode == 1010 && resultCode == RESULT_OK) {
                if (data == null && data.getData() == null) {
                    //Toast.makeText(context, "Data: Unavailable", Toast.LENGTH_SHORT).show();
                } else {
                    Uri selectedImageUri = data.getData();
                    //Toast.makeText(context, "Data: " + data.getData().getPath(), Toast.LENGTH_SHORT).show();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        imagePath = cursor.getString(columnIndex);
                        Picasso.get().load(new File(imagePath))
                                // .placeholder(getResources().getDrawable(R.drawable.add_profile_icon))
                                .into(imageView_image);
                        file = new File(imagePath);
                        cursor.close();
                    } else {
                        Toast.makeText(RTI.this, "Unable to load the image", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            if (requestCode == 1001) {
                onCaptureImageResult(data);

            }

        }


       /* if (requestCode==21 && resultCode == RESULT_OK  && data !=null){
            Uri path= data.getData();
            try {
                InputStream inputStream=RTI.this.getContentResolver().openInputStream(path );
                byte[] pdfInBytes=new byte[inputStream.available()];
                inputStream.read(pdfInBytes);
                EncodedPdf= Base64.encodeToString(pdfInBytes,Base64.DEFAULT);

                Toast.makeText(RTI.this, "okk"+EncodedPdf, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(RTI.this, "error"+e.toString(), Toast.LENGTH_SHORT).show();
            }
        }*/
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        imageView_image.setImageBitmap(thumbnail);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        // File destination = new File(android.os.Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
       /* File destination = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/",
                  "_Profile" + System.currentTimeMillis() + ".jpg");*/

        ContextWrapper cw = new ContextWrapper(RTI.this);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File destination = new File(directory, "UniqueFileName" + ".jpg");


        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(RTI.this, "erroe " + e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        System.out.println(" onCaptureImageResult : __EDITACCUNT____ " + destination.getPath());
        //uploadProfileImage("/storage/emulated/0/FunBook/Hemant_Sharma_Profile1519063771703.jpg");
        //setimage(thumbnail);
        //userImg_ImageView.setImageBitmap(thumbnail);

        file = new File(destination.getPath());
    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                //  Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(RTI.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
            return true;
        }}

    public void back(View view) {
        finish();
    }

    public void send_home(View view) {


        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        RTI.this.finish();
    }
}