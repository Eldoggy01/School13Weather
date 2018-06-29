package com.example.admin.school9databases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tw;
    DBManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tw = findViewById(R.id.textView1);
        Log.d("GG","ggggg");
        manager = new DBManager(this);
        manager.addNote("HOHOHOHO");
        tw.setText(manager.getNote());

    }
}
