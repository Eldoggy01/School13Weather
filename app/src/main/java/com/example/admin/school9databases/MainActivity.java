package com.example.admin.school9databases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private DBManager mDBManager;
    private MyAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        init();
    }

    private void init() {
        mDBManager = new DBManager(this);
        mDBManager.upgradeDB();
        mRecyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        Log.d("KG", "Закончил init()");
    }

    private void initViews() {
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddNoteActivity.newIntent(MainActivity.this));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("KG", "Зашли в onResume()");
        System.out.println("Зашли в onResume()");
        myAdapter = new MyAdapter(mDBManager.getNotes());
        mRecyclerView.setAdapter(myAdapter);
    }
}