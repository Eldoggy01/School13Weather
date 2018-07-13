package com.example.admin.school9databases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admin.school9databases.dbUtils.DBManager;


public class MainActivity extends AppCompatActivity {
    public static String logTag = "KG";
    private Button mAddButton;
    private MyIntentService service;
    private Button mEditStyleButton;
    private DBManager mDBManager;
    private MyAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        try {
            init();
        } catch (InterruptedException e) {

        }
    }

    private void init() throws InterruptedException {
        mDBManager = new DBManager(this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        startService(MyIntentService.getIntent(this));
        Thread.sleep(3000);
    }

    private void initViews() {
        mAddButton = findViewById(R.id.addButton);
        mEditStyleButton = findViewById(R.id.editStyleButton);
        mEditStyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter = new MyAdapter(mDBManager.getWeatherForWeek());
                mRecyclerView.setAdapter(myAdapter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("GGGG",mDBManager.getWeatherForWeek().get(0).toString());
        myAdapter = new MyAdapter(mDBManager.getWeatherForWeek());
        mRecyclerView.setAdapter(myAdapter);
    }
}