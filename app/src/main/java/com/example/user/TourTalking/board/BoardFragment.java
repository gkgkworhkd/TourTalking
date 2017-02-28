package com.example.user.TourTalking.board;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-02-27.
 */

public class BoardFragment extends Fragment {
    private String TAG;
    private View view;


    public BoardFragment() {
        TAG = getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.board_fragment, container, false);
        setImageAndData();
        return view;
    }

    public void setImageAndData() {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.board_image_container);

        for (int i = 0; i < 3; i++) {
            BoardImageView myImageView=new BoardImageView(getContext());
            //myImageView.setImageBitmap();
            linearLayout.addView(myImageView);
        }

    }
}
