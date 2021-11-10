package com.capstone.hexagon;

import android.app.ProgressDialog;
import android.content.ContentResolver;
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

import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button uploadBefore, uploadAfter, submitContribution;
    private static final int BEFORE_IMAGE_REQUEST_CODE = 1;
    private static final int AFTER_IMAGE_REQUEST_CODE = 2;
    private Bitmap beforeImage, afterImage;
    private ImageView imageViewBeforeImage, imageViewAfterImage;

    private static final int IMAGE_REQUEST = 3;
    private String imageOrder = "";
    private Uri uri;
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

        uploadBefore = (Button)findViewById(R.id.btnUploadBefore);
        uploadBefore.setOnClickListener(this);

        uploadAfter = (Button)findViewById(R.id.btnUploadAfter);
        uploadAfter.setOnClickListener(this);

        submitContribution = (Button)findViewById(R.id.btnSubmitContribution);
        submitContribution.setOnClickListener(this);

        imageViewBeforeImage = (ImageView) findViewById(R.id.image_view_before_image);
        imageViewAfterImage = (ImageView) findViewById(R.id.image_view_after_image);

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
        contribution = new Contribution();
        contribution.setPlayerId(playerId);
    }

    private void openCamera(int requestCode) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent, requestCode);
    }

//TODO

//    @Override
//    public void onClick(View v) {
//        // The Upload Before button is pressed
//        if (v.getId() == R.id.btnUploadBefore) {
//            openCamera(BEFORE_IMAGE_REQUEST_CODE);
//        }
//        // The Upload After button is pressed
//        else if (v.getId() == R.id.btnUploadAfter) {
//            openCamera(AFTER_IMAGE_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == BEFORE_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            beforeImage = (Bitmap) data.getExtras().get("data");
//            imageViewBeforeImage.setImageBitmap(beforeImage);
//        }
//        if (requestCode == AFTER_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            afterImage = (Bitmap) data.getExtras().get("data");
//            imageViewAfterImage.setImageBitmap(afterImage);
//        }
//    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*"); //adding * made it work
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
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
                            }
                            else if (imageOrder.equals("after")){
                                contribution.setAfterImg(downloadUri);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            uri = data.getData();
            uploadImage();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUploadBefore){
            imageOrder = "before";
            openImage();
        }
        else if (v.getId() == R.id.btnUploadAfter){
            imageOrder = "after";
            openImage();
        }
        else if (v.getId() == R.id.btnSubmitContribution){
            submitContribution();
        }
    }
}