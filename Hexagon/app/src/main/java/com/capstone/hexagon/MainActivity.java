package com.capstone.hexagon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SILENT_SIGN_IN_FAILED";
    private FirebaseAuth mAuth;

    private Button logout;
    private Button upload;
    private TextView rate; //TODO: temporary, move to camera page later

    private ImageButton imageButton0Left;
    private ImageButton imageButton0Right;
    private ImageButton imageButton1Left;
    private ImageButton imageButton1Mid;
    private ImageButton imageButton1Right;
    private ImageButton imageButton2Left;
    private ImageButton imageButton2Right;

    private ImageButton[] hexButtons;
    private int[] hexStatus;

    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(this);

        rate = (TextView)findViewById(R.id.btnRateImages);
        rate.setOnClickListener(this);

        upload = (Button)findViewById(R.id.btnUploadImages);
        upload.setOnClickListener(this);

        logout.setText("Log Out");
        rate.setText("Rate Images (Temp)");
        upload.setText("Upload Images (Temp)");

        imageButton1Left = (ImageButton) findViewById(R.id.imageButton1Left);
        imageButton1Mid = (ImageButton) findViewById(R.id.imageButton1Mid);
        imageButton1Right = (ImageButton) findViewById(R.id.imageButton1Right);

        imageButton0Left = (ImageButton) findViewById(R.id.imageButton0Left);
        imageButton0Right = (ImageButton) findViewById(R.id.imageButton0Right);

        imageButton2Left = (ImageButton) findViewById(R.id.imageButton2Left);
        imageButton2Right = (ImageButton) findViewById(R.id.imageButton2Right);

        imageButton1Left.setOnClickListener(this);
        imageButton1Mid.setOnClickListener(this);
        imageButton1Right.setOnClickListener(this);

        imageButton0Left.setOnClickListener(this);
        imageButton0Right.setOnClickListener(this);

        imageButton2Left.setOnClickListener(this);
        imageButton2Right.setOnClickListener(this);

        hexButtons = new ImageButton[] {imageButton0Left, imageButton0Right, imageButton1Left, imageButton1Right, imageButton2Left, imageButton2Right};
        hexStatus = new int[]{1, 2, 1, 0, 2, 0};

        for (int i = 0; i < hexButtons.length; i++) {
            if (hexStatus[i] == 0) {
                hexButtons[i].setBackgroundResource(R.drawable.empty_hex);
            }
            else if (hexStatus[i] == 1) {
                hexButtons[i].setBackgroundResource(R.drawable.inactive_hex);
            }
            else if (hexStatus[i] == 2) {
                hexButtons[i].setBackgroundResource(R.drawable.active_hex);
            }
        }
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
        if (v.getId() == R.id.btnRateImages){
            goToRatePage();
        }
        // Temporary button to see upload images page
        if (v.getId() == R.id.btnUploadImages) {
            goToUploadPage();
        }
        if (v.getId() == R.id.imageButton1Mid) {
            // Go To Unity
        }
        if (v.getId() == R.id.imageButton0Left) {
            String temp = "";
            if (hexStatus[0] == 0) {
                temp = "Empty";
            } else if (hexStatus[0] == 1) {
                temp = "Inactive";
            } else if (hexStatus[0] == 2) {
                temp = "Active";
            }
            MessageBox("Status of this hexagon is: " + temp + " .");
        }
        if (v.getId() == R.id.imageButton0Right) {
            String temp = "";
            if (hexStatus[1] == 0) {
                temp = "Empty";
            } else if (hexStatus[1] == 1) {
                temp = "Inactive";
            } else if (hexStatus[1] == 2) {
                temp = "Active";
            }
            MessageBox("Status of this hexagon is: " + temp + " .");
        }
        if (v.getId() == R.id.imageButton1Left) {
            String temp = "";
            if (hexStatus[2] == 0) {
                temp = "Empty";
            } else if (hexStatus[2] == 1) {
                temp = "Inactive";
            } else if (hexStatus[2] == 2) {
                temp = "Active";
            }
            MessageBox("Status of this hexagon is: " + temp + " .");
        }
        if (v.getId() == R.id.imageButton1Right) {
            String temp = "";
            if (hexStatus[3] == 0) {
                temp = "Empty";
            } else if (hexStatus[3] == 1) {
                temp = "Inactive";
            } else if (hexStatus[3] == 2) {
                temp = "Active";
            }
            MessageBox("Status of this hexagon is: " + temp + " .");
        }
        if (v.getId() == R.id.imageButton2Left) {
            String temp = "";
            if (hexStatus[4] == 0) {
                temp = "Empty";
            } else if (hexStatus[4] == 1) {
                temp = "Inactive";
            } else if (hexStatus[4] == 2) {
                temp = "Active";
            }
            MessageBox("Status of this hexagon is: " + temp + " .");
        }
        if (v.getId() == R.id.imageButton2Right) {
            String temp = "";
            if (hexStatus[5] == 0) {
                temp = "Empty";
            } else if (hexStatus[5] == 1) {
                temp = "Inactive";
            } else if (hexStatus[5] == 2) {
                temp = "Active";
            }
            MessageBox("Status of this hexagon is: " + temp + " .");
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

    public void MessageBox(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}