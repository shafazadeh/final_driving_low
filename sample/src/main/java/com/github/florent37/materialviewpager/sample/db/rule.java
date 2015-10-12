package com.github.florent37.materialviewpager.sample.db;

public class rule {
	 
	//************************static final variable**************************
	 public static final String KEY_ROWID = "_id";  
	 public static final String TableName="rules";
	 public static final String KEY_NAME = "name";
	 public static final String KEY_CATEGORY = "category";
	 public static final String KEY_PIC = "pic";
	 public static final String KEY_CONTENT = "content";
	
	 
	 //*******************value of content**************************
	    String category,id;
	    String content,name,pic;
	    
	    //***********************constructor***************************
	    /**
	     * سازنده کلاس قوانین
	     * @param category
	     * @param name
	     * @param id
	     * @param content
	     * @param pic
	     */
	    public rule( String category, String name,String id,String content,String pic)
	    {
	    	
	    	 this.category=category;
	         this.name=name ;
	         this.id=id;
	         this.content=content;
	         this.pic=pic;
	       
	    }
 
	    public rule()
	    {
	    	
	    }
	    //**************************set & get *****************************
	    public String getCategory(){
	        return this.category;
	    }
	    
	    public void setCategory(String category){
	        this.category=category;
	    }
	    
	    public String getContent(){
	        return this.content;
	    }
	  
	    public void setContent(String content){
	        this.content=content;
	    }
	    
	    public String getID(){
	        return this.id;
	    }
	 
	    public void setID(String id){
	        this.id = id;
	    }
	    
	    public String getpic(){
	        return this.pic;
	    }
	 
	    public void setpic(String pic){
	        this.pic=pic;
	    }
	    
	    public String getName(){
	        return this.name;
	    }
	  
	    public void setName(String content){
	        this.name=content;
	    }
	    
	    //**************************************************************
	    
	}