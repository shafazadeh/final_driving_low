package com.github.florent37.materialviewpager.sample.db;

public class sign 
{
	 
	
	//*********************static final var************************
	  public static final String KEY_ROWID = "_id";  
	  public static final String TableName="sign";
	  public static final String KEY_NAME = "name";
	  public static final String KEY_PIC = "pic";
	  public static final String KEY_CATEGORY = "category";
			
	//*********************var*********************
	  String id,name,pic,category;
			
			    
			    public sign(){
			 
			    }
			  
			    public sign( String name, String pic,String id,String category){
			    	
			    	
			    	 this.name=name ;
			    	 this.pic=pic;
			    	 this.id=id;
			    	this.category= category;
			       
			       
			       
			       
			    }
			 
			    public String getName(){
			        return this.name;
			    }
			 
			    
			    public void setName(String name){
			        this.name = name;
			    }
			 
			    
			    public String getCategory(){
			        return this.name;
			    }
			 
			    
			    public void setCategory(String category){
			        this.name=category;
			    }
			    
	
			    public String getpic(){
			        return this.pic;
			    }
			 
			    public void setpic(String pic){
			        this.pic=pic;
			    }
			    
			    public String getID(){
			        return this.id;
			    }
			 
			    // setting id
			    public void setID(String id){
			        this.id = id;
			    }
			    
			    
			}		 
