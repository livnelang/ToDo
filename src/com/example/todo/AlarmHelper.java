package com.example.todo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmHelper {
	public void setAlarm(Context c, Alarm alarm) {
		Log.i("tpd", "inside setAlarm()" );
		if (c == null || alarm == null)
			return;
		// create the intent, with the receiver that should handle the alarm.
		Intent activityIntent = new Intent(c.getApplicationContext(), alarm.getReciever());
		// set the action.
		activityIntent.setAction(alarm.getAction());
		// set the extras.
		activityIntent.putExtras(alarm.getExtras());
		PendingIntent pendingInent = PendingIntent.getBroadcast(c,
				alarm.getId(), activityIntent, Intent.FLAG_ACTIVITY_NO_HISTORY);
		AlarmManager alarmManager = (AlarmManager) c
				.getSystemService(Context.ALARM_SERVICE);
		if (alarm.getIntervalMillis() > 0)
			alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
					alarm.getTriggerAtMillis(), alarm.getIntervalMillis(),
					pendingInent);
		else {
			alarmManager.set(AlarmManager.RTC_WAKEUP,
					alarm.getTriggerAtMillis(), pendingInent);
		}
	}

	public void cancelAlarm() {
		//Now you will have to deal with it.

	}

}