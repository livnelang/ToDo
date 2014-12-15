package com.example.todo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class MainController {
	private Context context;
	private AlarmHelper alarmHelper;

	public MainController(Context context) {
		this.context = context;
		alarmHelper = new AlarmHelper();
	}
	
	public void CreateAlarm(String message,Date when)
	{
		Log.i("tpd", "inside CreateAlarm()" );
		Alarm alarm  =  new Alarm();
		alarm.setId(1234);
		Bundle extras = new Bundle();
		Log.i("tpd", "coming message: "+message );
		extras.putString(AppConst.Extra_Message, message);
		alarm.setExtras(extras);
		alarm.setAction(AppConst.ACTION_ALARM);
		alarm.setIntervalMillis(0);
		alarm.setReciever(NotificationBroadCastReciever.class);
		alarm.setTriggerAtMillis(when.getTime());
		alarmHelper.setAlarm(context, alarm);
	}
	
	/*public TaskBaseAdapter getAdapter(TaskBaseAdapter adp) {
		if (adp!=null) {
			return adp;
		}
		return null;
	}	*/
	
	
	/**
	 * Activates & instantiating new AsyncTask
	 * @throws MalformedURLException 
	 */
	public String getWebTask(TaskBaseAdapter adpt) throws MalformedURLException
	{
		String coming_task  = null;
		GetFromWebTask webtask = new GetFromWebTask(this.context,adpt);
		URL url = new URL("http://1-dot-serious-app-768.appspot.com/taskdispatcher/dispatch");
		try {
			coming_task = webtask.execute(new URL[] {url}).get();
		} 	catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coming_task;
		
		
		
		
	
	}
	
	
}