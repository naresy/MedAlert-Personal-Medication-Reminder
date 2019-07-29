package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public
class AlarmReceiver extends BroadcastReceiver {
    @Override
    public
    void onReceive(Context context, Intent intent) {
        Uri notification= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r= RingtoneManager.getRingtone(context.getApplicationContext(),notification);
        r.play();
        Vibrator v= (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
        int notificationId=intent.getIntExtra("notificationId",0);
        String message=intent.getStringExtra("todo");
        Intent mainintent=new Intent(context, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,mainintent,0);
        NotificationManager mynotification=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder=new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("It`s time")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        mynotification.notify(notificationId,builder.build());




    }


}
