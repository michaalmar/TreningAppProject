package com.example.treningappproject;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "excercice_table")
public class Excercice {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String day;

    private String name;

    private int sets;

    private int repeat;

    private int priority;


    public Excercice(String day, String name, int sets, int repeat, int priority) {
        this.day = day;
        this.name = name;
        this.sets = sets;
        this.repeat = repeat;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getRepeat() {
        return repeat;
    }

    public int getPriority() {
        return priority;
    }
}
