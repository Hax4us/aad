package com.mycompany.myapp;

import java.util.List;
import java.util.UUID;

public class DogHandler {
    
    public static String TAG = DogHandler.class.getSimpleName();
	
	private static DogHandler mDogHandlerInstance;
	
	private List<Dog> mDogs;
	
	private DogHandler() {
		mDogs = Dog.createFakeDogs(20);
		
	}
	
	public static DogHandler get() {
		if (mDogHandlerInstance == null) {
			mDogHandlerInstance = new DogHandler();
		}
		return mDogHandlerInstance;
	}
    
	protected List<Dog> getDogs() {
		return mDogs;
	}
	
	protected Dog getDog(UUID dogId) {
		for (Dog dog : mDogs) {
			if (dog.getId() == dogId) {
				return dog;
			}
		}
		return null;
	}
}
