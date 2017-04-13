package com.example.user.TourTalking.board;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.Comment;
import com.example.user.TourTalking.domain.TrvBoard;
import com.example.user.TourTalking.sharing.Fragments;
import com.example.user.TourTalking.sharing.ImageAsycnTask;

/**
 * Created by user on 2017-04-05.
 */

public class BoardCommentItem extends LinearLayout {
    private TextView commentText;
    private View view;
    private Parcelable dto;
    private int code;
    private TextView itemName;
    private TextView itemTitle;
    public ImageView itemImage;
    private String TAG;
    public BoardCommentItem(Context context, Parcelable dto, int code) {
        super(context);
        this.dto = dto;
        this.code = code;
        TAG=this.getClass().getSimpleName();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (code == Fragments.TRV_ITEM) {

            view = inflater.inflate(R.layout.main_notice_sep_item, this, true);
            init();
            TrvBoard trvBoard = (TrvBoard) dto;
            itemName.setText(trvBoard.getCompany_name());
            itemTitle.setText(trvBoard.getTrv_board_title());
            Log.d(TAG,trvBoard.getCompany_name()+"을 받아왔다");
            if (trvBoard.getTrvImageUrl().size() != 0) {
                ImageAsycnTask asycnTask = new ImageAsycnTask(itemImage);
                asycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, trvBoard.getTrvImageUrl().get(0).getImage_url());
            }
        } else {

            Comment comment = (Comment) dto;
            view = inflater.inflate(R.layout.board_comment, this, true);
            commentText = (TextView) view.findViewById(R.id.board_comment_text);
            commentText.setText(comment.getCommnet());
        }
    }

    public void init() {
        itemName = (TextView) view.findViewById(R.id.content);
        itemTitle = (TextView) view.findViewById(R.id.title);
        itemImage = (ImageView) view.findViewById(R.id.company_img);
    }
}
