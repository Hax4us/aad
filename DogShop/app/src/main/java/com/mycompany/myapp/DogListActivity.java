package com.mycompany.myapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.widget.Toast;

public class DogListActivity extends AppCompatActivity {
    
    public static String TAG = DogListActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_dog);

		FragmentManager fm = getSupportFragmentManager();

		Fragment fragment = fm.findFragmentById(R.id.fragment_container);

		if (fragment == null) {
			fragment = new DogListFragment();
		
			fm.beginTransaction()
				.add(R.id.fragment_container,fragment)
				.commit();
		}

    }
    
}
