package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){
        EditText editText = (EditText)findViewById(R.id.editTextTextPersonName);
        String s = editText.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", s).apply();
        goToActivity2(s);
    }

    public void goToActivity2(String s){
        Intent intent =new Intent(this, MainActivity2.class);
        intent.putExtra("username", s);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5", Context.MODE_PRIVATE);

        if(!sharedPreferences.getString("username", "").equals("")){
            Intent intent =new Intent(this, MainActivity2.class);
            startActivity(intent);
        }else {
            setContentView(R.layout.activity_main);
        }
    }
}