package com.company.companyadda.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.MediaRouteButton;
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
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.company.companyadda.Adapter.Dir_related_list_adapter;
import com.company.companyadda.Adapter.History_trans_list_adapter;
import com.company.companyadda.ApiModels.DirRelatedComp;
import com.company.companyadda.ApiModels.SerachDirectorListPojoModel;
import com.company.companyadda.Pojo.DirRelatedPojo;
import com.company.companyadda.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Director_Related_comp extends AppCompatActivity {


    private RequestQueue mRequestQueue;
    private String intent_no="06948405";
    private ProgressBar progress_bar;
    List<DirRelatedPojo> dirRelatedPojoList;
    private RecyclerView recycleView_dr_re;
    private Dir_related_list_adapter adapter;

    TextView textView_date,textView_design,textView_dir_name,textView_din,textView_date_reg;
    private String name;
    private String design;
    private String date_join;
    private String date_reg;

    ImageView share_compnay;
    NestedScrollView framLayout;
    NestedScrollView z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director__related_comp);

        recycleView_dr_re=findViewById(R.id.recycleView_dr_re);

        textView_din=findViewById(R.id.textView_din);
        textView_design=findViewById(R.id.textView_design);
        textView_dir_name=findViewById(R.id.textView_dir_name);
        textView_date=findViewById(R.id.textView_date);
        textView_date_reg=findViewById(R.id.textView_date_reg);


        share_compnay=findViewById(R.id.share_compnay);

        framLayout =(NestedScrollView) findViewById(R.id.ScrollLayout);

        z = (NestedScrollView) findViewById(R.id.ScrollLayout);

        share_compnay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveClick();

            }
        });




      intent_no=  getIntent().getStringExtra("din");

      name=  getIntent().getStringExtra("name");
      design=  getIntent().getStringExtra("design");
      date_join=  getIntent().getStringExtra("date_join");
      date_reg=  getIntent().getStringExtra("date_regin");


       textView_din.setText(intent_no);
       textView_dir_name.setText(name);
       textView_design.setText(design);
       textView_date.setText(date_join);
       textView_date_reg.setText(date_reg);

        progress_bar=findViewById(R.id.progress_bar);


        getDirDetails();

    }


    public void SaveClick(){
        progress_bar.setVisibility(View.VISIBLE);
        File file = createPdf(Director_Related_comp.this, framLayout);
        if (file != null) {
            //Uri uri = Uri.parse(file.getAbsolutePath());
            Uri uri = FileProvider.getUriForFile(Director_Related_comp.this,
                    Director_Related_comp.this.getPackageName() + ".provider", file);

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


        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
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
            Toast.makeText(Director_Related_comp.this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
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

        mRequestQueue = Volley.newRequestQueue(this);

        String url = "https://api.finanvo.in/director/companies?DIN="+intent_no;
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
                        DirRelatedComp dirRelatedComp;
                        dirRelatedComp = gson.fromJson(response.toString(),DirRelatedComp.class);

                        if (dirRelatedComp.getData().size()>0) {

                            dirRelatedPojoList=dirRelatedComp.getData();

                            recycleView_dr_re.setLayoutManager(new LinearLayoutManager(Director_Related_comp.this ));
                            adapter = new Dir_related_list_adapter(Director_Related_comp.this,dirRelatedPojoList);
                            recycleView_dr_re.setAdapter(adapter);


                        }
                        else {

                            Toast.makeText(Director_Related_comp.this ,"Response"+response.toString(), Toast.LENGTH_SHORT).show();


                        }




                        progress_bar.setVisibility(View.GONE);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        progress_bar.setVisibility(View.GONE);

                        Toast.makeText(Director_Related_comp.this, "Invalid CIN Number !"+error, Toast.LENGTH_SHORT).show();
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
                params.put("DIN", intent_no);
                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjectRequest);

    }

    public void back(View view) {
        finish();
    }

    public void send_home(View view) {

        Intent intent = new Intent(this,DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Director_Related_comp.this.finish();
    }
}