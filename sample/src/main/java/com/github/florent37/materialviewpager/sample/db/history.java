package com.github.florent37.materialviewpager.sample.db;

public class history
{
	public static final String TABLE_NAME = "history";
    public static final String EXAM_ID = "exam_id";
    public static final String EXAM_NAME = "exam_name"; 
    //
    public String exam_id,exam_name;
    //
	public history(String exam_id, String exam_name) 
	{
		this.exam_id = exam_id;
		this.exam_name = exam_name;
	}
    
    
}
