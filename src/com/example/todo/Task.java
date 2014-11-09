package com.example.todo;

public class Task 
{
	private String name;
	private String description;
	
	/**
	 * Task Default Constructor
	 */
	public Task()
	{
	
	}
	
	/**
	 * Task Constructor
	 * @param name
	 * @param description
	 */
	public Task(String name, String description) 
	{
		super();
		this.name = name;
		this.description = description;
	}
	
	
	

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	
	
	
	
	

}
