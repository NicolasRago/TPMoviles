package com.example.tpmoviles;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BroadcastIntentService extends IntentService {

    public static final String ACTION_ITERATION = "Iteracion_IntentService";
    public static final String ACTION_FIN_ITERATION = "Fin_Iteracion_IntentService";

    public BroadcastIntentService() {
        super("Intent Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(null, "In onHandleIntent");
        int num_iter = intent.getIntExtra("iteraciones",0);

        for (int i =0 ; i < num_iter; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_ITERATION);
            broadcastIntent.putExtra("iteracion", i);
            sendBroadcast(broadcastIntent);
        }
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_FIN_ITERATION);
        sendBroadcast(broadcastIntent);
    }
}
