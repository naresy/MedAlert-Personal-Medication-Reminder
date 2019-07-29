package com.example.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public
class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int notification=1;


    @Override
    protected
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.set).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
    }

    @Override
    public
    void onClick(View v) {
        EditText editText=findViewById(R.id.message);
        TimePicker timePicker=findViewById(R.id.time_set);
        Intent intent=new Intent(MainActivity.this,AlarmReceiver.class);
        intent.putExtra("notificationId",notification);
        intent.putExtra("todo",editText.getText().toString());
        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        switch(v.getId())
        {
            case R.id.set:
         int hour=timePicker.getCurrentHour();
         int minute=timePicker.getCurrentMinute();
                Calendar starttime=Calendar.getInstance();
                starttime.set(Calendar.HOUR_OF_DAY,hour);
                starttime.set(Calendar.MINUTE,minute);
                starttime.set(Calendar.SECOND,0);
                long alarmstarttime=starttime.getTimeInMillis();
                alarmManager.set(AlarmManager.RTC_WAKEUP,alarmstarttime,pendingIntent);
                Toast.makeText(this,"Done",Toast.LENGTH_LONG).show();
                break;
            case R.id.cancel:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this,"Canceled",Toast.LENGTH_LONG).show();
                break;


        }

ringtone();

    }
    private
    void ringtone() {

        Uri notification= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r= RingtoneManager.getRingtone(getApplicationContext(),notification);
        r.play();
        Vibrator v= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
    }
}
