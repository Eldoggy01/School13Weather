package com.example.admin.school9databases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    public static String logTag = "KG";
    private Button mAddButton;
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
        init();
    }

    private void init() {
        mDBManager = new DBManager(this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initViews() {
        mAddButton = findViewById(R.id.addButton);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddNoteActivity.newIntent(MainActivity.this));
            }
        });
        mEditStyleButton = findViewById(R.id.editStyleButton);
        mEditStyleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(EditStyleActivity.newIntent(MainActivity.this));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter = new MyAdapter(mDBManager.getNotes(),mDBManager.getStyle());
        mRecyclerView.setAdapter(myAdapter);
    }
}