package com.example.myfinalappsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.odata4j.consumer.ODataClientRequest;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.consumer.behaviors.OClientBehavior;
import org.odata4j.core.ODataVersion;
import org.odata4j.core.OEntity;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Animation topAnim, BotAnim, RightAnim, RightAnimTwo, RightAnimThree;
    DrawerLayout mdrawerLayout;
    NavigationView mnavigationview;
    ImageView menuBtn, notifiactionBtn, mExitBtn, mCloseButton, mNotificationBell;
    Button  mExitDialogBtn;
    LinearLayout mMap, mRelatedLayout;
    RelativeLayout contentView;
    ODataConsumer consumer;
    TextView machineCM1, machineDK1,machineDK2,machineE1;
    MachineClass machineClass;

    private long TimeReMaining = 3600000;// Time limit to execute the DataDisPlays
    private long TimeRepeatTask = 60000; // Repeat the task with cycle of 60.000 milliSecond

    ArrayList<MachineClass> MachineData = new ArrayList<MachineClass>();

    final float END_SCALE = 0.7f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

////////////////////////////////////////////////////////////////////////////////////////////////////

        String serviceUrl = ServiceAddress.serviceURL;
        OClientBehavior bacsicAthur = new OClientBehavior() {
            @Override
            public ODataClientRequest transform(ODataClientRequest request) {
                //return request.header("Accept", "application/json");
                return request.header("MaxDataServiceVersion", ODataVersion.V2.asString);
            }
        };
        consumer = ODataConsumers.newBuilder(serviceUrl).setClientBehaviors(bacsicAthur).build();

////////////////////////////////////////////////////////////////////////////////////////////////////


        Hooks(); // Hàm ánh xạ các view
        AnimationDisplay(); // Hàm trình bày các animation
        NavigationDrawer(); // Hàm cho Navigation drawer
        AnimateNavigationDrawer();
        BottomNavigation(); // Hàm cho Bottom Navigation
        Exit();             // Hàm cho Exit dialog

////////////////////////////////////////////////////////////////////////////////////////////////////

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                TimeReMaining = TimeReMaining - TimeRepeatTask; // Reduce time by TimeRepeatTask
                DataDisplay();
                handler.postDelayed(this,TimeRepeatTask);  // Do the task after TimeRepeatTask
            }
        };
        handler.postDelayed(runnable,500); // Original time value
    }


///////////////////////////////////////////////////////////////////////////////////////////////////

    private void DataDisplay() {
        new MachineDataTask().execute();

    }

    private void AnimateNavigationDrawer() {

        mdrawerLayout.setScrimColor(getResources().getColor(R.color.ButtonColor_four));

        mdrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }

    private void Exit() {
        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(DashBoard.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.exit_dialog);
                dialog.show();

                mExitDialogBtn = dialog.findViewById(R.id.exit_dialog_Exit);
                mCloseButton = dialog.findViewById(R.id.exit_dialog_close);

                mCloseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                mExitDialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                       System.exit(0);
                    }
                });

            }
        });

    }

    private void BottomNavigation() {

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    public void Detail(View view) {
        String viewID = getResources().getResourceName(view.getId());
        Intent intent = new Intent(getApplicationContext(), MachineDetails.class);
        intent.putExtra("MachineIdentity",viewID);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(mNotificationBell, "Notification_transition");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashBoard.this, pairs);
        startActivity(intent, options.toBundle());
    }

    private void Hooks() {
        contentView = findViewById(R.id.content);
        mExitBtn = findViewById(R.id.Dashboard_exitBtn);
        mRelatedLayout = findViewById(R.id.Relate_layout_one);
        mMap = findViewById(R.id.Dashboard_maps);
        bottomNavigationView = findViewById(R.id.Dashboard_Bottom_nav);
        mdrawerLayout = findViewById(R.id.Dashboard_drawerLayout);
        mnavigationview = findViewById(R.id.Dashboard_drawer_nav);
        menuBtn = findViewById(R.id.Dashboard_menuBtn);
        notifiactionBtn = findViewById(R.id.Dashboard_notificationBtn);
        machineCM1 = findViewById(R.id.CM1);
        machineDK1 = findViewById(R.id.DK1);
        machineDK2 = findViewById(R.id.DK2);
        machineE1 = findViewById(R.id.E1);
        mNotificationBell = findViewById(R.id.Dashboard_Notification_Bell);
    }

    private void NavigationDrawer() {
        mnavigationview.bringToFront();
        mnavigationview.setNavigationItemSelectedListener(this);
        mnavigationview.setCheckedItem(R.id.nav_home);
        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mdrawerLayout.isDrawerVisible(GravityCompat.START))
                    mdrawerLayout.closeDrawer(GravityCompat.START);
                else
                    mdrawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    String error;//  error to catch Exception

    private class MachineDataTask extends AsyncTask<String, Long, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Integer doInBackground(String...params) {

            try{
                for (OEntity DataFromDataBase : consumer.getEntities("TinhTrangMays").orderBy("may").execute())
                {
                    machineClass = new MachineClass();
                    machineClass.setMachineID(DataFromDataBase.getProperty("may", Integer.class).getValue());
                    machineClass.setMachineStatus(DataFromDataBase.getProperty("trangThai", Integer.class).getValue());
                    machineClass.setMachinePower(DataFromDataBase.getProperty("CongSuat", Double.class).getValue());
                    MachineData.add(machineClass);
                }
                return 1;
            }catch (Exception ex){
                error = ex.toString();
                    return -1;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            switch (integer){
                case 1:{
                    Toast.makeText(DashBoard.this,"Data has been update",Toast.LENGTH_SHORT).show();
                    Display();
                    break;
                }
                case -1:{
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }
    @Override
    public void onBackPressed() {

        if (mdrawerLayout.isDrawerVisible(GravityCompat.START))
            mdrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void AnimationDisplay() {

        topAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top_anim);
        BotAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_anim);
        RightAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_anim);
        RightAnimTwo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_anim_two);
        RightAnimThree = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_anim_three);

        mRelatedLayout.setAnimation(topAnim);
        mMap.setAnimation(RightAnimThree);
        bottomNavigationView.setAnimation(BotAnim);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_days: {
                Intent intent = new Intent(getApplicationContext(), Days.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(mNotificationBell, "Notification_transition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashBoard.this, pairs);
                startActivity(intent, options.toBundle());
                break;
            }
            case R.id.nav_weeks: {
                Intent intent = new Intent(getApplicationContext(), Weeks.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(mNotificationBell, "Notification_transition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashBoard.this, pairs);
                startActivity(intent, options.toBundle());
                break;
            }
            case R.id.nav_months: {
                Intent intent = new Intent(getApplicationContext(), Months.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(mNotificationBell, "Notification_transition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashBoard.this, pairs);
                startActivity(intent, options.toBundle());
                break;
            }
            case R.id.nav_SignIn: {
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(mNotificationBell, "Notification_transition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashBoard.this, pairs);
                startActivity(intent, options.toBundle());
                break;
            }

            case R.id.nav_profile: {
                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(mNotificationBell, "Notification_transition");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashBoard.this, pairs);
                startActivity(intent, options.toBundle());
                break;
            }
        }
        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Display() {
        DisplayCM1();
        DisplayCK1();
        DisplayCK2();
    }

    private void DisplayCM1() {
        for ( MachineClass ID : MachineData){
            if (ID.getMachineID()==160){
                switch (ID.getMachineStatus()) {
                    case 0: {
                        machineCM1.setText("CM1 Running");
                        break;
                    }
                    case 3: {
                        machineCM1.setText("CM1 Error");
                        break;
                    }
                    case 4: {
                        machineCM1.setText("CM1 Off");
                        break;
                    }
                }
            }
        }
    }

    private void DisplayCK1() {
        for ( MachineClass ID : MachineData){
            if (ID.getMachineID()==140){
                switch (ID.getMachineStatus()) {
                    case 0: {
                        machineDK1.setText("DK1 Running");
                        break;
                    }
                    case 3: {
                        machineDK1.setText("DK1 Error");
                        break;
                    }
                    case 4: {
                        machineDK1.setText("DK1 Off");
                        break;
                    }
                }
            }
        }
    }

    private void DisplayCK2() {
        for ( MachineClass ID : MachineData){
            if (ID.getMachineID()==141){
                switch (ID.getMachineStatus()) {
                    case 0: {
                        machineDK2.setText("DK2 Running");
                        break;
                    }
                    case 3: {
                        machineDK2.setText("DK2 Error");
                        break;
                    }
                    case 4: {
                        machineDK2.setText("DK2 Off");
                        break;
                    }
                }
            }
        }
    }

///////////////////////////////////////////////////////////////////////////////////////////////////
}



