package com.github.florent37.materialviewpager.sample.attrib;


import android.app.Activity;
import android.graphics.Color;

public  class attribute 
{
	
	public static Activity pre=null;
	
	public static int TEXT_SIZE_SMALL=0;
	public static int TEXT_SIZE_MEDIUM=1;
	public static int TEXT_SIZE_LARG=2;
	public static int TEXT_SIZE=TEXT_SIZE_MEDIUM;
	
	public static int[][] SIZE={{10,17,12,12,12,17,17,17,7},{13,20,15,15,15,20,20,20,10},{16,23,18,18,18,23,23,23,13}};
	
	public static int content_font_color=Color.DKGRAY;
	public static int title_font_color=Color.DKGRAY;
	public static int title1_font_color=Color.DKGRAY;
	public static int title2_font_color=Color.DKGRAY;
	public static int title3_font_color=Color.DKGRAY;
	public static int menu_font_color=Color.WHITE;
	public static int small_font_color=Color.DKGRAY;
	
	public static int exam_question_number=15;
	public static int exam_question_pass=14;
	public static int exam_time_min=10;
	
	public static String font_content="irsans.ttf";
	public static String font_title="BYEKAN.TTF";
	public static String font_menu="BYEKAN.TTF";
	public static String font_title1="irsans.ttf";
	public static String font_title2="irsans.ttf";
	public static String font_title3="irsans.ttf";
	public static int content_font_size=SIZE[TEXT_SIZE][0];
	public static int title_font_size=SIZE[TEXT_SIZE][1];
	public static int title1_font_size=SIZE[TEXT_SIZE][2];
	public static int title2_font_size=SIZE[TEXT_SIZE][3];
	public static int title3_font_size=SIZE[TEXT_SIZE][4];
	public static int menu_font_size=SIZE[TEXT_SIZE][5];
	public static int quest_font_size=SIZE[TEXT_SIZE][6];
	public static int option_font_size=SIZE[TEXT_SIZE][7];
	public static int small_font_size=SIZE[TEXT_SIZE][8];
	//
	public static boolean IsMute=false;

	public static boolean AUTOUPDATE=true;//
	//
	public static final int SWIPE_MIN_DISTANCE = 80;
	public static final int SWIPE_MAX_OFF_PATH = 250;
	public static final int SWIPE_THRESHOLD_VELOCITY = 200;
	
	public static void refresh()
	{
		 content_font_size=SIZE[TEXT_SIZE][0];
		 title_font_size=SIZE[TEXT_SIZE][1];
		 title1_font_size=SIZE[TEXT_SIZE][2];
		 title2_font_size=SIZE[TEXT_SIZE][3];
		 title3_font_size=SIZE[TEXT_SIZE][4];
		 menu_font_size=SIZE[TEXT_SIZE][5];
		 quest_font_size=SIZE[TEXT_SIZE][6];
		 option_font_size=SIZE[TEXT_SIZE][7];
		 small_font_size=SIZE[TEXT_SIZE][8];
	}
	
	public static int RESHAPE=2;
	
	//
}
