package com.alarm.darya.alarmexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class AlarmReceiver extends BroadcastReceiver {
    static Intent alarmScreenTask;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Alarm runningAlarm = (Alarm)bundle.getSerializable("alarm");

        if (alarmScreenTask == null)
            alarmScreenTask = new Intent(context, AlarmScreenActivity.class);

        bundle = new Bundle();
        bundle.putSerializable("alarm", runningAlarm);
        alarmScreenTask.putExtras(bundle);
        context.startActivity(alarmScreenTask);
    }


























}
