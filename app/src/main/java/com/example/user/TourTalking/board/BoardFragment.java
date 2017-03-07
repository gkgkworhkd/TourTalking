package com.example.user.TourTalking.board;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.mian.CompanyBoard;
import com.example.user.TourTalking.domain.mian.TrvBoard;
import com.example.user.TourTalking.main.NoticeItem;
import com.example.user.TourTalking.sharing.ImageAsycnTask;

/**
 * Created by user on 2017-02-27.
 */

public class BoardFragment extends Fragment {
    private String TAG;
    private View view;
    Parcelable dto;
    private LinearLayout linearLayout;
    String type;
    TextView compName;
    TextView cityNam;
    TextView boardTitle;
    TextView trvPlan;

    public BoardFragment() {
        TAG = getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.board_fragment, container, false);
        if (linearLayout == null)
            linearLayout = (LinearLayout) view.findViewById(R.id.board_image_container);
        linearLayout.removeAllViews();
        init();
        Log.d(TAG, "TYPE = " + type);
        if (type.equals(NoticeItem.TRVBOARD)) setTrvBoard();
        if (type.equals(NoticeItem.COMPBOARD)) setCompBoard();

        return view;
    }

    public void init() {
        compName = (TextView) view.findViewById(R.id.board_company_name);
        cityNam = (TextView) view.findViewById(R.id.board_city_name);
        boardTitle = (TextView) view.findViewById(R.id.board_title);
        trvPlan = (TextView) view.findViewById(R.id.board_plane);
    }


    public void setCompBoard() {
        ImageView imageView = new ImageView(getContext());
        CompanyBoard vo = (CompanyBoard) dto;
        ImageAsycnTask imageAsycnTask = new ImageAsycnTask(imageView);
        imageAsycnTask.execute(vo.getImage_url());
        linearLayout.addView(imageView);
        compName.setText(vo.getCompany_name());
        cityNam.setText("");
        boardTitle.setText(vo.getCompany_board_title());
        trvPlan.setText("");
    }

    public void setTrvBoard() {

        TrvBoard vo = (TrvBoard) dto;
        for (int i = 0; i < vo.getTrvImageUrl().size(); i++) {
            ImageView imageView = new ImageView(getContext());
            ImageAsycnTask imageAsycnTask = new ImageAsycnTask(imageView);
            imageAsycnTask.execute(vo.getTrvImageUrl().get(i).getImage_url());
            linearLayout.addView(imageView);
        }
        Log.d(TAG, "내용의 줄 수는 : " + vo.getTrvBoardContent().size());
        for (int i = 0; i < vo.getTrvBoardContent().size(); i++) {
            TextView textView = new TextView(getContext());
            Log.d(TAG, "내용은? : " + vo.getTrvBoardContent().get(i).getBoard_content());
            textView.append(vo.getTrvBoardContent().get(i).getBoard_content());
            //textView.append("\n");
            linearLayout.addView(textView);
        }
        compName.setText(vo.getCompany_name());
        cityNam.setText(vo.getCity_name());
        boardTitle.setText(vo.getTrv_board_title());
        trvPlan.setText(vo.getTrv_board_regdate());
    }
}
