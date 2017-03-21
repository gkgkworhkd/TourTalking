package com.example.user.TourTalking.sharing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.user.TourTalking.main.NoticeItem;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 2017-02-27.
 */

public class ImageAsycnTask extends AsyncTask<String, Void, Bitmap> {
    NoticeItem item;
    ImageView imageView;
    int userSize=0;

    public ImageAsycnTask(NoticeItem item) {
        this.item = item;
    }

    public ImageAsycnTask(ImageView imageView) {
        this.imageView = imageView;

    }
    public ImageAsycnTask(ImageView imageView,int userSize) {
        this.userSize=userSize;
        this.imageView = imageView;

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return getBitmap(params[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap s) {
        if (item != null) item.itemImage.setImageBitmap(resizeBitmap(s, item.getWidth()));
        else if (imageView != null) {
            if(userSize==0){
                imageView.setImageBitmap(resizeBitmap(s, imageView.getWidth()));
            }else {
                imageView.setImageBitmap(resizeBitmap(s, userSize));
            }
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    public Bitmap resizeBitmap(Bitmap original, int size) {

        int resizeWidth = size;
        if(resizeWidth==0)resizeWidth=50;
        double aspectRatio = (double) original.getHeight() / (double) original.getWidth();
        int targetHeight = (int) (resizeWidth * aspectRatio);
        Bitmap result = Bitmap.createScaledBitmap(original, resizeWidth, targetHeight, false);
        if (result != original) {
            original.recycle();
        }
        return result;
    }

    private Bitmap getBitmap(String url) {
        URL imgUrl = null;
        HttpURLConnection connection = null;
        InputStream is = null;
        Bitmap retBitmap = null;
        try {
            imgUrl = new URL(url);
            connection = (HttpURLConnection) imgUrl.openConnection();
            connection.setDoInput(true); //url로 input받는 flag 허용
            connection.connect(); //연결
            is = connection.getInputStream(); // get inputstream
            retBitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return retBitmap;
        }
    }
}
