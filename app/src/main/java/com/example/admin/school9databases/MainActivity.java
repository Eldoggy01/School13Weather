package com.example.admin.school9databases;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    private void init(){
        mDBManager = new DBManager(this);
//        mDBManager.addNote("HAHAHA");
//        String s = mDBManager.getNote();
//        Log.d("GG", "s = " + s);
//        addButton.setText(s);
        mRecyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initViews() {
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter = new MyAdapter(mDBManager.getNotes());
        mRecyclerView.setAdapter(myAdapter);
    }
}