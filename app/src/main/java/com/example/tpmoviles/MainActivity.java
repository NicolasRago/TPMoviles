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

        enunciado1 = findViewById(R.id.enunciado1);
        enunciado2 = findViewById(R.id.enunciado2);
        enunciado3 = findViewById(R.id.enunciado3);
        enunciado4 = findViewById(R.id.enunciado4);
        enunciado1.setOnClickListener(this);
        enunciado2.setOnClickListener(this);
        enunciado3.setOnClickListener(this);
        enunciado4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.enunciado1:
                intent = new Intent (MainActivity.this, Enunciado1Activity.class);
                startActivity(intent);
                break;
            case R.id.enunciado2:
                intent = new Intent (MainActivity.this, Enunciado2Activity.class);
                startActivity(intent);
                break;
            case R.id.enunciado3:
                intent = new Intent (MainActivity.this, Enunciado3Activity.class);
                startActivity(intent);
                break;
            case R.id.enunciado4:
                intent = new Intent (MainActivity.this, Enunciado4Activity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
