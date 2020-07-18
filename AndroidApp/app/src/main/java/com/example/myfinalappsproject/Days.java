package com.example.myfinalappsproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Days extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ImageView backBtn;
    BarChart mBarChart;
    LineChart mLineChart;
    BottomNavigationView mBottomNavigation;
    LinearLayout mCardOne,mCardTwo;
    TextView mPowerTxt,mPowerDescTxt,mProductivityTxt,mProductivityDescTxt;
    Animation mTextAnim,mCardAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        hook();
        BartChartDisPlay();
        LineChartDisPlay();
        BottomNavigation();
        AnimationDisPlay();

    }

    private void AnimationDisPlay() {
        mTextAnim = AnimationUtils.loadAnimation(Days.this,R.anim.text_anim);
        mCardAnim = AnimationUtils.loadAnimation(Days.this,R.anim.card_anim);
        mCardOne.setAnimation(mCardAnim);
        mCardTwo.setAnimation(mCardAnim);
        mPowerTxt.setAnimation(mTextAnim);
        mPowerDescTxt.setAnimation(mTextAnim);
        mProductivityTxt.setAnimation(mTextAnim);
        mProductivityDescTxt.setAnimation(mTextAnim);
    }

    private void BottomNavigation() {
        mBottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void Back(View view){
        onBackPressed();
    }

    private void hook() {
        mCardOne = findViewById(R.id.Days_Card_one);
        mCardTwo = findViewById(R.id.Days_Card_two);
        mPowerTxt = findViewById(R.id.Days_Power);
        mPowerDescTxt = findViewById(R.id.Days_PowerDecs);
        mProductivityTxt = findViewById(R.id.Days_Productivity);
        mProductivityDescTxt = findViewById(R.id.Days_ProductivityDesc);
        backBtn = findViewById(R.id.Days_backBtn);
        mBarChart = findViewById(R.id.Days_BarChart);
        mLineChart = findViewById(R.id.Days_LineChart);
        mBottomNavigation = findViewById(R.id.Days_Bottom_nav);
    }

    private ArrayList<Entry> linechartDataset(){

        ArrayList<Entry> dataSet = new ArrayList<Entry>();

        dataSet.add(new Entry(7,0));
        dataSet.add(new Entry(8,9));
        dataSet.add(new Entry(9,10));
        dataSet.add(new Entry(10,11));
        dataSet.add(new Entry(11,12));
        dataSet.add(new Entry(12,0));
        dataSet.add(new Entry(13,10));
        dataSet.add(new Entry(14,11));
        dataSet.add(new Entry(15,12));
        dataSet.add(new Entry(16,11));
        dataSet.add(new Entry(17,6));
        return dataSet;
    }

    private void LineChartDisPlay() {

        LineDataSet lineDataSet = new LineDataSet(linechartDataset(),"data");
        ArrayList<ILineDataSet> iLineDataSets =  new ArrayList<>();
        iLineDataSets.add(lineDataSet);
        LineData lineData = new LineData(iLineDataSets);
        mLineChart.setData(lineData);
        mLineChart.animateX(2000);
        mLineChart.getXAxis().setDrawGridLines(false);
        mLineChart.getAxisLeft().setDrawGridLines(false);
        mLineChart.getAxisRight().setDrawGridLines(false);
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.getAxisLeft().setEnabled(false);
        mLineChart.getLegend().setEnabled(false);
        mLineChart.getXAxis().setTextColor(getResources().getColor(R.color.TextColor_four_opt_two));
        mLineChart.getDescription().setEnabled(false);

        lineDataSet.setColor(getResources().getColor(R.color.IconColor_three));
        lineDataSet.setCircleColor(Color.YELLOW);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setLineWidth(2);
        lineDataSet.setCircleHoleRadius(5);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.TextColor_three));
        lineDataSet.setValueTextSize(10);

    }

    private void BartChartDisPlay() {

        ArrayList<BarEntry> power = new ArrayList<>();

        power.add(new BarEntry(7,550));
        power.add(new BarEntry(8,600));
        power.add(new BarEntry(9,400));
        power.add(new BarEntry(10,450));
        power.add(new BarEntry(11,400));
        power.add(new BarEntry(12,0));
        power.add(new BarEntry(13,300));
        power.add(new BarEntry(14,100));
        power.add(new BarEntry(15,500));
        power.add(new BarEntry(16,400));
        power.add(new BarEntry(17,0));

        BarDataSet barDataSet = new BarDataSet(power,"Power");
        barDataSet.setValueTextColor(getResources().getColor(R.color.TextColor_three));
        barDataSet.setValueTextSize(10f);
        barDataSet.setColor(getResources().getColor(R.color.ButtonColor_four));

        BarData barData = new BarData(barDataSet);
        mBarChart.setData(barData);
        mBarChart.setFitBars(true);
        mBarChart.getDescription().setEnabled(false);
        mBarChart.animateX(2000);
        mBarChart.animateY(1500);
        mBarChart.getXAxis().setDrawGridLines(false);
        mBarChart.getAxisLeft().setDrawGridLines(false);
        mBarChart.getAxisRight().setDrawGridLines(false);
        mBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mBarChart.getAxisRight().setEnabled(false);
        mBarChart.getAxisLeft().setEnabled(false);
        mBarChart.getLegend().setEnabled(false);
        mBarChart.getXAxis().setTextColor(getResources().getColor(R.color.TextColor_four_opt_two));

    }

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
    }
}
