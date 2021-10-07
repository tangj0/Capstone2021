package com.capstone.hexagon;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RateActivity extends AppCompatActivity implements View.OnClickListener {
    private Button retrieveContribution, submitRating;

    private FirebaseAuth auth;
    private StorageReference storageReference;
    private FirebaseFirestore fStore;

    private Uri uri;
    private String playerID;

    private Contribution contribution;
    private Rating rating;
    private List<Contribution> contributions;

    String TAG = RateActivity.class.getSimpleName();

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

        retrieveContribution = (Button) findViewById(R.id.btnRetrieveContribution);
        retrieveContribution.setOnClickListener(this);
        submitRating = (Button) findViewById(R.id.btnSubmitRating);
        submitRating.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        FirebaseUser player = auth.getCurrentUser();
        playerID = player.getUid();

        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        contribution = new Contribution();
        contributions = new ArrayList<>();
        rating = new Rating();
    }

    //TODO
    //Retrieve from Firestore, get from the "unrated" collection
    //then "move" the document to the "rated" section
    // then change it
    // TODO
    // right now getting a random doc, change to ordering by timestamp
    private void getContribution() {
        //https://stackoverflow.com/questions/47244403/how-to-move-a-document-in-cloud-firestore
        //TODO: change Adapter to take images from Firebase
    }


    private void getContributions(){
//        CollectionReference collectionReference = fStore.collection("contributions").document(playerID).collection("unrated");
        CollectionReference collectionReference = fStore.collection("contributions");


//        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException error) {
//                if (documentSnapshots != null && !documentSnapshots.getDocuments().isEmpty()){
//                    contributions = new ArrayList<>();
//                    List<DocumentSnapshot> documents = documentSnapshots.getDocuments();
//                    for (DocumentSnapshot value : documents) {
//                        Contribution contribution = value.toObject(Contribution.class);
//                        contributions.add(contribution);
//                    }
//                }
//            }
//        });


//        Task<QuerySnapshot> temp = collectionReference.get();
//        List<DocumentSnapshot> documents = temp.getResult().getDocuments();
//        for (DocumentSnapshot document : documents) {
////            DocumentSnapshot.ServerTimestampBehavior behavior = DocumentSnapshot.ServerTimestampBehavior.ESTIMATE;
////            Date date = document.getDate("timeStamp", behavior);
////            Contribution contribution = document.toObject(Contribution.class, behavior);
////            Contribution contribution = document.toObject(Contribution.class);
//            Map<String, Object> map = document.getData();;
//            Contribution contribution = new Contribution(map);
//            contributions.add(contribution);
//
//        }
//        return temp;



        collectionReference.get()
                .continueWithTask(new Continuation<QuerySnapshot, Task<List<QuerySnapshot>>>() {
                    @Override
                    public Task<List<QuerySnapshot>> then(@NonNull Task<QuerySnapshot> task) {
                        List<Task<QuerySnapshot>> tasks = new ArrayList<Task<QuerySnapshot>>();
                        for (DocumentSnapshot documentSnapshot : task.getResult()){
                            tasks.add(documentSnapshot.getReference().collection("unrated").get());
                        }
                        return Tasks.whenAllSuccess(tasks);
                    }
                }).addOnCompleteListener(new OnCompleteListener<List<QuerySnapshot>>() {
            @Override
            public void onComplete(@NonNull Task<List<QuerySnapshot>> task) {
                List<QuerySnapshot> list = task.getResult();
                for(QuerySnapshot querySnapshot : list) {
                    for (DocumentSnapshot documentSnapshot : querySnapshot) {
                        Contribution contribution = documentSnapshot.toObject(Contribution.class);
                        contributions.add(contribution);
                    }
                }

            }
        });

//        temp.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete (@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()){
//                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
//                        Map<String, Object> map = document.getData();;
//                        Contribution contribution = new Contribution(map);
//                        contributions.add(contribution);
//                    }
//                } else {
//                    Log.d(TAG, "Error getting contributions: ", task.getException());
//                }
//            }
//        });


//        Task<QuerySnapshot> temp = collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot documentSnapshots) {
////                if (!documentSnapshots.isEmpty()){
////                    for (DocumentSnapshot document : documentSnapshots) {
////                        Contribution contribution = document.toObject(Contribution.class);
////                        contributions.add(contribution);
////                    }
////                }
//                System.out.println("SUCCESS!");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG, "Failure due to: ", e);
//                System.out.println("Failure reason: "+ e);
//            }
//        });

    }

    private void submitRating() {

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRetrieveContribution){
            getContributions();
            System.out.println(contributions);

        }
        else if (v.getId() == R.id.btnSubmitRating) {
            submitRating();
        }
    }
}