package com.alarm.darya.alarmexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker alarmTimePicker;
    TextView txtAlarmStatus;
    Button btnAlarmStart, btnAlarmStop;

    AlarmControlManager alarmControlManager;
    BroadcastReceiver messageReceiver;

    //AlarmManager alarmManager;
    //Calendar calendar;
    //PendingIntent alarmPendingIntent;
    //Intent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimePicker = (TimePicker)findViewById(R.id.timePickerAlarm);
        txtAlarmStatus = (TextView)findViewById(R.id.txtAlarmStatus);
        btnAlarmStart = (Button)findViewById(R.id.btnAlarmStart);
        btnAlarmStop = (Button)findViewById(R.id.btnAlarmStop);

        alarmControlManager = new AlarmControlManager(this);
        IntentFilter intentFilter =
                new IntentFilter("com.alarm.darya.alarmexample.ALARM_ACTION");
        messageReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                int alarmAction = intent.getIntExtra("ALARM_ACTION", 0);
                //отложить будильник еще на 5 минут
                if (alarmAction == 0) {
                    Toast.makeText(context, "Будильник отложен на 5 минут",
                            Toast.LENGTH_SHORT).show();
                    alarmControlManager.delayAlarm(index);
                }
            }
        };
        registerReceiver(messageReceiver, intentFilter);
        //calendar = Calendar.getInstance();
        //alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        //alarmIntent = new Intent(this, AlarmReceiver.class);
    }

    int index = -1;

    @RequiresApi(api = Build.VERSION_CODES.M)
    void onBtnStartClicked(View button) {
        alarmTimePicker.setIs24HourView(true);
        int selectedHour = alarmTimePicker.getHour();
        int selectedMinute = alarmTimePicker.getMinute();

        Alarm alarm = new Alarm();
        alarm.title = "Тестовый будильник";
        alarm.timeHour = selectedHour;
        alarm.timeMinute = selectedMinute;

        alarmControlManager.createAlarm(alarm);
        alarmControlManager.setOnAlarm(++index);

        /*Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
        calendar.set(Calendar.MINUTE, selectedMinute);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().get(Calendar.DAY_OF_WEEK));*/

        /*alarmIntent.putExtra("isOn", true);
        alarmPendingIntent = PendingIntent
                .getBroadcast(MainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long timeInMillis = calendar.getTimeInMillis();
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeInMillis, alarmPendingIntent);*/
        updateStatus("Alarm on!");
    }


    void onBtnEndClicked(View button) {
        updateStatus("Alarm off!");
        alarmControlManager.cancelAlarm(index);
        //alarmManager.cancel(alarmPendingIntent);
        //alarmIntent.putExtra("isOn", false);
        //sendBroadcast(alarmIntent);
    }

    void updateStatus(String status) {
        txtAlarmStatus.setText(status);
    }
}
