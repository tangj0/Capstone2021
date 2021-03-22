package com.capstone.hexagon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button uploadBefore, uploadAfter;
    private static final int BEFORE_IMAGE_REQUEST_CODE = 1;
    private static final int AFTER_IMAGE_REQUEST_CODE = 2;
    private Bitmap beforeImage, afterImage;
    private ImageView imageViewBeforeImage, imageViewAfterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadBefore = (Button)findViewById(R.id.btnUploadBefore);
        uploadBefore.setOnClickListener(this);

        uploadAfter = (Button)findViewById(R.id.btnUploadAfter);
        uploadAfter.setOnClickListener(this);

        imageViewBeforeImage = (ImageView) findViewById(R.id.image_view_before_image);
        imageViewAfterImage = (ImageView) findViewById(R.id.image_view_after_image);

        Spinner garbageTypeOptions = findViewById(R.id.GarbageTypeOptions);
        String[] options = new String[]{"Mask", "Plastic bottle"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        garbageTypeOptions.setAdapter(arrayAdapter);
    }

    private void openCamera(int requestCode) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onClick(View v) {
        // The Upload Before button is pressed
        if (v.getId() == R.id.btnUploadBefore) {
            openCamera(BEFORE_IMAGE_REQUEST_CODE);
        }
        // The Upload After button is pressed
        else if (v.getId() == R.id.btnUploadAfter) {
            openCamera(AFTER_IMAGE_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BEFORE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            beforeImage = (Bitmap) data.getExtras().get("data");
            imageViewBeforeImage.setImageBitmap(beforeImage);
        }
        else if (requestCode == AFTER_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            afterImage = (Bitmap) data.getExtras().get("data");
            imageViewAfterImage.setImageBitmap(afterImage);
        }
    }
}