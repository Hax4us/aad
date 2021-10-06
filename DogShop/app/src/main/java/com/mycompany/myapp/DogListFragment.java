package com.mycompany.myapp;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.content.Intent;

public class DogListFragment extends Fragment {
    
    public static String TAG = DogListFragment.class.getSimpleName();

	private RecyclerView mDogListRecyclerView;
	private DogAdapter mDogAdapter;
	private List<Dog> mDogList;
	private DogHandler mDogHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mDogHandler = DogHandler.get();
		mDogList = mDogHandler.getDogs();
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dog_list, container, false);
		mDogListRecyclerView = view.findViewById(R.id.dog_list_view);
		mDogAdapter = new DogAdapter(mDogList);
		mDogListRecyclerView.setAdapter(mDogAdapter);
		mDogListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		
		return view;
	}
	
	private class DogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView mDogName;
		private TextView mDogAge;
		
		public DogViewHolder(View v) {
			super(v);
			
			mDogName = itemView.findViewById(R.id.dog_name);
			mDogAge = itemView.findViewById(R.id.dog_age);
			itemView.setOnClickListener(this);
		}
		
		public void bind(Dog dog) {
			mDogName.setText(dog.getName());
			mDogAge.setText(dog.getAge());
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = DogActivity.newIntent(getActivity(), mDogName.getText().toString(), mDogAge.getText().toString());
			startActivity(intent);
		}
	}
	
	private class DogAdapter extends RecyclerView.Adapter<DogViewHolder> {
		
		private List<Dog> mDogs;
		
		public DogAdapter(List<Dog> dogs) {
			mDogs = dogs;
		}

		@Override
		public DogViewHolder onCreateViewHolder(ViewGroup parent, int position) {
			LayoutInflater inflater = LayoutInflater.from(getActivity());
			View view = inflater.inflate(R.layout.dog_list_item, parent, false);
			return new DogViewHolder(view);
		}

		@Override
		public void onBindViewHolder(DogViewHolder holder, int position) {
			holder.bind(mDogs.get(position));
		}

		@Override
		public int getItemCount() {
			return mDogs.size();
		}
	}
    
}
