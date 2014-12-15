package com.example.todo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper 
{
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LivneDataBase";
    public static final String TABLE_NAME = "tasks";
    //Columns
    public static final String COLUMN_ID = "id";
	public static final String COLUMN_COMMENT = "task";
	//Annotations
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	public static String[] friendsColumns = { COLUMN_ID, COLUMN_COMMENT,};
	private Context context;
	
	SQLiteDatabase dba;
   
    	/**
    	 * DBHelper Constructor
    	 * @param context
    	 */
	   public DBHelper(Context context)
	   {
		   super(context, DATABASE_NAME, null, DATABASE_VERSION);
		   this.context = context;	// Giving the main Application context to DBHelper Object 
	   }
	   
	   @Override
	   public void onCreate(SQLiteDatabase db) 
	   {	
	 		Log.i("query_on_table_create"," creating table");

		   Toast toast =Toast.makeText(context, "entering DB OnCREATE()", Toast.LENGTH_SHORT);
		   toast.show();

		   // String to represent the query, Our Table will be called 'Tasks'
		   final String create_table = "CREATE TABLE "
				   + DBHelper.TABLE_NAME + "(" + DBHelper.COLUMN_ID
				   + " INTEGER PRIMARY KEY," + DBHelper.COLUMN_COMMENT
				   + " TEXT"+")";
				  
		 	
		 	
		 		Log.i("query_on_table_create"," creating table");
	 			System.out.println("sql os creating table (try)");
		 		db.execSQL(create_table);
		 		
		 	
		 		/*catch (SQLException e )
		 		{
					Log.i("query_on_table_create","cant create table");

		 			System.out.println("sql string was invalid");
		 			e.printStackTrace();
		 		}*/
	   }
	  
	   
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
 		Log.i("query_on_table_create"," creating table");

		Toast toast =Toast.makeText(context, "entering DB onUpgrade()", Toast.LENGTH_SHORT);
		toast.show();
		db.execSQL("DROP TABLE IF EXISTS tasks");
		onCreate(db);
	}
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
        onUpgrade(db, oldVersion, newVersion);
    }
	


}
