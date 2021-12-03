package com.capstone.hexagon;

import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

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

public class RateActivityGlen extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private Adapter adapter;

    private TextView tvRateTitle;
    private TextView garbageTypeName;
    private TextView tvGarbageType;
    private TextView garbageAmountName;
    private TextView tvGarbageAmount;

    private ImageButton yes;
    private ImageButton no;

    private ImageView before;
    private ImageView after;

    private ImageView result;
    private TextView value;

    private ImageView yourComment;
    private EditText yourCommentTextBox;
    private Button submit;

    private ImageView comment1;
    private TextView commentText1;

    private ImageView comment2;
    private TextView commentText2;

    private Button next;

    String TAG = RateActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvRateTitle = (TextView) findViewById(R.id.tvRateTitle);
        tvRateTitle.setText("Rate Contribution");

        garbageTypeName = (TextView) findViewById(R.id.garbageTypeName);
        garbageTypeName.setText("Garbage Type:");
        tvGarbageType = (TextView) findViewById(R.id.tvGarbageType);

        garbageAmountName = (TextView) findViewById(R.id.garbageAmountName);
        garbageAmountName.setText("Garbage Amount:");
        tvGarbageAmount = (TextView) findViewById(R.id.tvGarbageAmount);

        yes = (ImageButton) findViewById(R.id.yes);
        yes.setOnClickListener(this);
        yes.setBackgroundResource(R.drawable.yes);
        no = (ImageButton) findViewById(R.id.no);
        no.setOnClickListener(this);
        no.setBackgroundResource(R.drawable.no);

        result = (ImageView) findViewById(R.id.result);
        result.setBackgroundResource(R.drawable.empty);

        before = (ImageView) findViewById(R.id.before);
        before.setBackgroundResource(R.drawable.mask_before);

        after = (ImageView) findViewById(R.id.after);
        after.setBackgroundResource(R.drawable.mask_after);

        value = (TextView) findViewById(R.id.value);
        value.setText("AI recommendation: Valid");

        yourComment = (ImageView) findViewById(R.id.yourComment);
        comment1.setBackgroundResource(R.drawable.left);
        yourCommentTextBox = (EditText) findViewById(R.id.yourCommentTextBox);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);

        comment1 = (ImageView) findViewById(R.id.comment1);
        comment1.setBackgroundResource(R.drawable.right);
        commentText1 = (TextView) findViewById(R.id.commentText1);
        commentText1.setText("Fake!");

        comment2 = (ImageView) findViewById(R.id.comment2);
        comment2.setBackgroundResource(R.drawable.left);
        commentText2 = (TextView) findViewById(R.id.commentText2);
        commentText2.setText("Very Nice!");

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.yes){
            yes.setBackgroundResource(R.drawable.yestrans);
            no.setBackgroundResource(R.drawable.notrans);
            result.setBackgroundResource(R.drawable.yes75);
            value.setText("76%");
        }
        if (v.getId() == R.id.no){
            yes.setBackgroundResource(R.drawable.yestrans);
            no.setBackgroundResource(R.drawable.notrans);
            result.setBackgroundResource(R.drawable.yes75);
            value.setText("76%");
        }
        if (v.getId() == R.id.submit){
            submit.setAlpha(.5f);
            submit.setClickable(false);
        }
        if (v.getId() == R.id.next) {
            yes.setBackgroundResource(R.drawable.yes);
            no.setBackgroundResource(R.drawable.no);
            result.setBackgroundResource(R.drawable.empty);
            value.setText(" ");
            submit.setAlpha(1);
            submit.setClickable(true);
            commentText1.setText("I am not impressed.");
            commentText2.setText("Thank you very much.");
            before.setBackgroundResource(R.drawable.mask_before2);
            after.setBackgroundResource(R.drawable.mask_after2);
        }
    }
}