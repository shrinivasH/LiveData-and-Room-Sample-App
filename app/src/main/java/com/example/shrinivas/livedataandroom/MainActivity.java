package com.example.shrinivas.livedataandroom;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shrinivas.livedataandroom.adapter.ListViewAdapter;
import com.example.shrinivas.livedataandroom.dto.UserInfo;
import com.example.shrinivas.livedataandroom.view.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mUserName, mUserEmail;
    private RecyclerView mUserList;
    private Button mSubmit;
    private UserViewModel userViewModel;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSubmit = (Button) findViewById(R.id.button);
        mUserEmail = (EditText) findViewById(R.id.userEmail);
        mUserName = (EditText) findViewById(R.id.userName);
        mUserList = (RecyclerView) findViewById(R.id.userRecycler);
        mUserList.setLayoutManager(new LinearLayoutManager(this));
        listViewAdapter = new ListViewAdapter(getApplicationContext());
        mUserList.setAdapter(listViewAdapter);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllPersons().observe(this, new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(@Nullable List<UserInfo> userInfos) {
                if (userInfos == null) {
                    Log.d("msg", "info is empty");
                } else {
                    Log.d("msg", "data found");
                    setRecyclerView(userInfos);
                }
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = callToValidation();
                if (result) {
                    String userName = mUserName.getText().toString().trim();
                    String userEmail = mUserEmail.getText().toString().trim();
                    UserInfo userInfo = new UserInfo(userName, userEmail);
                    userViewModel.addPerson(userInfo);
                }


            }
        });
    }

    private boolean callToValidation() {
        String userName = mUserName.getText().toString().trim();
        String userEmail = mUserEmail.getText().toString().trim();
        if (userName.isEmpty()) {
            mUserName.requestFocus();
            mUserName.setError(getResources().getString(R.string.user_name_err));
            return false;
        } else if (userEmail.isEmpty()) {
            mUserEmail.requestFocus();
            mUserEmail.setError(getResources().getString(R.string.email_id_err));
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            mUserEmail.requestFocus();
            mUserEmail.setError(getResources().getString(R.string.invalid_email_id));
            return false;
        }

        return true;
    }

    private void setRecyclerView(List<UserInfo> recycleList) {
        listViewAdapter.setUserList(recycleList);
        listViewAdapter.notifyDataSetChanged();

    }
}
