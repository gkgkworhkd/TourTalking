package com.example.user.TourTalking.sharing;

import android.os.AsyncTask;
import android.util.Log;

import com.example.user.TourTalking.domain.City;
import com.example.user.TourTalking.domain.CompanyBoard;
import com.example.user.TourTalking.domain.Continent;
import com.example.user.TourTalking.domain.Country;
import com.example.user.TourTalking.domain.TrvBoard;
import com.example.user.TourTalking.domain.TrvBoardContent;
import com.example.user.TourTalking.domain.TrvImageUrl;
import com.example.user.TourTalking.estimate.EstimateCountryListActivity;

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
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-02-20.
 */

public class InitAsycnTask extends AsyncTask<String, Void, String> {
    private MainActivity mainActivity;
    private HashMap<String, List> annList = new HashMap<>();
    private HashMap<String, List> conutryList = new HashMap<>();
    private EstimateCountryListActivity estimateCountryListActivity;
    //For Conntection
    URL url;
    HttpURLConnection con;
    String TAG;

    public InitAsycnTask(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        TAG = this.getClass().getSimpleName();
    }
    public InitAsycnTask(EstimateCountryListActivity estimateCountryListActivity) {
        Log.d(TAG,"실행되었다");
        this.estimateCountryListActivity=estimateCountryListActivity;
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

    /*
    * 메인 공고 데이터 2,
    * 대륙및 국가 데이터
    * */
    @Override
    protected void onPostExecute(String s) {

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                List list = null;


                //Log.d(TAG,obj.getString("result"));
                // Log.d(TAG,obj+"생성되었다");
                if (obj.getString("result").equals("success")) {
                    if (obj.getString("dataType").equals("companyAnnounce")) {
                        //Log.d(TAG,obj.getString("data")+"는?");
                        // Log.d(TAG,"실행이된다");
                        JSONArray dataArray = new JSONArray(obj.getString("data"));

                        list = new ArrayList<CompanyBoard>();
                        for (int a = 0; a < dataArray.length(); a++) {
                            JSONObject data = dataArray.getJSONObject(a);

                            CompanyBoard dto = new CompanyBoard();
                            dto.setCompany_board_id(data.getInt("company_board_id"));
                            dto.setCompany_name(data.getString("company_name"));
                            dto.setCompany_board_title(data.getString("company_board_title"));
                            dto.setCompany_board_content(data.getString("company_board_content"));
                            dto.setCompany_board_regdate(data.getString("company_board_regdate"));
                            dto.setCompany_board_hit(data.getInt("company_board_hit"));
                            dto.setImage_url(data.getString("image_url"));
                            Log.d(TAG, data.getString("company_board_title"));
                            list.add(dto);
                        }
                        annList.put("companyAnnounce", list);


                    } else if (obj.getString("dataType").equals("trevelItem")) {
                        list = new ArrayList<TrvBoard>();
                        JSONArray dataArray = new JSONArray(obj.getString("data"));
                        Log.d(TAG, dataArray.length() + "길이는?");
                        for (int a = 0; a < dataArray.length(); a++) {
                            JSONObject data = dataArray.getJSONObject(a);
                            //Log.d(TAG, "데이터는?" + data);
                            Log.d(TAG, data.getString("trv_board_id"));
                            TrvBoard dto = new TrvBoard();
                            dto.setTrv_board_id(data.getInt("trv_board_id"));
                            dto.setCity_name(data.getString("city_name"));
                            dto.setCompany_name(data.getString("company_name"));
                            dto.setTrv_board_title(data.getString("trv_board_title"));
                           // dto.setTrv_board_content(data.getString("trv_board_content"));
                            dto.setTrv_board_hit(data.getInt("trv_board_hit"));
                            dto.setTrv_board_regdate(data.getString("trv_board_regdate"));
                            dto.setTrvImageUrl(getTrvBoarImage(data.getString("trvImageUrl")));
                            Log.d(TAG,data.getString("trvImageUrl"));


                            if(!data.getString("trvBoardContent").equals("[]"))dto.setTrvBoardContent(getTrvBoardContent(data.getString("trvBoardContent")));

                            list.add(dto);
                        }
                        annList.put("trevelItem", list);


                    } else if (obj.getString("dataType").equals("continent")) {
                        JSONArray dataArray = new JSONArray(obj.getString("data"));
                        list = new ArrayList<Continent>();
                        for (int a = 0; a < dataArray.length(); a++) {
                            JSONObject data = dataArray.getJSONObject(a);

                            Continent dto = new Continent();
                            dto.setContinent_id(data.getInt("continent_id"));
                            dto.setContinent_name(data.getString("continent_name"));
                            Log.d(TAG, data.getString("continent_id"));
                            list.add(dto);
                        }
                        conutryList.put("continent", list);
                    } else if (obj.getString("dataType").equals("country")) {
                        JSONArray dataArray = new JSONArray(obj.getString("data"));
                        list = new ArrayList<Country>();
                        for (int a = 0; a < dataArray.length(); a++) {
                            JSONObject data = dataArray.getJSONObject(a);
                            Country dto = new Country();
                            dto.setCountry_id(data.getInt("country_id"));
                            dto.setCountry_name(data.getString("country_name"));
                            dto.setContinent_id(data.getInt("continent_id"));
                            dto.setCity(getCityName(data.getString("city")));
                            dto.setCountry_image(data.getString("country_image"));
                            Log.d(TAG, data.getString("country_name"));
                            list.add(dto);
                        }
                        conutryList.put("country", list);
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
            if(mainActivity!=null){
                mainActivity.init();
            }else if(estimateCountryListActivity!=null){
                estimateCountryListActivity.init();
            }

        }

    }

    public HashMap<String, List> getAnnList() {
        return annList;
    }

    public HashMap<String, List> getConutryList() {
        return conutryList;
    }

    private List<City> getCityName(String json) {
        List<City> citie = new ArrayList<City>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                City city = new City();
                JSONObject obj = (JSONObject) array.get(i);
                city.setCity_name(obj.getString("city_name"));
                city.setCity_id(obj.getInt("city_id"));
                city.setCountry_name(obj.getString("country_name"));
                citie.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return citie;
    }

    public List<TrvImageUrl> getTrvBoarImage(String json) {
        List<TrvImageUrl> trvImageUrls = new ArrayList<TrvImageUrl>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                TrvImageUrl trvImageUrl = new TrvImageUrl();
                JSONObject obj = (JSONObject) array.get(i);
                trvImageUrl.setImage_url(obj.getString("image_url"));
                trvImageUrl.setTrv_image_id(obj.getInt("trv_image_id"));
                trvImageUrl.setTrv_image_content(obj.getString("trv_image_content"));
                trvImageUrls.add(trvImageUrl);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return trvImageUrls;
    }
    public List<TrvBoardContent> getTrvBoardContent(String json) {
        List<TrvBoardContent> trvBoardContents = new ArrayList<TrvBoardContent>();
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    TrvBoardContent trvBoardContent = new TrvBoardContent();
                    JSONObject obj = (JSONObject) array.get(i);
                    Log.d(TAG,"받아온 아이디는 : "+obj.getInt("trv_board_id"));
                    trvBoardContent.setTrv_board_id(obj.getInt("trv_board_id"));
                    trvBoardContent.setBoard_content(obj.getString("board_content"));
                    trvBoardContent.setBoard_content_id(obj.getInt("board_content_id"));
                    trvBoardContents.add(trvBoardContent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        return trvBoardContents;
    }


}
