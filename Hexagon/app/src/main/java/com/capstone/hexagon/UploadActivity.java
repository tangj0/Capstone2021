package com.capstone.hexagon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button submitContribution;
    private ImageButton backToMain;
    private static final int CAMERA_REQUEST = 1;
    private Bitmap imageBitmap;
    private String imageOrder = "";
    private Uri uri;
    private ImageView imageViewBeforeImage, imageViewAfterImage;
    private TextView imageTV1, imageTV2;

    private String playerId;
    private FirebaseUser player;
    private FirebaseAuth auth;
    private FirebaseFirestore fStore;
    private StorageReference storageReference;
    private Contribution contribution;
    private Spinner garbageTypeOptions, garbageAmountOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        submitContribution = (Button)findViewById(R.id.btnSubmitContribution);
        submitContribution.setOnClickListener(this);

        backToMain = (ImageButton) findViewById(R.id.back_button);
        backToMain.setOnClickListener(this);
        backToMain.setImageResource(R.drawable.logo);

        imageViewBeforeImage = (ImageView) findViewById(R.id.image_view_before_image);
        imageViewAfterImage = (ImageView) findViewById(R.id.image_view_after_image);
        imageViewBeforeImage.setOnClickListener(this);
        imageViewAfterImage.setOnClickListener(this);

        imageTV1 = (TextView) findViewById(R.id.image_tv1);
        imageTV2 = (TextView) findViewById(R.id.image_tv2);

        garbageTypeOptions = findViewById(R.id.garbageTypeOptions);
        String[] options = new String[]{"Mask", "Plastic bottle"}; // use for readability
//        ArrayAdapter<Contribution.GarbageType> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Contribution.GarbageType.values());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        garbageTypeOptions.setAdapter(arrayAdapter);

        garbageAmountOptions = findViewById(R.id.garbageAmountOptions);
        String[] amounts = new String[]{"1", "2", "3", "4", "5"};
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, amounts);
        garbageAmountOptions.setAdapter(arrayAdapter2);

        auth = FirebaseAuth.getInstance();
        player = auth.getCurrentUser();
        playerId = player.getUid();
        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        if (contribution == null){
            contribution = new Contribution();
            contribution.setPlayerId(playerId);
        }
    }

    private void openCamera() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "PNG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/HexTempPics/");
        File image = null;
        try {
            image = File.createTempFile(imageFileName, ".png", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        final String dir =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+ "/HexagonPics/";
//        File newdir = new File(dir);
//        newdir.mkdirs();
//        String file = dir + DateFormat.format("yyyy-MM-dd_hhmmss", new Date()).toString()+".jpg";
//
//        File newfile = new File(file);
//        try {
//            newfile.createNewFile();
//        } catch (IOException e) {}

//        Uri outputFileUri = Uri.fromFile(newfile);
//        Uri outputFileUri = FileProvider.getUriForFile(
//                UploadActivity.this,
//                "com.capstone.hexagon.provider", //(use your app signature + ".provider" )
//                newfile);

        Uri outputFileUri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                BuildConfig.APPLICATION_ID + ".provider", image);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }


    @Override
    public void onClick(View v) {
        // The Upload Before button is pressed
        if (v.getId() == R.id.image_view_before_image) {
            imageOrder = "before";
            openCamera();
        }
        // The Upload After button is pressed
        else if (v.getId() == R.id.image_view_after_image) {
            imageOrder = "after";
            openCamera();
        }
        else if (v.getId() == R.id.btnSubmitContribution){
            if (contribution.getBeforeImg() != null && contribution.getAfterImg() != null) {
                submitContribution();
            } else {
                Toast.makeText(UploadActivity.this, "Can't submit without Images", Toast.LENGTH_LONG).show();
            }
        }
        else if (v.getId() == R.id.back_button){
            goToMainPage();
        }
    }

    private void goToMainPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST) {
            File f = new File(Environment.getExternalStorageDirectory().toString());
            for (File temp : f.listFiles()) {
                if (temp.getName().equals("my_temp_img.jpg")) {
                    f = temp;
                    break;
                }
            }
            uri = Uri.fromFile(f);
            uploadImage();
//            imageBitmap = (Bitmap) data.getExtras().get("data");
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//            byte[] imageData = baos.toByteArray();
//            uploadImage(imageData);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() { //Upload actual image file to Storage (cloud storage)
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        if (uri != null) {
            UUID imageUUID = UUID.randomUUID();
            String strUUID = imageUUID.toString();
            StorageReference imageStorageRef = storageReference.child("players/" + playerId + "/images/" + imageOrder + "/" + strUUID + "." + getFileExtension(uri));
            imageStorageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(UploadActivity.this, "Uploaded image successfully!", Toast.LENGTH_LONG).show();

                    imageStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String downloadUri = uri.toString();
                            Log.d("DownloadUri: ", downloadUri);

                            // add image link to Storage to Contribution object
                            if (imageOrder.equals("before")){
                                contribution.setBeforeImg(downloadUri);
                                Toast.makeText(UploadActivity.this, "Setting Before Img", Toast.LENGTH_LONG).show();
                            }
                            else if (imageOrder.equals("after")){
                                contribution.setAfterImg(downloadUri);
                                Toast.makeText(UploadActivity.this, "Setting After Img", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UploadActivity.this, "Uploaded image unsuccessful.", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Toast.makeText(UploadActivity.this, "UIR null", Toast.LENGTH_LONG).show();
        }
    }

    private void submitContribution(){ //Upload to Firestore
        contribution.setGarbageType(contribution.getGarbageTypeFromString(garbageTypeOptions.getSelectedItem().toString())); //string to enum
        contribution.setGarbageAmount(Integer.parseInt(garbageAmountOptions.getSelectedItem().toString()));
        contribution.setMaxRatings(20);
        contribution.setNumOfRatings(0);
        contribution.setFinalRating(false);

        UUID contributionId = UUID.randomUUID();
        String strContUUID = contributionId.toString();
        contribution.setId(strContUUID);

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        DocumentReference documentReference = fStore.collection("contributions").document(playerId).collection("unrated").document(strContUUID);

        // Do this so document is not empty, otherwise won't appear in queries or snapshots
        Map<String, String> dummyField = new HashMap<>();
        dummyField.put("Name", player.getEmail());
        fStore.collection("contributions").document(playerId).set(dummyField, SetOptions.merge());

        documentReference.set(contribution).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UploadActivity.this, "Contribution uploaded successfully!", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
                else {
                    Toast.makeText(UploadActivity.this, "Contribution upload was unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}