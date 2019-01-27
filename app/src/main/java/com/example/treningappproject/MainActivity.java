package com.example.treningappproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button myExcerciceView;
    private Button myCalculatorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       myExcerciceView = (Button) findViewById(R.id.bt_excercice);
       myCalculatorView = (Button) findViewById(R.id.bt_calculators);

       myCalculatorView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openMyCalculator();
           }
       });

       myExcerciceView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               openMyExcercice();


           }
       });
    }

    private void openMyCalculator() {

        Intent intent = new Intent(this,MyCalculator.class);
        startActivity(intent);
    }

    public void openMyExcercice() {

        Intent intent = new Intent(this, MyExcercice.class);
        startActivity(intent);
    }








}
