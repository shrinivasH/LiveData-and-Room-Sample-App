package com.example.shrinivas.livedataandroom.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseCreator {
    private static UserDataBaseInfo userDatabase;
    private static final Object LOCK = new Object();

    public synchronized static UserDataBaseInfo getPersonDatabase(Context context) {
        if (userDatabase == null) {
            synchronized (LOCK) {
                if (userDatabase == null) {
                    userDatabase = Room.databaseBuilder(context,
                            UserDataBaseInfo.class, "movie db").build();
                }
            }
        }
        return userDatabase;
    }

}