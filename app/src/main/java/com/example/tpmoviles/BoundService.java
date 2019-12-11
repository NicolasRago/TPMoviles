package com.example.tpmoviles;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class BoundService extends Service {
    private final IBinder binder = new LocalBinder();
    private final static int LIMIT_RANDOM = 100;

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }

    public int getRandomNumber(){
        Log.d(null,"in getRandomNumber");
        return new Random().nextInt(LIMIT_RANDOM);
    }
}
