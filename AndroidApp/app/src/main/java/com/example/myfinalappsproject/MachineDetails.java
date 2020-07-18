package com.example.myfinalappsproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sun.jersey.core.util.StringIgnoreCaseKeyComparator;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.odata4j.consumer.ODataClientRequest;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.consumer.ODataConsumers;
import org.odata4j.consumer.behaviors.OClientBehavior;
import org.odata4j.core.ODataVersion;
import org.odata4j.core.OEntity;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.LoggingMXBean;

public class MachineDetails extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
// Variables
    ImageView backBtn;
    LineChart mLineChart;
    PieChart mPieChart;
    BottomNavigationView mbottomNavigation;
    Button mButton;
    TextView mDateCardOne,mDateCardTwo,mDateCardThree,mMachineID,mMachineStatus,mMachineProductivity,mWorkerName,mProductName,mMachineStartTime;
    MachineClass machineClass;
    UserClass workerClass,workerClasstwo;
    ODataConsumer consumer;
    Integer mMachineRunTime,mMachineOffTime,mMachineWaitTime,mMachineSetUpTime;
    ProductClass productClass;
    Integer mDay,mMonth,mMachineIdentify;
    Integer MachineID;
    String MachineName;
    Intent intentOne ;
////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_details);

//        mMonth=7;
//        mDay =14;
        Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DATE);
        mMonth = calendar.get(Calendar.MONTH)+1;
        // Get current day and month
/////////////////////////////////////////////////////////////////////////////////////////////////////

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

        hook();
        LineChartDisPlay();
        BottomNavifation();
        DateDisPlay();
        InformationDisPlay();

    }

    private void InformationDisPlay() {
        intentOne = getIntent();
        mMachineID.setText(intentOne.getStringExtra("MachineIdentity").substring(34));
        switch (intentOne.getStringExtra("MachineIdentity").substring(34)){
//            case "PC2":{
//                mMachineIdentify = 6;
//                break;
//            }
//            case "PC8":{
//                mMachineIdentify = 7;
//                break;
//            }
//            case "PC18":{
//                mMachineIdentify = 8;
//                break;
//            }
//            case "T1":{
//                mMachineIdentify = 9;
//                break;
//            }
//            case "TC6":{
//                mMachineIdentify = 10;
//                break;
//            }
//            case "TC7":{
//                mMachineIdentify = 11;
//                break;
//            }
//            case "TC8":{
//                mMachineIdentify = 12;
//                break;
//            }
//            case "TC9":{
//                mMachineIdentify = 13;
//                break;
//            }
//            case "TC10":{
//                mMachineIdentify = 14;
//                break;
//            }
//            case "TC11":{
//                mMachineIdentify = 15;
//                break;
//            }
//            case "TC14":{
//                mMachineIdentify = 16;
//                break;
//            }
//            case "MT1":{
//                mMachineIdentify = 17;
//                break;
//            }
//            case "MT2":{
//                mMachineIdentify = 18;
//                break;
//            }
//            case "MT3":{
//                mMachineIdentify = 19;
//                break;
//            }
//            case "MT15":{
//                mMachineIdentify = 20;
//                break;
//            }
//            case "MT18":{
//                mMachineIdentify = 21;
//                break;
//            }
//            case "P16":{
//                mMachineIdentify = 23;
//                break;
//            }
//            case "PC1":{
//                mMachineIdentify = 24;
//                break;
//            }
//            case "PC4":{
//                mMachineIdentify = 25;
//                break;
//            }
//            case "PC3":{
//                mMachineIdentify = 26;
//                break;
//            }
//            case "PC5":{
//                mMachineIdentify = 27;
//                break;
//            }
//            case "PC12":{
//                mMachineIdentify = 28;
//                break;
//            }
//            case "PC6":{
//                mMachineIdentify = 29;
//                break;
//            }
//            case "PC7":{
//                mMachineIdentify = 30;
//                break;
//            }
//            case "PC11":{
//                mMachineIdentify = 31;
//                break;
//            }
            case "DK1":{
                mMachineIdentify = 160;
                break;
            }
            case "CM1":{
                mMachineIdentify = 140;
                break;
            }
        }
        new InformationTask().execute(mDay,mMonth,mMachineIdentify);
    } // Make machine ID and exercute InformationTask

    private void DateDisPlay() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
//        mLocalTime = simpleDateFormat.format(calendar.getTime());
        String mLocalTimeForDisPlay = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String[] splitDate = mLocalTimeForDisPlay.split(",");
        mDateCardOne.setText(splitDate[1].trim());
        mDateCardTwo.setText(splitDate[1].trim());
        mDateCardThree.setText(splitDate[1].trim());
    } // Display Instance Day and Month

    private void BottomNavifation() {
        mbottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    private void hook() {
        mLineChart = findViewById(R.id.MachineDetail_linechart);
        backBtn  = findViewById(R.id.MachineDetail_backBtn);
        mPieChart = findViewById(R.id.MachineDetail_PipeChart);
        mbottomNavigation = findViewById(R.id.MachineDetail_bottom_nav);
        mButton = findViewById(R.id.MachineDetail_moreInforBtn);
        mDateCardOne = findViewById(R.id.MachineDetail_Date_Card_One);
        mDateCardTwo = findViewById(R.id.MachineDetail_Date_Card_Two);
        mDateCardThree = findViewById(R.id.MachineDetail_Date_Card_Three);
        mMachineID = findViewById(R.id.MachineDetail_MachineID);
        mMachineStatus = findViewById(R.id.MachineDetail_MachineStatus);
        mMachineProductivity = findViewById(R.id.MachineDetail_ProductivityTxt);
        mWorkerName = findViewById(R.id.MachineDetail_WorkerName);
        mProductName = findViewById(R.id.MachineDetail_ProductName);
        mMachineStartTime = findViewById(R.id.MachineDetail_TimeStart);
    } // Hook view with their ID

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void Back(View view) {
        onBackPressed();

    } // BackPress funtion

    public void MoreInforMation(View view){
        Intent intent = new Intent(getApplicationContext(),DetailInformation.class);
         intent.putExtra("MachineID",intentOne.getStringExtra("MachineIdentity").substring(34));
         intent.putExtra("MachineStatus",machineClass.getMachineStatus());
        intent.putExtra("MachineStartTime",productClass.getProductStartTime().toString());
        intent.putExtra("MachineOrigin",machineClass.getMachineOrigin());
        intent.putExtra("WorkerName",workerClasstwo.getUserName());
        intent.putExtra("WorkerID",workerClasstwo.getIdentityName());
        intent.putExtra("WorkerEmail",workerClasstwo.getEmail());
        intent.putExtra("ProductID",productClass.getProductName());
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(mButton,"Information_Btn_transition");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MachineDetails.this,pairs);
        startActivity(intent,options.toBundle());
    } // Jump to DaitailInformation Activity

    private ArrayList<Entry> linechartDataset(){
        ArrayList<Entry> dataSet = new ArrayList<Entry>();

        dataSet.add(new Entry(7,10));
        dataSet.add(new Entry(8,10));
        dataSet.add(new Entry(9,10));
        dataSet.add(new Entry(10,12));
        dataSet.add(new Entry(11,22));
        dataSet.add(new Entry(12,12));
        dataSet.add(new Entry(13,16));
        dataSet.add(new Entry(14,10));
        dataSet.add(new Entry(15,5));
        dataSet.add(new Entry(16,6));
        dataSet.add(new Entry(17,0));
        return dataSet;
    } // LineChart DataSet

    private void LineChartDisPlay() {

        LineDataSet lineDataSet = new LineDataSet(linechartDataset(),"data set");
        ArrayList<ILineDataSet> iLineDataSets =  new ArrayList<>();
        iLineDataSets.add(lineDataSet);
        LineData lineData = new LineData(iLineDataSets);
        mLineChart.setData(lineData);
        mLineChart.animateX(2000);
        mLineChart.getDescription().setEnabled(false);
        mLineChart.getXAxis().setDrawGridLines(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.getAxisLeft().setEnabled(false);
        mLineChart.getXAxis().setTextColor(getResources().getColor(R.color.IconColor_three));
        mLineChart.getLegend().setTextColor(getResources().getColor(R.color.IconColor_three));
        mLineChart.getLegend().setEnabled(false);

        lineDataSet.setColor(getResources().getColor(R.color.IconColor_three));
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleHoleRadius(5);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.TextColor_three));
        lineDataSet.setValueTextSize(10);

    } // Draw LineChart

    private void PieChartDisPlay() {
        ArrayList<PieEntry> products = new ArrayList<>();
        products.add(new PieEntry(mMachineRunTime,"Run"));
        products.add(new PieEntry(mMachineOffTime,"Stop"));
        products.add(new PieEntry(mMachineWaitTime,"Wait"));
        products.add(new PieEntry(mMachineSetUpTime,"Setup"));


        PieDataSet pieDataSet = new PieDataSet(products,"Productivity");
        pieDataSet.setColors(new int[]{getResources().getColor(R.color.ButtonColor_four),getResources().getColor(R.color.PieChart_color_one),getResources().getColor(R.color.PieChart_color_three),getResources().getColor(R.color.PieChart_color_two)});
        pieDataSet.setValueTextColor(getResources().getColor(R.color.TextColor_three));
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        mPieChart.setData(pieData);
        mPieChart.setCenterTextColor(R.color.IconColor_three);
        mPieChart.getDescription().setEnabled(false);
        mPieChart.animate();
        mPieChart.setHoleColor(R.color.CardColor_three);
        mPieChart.getLegend().setEnabled(false);
    }  // Draw PieChart

    String error;

    class InformationTask extends AsyncTask<Integer,Long,Integer>{

        @Override
        protected Integer doInBackground(Integer...integers) {

            Integer mMonth = integers[1];
            Integer mDay = integers[0];
            Integer mMachineIdentify =integers[2];
             try{
                for (OEntity DataFromDataBase : consumer.getEntities("ThoiGianMays")
                        .filter("day(NgayCapNhat) eq "+ mDay +" and month(NgayCapNhat) eq "+ mMonth+" and may eq "+ mMachineIdentify).orderBy("may").execute()){
                    machineClass = new MachineClass();
                    machineClass.setMachineStatus(DataFromDataBase.getProperty("Trangthaimay", Integer.class).getValue());
                    machineClass.setMachineID(DataFromDataBase.getProperty("may", Integer.class).getValue());
                    machineClass.setMachinePower(DataFromDataBase.getProperty("congsuat", Double.class).getValue());
                    machineClass.setMachineEnergy(DataFromDataBase.getProperty("NangLuong", Double.class).getValue());
                    machineClass.setMachineWaitTime(DataFromDataBase.getProperty("WaitTime", Integer.class).getValue());
                    machineClass.setMachineRunTime(DataFromDataBase.getProperty("RunningTime", Integer.class).getValue());
                    machineClass.setMachineOffTime(DataFromDataBase.getProperty("OffTime", Integer.class).getValue());
                    machineClass.setMachineSetUpTime(DataFromDataBase.getProperty("SetUpTime", Integer.class).getValue());
                    machineClass.setMachineDateTime(DataFromDataBase.getProperty("NgayCapNhat", LocalDateTime.class).getValue());
                }
                return 1;
            }catch (Exception ex){
                 error = ex.toString();
                return -1;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            switch (integer) {
                case 1:{
//                   Toast.makeText(getApplicationContext(),"Data has been update",Toast.LENGTH_SHORT).show();
                     new MachineInformationTask().execute();
                    ResultDisplay();
                    break;
                }
                case -1:{
                    Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                    break;
                }
            }
        }
    }  // Load data form entitity "ThoiGianMay"

    private void ResultDisplay() {
                mMachineRunTime = machineClass.getMachineRunTime();
                mMachineOffTime = machineClass.getMachineOffTime();
                mMachineSetUpTime = machineClass.getMachineSetUpTime();
                mMachineWaitTime = machineClass.getMachineSetUpTime();
                switch (machineClass.getMachineStatus()){
                    case 0: {
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
                Integer Productivity = (mMachineRunTime)*100/(mMachineRunTime+mMachineWaitTime+mMachineSetUpTime+mMachineOffTime);
                mMachineProductivity.setText(Productivity+"%");
                PieChartDisPlay();
        } // Display machine Status,calculate, display machine productivity and draw pipeChart

    String errorTwo;

    class MachineInformationTask extends AsyncTask<Integer,Long,Integer>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Integer doInBackground(Integer... integers) {
                MachineID = machineClass.getMachineID();
                try{
                    for (OEntity DataFromDataBaseTwo : consumer
                            .getEntities("Mays").filter(" Id eq "+ MachineID).execute()){
                    machineClass.setMachineName(DataFromDataBaseTwo.getProperty("MaSo",String.class).getValue());
                    machineClass.setMachineOrigin(DataFromDataBaseTwo.getProperty("NhanHieu",String.class).getValue());
                }
                    return 1;
                }
                catch (Exception ex){
                    errorTwo = ex.toString();
                    return -1;
                }
            }
            @Override
            protected void onPostExecute(Integer integer) {
                switch (integer){
                    case 1:{
//                        Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_LONG).show();
                        MachineName = machineClass.getMachineName();
                        new WorkerInformationTask().execute();
                        break;
                    }
                    case -1:{
                        Toast.makeText(getApplicationContext(),errorTwo,Toast.LENGTH_LONG).show();
                        break;
                    }

                }
            }
        } // Load data from entity "Mays"

    String errorThree;

    class WorkerInformationTask extends AsyncTask<String,Long,Integer>{
            @Override
            protected Integer doInBackground(String... strings) {
                try{
                    for (OEntity DataFromDataBaseThree : consumer.getEntities("CongViecs").filter("May eq '" + "MT3" +"'").execute()){
                    workerClass = new UserClass();
                    productClass = new ProductClass();
                    workerClass.setID(DataFromDataBaseThree.getProperty("CongViec_NhanVien",Integer.class).getValue());
                    productClass.setProductName(DataFromDataBaseThree.getProperty("MaChiTiet",String.class).getValue());
                    productClass.setProductStartTime(DataFromDataBaseThree.getProperty("GioBD",LocalDateTime.class).getValue());
                    }
                    return 1;
                }
                catch (Exception ex){
                    errorThree = ex.toString();
                    return -1;
                }
            }

            @Override
            protected void onPostExecute(Integer integer) {
                switch (integer){
                    case 1:{
//                        Toast.makeText(getApplicationContext(),"Finish",Toast.LENGTH_LONG).show();
                        mProductName.setText(productClass.getProductName().toString());
                        mMachineStartTime.setText(productClass.getProductStartTime().toString().substring(11,16));
                        new WorkerTask().execute();
                        break;
                    }
                    case -1:{
                        Toast.makeText(getApplicationContext(),errorThree,Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        } // Load data form entity "CongViecs"

    class WorkerTask extends AsyncTask<Integer,Long,Integer>{

            @Override
            protected Integer doInBackground(Integer... integers) {
                try{
                    workerClasstwo = new UserClass();
                    for (OEntity DataFromDataBaseFour : consumer.getEntities("NhanViens").filter("Id eq 27 and NghiViec eq false").execute()){
                        workerClasstwo.setUserName(DataFromDataBaseFour.getProperty("HoTen",String.class).getValue());
                        workerClasstwo.setIdentityName(DataFromDataBaseFour.getProperty("MaSo",String.class).getValue());
                        workerClasstwo.setEmail(DataFromDataBaseFour.getProperty("Email",String.class).getValue());
                    }
                    return 1;
                }catch (Exception ex){

                    errorThree = ex.toString();
                    return -1;
                }
            }

            @Override
            protected void onPostExecute(Integer integer) {
                switch (integer){
                    case 1:{
                        Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
                        mButton.setVisibility(View.VISIBLE);
                        mWorkerName.setText(workerClasstwo.getUserName());
                        break;
                    }
                    case -1:{
                        Toast.makeText(getApplicationContext(),errorThree,Toast.LENGTH_LONG).show();
                        break;
                    }
                }
            }
        } // Load data from entity "NhanViens"

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_home:
            {
                Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_days:
            {
                Intent intent = new Intent(getApplicationContext(),Days.class);
                startActivity(intent);
                break;
            }
            case R.id.nav_weeks:
                Intent intent = new Intent(getApplicationContext(),Weeks.class);
                startActivity(intent);
                break;
        }
        return true;
    } // Select icon in Navigation Bottom
}
