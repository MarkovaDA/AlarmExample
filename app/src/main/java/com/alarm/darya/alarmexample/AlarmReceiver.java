package com.alarm.darya.alarmexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isOn = intent.getBooleanExtra("isOn", false);
        Intent serviceIntent = new Intent(context, RingtonPlayingService.class);
        if (isOn) {
            context.startService(serviceIntent);
            Toast.makeText(context, "Wake up!!", Toast.LENGTH_SHORT).show();
        } else {
            context.stopService(serviceIntent);
            Toast.makeText(context, "Alarm cancelled!!", Toast.LENGTH_SHORT).show();
        }
    }


























}
