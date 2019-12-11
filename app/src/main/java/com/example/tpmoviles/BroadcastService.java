package com.example.tpmoviles;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BroadcastService extends Service {

    public static final String ACTION_ITERATION = "Iteracion_Service";
    public static final String ACTION_FIN_ITERATION = "Fin_Iteracion_Service";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Log.i(null, "In onStartCommand");
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
        return START_REDELIVER_INTENT;
    }
}
