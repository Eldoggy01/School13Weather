package com.example.admin.school9databases;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.school9databases.dbUtils.DBManager;

public class EditNoteActivity extends AppCompatActivity {
    private TextView mId;
    private EditText mNote;
    private Button mSave;
    private DBManager mDbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        mDbManager = new DBManager(EditNoteActivity.this);
        initViews();

    }

    private void initViews() {
        mId = findViewById(R.id.idTextView);
        mId.setText(getIntent().getStringExtra("id"));
        mNote = findViewById(R.id.noteEdit);
        mNote.setText(getIntent().getStringExtra("note"));
        mSave = findViewById(R.id.saveButton);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.logTag, "Кликнули Save");
//                mDbManager.updateNoteInDB(mId.getText().toString(),mNote.getText().toString());
                Toast.makeText(EditNoteActivity.this, "Заметка сохранена!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static Intent newIntent(Context context, String id, String note){
       Intent intent = newIntent(context);
       intent.putExtra("id",id);
       intent.putExtra("note",note);
       return intent;
    }
    private static Intent newIntent(Context context){
        Intent intent = new Intent(context, EditNoteActivity.class);
        return intent;
    }
}