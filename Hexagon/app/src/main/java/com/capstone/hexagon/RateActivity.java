package com.capstone.hexagon;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.UUID;

public class RateActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUploadBefore;
    private Button btnUploadAfter;
    private Button btnUploadContribution;
    private Button btnGetContribution;

    private FirebaseAuth auth;
    private StorageReference storageReference;
    private FirebaseFirestore fStore;

    private Uri uri;
    private String playerID;

    private static final int IMAGE_REQUEST = 2;

    private String imageOrder = "";

    private Contribution contribution; //dummy Contribution to test Rating, TODO: remove later

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        ViewPager viewPager = findViewById(R.id.view_pager);

        Adapter adapter = new Adapter(this);
        viewPager.setAdapter(adapter);

        Spinner rateOptions = findViewById(R.id.spinnerRateOptions);
        String[] options = new String[]{"Approve", "Reject"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        rateOptions.setAdapter(arrayAdapter);

        btnUploadBefore = (Button) findViewById(R.id.btnBeforeImg);
        btnUploadBefore.setOnClickListener(this);
        btnUploadAfter = (Button) findViewById(R.id.btnAfterImg);
        btnUploadAfter.setOnClickListener(this);
        btnUploadContribution = (Button) findViewById(R.id.btnUploadContribution);
        btnUploadContribution.setOnClickListener(this);
        btnGetContribution = (Button) findViewById(R.id.btnRetrieveContribution);
        btnGetContribution.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        FirebaseUser player = auth.getCurrentUser();
        playerID = player.getUid();

        storageReference = FirebaseStorage.getInstance().getReference();

        contribution = new Contribution();
    }

    private void uploadDummyContribution(){ //Upload to Firestore
        contribution.setGarbageType(Contribution.GarbageType.MASK);
        contribution.setGarbageAmount(1);
        contribution.setOverallApproval(false);
        Date date = new Date(2021, 3, 17); //TODO: fix Date, use DateTime I Think...
        contribution.setDate(date);
//        contribution.setOverallApproval(false); //don't have to set this yet

        UUID contributionID = UUID.randomUUID();
        String strContUUID = contributionID.toString();

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("contributions").document(playerID).collection("unrated").document(strContUUID);
        documentReference.set(contribution).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RateActivity.this, "Contribution uploaded successfully!", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
                else {
                    Toast.makeText(RateActivity.this, "Contribution upload was unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //TODO
    //Retrieve from Firestore, get from the "unrated" collection
    //then "move" the document to the "rated" section
    private void getDummyContribution() {
        //https://stackoverflow.com/questions/47244403/how-to-move-a-document-in-cloud-firestore
        //TODO: change Adapter to take images from Firebase
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*"); //adding * made it work
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            uri = data.getData();
            uploadImage();
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
            StorageReference imageStorageRef = storageReference.child("players/" + playerID + "/images/" + imageOrder + "/" + strUUID + "." + getFileExtension(uri));
            imageStorageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(RateActivity.this, "Uploaded image successfully!", Toast.LENGTH_LONG).show();

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
                    Toast.makeText(RateActivity.this, "Uploaded image unsuccessful.", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Toast.makeText(RateActivity.this, "UIR null", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnBeforeImg){
            imageOrder = "before";
            openImage();
        }
        else if (v.getId() == R.id.btnAfterImg){
            imageOrder = "after";
            openImage();
        }
        else if (v.getId() == R.id.btnUploadContribution){
            uploadDummyContribution();
        }
    }
}