package com.hax4us.myblogger.modals;
import java.util.ArrayList;

public class BlogPost {
	private String title;
	private String content;
	private ArrayList<BlogImage> images;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public ArrayList<BlogImage> getImages() {
		return images;
	}
	
	public void setImages(ArrayList<BlogImage> images) {
		this.images = images;
	}
}