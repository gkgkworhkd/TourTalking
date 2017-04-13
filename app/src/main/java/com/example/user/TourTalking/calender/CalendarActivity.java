package com.example.user.TourTalking.calender;

/**
 * Created by user on 2017-03-16.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.TourTalking.estimate.EstimateActivity;
import com.example.user.TourTalking.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CalendarActivity extends AppCompatActivity {
    /**
     * 연/월 텍스트뷰
     */
    private TextView tvDate;
    /**
     * 그리드뷰 어댑터
     */
    private GridAdapter gridAdapter;
    /**
     * 일 저장 할 리스트
     */
    private ArrayList<String> dayList;
    /**
     * 그리드뷰
     */

    private GridView gridView;
    /**
     * 캘린더 변수
     */
    String dTime;
    SimpleDateFormat formatter;
    private Calendar mCal;
    private String TAG;
    private String selectDay;
    public String choiesDay;
    Intent intent, intent2;
    private TextView planTitle;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        tvDate = (TextView) findViewById(R.id.tv_date);
        gridView = (GridView) findViewById(R.id.gridview);
        planTitle = (TextView) findViewById(R.id.est_plan_title);


        TAG = this.getClass().getSimpleName();
        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        init(date);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        planTitle.setText("도착일을 선택해 주세요");
        if (getIntent().getStringExtra("count").equals("0")) planTitle.setText("출발일을 선택해 주세요");
    }


    public void calBt(View view) {
        int id = view.getId();
        if (id == R.id.calender_before) {
            Date date = null;
            try {
                date = formatter.parse(getBeforeYearMonthByYM(dTime, 2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            init(date);
        } else if (id == R.id.calender_next) {
            Date date = null;
            try {
                date = formatter.parse(getNextYearMonthByYM(dTime, 0));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            init(date);

        } else if (id == R.id.calender_req) {
            if (choiesDay != null) {
                selectDay = selectDay + choiesDay;
                if (getIntent().getStringExtra("count").equals("0")) {
                    intent = new Intent(this, EstimateActivity.estimateActivity.getClass());
                    intent.putExtra("arriveDay", selectDay);
                    startActivity(intent);
                } else {
                    intent2 = new Intent(this, EstimateActivity.estimateActivity.getClass());
                    intent2.putExtra("depDay", selectDay);
                    startActivity(intent2);
                }
            } else {
                Toast.makeText(this, "날자를 선택해 주세요", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void init(Date date) {


        formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        dTime = formatter.format(date);
        Log.d(TAG, "오늘 날자는 : " + dTime);
        try {
            formatter.parse(getBeforeYearMonthByYM(dTime, 1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //date = new Date(getBeforeYearMonthByYM(dTime, 1));
        // Log.d(TAG, "전달 날자 : " + );

        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);
        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));
        selectDay = curYearFormat.format(date) + curMonthFormat.format(date);
        //gridview 요일 표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");
        mCal = Calendar.getInstance();
        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);
        gridAdapter = new GridAdapter(this, dayList);
        gridView.setAdapter(gridAdapter);
    }


    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    private void setCalendarDate(int month) {

        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
        }
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Calendar Page")
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }


    /**
     * 그리드뷰 어댑터
     */
    private class GridAdapter extends BaseAdapter {
        private final List<String> list;
        private final LayoutInflater inflater;
        private CalendarActivity context;
        private ArrayList<View> views;

        /**
         * 생성자
         *
         * @param context
         * @param list
         */

        public GridAdapter(Context context, List<String> list) {
            this.context = (CalendarActivity) context;
            this.list = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            views = new ArrayList<View>();

        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.calendar_item_gridview, parent, false);
                holder = new ViewHolder();
                holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvItemGridView.setText("" + getItem(position));
            final String selectDay = holder.tvItemGridView.getText().toString();
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < views.size(); i++) {
                        views.get(i).setBackgroundColor(Color.WHITE);

                    }
                    if (selectDay.length() == 1) {
                        context.choiesDay = "0" + selectDay;
                    } else {
                        context.choiesDay = selectDay;
                    }

                    v.setBackgroundColor(Color.parseColor("#dae8ff"));

                }
            });
            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) {
                holder.tvItemGridView.setTextColor(Color.BLACK);
            }
            if (position == 7 || position == 14 || position == 21 || position == 28|| position == 35|| position == 42) {
                holder.tvItemGridView.setTextColor(Color.BLUE);
            }
            if (position == 6 || position == 13 || position == 20 || position == 27|| position == 34|| position == 41) {
                holder.tvItemGridView.setTextColor(Color.RED);
            }

            views.add(convertView);
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvItemGridView;
    }


    public String getBeforeYearMonthByYM(String yearMonth, int minVal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(4, 6));
        int day = Integer.parseInt(yearMonth.substring(6, 8));
        cal.set(year, month - minVal, day);
        String beforeYear = dateFormat.format(cal.getTime()).substring(0, 4);
        String beforeMonth = dateFormat.format(cal.getTime()).substring(4, 6);
        String beforeDay = dateFormat.format(cal.getTime()).substring(6, 8);
        String retStr = beforeYear + beforeMonth + beforeDay;

        return retStr;
    }

    public String getNextYearMonthByYM(String yearMonth, int minVal) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(4, 6));
        int day = Integer.parseInt(yearMonth.substring(6, 8));
        cal.set(year, month + minVal, day);
        String nextYear = dateFormat.format(cal.getTime()).substring(0, 4);
        String nextMonth = dateFormat.format(cal.getTime()).substring(4, 6);
        String nextDay = dateFormat.format(cal.getTime()).substring(6, 8);
        String retStr = nextYear + nextMonth + nextDay;

        Log.d(TAG, "retStr : " + retStr);
        return retStr;
    }

}
