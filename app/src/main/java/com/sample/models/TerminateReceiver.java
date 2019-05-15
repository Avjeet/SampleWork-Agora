package com.sample.models;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sample.Agora.ConstantApp;
import com.sample.MyApplication;

public class TerminateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ((MyApplication) context.getApplicationContext()).getWorkerThread().getRtcEngine().leaveChannel();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(intent.getIntExtra(ConstantApp.ACTION_KEY_U_ID, 0));
        Intent i = new Intent(ConstantApp.INTENT_FILTER_STOP);
        context.sendBroadcast(i);
    }
}
