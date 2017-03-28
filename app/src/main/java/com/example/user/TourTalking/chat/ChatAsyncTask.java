package com.example.user.TourTalking.chat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by user on 2017-03-07.
 */

public class ChatAsyncTask extends AsyncTask<String, Void, String> {
    private String TAG;
    private Socket socket;
    private ChatActivity activity;
    private BufferedReader buffr;
    private BufferedWriter buffw;
    boolean flag = true;
    String msg;
    Handler handler=new Handler();

    public ChatAsyncTask(ChatActivity chatActivity) {
        activity = chatActivity;
        this.TAG = this.getClass().getSimpleName();
    }

    @Override
    protected void onPreExecute() {

    }

    public void listen() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        String msg = buffr.readLine();
                        if (msg.equals("close")){
                            flag = false;
                            Log.d(TAG,"읽기를 끝내고빠져나옴");
                            buffw.write(msg);
                            buffw.write("\n");
                            buffw.flush();
                            closeConnection();
                            break;
                        }

                        Bundle bundle = new Bundle();
                        bundle.putCharSequence("msg", msg);
                        Message message = new Message();
                        message.setData(bundle);
                        activity.handler.handleMessage(message);
                        activity.handler.post(new Runnable() {
                            @Override
                            public void run() {
                                activity.adapter.notifyDataSetChanged();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }



    public void send() {
        final Thread thread = new Thread() {
            @Override
            public void run() {

                while (flag) {
                    try {

                        if (msg == null) {

                        } else {
                            Log.d(TAG, "write 가 실행됨");
                            Log.d(TAG, msg + " 를 보냄");
                            buffw.write(msg);
                            buffw.write("\n");
                            buffw.flush();
                            Log.d(TAG, "flush 가 실행됨");
                            msg = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

    }


    @Override
    protected String doInBackground(String... params) {
        String endMsg = null;
        try {
            socket = new Socket(params[0], Integer.parseInt(params[1]));
            buffr = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
            buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
            buffw.write(params[2]);
            buffw.write("\n");
            buffw.flush();
            listen();
            send();
            //받은 메세지를 내 화면에 뿌린다.!
        } catch (IOException e) {
            e.printStackTrace();
        }
        return endMsg;
    }

    public void closeConnection() {
        Log.d(TAG, "closeConnection Operation");
        Log.d(TAG, "리슨과 샌드종료");
        if (buffr != null) {
            try {
                Log.d(TAG, "버프가 닫히기전");
                buffr.close();
                Log.d(TAG, "버프가 닫힘");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (buffw != null) {
            try {
                buffr.close();
                Log.d(TAG, "버프가 닫힘");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
                Log.d(TAG, "소켓이 닫힘");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }


}
