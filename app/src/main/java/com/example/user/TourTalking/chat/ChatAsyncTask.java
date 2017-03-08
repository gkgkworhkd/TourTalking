package com.example.user.TourTalking.chat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

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
                try {
                    String msg = buffr.readLine();
                    if (msg == "close") flag = false;
                    Bundle bundle = new Bundle();
                    bundle.putCharSequence("msg", msg);
                    Message message = new Message();
                    message.setData(bundle);
                    activity.handler.handleMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void send(final String msg) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    buffw.write(msg);
                    Log.d(TAG, "write 가 실행됨");
                    buffw.write("\n");
                    buffw.flush();
                    Log.d(TAG, "flush 가 실행됨");
                } catch (IOException e) {
                    e.printStackTrace();
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
            while (flag) {
                listen();
            }
            //받은 메세지를 내 화면에 뿌린다.!
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (buffr != null) {
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (buffw != null) {
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return endMsg;
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
