package com.company.companyadda.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.companyadda.Adapter.NewListAdapter;
import com.company.companyadda.ApiModels.NewListModel;
import com.company.companyadda.ApiModels.PanDetailsModel;
import com.company.companyadda.Helper.Captcha;
import com.company.companyadda.Helper.MathCaptcha;
import com.company.companyadda.Pojo.AccessPojo;
import com.company.companyadda.Pojo.ArticlesListPojo;
import com.company.companyadda.R;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class PanValid extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private ProgressBar progress_bar;
    EditText pan_nu;
    MaterialButton material_button_start,captcha;
    private String access_tok;
    CheckBox checkbox_term_and_cond,not_robot;

    String SITE_KEY = "6LeeY-4dAAAAAKNhsDow6_WNm5gnQ7WpgD8UuOXT";
    String SECRET_KEY = "6LeeY-4dAAAAANxwXhjZ_vpYk0WjuOtXxBGU5nG_";
    RequestQueue queue;
    private boolean is_robot_boolean;

    ImageView im;
    ImageView imageView_refres;
    Button btn;
    EditText editText_ans;
    Captcha c;
    SeekBar seekBar;
    TextView textView_com_per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_valid);


        queue = Volley.newRequestQueue(getApplicationContext());

        im = (ImageView)findViewById(R.id.imageView1);
        seekBar = findViewById(R.id.seekBar);
        textView_com_per = findViewById(R.id.textView_com_per);
        seekBar.setMax(10);
        seekBar.setProgress(0);
        editText_ans = findViewById(R.id.editText_ans);
        imageView_refres = findViewById(R.id.imageView_refres);

         c = new MathCaptcha(300, 100, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
        //Captcha c = new TextCaptcha(300, 100, 5, TextOptions.NUMBERS_AND_LETTERS);
        im.setImageBitmap(c.image);
        im.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
      //  ans.setText(c.answer);


        imageView_refres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = new MathCaptcha(300, 100, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
                //Captcha c = new TextCaptcha(300, 100, 5, TextOptions.NUMBERS_AND_LETTERS);
                im.setImageBitmap(c.image);
                im.setLayoutParams(new LinearLayout.LayoutParams(c.width *2, c.height *2));
                //  ans.setText(c.answer);
            }
        });



        pan_nu=findViewById(R.id.pan_nu);
        progress_bar=findViewById(R.id.progress_bar);
        material_button_start=findViewById(R.id.material_button_start);
        captcha=findViewById(R.id.captcha);
        checkbox_term_and_cond=findViewById(R.id.checkbox_term_and_cond);
        not_robot=findViewById(R.id.not_robot);

        progress_bar.setVisibility(View.VISIBLE);


        getAccessToken();


        not_robot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    is_robot_boolean=true;
                    verifyGoogleReCAPTCHA();
                }
                else {
                    is_robot_boolean=false;

                }
            }
        });


        captcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyGoogleReCAPTCHA();
            }
        });

        material_button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (pan_nu.getText().toString().length()>0){

                    if (pan_nu.getText().toString().length()==10) {

                        if (checkbox_term_and_cond.isChecked()) {

                            if (editText_ans.getText().toString().equals(c.answer)){
                                getPandetails();
                            }else {
                                Toast.makeText(PanValid.this, "Enter Wrong Answer !!!", Toast.LENGTH_SHORT).show();

                            }


                        } else {
                            Toast.makeText(PanValid.this, " Check Term and Condition", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        pan_nu.setError("Enter Valid Pan Number");


                    }

                }else {
                    pan_nu.setError("Enter Pan NUmber");
                }

            }
        });

        pan_nu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // Integer enteredProgress = Integer.valueOf(s.toString());
                seekBar.setProgress(charSequence.length());
                textView_com_per.setText("PAN Card Details "+ charSequence.length()*10 +"% Completed");

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void back(View view) {
        finish();
    }


    public void send_home(View view) {


        Intent intent = new Intent(this, DashBoard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        PanValid.this.finish();



    }


    private void verifyGoogleReCAPTCHA() {

        // below line is use for getting our safety
        // net client and verify with reCAPTCHA
        SafetyNet.getClient(this).verifyWithRecaptcha(SITE_KEY)
                // after getting our client we have
                // to add on success listener.
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        // in below line we are checking the response token.
                        if (!response.getTokenResult().isEmpty()) {
                            // if the response token is not empty then we
                            // are calling our verification method.
                            handleVerification(response.getTokenResult());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // this method is called when we get any error.
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            // below line is use to display an error message which we get.
                            Log.d("TAG", "Error message: " +
                                    CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
                            // below line is use to display a toast message for any error.
                            Toast.makeText(PanValid.this, "Error found is : " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    protected void handleVerification(final String responseToken) {
        // inside handle verification method we are
        // verifying our user with response token.
        // url to sen our site key and secret key
        // to below url using POST method.
        String url = "https://www.google.com/recaptcha/api/siteverify";

        // in this we are making a string request and
        // using a post method to pass the data.
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // inside on response method we are checking if the
                        // response is successful or not.
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {
                                // if the response is successful then we are
                                // showing below toast message.
                                //Toast.makeText(PanValid.this, "User verified with reCAPTCHA", Toast.LENGTH_SHORT).show();
                            } else {
                                // if the response if failure we are displaying
                                // a below toast message.
                               // Toast.makeText(getApplicationContext(), String.valueOf(jsonObject.getString("error-codes")), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {
                            // if we get any exception then we are
                            // displaying an error message in logcat.
                            Log.d("TAG", "JSON exception: " + ex.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // inside error response we are displaying
                        // a log message in our logcat.
                        Log.d("TAG", "Error message: " + error.getMessage());
                    }
                }) {
            // below is the getParamns method in which we will
            // be passing our response token and secret key to the above url.
            @Override
            protected Map<String, String> getParams() {
                // we are passing data using hashmap
                // key and value pair.
                Map<String, String> params = new HashMap<>();
                params.put("secret", SECRET_KEY);
                params.put("response", responseToken);
                return params;
            }
        };
        // below line of code is use to set retry
        // policy if the api fails in one try.
        request.setRetryPolicy(new DefaultRetryPolicy(
                // we are setting time for retry is 5 seconds.
                50000,

                // below line is to perform maximum retries.
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // at last we are adding our request to queue.
        queue.add(request);
    }



    void getAccessToken() {

        progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(PanValid.this);

        String url = "https://api.sandbox.co.in/authenticate";
        JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Gson gson = new Gson();
                        AccessPojo accessPojo;
                        accessPojo = gson.fromJson(response.toString(), AccessPojo.class);

                         access_tok = accessPojo.getAccess_token();

                      //  Toast.makeText(PanValid.this, "Response" + access_tok, Toast.LENGTH_SHORT).show();

                        progress_bar.setVisibility(View.GONE);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PanValid.this, " error" + error, Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("x-api-key", "key_live_vg0AG66VOVOYsC6DXoO3FKaLJBryrATz");
                paramss.put("x-api-secret", "secret_live_xo5Z38mSzROuCSLYNRwLgDDkNoI8l1QC");
                paramss.put("x-api-version", "1.0");
                return paramss;
            }

            //Pass Your Parameters here
          /*  @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
*//*
                params.put("query", editText_search.getText().toString());
*//*
                return params;
            }*/
        };
        mRequestQueue.add(jsonObjectRequest);

    }

    void  getPandetails() {

        progress_bar.setVisibility(View.VISIBLE);

        mRequestQueue = Volley.newRequestQueue(PanValid.this);

        String url = "https://api.sandbox.co.in/pans/"+pan_nu.getText().toString()+"?consent=y&reason=KYC purpose";
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
                        PanDetailsModel accessPojo;
                        accessPojo = gson.fromJson(response.toString(), PanDetailsModel.class);

                        Intent intent= new Intent(PanValid.this,PainDetails.class);
                        intent.putExtra("num",accessPojo.getData().getPan());

                        intent.putExtra("nam",accessPojo.getData().getFull_name());
                        intent.putExtra("sta",accessPojo.getData().getStatus());
                        intent.putExtra("cat",accessPojo.getData().getCategory());

                        intent.putExtra("f_name",accessPojo.getData().getFirst_name());
                        intent.putExtra("l_name",accessPojo.getData().getLast_name());
                        intent.putExtra("add",accessPojo.getData().getAadhaar_seeding_status());
                        intent.putExtra("las",accessPojo.getData().getLast_updated());

                        startActivity(intent);

                        Toast.makeText(PanValid.this, "Response" , Toast.LENGTH_SHORT).show();

                        progress_bar.setVisibility(View.GONE);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PanValid.this, "Invalid Pan Number !!!", Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);

                    }
                }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("Authorization",access_tok );
                paramss.put("x-api-key", "key_live_vg0AG66VOVOYsC6DXoO3FKaLJBryrATz");
                paramss.put("x-api-version", "1.0");
                return paramss;
            }

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("pan", "BSWPD9982K");

                return params;
            }
        };
        mRequestQueue.add(jsonObjectRequest);

    }



}