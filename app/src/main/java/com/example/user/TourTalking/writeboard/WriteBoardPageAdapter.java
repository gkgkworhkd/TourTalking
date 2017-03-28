package com.example.user.TourTalking.writeboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-21.
 */

public class WriteBoardPageAdapter extends FragmentStatePagerAdapter {


    ArrayList<WriteBoardFragment > writeBoardFragment;
    public WriteBoardPageAdapter(FragmentManager fm) {
        super(fm);
        writeBoardFragment=new ArrayList<WriteBoardFragment>();
        for(int i=0;i<4;i++){
            writeBoardFragment.add(new WriteBoardFragment());
        }
    }

    @Override
    public int getCount() {
        return writeBoardFragment.size();
    }

    @Override
    public Fragment getItem(int position) {
        return writeBoardFragment.get(position);
    }
}
