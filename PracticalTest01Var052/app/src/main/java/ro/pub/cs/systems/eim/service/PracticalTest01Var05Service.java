package ro.pub.cs.systems.eim.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import ro.pub.cs.systems.eim.general.Constants;

public class PracticalTest01Var05Service extends Service {

    private ProcessingThread processingThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String sablon = intent.getStringExtra(Constants.TOTAL_PUSHES);
        Log.d("tagnu", "Am primit numerele, service " + sablon);
        processingThread = new ProcessingThread(this, sablon);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        processingThread.stopThread();
    }
}