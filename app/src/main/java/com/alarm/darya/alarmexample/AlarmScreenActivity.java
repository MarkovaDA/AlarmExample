package com.alarm.darya.alarmexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlarmScreenActivity extends AppCompatActivity {
    Intent serviceIntent;
    Intent messageIntent;
    TextView txtAlarmTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        txtAlarmTitle = (TextView)findViewById(R.id.txtAlarmTitle);

        setContentView(R.layout.activity_alarm_screen);
        serviceIntent = new Intent(this, RingtonPlayingService.class);

        messageIntent = new Intent("com.alarm.darya.alarmexample.ALARM_ACTION");
        startService(serviceIntent);
    }

    void onClickBtnWakeUp(View btn) {
        messageIntent.putExtra("ALARM_ACTION", 0);
        stopService(serviceIntent);
        sendBroadcast(messageIntent);
        //finish();
    }

    void onClickBtnDelay(View btn) {
        messageIntent.putExtra("ALARM_ACTION", 1);
        stopService(serviceIntent);
        sendBroadcast(messageIntent);
        //finish();
    }
}
