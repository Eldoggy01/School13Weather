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

public class EditNoteActivity extends AppCompatActivity {
    private TextView mId;
    private EditText mNote;
    private Button mSave;
    private DBManager mDbManager;
//Нужно сюда передавать значения для этих EditText в виде текущих значений того холдера, с которого попали сюда.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
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
                Log.d("KG", "Кликнули Save");
                mDbManager.updateById(mId.getText().toString(),mNote.getText().toString());
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