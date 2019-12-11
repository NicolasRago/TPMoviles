package com.example.tpmoviles;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Enunciado4Activity extends AppCompatActivity implements View.OnClickListener {

    Button buttonService;
    Button buttonIntentService;
    Button buttonBoundService;
    TextView iterationServiceTextView;
    TextView iterationIntentServiceTextView;
    TextView numRandomTextView;

    private static final int NUM_ITER = 4;

    IntentFilter mIntentFilter;

    BoundService mService;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enunciado_4);

        buttonService = findViewById(R.id.service);
        buttonIntentService = findViewById(R.id.intentservice);
        buttonBoundService = findViewById(R.id.BoundService);
        buttonService.setOnClickListener(this);
        buttonIntentService.setOnClickListener(this);
        buttonBoundService.setOnClickListener(this);

        iterationServiceTextView = findViewById(R.id.iterationServiceTextView);
        iterationIntentServiceTextView = findViewById(R.id.iterationIntentServiceTextView);
        numRandomTextView = findViewById(R.id.numRandomTextView);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadcastService.ACTION_ITERATION);
        mIntentFilter.addAction(BroadcastService.ACTION_FIN_ITERATION);
        mIntentFilter.addAction(BroadcastIntentService.ACTION_ITERATION);
        mIntentFilter.addAction(BroadcastIntentService.ACTION_FIN_ITERATION);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.service:
                Log.d(null,"Button Service");
                intent = new Intent(Enunciado4Activity.this,
                        BroadcastService.class);
                intent.putExtra("iteraciones", NUM_ITER);
                startService(intent);
                break;
            case R.id.intentservice:
                Log.d(null,"Button intentservice");
                intent = new Intent(Enunciado4Activity.this,
                        BroadcastIntentService.class);
                intent.putExtra("iteraciones", NUM_ITER);
                startService(intent);
                break;
            case R.id.BoundService:
                Log.d(null,"Button BoundService");
                int num = mService.getRandomNumber();
                numRandomTextView.setText(String.valueOf(num));
                break;
            default:
                break;
        }
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(null,"in BroadcastReceiver");
            if(intent.getAction().equals(BroadcastService.ACTION_ITERATION)) {
                int iReceiver = intent.getIntExtra("iteracion",0);
                iterationServiceTextView.setText(String.valueOf(iReceiver));
            } else if(intent.getAction().equals(BroadcastService.ACTION_FIN_ITERATION)) {
                Toast.makeText(Enunciado4Activity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                Intent stopIntent = new Intent(Enunciado4Activity.this,
                        BroadcastService.class);
                stopService(stopIntent);
            } else if(intent.getAction().equals(BroadcastIntentService.ACTION_ITERATION)) {
                int iReceiver = intent.getIntExtra("iteracion",0);
                iterationIntentServiceTextView.setText(String.valueOf(iReceiver));
            } else if(intent.getAction().equals(BroadcastService.ACTION_FIN_ITERATION)) {
                Toast.makeText(Enunciado4Activity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                Intent stopIntent = new Intent(Enunciado4Activity.this,
                        BroadcastIntentService.class);
                stopService(stopIntent);
            }
        }
    };

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }
}
