package com.example.user.TourTalking.login;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by user on 2017-04-06.
 */

public class DataCheckAsycnTask extends AsyncTask<String, Void, String> {
    private String id;
    private String pwd;
    URL url;
    HttpURLConnection con;
    String TAG;

    public DataCheckAsycnTask(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuffer sb = null;
        BufferedReader buffr = null;
        BufferedWriter buffw = null;
        StringBuffer sbw = new StringBuffer();
        try {
            url = new URL(params[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(params[1]);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            sbw.append("?id=" + id);
            sbw.append("&pwd=" + pwd);
            Properties properties = new Properties();
            properties.put("id", id);
            properties.put("pwd", pwd);
            con.connect();
            buffr = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            buffw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "utf-8"));
            buffw.write(sbw.toString()+"\n");
            //buffw.write(encodeString(properties));
            buffw.flush();

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


        //return sb.toString();
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public static String encodeString(Properties params) {
        StringBuffer sb = new StringBuffer(256);
        Enumeration names = params.propertyNames();

        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            String value = params.getProperty(name);
            sb.append(URLEncoder.encode(name) + "=" + URLEncoder.encode(value));

            if (names.hasMoreElements()) sb.append("&");
        }
        return sb.toString();
    }
}
