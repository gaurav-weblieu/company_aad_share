package com.company.companyadda.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.company.companyadda.Activities.About_Us;
import com.company.companyadda.Activities.ChatBoot;
import com.company.companyadda.Activities.City_select;
import com.company.companyadda.Activities.Contact_us;
import com.company.companyadda.Activities.DashBoard;
import com.company.companyadda.Activities.FeedBack;
import com.company.companyadda.Activities.Login;
import com.company.companyadda.Activities.My_request;
import com.company.companyadda.Activities.Offers;
import com.company.companyadda.Activities.Our_partners;
import com.company.companyadda.Activities.PanValidation;
import com.company.companyadda.Activities.Privacy_policy;
import com.company.companyadda.Activities.RTI;
import com.company.companyadda.Activities.Raise_a_query;
import com.company.companyadda.Activities.Refer_earn;
import com.company.companyadda.Activities.Term_contition;
import com.company.companyadda.Activities.Wallet;
import com.company.companyadda.Activities.WebTrial;
import com.company.companyadda.Pojo.Wallet_history_pojo;
import com.company.companyadda.R;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Nav_list_adapter extends RecyclerView.Adapter<Nav_list_adapter.ViewHolder> {
    private final AlertDialog.Builder builder;
    Context context;
    ArrayList<Integer> arrayList_nav_icon;
    ArrayList<String> arrayList_nav_name;
    private SharedPreferences sharedPreferences_login;
    private ViewGroup viewGroup;


    public Nav_list_adapter(Context context, ArrayList<Integer> arrayList_nav_icon,ArrayList<String> arrayList_nav_name) {
        this.context = context;
        this.arrayList_nav_icon = arrayList_nav_icon;
        this.arrayList_nav_name = arrayList_nav_name;

        builder = new AlertDialog.Builder(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.nav_drawer_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @NonNull
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(arrayList_nav_name.get(position));
        holder.imageView.setImageResource(arrayList_nav_icon.get(position));

        if (position==3 || position==4|| position==5 || position==6){
            holder.animationView.setVisibility(View.VISIBLE);
        }

        holder.linear_layout_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){

                    case 0:
                        context.startActivity(new Intent(context, DashBoard.class));
                        break;

                    case 1:
                        context.startActivity(new Intent(context, My_request.class));
                        break;

                    case 2:
                        context.startActivity(new Intent(context, Wallet.class));

                        break;

                    case 3:

                        showBottomSheetDialogGstByPan();

                        break;

                    case 4:

                        showBottomSheetDialogEChallen();

                        break;

                    case 5:

                        context.startActivity(new Intent(context, RTI.class));


                        break;

                    case 6:

                        showBottomSheetANRStatus();


                        break;

                    case 7:

                        showBottomSheetITlogin();


                        break;

                    case 8:

                        showBottomSheetDINSTATUS();


                        break;

                    case 9:

                        showBottomSheetDialogCal();


                        break;


                    case 10:


                        Intent intent = new Intent(context, WebTrial.class);
                        intent.putExtra("url",
                                "https://eportal.incometax.gov.in/iec/foservices/#/pre-login/bl-link-aadhaar");
                        context.startActivity(intent);


                        break;

                    case 11:

                        showBottomSheetDialog();

                        break;


                    case 12:
                        context.startActivity(new Intent(context, Offers.class));

                        break;

                    case 13:
                        context.startActivity(new Intent(context, Our_partners.class));
                        break;



                    case 14:
                        context.startActivity(new Intent(context, Refer_earn.class));

                        break;

                    case 15:
                        context.startActivity(new Intent(context, Term_contition.class));

                        break;


                    case 16:
                        context.startActivity(new Intent(context, About_Us.class));

                        break;

                    case 17:
                        //startActivity(new Intent(DashBoard.this, WebTrial.class));
                        context.startActivity(new Intent(context, Contact_us.class));


                        break;

//                    case 14:
//                        //startActivity(new Intent(DashBoard.this, WebTrial.class));
//                        context.startActivity(new Intent(context, ChatBoot.class));
//                        break;

                    case 18:
                        context.startActivity(new Intent(context, FeedBack.class));
                        break;

                    case 19:
                        context.startActivity(new Intent(context, Raise_a_query.class));
                        break;

                    case 20:
                        context.startActivity(new Intent(context, Privacy_policy.class));


                        break;


                    case 21:

                        sharedPreferences_login=context.getSharedPreferences("login_details",MODE_PRIVATE);


                        builder.setMessage("Are you sure you want to logout ?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        sharedPreferences_login.edit().putBoolean("isLogin",false).apply();
                                        Intent intent = new Intent(context, Login.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        context.startActivity(intent);
                                        // context.finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        alert.setTitle("Logout");
                        alert.show();

                        break;

                }


            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayList_nav_icon.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

       ImageView imageView;
        TextView name;
        LinearLayout linear_layout_nav;
        LottieAnimationView animationView;

        public ViewHolder(View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            imageView=itemView.findViewById(R.id.imageView);
            linear_layout_nav=itemView.findViewById(R.id.linear_layout_nav);
            animationView=itemView.findViewById(R.id.animationView);


        }

    }

    private void showBottomSheetDialog() {


        Intent intent = new Intent(context, WebTrial.class);
        intent.putExtra("url","https://tin.tin.nsdl.com/oltas/refund-status-pan.html");
        context.startActivity(intent);

        /*RoundedBottomSheetDialog roundedBottomSheetDialog = new RoundedBottomSheetDialog(context);
        View bottomSheetDialogView = LayoutInflater
                .from(context)
                .inflate(R.layout.refund_money_view, viewGroup, false);
        roundedBottomSheetDialog.setContentView(bottomSheetDialogView);
        roundedBottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        roundedBottomSheetDialog.show();
        roundedBottomSheetDialog.setCancelable(false);

        WebView webView = bottomSheetDialogView.findViewById(R.id.webView_refund);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://tin.tin.nsdl.com/oltas/refund-status-pan.html");

        ImageView  imageView = bottomSheetDialogView.findViewById(R.id.imageView_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundedBottomSheetDialog.dismiss();
            }
        });*/

    }





    private void showBottomSheetDialogCal() {


        Intent intent = new Intent(context, WebTrial.class);
        intent.putExtra("url","https://www.incometaxindia.gov.in/pages/tools/income-tax-calculator.aspx");
        context.startActivity(intent);

       /* RoundedBottomSheetDialog roundedBottomSheetDialog = new RoundedBottomSheetDialog(context);
        View bottomSheetDialogView = LayoutInflater
                .from(context)
                .inflate(R.layout.refund_money_view, viewGroup, false);
        roundedBottomSheetDialog.setContentView(bottomSheetDialogView);
        roundedBottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        roundedBottomSheetDialog.show();
        roundedBottomSheetDialog.setCancelable(false);

        WebView webView = bottomSheetDialogView.findViewById(R.id.webView_refund);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://www.incometaxindia.gov.in/pages/tools/income-tax-calculator.aspx");

        ImageView  imageView = bottomSheetDialogView.findViewById(R.id.imageView_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundedBottomSheetDialog.dismiss();
            }
        });*/

    }

    private void showBottomSheetANRStatus() {

        Intent intent = new Intent(context, WebTrial.class);
        intent.putExtra("url","https://services.gst.gov.in/services/arnstatus");
        context.startActivity(intent);

       /* RoundedBottomSheetDialog roundedBottomSheetDialog = new RoundedBottomSheetDialog(context);
        View bottomSheetDialogView = LayoutInflater
                .from(context)
                .inflate(R.layout.refund_money_view, viewGroup, false);
        roundedBottomSheetDialog.setContentView(bottomSheetDialogView);
        roundedBottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        roundedBottomSheetDialog.show();
        roundedBottomSheetDialog.setCancelable(false);

        WebView webView = bottomSheetDialogView.findViewById(R.id.webView_refund);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://services.gst.gov.in/services/arnstatus");

        ImageView  imageView = bottomSheetDialogView.findViewById(R.id.imageView_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundedBottomSheetDialog.dismiss();
            }
        });
*/
    }

    private void showBottomSheetDINSTATUS() {


        Intent intent = new Intent(context, WebTrial.class);
        intent.putExtra("url","https://www.mca.gov.in/mcafoportal/showEnquireDIN.do");
        context.startActivity(intent);


       /* RoundedBottomSheetDialog roundedBottomSheetDialog = new RoundedBottomSheetDialog(context);
        View bottomSheetDialogView = LayoutInflater
                .from(context)
                .inflate(R.layout.refund_money_view, viewGroup, false);
        roundedBottomSheetDialog.setContentView(bottomSheetDialogView);
        roundedBottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        roundedBottomSheetDialog.show();
        roundedBottomSheetDialog.setCancelable(false);

        WebView webView = bottomSheetDialogView.findViewById(R.id.webView_refund);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://www.mca.gov.in/mcafoportal/showEnquireDIN.do");

        ImageView  imageView = bottomSheetDialogView.findViewById(R.id.imageView_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundedBottomSheetDialog.dismiss();
            }
        });*/

    }



    private void showBottomSheetITlogin() {

        Intent intent = new Intent(context, WebTrial.class);
        intent.putExtra("url","https://eportal.incometax.gov.in/iec/foservices/#/login");
        context.startActivity(intent);


        /*RoundedBottomSheetDialog roundedBottomSheetDialog = new RoundedBottomSheetDialog(context);
        View bottomSheetDialogView = LayoutInflater
                .from(context)
                .inflate(R.layout.refund_money_view, viewGroup, false);
        roundedBottomSheetDialog.setContentView(bottomSheetDialogView);
        roundedBottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        roundedBottomSheetDialog.show();
        roundedBottomSheetDialog.setCancelable(false);

        WebView webView = bottomSheetDialogView.findViewById(R.id.webView_refund);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl("https://eportal.incometax.gov.in/iec/foservices/#/login");

        ImageView  imageView = bottomSheetDialogView.findViewById(R.id.imageView_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundedBottomSheetDialog.dismiss();
            }
        });*/

    }


    private void showBottomSheetDialogGstByPan() {


        Intent intent = new Intent(context, WebTrial.class);
        intent.putExtra("url","https://services.gst.gov.in/services/searchtpbypan");
        context.startActivity(intent);

       /* RoundedBottomSheetDialog roundedBottomSheetDialog = new RoundedBottomSheetDialog(context);
        View bottomSheetDialogView = LayoutInflater
                .from(context)
                .inflate(R.layout.gst_by_pan, viewGroup, false);
        roundedBottomSheetDialog.setContentView(bottomSheetDialogView);
        roundedBottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        roundedBottomSheetDialog.show();
        roundedBottomSheetDialog.setCancelable(false);

        WebView  webView = bottomSheetDialogView.findViewById(R.id.webView_refund);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.loadUrl("https://services.gst.gov.in/services/searchtpbypan");

        ImageView  imageView = bottomSheetDialogView.findViewById(R.id.imageView_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundedBottomSheetDialog.dismiss();
            }
        });*/

    }


    private void showBottomSheetDialogEChallen() {

        Intent intent = new Intent(context, WebTrial.class);
        intent.putExtra("url","https://onlineservices.tin.egov-nsdl.com/etaxnew/tdsnontds.jsp");
        context.startActivity(intent);

        /*RoundedBottomSheetDialog roundedBottomSheetDialog = new RoundedBottomSheetDialog(context);
        View bottomSheetDialogView = LayoutInflater
                .from(context)
                .inflate(R.layout.e_challen_view, viewGroup, false);
        roundedBottomSheetDialog.setContentView(bottomSheetDialogView);
        roundedBottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        roundedBottomSheetDialog.show();
        roundedBottomSheetDialog.setCancelable(false);



        WebView  webView = bottomSheetDialogView.findViewById(R.id.webView_refund);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.requestFocus();
        webView.loadUrl("https://onlineservices.tin.egov-nsdl.com/etaxnew/tdsnontds.jsp");

        ImageView  imageView = bottomSheetDialogView.findViewById(R.id.imageView_cancel);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundedBottomSheetDialog.dismiss();
            }
        });*/

    }





}