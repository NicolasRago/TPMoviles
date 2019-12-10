package com.example.tpmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Enunciado1Activity extends AppCompatActivity {

    private TextView numberDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enunciado1);
        numberDisplayed = findViewById(R.id.number);
    }

    public void increment(View view) {
        numberDisplayed.setText(new Integer(Integer.parseInt(numberDisplayed.getText().toString()) + 1).toString());
        findViewById(R.id.button_restart).setVisibility(View.VISIBLE);
    }

    public void restart(View view) {
        numberDisplayed.setText("0");
        findViewById(R.id.button_restart).setVisibility(View.INVISIBLE);
    }
}
