package com.alarm.darya.alarmexample;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


public class AlarmEnvironment {
    Intent alarmIntent;
    PendingIntent alarmPendingIntent;
    Alarm entityAlarm;

    public AlarmEnvironment(Context context, Alarm alarm) {
        entityAlarm = alarm;
        alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmPendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
