package com.example.treningappproject;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = Excercice.class, version = 1)
public abstract class ExcerciceDatabase extends RoomDatabase {

    private static ExcerciceDatabase instance;

    public abstract ExcerciceDao excerciceDao();

    public static  synchronized  ExcerciceDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ExcerciceDatabase.class, "excercice_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private ExcerciceDao excerciceDao;

        private PopulateDbAsyncTask(ExcerciceDatabase db){
            excerciceDao = db.excerciceDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            excerciceDao.insert(new Excercice("Poniedziałek","Pullups", 3,6,1 ));
            excerciceDao.insert(new Excercice("Wtorek","Pullups", 3,6,2 ));

    return null;
        }
    }

}
