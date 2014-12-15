package com.example.todo;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Class to hold reference for database
 * using DBHelper class 
 * @author Livne
 *
 */
public class TaskDAO implements IdataAcces 
{
	private static TaskDAO instance;
	private Context context;
	private DBHelper dbHelper;
	// Database fields
	private SQLiteDatabase database;
	
	
	/**
	 * Private Singleton Constructor
	 * @param context
	 */
	private TaskDAO(Context context) 
	{
		this.context = context;
		dbHelper = new DBHelper(this.context);
		this.context = context;	// Giving the main Application context to DBHelper Object 

	}
	
	
	/**
	 * Static Call To TaskDAO
	 * @param context
	 * @return
	 */
	public static TaskDAO getInstatnce(Context context) 
	{
		if (instance == null)
		{
		instance = new TaskDAO(context);
		}
		return instance;
	}

	
	/**
	 * Add Task Function
	 */
	@Override
	public boolean addTask(String new_task) 
	{
		if (new_task == null)
		{
			return false;
		}
		
		//build the content values
		ContentValues values = new ContentValues();
		values.put(DBHelper.COLUMN_COMMENT, new_task);
		// Insert To Database
		long return_query;
		try
		{
			return_query = database.insert(DBHelper.TABLE_NAME, "task", values);
		}
		
		
		
		
		
		
		catch(SQLException e)
		{
			
			Log.i("query4","cant find table");
		}
		
	
		catch(Exception e)
		{
			e.printStackTrace();
			Log.i("query4","cant find table");
		}
		
		
		
		return true;
	}
	
	
	/**
	 * Open DataBase For Session
	 */
	@Override
	public void open() throws SQLException 
	{
		database = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Close DataBase For Session
	 */
	@Override
	public void close() 
	{
		dbHelper.close();
	}


	/**
	 * Retrieving all tasks from DataBase
	 */
	@Override
	public List<String> getAllTasks() 
	{
		Toast toast;

		Cursor cursor=null;
		List<String> all_tasks = new ArrayList<String>();
		this.open();

		String selectQuery = "SELECT * FROM tasks";
		cursor = database.rawQuery(selectQuery,null);
		
		// looping through all rows and adding tasks to list
	    if (cursor.moveToFirst()) 
	    {

	        do 
	        {
	            // Adding contact to list
	        	all_tasks.add(cursor.getString(1));
	        	
	        } 	while (cursor.moveToNext());
	    }
	    
	    else
	    {
	    	return null;
	    }
	  /*  toast =Toast.makeText(this.context,"There are: "+Integer.toString(all_tasks.size())+" tasks in DB", Toast.LENGTH_SHORT);
		toast.show(); */
		return all_tasks;
	}
	
	
	
	
	/**
	 * Remove all tasks from database.
	 */
	public int removeAll()
	{
		Toast toast =Toast.makeText(this.context,"entering removeTasks() ", Toast.LENGTH_SHORT);
		toast.show();
		this.open();
		
		int removed=0;
		try
		{
			removed=database.delete(DBHelper.TABLE_NAME,null, null);
			//int count = database.ge
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			Log.i("remove_tasks",Integer.toString(removed));
			return removed;
		}
		
		return removed;
	}

	
	/**
	 * Remove Task Function
	 */
	@Override
	public boolean removeTask(String task) {
		//opens the database for writing
		Log.i("click_rem"," in dao string= "+task);

		this.open();
		Log.i("click_rem"," in dao after open() ");

		int deleted = 0;
		// put an int for our callback query
		deleted = database.delete("tasks","task" +" ='" + task+"'", null);
		Log.i("click_rem"," in dao ");
		// check whether the task was deleted or not
		if(deleted ==1)
		{
			// if it do
			return true;
		}
		
		
		
		// if its not
		return false;
	}

}
