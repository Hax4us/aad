package com.hax4us.myblogger.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.hax4us.myblogger.R;
import com.hax4us.myblogger.adapters.BlogPostListAdapter;
import com.hax4us.myblogger.modals.BlogResponse;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BlogPostListFragment extends Fragment {
	
	private final String TAG = BlogPostListFragment.class.getSimpleName();
	
	private String BLOGGER_POST_ENDPOINT = "https://www.googleapis.com/blogger/v3/blogs/%s/posts?key=%s&fetchImages=true";
	
	private String BLOG_ID = "2029578079756347786";
	private String KEY = "AIzaSyBAUTeGqgB-0BRUEew3sptZI51lZzcSOD8";
	
	private BlogResponse blogResponse;
	
	private RecyclerView recyclerView;
	private BlogPostListAdapter adapter;
	
    public static BlogPostListFragment newInstance() {
        return new BlogPostListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_post_list, parent, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchPostFromBlogger();
    }

    private void fetchPostFromBlogger() {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
				.url(String.format(BLOGGER_POST_ENDPOINT, BLOG_ID, KEY))
				.build();

        client.newCall(request)
                .enqueue(
                        new Callback() {

                            @Override
                            public void onFailure(Call call, IOException exception) {
								Log.e(TAG, exception.getMessage());
							}

                            @Override
                            public void onResponse(Call call, Response response) {
								if (!response.isSuccessful()) {
									onFailure(call, new IOException());
									return;
								}
								
								try {
									Gson gson = new Gson();
									blogResponse = gson.fromJson(response.body().string(), BlogResponse.class);
								    //Log.d(TAG, response.body().string());
									adapter = new BlogPostListAdapter(blogResponse.getPosts());
									getActivity().runOnUiThread(new Runnable() {
										@Override
										public void run() {
											recyclerView.setAdapter(adapter);
										}
									});
						    		
									
									Log.d(TAG, blogResponse.getPosts().get(0).getTitle());
									
								} catch (IOException e) {
									Log.e(TAG, "unable to fetch posts : ", e);
								}
							}
                        });
    }
}
