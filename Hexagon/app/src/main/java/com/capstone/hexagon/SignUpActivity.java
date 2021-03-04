package com.capstone.hexagon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    private Button signUp;
    private TextView title;
    private EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
    private ProgressBar progressBar;

//    private FirebaseDatabase database;
//    private DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("myMessage");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//        mRef = database.getReference("myMessage");

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
            startActivity(new Intent(this, LoginActivity.class));
        }
        else if (v.getId() == R.id.btnSignUp){
            signUp();
//            String message = "Hello World!";
//            mRef.push().setValue(message);
        }
    }

    private void signUp() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (name.isEmpty()){
            editTextName.setError("Please enter your name");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(SignUpActivity.this,"Email address is invalid",Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Please enter a password");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){ //firebase requires this
            editTextPassword.setError("Minimum password length must be 6 characters");
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

                        if (task.isSuccessful()){
                            Player player = new Player(name, email);

                            FirebaseDatabase.getInstance().getReference("players")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(player).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "You have signed up successfully!", Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(SignUpActivity.this, "Sign up was unsuccessful, please try again.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Sign up was unsuccessful, please try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}