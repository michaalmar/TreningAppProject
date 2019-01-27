package com.example.treningappproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyCalculator extends AppCompatActivity {

    private FragmentData fragmentData;
    private FragmentSummary fragmentSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calculator);

        fragmentData = new FragmentData();
        fragmentSummary = new FragmentSummary();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_data,fragmentData)
                .replace(R.id.container_summary,fragmentSummary)
                .commit();


    }
}
