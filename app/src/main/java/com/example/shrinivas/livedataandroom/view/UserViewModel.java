package com.example.shrinivas.livedataandroom.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.shrinivas.livedataandroom.database.UserRepository;
import com.example.shrinivas.livedataandroom.dto.UserInfo;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository = new UserRepository(this.getApplication());

    private final Executor executor = Executors.newFixedThreadPool(2);



    public UserViewModel(Application application) {
        super(application);
    }

    public void addPerson(final UserInfo p) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                userRepository.addPerson(p);
            }
        });
    }

    //Since room DAO returns LiveData, it runs on background thread.
    public LiveData<List<UserInfo>> getAllPersons() {
        return userRepository.getAllPersons();
    }
}