package com.example.todo;

import com.example.todo.R;
import com.example.todo.ToDoList;
import com.example.todo.AppConst;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

	public class NotificationBroadCastReciever extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// Create the Notification.
			Bundle extras = intent.getExtras();
			// Fetch the message from the bundle.
			String message = extras.getString(AppConst.Extra_Message);
			// crate the notification.
			createNotification(context, message);
		}

	/**
	 * Crate notification with a specific message.
	 */
	public void createNotification(Context context, String message) {
	// get the notification manager service.
	NotificationManager nofiManager = (NotificationManager) context
	.getSystemService(Context.NOTIFICATION_SERVICE);
	
	// Prepare intent which is triggered if the
	// notification is selected
	Intent intent = new Intent(context, ToDoList.class);
	PendingIntent pIntent = PendingIntent.getActivity(context, 1, intent,
	PendingIntent.FLAG_ONE_SHOT);

	// Build notification
	Log.i("tpd", "Build notification" );
	Log.i("tpd", "Current message building: "+message );
	Notification noti = new Notification.Builder(context)
	.setContentTitle(message).setContentText(message)
	.setSmallIcon(R.drawable.ic_launcher).setContentIntent(pIntent)
	.build();
	
	// hide the notification after its selected
	noti.flags |= Notification.FLAG_AUTO_CANCEL;
	nofiManager.notify(0, noti);
	}
	

	
	
}