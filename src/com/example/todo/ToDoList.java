package com.example.todo;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.R.bool;
import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.*;

import com.example.todo.R;

public class ToDoList extends ActionBarActivity 
{
	static boolean alreadyLaunched = false;
	private ListView mListView;	
	private List<String> TodoItems;
	private TaskBaseAdapter adapter;
	private DBHelper db;
	private Context context;	
	private TaskDAO dao; 					// reference for data access object -> our tasks in SQLite
	private MainController controller;
	
	/**
	 * Function Loads When Activity Create
	 */
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list);
		// Get instance for TaskDAO
		dao = TaskDAO.getInstatnce(getBaseContext());
		// initialize our controller
		controller = new MainController(this); 
		
		try{
				// Setting Our Item List 
				TodoItems  = new ArrayList<String>();
				//Reads from DataBase only if its not empty
				if(dao.getAllTasks()!=null) { TodoItems =  dao.getAllTasks(); };
				//Setting up our ListView
				mListView = (ListView) findViewById( R.id.mListView ); 
				//Setting up our Adapter
				adapter = new TaskBaseAdapter(this, TodoItems);
				//set our custom adapter with parameters : 1.Context | 2.the tasks that dao got from our DB 
				mListView.setAdapter(adapter);
			}

	catch(SQLException e)
	{
		e.printStackTrace();
		Log.i("query4","cant find table");
	}
	

	catch(Exception e)
	{
		e.printStackTrace();
		Log.i("query4","cant find table");
	}

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) 
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Leads To A new Task Class Activity
	 * 
	 * @param v
	 */
	public void Start_new_Task_Activity(View v)  
	{
		Intent intent = new Intent(this, CreateTaskActivity.class);
		startActivityForResult(intent, 2);// Activity is started with requestCode 2

	}
	
	
	/**
	 * When user clicked the alarm icon
	 * 
	 * @param v
	 */
	public void setTime(View v)  
	{
		
		ListView lv = mListView;
		int position = lv.getPositionForView(v);
		final String clicked_task = TodoItems.get(position);
		
	//	TodoItems.get((int) position);
	//	Log.i("selected",Long.toString(position));
		// first, well get the task string from the view
	//	TextView tv = (TextView) v.findViewById(R.id.task_view);
		//String message = tv.getText().toString();
		//final String message = "ASdsad";
		if(clicked_task==null) {
			
	
		Log.i("selected","null textview");
		}
			else {
				Log.i("selected",clicked_task);
			}
		
		// Current Time Calendar  
		Calendar mcurrentTime = Calendar.getInstance();
		int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
		int minute = mcurrentTime.get(Calendar.MINUTE); 
		
		// A Helper Calendar
		final Calendar c = Calendar.getInstance();

		TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
		 
	        int callCount = 0;   //To track number of calls to onTimeSet()
	       
		            @Override
		            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) 
		            {
		            	 Log.i("tpd", "entering tpd");
		            	 Log.i("tpd","callcount: "+ Integer.toString(callCount));
		            	if(callCount == 0)    // On second call
		                {
		            		c.setTime(new Date());
		            		c.set(Calendar.HOUR_OF_DAY, selectedHour);
		            		c.set(Calendar.MINUTE, selectedMinute);  
		            		 controller.CreateAlarm(clicked_task, c.getTime()); 
		                    Log.i("tpd", "after controller");
		                    //finish();
		                }
		            	
		            	Log.i("tpd", Integer.toString(callCount)); 
		            	callCount++;    // Incrementing call count.
		            	
		            	
		            }
		        }, hour, minute, false);
		tpd.show();
		

	}

	/**
	 * Loads on task done button 
	 * will delete task from DB & ListView
	 * @param view
	 */
	public void onItemClickRemove(View view)
	{
		ListView lv = mListView;
		int position = lv.getPositionForView(view);
		
		//Log.i("click_rem","entering remove_click");
		String clicked_task = TodoItems.get(position);
	//	Log.i("click_rem"," second entering remove_click");
		//if the task was removed from database - then well update the cache
		if (dao.removeTask(clicked_task))
		{
			// update the cash
			TodoItems.remove(clicked_task);
			Log.i("click_rem",TodoItems.toString());
			// update the adapter
			//adapter.UpdateDataSource(TodoItems);
			Log.i("click_rem"," 4 entering remove_click");
			// notify all list items ( as observers) that data has changed
			adapter.notifyDataSetChanged();
		}
	}
	
	
	
	/**
	 * Gives us Random task from the web 
	 * and will add it to the ListView
	 * @param view
	 * @throws MalformedURLException 
	 */
	public void onRandomTaskClick(View view) throws MalformedURLException
	{	
		String random_task = null;
		random_task = controller.getWebTask(adapter);
		if(random_task != null) {
			TodoItems.add(random_task);
			adapter.notifyDataSetChanged();
		}
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		
	  // Checking request code from intent
	  if ( requestCode == 2) 
	  {
		  try 
		  {
			  	boolean added_task=false;
			  	String message=data.getStringExtra("MESSAGE");    
			  	//open the connection to the DAO
			  	dao.open();
			  	added_task= dao.addTask(message);
			  /*	Toast.makeText(this,message,
			  	Toast.LENGTH_SHORT).show(); */
			  	if(added_task)
			  	{
			  		//Updating the cash
			  		TodoItems = dao.getAllTasks();
			  		//Updating the adapter
			  		adapter.UpdateDataSource(TodoItems);
			  	}
			  	else
			  	{
			  		Toast.makeText(this,"cant add message to DBA",Toast.LENGTH_SHORT).show();
			  	}
		  }
 
	   catch (Exception e) 
	   {
		   e.printStackTrace();
		   Log.i("query_5","problem on return from message");
	   }		  
	  }
	}
}
	
	


