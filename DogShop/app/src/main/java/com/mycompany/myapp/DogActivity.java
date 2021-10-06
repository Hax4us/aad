package com.mycompany.myapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.content.Context;

public class DogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
		
		FragmentManager fm = getSupportFragmentManager();
		
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		
		if (fragment == null) {
			fragment = new DogFragment();
			
			fm.beginTransaction()
			        .add(R.id.fragment_container,fragment)
					.commit();
		}

    }
	
	public static Intent newIntent(Context context, String dogName, String dogAge) {
		Intent intent = new Intent(context, DogActivity.class);
		intent.putExtra("name", dogName);
		intent.putExtra("age", dogAge);
		return intent;
	}

}
