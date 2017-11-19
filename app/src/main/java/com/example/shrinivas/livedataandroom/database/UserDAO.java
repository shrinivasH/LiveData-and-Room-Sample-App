package com.example.shrinivas.livedataandroom.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.shrinivas.livedataandroom.dto.UserInfo;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public long insertUser(UserInfo userInfo);

    @Query("SELECT * FROM UserInfo")
    public LiveData<List<UserInfo>> getData();

    @Query("SELECT * FROM UserInfo")
    public List<UserInfo> getDataNon();
}