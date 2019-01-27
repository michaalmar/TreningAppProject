package com.example.treningappproject;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ExcerciceViewModel extends AndroidViewModel {

    private ExcerciceRepository repository;
    private LiveData<List<Excercice>> allExcercices;
    public ExcerciceViewModel(@NonNull Application application) {
        super(application);
        repository = new ExcerciceRepository(application);
        allExcercices = repository.getAllExcercice();
    }

    public void insert(Excercice excercice){
        repository.insert(excercice);
    }

    public void update(Excercice excercice){
        repository.update(excercice);
    }

    public void delete(Excercice excercice){
        repository.delete(excercice);
    }

    public void deleteAllExcercices(){ repository.deleteAllExcercice(); }

    public LiveData<List<Excercice>> getAllExcercices()
    {
        return allExcercices;
    }


}
