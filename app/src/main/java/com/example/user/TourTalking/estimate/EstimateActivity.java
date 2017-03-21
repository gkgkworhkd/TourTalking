package com.example.user.TourTalking.estimate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.calender.CalendarActivity;
import com.example.user.TourTalking.country_list.CompanyLsitAsycnTask;
import com.example.user.TourTalking.sharing.MainActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-14.
 */


public class EstimateActivity extends AppCompatActivity {
    TextView s, b, plan;
    ImageView s_image, b_image;
    private String finshDay;
    private String depAriplane;
    private String TAG;
    String arrvieDay;
    String depDay;
    String startDay;
    String destName;

    boolean flag;
    String cityName;
    public static final int CHCOMPNAY = 11;
    private TextView destTextView;
    private CheckedTextView tour, rest, business, golf, sea, leisure, check_near_air, check_near_downtw, check_near_tour, check_near_sea, check_cheap, check_luxury;
    private ArrayList<CheckedTextView> checkedTextViews;
    public static EstimateActivity estimateActivity;
    private int count = 0;

    private boolean choiceCompany;

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
        cityName = getIntent().getStringExtra("cityName");
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
            count = 1;
            startActivity(new Intent(this, CalendarActivity.class).putExtra("count", "" + count + ""));
        } else if (depDay != null) {
            finshDay = makePlan(depDay);
            plan.append("~" + finshDay);
        }
        if ((destName = intent.getStringExtra("countName")) != null) {
            destTextView.setText(destName);
        }
        if (intent.getBooleanExtra("choiceCompany", true)) {
            choiceCompany = true;
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


        s = (TextView) findViewById(R.id.est_s);
        s_image = (ImageView) findViewById(R.id.est_s_img);
        b = (TextView) findViewById(R.id.est_b);
        b_image = (ImageView) findViewById(R.id.est_b_img);
        plan = (TextView) findViewById(R.id.est_plan);
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
                if (cityName==null) {
                    startActivity(new Intent(this, EstimatePopupActivity.class));

                } else {
                    Log.d(TAG,cityName+"은?");
                    CompanyLsitAsycnTask companyLsitAsycnTask = new CompanyLsitAsycnTask(this, CHCOMPNAY);
                    companyLsitAsycnTask.execute("http://192.168.219.101:7777/device/compList?city_name=", "GET", cityName);
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
            case R.id.est_b:
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
                break;
            case R.id.est_plan:
                Intent intent = new Intent(this, CalendarActivity.class);
                intent.putExtra("count", "" + count + "");
                this.startActivity(intent);
                break;

        }
    }
}
