package com.example.user.TourTalking.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.user.TourTalking.List.FriendLsitAsycnTask;
import com.example.user.TourTalking.List.ListActivity;
import com.example.user.TourTalking.R;
import com.example.user.TourTalking.board.BoardListActivity;
import com.example.user.TourTalking.login.LoginActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends MyAppCompatActivity {
    private String TAG;
    private Toolbar toolbar;
    private MyPagerAdapter myPagerAdapter;
    public ViewPager viewPager;
    static public MainActivity mainActivity;
    private BackPressCloseHandler backPressCloseHandler;
    public InitAsycnTask initAsycnTask;
    boolean isRunIntro = true;
    //TODO 임시 MEMBERTYPE&PK
    private String memberType;
    private String member_pk;
    Bundle savedInstanceState;
    public String[] memberInfo = {memberType, member_pk};
    public Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        TAG = getClass().getSimpleName();
        mainActivity = this;
        setContentView(R.layout.intro_activity);
        isRunIntro = getIntent().getBooleanExtra("intro", true);
        if (isRunIntro) {
            beforeIntro();
        } else {
            afterIntro(savedInstanceState);
        }
        //startActivity(new Intent(L));
    }

    public void setMember_pk(String member_pk) {
        this.member_pk = member_pk;
    }
    public void setMemberType(String member_pk){
        this.member_pk=member_pk;
    }
    public String getMember_pk(){
        return member_pk;
    }

    private void beforeIntro() {
        // 약 2초간 인트로 화면을 출력.
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("intro", false);
                startActivity(intent);
                // 액티비티 이동시 페이드인/아웃 효과를 보여준다. 즉, 인트로
                //    화면에 부드럽게 사라진다.
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        }, 2000);
    }

    // 인트로 화면 이후.
    private void afterIntro(Bundle savedInstanceState) {
        // 기본 테마를 지정한다.

        setContentView(R.layout.main_activity);

        initAsycnTask = new InitAsycnTask(MainActivity.mainActivity);
        initAsycnTask.execute("http://192.168.219.100:7777/device/init", "GET");

    }


    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.fragment_containar);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(myPagerAdapter);


        //Log.d(TAG,listView_main+"이 생성되었다.");
        //SetUp ListView
        backPressCloseHandler = new BackPressCloseHandler(this);
    }


    public void MainBt(View view) {
        switch (view.getId()) {
            case R.id.talk_img:
                FriendLsitAsycnTask asycnTask = new FriendLsitAsycnTask(this);
                //TODO 친구목록을 요청하는 서버 작업및 요청
                asycnTask.execute("http://192.168.219.100:7777/device/compList?city_name=", "GET", "테스트 지역1");
                break;
            case R.id.trv_img:
                viewPager.setCurrentItem(1);
                break;
            case R.id.board:
                Intent intent1 = new Intent(this, BoardListActivity.class);
                startActivity(intent1);
                break;
            case R.id.home_toggle:
                viewPager.setCurrentItem(0);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    public MyPagerAdapter getMyPagerAdapter() {
        return myPagerAdapter;
    }
}
