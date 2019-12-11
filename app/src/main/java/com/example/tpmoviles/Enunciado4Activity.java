package com.example.tpmoviles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Enunciado4Activity extends AppCompatActivity implements View.OnClickListener {

    Button buttonService;
    Button buttonIntentService;
    TextView iterationServiceTextView;
    TextView iterationIntentServiceTextView;

    public static final String mBroadcastServiceAction = "com.truiton.broadcast.integer";
    public static final String mBroadcastIntentServiceAction = "com.truiton.broadcast.integer";

    private int num_iter;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enunciado_4);

        buttonService = findViewById(R.id.service);
        buttonIntentService = findViewById(R.id.intentservice);
        buttonService.setOnClickListener(this);
        buttonIntentService.setOnClickListener(this);

        iterationServiceTextView = findViewById(R.id.iterationServiceTextView);
        iterationIntentServiceTextView = findViewById(R.id.iterationIntentServiceTextView);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(mBroadcastServiceAction);
        mIntentFilter.addAction(mBroadcastIntentServiceAction);

        num_iter = 15;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.service:
                intent = new Intent(Enunciado4Activity.this,
                        BroadcastService.class);
                intent.putExtra("num_iter", num_iter);
                startService(intent);
                break;
            case R.id.intentservice:
                intent = new Intent(Enunciado4Activity.this,
                        BroadcastIntentService.class);
                intent.putExtra("num_iter", num_iter);
                startService(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(null, "In onReceive");
            if (intent.getAction().equals(mBroadcastServiceAction)) {
                int iReceiver = intent.getIntExtra("num_iter",0);
                iterationServiceTextView.setText(String.valueOf(iReceiver));
                Intent stopIntent = new Intent(Enunciado4Activity.this,
                        BroadcastService.class);
                stopService(stopIntent);}
            else if(intent.getAction().equals(mBroadcastIntentServiceAction)) {
                int iReceiver = intent.getIntExtra("num_iter",0);
                iterationIntentServiceTextView.setText(String.valueOf(iReceiver));
                Intent stopIntent = new Intent(Enunciado4Activity.this,
                        BroadcastIntentService.class);
                stopService(stopIntent);}
        }
    };
}
