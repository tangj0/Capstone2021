package com.capstone.hexagon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import androidx.viewpager.widget.ViewPager;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//reference: https://stackoverflow.com/questions/51248948/how-to-load-url-image-to-bitmap-convert-it-to-drawable-and-display-it-with-imag/51253405

public class ImageLoadAsyncTask extends AsyncTask<Void, Void, Bitmap[]> {

    private String[] urls;
    private Adapter adapter;

    private ViewPager viewPager;
    private Bitmap[] bitmaps = new Bitmap[2];

    public ImageLoadAsyncTask(String[] urls, Adapter adapter, ViewPager viewPager) {
        this.urls = urls;
        this.adapter = adapter;

        this.viewPager = viewPager;
    }

    @Override
    protected Bitmap[] doInBackground(Void... params) {
        try {
            for (int i = 0; i< urls.length; i++) {
                URL urlConnection = new URL(urls[i]);
                HttpURLConnection connection = (HttpURLConnection) urlConnection.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                bitmaps[i] = myBitmap;
            }
            return bitmaps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap[] result) {
        super.onPostExecute(result);

        adapter.setImageArray(result);
        viewPager.setAdapter(adapter);
    }
}