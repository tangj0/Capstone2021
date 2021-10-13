package com.capstone.hexagon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SILENT_SIGN_IN_FAILED";
    private FirebaseAuth mAuth;

    private Button logout;
    private Button upload;
    private TextView title;

    private TextView rate; //TODO: temporary, move to camera page later

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        title = (TextView) findViewById(R.id.title);
        //title.append("\nHello " + FirebaseFirestore.getInstance().collection("players").document(mAuth.getCurrentUser().getUid()).get().);

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(this);

        rate = (TextView)findViewById(R.id.btnRateImages);
        rate.setOnClickListener(this);

        upload = (Button)findViewById(R.id.btnUploadImages);
        upload.setOnClickListener(this);
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
        // Temporary button to see Rate images page
        else if (v.getId() == R.id.btnRateImages){
            goToRatePage();
        }
        // Temporary button to see upload images page
        else if (v.getId() == R.id.btnUploadImages) {
            goToUploadPage();
        }
    }

    private void goToLoginPage() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void goToRatePage() {
        startActivity(new Intent(this, RateActivity.class));
        finish();
    }

    private void goToUploadPage() {
        startActivity(new Intent(this, UploadActivity.class));
        finish();
    }
}