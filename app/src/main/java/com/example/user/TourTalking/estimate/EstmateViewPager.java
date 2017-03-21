package com.example.user.TourTalking.estimate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.sharing.Fragments;

/**
 * Created by user on 2017-03-20.
 */

public class EstmateViewPager extends FragmentStatePagerAdapter {
    Fragments fragment_est;
    public Fragments[] myFragment = {fragment_est};
    public static int[] FRGID = {R.layout.estimate_complist_fragment};

    public EstmateViewPager(FragmentManager manager) {
        super(manager);
        for (int i = 0; i < FRGID.length; i++) {
            createFragmnet(FRGID[i], i);
        }

    }

    public void createFragmnet(int id, int count) {
        Bundle bundle = new Bundle();
        bundle.putInt("pageId", id);
        myFragment[count] = new Fragments();
        myFragment[count].setArguments(bundle);
    }

    @Override
    public int getCount() {
        return myFragment.length;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragment[position];
    }
}
