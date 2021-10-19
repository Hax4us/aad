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
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class DogFragment extends Fragment {
    
    public static String TAG = DogFragment.class.getSimpleName();
	
	private EditText mDogName;
	private DogDbManager manager;
	private EditText mDogAge;
	private CheckBox mIsInStock;
	private Button mSaveButton;
	private Button mSetDateButton;

	private Dog mDog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    manager = DogDbManager.getInstance(getActivity());
		if ( getActivity().getIntent().getStringExtra("id") != null) {
			UUID id = UUID.fromString(getActivity().getIntent().getStringExtra("id"));
			mDog = manager.getDog(id);
		} else {
			mDog = new Dog();
		}
		
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dog, container, false);
		mDogName = view.findViewById(R.id.fragment_dog_name);
		mDogAge = view.findViewById(R.id.fragment_dog_age);
		mIsInStock = view.findViewById(R.id.fragment_dog_stock_checkbox);
		mSaveButton = view.findViewById(R.id.fragment_dog_save_button);
		mSetDateButton = view.findViewById(R.id.fragment_dog_stock_date);
		
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
		updateDateButton();
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
	
	
	private void updateDateButton() {
		mSetDateButton.setText(mDog.getPrettyDate());
	}
	
	
	private void setUpWidgets() {
		mSaveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				saveChanges();
			}
		});
		
		mSetDateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(mDog.getDate());
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker picker, int year, int month, int day) {
						Date date = new GregorianCalendar(year, month, day).getTime();
						mDog.setDate(date);
						updateDateButton();
					}
					
				}, year, month, day);
				datePickerDialog.show();
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
