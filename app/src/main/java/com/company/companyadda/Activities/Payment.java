package com.company.companyadda.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.company.companyadda.Adapter.History_trans_list_adapter;
import com.company.companyadda.ApiModels.FormModels;
import com.company.companyadda.ApiModels.PaymentPojoModel;
import com.company.companyadda.Pojo.PaymentPojo;
import com.company.companyadda.R;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Payment extends AppCompatActivity implements PaymentResultWithDataListener {

    private static final int UPI_PAYMENT = 1;
    private static final String TAG = "payment";
    private TextView textView_symbol;
    LinearLayout linear_layout;
    CardView cardView_pay,cardView_upi,cardView_google;
    private SharedPreferences sharedPreferences_login;
    private String user_id;
    private ProgressBar progress_bar;
    RecyclerView recycleView_trans_his;
    private History_trans_list_adapter adapter;
    private List<PaymentPojo> paymentList= new ArrayList<>();
    private String amount="1";
    private String texid;
    private String app_from="Paytm";
    CardView cardView_razor;
    private EditText textView_balance,textView_note;
    String note;

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {



        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case UPI_PAYMENT :
                if ((RESULT_OK==resultCode) || (requestCode==11)){
                    if (data!=null){
                        String txt_res=data.getStringExtra("response");
                        Log.d("UPI","onActivityResult"+txt_res);
                        ArrayList<String> dataList=new ArrayList<>();
                        dataList.add(txt_res);
                        upiPaymentDataOperation(dataList);
                    }else {
                        Log.d("UPI","onActivityResult"+"return data null");
                        ArrayList<String> dataList=new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                }else {
                    Log.d("UPI","onActivityResult"+"return data null");
                    ArrayList<String> dataList=new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }

                break;
        }





    }*/

   /* private void upiPaymentDataOperation(ArrayList<String> dataList) {
        if (isConnectionAvailable(Payment.this)){
            String str= dataList.get(0);
            Log.d("UPIPAY","upiPaymentDataOperation"+str);
            String paymentCancel="";
            if (str==null) str ="diacard";
            String status="";
            String approvalRefNo="";
            String response[]=str.split("&");
            for (int i =0; i<response.length;i++){
                String equalStr[]=response[i].split("=");
                if (equalStr.length>=2){


                    if (equalStr[0].toLowerCase().equals("txnId".toLowerCase())){
                        texid=equalStr[1].toLowerCase();

                    }

                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())){
                        status=equalStr[1].toLowerCase();
                    }else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())){
                        approvalRefNo=equalStr[1];
                    }
                }else {
                    paymentCancel="Payment Cancelled By User.";
                }
            }

            if (status.equals("success")){
                Toast.makeText(this, "Transaction successful", Toast.LENGTH_SHORT).show();
                Log.d("UPI","response :"+approvalRefNo);
              //  linear_layout_time.setVisibility(View.GONE);

                Upload_payment(user_id,texid,app_from,"0",approvalRefNo,status,amount);

            }else if ("Payment cancelled by user.".equals(paymentCancel)){
              //  linear_layout_time.setVisibility(View.GONE);

                Toast.makeText(this, "Transaction cancelled by user", Toast.LENGTH_SHORT).show();
            }
            else {
              //  linear_layout_time.setVisibility(View.GONE);

                Toast.makeText(this, "Transaction Failed.Please try again", Toast.LENGTH_SHORT).show();

            }


        }else {
            //linear_layout_time.setVisibility(View.GONE);

            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }*/

    private boolean isConnectionAvailable(Payment payment) {
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        textView_symbol=findViewById(R.id.textView_symbol);
        linear_layout=findViewById(R.id.linear_layout);
        cardView_pay=findViewById(R.id.cardView_pay);
        cardView_upi=findViewById(R.id.cardView_upi);
        progress_bar=findViewById(R.id.progress_bar);
        cardView_google=findViewById(R.id.cardView_google);
        recycleView_trans_his=findViewById(R.id.recycleView_trans_his);
        cardView_razor=findViewById(R.id.cardView_razor);
        textView_balance=findViewById(R.id.textView_balance);
        textView_note=findViewById(R.id.textView_note);
      //  linear_layout_time=findViewById(R.id.linear_layout_time);
        linear_layout.setVisibility(View.GONE);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        user_id=sharedPreferences_login.getString("user_id","");

        cardView_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  linear_layout.setVisibility(View.VISIBLE);
                int resId = R.anim.layout_animation_fall_down;
                LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(Payment.this, resId);
                linear_layout.setLayoutAnimation(animation);*/

                if (textView_balance.getText().toString().length()>0){
                    amount=textView_balance.getText().toString();
                    note=textView_note.getText().toString();
                    startPayment();
                }else {
                    textView_balance.setError("Enter Some Amount");
                }
            }
        });

        textView_balance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    textView_note.setVisibility(View.VISIBLE);
                }
                else {
                    textView_note.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cardView_razor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        cardView_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                app_from="Google Pay";

                String UPI=  getUPIString("companyaddataxcorppvtltd@icici","COMPANYADDA TAXCORP PVT LTD","","","","note",
                        amount,"INR","");

                String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
                int GOOGLE_PAY_REQUEST_CODE = 123;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(UPI));
                intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
                startActivityForResult(intent, 1);
            }
        });

        cardView_upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*Uri uri= Uri.parse("upi://pay").buildUpon()
                        .appendQueryParameter("pa","8010920184@ybl")
                        .appendQueryParameter("tr","merchant order ID")
                        .appendQueryParameter("pn","Preet Bhati")
                        .appendQueryParameter("tn","this note")
                        .appendQueryParameter("am","1")
                        .appendQueryParameter("cu","INR")
                        .build();*/

              String UPI=  getUPIString("companyaddataxcorppvtltd@icici","COMPANYADDA TAXCORP PVT LTD","","","","note",
                        amount,"INR","");

               /* Intent upiIntent= new Intent(Intent.ACTION_VIEW);
                upiIntent.setData(uri);

                Intent choser= Intent.createChooser(upiIntent,"Pay With");

                if (null != choser.resolveActivity(getPackageManager())){
                    startActivityForResult(choser,UPI_PAYMENT);
                }else {
                    Toast.makeText(Payment.this, "No UPI Payment Application not install", Toast.LENGTH_SHORT).show();
                }*/

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(UPI));
                Intent chooser = Intent.createChooser(intent, "Pay with...");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivityForResult(chooser, 1, null);
                }




            }
        });


        textView_symbol.setText(R.string.Rs);


        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        user_id=sharedPreferences_login.getString("user_id","");


        Upload_payment(user_id);

        Checkout.preload(getApplicationContext());

    }


    public void startPayment() {


        int amount_int= Integer.parseInt(amount);

        Checkout checkout = new Checkout();


        checkout.setKeyID(sharedPreferences_login.getString("razorpay_key_id",""));

       // Toast.makeText(Payment.this, "Id"+sharedPreferences_login.getString("razorpay_key_id",""), Toast.LENGTH_SHORT).show();
        /**
         * Instantiate Checkout
         */

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.splash_app_logo);

        /**
         * Reference to current activity
         */
        final Payment activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "COMPANYADDA TAXCORP PVT LTD");
            options.put("description", note);
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#FECD08");
            options.put("currency", "INR");
            options.put("amount", String.valueOf(amount_int*100));//pass amount in currency subunits
            options.put("prefill.email", sharedPreferences_login.getString("email", "").toString());
            options.put("prefill.contact",sharedPreferences_login.getString("phone", "").toString());
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }


    private String getUPIString(String payeeAddress, String payeeName, String payeeMCC, String trxnID, String trxnRefId,
                                String trxnNote, String payeeAmount, String currencyCode, String refUrl) {
        String UPI = "upi://pay?pa=" + payeeAddress + "&pn=" + payeeName
                + "&mc=" + payeeMCC + "&tid=" + trxnID + "&tr=" + trxnRefId
                + "&tn=" + trxnNote + "&am=" + payeeAmount + "&cu=" + currencyCode
                + "&refUrl=" + refUrl;
        return UPI.replace(" ", "+");
    }



    public void back(View view) {
        finish();
    }



    public void Upload_payment(String user_id,String razorpay_payment_id,String payment_via,String razorpay_order_id,String razorpay_signature,String Status,String amount){

        progress_bar.setVisibility(View.VISIBLE);


        Call<PaymentPojoModel> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .Payment(user_id,razorpay_payment_id,payment_via,razorpay_order_id,razorpay_signature,Status,amount,note);

        myProductsCall.enqueue(new Callback<PaymentPojoModel>() {
            @Override
            public void onResponse(Call<PaymentPojoModel> call, Response<PaymentPojoModel> response) {
                if (response.body() != null) {
                    PaymentPojoModel paymentPojoModel = response.body();
                    if (paymentPojoModel.getResult().getSTATUS().equals("true")){
                        //    Toast.makeText(Fill_form.this, "true", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Payment.this,PaymentDone.class));
                        finish();
                        progress_bar.setVisibility(View.GONE);

                    }
                    else if (paymentPojoModel.getResult().getSTATUS().equals("false")) {
                        Toast.makeText(Payment.this, "false", Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                } else if (response.body() == null) {
                    Toast.makeText(Payment.this, "null", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);



                }
            }

            @Override
            public void onFailure(Call<PaymentPojoModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);

                Toast.makeText(Payment.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void Upload_payment(String user_id){


        progress_bar.setVisibility(View.VISIBLE);

        Call<PaymentPojoModel> myProductsCall = RetrofitServicesHandler
                .getInstance()
                .getApi()
                .Payment(user_id);

        myProductsCall.enqueue(new Callback<PaymentPojoModel>() {
            @Override
            public void onResponse(Call<PaymentPojoModel> call, Response<PaymentPojoModel> response) {
                if (response.body() != null) {
                    PaymentPojoModel paymentPojoModel = response.body();
                    if (paymentPojoModel.getResult().getSTATUS().equals("true")){

                        paymentList=paymentPojoModel.getProfile();

                        recycleView_trans_his.setLayoutManager(new LinearLayoutManager(Payment.this ));
                        adapter = new History_trans_list_adapter(Payment.this,paymentList);
                        recycleView_trans_his.setAdapter(adapter);

                        progress_bar.setVisibility(View.GONE);


                    }
                    else if (paymentPojoModel.getResult().getSTATUS().equals("false")) {
                       // Toast.makeText(Payment.this, "false", Toast.LENGTH_SHORT).show();
                           progress_bar.setVisibility(View.GONE);

                    }
                } else if (response.body() == null) {
                    Toast.makeText(Payment.this, "null", Toast.LENGTH_SHORT).show();
                     progress_bar.setVisibility(View.GONE);



                }
            }

            @Override
            public void onFailure(Call<PaymentPojoModel> call, Throwable t) {
                progress_bar.setVisibility(View.GONE);

                Toast.makeText(Payment.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {

        /*Upload_payment(user_id,paymentData.getPaymentId(),"UPI",paymentData.getOrderId(),paymentData.getSignature(),
                "Success",amount);*/

        Intent intent= new Intent(Payment.this,PaymentDone.class);
        intent.putExtra("user_id",user_id);
        intent.putExtra("payment_id",paymentData.getPaymentId());
        intent.putExtra("payment_via","UPI");
        intent.putExtra("order_id",paymentData.getOrderId());
        intent.putExtra("sig",paymentData.getSignature());
        intent.putExtra("status","Success");
        intent.putExtra("amount",amount);
        intent.putExtra("note",note);
        startActivity(intent);

        Toast.makeText(Payment.this, "Paymrent "+paymentData.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(Payment.this, "Error "+paymentData.toString(), Toast.LENGTH_SHORT).show();

    }
}