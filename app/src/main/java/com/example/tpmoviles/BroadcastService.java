package com.example.tpmoviles;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class BroadcastService extends Service {

    public static final String ACTION_ITERATION = "Iteracion_Service";
    private final static String TAG = BroadcastService.class.getCanonicalName();

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, String.valueOf(msg.arg2));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_ITERATION);
            broadcastIntent.putExtra("iteracion", msg.arg2);
            sendBroadcast(broadcastIntent);
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread thread = new HandlerThread("ServiceStartArguments", android.os.Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service starting", Toast.LENGTH_SHORT).show();
        int iteration = intent.getIntExtra("iteracion", 0);
        final Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        msg.arg2 = iteration;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mServiceHandler.sendMessage(msg);
            }
        }).start();
        return START_STICKY;
    }
}
