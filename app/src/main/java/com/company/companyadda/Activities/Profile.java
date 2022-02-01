package com.company.companyadda.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.company.companyadda.Fragment.ProfileFragment;
import com.company.companyadda.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Profile extends AppCompatActivity {

   /* private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    ImageView imageView_profile_image;
    private String imagePath;
    private File file;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        openFragment(new ProfileFragment());


        /*imageView_profile_image=findViewById(R.id.imageView_profile_image);

        imageView_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
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

            }
        });


        isStoragePermissionGranted();*/

    }

    public void continue_fill_form(View view) {
        finish();
    }

    public void noti(View view) {
        startActivity(new Intent(this,Notification.class));
    }

    public void back(View view) {
        finish();
    }


  /*  public boolean isStoragePermissionGrantedagain() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               *//* fileUri = getOutputMediaFileUri(1);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);*//*
                startActivityForResult(intent, 1001);
                return true;
            } else {
                Toast.makeText(this, "Permission is denied", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
                    Toast.makeText(Profile.this, "Permission Granted", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // Permission Denied
                    Toast.makeText(Profile.this, "Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                            .into(imageView_profile_image);
                    file = new File(imagePath);
                    cursor.close();
                } else {
                    Toast.makeText(this, "Unable to load the image", Toast.LENGTH_SHORT).show();
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
       *//* File destination = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/",
                  "_Profile" + System.currentTimeMillis() + ".jpg");*//*

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
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
            Toast.makeText(this, "erroe " + e, Toast.LENGTH_SHORT).show();
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

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
            return true;
        }*/


    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout_profile, fragment);
        transaction.commit();
    }


    public void send_home(View view) {
        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Profile.this.finish();
    }

}