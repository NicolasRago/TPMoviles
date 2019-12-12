package com.example.tpmoviles;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastIntentService extends IntentService {

    public static final String ACTION_ITERATION = "Iteracion_IntentService";

    public BroadcastIntentService() {
        super("Intent Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if ( intent != null ) {
            int iteration = intent.getIntExtra("iteracion", 0);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_ITERATION);
            broadcastIntent.putExtra("iteracion", iteration);
            sendBroadcast(broadcastIntent);
        }
    }
}
