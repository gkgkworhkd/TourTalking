package com.example.user.TourTalking.board;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-22.
 */

public class BoardTrvImagePageAdapter extends FragmentStatePagerAdapter {
    ArrayList<BoardImageFragment> fragments;
    public BoardTrvImagePageAdapter(FragmentManager fm,ArrayList<BoardImageFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
