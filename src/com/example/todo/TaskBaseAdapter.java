package com.example.todo;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
	
	/**
	 * Our Custom BasAdapter
	 * @author Livne Lang
	 *
	 */
	public class TaskBaseAdapter extends BaseAdapter {
		
		private List<String> tasks;
		private Context context;
		
		
		
		/**
		 * Static class as ViewHolder
		 * @author Livne
		 *
		 */
		static class ViewHolder
		{
			TextView tv;
			ImageButton ib;
			Button done_b;
			
			/**
			 * ViewHolder Constructor 
			 * 
			 * @param View (view of single row inside the List
			 */
			ViewHolder(View v)
			{
				tv = (TextView) v.findViewById(R.id.task_view);
				ib = (ImageButton) v.findViewById(R.id.imageButton1);
				done_b = (Button) v.findViewById(R.id.button1);
			}	
		}
		
		/**
		 * TaskBaseAdapter Constructor
		 * @param context
		 * @param tasks =a list of tasks
		 */
		public TaskBaseAdapter(Context context, List<String> tasks) {
			this.tasks = tasks;
			this.context = context;
			tasks =new ArrayList<String>();
		}
		
		/**
		 * Gets the amount of tasks
		 */
		@Override
		public int getCount() {
		return tasks.size();
		}
		
		/**
		 * Gets the current List Row Object
		 */
		@Override
		public Object getItem(int position) {
			if (this.tasks != null && tasks.size() > position)
				return this.tasks.get(position);
				return null;
		}

		/**
		 * Get the row id
		 */
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		
		/**
		 * Function to get the view of a single row
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ViewHolder holder = null;
			
			if (row == null) {
				row = LayoutInflater.from(context).inflate(R.layout.task_view, null);
				holder = new ViewHolder(row);
				// Set tag to remember the viewholder
				row.setTag(holder);
				 Log.i("holder","creating a new view (row)");
			}
			
			// recycling
			else { 
				holder = (ViewHolder) row.getTag();
				Log.i("holder","recycling views");
			}
			
			// set the values ( this case only textview)
			holder.tv.setText(tasks.get(position));
			
			return row;
			
		}
		
		/**
		 * Essential function call to update the ListView
		 * @param updated_tasks
		 */
		public void UpdateDataSource(List<String> updated_tasks)
		{
			
			if(updated_tasks == null)
			{
				return; 				 // if arrived DB is empty - do not update us
			}
			this.tasks= updated_tasks;  // else - update the new view/adapter
		}
		
	}
		
	
