package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    int noteId = -1;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editText = (EditText) findViewById(R.id.editTextTextMultiLine);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);
        if(noteId != -1){
            Note note = MainActivity2.notes.get(noteId);
            String noteContent = note.getContent();
            editText.setText(noteContent);
        }
    }

    public void saveMethod(View view){
        editText = (EditText) findViewById(R.id.editTextTextMultiLine);
        String content = editText.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE,null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

//        ArrayList<Note> notes = dbHelper.readNotes(username);

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteId == -1){
            title = "NOTE_" + (MainActivity2.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        }else{
            title = "NOTE_" + (noteId + 1);
            dbHelper.updateNote(username, title, content, date);
        }

        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}