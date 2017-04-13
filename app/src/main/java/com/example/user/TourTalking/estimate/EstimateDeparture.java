package com.example.user.TourTalking.estimate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.Estimate;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-30.
 */

public class EstimateDeparture extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> departureName;
    private String TAG;
    EstimateDeparture activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();

        setContentView(R.layout.estimate_departure_list_activity);
        listView = (ListView) findViewById(R.id.est_depature_list);
        departureName = new ArrayList<String>();
        departureName.add("김포공항");
        departureName.add("인천 국제공항");
        departureName.add("제주 국제공항");
        departureName.add("김해 국제공항");
        departureName.add("청주 국제공항");
        departureName.add("무안 국제공항");
        Log.d(TAG, "엑티비티가 실행됨");
        DepartureAdapter adapter = new DepartureAdapter(this, departureName);
        listView.setAdapter(adapter);
        Log.d(TAG, "리스트가 붙음");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                Intent intent = new Intent(activity, EstimateActivity.class);
                intent.putExtra("apName", textView.getText());
                activity.startActivity(intent);
            }
        });
        activity = this;
    }


    public class DepartureAdapter extends BaseAdapter {
        private ArrayList<String> departureName;
        private EstimateDeparture context;

        public DepartureAdapter(EstimateDeparture context, ArrayList<String> departureName) {
            this.context = context;
            this.departureName = departureName;
        }

        @Override
        public int getCount() {
            return departureName.size();
        }

        @Override
        public Object getItem(int position) {
            return departureName.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            TextView textView = new TextView(context);
            textView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(25);
            textView.setPadding(5, 5, 5, 5);
            textView.setTextColor(Color.parseColor("#9bd6ff"));
            textView.setText(departureName.get(position));
            view = textView;
            return view;
        }
    }
}
