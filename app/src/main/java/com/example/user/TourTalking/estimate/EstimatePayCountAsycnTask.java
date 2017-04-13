package com.example.user.TourTalking.estimate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by user on 2017-03-31.
 */

public class EstimatePayCountAsycnTask extends AsyncTask<TextView, Void, String> {
    private EstimateActivity context;
    boolean adultFlag = true;
    int totalPay;
    int takeAway;
    private String TAG;
    int indivi;
    Message message;
    Bundle bundle;

    public EstimatePayCountAsycnTask(EstimateActivity context) {
        this.context = context;
        TAG = this.getClass().getSimpleName();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void adultCountListen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!context.totalPay.getText().toString().equals("")) {
                        totalPay = Integer.parseInt(context.totalPay.getText().toString());
                        if(!context.childCount.getText().toString().equals("") && !context.adultCount.getText().toString().equals("")){
                            takeAway = Integer.parseInt(context.adultCount.getText().toString()) + Integer.parseInt(context.childCount.getText().toString());
                        }else if(!context.childCount.getText().toString().equals("")){
                            takeAway = Integer.parseInt(context.childCount.getText().toString());
                        }else if(!context.adultCount.getText().toString().equals("")){
                            takeAway = Integer.parseInt(context.adultCount.getText().toString());
                        }else {
                            takeAway=0;
                        }


                    } else {
                        totalPay = 0;
                        takeAway = 0;
                    }

                    Thread.sleep(100);
                    if (takeAway != 0 && totalPay != 0) {
                        indivi = totalPay / takeAway;
                        /*message = new Message();
                        bundle = new Bundle();
                        bundle.putString("indiviPay", Integer.toString(indivi));
                        message.setData(bundle);*/
                       // context.handler.handleMessage(message);
                        context.handler.post(new Runnable() {
                            @Override
                            public void run() {
                            context.indiviPay.setText(Integer.toString(indivi));
                            }
                        });

                    }else{
                        context.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                context.indiviPay.setText(Integer.toString(totalPay));
                            }
                        });
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected String doInBackground(TextView... params) {

        while (adultFlag) {
            adultCountListen();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
