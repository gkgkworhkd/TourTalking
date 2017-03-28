package com.example.user.TourTalking.board;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.CompanyBoard;
import com.example.user.TourTalking.domain.TrvBoard;
import com.example.user.TourTalking.main.NoticeItem;
import com.example.user.TourTalking.sharing.ImageAsycnTask;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-27.
 */

public class BoardFragment extends Fragment {
    private String TAG;
    private View view;
    Parcelable dto;
    private LinearLayout linearLayout;
    String type;
    BoardActivity boardActivity;
    TextView title;
    TextView name;
    TextView regdate;
    TextView hit;

    public BoardFragment() {
        TAG = getClass().getSimpleName();
        boardActivity = (BoardActivity) getActivity();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Log.d(TAG, "TYPE = " + type);

        if (type.equals(NoticeItem.TRVBOARD)) {
            Log.d(TAG, "여행상품 선택");
            view = inflater.inflate(R.layout.board_fragment, container, false);
            if (linearLayout == null)
                linearLayout = (LinearLayout) view.findViewById(R.id.trv_board_image_container);
            init();

            setTrvBoard();
        } else if (type.equals(NoticeItem.COMPBOARD)) {
            Log.d(TAG, "공지사항 선택");
            view = inflater.inflate(R.layout.board_company_fragment, container, false);
            if (linearLayout == null)
                linearLayout = (LinearLayout) view.findViewById(R.id.board_image_container);
            init();
            setCompBoard();
        }

        return view;
    }

    public void init() {
        title = (TextView) view.findViewById(R.id.company_board_title);
        name = (TextView) view.findViewById(R.id.company_board_compName);
        regdate = (TextView) view.findViewById(R.id.company_board_regdate);
        hit = (TextView) view.findViewById(R.id.company_board_hit);
    }

    public void setCompBoard() {
        ImageView imageView = new ImageView(getContext());
        CompanyBoard vo = (CompanyBoard) dto;

        title.setText(vo.getCompany_board_title());
        name.setText(vo.getCompany_name());
        regdate.setText(vo.getCompany_board_regdate());
        hit.setText(Integer.toString(vo.getCompany_board_hit()));

        ImageAsycnTask imageAsycnTask = new ImageAsycnTask(imageView);
        imageAsycnTask.execute(vo.getImage_url());
        imageView.setPadding(0, 15, 0, 15);
        linearLayout.addView(imageView);
        TextView textView = new TextView(getContext());
        textView.setPadding(5, 10, 5, 0);
        textView.append(vo.getCompany_board_content());
        linearLayout.addView(textView);
    }

    public void setTrvBoard() {
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.board_trv_viewPager);
        ArrayList<BoardImageFragment> fragments = new ArrayList<BoardImageFragment>();
        LinearLayout layout=(LinearLayout) view.findViewById(R.id.board_thumbnail_layout);
        BoardTrvImagePageAdapter adapter = null;
        TrvBoard vo = (TrvBoard) dto;
        title.setText(vo.getTrv_board_title());
        name.setText(vo.getCompany_name());
        regdate.setText(vo.getTrv_board_regdate());
        hit.setText(Integer.toString(vo.getTrv_board_hit()));
        TextView textView=(TextView)view.findViewById(R.id.trv_board_content);

        for (int i = 0; i < vo.getTrvBoardContent().size(); i++) {
            textView.append(vo.getTrvBoardContent().get(i).getBoard_content());
            textView.append("\n");
        }
        //linearLayout.addView(textView);

        for (int i = 0; i < vo.getTrvImageUrl().size(); i++) {
            final int count=i;
            BoardImageFragment fragment = new BoardImageFragment();
            ImageView imageView=new ImageView(getActivity());
            ImageView thumbImageView=new ImageView(getActivity());
            thumbImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            thumbImageView.setPadding(15,0,0,0);
            thumbImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(count);
                }
            });

            TextView imageText=new TextView(getActivity());
            imageText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            imageText.setBackgroundColor(Color.RED);
            imageText.setText(vo.getTrvImageUrl().get(i).getTrv_image_content());
            Log.d(TAG,vo.getTrvImageUrl().get(i).getTrv_image_content()+"나와랏");
            new ImageAsycnTask(thumbImageView,350).execute(vo.getTrvImageUrl().get(i).getImage_url());
            ImageAsycnTask imageAsycnTask = new ImageAsycnTask(imageView);
            imageAsycnTask.execute(vo.getTrvImageUrl().get(i).getImage_url());
            fragment.imageText=imageText;
            fragment.imageView=imageView;
            fragments.add(fragment);
            layout.addView(thumbImageView);
        }
        Log.d(TAG,"fragments.size() : "+fragments.size());
        adapter = new BoardTrvImagePageAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);




    }
}
