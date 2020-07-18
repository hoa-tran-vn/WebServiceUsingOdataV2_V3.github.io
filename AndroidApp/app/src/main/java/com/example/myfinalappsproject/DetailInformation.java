package com.example.myfinalappsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.util.Calendar;

public class DetailInformation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
// Variables
    BottomNavigationView mBottomNavigation;
    TextView mDayCardOne,mDayCardTwo,mDayCardThree,mMachineID,mMachineStatus,mMachineStartTime,mMachineOrigin;
    TextView mWorkerName,mWorkerId,mWorkerEmail;
    TextView mProductID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);

        Hook();
        BottomNavigation();
        DisplayInformation();
    }

    private void DisplayInformation() {
        Intent intent = getIntent();
        Calendar calendar = Calendar.getInstance();
        String mLocalTimeForDisPlay = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String[] splitDate = mLocalTimeForDisPlay.split(",");
        mDayCardOne.setText(splitDate[1].trim());
        mDayCardTwo.setText(splitDate[1].trim());
        mDayCardThree.setText(splitDate[1].trim());
        mMachineID.setText(intent.getStringExtra("MachineID"));
        mMachineStartTime.setText(intent.getStringExtra("MachineStartTime").substring(11,16));
        mMachineOrigin.setText(intent.getStringExtra("MachineOrigin"));
        mWorkerName.setText(intent.getStringExtra("WorkerName"));
        mWorkerId.setText(intent.getStringExtra("WorkerID"));
        mWorkerEmail.setText(intent.getStringExtra("WorkerEmail"));
        mProductID.setText(intent.getStringExtra("ProductID"));
        switch (intent.getIntExtra("MachineStatus",0)){
            case 0:{
                mMachineStatus.setText("Running");
                break;
            }
            case 3:{
                mMachineStatus.setText("Error");
                break;
            }
            case 4:{
                mMachineStatus.setText("Off");
                break;
            }
        }
    } // Display all information

    private void BottomNavigation() {
        mBottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void Hook() {
        mBottomNavigation = findViewById(R.id.DetailInfor_bottom_nav);
        mDayCardOne = findViewById(R.id.DetailInfor_DayCardOne);
        mDayCardTwo = findViewById(R.id.DetailInfor_DayCardTwo);
        mDayCardThree = findViewById(R.id.DetailInfor_DayCardThree);
        mMachineID = findViewById(R.id.DetailInfor_MachineID);
        mMachineStatus = findViewById(R.id.DetailInfor_MachineStatus);
        mMachineStartTime = findViewById(R.id.DetailInfor_MachineStartTime);
        mMachineOrigin = findViewById(R.id.DetailInfor_MachineOrigin);
        mWorkerName = findViewById(R.id.DetailInfor_WorkerName);
        mWorkerId = findViewById(R.id.DetailInfor_WorkerID);
        mWorkerEmail = findViewById(R.id.DetailInfor_WorkerEmail);
        mProductID = findViewById(R.id.DetailInfor_ProductID);
    } // Hook view with their ID

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void Back(View view){
        onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_days:
            {
                Intent intent = new Intent(getApplicationContext(),Days.class);
                startActivity(intent);
                break;
            }
            case  R.id.nav_weeks:
            {
                Intent intent = new Intent(getApplicationContext(),Weeks.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_home:
            {
                Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intent);
                break;
            }

        }
        return true;
    }
}
