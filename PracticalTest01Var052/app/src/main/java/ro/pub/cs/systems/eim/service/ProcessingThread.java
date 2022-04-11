package ro.pub.cs.systems.eim.service;

import android.content.Context;
import android.content.Intent;

import ro.pub.cs.systems.eim.general.Constants;

public class ProcessingThread extends Thread{

    String a;//
    //    boolean isRunning = true;
    Context ctx;
    private boolean isRunning;
    public ProcessingThread(Context context, String a) {
        this.a = a;
        this.ctx = context;
    }

    @Override
    public void run() {
        while(isRunning) {
            Intent intent = new Intent();
            intent.putExtra(Constants.SERVICE_eXTRA, "Sablonul este " + a);
            intent.setAction(Constants.globalAction);
            ctx.sendBroadcast(intent);
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}