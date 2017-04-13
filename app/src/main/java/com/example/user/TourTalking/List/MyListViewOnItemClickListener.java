package com.example.user.TourTalking.List;

        import android.content.Intent;
        import android.view.View;
        import android.widget.AdapterView;

        import com.example.user.TourTalking.R;
        import com.example.user.TourTalking.chat.ChatActivity;
        import com.example.user.TourTalking.domain.Company;
        import com.example.user.TourTalking.profile.ProfileActivity;

/**
 * Created by user on 2017-02-16.
 */

public class MyListViewOnItemClickListener implements AdapterView.OnClickListener {
    int pageId;
    private Company dto;
    ListActivity listActivity;
    Intent intent;
    public MyListViewOnItemClickListener(ListActivity listActivity,int pageId,Company dto) {
        this.dto=dto;
        this.pageId = pageId;
        this.listActivity=listActivity;
    }


    @Override
    public void onClick(View v) {
        if (pageId == R.layout.chatlist_member_fragment) {
            //Toast.makeText(listActivity,position+"번째가 눌렸다",Toast.LENGTH_SHORT).show();
            intent=new Intent(listActivity, ProfileActivity.class);
            intent.putExtra("compData", dto);
            intent.putExtra("country_id", 444);
        } else if (pageId == R.layout.chatlist_list_fragment) {
            //Toast.makeText(listActivity,"chatlist "+position+"번째가 눌렸다",Toast.LENGTH_SHORT).show();
            intent=new Intent(listActivity, ChatActivity.class);
        }
        listActivity.startActivity(intent);
    }
}
