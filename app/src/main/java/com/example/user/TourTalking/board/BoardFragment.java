package com.example.user.TourTalking.board;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.Comment;
import com.example.user.TourTalking.domain.CompanyBoard;
import com.example.user.TourTalking.domain.TrvBoard;
import com.example.user.TourTalking.main.NoticeItem;
import com.example.user.TourTalking.sharing.Fragments;
import com.example.user.TourTalking.sharing.ImageAsycnTask;
import com.example.user.TourTalking.sharing.MainActivity;

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
    TextView boardList;
    Button commentBt;
    private EditText board_comment;

    public BoardFragment() {
        TAG = getClass().getSimpleName();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        boardActivity = (BoardActivity) getActivity();
        Log.d(TAG, boardActivity + "레퍼런스는?");
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
        imageAsycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, vo.getImage_url());
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
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.board_thumbnail_layout);
        BoardTrvImagePageAdapter adapter = null;
        boardList = (TextView) view.findViewById(R.id.req_boarList);
        TrvBoard vo = (TrvBoard) dto;
        title.setText(vo.getTrv_board_title());
        name.setText(vo.getCompany_name());
        regdate.setText(vo.getTrv_board_regdate());
        hit.setText(Integer.toString(vo.getTrv_board_hit()));
        TextView textView = (TextView) view.findViewById(R.id.trv_board_content);


        boardList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), BoardListActivity.class));
            }
        });

        for (int i = 0; i < vo.getTrvBoardContent().size(); i++) {
            textView.append(vo.getTrvBoardContent().get(i).getBoard_content());
            textView.append("\n");
        }
        //linearLayout.addView(textView);

        for (int i = 0; i < vo.getTrvImageUrl().size(); i++) {
            final int count = i;
            BoardImageFragment fragment = new BoardImageFragment();

            ImageView thumbImageView = new ImageView(getActivity());
            thumbImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            thumbImageView.setPadding(0, 0, 5, 0);
            thumbImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(count);
                }
            });


            Log.d(TAG, vo.getTrvImageUrl().get(i).getTrv_image_content() + "나와랏");
            new ImageAsycnTask(thumbImageView, 250).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, vo.getTrvImageUrl().get(i).getImage_url());
            Bundle bundle = new Bundle();
            bundle.putString("trvImageUrl", vo.getTrvImageUrl().get(i).getImage_url());
            bundle.putString("trvImageText", vo.getTrvImageUrl().get(i).getTrv_image_content());
            fragment.setArguments(bundle);
            fragments.add(fragment);
            layout.addView(thumbImageView);
        }
        adapter = new BoardTrvImagePageAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        setComment();


        ArrayList<TrvBoard> arrayList = (ArrayList<TrvBoard>) MainActivity.mainActivity.initAsycnTask.getAnnList().get("trevelItem");
        initCommentBoardList(arrayList);

    }

    public void setComment() {
        board_comment = (EditText) view.findViewById(R.id.board_comment);
        final ArrayList<Comment> comments = new ArrayList<>();
        final ListView commentList = (ListView) view.findViewById(R.id.board_comment_list);
        final BoardCommentAdapter boardCommentAdapter = new BoardCommentAdapter(boardActivity, comments, 333);
       /* for (int i = 0; i < 4; i++) {
            Comment comment = new Comment();
            comment.setCommnet("이것은 댓글");
            boardCommentAdapter.list.add(comment);
        }*/
        commentList.setAdapter(boardCommentAdapter);
        setListViewHeightBasedOnChildren(commentList);

        commentBt = (Button) view.findViewById(R.id.board_comment_bt);
        commentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 클릭시 insert Comment
                Comment comment = new Comment();
                comment.setCommnet(board_comment.getText().toString());
                board_comment.setText("");
                boardCommentAdapter.list.add(comment);
                boardActivity.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setListViewHeightBasedOnChildren(commentList);
                        boardCommentAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public void initCommentBoardList(ArrayList<TrvBoard> list) {
        ListView commentBoardList = (ListView) view.findViewById(R.id.board_listView);
        BoardCommentListViewAdapter boardCommentListViewAdapter = new BoardCommentListViewAdapter(boardActivity, list);
        commentBoardList.setAdapter(boardCommentListViewAdapter);
        setListViewHeightBasedOnChildren(commentBoardList);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}
