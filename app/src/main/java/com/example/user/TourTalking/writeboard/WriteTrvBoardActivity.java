package com.example.user.TourTalking.writeboard;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-04-05.
 */

public class WriteTrvBoardActivity extends AppCompatActivity {
    WriteTrvBoardActivity writeTrvBoardActivity;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_IMAGE = 2;
    private Uri mImageCaptureUri;
    private ImageView iv_ChosePic1, iv_ChosePic2, iv_ChosePic3, iv_ChosePic4;
    private String TAG;
    private int choPic;
    private TextView trv_image_text1, trv_image_text2, trv_image_text3, trv_image_text4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_trv_board_activity);
        writeTrvBoardActivity = this;
        TAG = this.getClass().getSimpleName();
        iv_ChosePic1 = (ImageView) findViewById(R.id.trv_board_image1);
        iv_ChosePic2 = (ImageView) findViewById(R.id.trv_board_image2);
        iv_ChosePic3 = (ImageView) findViewById(R.id.trv_board_image3);
        iv_ChosePic4 = (ImageView) findViewById(R.id.trv_board_image4);

        trv_image_text1 = (TextView) findViewById(R.id.trv_image_text1);
        trv_image_text2 = (TextView) findViewById(R.id.trv_image_text2);
        trv_image_text3 = (TextView) findViewById(R.id.trv_image_text3);
        trv_image_text4 = (TextView) findViewById(R.id.trv_image_text4);
    }

    public void registTrv(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림").setMessage("새글이 등록되었습니다.");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                writeTrvBoardActivity.finish();
            }
        }).show();

    }

    public void photoBt(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.trv_board_image1:
                choPic = R.id.trv_board_image1;
                break;
            case R.id.trv_board_image2:
                choPic = R.id.trv_board_image2;
                break;
            case R.id.trv_board_image3:
                choPic = R.id.trv_board_image3;
                break;
            case R.id.trv_board_image4:
                choPic = R.id.trv_board_image4;
                break;
            case R.id.trv_image_text1:

                break;
            case R.id.trv_image_text2:

                break;
            case R.id.trv_image_text3:

                break;
            case R.id.trv_image_text4:

                break;
        }
        doTakeAlbumAction();
    }

    public void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "OnActivitResult 응답성공");
        if (resultCode != RESULT_OK) {
            Log.d(TAG, "NULL이나왔어");
            return;
        }
        switch (requestCode) {
            case PICK_FROM_ALBUM:
                Log.d(TAG, "앨범 선택 성공");
                mImageCaptureUri = data.getData();
                Log.d(TAG, mImageCaptureUri + "chaturl");
               /* Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image*//*");
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 200);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_IMAGE);
                break;
            case CROP_FROM_IMAGE:*/
                if (resultCode != RESULT_OK) {
                    return;
                }
                Log.d(TAG, "번들선택 성공");
                final Bundle extras = data.getExtras();
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/smartWheel" + System.currentTimeMillis() + ".jpg";
                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    switch (choPic) {
                        case R.id.trv_board_image1:
                            iv_ChosePic1.setImageBitmap(photo);
                            break;
                        case R.id.trv_board_image2:
                            iv_ChosePic2.setImageBitmap(photo);
                            break;
                        case R.id.trv_board_image3:
                            iv_ChosePic3.setImageBitmap(photo);
                            break;
                        case R.id.trv_board_image4:
                            iv_ChosePic4.setImageBitmap(photo);
                            break;
                    }

                    //TODO 확인을 누르면 여기서 생성된 비트맵을 서버에 올린다

                }
                break;
        }
    }
}
