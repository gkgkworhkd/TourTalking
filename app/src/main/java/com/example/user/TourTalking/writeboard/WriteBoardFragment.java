package com.example.user.TourTalking.writeboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-03-21.
 */

public class WriteBoardFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.write_board_image_fragment,container,false);
        return view;
    }
}
