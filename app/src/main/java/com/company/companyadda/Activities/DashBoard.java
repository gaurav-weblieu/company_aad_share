package com.company.companyadda.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.company.companyadda.Adapter.Nav_list_adapter;
import com.company.companyadda.Adapter.SliderAdapterExample;
import com.company.companyadda.Adapter.dashboard_list_adapter;
import com.company.companyadda.ApiModels.ProfileModel;
import com.company.companyadda.ApiModels.SliderItem;
import com.company.companyadda.CheckNetwork;
import com.company.companyadda.Fragment.HomeFragment;
import com.company.companyadda.Fragment.MyRequestFragment;
import com.company.companyadda.Fragment.NewsContainer;
import com.company.companyadda.Fragment.OffersFragment;
import com.company.companyadda.Fragment.ProfileFragment;
import com.company.companyadda.Pojo.ServiceListPojo;
import com.company.companyadda.R;
import com.company.companyadda.ViewModel.Category_View_Model;
import com.company.companyadda.api_service.RetrofitServicesHandler;
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private SharedPreferences sharedPreferences_login;
    private BottomNavigationView bottom_navigation;
    AlertDialog.Builder builder;

    TextView textView_name;
    TextView textView_number;
    TextView textView_email;
    ImageView imageView_profile_image;
    private ViewGroup viewGroup;
    private String user_id;
    FloatingActionButton bot_chat;

    ArrayList<Integer> arrayList_nav_icon= new ArrayList<>();
    ArrayList<String> arrayList_nav_name= new ArrayList<>();
    private RecyclerView recycleView_nevgation_menu;
    private LinearLayout linearLayout_header;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        sharedPreferences_login=getSharedPreferences("login_details",MODE_PRIVATE);

        builder = new AlertDialog.Builder(this);

        toolbar = findViewById(R.id.toolbar_dashboard);
        bottom_navigation=findViewById(R.id.bottom_navigation);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        linearLayout_header =  findViewById(R.id.linearLayout_header);
        recycleView_nevgation_menu =  findViewById(R.id.recycleView_nevgation_menu);
        bot_chat =  findViewById(R.id.bot_chat);

        bot_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(DashBoard.this,ChatBoot.class));
            }
        });

        arrayList_nav_icon.add(R.drawable.home_image_icon);
        arrayList_nav_icon.add(R.drawable.request_image_icon);
        arrayList_nav_icon.add(R.drawable.wallet_image_icon);
        arrayList_nav_icon.add(R.drawable.payment_image_icon);
        arrayList_nav_icon.add(R.drawable.payment_image_icon);
        arrayList_nav_icon.add(R.drawable.itr_icon);
        arrayList_nav_icon.add(R.drawable.calculator_icon);
        arrayList_nav_icon.add(R.drawable.it_login);
        arrayList_nav_icon.add(R.drawable.status_din);
        arrayList_nav_icon.add(R.drawable.refund_image);
        arrayList_nav_icon.add(R.drawable.link);
        arrayList_nav_icon.add(R.drawable.offer_image_icon);
        arrayList_nav_icon.add(R.drawable.partners_image_icon);
        arrayList_nav_icon.add(R.drawable.refer_earn_icon);
        arrayList_nav_icon.add(R.drawable.term_cod_image_icon);
        arrayList_nav_icon.add(R.drawable.about_us_image_icon);
        arrayList_nav_icon.add(R.drawable.contact_us);
        arrayList_nav_icon.add(R.drawable.chat_icon);
        arrayList_nav_icon.add(R.drawable.feedback_icon);
        arrayList_nav_icon.add(R.drawable.raise_a_query_icon);
        arrayList_nav_icon.add(R.drawable.privacy_policy_icon);
        arrayList_nav_icon.add(R.drawable.log_out);

        arrayList_nav_name.add("Home");
        arrayList_nav_name.add("My Request");
        arrayList_nav_name.add("Wallet");
        arrayList_nav_name.add("GST by PAN");
        arrayList_nav_name.add("Echallan Payment");
        arrayList_nav_name.add("Free ITR");
        arrayList_nav_name.add("ARN Status");
        arrayList_nav_name.add("IT Login");
        arrayList_nav_name.add("DIN Status");
        arrayList_nav_name.add("Tax Calculator");
        arrayList_nav_name.add("Pan Link with Addhar");
        arrayList_nav_name.add("Refund Status");
        arrayList_nav_name.add("Offers");
        arrayList_nav_name.add("Partners");
        arrayList_nav_name.add("Refer and Earn");
        arrayList_nav_name.add("Term and Conditions");
        arrayList_nav_name.add("About Us");
        arrayList_nav_name.add("Contact Us");
        arrayList_nav_name.add("Feedback");
        arrayList_nav_name.add("Raise a Query");
        arrayList_nav_name.add("Privacy Policy");
        arrayList_nav_name.add("Log Out");

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        bottom_navigation.setItemIconTintList(null);


        recycleView_nevgation_menu.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false ));
        Nav_list_adapter navigation_menu_list_adapter = new Nav_list_adapter(DashBoard.this,arrayList_nav_icon,arrayList_nav_name);
        recycleView_nevgation_menu.setAdapter(navigation_menu_list_adapter);


        textView_name=findViewById(R.id.textView_name);
        textView_number=findViewById(R.id.textView_number);
        textView_email=findViewById(R.id.textView_email);
         imageView_profile_image=findViewById(R.id.imageView_profile_image);

         new Thread(runnable).start();

        linearLayout_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this,Profile.class));
            }
        });


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                // mainLayout.setTranslationX(slideOffset * drawerView.getWidth());
                drawer.bringChildToFront(drawerView);
                drawer.requestLayout();
            }
        };

        //  actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.purple));


        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_round_menu_24, getTheme());
        actionBarDrawerToggle.setHomeAsUpIndicator(drawable);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });


        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home_bottom_nav:
                        startActivity(new Intent(DashBoard.this,DashBoard.class));
                        break;

                    case R.id.building_bottom_nav:

                       /* openFragment(new MyRequestFragment());*/


                        Uri uri = Uri.parse("smsto:" + "9524872487");
                        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                        i.setPackage("com.whatsapp");
                        startActivity(Intent.createChooser(i, ""));

                        break;

                        case R.id.more_bottom_nav:
                        openFragment(new OffersFragment());
                        break;

                    case R.id.feed:
                        openFragment(new NewsContainer());
                        break;

                    case R.id.profile_bottom_nav:
                        openFragment(new ProfileFragment());
                        break;

                }

                return true;
            }
        });


        setData();


        openFragment(new HomeFragment());


        if(CheckNetwork.isInternetAvailable(DashBoard.this)) //returns true if internet available
        {


        }
        else
        {

            startActivity(new Intent(this,NoInternet.class));
            finish();

           // Toast.makeText(DashBoard.this,"No Internet Connection",).show();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.noti:
                startActivity(new Intent(DashBoard.this, Notification.class));
                break;


        }


        return super.onOptionsItemSelected(item);
    }

    public void searchClick(View view) {
        startActivity(new Intent(DashBoard.this,New_service.class));
    }



    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout_main_container, fragment);
        transaction.commit();
    }

    void setData(){

        textView_name.setText(" , "+sharedPreferences_login.getString("name","").toString());
        textView_number.setText(sharedPreferences_login.getString("phone","").toString());
        textView_email.setText(sharedPreferences_login.getString("email","").toString());

        String image_url = sharedPreferences_login.getString("image_url", "");


        if (image_url != "") {
            Glide.with(this)
                    .load(image_url)
                    .fitCenter()
                    .into(imageView_profile_image);

        }

    }


    Runnable runnable= new Runnable() {

        @Override
        public void run() {

            user_id=sharedPreferences_login.getString("user_id","");

            RequestBody r_user_id = RequestBody.create(MediaType.parse("multipart/form-data"), user_id);

            Call<ProfileModel> userLoginCall = RetrofitServicesHandler
                    .getInstance()
                    .getApi()
                    .profile(
                            r_user_id);

            userLoginCall.enqueue(new Callback<ProfileModel>() {
                @Override
                public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                    if (response.body() != null) {
                        ProfileModel profileModel = response.body();

                        if (profileModel.getResult().getSTATUS().equals("true")) {
                        //    Toast.makeText(DashBoard.this, "" + profileModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();


                            sharedPreferences_login.edit().putString("name", profileModel.getProfile().get(0).getName()).apply();
                            sharedPreferences_login.edit().putString("phone", profileModel.getProfile().get(0).getMobile()).apply();
                            sharedPreferences_login.edit().putString("email", profileModel.getProfile().get(0).getEmail()).apply();
                            sharedPreferences_login.edit().putString("image_url", profileModel.getProfile().get(0).getImage()).apply();




                        } else if (!profileModel.getResult().getSTATUS().equals("true")) {
                          //  Toast.makeText(DashBoard.this, profileModel.getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();
                        }
                    } else if (response.body() == null) {

                       // Toast.makeText(DashBoard.this, response.body().getResult().getMESSAGE(), Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<ProfileModel> call, Throwable t) {
                    String s = t.toString();
                    Toast.makeText(DashBoard.this, "" + t, Toast.LENGTH_SHORT).show();
                }
            });

        }
    };

    @Override
    public void onBackPressed() {
        if (bottom_navigation.getSelectedItemId() == R.id.home_bottom_nav) {
            super.onBackPressed();
        } else {
            finish();
            bottom_navigation.setSelectedItemId(R.id.home_bottom_nav);
        }
    }



}