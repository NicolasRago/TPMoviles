package com.example.tpmoviles;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BroadcastIntentService extends IntentService {

    public BroadcastIntentService(String name) {
        super("Iterator Intent Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(null, "In onHandleIntent");
        final int num_iter = intent.getIntExtra("num_iter",0);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction(Enunciado4Activity.mBroadcastIntentServiceAction);
                broadcastIntent.putExtra("num_iter", num_iter);
                sendBroadcast(broadcastIntent);
            }
        }).start();
    }
}
