package com.example.model;

import java.util.Date;


public class Manufacturer {
	
	private int id;
	
	private String name;
	
	private Date foundDate;
	
	private Boolean active;
	
	public Manufacturer()
	{
		
	}

	public Manufacturer(int id, String name, Date foundDate, Boolean active) {
		super();
		this.id = id; 
		this.name = name;
		this.foundDate = foundDate;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFoundDate() {
		return foundDate;
	}

	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	

}
