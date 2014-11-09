package com.example.todo;

import java.util.ArrayList;
import java.util.Arrays;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ToDoList extends ActionBarActivity 
{
	static boolean alreadyLaunched = false;
	private ListView mListView;	
	private ArrayList<String> TodoItems;
	private ArrayAdapter<String> adapter;
//	private int init
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_to_do_list);
		
		String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
                "Jupiter", "Saturn", "Uranus", "Neptune"};  
		
		
	/*	Context context = getApplicationContext();
    	CharSequence text = "starting now";
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show(); */
		
    	
    	TodoItems  = new ArrayList<String>();
    	TodoItems.addAll( Arrays.asList(planets) );
    	mListView = (ListView) findViewById( R.id.mListView ); 
    	adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, TodoItems);
    	//check_incoming();
    	mListView.setAdapter(adapter);
    	adapter.add("first word");

    	
    	
    
    	
    	
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
	 * The Function checks whether a new task arrived
	 * 
	 */
	public void check_incoming()
	{
		Toast.makeText(this,"entering for checking",
		        Toast.LENGTH_SHORT).show();
		
		Intent intent = getIntent();  
	    String sharedText = intent.getStringExtra(CreateTaskActivity.EXTRA_MESSAGE);
	    
	    Toast.makeText(this,sharedText,
		        Toast.LENGTH_SHORT).show();
	    if (sharedText != null) 
	    {
	    	int size =sharedText.length();
	    	if(size>0)
	    	{
	    		Toast.makeText(this,"entering size of word",
	    		        Toast.LENGTH_SHORT).show();
	    		adapter.add(sharedText);
	    	}
	    }
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		Toast.makeText(this,"returned",
		        Toast.LENGTH_SHORT).show();
		
	  if ( requestCode == 2) 
	  {
		  
	
		  
		  String message=data.getStringExtra("MESSAGE");   
		  Toast.makeText(this,message,
			        Toast.LENGTH_SHORT).show();
		  adapter.add(message);
		  
	//	  check_incoming();
	  } 
	} 
	

}
	
	


