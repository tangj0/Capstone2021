package com.capstone.hexagon;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class RateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        ViewPager viewPager = findViewById(R.id.view_pager);

        Adapter adapter = new Adapter(this);
        viewPager.setAdapter(adapter);

        Spinner rateOptions = findViewById(R.id.spinnerRateOptions);
        String[] options = new String[]{"Approve", "Reject"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        rateOptions.setAdapter(arrayAdapter);
    }
}