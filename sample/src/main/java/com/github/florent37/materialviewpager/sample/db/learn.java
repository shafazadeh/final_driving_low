package com.github.florent37.materialviewpager.sample.db;

public class learn 
{
	public static final String TABLE_NAME = "learn";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "_title"; 
    public static final String COL_TEXT = "_text";
    
    //
    int id;
    String title,text;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public learn(int id, String title, String text) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
	}
	//
	public learn() {
		super();
	}
    
    
}
