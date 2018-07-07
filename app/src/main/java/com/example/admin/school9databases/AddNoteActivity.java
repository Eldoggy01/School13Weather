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
    private Button buttonAdd;
    private EditText editText;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();

    }

    private void initViews() {
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KG", "Зашли в onClick");
                dbManager = new DBManager(AddNoteActivity.this);
                dbManager.addNote(editText.getText().toString());
                Toast.makeText(AddNoteActivity.this, "Добавлена заметка: " + editText.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        editText = findViewById(R.id.noteToAddEditText);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        return intent;
    }

}