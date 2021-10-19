package com.mycompany.myapp;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Dog {
	private String mName;
	private String mBreed;
	private String mAge;
	private boolean mInStock;
	private UUID mDogId;
	private Date mDate;
	
	public Dog() {
		mDogId = UUID.randomUUID();
		mDate = new Date();
	}

	public void setBreed(String breed) {
		this.mBreed = breed;
	}

	public String getBreed() {
		return mBreed;
	}
	
	public void setName(String name) {
		this.mName = name;
	}

	public String getName() {
		return mName;
	}
	
	public void setAge(String age) {
		mAge = age;
	}
	
	public String getAge() {
	    return mAge;
	}
	
	public void setInStock(boolean inStock) {
		mInStock = inStock;
	}
	
	public boolean isInStock() {
		return mInStock;
	}
	
	public UUID getId() {
		return mDogId;
	}
	
	public void setId(UUID id) {
		mDogId = id;
	}
	
	public void setDate(Date date) {
		mDate = date;
	}
	
	public Date getDate() {
		return mDate;
	}
	
	public String getPrettyDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(mDate);
	}
}
