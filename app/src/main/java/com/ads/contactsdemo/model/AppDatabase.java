package com.ads.contactsdemo.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact.class }, exportSchema = false, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract ContactDao contactDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    // **
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    // **
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private ContactDao contactDao;

        private PopulateDbAsyncTask(AppDatabase db) {
            contactDao = db.contactDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /*CATEGORIES*/
            contactDao.insert(new Contact(1,"Darth Vader","+260977123456","jd@gmail.com"));
            contactDao.insert(new Contact(2,"Han Solo","+260977123456","jd@gmail.com"));
            contactDao.insert(new Contact(3,"Luke Skywalker","+260977654321","lskywalker@gmail.com"));
            contactDao.insert(new Contact(4,"Obi-Wan Kenobi","+260977654321","lskywalker@gmail.com"));

            return null;
        }
    }
}
