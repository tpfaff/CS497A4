package com.example.assignment4;

import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	Cursor dbCursor=null;
	SQLiteDatabase db;
	
	String title1=null;
	String title2=null;
	String title3=null;
	
	int drawable1Resource=0;
	int drawable2Resource=0;
	int drawable3Resource=0;
	
	float rating1=0;
	float rating2=0;
	float rating3=0;
	
	ImageView img1=null;
	ImageView img2=null;
	ImageView img3=null;
	
	TextView tv1=null;
	TextView tv2=null;
	TextView tv3=null;
	
	RatingBar ratingbar1 =null;
	RatingBar ratingbar2 =null;
	RatingBar ratingbar3 =null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		
		img1=(ImageView)findViewById(R.id.img_view_1);
		img2=(ImageView)findViewById(R.id.img_view_2);
		img3=(ImageView)findViewById(R.id.img_view_3);
	
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		tv3=(TextView)findViewById(R.id.textView3);
		
		ratingbar1 =(RatingBar)findViewById(R.id.rating_bar_1);
		ratingbar2 =(RatingBar)findViewById(R.id.rating_bar_2);
		ratingbar3 =(RatingBar)findViewById(R.id.rating_bar_3);
		deleteDatabase();
		initDatabase();
		
		db=initDatabase();
		                  //table //columns (null gives all columns) //WHERE clause
		dbCursor=db.query("Pics",null,null,null,null,null,null);//get the whole table
		
		getDrawablesFromDb();	
		setDrawables();
		getRatingsFromDb();
		setRatings();
		getTitlesFromDb();
		setTitles();
		
		 db.close();
	}
	
	

	private void setTitles() {
		tv1.setText(title1);
		tv2.setText(title2);
		tv3.setText(title3);
		
	}



	private void getTitlesFromDb() {
		if(dbCursor.moveToFirst()){
			title1=dbCursor.getString(0);
			dbCursor.moveToNext();
			title2=dbCursor.getString(0);
			dbCursor.moveToNext();
			title3=dbCursor.getString(0);
		}
	}



	private void setRatings() {
		ratingbar1.setRating(rating1);
		ratingbar2.setRating(rating2);
		ratingbar3.setRating(rating3);
	}



	private void getRatingsFromDb() {
		if(dbCursor.moveToFirst()){
			rating1=dbCursor.getFloat(2);
			dbCursor.moveToNext();
			rating2=dbCursor.getFloat(2);
			dbCursor.moveToNext();
			rating3=dbCursor.getFloat(2);
		}
		
	}



	private void setDrawables() {
		Drawable drawable1=getResources().getDrawable(drawable1Resource);
		Drawable drawable2=getResources().getDrawable(drawable2Resource);
		Drawable drawable3=getResources().getDrawable(drawable3Resource);
		
		img1.setImageDrawable(drawable1);
		img2.setImageDrawable(drawable2);
		img3.setImageDrawable(drawable3);
	}



	private boolean deleteDatabase() {
		File f= new File("\\Database");
		return SQLiteDatabase.deleteDatabase(f);
	}

	private SQLiteDatabase initDatabase(){
		db=this.openOrCreateDatabase("Database",this.MODE_PRIVATE,null);
		
		db.execSQL("CREATE TABLE IF NOT EXISTS Pics(DrawableName TEXT,DrawableReference INTEGER,PictureRating REAL)");
	    dbCursor=db.query("Pics",null,null,null,null,null,null);//get the whole table
	 if(!dbCursor.moveToFirst()){//if theres not an element in the db yet, insert stuff
		db.execSQL("INSERT INTO Pics VALUES('"+"Space1"+"\'"+","+"\'"+R.drawable.space1+"\'"+","+"\'"+0+"')");
		db.execSQL("INSERT INTO Pics VALUES('"+"Space2"+"\'"+","+"\'"+R.drawable.space2+"\'"+","+"\'"+0+"')");
		db.execSQL("INSERT INTO Pics VALUES('"+"Space3"+"\'"+","+"\'"+R.drawable.space3+"\'"+","+"\'"+0+"')");

		}
	 return db;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private boolean getDrawablesFromDb(){
		if(dbCursor.moveToFirst()){
			drawable1Resource=dbCursor.getInt(1); //get the first image's drawable folder reference
			dbCursor.moveToNext();
			drawable2Resource=dbCursor.getInt(1);//get the second image's drawable folder reference
			dbCursor.moveToNext();
			drawable3Resource=dbCursor.getInt(1);//get the third image's drawable folder reference
			return true;
		}
		return false;
}
}
