package com.example.user.TourTalking.List;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.user.TourTalking.company.CompanyActivity;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.estimate.EstimateActivity;
import com.example.user.TourTalking.estimate.EstimateChoiceCompanyActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 2017-03-06.
 */

public class FriendLsitAsycnTask extends AsyncTask<String, Void, String> {
    private Context context;
    private URL url;
    private HttpURLConnection con;
    private String TAG;
    private String cityName;
    public FriendLsitAsycnTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuffer sb = null;
        BufferedReader buffr = null;
        cityName=params[2];
        try {
            url = new URL(params[0]+params[2]);
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

        Intent intent = new Intent(context, ListActivity.class);
        intent.putParcelableArrayListExtra("friendList", companies);
        context.startActivity(intent);

    }
}
