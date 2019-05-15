package com.sample.Agora.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.sample.R;
import com.sample.Agora.ConstantApp;
import com.sample.models.TerminateReceiver;
import com.sample.ui.LiveRoomActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Your name on 13-05-2019.
 */
public class NotificationUtil {

    public static void createPersistentNotification(Context context, String channelName, int role, int mUid) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String description = context.getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(mUid + "_channel", channelName, importance);
            channel.setDescription(description);
            channel.setSound(null, null);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(context, LiveRoomActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(ConstantApp.ACTION_KEY_U_ID, mUid);
        intent.putExtra(ConstantApp.ACTION_KEY_CROLE, role);
        intent.putExtra("startedFromNotification", true);

        Intent snoozeIntent = new Intent(context, TerminateReceiver.class);
        snoozeIntent.setAction(context.getString(R.string.action_terminate));
        snoozeIntent.putExtra(ConstantApp.ACTION_KEY_U_ID, mUid);
        snoozeIntent.putExtra(ConstantApp.ACTION_KEY_CROLE, role);

        PendingIntent stopBroadcast = PendingIntent.getBroadcast(context, 0, snoozeIntent, 0);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, mUid + "_channel")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Broadcast Running")
                .setContentText("Commentary active")
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setOngoing(true)
                .setSound(null)
                .addAction(R.drawable.ic_stop, context.getString(R.string.stop), stopBroadcast)
                .setPriority(NotificationCompat.PRIORITY_MAX);
// notificationId is a unique int for each notification that you must define
        notificationManager.notify(mUid, builder.build());
    }
}
