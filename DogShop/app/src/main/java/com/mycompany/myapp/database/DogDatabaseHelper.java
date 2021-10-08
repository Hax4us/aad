package com.mycompany.myapp.database;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.mycompany.myapp.database.DogShopSchema.DogTable;

public class DogDatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "dogShopDb.db"; // /data/data/com.mycompany.myapp/databases/DATABASE_NAME.db
	private static final int VERSION = 1;
	
	private final String SQL_COMMAND = "create table " + 
	        DogTable.NAME + 
			"(" +
			"_id integer primary key autoincrement, " +
			DogTable.Cols.UDID + ", " +
			DogTable.Cols.NAME + ", " +
			DogTable.Cols.AGE + ", " +
			DogTable.Cols.IN_STOCK +
			")";
	
	public DogDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(SQL_COMMAND);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
    
}
