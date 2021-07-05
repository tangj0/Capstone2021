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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class RateActivity extends AppCompatActivity implements View.OnClickListener {
    private Count currentCount;
    private int contributionID;

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
        fStore = FirebaseFirestore.getInstance();

        contribution = new Contribution();

    }

    private void uploadDummyContribution(){ //Upload to Firestore
        Date currentTime = Calendar.getInstance().getTime();
//        contribution = new Contribution(Contribution.GarbageType.MASK, 1, currentTime); // don't do this, image paths will stay null
        contribution.setGarbageType(Contribution.GarbageType.MASK);
        contribution.setGarbageAmount(1);
        contribution.setCurrentTime(currentTime);

//        UUID contributionID = UUID.randomUUID();
//        String strContUUID = contributionID.toString();
        contributionID = getCount();
        setCount(contributionID + 1);
        String strContUUID = Integer.toString(contributionID);

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading");
        pd.show();

        DocumentReference documentReference = fStore.collection("contributions").document(playerID).collection("unrated").document(strContUUID);
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

    private int getCount(){
        currentCount = new Count();

        DocumentReference documentReference = fStore.collection("counts").document("contributionsCount");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        if (document.exists()) {
                            Map<String, Object> map = document.getData();
//                            if (map.size() == 0) {
//                                Log.d("EMPTY_DOC", "Document is empty");
//                            } else {
//                                Log.d("NON-EMPTY_DOC", "Document is not empty");
//                                currentCount.setCount(Integer.getInteger(map.toString()));
//                            }
                            Log.d("MAP", String.valueOf(document.getData().get("count")));
                            String val = String.valueOf(document.getData().get("count"));
                            Log.d("VAL", val);
                            currentCount.setCount(Integer.parseInt(val));

                        } else {
                            Log.d("DOESN'T_EXIST", "Document doesn't exist");

                            documentReference.set(currentCount);
                            currentCount.setCount(0);

                        }
                    }
                    else {
                        Log.d("NULL", "Document is null");
                    }
                }
            }
        });

        Log.d("COUNT", String.valueOf(currentCount.getCount()));
        return currentCount.getCount();

    }

    private void setCount(int newID){
        currentCount = new Count();
        currentCount.setCount(newID);
        DocumentReference documentReference = fStore.collection("counts").document("contributionsCount");
        documentReference.set(currentCount);
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
        else if (v.getId() == R.id.btnRetrieveContribution){ //TODO: only temp test
            getCount();
        }
    }
}