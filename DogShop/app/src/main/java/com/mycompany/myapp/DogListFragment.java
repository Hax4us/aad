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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DogListFragment extends Fragment {
    
    public static String TAG = DogListFragment.class.getSimpleName();

	private RecyclerView mDogListRecyclerView;
	private TextView mEmtpyListMessage;
	private DogAdapter mDogAdapter;
	private List<Dog> mDogList;
	private DogDbManager mManager;
	//private DogHandler mDogHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
		
		//mDogHandler = DogHandler.get();
		mManager = DogDbManager.getInstance(getActivity());
		
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dog_list, container, false);
		mDogListRecyclerView = view.findViewById(R.id.dog_list_view);
		mEmtpyListMessage = view.findViewById(R.id.list_not_found);
		mDogListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		updateUI();
	}
	
	private void updateUI() {
		mDogList = mManager.getDogs();
		if (mDogAdapter == null) {
			mDogAdapter = new DogAdapter(mDogList);
			mDogListRecyclerView.setAdapter(mDogAdapter);
		} else {
			mDogAdapter.updateDogsList(mDogList);
			mDogAdapter.notifyDataSetChanged();
		}
		
		if (mDogList.isEmpty()) {
			mDogListRecyclerView.setVisibility(View.GONE);
			mEmtpyListMessage.setVisibility(View.VISIBLE);
		} else {
			mDogListRecyclerView.setVisibility(View.VISIBLE);
			mEmtpyListMessage.setVisibility(View.GONE);
		}
	}
	

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.main_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_add_dog:
				Intent intent = new Intent(getActivity(), DogActivity.class);
				startActivity(intent);
				return true;
			default:
			    return super.onOptionsItemSelected(item);
		}
	}
	
	private class DogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private Dog dog;
		private TextView mDogName;
		private TextView mDogAge;
		
		public DogViewHolder(View v) {
			super(v);
			
			mDogName = itemView.findViewById(R.id.dog_name);
			mDogAge = itemView.findViewById(R.id.dog_age);
			itemView.setOnClickListener(this);
		}
		
		public void bind(Dog dog) {
			this.dog = dog;
			mDogName.setText(dog.getName());
			mDogAge.setText(dog.getAge());
		}
		
		@Override
		public void onClick(View v) {
			Intent intent = DogActivity.newIntent(getActivity(), dog.getName(), dog.getAge(), dog.getId().toString());
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
		
		public void updateDogsList(List<Dog> dogs) {
			mDogs = dogs;
		}
	}
    
}
