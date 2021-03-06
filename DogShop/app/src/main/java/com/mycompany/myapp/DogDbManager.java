package com.mycompany.myapp;
import android.content.Context;
import com.mycompany.myapp.database.DogDatabaseHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import com.mycompany.myapp.database.DogShopSchema.DogTable;
import java.util.List;
import java.util.UUID;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.util.Log;

public class DogDbManager {
	
	private String TAG = "DogDbManager";
	
	private DogDatabaseHelper dbHelper;
	private SQLiteDatabase database;
	private static DogDbManager instance;
	
	private DogDbManager(Context context) {
		
		dbHelper = new DogDatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		
	}
	
	public synchronized static DogDbManager getInstance(Context context) {
		if (instance == null) {
			instance = new DogDbManager(context);
		}
		return instance;
	}
	
	public void addDog(Dog dog) {
		ContentValues values = getContentValues(dog);
		database.insert(DogTable.NAME, null, values);	
	}
	
	public void updateDog(Dog dog) {
		ContentValues values = getContentValues(dog);
		String udid = dog.getId().toString();
		database.update(DogTable.NAME, values, DogTable.Cols.UDID + " = ?", new String[] {udid});
	}
	
	public boolean deleteDog(Dog dog) {
		int count = database.delete(DogTable.NAME, DogTable.Cols.UDID + " = ?", new String[] {dog.getId().toString()});
		if (count == 1) {
			return true;
		}
		return false;
	}
	
	public List<Dog> getDogs() {
		List<Dog> dogs = new ArrayList<>();
		Cursor cursor = queryDogs(null, null);
		
		try {
			cursor.moveToFirst();
			while(!cursor.isAfterLast()) {
				dogs.add(createDog(cursor));
				cursor.moveToNext();
			}
		} finally {
			cursor.close();
		}
		
		return dogs;
	}
	
	private Dog createDog(Cursor cursor) {
		String udid = cursor.getString(cursor.getColumnIndex(DogTable.Cols.UDID));
		String name = cursor.getString(cursor.getColumnIndex(DogTable.Cols.NAME));
		String age = cursor.getString(cursor.getColumnIndex(DogTable.Cols.AGE));
		int in_stock = cursor.getInt(cursor.getColumnIndex(DogTable.Cols.IN_STOCK));
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = formater.parse(cursor.getString(cursor.getColumnIndex(DogTable.Cols.IN_STOCK_DATE)));
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage());
		}
		
		Dog dog = new Dog();
		
		dog.setName(name);
		dog.setAge(age);
		dog.setId(UUID.fromString(udid));
		if (in_stock == 1) {
			dog.setInStock(true);
		} else {
			dog.setInStock(false);
		}
		
		dog.setDate(date);
		
		return dog;
		
	}
	
	public Dog getDog(UUID dogId) {
		Cursor cursor = queryDogs(DogTable.Cols.UDID + " = ?", new String[] {dogId.toString()});
		Dog dog;
		try {
			cursor.moveToFirst();
			dog = createDog(cursor);
			
		} finally {
			cursor.close();
		}
		
		return dog;
	}
	
	private static ContentValues getContentValues(Dog dog) {
		ContentValues values = new ContentValues();
		values.put(DogTable.Cols.NAME, dog.getName());
		values.put(DogTable.Cols.AGE, dog.getAge());
		values.put(DogTable.Cols.UDID, dog.getId().toString());
		values.put(DogTable.Cols.IN_STOCK, dog.isInStock() ? 1 : 0);
		values.put(DogTable.Cols.IN_STOCK_DATE, dog.getPrettyDate());
		
		return values;
	}
	
	private Cursor queryDogs(String whereClause, String[] whereArgs) {
		Cursor cursor = database.query(DogTable.NAME,
		        null,
		        whereClause,
				whereArgs,
				null,
				null,
				null);
				
		return cursor;
	}
    
}
