package com.example.tpmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean start;
    private int period;
    private int count;

    private Button BStart,BStop,BRestart;
    private EditText EditViewSleep;
    private TextView TextViewContador;

    private String sharedPrefFile = "com.example.android.countsharedprefs";

    StartCountTask aux;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0;
        BStart = findViewById(R.id.BStart);
        BStart.setOnClickListener(this);
        BStop = findViewById(R.id.BStop);
        BStop.setOnClickListener(this);
        BRestart = findViewById(R.id.BRestart);
        BRestart.setOnClickListener(this);
        TextViewContador = findViewById(R.id.Contador);
        EditViewSleep = findViewById(R.id.Sleep);
        period = Integer.parseInt(EditViewSleep.getText().toString());

        count = getSharedPreferences(sharedPrefFile, MODE_PRIVATE).getInt("count",0);
        period = getSharedPreferences(sharedPrefFile, MODE_PRIVATE).getInt("period",1);
        TextViewContador.setText(String.valueOf(count));
        EditViewSleep.setText(String.valueOf(period));

        if (count == 0){
            BRestart.setEnabled(false);
            BStop.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BStart:
                start = true;
                period = Integer.parseInt(EditViewSleep.getText().toString());
                aux = new StartCountTask();
                aux.execute(count);

                BRestart.setEnabled(true);
                BStart.setEnabled(false);
                BStop.setEnabled(true);
                break;
            case R.id.BStop:
                aux.cancel(true);
                BStart.setEnabled(true);
                break;
            case R.id.BRestart:
                count = 0;
                TextViewContador.setText(String.valueOf(count));
                if (aux != null)
                    aux.cancel(true);

                BStart.setEnabled(true);
                BRestart.setEnabled(false);
                BStop.setEnabled(false);
                break;
            default:
                break;
        }
    }

    private class StartCountTask extends AsyncTask<Integer, Integer, Integer> {
        protected Integer doInBackground(Integer... integers) {
            count = integers[0];
            while (start){
                try {
                    Thread.sleep(period);
                    count++;
                    Log.e("CONTADOR", String.valueOf(count));
                    publishProgress((int) count);
                } catch (InterruptedException e) {
                }
                if(isCancelled())
                    start = false;
            }
            return count;
        }

        protected void onProgressUpdate(Integer... progress) {
            Log.e("ACTUALIZAR VIEW","NUEVO PROGRESS: "+progress[0]);
            TextViewContador.setText(progress[0].toString());
        }

        protected void onPostExecute(Integer result) {
            Log.e("TAG","Tarea pausada"+result);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = getSharedPreferences(sharedPrefFile, 0).edit();
        preferencesEditor.putInt("count", count);
        preferencesEditor.putInt("period", period);
        preferencesEditor.apply();
    }
}
