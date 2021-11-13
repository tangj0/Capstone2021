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

    private TextView user;
    private TextView time;
    private TextView place;

    private TextView mon;
    private TextView tue;
    private TextView wed;
    private TextView thu;
    private TextView fri;
    private TextView sat;
    private TextView sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stat);

        go_back_to_main = (Button) findViewById(R.id.back_button);
        go_back_to_main.setOnClickListener(this);

        user = (TextView) findViewById(R.id.user);
        time = (TextView) findViewById(R.id.time);
        place = (TextView) findViewById(R.id.space);

        mon = (TextView) findViewById(R.id.mon);
        tue = (TextView) findViewById(R.id.tue);
        wed = (TextView) findViewById(R.id.wed);
        thu = (TextView) findViewById(R.id.thu);
        fri = (TextView) findViewById(R.id.fri);
        sat = (TextView) findViewById(R.id.sat);
        sun = (TextView) findViewById(R.id.sun);

        mon.setText("MON");
        tue.setText("TUE");
        wed.setText("WED");
        thu.setText("THU");
        fri.setText("FRI");
        sat.setText("SAT");
        sun.setText("SUN");



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
