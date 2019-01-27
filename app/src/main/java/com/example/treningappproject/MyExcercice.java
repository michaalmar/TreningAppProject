package com.example.treningappproject;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;

public class MyExcercice extends AppCompatActivity {

    public static final int ADD_EXCERCICE_REQUEST = 1;

    public static final int EDIT_EXCERCICE_REQUEST = 2;

    private ExcerciceViewModel excerciceViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_excercice);

        FloatingActionButton buttonAddExcercice = findViewById(R.id.button_add_excercice);
        buttonAddExcercice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyExcercice.this, AddEditExcercice.class);
                startActivityForResult(intent,ADD_EXCERCICE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ExcerciceAdapter adapter = new ExcerciceAdapter();
        recyclerView.setAdapter(adapter);



        excerciceViewModel = ViewModelProviders.of(this).get(ExcerciceViewModel.class);
        excerciceViewModel.getAllExcercices().observe(this, new Observer<List<Excercice>>() {
            @Override
            public void onChanged(@Nullable List<Excercice> excercices) {
                //update RecyclerView

             adapter.setExcercices(excercices);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
               excerciceViewModel.delete(adapter.getExcerciceAt(viewHolder.getAdapterPosition()));
               Toast.makeText(MyExcercice.this,"Delete Excercice",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new ExcerciceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Excercice excercice) {
                Intent intent = new Intent(MyExcercice.this, AddEditExcercice.class);
                intent.putExtra(AddEditExcercice.EXTRA_ID,excercice.getId());
                intent.putExtra(AddEditExcercice.EXTRA_DAY,excercice.getDay());
                intent.putExtra(AddEditExcercice.EXTRA_NAME,excercice.getName());
                intent.putExtra(AddEditExcercice.EXTRA_SET,excercice.getSets());
                intent.putExtra(AddEditExcercice.EXTRA_REP,excercice.getRepeat());
                intent.putExtra(AddEditExcercice.EXTRA_PRIORITY,excercice.getPriority());
                startActivityForResult(intent,EDIT_EXCERCICE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_EXCERCICE_REQUEST && resultCode == RESULT_OK){
            String day = data.getStringExtra(AddEditExcercice.EXTRA_DAY);
            String name = data.getStringExtra(AddEditExcercice.EXTRA_NAME);
            int sets = data.getIntExtra(AddEditExcercice.EXTRA_SET,1);
            int repeat = data.getIntExtra(AddEditExcercice.EXTRA_REP,1);
            int priority = data.getIntExtra(AddEditExcercice.EXTRA_PRIORITY,1);

            Excercice excercice = new Excercice(day,name,sets,repeat,priority);
            excerciceViewModel.insert(excercice);

            Toast.makeText(this, "Excercice saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_EXCERCICE_REQUEST && resultCode == RESULT_OK) {

            int id = data.getIntExtra(AddEditExcercice.EXTRA_ID,-1);

            if(id == -1){
                Toast.makeText(this,"Excercice can't be update",Toast.LENGTH_SHORT).show();
                return;
            }

            String day = data.getStringExtra(AddEditExcercice.EXTRA_DAY);
            String name = data.getStringExtra(AddEditExcercice.EXTRA_NAME);
            int sets = data.getIntExtra(AddEditExcercice.EXTRA_SET,1);
            int repeat = data.getIntExtra(AddEditExcercice.EXTRA_REP,1);
            int priority = data.getIntExtra(AddEditExcercice.EXTRA_PRIORITY,1);

            Excercice excercice = new Excercice(day,name,sets,repeat,priority);
            excercice.setId(id);
            excerciceViewModel.update(excercice);

            Toast.makeText(this, "Excercice updated", Toast.LENGTH_SHORT ).show();
        }
        else
        {
            Toast.makeText(this, "Excercice not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete_all_excercices:
                excerciceViewModel.deleteAllExcercices();
                Toast.makeText(this, "Delete all excercice", Toast.LENGTH_SHORT).show();
                return true;
default:
    return super.onOptionsItemSelected(item);

        }

    }
}
