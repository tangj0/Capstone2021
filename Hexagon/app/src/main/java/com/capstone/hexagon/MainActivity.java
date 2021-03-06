package com.capstone.hexagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SILENT_SIGN_IN_FAILED";
    private FirebaseAuth mAuth;

    private Button logout;
    private TextView title;

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;

//    private FirebaseDatabase database;
//    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("myMessage");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//        mRef = database.getReference("myMessage");

        title = (TextView) findViewById(R.id.title);

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(this);
    }

    //using if statements instead of switch because of this warning:
    //"Resource IDs will be non-final in Android Gradle Plugin version 5.0, avoid using them in switch case statements"
    @Override
    public void onClick(View v) {
        // Logout button is clicked
        if (v.getId() == R.id.btnLogout){
            // Sign out the user
            FirebaseAuth.getInstance().signOut();

            // Go to the login page
            goToLoginPage();
        }
    }

    private void goToLoginPage() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}