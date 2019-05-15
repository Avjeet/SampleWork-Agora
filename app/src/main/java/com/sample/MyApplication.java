package com.sample;

import android.content.IntentFilter;

import androidx.multidex.MultiDexApplication;

import com.sample.models.TerminateReceiver;
import com.sample.models.WorkerThread;

public class MyApplication extends MultiDexApplication {

    static MyApplication myApplication;
    private WorkerThread mWorkerThread;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public synchronized void initWorkerThread() {
        if (mWorkerThread == null) {
            mWorkerThread = new WorkerThread(getApplicationContext());
            mWorkerThread.start();

            mWorkerThread.waitForReady();
        }
    }

    public synchronized WorkerThread getWorkerThread() {
        return mWorkerThread;
    }

    public synchronized void deInitWorkerThread() {
        mWorkerThread.exit();
      /*  try {
            mWorkerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        mWorkerThread.interrupt();
        mWorkerThread = null;
    }
}
