package com.alarm.darya.alarmexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker alarmTimePicker;
    TextView txtAlarmStatus;
    Button btnAlarmStart, btnAlarmStop;

    AlarmManager alarmManager;
    Calendar calendar;
    PendingIntent alarmPendingIntent;
    Intent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimePicker = (TimePicker)findViewById(R.id.timePickerAlarm);
        txtAlarmStatus = (TextView)findViewById(R.id.txtAlarmStatus);
        btnAlarmStart = (Button)findViewById(R.id.btnAlarmStart);
        btnAlarmStop = (Button)findViewById(R.id.btnAlarmStop);
        //context = this;
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(this, AlarmReceiver.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void onBtnStartClicked(View button) {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);//текущий час
        int minute = currentTime.get(Calendar.MINUTE);//текущая минута

        int selectedHour = alarmTimePicker.getHour();
        int selectedMinute = alarmTimePicker.getMinute();

        if (selectedHour > 12) //12-часовой формат времени
            selectedHour -= 12;

        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
        calendar.set(Calendar.MINUTE,  selectedMinute);

        alarmIntent.putExtra("isOn", true);
        alarmPendingIntent = PendingIntent
                .getBroadcast(MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),  alarmPendingIntent);
        updateStatus("Alarm on!");
    }
    //должен срабатоть через 2 минуты после запуска
        /*alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 2 * 60 * 1000,
                alarmPendingIntent);*/

    //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Seconds, AlarmManager.INTERVAL_DAY * 7, pendingIntent);

    void onBtnEndClicked(View button) {
        updateStatus("Alarm off!");
        alarmManager.cancel(alarmPendingIntent);
        alarmIntent.putExtra("isOn", false);
        sendBroadcast(alarmIntent);
    }

    void updateStatus(String status) {
        txtAlarmStatus.setText(status);
    }
}
