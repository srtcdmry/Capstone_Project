package com.dgpays.myapplication.Utils;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

import com.dgpays.myapplication.database.AppDatabase;
import com.dgpays.myapplication.model.UserResource;

public class DatabaseInitializer {
    public static void populateAsync(@NonNull final AppDatabase db, UserResource user) {
        PopulateDbAsync task = new PopulateDbAsync(db, user);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db, UserResource user) {
        populateWithTestData2(db);
    }

    private static UserResource addUser(final AppDatabase db, UserResource user) {
        db.userDao().insertAll(user);
        return user;
    }

    private static void populateWithTestData(AppDatabase db, UserResource user) {
        addUser(db, user);
    }

    public static UserResource populateWithTestData2(AppDatabase db) {
        UserResource user = db.userDao().getAll();

        return user;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        UserResource user1;

        PopulateDbAsync(AppDatabase db, UserResource user) {
            mDb = db;
            user1 = user;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb, user1);
            return null;
        }

    }
}