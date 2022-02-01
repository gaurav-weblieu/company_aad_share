package com.company.companyadda.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.company.companyadda.Adapter.Active_list_adapter;
import com.company.companyadda.Adapter.Doc_list_adapter;
import com.company.companyadda.ApiModels.DocModel;
import com.company.companyadda.ApiModels.ProfileModel;
import com.company.companyadda.Interface.DeleteInterface;
import com.company.companyadda.Interface.onDocment;
import com.company.companyadda.Pojo.DocPojo;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocUpload extends AppCompatActivity implements onDocment , DeleteInterface {

    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private SharedPreferences sharedPreferences_login;
    private String user_id;
    private EditText edit_doc_name;
    private String name;
    CardView upload;
    ImageView imageView_profile_image;
    private String imagePath;
    private File file;
    private MultipartBody.Part image;
    ProgressBar progress_bar;
    RecyclerView recycleView_docs;
    List<DocPojo> docPojoList = new ArrayList<>();
    private DownloadManager manager;

    ProgressDialog mProgressDialog;
    ImageView mImageView;
    URL url;
    AsyncTask mMyTask;
    private String check_final;
    private Doc_list_adapter adapter;

    LottieAnimationView animationView;

    String EncodedPdf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_upload);

        edit_doc_name=findViewById(R.id.edit_doc_name);
        upload=findViewById(R.id.upload);
        progress_bar=findViewById(R.id.progress_bar);
        imageView_profile_image=findViewById(R.id.imageView_profile_image);
        recycleView_docs=findViewById(R.id.recycleView_docs);
        animationView=findViewById(R.id.animationView);
        animationView.setVisibility(View.GONE);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        user_id=sharedPreferences_login.getString("user_id","");

        imageView_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DocUpload.this);
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




                    }
                });
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_doc_name.getText().toString().length()>0){
                    if (file!=null){
                        new Thread(runnable).start();
                        progress_bar.setVisibility(View.VISIBLE);
                    }else {
                        Toast.makeText(DocUpload.this, "Please Select Image to Upload !", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    edit_doc_name.setError("Enter Document Name");
                }

            }
        });

        isStoragePermissionGranted();

        new Thread(runnable_list).start();
        progress_bar.setVisibility(View.VISIBLE);

    }

    public void back(View view) {
        finish();
    }

    public void send_home(View view) {


        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        DocUpload.this.finish();
    }


    Runnable runnable= new Runnable() {


        @Override
        public void run() {

            name = edit_doc_name.getText().toString();

            RequestBody r_user_id = RequestBody.create(MediaType.parse("multipart/form-data"), user_id);
            RequestBody r_name = RequestBody.create(MediaType.parse("multipart/form-data"), name);

            if (file != null) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("Multipart/form-data"), file);
                image = MultipartBody.Part.createFormData("document_image", file.getName(), requestFile);
            }


            Call<DocModel> userLoginCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .UploadDocuments(
                            r_user_id,
                            r_name,
                            image
                    );

            userLoginCall.enqueue(new Callback<DocModel>() {
                @Override
                public void onResponse(Call<DocModel> call, Response<DocModel> response) {
                    if (response.body() != null) {
                        DocModel docModel = response.body();

                        if (docModel.getResult().getSTATUS().equals("true")) {
                            progress_bar.setVisibility(View.GONE);
                            Toast.makeText(DocUpload.this, "" + docModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                           // Toast.makeText(DocUpload.this, ""+user_id, Toast.LENGTH_SHORT).show();

                            docPojoList=docModel.getDocs_Details();

                            animationView.setVisibility(View.GONE);

                            recycleView_docs.setLayoutManager(new LinearLayoutManager(DocUpload.this ));
                             adapter = new Doc_list_adapter(DocUpload.this,docPojoList,DocUpload.this);
                            recycleView_docs.setAdapter(adapter);

                            edit_doc_name.setText("");
                            imageView_profile_image.setImageResource(R.drawable.upload_icon);


                        } else if (!docModel.getResult().getSTATUS().equals("true")) {
                            Toast.makeText(DocUpload.this, docModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);
                        }
                    } else if (response.body() == null) {

                        Toast.makeText(DocUpload.this, response.body().getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                }

                @Override
                public void onFailure(Call<DocModel> call, Throwable t) {
                    progress_bar.setVisibility(View.GONE);
                    String s = t.toString();
                    Toast.makeText(DocUpload.this, "" + t, Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    Runnable runnable_list= new Runnable() {

        @Override
        public void run() {

            animationView.setVisibility(View.GONE);

            Call<DocModel> userLoginCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .UploadDocuments(
                           user_id
                    );

            userLoginCall.enqueue(new Callback<DocModel>() {
                @Override
                public void onResponse(Call<DocModel> call, Response<DocModel> response) {
                    if (response.body() != null) {
                        DocModel docModel = response.body();

                        if (docModel.getResult().getSTATUS().equals("true")) {
                            progress_bar.setVisibility(View.GONE);
                           // Toast.makeText(DocUpload.this, "" + docModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            // Toast.makeText(DocUpload.this, ""+user_id, Toast.LENGTH_SHORT).show();

                            docPojoList=docModel.getDocs_Details();

                            recycleView_docs.setLayoutManager(new LinearLayoutManager(DocUpload.this ));
                            Doc_list_adapter adapter = new Doc_list_adapter(DocUpload.this,docPojoList,DocUpload.this);
                            recycleView_docs.setAdapter(adapter);

                            animationView.setVisibility(View.GONE);


                        } else if (!docModel.getResult().getSTATUS().equals("true")) {
                            //Toast.makeText(DocUpload.this, docModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);
                            animationView.setVisibility(View.VISIBLE);

                        }
                    } else if (response.body() == null) {

                       // Toast.makeText(DocUpload.this, response.body().getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);
                        animationView.setVisibility(View.VISIBLE);

                    }
                }

                @Override
                public void onFailure(Call<DocModel> call, Throwable t) {
                    progress_bar.setVisibility(View.GONE);
                    animationView.setVisibility(View.VISIBLE);

                    String s = t.toString();
                  //  Toast.makeText(DocUpload.this, "" + t, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DocUpload.this, "Permission is denied", Toast.LENGTH_SHORT).show();

                ActivityCompat.requestPermissions(DocUpload.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
                    Toast.makeText(DocUpload.this, "Permission Granted", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    // Permission Denied
                    Toast.makeText(DocUpload.this, "Permission Denied", Toast.LENGTH_SHORT)
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
                    Toast.makeText(DocUpload.this, "Unable to load the image", Toast.LENGTH_SHORT).show();
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

        ContextWrapper cw = new ContextWrapper(DocUpload.this);
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
            Toast.makeText(DocUpload.this, "erroe " + e, Toast.LENGTH_SHORT).show();
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

                ActivityCompat.requestPermissions(DocUpload.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Toast.makeText(this, "Permission is granted", Toast.LENGTH_SHORT).show();
            return true;
        }
    }




    @Override
    public void onDocClick(String url,String check) {

        check_final=check;

        URL url1 = null;
        mProgressDialog = new ProgressDialog(DocUpload.this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setMessage("Please wait, we are downloading your image file...");

        try {
            url1 = new URL(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mMyTask = new DownloadTask().execute(url1);

    }

    @Override
    public void onDeleteClick(String deleteID) {

        progress_bar.setVisibility(View.VISIBLE);

        Runnable runnable_delte= new Runnable() {


            @Override
            public void run() {

                Call<DocModel> userLoginCall = RetrofitServicesHandler
                        .getInstance()
                        .getApi()
                        .UploadDocumentsDelete(
                                deleteID
                        );

                userLoginCall.enqueue(new Callback<DocModel>() {
                    @Override
                    public void onResponse(Call<DocModel> call, Response<DocModel> response) {
                        if (response.body() != null) {
                            DocModel docModel = response.body();

                            if (docModel.getResult().getSTATUS().equals("true")) {
                                progress_bar.setVisibility(View.GONE);
                                 Toast.makeText(DocUpload.this, "" + docModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                                // Toast.makeText(DocUpload.this, ""+user_id, Toast.LENGTH_SHORT).show();

                                docPojoList.clear();
                                new Thread(runnable_list).start();

                            } else if (!docModel.getResult().getSTATUS().equals("true")) {
                                Toast.makeText(DocUpload.this, docModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                                progress_bar.setVisibility(View.GONE);
                            }
                        } else if (response.body() == null) {

                            Toast.makeText(DocUpload.this, response.body().getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                            progress_bar.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onFailure(Call<DocModel> call, Throwable t) {
                        progress_bar.setVisibility(View.GONE);
                        String s = t.toString();
                        Toast.makeText(DocUpload.this, "" + t, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };


        new Thread(runnable_delte).start();


    }

    public void setting_pin(View view) {
        startActivity(new Intent(DocUpload.this,SettingPin.class));
    }

    class DownloadTask extends AsyncTask<URL,Void,Bitmap> {
        protected void onPreExecute() {
            mProgressDialog.show();
        }

        protected Bitmap doInBackground(URL... urls) {
            URL url = urls[0];
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                return BitmapFactory.decodeStream(bufferedInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        // When all async task done
        protected void onPostExecute(Bitmap result) {
            // Hide the progress dialog
            if (result != null) {
                saveTempBitmap(result);
                Toast.makeText(DocUpload.this, "File Downloaded Successfully !", Toast.LENGTH_SHORT).show();
            } else {
                // Notify user that an error occurred while downloading image
                Toast.makeText(DocUpload.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }



    }


    public void saveTempBitmap(Bitmap bitmap) {
        if (isExternalStorageWritable()) {
            saveImage(bitmap);
        }else{
            //prompt the user or do something
        }
    }

    private void saveImage(Bitmap finalBitmap) {

        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"Company Adda");
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if(!isDirectoryCreated)
                Log.i("TAG", "Can't create directory to save the image");

        }
        String filename = pictureFileDir.getPath() +File.separator+ System.currentTimeMillis()+".jpg";
        File file = new File(filename);

        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            mProgressDialog.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
            mProgressDialog.dismiss();
            Toast.makeText(DocUpload.this, "Error"+e, Toast.LENGTH_SHORT).show();

        }

        if (check_final=="2"){
            Uri uri = FileProvider.getUriForFile(DocUpload.this,
                    DocUpload.this.getPackageName() + ".provider", file);

            shareImage(uri);
        }

    }

    private void shareImage(Uri imagePath) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imagePath);
        shareIntent.setType("image/*");
        // Launch sharing dialog for image
        startActivity(Intent.createChooser(shareIntent, "Share Image"));
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}