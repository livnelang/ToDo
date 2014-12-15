package com.example.todo;

import java.util.List;

import android.database.SQLException;

/**
 * Indicates The DAO Interface
 * @author Livne
 */
public interface IdataAcces 
{
	// Function for adding a task to database
	boolean addTask(String task);
	
	// Function for Removing a task to database
	boolean removeTask(String task);
	
	// Function for open database session
	void open() throws SQLException;
	
	// Function for closes database session
	void close();
	
	// Function for getting all task from database
	List<String> getAllTasks();
	
	// remove all tasks from our table
	int removeAll();

	
}
