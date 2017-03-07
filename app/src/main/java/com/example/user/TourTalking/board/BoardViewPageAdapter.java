package com.example.user.TourTalking.board;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by user on 2017-02-27.
 */

public class BoardViewPageAdapter extends FragmentStatePagerAdapter{
    BoardFragment fragment;
    public BoardViewPageAdapter(FragmentManager fm) {
        super(fm);
        fragment=new BoardFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment;
    }

}
