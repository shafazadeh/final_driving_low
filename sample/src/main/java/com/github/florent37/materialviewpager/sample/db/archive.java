package com.github.florent37.materialviewpager.sample.db;

public class archive 
{
	//************************static final variable**************************
		 public static final String KEY_ROWID = "_id";  
		 public static final String TableName="archive";
		 public static final String KEY_ANSWER = "answer";
		 public static final String KEY_EXAMID = "exam_id";
		 //*******************value of content**************************
		    String answer,id,exam_id;
		    //***********************constructor***************************
		    /**
		     * سازنده کلاس قوانین
		     * @param category
		     * @param name
		     * @param id
		     * @param content
		     * @param pic
		     */
		    public archive( String answer, String id,String exam_id)
		    {
		    	 this.answer=answer;
		         this.exam_id=exam_id ;
		         this.id=id;		       
		    }
	 
		    public archive()
		    {
		    	
		    }
		    //**************************set & get *****************************

			public String getAnswer() {
				return answer;
			}

			public void setAnswer(String answer) {
				this.answer = answer;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getExam_id() {
				return exam_id;
			}

			public void setExam_id(String exam_id) {
				this.exam_id = exam_id;
			}
		    
		    
		    //**************************************************************
		    
		}