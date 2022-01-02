package com.hax4us.myblogger.modals;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class BlogResponse {
	@SerializedName("items")
	private ArrayList<BlogPost> posts;
}