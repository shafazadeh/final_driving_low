

package com.github.florent37.materialviewpager.sample.db;



import java.util.ArrayList;
import java.util.List;
import com.github.florent37.materialviewpager.sample.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Simple  database access helper class. 
 * Defines the basic CRUD operations (Create, Read, Update, Delete)
 * for the example, and gives the ability to list all Row as well as
 * retrieve or modify a specific Row.
 * 
 */


public class DbAdapter {
   
	
	public DataBaseHelper mDb;
	  public Activity act;
	    /**
	     * Database creation SQL statement
	     */

	    private final Context mCtx;
	    public static final String TABLE_CREATE =
	            "CREATE TABLE tbl_image (" +
	            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 
	            "image_data BLOB," + "image_name TEXT" +
	            ");"
	    ;

	    public DbAdapter(Activity ctx)throws Exception {
	    	this.mCtx=ctx;
	    	this.act=ctx;
	    	mDb =new DataBaseHelper(ctx);
	    	try {  
		        mDb.createDataBase();
		        } catch (Exception ioe) {
		         
		        	createToast(3000,ioe.getMessage());
		        	throw ioe;
		        }
	    	}
	    
	    public long insert(byte[] image) throws Exception{
	    	long l=0;
	    	try {        
 	        	mDb.openDataBase();
 	        	l=mDb.myDataBase.insert(images.TABLE_NAME, null, createContentValues(image));
 		    	mDb.close();
 	        }catch(Exception sqle){
 	        	createToast(3000,sqle.getMessage());
 	        	throw sqle;
 	        }    
	    	
            return l;
    }
    
	    private ContentValues createContentValues(byte[] image) {
    	
            ContentValues cv = new ContentValues();
            cv.put(images.COL_DATA, image);
            
            return cv;
    }
    
	    public byte[] fetchSingle(int id)throws Exception {
	    	 byte[] image = null;
	    	try {        
 	        	mDb.openDataBase();
 	        	Cursor queryCursor = mDb.myDataBase.query(images.TABLE_NAME, images.PROJECTION_ALL, images.COL_ID + " = " + id, null, null, null, null);
 	            if(queryCursor == null) {
 	                    return null;
 	            }
 	            if(queryCursor.moveToFirst()) {
 	                    image = queryCursor.getBlob(queryCursor.getColumnIndexOrThrow(images.COL_DATA));
 	            }
 	            queryCursor.close();
 	            mDb.close();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
            return image;
            
    }
	    //
	    public images fetchimage(int id)throws Exception {
	    	String text="";
	    	byte[] image = null;
	    	try {        
 	        	mDb.openDataBase();
 	        	Cursor queryCursor = mDb.myDataBase.query(images.TABLE_NAME, images.PROJECTION_ALL, images.COL_ID + " = " + id, null, null, null, null);
                if(queryCursor == null) {
                        return null;
                }
                if(queryCursor.moveToFirst()) {
                        image = queryCursor.getBlob(queryCursor.getColumnIndexOrThrow(images.COL_DATA));
                        text=queryCursor.getString(2);
                }
                queryCursor.close();
                mDb.close();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }

	                
	        return new images(Utilities.getImage(image), id, text);
	                
	  }
	 // Adding new rules
	    public void addrules(rule rules) {
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(SQLException sqle){
 	        	createToast(2000, sqle.getMessage());
 	        }catch(Exception sqle){
 	        	createToast(3000, sqle.getMessage());
 	        }
	        ContentValues values = new ContentValues();
	        values.put(rule.KEY_CATEGORY, rules.getCategory()); 
	        values.put(rule.KEY_NAME, rules.getName()); 
	        values.put(rule.KEY_CONTENT, rules.getContent());
	        values.put(rule.KEY_PIC, rules.getpic());
	     
	        // Inserting Row
	        mDb.myDataBase.insert(rule.TableName, null, values);
	        mDb.close(); // Closing database connection
	    }
	    
	    public void addhistory(history his)
	    {
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(SQLException sqle){
 	        	createToast(2000, sqle.getMessage());
 	        }catch(Exception sqle){
 	        	createToast(3000, sqle.getMessage());
 	        }
	        ContentValues values = new ContentValues();
	        values.put(history.EXAM_ID, his.exam_id); 
	        values.put(history.EXAM_NAME, his.exam_name); 
	     
	        // Inserting Row
	        mDb.myDataBase.insert(history.TABLE_NAME, null, values);
	        mDb.close(); // Closing database connection
	    }
	    
	    public void addExam(exam exams) {
		      
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        	ContentValues values = new ContentValues();
 		        values.put(exam.KEY_QUESTION, exams.getQuestion()); 
 		        values.put(exam.KEY_OPT1, exams.getOpt1()); 
 		        values.put(exam.KEY_OPT2, exams.getOpt2());
 		        values.put(exam.KEY_OPT3, exams.getOpt3());
 		        values.put(exam.KEY_OPT4, exams.getOpt4());
 		        values.put(exam.KEY_RIGHTOPT, exams.getRightOpt());
 		        values.put(exam.KEY_REPEAT, exams.getRepeat());
 		        values.put(exam.KEY_WRONG, exams.getWrong());
 		        values.put(exam.KEY_TYPE, exams.getType());
 		        values.put(exam.KEY_PIC, exams.getPic());
 		        
 		        mDb.myDataBase.insert(exam.TableName, null, values);
 		        mDb.close(); 
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	        }
	        
	    }
	    
	    public void Addsign(sign signs) {
		      
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        	ContentValues values = new ContentValues();
 		        values.put(sign.KEY_NAME, signs.getName());
 		        values.put(sign.KEY_PIC, signs.getpic());
 		        values.put(sign.KEY_CATEGORY, signs.getCategory()); 
 		        
 		     
 		        // Inserting Row
 		        mDb.myDataBase.insert(sign.TableName, null, values);
 		        mDb.close(); // Closing database connection
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	        }
	        
	    }

	    public void Addarchive(archive archives)
	    {
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(SQLException sqle){
 	        	createToast(3000, sqle.getMessage());
 	        }catch(Exception sqle){
 	        	createToast(3000, sqle.getMessage());
 	        }
	        ContentValues values = new ContentValues();
	        values.put(archive.KEY_EXAMID, archives.getExam_id());
	        values.put(archive.KEY_ROWID, archives.getId());
	        values.put(archive.KEY_ANSWER, archives.answer); 
	        // Inserting Row
	        mDb.myDataBase.insert(archive.TableName, null, values);
	        mDb.close(); // Closing database connection
	    }
	    //***********************
	    public rule getrules(int id) throws Exception{
	      
	    	rule rules=null;
	    	try {        
 	        	mDb.openDataBase();
 	        	Cursor cursor = mDb.myDataBase.query(rule.TableName, new String[] { rule.KEY_CATEGORY,
 		               rule.KEY_NAME,rule.KEY_ROWID,rule.KEY_CONTENT,rule.KEY_PIC  }, rule.KEY_ROWID + "=?",
 		                new String[] { String.valueOf(id) }, null, null, null, null);
 	        	
 		        if (cursor != null)
 		            cursor.moveToFirst();
 		       
 		       
 		        rules = new rule(cursor.getString(0),cursor.getString(1),cursor.getString(2),
 		                cursor.getString(3), cursor.getString(4));
 		        // return contact
 		       if (cursor != null && !cursor.isClosed()) {
 		             cursor.close();
 		          }
 		        mDb.close();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        
	        return rules;
	    }
	    //*************************************
	    public learn getlearn(int id)throws Exception {
		      
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        Cursor cursor = mDb.myDataBase.query(learn.TABLE_NAME, new String[] { learn.COL_TEXT,learn.COL_TITLE }, learn.COL_ID + "=?",
	                new String[] { String.valueOf(id) }, null, null, null, null);
	        if (cursor != null)
	            cursor.moveToFirst();
	        
	       
	        learn learns = new learn(id,cursor.getString(1),cursor.getString(0));
	        // return contact
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	        mDb.close();
	        return learns;
	    }
	    
	    /////////////////////////
	    public exam getExam(int id) throws Exception{
		      
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        Cursor cursor = mDb.myDataBase.query(exam.TableName, new String[] {exam.KEY_HELP, exam.KEY_QUESTION,
	               exam.KEY_OPT1,exam.KEY_OPT2,exam.KEY_OPT3,exam.KEY_OPT4,exam.KEY_RIGHTOPT,exam.KEY_REPEAT,
	               exam.KEY_WRONG,exam.KEY_TYPE,exam.KEY_PIC,exam.KEY_ROWID  }, exam.KEY_ROWID + "=?",
	                new String[] { String.valueOf(id) }, null, null, null, null);
	        if (cursor != null)
	            cursor.moveToFirst();
	        
	        exam exams= new exam(cursor.getString(0),cursor.getString(1),cursor.getString(2),
	                cursor.getString(3), cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),
	                cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(10));
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	        mDb.close();
	        return exams;
	    }
	    //////////////////////////////////////////////////
	    
	    public sign getsigns(int id) throws Exception{
		      
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        Cursor cursor = mDb.myDataBase.query(sign.TableName, new String[] {sign.KEY_NAME ,sign.KEY_PIC,
	           sign.KEY_ROWID, sign.KEY_CATEGORY,  }, sign.KEY_ROWID + "=?",
	                new String[] { String.valueOf(id) }, null, null, null, null);
	        if (cursor != null)
	            cursor.moveToFirst();
	        
	        sign signs= new sign(cursor.getString(0),cursor.getString(1),cursor.getString(2),
	                cursor.getString(3));
	        // return contact
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	        mDb.close();
	        return signs;
	    }
	 //////////////////////////////////////////////////////
	    
	    public List<rule> getAllrules()throws Exception {
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	    	rule rules;
	        List<rule> rulesList = new ArrayList<rule>();
	        // Select All Query
	        String selectQuery = "SELECT  * FROM " + rule.TableName;
	     
	       
	        Cursor cursor = mDb.myDataBase.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                rules=new rule();
	                rules.setCategory(cursor.getString(0));
	                rules.setName(cursor.getString(1));
	                rules.setID(cursor.getString(2));
	                rules.setContent(cursor.getString(3));
	                rules.setpic(cursor.getString(4));
	                
	                // Adding contact to list
	                rulesList.add(rules);
	            } while (cursor.moveToNext());
	        }
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	        mDb.close();
	        return rulesList;
	    }  
	    //////////////////////////////
	    
	    public List<exam> getAllexam() throws Exception{
	    	exam exams;
	        List<exam> examList = new ArrayList<exam>();
	    	 try {        
	 	        	mDb.openDataBase();
	 	        // Select All Query
	 		        String selectQuery = "SELECT  * FROM " + exam.TableName;
	 		        Cursor cursor = mDb.myDataBase.rawQuery(selectQuery, null);
	 		     
	 		        // looping through all rows and adding to list
	 		        if (cursor.moveToFirst()) {
	 		            do {
	 		               
	 		            	exams=new exam();
	 		                exams.setQuestion(cursor.getString(0));
	 		                exams.setOpt1(cursor.getString(1));
	 		                exams.setOpt2(cursor.getString(2));
	 		                exams.setOpt3(cursor.getString(3));
	 		                exams.setOpt4(cursor.getString(4));
	 		                exams.setRightOpt(cursor.getString(5));
	 		                exams.setRepeat(cursor.getString(6));
	 		                exams.setWrong(cursor.getString(7));
	 		                exams.setType(cursor.getString(8));
	 		                exams.setPic(cursor.getString(9));
	 		                exams.setID(cursor.getString(10));
	 		                
	 		                // Adding contact to list
	 		                examList.add(exams);
	 		            } while (cursor.moveToNext());
	 		        }	 		 
	 		       if (cursor != null && !cursor.isClosed()) {
	 		             cursor.close();
	 		          }
	 		        mDb.close();
	 	        }catch(Exception sqle){
	 	        	createToast(3000,sqle.getMessage());
	 	        	throw sqle;
	 	        }
	        return examList;
	    }        
	    
	    //////////////////////////////
	    public List<archive> getAllarchive()throws Exception
	    {
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        	createToast(3000,sqle.getMessage());
 	        	throw sqle;
 	        }
	    	//
	    	archive archives;
	    	List<archive> archiveList = new ArrayList<archive>();
	    	// Select All Query
	    	String selectQuery = "SELECT  * FROM " + archive.TableName;
	    	Cursor cursor = mDb.myDataBase.rawQuery(selectQuery, null);
	    	// looping through all rows and adding to list
	    	if (cursor.moveToFirst()) {
            do {
               
            	archives=new archive();
            	archives.setExam_id(cursor.getString(0));
            	archives.setId(cursor.getString(1));
            	archives.setAnswer(cursor.getString(2));             
                // Adding contact to list
            	archiveList.add(archives);
            } while (cursor.moveToNext());
        }
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	     mDb.close();
        return archiveList;
	    }
	    
	    ///////////////////////////////
	    public List<sign> getAllsign()throws Exception {
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	    	
	    sign signs;
	        List<sign> signList = new ArrayList<sign>();
	        // Select All Query
	        String selectQuery = "SELECT  * FROM " + sign.TableName;
	     
	       
	        Cursor cursor = mDb.myDataBase.rawQuery(selectQuery, null);
	     
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	               signs=new sign();
	            	signs.setName(cursor.getString(0));
	            	 signs.setpic(cursor.getString(1));
	            	   signs.setID(cursor.getString(2));
	            	signs.setCategory(cursor.getString(3));
	               
	             
	               
	                
	                // Adding contact to list
	                signList.add(signs);
	            } while (cursor.moveToNext());
	        }
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	        mDb.close();
	        return signList;
	    }
	   ///////////////////////////////// 
	    public List<List<Object>> selectRecordsFromDBList(String query)throws Exception { 
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	          List<List<Object>> retList = new ArrayList<List<Object>>();
	        List<Object> list = new ArrayList<Object>();
	          Cursor cursor = mDb.myDataBase.rawQuery(query, null);            
	          if (cursor.moveToFirst()) {
	             do {
	                 list = new ArrayList<Object>();
	                 for(int i=0; i<cursor.getColumnCount(); i++){                   
	                     list.add( cursor.getString(i) );
	                 }   
	                 retList.add(list);
	             } while (cursor.moveToNext());
	          }
	          if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	          mDb.close();
	          return retList;
	       }
		

	    
	    
	    
		 ////////////////////
		 public Cursor fetchData(String query) throws Exception {
			 
			 try {        
	 	        	mDb.openDataBase();
	 	        }catch(Exception sqle){
	 	        createToast(3000,sqle.getMessage());
	 	       throw sqle;
	 	        }
			 Cursor mCursor=mDb.myDataBase.rawQuery(query,null);
			 if (mCursor != null && !mCursor.isClosed()) {
				 mCursor.close();
	          }
            mDb.close();
			 return mCursor;
			 }
		 /////////////////////////
	    
	    public int getrulesCount() throws Exception{
	    	
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        String countQuery = "SELECT  * FROM " + rule.TableName ;
	        Cursor cursor = mDb.myDataBase.rawQuery(countQuery, null);
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	 
	        // return count
	        mDb.close();
	        return cursor.getCount();
	    }
	    
	    /////////////////
	    public int getExamCount()throws Exception {
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        String countQuery = "SELECT  * FROM " + exam.TableName ;
	        Cursor cursor = mDb.myDataBase.rawQuery(countQuery, null);
	 
	        // return count
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	        mDb.close();
	        return cursor.getCount();
	    }
	    //////////////////////////
	    public int getsignCount()throws Exception {
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        String countQuery = "SELECT  * FROM " + sign.TableName ;
	        Cursor cursor = mDb.myDataBase.rawQuery(countQuery, null);
	        if (cursor != null && !cursor.isClosed()) {
	             cursor.close();
	          }
	 
	        // return count
	        mDb.close();
	        return cursor.getCount();
	    }
	    //////////////////////
	    // Updating single contact
	    public int updaterules(rule rules)throws Exception {
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	    
	        ContentValues values = new ContentValues();
	        values.put(rule.KEY_CATEGORY, rules.getCategory()); 
	        values.put(rule.KEY_NAME, rules.getName()); 
	        values.put(rule.KEY_CONTENT, rules.getContent());
	        values.put(rule.KEY_PIC, rules.getpic());
	        mDb.close();
	        // updating row
	        return mDb.myDataBase.update(rule.TableName, values, rule.KEY_ROWID + " = ?",
	                new String[] { String.valueOf(rules.getID()) });
	    }
	    
	    
	    /////////////////////////////////
	    
	    public int updateExam(exam exams)throws Exception {
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        ContentValues values = new ContentValues();
	        values.put(exam.KEY_QUESTION, exams.getQuestion()); 
	        values.put(exam.KEY_OPT1, exams.getOpt1()); 
	        values.put(exam.KEY_OPT2, exams.getOpt2());
	        values.put(exam.KEY_OPT3, exams.getOpt3());
	        values.put(exam.KEY_OPT4, exams.getOpt4());
	        values.put(exam.KEY_RIGHTOPT, exams.getRightOpt());
	        values.put(exam.KEY_REPEAT, exams.getRepeat());
	        values.put(exam.KEY_WRONG, exams.getWrong());
	        values.put(exam.KEY_TYPE, exams.getType());
	        values.put(exam.KEY_PIC, exams.getPic());
	        mDb.close();
	        // updating row
	        return mDb.myDataBase.update(exam.TableName, values, exam.KEY_ROWID + " = ?",
	                new String[] { String.valueOf(exams.getID()) });
	    }  
	    /////////////////////////////////////
	    public int updatesign(sign signs)throws Exception {
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        ContentValues values = new ContentValues();
	        values.put(sign.KEY_NAME, signs.getName());
	        values.put(sign.KEY_PIC, signs.getpic());
	        values.put(sign.KEY_CATEGORY, signs.getCategory()); 
	         mDb.close(); 
	        // updating row
	        return mDb.myDataBase.update(sign.TableName, values, sign.KEY_ROWID + " = ?",
	                new String[] { String.valueOf(signs.getID()) });
	    }
	    ///////////////////////////////
	    
	    public void deleterules(rule rules)throws Exception {
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        mDb.myDataBase.delete(rule.TableName, rule.KEY_ROWID + " = ?",
	                new String[] { String.valueOf(rules.getID()) });
	        mDb.close();
	    }
	 /////////////////////////////////////////
	    public void deleteExam(exam exams)throws Exception {
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	        mDb.myDataBase.delete(exam.TableName,exam. KEY_ROWID + " = ?",
	                new String[] { String.valueOf(exams.getID()) });
	        mDb.close();
	    }
	 ///////////////////////////////////////////////////////
	    public void deletesign(sign signs)throws Exception {
	    	
	    	try {        
 	        	mDb.openDataBase();
 	        	 mDb.myDataBase.delete(sign.TableName, sign.KEY_ROWID + " = ?",
 		                new String[] { String.valueOf(signs.getID()) });
 	        }catch(Exception sqle){
 	        createToast(3000,sqle.getMessage());
 	       throw sqle;
 	        }
	       
	        mDb.close();
	    }
	    //***************
	    public  void  createToast(int duration,String str)
		{
			LayoutInflater inflater = act.getLayoutInflater();
			View layout = inflater.inflate(R.layout.toast_layout,
			                               (ViewGroup) act.findViewById(R.id.toast_layout_root));

			TextView text = (TextView) layout.findViewById(R.id.text);
			//text.setText(PersianReshape.reshape(str));
            text.setText(str);
			Typeface face = Typeface.createFromAsset(mCtx.getAssets(),"font/"+com.github.florent37.materialviewpager.sample.attrib.attribute.font_title1);
			text.setTypeface(face);
			text.setTextSize(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_size);
			text.setTextColor(com.github.florent37.materialviewpager.sample.attrib.attribute.title1_font_color);
			Toast toast = new Toast(act.getApplicationContext());
			//toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
			toast.setDuration(duration);
			toast.setView(layout);
			toast.show();
		}
	 
}		
		



	    
	    
	    
	    
	    
	    
	    

	

	    


	



