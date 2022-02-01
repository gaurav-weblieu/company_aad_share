package com.company.companyadda.Fragment;

import android.Manifest;
import android.app.Notification;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.company.companyadda.Activities.Profile;
import com.company.companyadda.Activities.Raise_a_query;
import com.company.companyadda.ApiModels.ProfileModel;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.company.companyadda.Activities.Fill_form.isValidEmail;


public class ProfileFragment extends Fragment {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    ImageView imageView_profile_image;
    private String imagePath;
    private File file;
    private SharedPreferences sharedPreferences_login;
    EditText editText_name,editText_phone,editText_email;
    private MultipartBody.Part image;
    private String name;
    private String email;
    private String phone;
    private ProgressBar progress_bar;
    private String user_id;
    private MaterialButton material_button_save;
    private Spinner spinner;

    List<String> categories = new ArrayList<String>();
    private String city="Delhi NCR";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile, container, false);


        sharedPreferences_login=getActivity().getSharedPreferences("login_details",getActivity().MODE_PRIVATE);

        imageView_profile_image=view.findViewById(R.id.imageView_profile_image);
        editText_name=view.findViewById(R.id.editText_name);
        editText_phone=view.findViewById(R.id.editText_phone);
        editText_phone.setEnabled(false);
        editText_phone.setKeyListener(null);
        editText_email=view.findViewById(R.id.editText_email);
        progress_bar=view.findViewById(R.id.progress_bar);
        material_button_save=view.findViewById(R.id.material_button_save);

        spinner = view. findViewById(R.id.spinner_city);

        String phone=sharedPreferences_login.getString("phone","");

        editText_name.setText(sharedPreferences_login.getString("name","").toString());
        editText_phone.setText(sharedPreferences_login.getString("phone","").toString());
        editText_email.setText(sharedPreferences_login.getString("email","").toString());

        String image_url = sharedPreferences_login.getString("image_url", "");

        user_id=sharedPreferences_login.getString("user_id","");

/*
        if (image_url != "") {
            Glide.with(this)
                    .load(image_url)
                    .fitCenter()
                    .placeholder(getResources().getDrawable(R.drawable.add_profile_icon))
                    .into(imageView_profile_image);

        }*/

        if (!image_url.equals("")) {
            Picasso.get()
                    .load(image_url)
                    .into(imageView_profile_image);

        }


        categories.add("Delhi NCR");
        categories.add("Delhi");
        categories.add("Noida");
        categories.add("Greater Noida");
        categories.add("Gurugram");
        categories.add("Ghaziabad");
        categories.add("Other");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city=categories.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageView_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                ViewGroup viewGroup =view. findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.customview_select_image, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                LinearLayout linearLayout_camera = dialogView.findViewById(R.id.liner_camera);
                LinearLayout linearLayout_galley = dialogView.findViewById(R.id.liner_galley);
                ImageView imageView_profile_diaa = dialogView.findViewById(R.id.imageView_profile_diaa);

                if (!image_url.equals("")) {
                    Picasso.get()
                            .load(image_url)
                            .into(imageView_profile_diaa);
                }

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

            }
        });


        isStoragePermissionGranted();

        material_button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText_name.getText().toString().length()<=0){
                    editText_name.setError("Please Enter Name");
                }else
                if (editText_email.getText().toString().length()<=0){
                    editText_email.setError("Please Enter Email");
                }else
                if (!isValidEmail(editText_email.getText().toString())) {
                    editText_email.setError("Please enter valid email");
                } else {

                    progress_bar.setVisibility(View.VISIBLE);
                    new Thread(runnable).start();

                }

            }
        });


        return view;
    }



    public boolean isStoragePermissionGrantedagain() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               /* fileUri = getOutputMediaFileUri(1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*/
                startActivityForResult(intent, 1001);
                return true;
            } else {
                Toast.makeText(getActivity(), "Permission is denied", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
                    Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT)
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
        if (requestCode == 1010 && resultCode == RESULT_OK) {
            if (data == null && data.getData() == null) {
                //Toast.makeText(context, "Data: Unavailable", Toast.LENGTH_SHORT).show();
            } else {
                Uri selectedImageUri = data.getData();
                //Toast.makeText(context, "Data: " + data.getData().getPath(), Toast.LENGTH_SHORT).show();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePath = cursor.getString(columnIndex);
                    Picasso.get().load(new File(imagePath))
                            // .placeholder(getResources().getDrawable(R.drawable.add_profile_icon))
                            .into(imageView_profile_image);
                    file = new File(imagePath);
                    cursor.close();
                } else {
                    Toast.makeText(getActivity(), "Unable to load the image", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (requestCode == 1001) {
            onCaptureImageResult(data);

        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        imageView_profile_image.setImageBitmap(thumbnail);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        // File destination = new File(android.os.Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
       /* File destination = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/",
                  "_Profile" + System.currentTimeMillis() + ".jpg");*/

        ContextWrapper cw = new ContextWrapper(getActivity());
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
            Toast.makeText(getActivity(), "erroe " + e, Toast.LENGTH_SHORT).show();
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
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                //  Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }


    Runnable runnable= new Runnable() {

        @Override
        public void run() {



            name = editText_name.getText().toString();
            email = editText_email.getText().toString();

            RequestBody r_user_id = RequestBody.create(MediaType.parse("multipart/form-data"), user_id);
            RequestBody r_name = RequestBody.create(MediaType.parse("multipart/form-data"), name);
            RequestBody r_email = RequestBody.create(MediaType.parse("multipart/form-data"), email);

            if (file != null) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("Multipart/form-data"), file);
                image = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            }


            Call<ProfileModel> userLoginCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .profile(
                            r_user_id,
                            r_name,
                            r_email,
                            image
                    );

            userLoginCall.enqueue(new Callback<ProfileModel>() {
                @Override
                public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                    if (response.body() != null) {
                        ProfileModel profileModel = response.body();

                        if (profileModel.getResult().getSTATUS().equals("true")) {
                            progress_bar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "" + profileModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();

                            sharedPreferences_login.edit().putString("name", profileModel.getProfile().get(0).getName()).apply();
                            sharedPreferences_login.edit().putString("phone", profileModel.getProfile().get(0).getMobile()).apply();
                            sharedPreferences_login.edit().putString("email", profileModel.getProfile().get(0).getEmail()).apply();
                            sharedPreferences_login.edit().putString("city", city).apply();
                            String image=profileModel.getProfile().get(0).getImage();
                            if (file != null){
                                sharedPreferences_login.edit().putString("image_url", image).apply();
                            }

                           setData();


                        } else if (!profileModel.getResult().getSTATUS().equals("true")) {
                            Toast.makeText(getActivity(), profileModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);
                        }
                    } else if (response.body() == null) {

                        Toast.makeText(getActivity(), response.body().getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                }

                @Override
                public void onFailure(Call<ProfileModel> call, Throwable t) {
                    progress_bar.setVisibility(View.GONE);
                    String s = t.toString();
                    Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    void setData(){

        editText_name.setText(sharedPreferences_login.getString("name","").toString());
        editText_phone.setText(sharedPreferences_login.getString("phone","").toString());
        editText_email.setText(sharedPreferences_login.getString("email","").toString());

        String image_url = sharedPreferences_login.getString("image_url", "");


        if (image_url != "") {
            Glide.with(this)
                    .load(image_url)
                    .fitCenter()
                    .into(imageView_profile_image);

        }





    }


    void uploadPDF(){

    }

}