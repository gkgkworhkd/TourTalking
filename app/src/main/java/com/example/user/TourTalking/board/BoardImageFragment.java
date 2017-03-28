package com.example.user.TourTalking.board;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;

/**
 * Created by 고재광 on 2017-03-22.
 */

public class BoardImageFragment extends Fragment {
    View view;
    LinearLayout layout;
    ImageView imageView;
    TextView imageText;
    public BoardImageFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.board_image_fragment, null);
        layout = (LinearLayout) view.findViewById(R.id.board_trv_image_container);
        layout.addView(imageView);
        layout.addView(imageText);
        return view;
    }
}
