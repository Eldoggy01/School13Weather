package com.example.admin.school9databases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    private Button mButtonAdd;
    private EditText mNote;
    private DBManager mDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();

    }

    private void initViews() {
        mButtonAdd = findViewById(R.id.buttonAdd);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.logTag, "Зашли в onClick");
                mDbManager = new DBManager(AddNoteActivity.this);
                mDbManager.addNote(mNote.getText().toString());
                Toast.makeText(AddNoteActivity.this, "Добавлена заметка: " + mNote.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        mNote = findViewById(R.id.noteToAddEditText);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }

}