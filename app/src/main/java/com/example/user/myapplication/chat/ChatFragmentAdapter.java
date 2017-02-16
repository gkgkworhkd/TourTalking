package com.example.user.myapplication.chat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017-02-14.
 */

public class ChatFragmentAdapter extends FragmentStatePagerAdapter{
    private String TAG;
    Fragment[] myFragment = new Fragment[2];
    public static int[] CHFRGID = {R.layout.chat_member_fragment,R.layout.chat_list_fragment};

    public ChatFragmentAdapter(FragmentManager fm) {
        super(fm);
        TAG=this.getClass().getSimpleName();
        for (int i = 0; i < CHFRGID .length; i++) {
            createFragmnet(CHFRGID [i], i);
        }
    }

    public void createFragmnet(int id, int count) {

        Bundle bundle = new Bundle();
        bundle.putInt("c_pageId", id);
        myFragment[count] = new ListFragments();
        Log.d(TAG,"chat의 createFragment 가 작동되었고 Fragment의 주솟값은 : "+myFragment[count]);
        myFragment[count].setArguments(bundle);
    }

    @Override
    public int getCount() {
        return CHFRGID .length;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragment[position];
    }
}
