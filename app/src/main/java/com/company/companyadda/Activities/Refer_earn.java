package com.company.companyadda.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.company.companyadda.ApiModels.LoginModels;
import com.company.companyadda.ApiModels.ReferModel;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import org.jetbrains.annotations.NotNull;

import java.security.interfaces.DSAKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Refer_earn extends AppCompatActivity {

    private SharedPreferences sharedPreferences_login;
    TextView textView_refer_code;
    ImageView imageView_share;
    ProgressBar progress_bar;
    CardView cardView_apply;
    EditText editText_refer;
    private String user_id;
    private String my_ref;
    LinearLayout animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);

        textView_refer_code=findViewById(R.id.textView_refer_code);
        imageView_share=findViewById(R.id.imageView_share);
        progress_bar=findViewById(R.id.progress_bar);
        cardView_apply=findViewById(R.id.cardView_apply);
        editText_refer=findViewById(R.id.editText_refer);
        animationView=findViewById(R.id.animationView);


        progress_bar.setVisibility(View.GONE);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        user_id=sharedPreferences_login.getString("user_id","");

         my_ref=sharedPreferences_login.getString("ref_code","");


        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();

                            String reflink=deepLink.toString();

                            try {

                                String one=reflink.substring(reflink.lastIndexOf("="));

                                String finalString=one.substring(1,one.length());

                                editText_refer.setText(finalString);

                                Toast.makeText(Refer_earn.this, ""+finalString, Toast.LENGTH_SHORT).show();

                            }catch (Exception e){

                            }

                        }


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "getDynamicLink:onFailure", e);
                    }
                });

        imageView_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                        .setLink(Uri.parse("https://acubeappsdevelopment.com/projects/manojCompanyAdda/"))
                        .setDomainUriPrefix("https://companyadda.page.link")
                        // Open links with this app on Android
                        .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                        // Open links with com.example.ios on iOS
                        .setIosParameters(new DynamicLink.IosParameters.Builder("com.example.ios").build())
                        .buildDynamicLink();

               // Uri dynamicLinkUri = dynamicLink.getUri();

               /* Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,dynamicLinkUri.toString());
                intent.setType("text/plan");
                startActivity(intent);*/

                  String sharelinktext  = "https://companyadda.page.link/?"+
                "link=https://acubeappsdevelopment.com/projects/manojCompanyAdda/myrefer.php?custid="+my_ref+
                "&apn="+ getPackageName()+
                "&st="+"Company Adda Reward Link"+
                "&sd="+"Reward Coins 50"+
                "&si="+"https://www.acubeappsdevelopment.com/projects/manojCompanyAdda/site/images/logo.png";

                  //https://www.blueappsoftware.com/wp-content/uploads/2018/06/blueapp-software-144-350.png
                  //https://acubeappsdevelopment.com/projects/manojCompanyAdda/site/images/logo.png
                  //https://www.blueappsoftware.com/logo-1.png

                Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
                        //.setLongLink(Uri.parse(dynamicLinkUri.toString()))
                        .setLongLink(Uri.parse(sharelinktext))
                        .buildShortDynamicLink()
                        .addOnCompleteListener(Refer_earn.this, new OnCompleteListener<ShortDynamicLink>() {
                            @Override
                            public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                                if (task.isSuccessful()) {
                                    // Short link created
                                    Uri shortLink = task.getResult().getShortLink();
                                    Uri flowchartLink = task.getResult().getPreviewLink();

                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_SEND);
                                    intent.putExtra(Intent.EXTRA_TEXT,shortLink.toString());
                                    intent.setType("text/plan");
                                    startActivity(intent);

                                } else {
                                    // Error
                                    // ...
                                }
                            }
                        });



            }
        });



        textView_refer_code.setText(sharedPreferences_login.getString("ref_code",""));

        cardView_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
                progress_bar.setVisibility(View.VISIBLE);

            }
        });

    }

    public void back(View view) {
        finish();
    }



    Runnable runnable= new Runnable() {
        @Override
        public void run() {

            Call<ReferModel> myProductsCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .ReferCode(user_id,editText_refer.getText().toString());

            myProductsCall.enqueue(new Callback<ReferModel>() {
                @Override
                public void onResponse(@NotNull Call<ReferModel> call, @NotNull Response<ReferModel> response) {
                    if (response.body() != null) {
                        ReferModel referModel = response.body();
                        if (referModel.getResult().getSTATUS().equals("true")) {

                            progress_bar.setVisibility(View.GONE);

                           // Toast.makeText(Refer_earn.this, ""+referModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();

                            animationView.setVisibility(View.VISIBLE);

                            sharedPreferences_login.edit().putBoolean("is_refer_done",true).apply();

                            Toast.makeText(Refer_earn.this, "Congratulation your have earned 50 Rupee in your wallet", Toast.LENGTH_LONG).show();

                            new Handler().postDelayed(new Runnable(){
                                @Override
                                public void run() {
                                    animationView.setVisibility(View.GONE);
                                    startActivity(new Intent(Refer_earn.this, DashBoard.class));
                                    finish();
                                }
                            }, 3000);


                        } else if (referModel.getResult().getSTATUS().equals("false")) {

                            Toast.makeText(Refer_earn.this, ""+referModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();


                             progress_bar.setVisibility(View.GONE);
                        }
                    } else if (response.body() == null) {

                        Toast.makeText(Refer_earn.this, "null", Toast.LENGTH_SHORT).show();


                         progress_bar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<ReferModel> call, Throwable t) {

                    progress_bar.setVisibility(View.GONE);

                    Log.d("tag",t.toString());

                }
            });



        }
    };
}