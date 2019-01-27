package com.example.treningappproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class AddEditExcercice extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_ID =
            "com.example.treningappproject.EXTRA_ID";

    public static final String EXTRA_DAY =
            "com.example.treningappproject.EXTRA_DAY";

    public static final String EXTRA_NAME =
            "com.example.treningappproject.EXTRA_NAME";

    public static final String EXTRA_SET =
            "com.example.treningappproject.EXTRA_SET";

    public static final String EXTRA_REP =
            "com.example.treningappproject.EXTRA_REP";

    public static final String EXTRA_PRIORITY =
            "com.example.treningappproject.EXTRA_PRIORITY";



    private EditText editTextName;
    private EditText editTextSets;
    private EditText editTextReps;
    private NumberPicker numberPickerPriority;
    private Spinner spinnerDay;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_excercice);

        spinnerDay = findViewById(R.id.spinnerDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.DayOfWeek, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDay.setAdapter(adapter);
        spinnerDay.setOnItemSelectedListener(this);
        editTextName = findViewById(R.id.edit_text_name);
        editTextSets = findViewById(R.id.edit_text_set);
        editTextReps = findViewById(R.id.edit_text_repets);
        numberPickerPriority = findViewById(R.id.number_picker_priority);


        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);



        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID))
        {

            setTitle("Edit Excercice");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextReps.setText(intent.getIntExtra(EXTRA_REP,1));
            editTextSets.setText(intent.getIntExtra(EXTRA_SET,1));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
            //int spinnerPosition = adapter.getPosition((CharSequence)this);
            //spinnerDay.setSelection(spinnerPosition);
        }else
        setTitle("Add Excercice");
    }

    private void saveExcercice(){
        String name = editTextName.getText().toString();
        String set = editTextSets.getText().toString();
        int finalset = Integer.parseInt(set);
        String rep = editTextReps.getText().toString();
        int finalrep = Integer.parseInt(rep);
        int priority =numberPickerPriority.getValue();
        String day = spinnerDay.getSelectedItem().toString();

        if (name.trim().isEmpty() || day.trim().isEmpty() ) {
            Toast.makeText(this, "Please fill the blank spaces", Toast.LENGTH_SHORT).show();
            return;

        }
            Intent data = new Intent();
            data.putExtra(EXTRA_DAY, day);
            data.putExtra(EXTRA_NAME, name);
            data.putExtra(EXTRA_SET, finalset);
            data.putExtra(EXTRA_REP, finalrep);
            data.putExtra(EXTRA_PRIORITY, priority);

            int id = getIntent().getIntExtra(EXTRA_ID,-1);
            if(id !=-1){
                data.putExtra(EXTRA_ID,id);

            }

        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_excercice_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){

           case R.id.save_excercice:
           saveExcercice();
           return true;
           default:
               return super.onOptionsItemSelected(item);
       }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String text = parent.getItemAtPosition(position).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
