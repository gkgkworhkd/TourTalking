package com.example.user.TourTalking.board;

import android.os.AsyncTask;
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
import com.example.user.TourTalking.sharing.ImageAsycnTask;

/**
 * Created by 고재광 on 2017-03-22.
 */

public class BoardImageFragment extends Fragment {
    View view;
    LinearLayout layout;
    String imageUrl;
    String imageSbText;
    public BoardImageFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.board_image_fragment, null);
        Bundle bundle=getArguments();
        imageUrl=bundle.getString("trvImageUrl");
        imageSbText=bundle.getString("trvImageText");
        layout = (LinearLayout) view.findViewById(R.id.board_trv_image_container);
        ImageView imageView = new ImageView(getActivity());
        ImageAsycnTask imageAsycnTask = new ImageAsycnTask(imageView, 800);
        imageAsycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,imageUrl);
        TextView imageText = new TextView(getActivity());
        imageText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        imageText.setPadding(5,5,5,0);
        imageText.setText(imageSbText);
        layout.addView(imageView);
        layout.addView(imageText);
        return view;
    }
}
