package com.example.shrinivas.livedataandroom.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.shrinivas.livedataandroom.dto.UserInfo;

import java.util.List;

public class UserRepository {
    private final UserDAO userDAO;

    public UserRepository(Context context) {
        userDAO = DatabaseCreator.getPersonDatabase(context).userDatabase();
    }

    public void addPerson(UserInfo p) {
        long rec = userDAO.insertUser(p);
        Log.d("db insert ", "added " + rec);
    }


    public LiveData<List<UserInfo>> getAllPersons() {
        LiveData<List<UserInfo>> test = userDAO.getData();
        Log.d("TAG", "TAG");
        return userDAO.getData();
    }

}