package com.alarm.darya.alarmexample;


import android.net.Uri;

import java.io.Serializable;

//простенькая модель будильника
public class Alarm implements Serializable {
    public String title;
    public int timeHour;
    public int timeMinute;
    public boolean enable;
    //public Uri alarmTone;
}
