package com.example.user.TourTalking.board;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.user.TourTalking.domain.TrvBoard;
import com.example.user.TourTalking.sharing.MainActivity;

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
import java.util.List;

/**
 * Created by user on 2017-03-14.
 */

public class BoardListAsycnTask extends AsyncTask<String, Void, String> {
    BoardListActivity activity;
    //For Conntection
    URL url;
    HttpURLConnection con;
    String TAG;
    BoardActivity boardActivity;
    ArrayList<TrvBoard> trvList = new ArrayList<TrvBoard>();

    public BoardListAsycnTask(Context c) {
        TAG = this.getClass().getSimpleName();
        activity = (BoardListActivity) c;
    }

    public BoardListAsycnTask(BoardActivity boardActivity) {
        TAG = this.getClass().getSimpleName();
        this.boardActivity = boardActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuffer sb = null;
        BufferedReader buffr = null;
        try {
            url = new URL(params[0]);
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
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                List list = null;


                //Log.d(TAG,obj.getString("result"));
                // Log.d(TAG,obj+"생성되었다");
                if (obj.getString("result").equals("success")) {
                    if (obj.getString("dataType").equals("trevelItem")) {
                        list = new ArrayList<TrvBoard>();
                        JSONArray dataArray = new JSONArray(obj.getString("data"));
                        Log.d(TAG, dataArray.length() + "길이는?");
                        for (int a = 0; a < dataArray.length(); a++) {
                            JSONObject data = dataArray.getJSONObject(a);
                            //Log.d(TAG, "데이터는?" + data);
                            Log.d(TAG, data.getString("trv_board_id"));
                            TrvBoard dto = new TrvBoard();
                            dto.setTrv_board_id(data.getInt("trv_board_id"));
                            dto.setCity_name(data.getString("company_name"));
                            dto.setCompany_name(data.getString("city_name"));
                            dto.setTrv_board_title(data.getString("trv_board_title"));
                            // dto.setTrv_board_content(data.getString("trv_board_content"));
                            dto.setTrv_board_hit(data.getInt("trv_board_hit"));
                            dto.setTrv_board_regdate(data.getString("trv_board_regdate"));
                            dto.setTrvImageUrl(MainActivity.mainActivity.initAsycnTask.getTrvBoarImage(data.getString("trvImageUrl")));
                            Log.d(TAG, data.getString("trvImageUrl"));

                            if (!data.getString("trvBoardContent").equals("[]"))
                                dto.setTrvBoardContent(MainActivity.mainActivity.initAsycnTask.getTrvBoardContent(data.getString("trvBoardContent")));
                            trvList.add(dto);
                        }
                    }

                } else if (obj.getString("result").equals("fail")) {
//TODO 인터넷이 연결되있지않다는 메세지
                }
                //mainActivity.getMyPagerAdapter().myFragment[0].initData.add(annList);
                //mainActivity.getMyPagerAdapter().myFragment[1].initData.add(conutryList);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (activity != null) {
                activity.init();
            }
        }
    }

}
