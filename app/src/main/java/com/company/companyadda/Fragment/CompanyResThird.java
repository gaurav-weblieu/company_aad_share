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
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.company.companyadda.Activities.Fill_form;
import com.company.companyadda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class CompanyResThird extends Fragment {


    private CardView cardView_started;
    ScrollView LayoutCap;
    FloatingActionButton fab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.compant_res_third, container, false);

        cardView_started=view.findViewById(R.id.cardView_start);
        LayoutCap=view.findViewById(R.id.scrollView);
        fab=view.findViewById(R.id.fab);

        cardView_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), Fill_form.class);
                intent.putExtra("pos",String.valueOf(0));
                getActivity().startActivity(intent);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveClick();
            }
        });


        return  view;
    }


    public void SaveClick(){

        File file = createPdf(getActivity(), LayoutCap);
        if (file != null) {
            //Uri uri = Uri.parse(file.getAbsolutePath());
            Uri uri = FileProvider.getUriForFile(getActivity(),
                    getActivity().getPackageName() + ".provider", file);

            shareImage(uri);


            Log.i("TAG", "Drawing saved to the gallery!");
        } else {
            Log.i("TAG", "Oops! Image could not be saved.");
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

    private Bitmap getBitmapFromView(View view) {

        int totalHeight = LayoutCap.getChildAt(0).getHeight();
        int totalWidth = LayoutCap.getChildAt(0).getWidth();
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



}