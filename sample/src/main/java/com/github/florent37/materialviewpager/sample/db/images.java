package com.github.florent37.materialviewpager.sample.db;

import android.graphics.Bitmap;

public class images
{
	public static final String TABLE_NAME = "tbl_image";
    public static final String COL_ID = "_id";
    public static final String COL_DATA = "image_data"; 
    public static final String COL_NAME = "image_name"; 
    //
    public static final String[] PROJECTION_ALL = new String[] {
        COL_ID, COL_DATA,COL_NAME
};
    
    
    private Bitmap data;
    private int id;
    private String name;
	public Bitmap getData() {
		return data;
	}
	public void setData(Bitmap data) {
		this.data = data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public images(Bitmap data, int id, String name) {
		super();
		this.data = data;
		this.id = id;
		this.name = name;
	}
	public images() {
		super();
		this.data = null;
	} 
    

}
