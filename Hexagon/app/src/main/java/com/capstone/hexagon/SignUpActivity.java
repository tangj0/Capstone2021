package com.capstone.hexagon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    private String playerID;

    private Button signUp;
    private TextView title;
    private EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        title = (TextView) findViewById(R.id.tvTitle);
        title.setOnClickListener(this); //use this to bring user back to login page by clicking on title Hexagon

        signUp = (Button) findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.etName);
        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextPassword = (EditText) findViewById(R.id.etPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    //using if statements instead of switch because of this warning:
    //"Resource IDs will be non-final in Android Gradle Plugin version 5.0, avoid using them in switch case statements"
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvTitle){
            goToLoginPage();
        }
        else if (v.getId() == R.id.btnSignUp){
            signUp();
        }
    }

    private void signUp() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Validate the name
        if (name.isEmpty()) {
            editTextName.setError("Please enter your name");
            editTextName.requestFocus();
            return;
        }

        // Validate the email address
        try {
            validateEmail(email);
        } catch (IllegalArgumentException e) {
            editTextEmail.setError(e.getMessage());
            editTextEmail.requestFocus();
            return;
        }

        // Validate the password
        try {
            validatePassword(password);
        } catch (IllegalArgumentException e) {
            editTextPassword.setError(e.getMessage());
            editTextPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()){
            editTextConfirmPassword.setError("Please confirm your password");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)){
            Toast.makeText(SignUpActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // need this to get user display name
                            FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name).build();
                            user1.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("Profile updated for: ", name);
                                            }
                                        }
                                    });

                            //add user to our database (TODO: there might be a better way to do this...)
                            Player player = new Player(name, email);
                            playerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            DocumentReference documentReference = FirebaseFirestore.getInstance().collection("players").document(playerID);
                            documentReference.set(player).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "You have signed up successfully!", Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility(View.INVISIBLE);
                                        goToMainPage();
                                    }
                                    else {
                                        Toast.makeText(SignUpActivity.this, "Sign up was unsuccessful, please try again (Error code 1).", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Sign up was unsuccessful, please try again (Error code 2).", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        progressBar.setVisibility(View.INVISIBLE);
    }

    public static void validateEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Please enter your email");
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw new IllegalArgumentException("Email address is invalid");
        }
    }

    public static void validatePassword(String password) {
        if (password.isEmpty()){
            throw new IllegalArgumentException("Please enter a password");
        }

        if (password.length() < 6){ //firebase requires this
            throw new IllegalArgumentException("Minimum password length must be 6 characters");
        }
    }

    public static boolean validateName(String name) {
        if (name.isEmpty()){
            return false;
        }

        return true;
    }

    private void goToLoginPage() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void goToMainPage() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}