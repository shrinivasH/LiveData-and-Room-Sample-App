package com.example.shrinivas.livedataandroom.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.shrinivas.livedataandroom.dto.UserInfo;

@Database(entities = {UserInfo.class}, version = 1)
public abstract class UserDataBaseInfo extends RoomDatabase {
    public abstract UserDAO userDatabase();
}