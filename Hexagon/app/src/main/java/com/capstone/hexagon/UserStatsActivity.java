package com.capstone.hexagon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class UserStatsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button go_back_to_main;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stat);

        go_back_to_main = (Button) findViewById(R.id.back_button);
        go_back_to_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Sign up button is clicked
        if (v.getId() == R.id.back_button) {
            goToLoginPage();
        }

//        else if (v.getId() == R.id.user_stat_button) {
//            goToUserStatPage();
//        }
    }

    private void goToLoginPage() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
