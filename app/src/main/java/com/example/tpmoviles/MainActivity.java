package com.example.tpmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button enunciado1,enunciado2,enunciado3,enunciado4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enunciado3 = findViewById(R.id.enunciado3);
        enunciado4 = findViewById(R.id.enunciado4);
        enunciado3.setOnClickListener(this);
        enunciado4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.enunciado3:
                intent= new Intent (MainActivity.this, Enunciado3Activity.class);
                startActivity(intent);
                break;
            case R.id.enunciado4:
                intent= new Intent (MainActivity.this, Enunciado4Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
