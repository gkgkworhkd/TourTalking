package com.example.user.TourTalking.sharing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-02-13.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    Fragments fragment_main, fragment_info;
    public Fragments[] myFragment = {fragment_main, fragment_info};
    public static int[] FRGID = {R.layout.main_fragment, R.layout.country_list_fragment};

    public MyPagerAdapter(FragmentManager manager) {
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
