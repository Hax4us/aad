package com.mycompany.myapp;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;

public class DogFragment extends Fragment {
    
    public static String TAG = DogFragment.class.getSimpleName();
	
	private EditText mDogName;
	private EditText mDogAge;
	private CheckBox mIsInStock;
	private Button mSaveButton;

	private Dog mDog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDog = new Dog();
		String name = getActivity().getIntent().getStringExtra("name");
		String age = getActivity().getIntent().getStringExtra("age");
		mDog.setName(name);
		mDog.setAge(age);
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
		
		mDogName.setText(mDog.getName());
		mDogAge.setText(mDog.getAge());
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
	}
	
    
}
