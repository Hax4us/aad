package com.hax4us.myblogger.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BlogPostListFragment extends Fragment {
	
	private final String TAG = BlogPostListFragment.class.getSimpleName();

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
    public void onViewCreated(View arg0, Bundle arg1) {
        super.onViewCreated(arg0, arg1);
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchPostFromBlogger();
    }

    private fetchPostFromBlogger() {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("").build();

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
								
							}
                        });
    }
}
