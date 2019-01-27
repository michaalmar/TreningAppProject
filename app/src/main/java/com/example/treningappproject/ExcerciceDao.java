package com.example.treningappproject;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ExcerciceDao {

    @Insert
    void insert(Excercice excercice);

    @Update
    void update(Excercice excercice);

    @Delete
    void delete(Excercice excercice);

    @Query("DELETE FROM excercice_table")
    void deleteAllExcercice();

    @Query("SELECT * FROM excercice_table ORDER BY priority ASC")
    LiveData<List<Excercice>> getAllExcercices();




}
