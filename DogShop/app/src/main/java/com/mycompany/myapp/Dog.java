package com.mycompany.myapp;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Dog {
	private String mName;
	private String mBreed;
	private String mAge;
	private boolean mInStock;
	private UUID mDogId;
	
	public Dog() {
		mDogId = UUID.randomUUID();
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
	
	public static List<Dog> createFakeDogs(int n) {
		List<Dog> dogs = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Dog dog = new Dog();
			String name = "Dog" + i;
			String breed = "Breed" + i;
			String age = "" + i;
			dog.setName(name);
			dog.setBreed(breed);
			dog.setAge(age);
			dogs.add(dog);
			
		}
		
		return dogs;
	}
}
