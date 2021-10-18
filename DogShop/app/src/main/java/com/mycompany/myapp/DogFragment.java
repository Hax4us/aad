package com.mycompany.myapp;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import java.util.UUID;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

public class DogFragment extends Fragment {
    
    public static String TAG = DogFragment.class.getSimpleName();
	
	private EditText mDogName;
	private DogDbManager manager;
	private EditText mDogAge;
	private CheckBox mIsInStock;
	private Button mSaveButton;

	private Dog mDog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    manager = DogDbManager.getInstance(getActivity());
		mDog = new Dog();
		String name = getActivity().getIntent().getStringExtra("name");
		String age = getActivity().getIntent().getStringExtra("age");
		if ( getActivity().getIntent().getStringExtra("id") != null) {
			UUID id = UUID.fromString(getActivity().getIntent().getStringExtra("id"));
			mDog.setId(id);
		}
		
		mDog.setName(name);
		mDog.setAge(age);
		
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dog, container, false);
		mDogName = view.findViewById(R.id.fragment_dog_name);
		mDogAge = view.findViewById(R.id.fragment_dog_age);
		mIsInStock = view.findViewById(R.id.fragment_dog_stock_checkbox);
		mSaveButton = view.findViewById(R.id.fragment_dog_save_button);
		setUpWidgets();

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		if (mDog.getName() != null) {
			mDogName.setText(mDog.getName());
			mDogAge.setText(mDog.getAge());
		}
		
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_dog_menu, menu);
		if (mDog.getName() == null) {
			menu.getItem(0).setVisible(false);
		}
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_delete_dog:
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("DogShop")
			    .setMessage("Are you sure")
			    .setCancelable(false)
			    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.d(TAG, which + "");
						manager.deleteDog(mDog);
						getActivity().finish();
					}
					
				})
			    .setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Log.d(TAG, which + "");
						dialog.dismiss();
					}
				});
				
				builder.show();
				
				return true;
				
			default: 
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	
	
	private void setUpWidgets() {
		mSaveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveChanges();
			}
		});
	}
	
	private void saveChanges() {
		String name = mDogName.getText().toString();
		String age = mDogAge.getText().toString();
		boolean inStock = mIsInStock.isChecked();
		
		mDog.setName(name);
		mDog.setAge(age);
		mDog.setInStock(inStock);
		
		manager.addDog(mDog);
	}
	
    
}
