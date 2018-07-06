package com.example.admin.school9databases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditNoteActivity extends AppCompatActivity {
    private TextView mId;
    private EditText mNote;
    private Button mSave;
    private DBManager mDbManager;
//Нужно сюда передавать значения для этих EditText в виде текущих значений того холдера, с которого попали сюда.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();

    }

    private void initViews() {
        mDbManager = new DBManager(EditNoteActivity.this);
        mId = findViewById(R.id.idTextView);
        mId.setText(getIntent().getStringExtra("id"));
        mNote = findViewById(R.id.noteEdit);
        mNote.setText(getIntent().getStringExtra("note"));
        mSave = findViewById(R.id.saveButton);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Реализовать запись в БД новых значений
                mDbManager.updateById(mId.getText().toString(),mNote.getText().toString());
            }
        });
    }
}