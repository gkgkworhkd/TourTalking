package com.example.user.TourTalking.estimate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.calender.CalendarActivity;
import com.example.user.TourTalking.country_list.CompanyLsitAsycnTask;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-14.
 */


public class EstimateActivity extends AppCompatActivity {
    private TextView plan, arrvePlan;
    private String finshDay;
    private String depAriplane;
    private String TAG;
    private String arrvieDay;
    private String depDay;
    private String startDay;
    private String destName;
    EstimatePayCountAsycnTask asycnTask;
    boolean flag;
    private String cityName;
    public static final int CHCOMPNAY = 11;
    private TextView destTextView;
    private CheckedTextView tour, rest, business, golf, sea, leisure, check_near_air, check_near_downtw, check_near_tour, check_near_sea, check_cheap, check_luxury;
    private ArrayList<CheckedTextView> checkedTextViews;
    public static EstimateActivity estimateActivity;
    private int count = 0;


    private String apName;
    private TextView apText;
    private boolean choiceCompany;
    String companyName;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String individual = bundle.getString("indiviPay");
            indiviPay.setText(individual);
        }
    };
    TextView adultCount;
    TextView childCount;
    TextView totalPay;
    TextView indiviPay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        flag = getIntent().getBooleanExtra("type", false);
        /*Intent intent = new Intent(EstimateActivity.this, EstimateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("type", true);
        startActivity(intent);*/
        init();
        if (getIntent().getStringExtra("cityName") != null) {

            cityName = getIntent().getStringExtra("cityName");
            destTextView.setText(cityName);
        } else if (getIntent().getStringExtra("countryName") != null) {

            cityName = getIntent().getStringExtra("countryName");
            destTextView.setText(cityName);
        } else if (getIntent().getStringExtra("companyName") != null) {

            companyName = getIntent().getStringExtra("companyName");
            destTextView.setText(companyName);
        }

        //Log.d(TAG, cityName + "의 값은? ");

    }

    public void makeCheck() {
        for (int i = 0; i < checkedTextViews.size(); i++) {
            final CheckedTextView ch = checkedTextViews.get(i);
            ch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ch.isChecked())
                        ch.setChecked(false);
                    else
                        ch.setChecked(true);
                }
            });

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        depDay = intent.getStringExtra("depDay");
        Log.d(TAG, "인텐트를 받았다" + intent.getStringExtra("arriveDay"));
        if (intent.getStringExtra("arriveDay") != null) {
            arrvieDay = intent.getStringExtra("arriveDay");
            startDay = makePlan(arrvieDay);
            Log.d(TAG, "출발날자는 : " + startDay);
            plan.setText(startDay);
        } else if (depDay != null) {
            finshDay = makePlan(depDay);
            arrvePlan.setText(finshDay);
        }
        if ((destName = intent.getStringExtra("countName")) != null) {
            destTextView.setText(destName);
        }
        if (intent.getBooleanExtra("choiceCompany", true)) {
            choiceCompany = true;
        }

        if ((apName = intent.getStringExtra("apName")) != null) {
            apText.setText(apName);

        }
        //Log.d(TAG, choiceCompany + "의 값은? ");
    }

    public String makePlan(String req) {
        String result = null;
        String year = req.substring(0, 4);
        String month = req.substring(4, 6);
        String day = req.substring(6, 8);
        result = year + "." + month + "." + day;
        return result;
    }

    public void init() {

        setContentView(R.layout.estimate_activity);
        estimateActivity = this;
        tour = (CheckedTextView) findViewById(R.id.check_tour);
        rest = (CheckedTextView) findViewById(R.id.check_rest);
        business = (CheckedTextView) findViewById(R.id.check_business);
        golf = (CheckedTextView) findViewById(R.id.check_golf);
        sea = (CheckedTextView) findViewById(R.id.check_sea);
        leisure = (CheckedTextView) findViewById(R.id.check_leisure);
        check_near_air = (CheckedTextView) findViewById(R.id.check_near_air);
        check_near_downtw = (CheckedTextView) findViewById(R.id.check_near_downtw);
        check_near_tour = (CheckedTextView) findViewById(R.id.check_near_tour);
        check_near_sea = (CheckedTextView) findViewById(R.id.check_near_sea);
        check_cheap = (CheckedTextView) findViewById(R.id.check_cheap);
        check_luxury = (CheckedTextView) findViewById(R.id.check_luxury);
        destTextView = (TextView) findViewById(R.id.est_destName);
        apText = (TextView) findViewById(R.id.est_depature);

        adultCount = (TextView) findViewById(R.id.est_adult);
        childCount = (TextView) findViewById(R.id.est_child);
        totalPay = (TextView) findViewById(R.id.est_totalpay);
        indiviPay = (TextView) findViewById(R.id.est_indivipay);


        checkedTextViews = new ArrayList<CheckedTextView>();
        checkedTextViews.add(tour);
        checkedTextViews.add(rest);
        checkedTextViews.add(business);
        checkedTextViews.add(golf);
        checkedTextViews.add(sea);
        checkedTextViews.add(leisure);
        checkedTextViews.add(check_near_air);
        checkedTextViews.add(check_near_downtw);
        checkedTextViews.add(check_near_tour);
        checkedTextViews.add(check_near_sea);


        makeCheck();


        /*s = (TextView) findViewById(R.id.est_s);
        s_image = (ImageView) findViewById(R.id.est_s_img);
        b = (TextView) findViewById(R.id.est_b);
        b_image = (ImageView) findViewById(R.id.est_b_img);*/
        plan = (TextView) findViewById(R.id.est_plan);
        arrvePlan = (TextView) findViewById(R.id.est_plan_arrve);

        asycnTask=new EstimatePayCountAsycnTask(this);
        asycnTask.adultFlag=true;
        asycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void estimateCheck(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.check_luxury:
                if (check_luxury.isChecked()) {
                    check_luxury.setChecked(false);
                } else {
                    check_luxury.setChecked(true);
                    check_cheap.setChecked(false);
                }
                break;
            case R.id.check_cheap:
                if (check_cheap.isChecked()) {
                    check_cheap.setChecked(false);
                } else {
                    check_cheap.setChecked(true);
                    check_luxury.setChecked(false);
                }
                break;
            case R.id.check_request:
                if (cityName == null) {
                    startActivity(new Intent(this, EstimatePopupActivity.class));
                    asycnTask.adultFlag=false;
                    this.finish();
                } else {
                    Log.d(TAG, cityName + "은?");
                    CompanyLsitAsycnTask companyLsitAsycnTask = new CompanyLsitAsycnTask(this, CHCOMPNAY);
                    Log.d(TAG, companyLsitAsycnTask + "은?");
                    companyLsitAsycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://192.168.219.100:7777/device/compList?city_name=", "GET", "테스트 지역1");
                    Log.d(TAG, "excute실행");
                    asycnTask.adultFlag=false;
                    this.finish();

                }

        }
    }

    public void estimateBt(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.est_dest:
                Intent intent2 = new Intent(this, EstimateCountryListActivity.class);
                startActivity(intent2);
                break;
           /* case R.id.est_b:
                b.setTextColor(Color.parseColor("#769dff"));
                s.setTextColor(Color.parseColor("#FFBFBFBF"));
                s_image.setImageResource(0);
                b_image.setImageResource(R.drawable.check);
                depAriplane = b.getText().toString();
                break;
            case R.id.est_s:
                s.setTextColor(Color.parseColor("#769dff"));
                b.setTextColor(Color.parseColor("#FFBFBFBF"));
                b_image.setImageResource(0);
                s_image.setImageResource(R.drawable.check);
                depAriplane = s.getText().toString();
                break;*/
            case R.id.est_plan:
                Intent intent = new Intent(this, CalendarActivity.class);
                count = 0;
                intent.putExtra("count", "" + count + "");
                this.startActivity(intent);
                break;
            case R.id.est_plan_arrve:
                count = 1;
                startActivity(new Intent(this, CalendarActivity.class).putExtra("count", "" + count + ""));
                break;
            case R.id.est_depature:
                //startActivity(new Intent(this, EstimateDeparture.class));
                PopupMenu p = new PopupMenu(
                        getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.departure, p.getMenu());
                p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        apText.setText(item.getTitle().toString());
                        return false;
                    }
                });
                p.show();
                break;
        }
    }
}
