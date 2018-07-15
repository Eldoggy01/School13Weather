package com.example.admin.school9databases;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.admin.school9databases.dbUtils.DBManager;


public class MainActivity extends AppCompatActivity {
    public static String logTag = "GG";
    private BroadcastReceiver broadcastReceiver;
    private Button mUpdateButton;
    private DBManager mDBManager;
    private MyAdapter myAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final String INTENT_FILTER = "MyIntentService";
    private IntentFilter intentFilter = new IntentFilter(INTENT_FILTER);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        init();
    }

    private void init() {
        mDBManager = new DBManager(this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter(mDBManager.getWeatherForWeek());
        mRecyclerView.setAdapter(myAdapter);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getStringExtra("data").equals("success")) {
                    myAdapter = new MyAdapter(mDBManager.getWeatherForWeek());
                    mRecyclerView.setAdapter(myAdapter);
                    Toast.makeText(MainActivity.this, "Данные обновлены", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Не удалось получить данные", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void initViews() {
        mUpdateButton = findViewById(R.id.updateButton);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(MyIntentService.getIntent(MainActivity.this));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }
}