package com.mycompany.myapp;
import android.content.Context;
import com.mycompany.myapp.database.DogDatabaseHelper;
import android.database.sqlite.SQLiteDatabase;

public class DogDbManager {
	
	private DogDatabaseHelper dbHelper;
	private SQLiteDatabase database;
	
	public DogDbManager(Context context) {
		
		dbHelper = new DogDatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		
	}
    
}
