package com.company.companyadda.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.Activities.Company_search;
import com.company.companyadda.Activities.Director_Related_comp;
import com.company.companyadda.Adapter.Dir_related_list_adapter;
import com.company.companyadda.Adapter.Director_details_list_adapter;
import com.company.companyadda.Adapter.MAin_Director__details_list_adapter;
import com.company.companyadda.ApiModels.DirectorDetailsPojoClass;
import com.company.companyadda.ApiModels.RefreshComapnyResp;
import com.company.companyadda.ApiModels.SerachDirectorListPojoModel;
import com.company.companyadda.Interface.onRefreshClickDir;
import com.company.companyadda.Pojo.AuthPojo;
import com.company.companyadda.Pojo.SearchDirectorPojo;
import com.company.companyadda.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Director_Detaisl extends Fragment implements onRefreshClickDir {


    private RequestQueue mRequestQueue;
    private String auth_Key;
    private String intent_no;
    ProgressBar progress_bar;
    private RecyclerView recycleView_dir_det;
    List<SearchDirectorPojo> arrayList_name= new ArrayList<>();
    ImageView share_compnay;
    NestedScrollView framLayout;
    NestedScrollView z;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_director__detaisl, container, false);


        progress_bar=view.findViewById(R.id.progress_bar);
        recycleView_dir_det=view.findViewById(R.id.recycleView_dir_det);
        share_compnay=view.findViewById(R.id.share_compnay);

        framLayout =(NestedScrollView) view.findViewById(R.id.ScrollLayout);

        z = (NestedScrollView) view.findViewById(R.id.ScrollLayout);

        intent_no = getArguments().getString("no");


        share_compnay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveClick();

            }
        });

        getDirDetails();

        return view;
    }


    public void SaveClick(){
        progress_bar.setVisibility(View.VISIBLE);
        File file = createPdf(getActivity(), framLayout);
        if (file != null) {
            //Uri uri = Uri.parse(file.getAbsolutePath());
            Uri uri = FileProvider.getUriForFile(getActivity(),
                    getActivity().getPackageName() + ".provider", file);

            shareImage(uri);

            progress_bar.setVisibility(View.GONE);

            Log.i("TAG", "Drawing saved to the gallery!");
        } else {
            progress_bar.setVisibility(View.GONE);
            Log.i("TAG", "Oops! Image could not be saved.");
        }
    }

    private File createPdf(Context context, View drawView){

        Bitmap bitmap =getBitmapFromView(drawView);


        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels ;
        float width = displaymetrics.widthPixels ;

        int convertHighet = (int) hight, convertWidth = (int) width;

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();


        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawPaint(paint);



        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        document.finishPage(page);

        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"Company Adda");
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if(!isDirectoryCreated)
                Log.i("TAG", "Can't create directory to save the image");
        }
        String filename = pictureFileDir.getPath() +File.separator+ System.currentTimeMillis()+".pdf";
        File pictureFile = new File(filename);

        try {
            document.writeTo(new FileOutputStream(pictureFile));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();

        // scanGallery( context,pictureFile.getAbsolutePath());


        return pictureFile;
    }

    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(View view) {

        int totalHeight = z.getChildAt(0).getHeight();
        int totalWidth = z.getChildAt(0).getWidth();
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth, totalHeight,Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }


    private void shareImage(Uri imagePath) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        sharingIntent.setType("application/pdf");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, imagePath);
        startActivity(Intent.createChooser(sharingIntent, "Share PDF Using"));
    }



    void   getDirDetails(){


        progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(getActivity());

        String url = "https://api.finanvo.in/company/directors?CIN="+intent_no;
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Gson gson = new Gson();
                        SerachDirectorListPojoModel serachDirectorListPojoModel;
                        serachDirectorListPojoModel = gson.fromJson(response.toString(),SerachDirectorListPojoModel.class);

                        if (serachDirectorListPojoModel.getData().size()>0) {

                            arrayList_name=serachDirectorListPojoModel.getData();

                            recycleView_dir_det.setLayoutManager(new LinearLayoutManager(getActivity() ));
                            MAin_Director__details_list_adapter adapter = new MAin_Director__details_list_adapter(getActivity(),arrayList_name,Director_Detaisl.this);
                            recycleView_dir_det.setAdapter(adapter);

                        }else {
                        }



                        // Toast.makeText(getActivity(), "Response"+response.toString(), Toast.LENGTH_SHORT).show();

                        progress_bar.setVisibility(View.GONE);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progress_bar.setVisibility(View.GONE);

                        //Toast.makeText(getActivity(), "Invalid CIN Number !", Toast.LENGTH_SHORT).show();
                    }
                })

        {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("x-api-key", "j9g1z0f92C");
                paramss.put("x-api-secret-key", "sVgSRmUs54i56tcBIsLjgd1trdLsSPbZCZKiRu5c");
                return paramss;
            }
            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("CIN", intent_no);
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);

    }


    void   getRefreshDirDetails(String din_no){


        progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(getActivity());

        String url = "https://api.finanvo.in/config/refresh/director?DIN="+din_no;
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Gson gson = new Gson();
                        RefreshComapnyResp refreshComapnyResp;
                        refreshComapnyResp = gson.fromJson(response.toString(),RefreshComapnyResp.class);


                        Toast.makeText(getActivity(), " "+refreshComapnyResp.getMessage(), Toast.LENGTH_LONG).show();

                        progress_bar.setVisibility(View.GONE);

                        getDirDetails();

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progress_bar.setVisibility(View.GONE);

                        //Toast.makeText(getActivity(), "Invalid CIN Number !", Toast.LENGTH_SHORT).show();
                    }
                })

        {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("x-api-key", "j9g1z0f92C");
                paramss.put("x-api-secret-key", "sVgSRmUs54i56tcBIsLjgd1trdLsSPbZCZKiRu5c");
                return paramss;
            }
            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("CIN", din_no);
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);

    }

    @Override
    public void reFreshDir(String din) {
        getRefreshDirDetails(din);
    }
}