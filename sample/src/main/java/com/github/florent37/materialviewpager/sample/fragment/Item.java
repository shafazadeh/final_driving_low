package com.github.florent37.materialviewpager.sample.fragment;

import android.graphics.Bitmap;

/**
 * 
 * @author manish.s
 *
 */

public class Item {
	Bitmap image;
	String title;
    String name;
    int index;
	
	public Item(Bitmap image, String title,String n,int i) {
		super();
		this.image = image;
		this.title = title;
        this.name=n;
        this.index=i;
	}
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
