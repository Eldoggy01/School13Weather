package com.example.admin.school9databases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
    DBManager mDBManager;
    DbHelper mDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mDBManager = new DBManager(this);
        mDBManager.addNote("HAHAHA");
        String s = mDBManager.getNote();
        Log.d("GG","s = " + s);
        textView.setText(s);
    }

    private void initViews(){
        textView = findViewById(R.id.textView);
    }
}
