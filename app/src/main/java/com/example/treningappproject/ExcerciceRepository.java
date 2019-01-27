package com.example.treningappproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.os.AsyncTask;

import java.util.List;

public class ExcerciceRepository {

    private ExcerciceDao excerciceDao;
    private LiveData<List<Excercice>> allExcercice;

    public ExcerciceRepository(Application application){

        ExcerciceDatabase database = ExcerciceDatabase.getInstance(application);
        excerciceDao = database.excerciceDao();
        allExcercice = excerciceDao.getAllExcercices();

    }

    public void insert(Excercice excercice){
        new InsertExcerciceAsyncTask(excerciceDao).execute(excercice);

    }

    public void update(Excercice excercice){
        new UpdateExcerciceAsyncTask(excerciceDao).execute(excercice);


    }

    public  void delete(Excercice excercice){
        new DeleteExcerciceAsyncTask(excerciceDao).execute(excercice);

    }

    public  void deleteAllExcercice(){
        new DeleteAllExcercicesAsyncTask(excerciceDao).execute();

    }

    public  LiveData<List<Excercice>> getAllExcercice(){
        return  allExcercice;
    }

    private static class InsertExcerciceAsyncTask extends AsyncTask<Excercice, Void, Void>{

        private ExcerciceDao excerciceDao;

        private InsertExcerciceAsyncTask(ExcerciceDao excerciceDao){
            this.excerciceDao = excerciceDao;

        }

        @Override
        protected Void doInBackground(Excercice... excercices) {

            excerciceDao.insert(excercices[0]);
            return null;
        }
    }
    private static class UpdateExcerciceAsyncTask extends AsyncTask<Excercice, Void, Void>{

        private ExcerciceDao excerciceDao;

        private UpdateExcerciceAsyncTask(ExcerciceDao excerciceDao){
            this.excerciceDao = excerciceDao;

        }

        @Override
        protected Void doInBackground(Excercice... excercices) {

            excerciceDao.update(excercices[0]);
            return null;
        }
    }
    private static class DeleteExcerciceAsyncTask extends AsyncTask<Excercice, Void, Void>{

        private ExcerciceDao excerciceDao;

        private DeleteExcerciceAsyncTask(ExcerciceDao excerciceDao){
            this.excerciceDao = excerciceDao;

        }

        @Override
        protected Void doInBackground(Excercice... excercices) {

            excerciceDao.delete(excercices[0]);
            return null;
        }
    }
    private static class DeleteAllExcercicesAsyncTask extends AsyncTask<Void, Void, Void>{

        private ExcerciceDao excerciceDao;

        private DeleteAllExcercicesAsyncTask(ExcerciceDao excerciceDao){
            this.excerciceDao = excerciceDao;

        }

        @Override
        protected Void doInBackground(Void... voids) {

            excerciceDao.deleteAllExcercice();
            return null;
        }
    }




}
