package com.dgpays.myapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dgpays.myapplication.model.UserResource;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    UserResource getAll();

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(UserResource... users);

    @Delete
    void delete(UserResource user);
}
