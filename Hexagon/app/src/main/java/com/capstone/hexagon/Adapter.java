package com.capstone.hexagon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Adapter extends PagerAdapter {

    private Context context;
    private Bitmap[] imageArray;
//    private Drawable[] imageArray;
//    private int[] imageArray = new int[] {R.drawable.mask_before, R.drawable.mask_after};

    public void setImageArray(Bitmap[] imageArray) {
        this.imageArray = imageArray;
    }

    Adapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
//        imageView.setImageResource(imageArray[position]);
//        imageView.setImageDrawable(imageArray[position]);
        imageView.setImageBitmap(imageArray[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
