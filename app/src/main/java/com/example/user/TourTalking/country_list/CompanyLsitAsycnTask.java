package com.example.user.TourTalking.country_list;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.user.TourTalking.company.CompanyActivity;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.estimate.EstimateActivity;
import com.example.user.TourTalking.estimate.EstimateChoiceCompanyActivity;
import com.example.user.TourTalking.estimate.EstimateCountryListActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by user on 2017-03-06.
 */

public class CompanyLsitAsycnTask extends AsyncTask<String, Void, String> {
    private Context context;
    private Intent intent;
    private URL url;
    private HttpURLConnection con;
    private String TAG;
    private String cityName;
    private int type;
    String destUrl;
    int city_id=0;

    public CompanyLsitAsycnTask(Context context, int type) {

        this.context = context;
        this.type = type;
    }
    public CompanyLsitAsycnTask(Context context, int type,int city_id) {
        this.context = context;
        this.type = type;
        this.city_id = city_id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        TAG=this.getClass().getSimpleName();
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuffer sb = null;
        BufferedReader buffr = null;
        Log.d(TAG, "doinBack 실행");
        cityName = params[2];
        try {
            try {
                destUrl = URLEncoder.encode(params[2], "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            url = new URL(params[0] + destUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(params[1]);
            con.setDoInput(true);
            con.connect();
            buffr = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String data = null;
            sb = new StringBuffer();
            while (true) {
                data = buffr.readLine();
                if (data == null) break;
                sb.append(data);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffr != null) try {
                buffr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (con != null) con.disconnect();
        }


        return sb.toString();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ArrayList<Company> companies = new ArrayList<Company>();
        try {
            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Company dto = new Company();
                dto.setCompany_id(obj.getInt("company_id"));
                dto.setCompany_intro(obj.getString("company_intro"));
                dto.setCompany_name(obj.getString("company_name"));
                dto.setImage_url(obj.getString("image_url"));
                companies.add(dto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (type != EstimateActivity.CHCOMPNAY) {
            intent = new Intent(context, CompanyActivity.class);
        } else {
            Log.d(TAG, type + " 이 실행되었다");
            Log.d(TAG, companies.get(2).getCompany_name() + "이 있따");
            intent = new Intent(context, EstimateChoiceCompanyActivity.class);
        }

        intent.putExtra("cityName", cityName);
        intent.putExtra("cityId", city_id);
        intent.putParcelableArrayListExtra("compList", companies);
        context.startActivity(intent);

    }
}
