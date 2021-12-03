package com.capstone.hexagon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class RateActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private Adapter adapter;

    private Spinner rateOptions;
    private final String[] approvalOptions = new String[]{"Approve", "Reject"};
    private Button retrieveContribution, submitRating;
    private ImageButton backToMain;
    private TextView tvGarbageType, tvGarbageAmount;
    private EditText etComment;

    private FirebaseAuth auth;
    private StorageReference storageReference;
    private FirebaseFirestore fStore;

    private Uri uri;
    private String playerId;

    private Contribution contToRate;
    private Rating rating;
    private final int MAX_NUM_OF_RATINGS = 5; //TODO: change to 21
    private List<Contribution> contributions;
    private List<Rating> ratings;
    private List<Contribution> contsToRemove;
    private List<Boolean> equalsCurr;

    String TAG = RateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        viewPager = findViewById(R.id.view_pager);
        adapter = new Adapter(this);

        rateOptions = findViewById(R.id.spinnerRateOptions);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, approvalOptions);
        rateOptions.setAdapter(arrayAdapter);

        retrieveContribution = (Button) findViewById(R.id.btnRetrieveContribution);
        retrieveContribution.setOnClickListener(this);
        submitRating = (Button) findViewById(R.id.btnSubmitRating);
        submitRating.setOnClickListener(this);

        backToMain = (ImageButton) findViewById(R.id.back_button);
        backToMain.setOnClickListener(this);
        backToMain.setImageResource(R.drawable.logo);

        tvGarbageType = (TextView) findViewById(R.id.tvGarbageType);
        tvGarbageAmount = (TextView) findViewById(R.id.tvGarbageAmount);

        etComment = (EditText) findViewById(R.id.editTextMultiLineComment);

        auth = FirebaseAuth.getInstance();
        FirebaseUser player = auth.getCurrentUser();
        playerId = player.getUid();

        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseFirestore.getInstance();

        contributions = new ArrayList<>();
        contsToRemove = new ArrayList<>();
        rating = new Rating();
        ratings = new ArrayList<>();

        getAllContributions();
    }

    // get all contributions from the "unrated" pile, from all players
    // reference: https://stackoverflow.com/questions/48027696/firestore-task-whenallcomplete-task-is-not-yet-complete
    // Continuation<> explanation: https://stackoverflow.com/questions/40161333/google-play-services-task-api-continuewith-vs-continuewithtask
    public void getAllContributions() {
        CollectionReference collectionReference = fStore.collection("contributions");

        // Continuation<input, Task<output>>, task = input, tasks = output
        collectionReference.get()
                .continueWithTask(new Continuation<QuerySnapshot, Task<List<QuerySnapshot>>>() {
                    @Override
                    public Task<List<QuerySnapshot>> then(@NonNull Task<QuerySnapshot> task) {
                        List<Task<QuerySnapshot>> tasks = new ArrayList<Task<QuerySnapshot>>();

                        // getting each of the "unrated" collections from each contribution, hence the result is a List<QuerySnapshot>
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

                        // add everything to list for now, filter out current player later
                        // otherwise null pointer exception, need to finish getting contribution first
                        contributions.add(contribution);
                    }
                }

            }
        });
    }

    private void getContribution() throws IOException {
        // remove all contributions by current player and contributions to-be-rated from contributions list
        if (contributions != null) {
            for (Contribution contribution : contributions) {
                if (!contribution.getPlayerId().contentEquals(playerId)) {
                    contToRate = contribution;
                    contsToRemove.add(contribution);
                    break;
                } else {
                    contsToRemove.add(contribution);
                }
            }
            contributions.removeAll(contsToRemove);
        }

        // set rating page contribution to contToRate
        if (contToRate != null) {
            tvGarbageType.setText(contToRate.getGarbageType().toString());
            tvGarbageAmount.setText(String.valueOf(contToRate.getGarbageAmount()));

            // set rating viewPager images
            String[] urls = {contToRate.getBeforeImg(), contToRate.getAfterImg()};
            ImageLoadAsyncTask imageLoadAsyncTask = new ImageLoadAsyncTask(urls, adapter, viewPager);
            imageLoadAsyncTask.execute();

            // TODO: in future, sort contributions by date (first come first serve)
            // TODO: prevent players from getting a new contribution without submitting the first rating
        }

    }



    private void submitRating() {
        // set current rating
        UUID ratingId = UUID.randomUUID();
        String strRatingId = ratingId.toString();
        if (contToRate != null && tvGarbageType.getText() != "") {
            rating.setId(strRatingId);

            rating.setRaterId(playerId);

            if (rateOptions.getSelectedItem().toString().contentEquals(approvalOptions[0])) {
                rating.setApproval(true);
            }
            else if (rateOptions.getSelectedItem().toString().contentEquals(approvalOptions[1])) {
                rating.setApproval(false);
            }
            rating.setComment(etComment.getText().toString());


            // submit current rating
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Uploading");
            pd.show();

            DocumentReference ratingDocumentReference = fStore.collection("contributions")
                    .document(contToRate.getPlayerId())
                    .collection("unrated")
                    .document(contToRate.getId())
                    .collection("ratings")
                    .document(strRatingId);
            ratingDocumentReference.set(rating).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RateActivity.this, "Rating uploaded successfully!", Toast.LENGTH_LONG).show();
                        pd.dismiss();
                    }
                    else {
                        Toast.makeText(RateActivity.this, "Rating upload was unsuccessful", Toast.LENGTH_LONG).show();
                    }
                }
            });

            //Increment corresponding contribution rating count
            int numOfRatings = contToRate.getNumOfRatings() + 1;
            fStore.collection("contributions")
                    .document(contToRate.getPlayerId())
                    .collection("unrated")
                    .document(contToRate.getId()).update("numOfRatings", numOfRatings);

            // automatically clear contToRate UI (don't want same user rating same cont twice)
            tvGarbageType.setText("");
            tvGarbageAmount.setText("");
            etComment.getText().clear();

            // clear contToRate images
            Bitmap[] clear = new Bitmap[2];
            clear[0] = null;
            clear[1] = null;
            adapter.setImageArray(clear);
            viewPager.setAdapter(adapter);

            // if a contribution has enough ratings, compute finalRating and move it to the "rated" pile
            if (numOfRatings >= MAX_NUM_OF_RATINGS) { //should never be > though
                calcFinalRating();
            }
        }

    }

    // get all ratings from the current contribution
    private void calcFinalRating() {
        CollectionReference ratingsRef = fStore.collection("contributions")
                .document(contToRate.getPlayerId())
                .collection("unrated")
                .document(contToRate.getId())
                .collection("ratings");

        ratingsRef.get()
                .continueWithTask(new Continuation<QuerySnapshot, Task<List<DocumentSnapshot>>>() {
                    @Override
                    public Task<List<DocumentSnapshot>> then(@NonNull Task<QuerySnapshot> task) {
                        List<Task<DocumentSnapshot>> tasks = new ArrayList<Task<DocumentSnapshot>>();
                        for (DocumentSnapshot documentSnapshot : task.getResult()){
                            tasks.add(documentSnapshot.getReference().get());
                        }

                        Log.d("TASKS len: ", String.valueOf(tasks.size()));
                        return Tasks.whenAllSuccess(tasks);
                    }
                }).addOnCompleteListener(new OnCompleteListener<List<DocumentSnapshot>>() {
            @Override
            public void onComplete(@NonNull Task<List<DocumentSnapshot>> task) {
                List<DocumentSnapshot> list = task.getResult();

                // average all ratings
                for(DocumentSnapshot documentSnapshot : list) {
                    Rating rating = documentSnapshot.toObject(Rating.class);
                    ratings.add(rating);
                }

                Log.d("Rating: ", String.valueOf(ratings));
                int trueCount = 0;
                for (Rating rating : ratings) {
                    if (rating.isApproval()) {
                        trueCount++;
                    }
                }
                Log.d("True count: ", String.valueOf(trueCount));
                boolean finalRating = ((float) trueCount / (float) MAX_NUM_OF_RATINGS) > 0.5;
                fStore.collection("contributions")
                        .document(contToRate.getPlayerId())
                        .collection("unrated")
                        .document(contToRate.getId()).update("finalRating", finalRating);

                // move contribution to rated pile
                DocumentReference contRef = fStore.collection("contributions")
                        .document(contToRate.getPlayerId())
                        .collection("unrated")
                        .document(contToRate.getId());

                DocumentReference newContRef = fStore.collection("contributions")
                        .document(contToRate.getPlayerId())
                        .collection("rated")
                        .document(contToRate.getId());

                contRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        newContRef.set(Objects.requireNonNull(documentSnapshot.getData()));
                    }
                });

                // move ratings of rated contribution
                CollectionReference newRatingsRef = newContRef.collection("ratings");
                ratingsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()){
                            newRatingsRef.add(Objects.requireNonNull(documentSnapshot.getData()));
                        }
                    }
                }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        //delete old contribution and its ratings in unrated
                        ratingsRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot querySnapshot) {
                                for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
                                    documentSnapshot.getReference().delete();
                                }
                                Log.d(String.valueOf(ratingsRef), " ratings successfully deleted");
                            }
                        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                contRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(String.valueOf(contRef), " contribution successfully deleted");
                                    }
                                });
                            }
                        });
                    }
                });

            }
        });

    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRetrieveContribution){
//            System.out.println(contributions);
            try {
                getContribution();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println(contsToRemove);
//            System.out.println("To RATE: " + contToRate);
        }
        else if (v.getId() == R.id.btnSubmitRating) {
            submitRating();
//            System.out.println("RATING: " + rating);
//            System.out.println(rating.getComment().toString());
//            System.out.println(rating.getId());
//            System.out.println(rating.getRaterId());
//            System.out.println(rating.isApproval());
        }
        else if (v.getId() == R.id.back_button) {
            goToMainPage();
        }
    }

    private void goToMainPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}