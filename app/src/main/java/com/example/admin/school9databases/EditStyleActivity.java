package com.example.admin.school9databases;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


public class EditStyleActivity extends AppCompatActivity {
    private Button mDefaultBackgroundButton;
    private Button mRedBackgroundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_style);
        initViews();

    }

    private void initViews() {
        mDefaultBackgroundButton = findViewById(R.id.defaultColor);
        mDefaultBackgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter.setStyle(Color.TRANSPARENT);
            }
        });
        mRedBackgroundButton = findViewById(R.id.redColor);
        mRedBackgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter.setStyle(Color.RED);
            }
        });
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, EditStyleActivity.class);
        return intent;
    }

}