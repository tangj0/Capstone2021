package com.capstone.hexagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView signUp;
    private TextView login;
    private TextView forgotPassword;

    private EditText editTextEmail;
    private EditText editTextPassword;

    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 1; // The sign in with Google request code
    private static final String GOOGLE_TAG = "GOOGLE_SIGN_IN";
    private static final String EMAIL_TAG = "EMAIL_SIGN_IN";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Buttons
        signUp = (TextView)findViewById(R.id.btnGoToSignUp);
        signUp.setOnClickListener(this);
        signUp = (TextView)findViewById(R.id.btnLogin);
        signUp.setOnClickListener(this);
        signUp = (TextView)findViewById(R.id.btnForgotPass);
        signUp.setOnClickListener(this);

        // Text
        editTextEmail = (EditText)findViewById(R.id.etEmail);
        editTextPassword = (EditText)findViewById(R.id.etPassword);

        // Sign in with Google

        mAuth = FirebaseAuth.getInstance();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Register the sign in with Google button's OnClickListener to sign in when clicked
        findViewById(R.id.google_sign_in_button).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if the user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();

        // Update UI accordingly
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            // The user has already signed in to the app with Google

            // Go to the Main page
            goToMainPage();
        }
        else {
            // The user has not yet signed in to the app with Google
            // Stay on the login page
        }
    }

    @Override
    public void onClick(View v) {
        // Sign up button is clicked
        if (v.getId() == R.id.btnGoToSignUp){
            // Go to the Sign up page
            goToSignUpPage();
        }
        // Sign in with Google button is clicked
        else if (v.getId() == R.id.google_sign_in_button) {
            signInWithGoogle();
        }
        // Login button is clicked
        else if (v.getId() == R.id.btnLogin) {
            signInWithEmailAndPassword(editTextEmail.getText().toString().trim(), editTextPassword.getText().toString().trim());
        }
    }

    private void signInWithEmailAndPassword(String email, String password) {

        // Validate the email
        try {
            System.out.println("@: " + email);
            SignUpActivity.validateEmail(email);
        } catch (IllegalArgumentException e) {
            editTextEmail.setError(e.getMessage());
            editTextEmail.requestFocus();
            return;
        }

        // Validate the password
        try {
            SignUpActivity.validatePassword(password);
        } catch (IllegalArgumentException e) {
            editTextPassword.setError(e.getMessage());
            editTextPassword.requestFocus();
            return;
        }

        // Attempt to sign the user in with the given email and password
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(EMAIL_TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(EMAIL_TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            // ...
                        }

                        // ...
                    }
                });
    }

    private void signInWithGoogle() {
        // Starting the intent prompts the user to select a Google account to sign in with
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(GOOGLE_TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google sign in failed, update UI appropriately
                Log.w(GOOGLE_TAG, "Google sign in failed", e);
                updateUI(null);
            }
            //handleSignInResult(task);
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        // After a user successfully signs in, get an ID token from the GoogleSignInAccount
        // object, exchange it for a Firebase credential, and authenticate with Firebase using
        // the Firebase credential.
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Log.d(GOOGLE_TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Determine if the user just signed up (new user) or signed in
                            if (task.getResult().getAdditionalUserInfo().isNewUser()) {
                                // User signed up, so add a Player instance in the database
                                Player player = new Player(user.getDisplayName(), user.getEmail());
                                FirebaseFirestore.getInstance().collection("players")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .set(player).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(LoginActivity.this, "You have signed up successfully!", Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            Toast.makeText(LoginActivity.this, "Sign up was unsuccessful, please try again.", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }

                            // Otherwise, user signed in. No need to display a message.

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(GOOGLE_TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this,"Sign in with Google failed.",Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void newUserSignUp() {

    }

    private void goToMainPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void goToSignUpPage() {
        startActivity(new Intent(this, SignUpActivity.class));
        finish();
    }
}