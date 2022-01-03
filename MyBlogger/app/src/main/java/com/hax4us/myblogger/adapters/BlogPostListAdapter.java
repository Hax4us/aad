package com.hax4us.myblogger.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.hax4us.myblogger.fragments.BlogPostListFragment;
import com.hax4us.myblogger.modals.BlogPost;
import com.hax4us.myblogger.R;
import java.util.ArrayList;

public class BlogPostListAdapter extends RecyclerView.Adapter<BlogPostListAdapter.BlogPostViewHolder> {
	
	private ArrayList<BlogPost> posts;
	
	public BlogPostListAdapter(ArrayList<BlogPost> posts) {
		this.posts = posts;
	}
	
	class BlogPostViewHolder extends RecyclerView.ViewHolder {
		
		ImageView image;
		TextView title;
		
		public BlogPostViewHolder(View view) {
			super(view);
			image = view.findViewById(R.id.blog_post_thumbnail);
			title = view.findViewById(R.id.blog_post_title);
		}
		
		public void bind(BlogPost post) {
			
		}
	}
	
	@Override
	public BlogPostViewHolder onCreateViewHolder(ViewGroup parent, int type) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.blog_post_item, parent, false);
		return new BlogPostViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(BlogPostViewHolder holder, int pos) {
		holder.bind(posts.get(pos));
	}
	
	@Override
	public int getItemCount() {
		return posts.size();
	}
	
	
}