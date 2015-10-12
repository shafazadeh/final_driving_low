package com.github.florent37.materialviewpager.sample.db;

public class exam  {
	
	//*****************************static final variable*******************
	 public static final String KEY_ROWID = "_id";
	 public static final String TableName = "exam" ; 
	 public static final String KEY_QUESTION = "question";
	 public static final String KEY_OPT1 = "opt1";
	 public static final String KEY_OPT2 = "opt2";
	 public static final String KEY_OPT3 = "opt3";
	 public static final String KEY_OPT4 = "opt4";
	 public static final String KEY_RIGHTOPT = "rightopt";
	 public static final String KEY_REPEAT = "repeat";
	 public static final String KEY_WRONG = "wrong";
	 public static final String KEY_TYPE="type";
	 public static final String KEY_PIC = "pic";
	 public static final String KEY_HELP = "help";
	//**************************variable of exame***********************   
	  String id,question,opt1,opt2,opt3,opt4,rightopt,repeat,wrong,type,pic,help;
	 //*************************************************************
	    public exam(){
	 
	    }

	    public exam( String help,String question, String opt1, String opt2, String opt3, String opt4,String rightopt ,String repeat ,String wrong , String type, String pic, String id)
	    {
	    	this.help=help;
	    	 this.question= question;
	         this.opt1=opt1 ;
	         this.opt2=opt2;
	         this.opt3=opt3;
	         this.opt4=opt4;
	         this.rightopt=rightopt;
	         this.repeat=repeat;
	         this.wrong=wrong;
	         this.type=type;
	         this.pic=pic;
	         this.id=id;
	    }
	 
	    
	   
	    public String getHelp() {
			return help;
		}

		public void setHelp(String help) {
			this.help = help;
		}

		public String getQuestion(){
	        return this.question;
	    }
	 
	   
	    public void setQuestion(String question){
	        this.question = question;
	    }
	 
	 
	    public String getOpt1(){
	        return this.opt1;
	    }
	    public void setOpt1(String opt1){
	        this.opt1=opt1;
	    }
	    
	    public String getOpt2(){
	        return this.opt2;
	    }
	    public void setOpt2(String opt2){
	        this.opt2=opt2;
	    } 
	    public String getOpt3(){
	        return this.opt3;
	    }
	    public void setOpt3(String opt3){
	        this.opt3=opt3;
	    }
	    
	    public String getOpt4(){
	        return this.opt4;
	    }
	    public void setOpt4(String opt4){
	        this.opt4=opt4;
	    }
	    
	    public String getRightOpt(){
	        return this.rightopt;
	    }
	    public void setRightOpt(String rightopt){
	        this.rightopt=rightopt;
	    }
	    
	    public String getRepeat(){
	        return this.repeat;
	    }
	    public void setRepeat(String repeat){
	        this.repeat=repeat;
	    }
	    
	    public String getWrong(){
	        return this.wrong;
	    }
	    public void setWrong(String wrong){
	        this.wrong=wrong;
	    }
	    
	    public String getType(){
	        return this.type;
	    }
	    public void setType(String type){
	        this.type=type;
	    }
	    
	    
	    public String getPic(){
	        return this.pic;
	    }
	    public void setPic(String pic){
	        this.pic=pic;
	    } 
	    
	    public String getID(){
	        return this.id;
	    }
	 
	    // setting id
	    public void setID(String id){
	        this.pic = id;
	    }
	}
