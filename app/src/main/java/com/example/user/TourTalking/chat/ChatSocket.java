package com.example.user.TourTalking.chat;

import android.os.Bundle;
import android.os.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by user on 2017-03-08.
 */

public class ChatSocket extends Thread {
    private BufferedWriter buffw;
    private BufferedReader buffr;
    private ChatActivity activity;

    public ChatSocket(ChatActivity activity, BufferedReader buffr, BufferedWriter buffw) {
        this.buffw = buffw;
        this.buffr = buffr;
        this.activity = activity;
    }

    @Override
    public void run() {
        while (true) {
            listen();
        }
    }

    public void listen() {
        try {
            String msg=buffr.readLine();
            Bundle bundle=new Bundle();
            bundle.putCharSequence("msg",msg);
            Message message=new Message();
            message.setData(bundle);
            activity.handler.handleMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        try {
            buffw.write(msg);
            buffw.write("\n");
            buffw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
