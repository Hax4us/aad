
package com.hax4us.menu;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.item1:
				Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.item2:
				Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.item3:
				Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.item4:
				Toast.makeText(this, "Item1", Toast.LENGTH_SHORT).show();
				return true;
			default:
			    super.onOptionsItemSelected(item);
				return true;
				
		}
	}
	
	

}
