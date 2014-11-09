/**
 * 
 */
package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author Livne
 *
 */
public class CreateTaskActivity extends Activity 
{
	public final static String EXTRA_MESSAGE = "com.example.todo.MESSAGE"; // key for the carried task
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_task);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.to_do_list, menu);
		return true;
	}
	
	public void CreateTask(View v)
	{
		System.out.print("enterd create task function !");
		Context context = getApplicationContext();
    	CharSequence text = "entering CreateTASK()";
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
		
		
		Intent intent = new Intent(this, ToDoList.class);   // create's main activity again
		EditText editText = (EditText) findViewById(R.id.editText1);      // gets from text field
		String message = editText.getText().toString(); 			  	 // we have the task
		
		toast = Toast.makeText(context, message, duration);
    	toast.show();
		
		
		intent.putExtra("MESSAGE", message);      	// puts the message on the way
		intent.setType("text/plain");
		
		setResult(2,intent);  
        finish();//finishing activity  
	}
	

}
